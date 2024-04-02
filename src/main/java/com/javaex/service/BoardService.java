package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	public List<BoardVo> exeList(int crtPage) {
		System.out.println("BoardService.exeList()");

		/////////////////////////////////////////////
		// 리스트가져오기
		/////////////////////////////////////////////

		// 페이지당 글갯수
		int listCnt = 10; // 한페이지에 출력되는 글갯수

		// 현재페이지 계산 crtPage가 0보다 작거나 같을때는 1페이지로 처리
		crtPage = (crtPage > 0) ? crtPage : (crtPage = 1);

		// limit 30, 10 ==> 31번째 글부터 10개 (4page 예시)
		// 시작번호 => (페이지번호 - 1) * 페이지당 글갯수 = (4 - 1) * 10 = 30
		int startRowNo = (crtPage - 1) * listCnt;

		// 묶어서 보내야한다
		Map<String, Object> limitMap = new HashMap<String, Object>();
		limitMap.put("startRowNo", startRowNo);
		limitMap.put("listCnt", listCnt);

		List<BoardVo> boardList = boardDao.boardSelectList(limitMap);

		return boardList;

	}
	
	
	/*
	// 리스트(검색O,페이징O)
	public Map<String, Object> exeList(int crtPage, String keyword) {
		System.out.println("BoardService.exeList()");

		/////////////////////////////////////////////
		// 리스트가져오기
		/////////////////////////////////////////////

		// 페이지당 글갯수
		int listCnt = 10; // 한페이지에 출력되는 글갯수

		// 현재페이지 계산 crtPage가 0보다 작거나 같을때는 1페이지로 처리
		crtPage = (crtPage > 0) ? crtPage : (crtPage = 1);

		// limit 30, 10 ==> 31번째 글부터 10개 (4page 예시)
		// 시작번호 => (페이지번호 - 1) * 페이지당 글갯수 = (4 - 1) * 10 = 30
		int startRowNo = (crtPage - 1) * listCnt;

		// 묶어서 보내야한다
		Map<String, Object> limitMap = new HashMap<String, Object>();
		limitMap.put("startRowNo", startRowNo);
		limitMap.put("listCnt", listCnt);
		limitMap.put("keyword", keyword);

		List<BoardVo> boardList = boardDao.boardSelectList(limitMap);

		/////////////////////////////////////////////
		// 페이징 계산
		/////////////////////////////////////////////
		// 페이지당 버튼 갯수
		int pageBtnCount = 5;

		// 전체글갯수
		int totalCnt = boardDao.selectTotalCnt(keyword);

		// 마지막버튼번호
		int endPageBtnNo = (int) Math.ceil(crtPage / (double) pageBtnCount) * pageBtnCount;

		// 시작버튼번호
		int startPageBtnNo = (endPageBtnNo - pageBtnCount) + 1;

		// 다음화살표 유무
		boolean next = false;
		if (listCnt * endPageBtnNo < totalCnt) {
			next = true;

		} else { // 다음버튼이 없을(false)때 endPageBtnNo 을 다시계산
			endPageBtnNo = (int) Math.ceil(totalCnt / (double) listCnt);
			// 157/10.0 ==>15.7 ==> 16.0 
		}

		// 이전화살표 유무
		boolean prev = false;
		if (startPageBtnNo != 1) {
			prev = true;
		}

		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("pageBtnCount", pageBtnCount);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("prev", prev);
		pMap.put("next", next);
		pMap.put("boardList", boardList);

		return pMap;

	}
	*/
}
