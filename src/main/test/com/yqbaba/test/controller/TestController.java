package com.yqbaba.test.controller;

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
