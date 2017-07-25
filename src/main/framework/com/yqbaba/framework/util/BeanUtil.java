package com.yqbaba.framework.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class BeanUtil {

	private static final Object[] EMPTY_ARRAY = new Object[0];
	private static final JSONConfig JSON_CONFIG = new JSONConfig();

	public static void populateMap(Map map, Object obj) {
		PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(obj.getClass());
		for (int i = 0; i < descriptors.length; i++) {
			PropertyDescriptor dsc = descriptors[i];
			Method method = dsc.getReadMethod();
			if (method == null) {
				continue;
			}

			try {
				Object value = method.invoke(obj, EMPTY_ARRAY);
				if (value != null) {
					map.put(dsc.getName(), value);
				}
			} catch (InvocationTargetException e) {
				throw new RuntimeException(e.getTargetException());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static void populateMap(Map map, Object obj, String... props) {
		if (props == null || props.length == 0) {
			populateMap(map, obj);
			return;
		}

		Map<String, Map> propMap = getPropMap(props);
		fillMap(map, obj, propMap);
	}

	public static Map toMap(Object obj) {
		Map m = new HashMap();
		populateMap(m, obj);
		return m;
	}

	public static Map toMap(Object obj, String... props) {
		Map m = new HashMap();
		populateMap(m, obj, props);
		return m;
	}

	public static void copyProperties(Object dest, Object orig) {
		try {
			PropertyUtils.copyProperties(dest, orig);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e.getTargetException());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Object getProperty(Object obj, String propertyName) {
		try {
			return PropertyUtils.getProperty(obj, propertyName);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e.getTargetException());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Method getMethod(Class clazz, String methodName) throws NoSuchMethodException {
		for (Method method : clazz.getMethods()) {
			if (methodName.equals(method.getName())) {
				return method;
			}
		}

		throw new NoSuchMethodException(methodName + "() is not defined in " + clazz);
	}

	public static String json(Object obj) {
		if (obj == null) {
			return null;
		}

		return JSONObject.fromObject(obj, JSON_CONFIG).toString();
	}

	public static String json(Object obj, String... props) {
		if (props == null || props.length == 0) {
			return json(obj);
		}

		if (obj == null) {
			return null;
		}

		Map<String, Map> propMap = getPropMap(props);

		Map m = new HashMap();
		fillMap(m, obj, propMap);
		return JSONObject.fromObject(m, JSON_CONFIG).toString();
	}

	private static void fillMap(Map m, Object obj, Map<String, Map> propMap) {
		for (Entry<String, Map> e : propMap.entrySet()) {
			String prop = e.getKey();
			Object value = getProperty(obj, prop);
			if (value == null) {
				continue;
			}

			Map<String, Map> subprops = e.getValue();
			if (subprops.isEmpty()) {
				m.put(prop, value);
			} else {
				if (value instanceof Iterable) {
					List list = new ArrayList();
					for (Object o : (Iterable) value) {
						Map mm = new HashMap();
						fillMap(mm, o, subprops);
						list.add(mm);
					}

					m.put(prop, list);
				} else {
					Map mm = new HashMap();
					m.put(prop, mm);
					fillMap(mm, value, subprops);
				}
			}
		}
	}

	private static Map<String, Map> getPropMap(String... props) {
		Map<String, Map> propMap = new HashMap<String, Map>();
		for (String key : props) {
			int splitIndex = key.indexOf('.');
			Map<String, Map> map = propMap;

			while (splitIndex != -1) {
				String prefix = key.substring(0, splitIndex);
				Map<String, Map> prop = (Map<String, Map>) map.get(prefix);
				if (prop == null) {
					prop = new HashMap<String, Map>();
					map.put(prefix, prop);
				}

				key = key.substring(splitIndex + 1);
				splitIndex = key.indexOf('.');
				map = prop;
			}

			map.put(key, Collections.EMPTY_MAP);
		}

		return propMap;
	}

	public static String jsonWithout(Object obj, String... props) {
		if (props == null || props.length == 0) {
			return json(obj);
		}

		if (obj == null) {
			return null;
		}

		Map<String, Map> propMap = getPropMap(props);

		Map m = new HashMap();
		fillMapWithout(m, obj, propMap);
		return JSONObject.fromObject(m, JSON_CONFIG).toString();
	}

	/**
	 * 只序列化可写属性，常用于存取数据库
	 */
	public static String jsonForWritables(Object obj) {
		return jsonForWritables(obj, (Set<String>) null);
	}

	private static String jsonForWritables(Object obj, Set<String> ignorePropSet) {
		if (obj == null) {
			return null;
		}

		Map m = new HashMap();
		fillMapWithWritables(m, obj, ignorePropSet);
		return JSONObject.fromObject(m, JSON_CONFIG).toString();
	}

	/**
	 * 只序列化可写属性，并排除指定的属性名，常用于存取数据库
	 * 注意：如果ignoreProps指定了gmtModified，那么obj及obj的子孙对象上的gmtModified都会被排除在外
	 */
	public static String jsonForWritables(Object obj, String... ignoreProps) {
		Set<String> ignorePropSet;
		if (ignoreProps != null && ignoreProps.length > 0) {
			ignorePropSet = new HashSet<String>();
			for (String p : ignoreProps) {
				ignorePropSet.add(p);
			}
		} else {
			ignorePropSet = null;
		}

		return jsonForWritables(obj, ignorePropSet);
	}

	public static String jsonArrayForWritables(Collection coll) {
		return jsonArrayForWritables(coll, (String[]) null);
	}

	public static String jsonArrayForWritables(Collection coll, String... ignoreProps) {
		if (coll == null) {
			return null;
		}

		Set<String> ignorePropSet;
		if (ignoreProps != null && ignoreProps.length > 0) {
			ignorePropSet = new HashSet<String>();
			for (String p : ignoreProps) {
				ignorePropSet.add(p);
			}
		} else {
			ignorePropSet = null;
		}

		List list = new ArrayList();
		for (Object obj : coll) {
			Map m = new HashMap();
			fillMapWithWritables(m, obj, ignorePropSet);
			list.add(m);
		}

		return JSONArray.fromObject(list, JSON_CONFIG).toString();
	}

	public static String jsonArrayForWritables(Object[] arr) {
		if (arr == null) {
			return null;
		}

		return jsonArrayForWritables(Arrays.asList(arr));
	}

	public static String jsonArrayForWritables(Object[] arr, String... ignoreProps) {
		if (arr == null) {
			return null;
		}

		return jsonArrayForWritables(Arrays.asList(arr), ignoreProps);
	}

	private static void fillMapWithWritables(Map m, Object obj, Set<String> ignorePropSet) {
		PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(obj.getClass());
		for (int i = 0; i < descriptors.length; i++) {
			PropertyDescriptor dsc = descriptors[i];
			String prop = dsc.getName();

			if (ignorePropSet != null && ignorePropSet.contains(prop)) {
				continue;
			}

			Method wm = dsc.getWriteMethod();
			if (wm == null) {
				continue;
			}
			Method rm = dsc.getReadMethod();
			if (rm == null) {
				continue;
			}

			Object value;
			try {
				value = rm.invoke(obj, EMPTY_ARRAY);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

			if (value == null) {
				continue;
			}

			Class clazz = value.getClass();
			if (clazz.isPrimitive() || String.class.isAssignableFrom(clazz) || Number.class.isAssignableFrom(clazz)
					|| Boolean.class.isAssignableFrom(clazz)) {

				m.put(prop, value);
			} else if (value instanceof Iterable) {
				List list = new ArrayList();
				for (Object o : (Iterable) value) {
					Map mm = new HashMap();
					fillMapWithWritables(mm, o, ignorePropSet);
					list.add(mm);
				}

				m.put(prop, list);
			} else if (value instanceof Date) {
				Map mm = new HashMap();
				m.put(prop, mm);
				mm.put("time", ((Date) value).getTime());
			} else {
				Map mm = new HashMap();
				m.put(prop, mm);
				fillMapWithWritables(mm, value, ignorePropSet);
			}
		}
	}

	private static void fillMapWithout(Map m, Object obj, Map<String, Map> propMap) {
		PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(obj.getClass());
		for (int i = 0; i < descriptors.length; i++) {
			PropertyDescriptor dsc = descriptors[i];
			Method method = dsc.getReadMethod();
			if (method == null) {
				continue;
			}

			Object value;
			try {
				value = method.invoke(obj, EMPTY_ARRAY);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

			if (value == null) {
				continue;
			}

			String prop = dsc.getName();
			Map<String, Map> subprops = propMap.get(prop);
			if (subprops == null) {
				m.put(prop, value);
			} else if (!subprops.isEmpty()) {
				if (value instanceof Iterable) {
					List list = new ArrayList();
					for (Object o : (Iterable) value) {
						Map mm = new HashMap();
						fillMapWithout(mm, o, subprops);
						list.add(mm);
					}

					m.put(prop, list);
				} else {
					Map mm = new HashMap();
					m.put(prop, mm);
					fillMapWithout(mm, value, subprops);
				}
			}
		}
	}

	public static String jsonArray(Object obj) {
		if (obj == null) {
			return null;
		}

		return JSONArray.fromObject(obj, JSON_CONFIG).toString();
	}

	public static String jsonArray(Collection coll, String... props) {
		if (props == null || props.length == 0) {
			return jsonArray(coll);
		}

		if (coll == null) {
			return null;
		}

		Map<String, Map> propMap = getPropMap(props);
		List list = new ArrayList();
		for (Object obj : coll) {
			Map m = new HashMap();
			fillMap(m, obj, propMap);
			list.add(m);
		}

		return JSONArray.fromObject(list, JSON_CONFIG).toString();
	}

	public static String jsonArray(Object[] arr, String... props) {
		if (arr == null) {
			return null;
		}

		return jsonArray(Arrays.asList(arr), props);
	}

	public static String jsonArrayWithout(Collection coll, String... props) {
		if (props == null || props.length == 0) {
			return jsonArray(coll);
		}

		if (coll == null) {
			return null;
		}

		Map<String, Map> propMap = getPropMap(props);
		List list = new ArrayList();
		for (Object obj : coll) {
			Map m = new HashMap();
			fillMapWithout(m, obj, propMap);
			list.add(m);
		}

		return JSONArray.fromObject(list, JSON_CONFIG).toString();
	}

	public static String jsonArrayWithout(Object[] arr, String... props) {
		if (arr == null) {
			return null;
		}

		return jsonArrayWithout(Arrays.asList(arr), props);
	}

	public static <T> T json2Bean(String str, Class<T> clazz) {
		return json2Bean(str, clazz, null);
	}

	public static <T> T json2Bean(String str, Class<T> clazz, Map<String, Class> classMap) {
		JSONObject jsonObject = JSONObject.fromObject(str);
		JSONConfig jsonConfig = new JSONConfig();
		jsonConfig.setRootClass(clazz);

		if (classMap != null) {
			jsonConfig.setClassMap(classMap);
		}

		return (T) JSONObject.toBean(jsonObject, jsonConfig);
	}

	public static <T> List<T> json2List(String str, Class<T> clazz) {
		return json2List(str, clazz, null);
	}

	public static <T> List<T> json2List(String str, Class<T> clazz, Map<String, Class> classMap) {
		JSONArray jsonArray = JSONArray.fromObject(str);
		JSONConfig jsonConfig = new JSONConfig();
		jsonConfig.setRootClass(clazz);

		if (classMap != null) {
			jsonConfig.setClassMap(classMap);
		}

		return (List<T>) JSONArray.toCollection(jsonArray, jsonConfig);
	}

}
