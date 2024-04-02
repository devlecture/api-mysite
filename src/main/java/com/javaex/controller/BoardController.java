package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.BoardService;
import com.javaex.util.JsonResult;
import com.javaex.vo.BoardVo;

@RestController
@RequestMapping(value = "/api/boards")
public class BoardController {

	@Autowired
	private BoardService boardService;

	// 추가10개 검색X
	@GetMapping(value = "")
	public JsonResult list(@RequestParam(value = "crtPage", required = false, defaultValue = "1") int crtPage) {
		System.out.println("BoardController.list()");
		
		System.out.println(crtPage);

		// boardService를 통해서 리스트를 가져온다
		List<BoardVo> boardList = boardService.exeList(crtPage);
		// System.out.println(boardList);

		// 응답 데이터
		return JsonResult.success(boardList);

	}

	/*
	 * // 리스트(검색O,페이징O) // *로그인 필요없음
	 * 
	 * @GetMapping(value = "") public JsonResult
	 * list( @RequestParam(value="crtPage", required = false, defaultValue = "1")
	 * int crtPage,
	 * 
	 * @RequestParam(value="keyword", required = false, defaultValue = "") String
	 * keyword) {
	 * 
	 * System.out.println("BoardController.list()");
	 * 
	 * // boardService를 통해서 리스트를 가져온다 Map<String, Object> pMap =
	 * boardService.exeList(crtPage,keyword); //System.out.println(boardList);
	 * 
	 * // 응답 데이터 return JsonResult.success(pMap);
	 * 
	 * }
	 */

}
