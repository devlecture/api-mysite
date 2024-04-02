package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.AttachService;
import com.javaex.util.JsonResult;
import com.javaex.vo.UserVo;

@RestController
@RequestMapping(value = "/api/attach")
public class AttachController {

	@Autowired
	private AttachService attachService;
	
	@PostMapping(value = "")
	public JsonResult upload(@RequestParam MultipartFile file, @ModelAttribute UserVo userVo) {
		System.out.println("AttachController.upload()");
		
		System.out.println(file.isEmpty());
		System.out.println(file.getOriginalFilename());
		System.out.println(userVo);
		
		String saveName = attachService.exeUpload(file);

		// 응답 데이터
		return JsonResult.success(saveName);

	}
}
