package com.yqbaba.framework.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JSONConfig extends JsonConfig {

	private static final JsonValueProcessor BIGDECIMAL_PROCESSOR = new JsonValueProcessor() {

		@Override
		public Object processArrayValue(Object value, JsonConfig jsonConfig) {
			return process(value, jsonConfig);
		}

		@Override
		public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
			return process(value, jsonConfig);
		}

		private Object process(Object value, JsonConfig jsonConfig) {
			return value == null ? null : value.toString();
		}
	};

	private static final JsonValueProcessor DATE_PROCESSOR = new JsonValueProcessor() {

		@Override
		public Object processArrayValue(Object value, JsonConfig jsonConfig) {
			return process(value, jsonConfig);
		}

		@Override
		public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
			return process(value, jsonConfig);
		}

		private Object process(Object value, JsonConfig jsonConfig) {
			if (value instanceof Date) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return sdf.format(value);
			}
			return value == null ? "" : value.toString();
		}

	};

	public JSONConfig() {
		registerJsonValueProcessor(BigDecimal.class, BIGDECIMAL_PROCESSOR);
		registerJsonValueProcessor(Date.class, DATE_PROCESSOR);
	}

}

