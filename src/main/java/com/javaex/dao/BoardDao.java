package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;

	public List<BoardVo> boardSelectList(Map<String, Object> limitMap) {
		System.out.println("BoardDao.boardSelectList()");

		List<BoardVo> boardList = sqlSession.selectList("board.selectList", limitMap);

		return boardList;

	}
	
	/*
	// 리스트(검색O,페이징O)
	public List<BoardVo> boardSelectList2(Map<String, Object> limitMap) {
		System.out.println("BoardDao.boardSelectList()");

		List<BoardVo> boardList = sqlSession.selectList("board.selectList", limitMap);

		return boardList;

	}
	
	// 글 전체 갯수(검색O,페이징O)
	public int selectTotalCnt2(String keyword) {
		System.out.println("BoardDao.selectTotalCnt()");

		int totalCount = sqlSession.selectOne("board.selectTotalCnt", keyword);

		return totalCount;
	}
	*/

}
