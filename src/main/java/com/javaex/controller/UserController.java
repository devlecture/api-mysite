package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.UserService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	//로그인
	// *로그인 필요없음
	@PostMapping(value = "/login")
	public JsonResult login(@RequestBody UserVo userVo, HttpServletResponse response) {
		System.out.println("UserController.login()");

		UserVo authUser = userService.exeLogin(userVo);
		System.out.println(authUser);
		if (authUser != null) {
			// 로그인 성공
			System.out.println("로그인 성공");

			// token생성하고 응답해더에 추가 //여기서는 사용자 no를 문자열로 넘김 //토큰이 주체는 문자열로 넘겨야함
			JwtUtil.createTokenAndSetHeader(response, "" + authUser.getNo());

			// 응답 데이터
			return JsonResult.success(authUser);

		} else {
			// 로그인 실패
			return JsonResult.fail("인증실패");
		}
	}


	// 회원정보 수정폼
	// *로그인 필요
	@GetMapping(value = "/modify")
	public JsonResult modifyform(HttpServletRequest request) {
		System.out.println("UserController.modifyform()");

		int no = JwtUtil.getNoFromHeader(request);

		if (no != -1) {
			UserVo userVo = userService.exeModifyForm(no);
			return JsonResult.success(userVo);

		} else {
			return JsonResult.fail("토큰오류"); // 클라이언트 토큰삭제처리

		}

	}

	// 회원정보 수정
	// *로그인 필요
	@PutMapping(value = "/modify")
	public JsonResult modify(HttpServletRequest request, @RequestBody UserVo userVo) {
		System.out.println("UserController.modify()");

		//로그인이 필요할때는 토큰에서 no가져옴
		int no = JwtUtil.getNoFromHeader(request);

		//토큰이 없으면 -1
		if (no != -1) {
			userVo.setNo(no);
			userService.exeModify(userVo);

			return JsonResult.success(userVo.getName());

		} else {
			return JsonResult.fail("토큰없음");
			
		}

	}
}
