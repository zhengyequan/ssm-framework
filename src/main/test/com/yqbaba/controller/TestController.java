package com.yqbaba.controller;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yqbaba.baba.entity.LoupanInfo;
import com.yqbaba.baba.service.BabaService;
import com.yqbaba.framework.util.BeanUtil;
import com.yqbaba.framework.util.DateUtil;
import com.yqbaba.framework.util.StringUtil;

@Controller
@Scope("prototype")
@RequestMapping("test")
public class TestController {

	@Resource
	private BabaService babaService;

	@RequestMapping("/createLoupanInfo.do")
	public String createLoupanInfo(LoupanInfo loupan, String openDate, String rootDir) {
		loupan.setGmtOpen(DateUtil.parse(openDate, "yyyy-MM-dd"));
		loupan.setIndexImgs(getImgNamesFromDesk(rootDir, "index"));
		loupan.setPlanImgs(getImgNamesFromDesk(rootDir, "plan"));
		loupan.setEffectImgs(getImgNamesFromDesk(rootDir, "effect"));
		loupan.setSupportImgs(getImgNamesFromDesk(rootDir, "support"));
		loupan.setModelImgs(getImgNamesFromDesk(rootDir, "model"));
		loupan.setLayoutImgs(getImgNamesFromDesk(rootDir, "layout"));

		babaService.createLoupanInfo(loupan);
		return "common/json_success";
	}

	/** 查看该房产是否整理过 */
	@RequestMapping("/confirmHas.do")
	public String getByName(ModelMap map, String name) {
		LoupanInfo loupan = babaService.getByName(name);
		map.put("data", BeanUtil.json(loupan));
		return "common/json_success";
	}

	/** png -- > jpg， 只改后缀 */
	@RequestMapping("/png2Jpg.do")
	public String png2Jpg(int id) {
		LoupanInfo loupan = babaService.getById(id);
		String indexImgs = loupan.getIndexImgs();
		if (StringUtil.isNotBlank(indexImgs)) {
			List<String> indexList = BeanUtil.json2List(indexImgs, String.class);
			List<String> indexRes = new ArrayList<String>();
			for (String str : indexList) {
				str = str.substring(0, str.lastIndexOf(".")) + ".jpg";
				indexRes.add(str);
			}
			loupan.setIndexImgs(BeanUtil.jsonArray(indexRes));
		}

		String planImgs = loupan.getPlanImgs();
		if (StringUtil.isNotBlank(planImgs)) {
			List<String> planList = BeanUtil.json2List(planImgs, String.class);
			List<String> planRes = new ArrayList<String>();
			for (String str : planList) {
				str = str.substring(0, str.lastIndexOf(".")) + ".jpg";
				planRes.add(str);
			}
			loupan.setPlanImgs(BeanUtil.jsonArray(planRes));
		}

		String effectImgs = loupan.getEffectImgs();
		if (StringUtil.isNotBlank(effectImgs)) {
			List<String> effectList = BeanUtil.json2List(effectImgs, String.class);
			List<String> effectRes = new ArrayList<String>();
			for (String str : effectList) {
				str = str.substring(0, str.lastIndexOf(".")) + ".jpg";
				effectRes.add(str);
			}
			loupan.setEffectImgs(BeanUtil.jsonArray(effectRes));
		}

		String supportImgs = loupan.getSupportImgs();
		if (StringUtil.isNotBlank(supportImgs)) {
			List<String> supportList = BeanUtil.json2List(supportImgs, String.class);
			List<String> supportRes = new ArrayList<String>();
			for (String str : supportList) {
				str = str.substring(0, str.lastIndexOf(".")) + ".jpg";
				supportRes.add(str);
			}
			loupan.setSupportImgs(BeanUtil.jsonArray(supportRes));
		}

		String modelImgs = loupan.getModelImgs();
		if (StringUtil.isNotBlank(modelImgs)) {
			List<String> modelList = BeanUtil.json2List(modelImgs, String.class);
			List<String> modelRes = new ArrayList<String>();
			for (String str : modelList) {
				str = str.substring(0, str.lastIndexOf(".")) + ".jpg";
				modelRes.add(str);
			}
			loupan.setModelImgs(BeanUtil.jsonArray(modelRes));
		}

		String layoutImgs = loupan.getLayoutImgs();
		if (StringUtil.isNotBlank(layoutImgs)) {
			List<String> layoutList = BeanUtil.json2List(layoutImgs, String.class);
			List<String> layoutRes = new ArrayList<String>();
			for (String str : layoutList) {
				str = str.substring(0, str.lastIndexOf(".")) + ".jpg";
				layoutRes.add(str);
			}
			loupan.setLayoutImgs(BeanUtil.jsonArray(layoutRes));
		}

		babaService.png2Jpg(loupan);
		return "common/json_success";
	}

	/** 读取图片并重命名 */
	private String getImgNamesFromDesk(String root, String sub) {
		File rootDir = new File("D:/" + root + "/");
		System.out.println(rootDir.isDirectory());
		File[] subDirs = rootDir.listFiles(new FileFilter() {
			public boolean accept(File file) {
				if (StringUtil.equals(file.getName(), sub)) {
					return true;
				}
				return false;
			}
		});

		if (subDirs.length > 0) {
			File file = subDirs[0];
			File[] imgs = file.listFiles();
			for (int i = 0; i < imgs.length; i++) {
				File img = imgs[i];
				img.renameTo(new File("D:/" + root + "/" + sub + "/" + sub + "_" + (i + 1) + ".jpg"));
			}
		}

		List<String> imgList = new ArrayList<String>();
		if (subDirs.length > 0) {
			File file = subDirs[0];
			File[] imgs = file.listFiles();
			for (int i = 0; i < imgs.length; i++) {
				File img = imgs[i];
				imgList.add("images/loupan/hangzhou/" + root + "/" + sub + "/" + img.getName());
			}
		}

		return BeanUtil.jsonArray(imgList);
	}

}
