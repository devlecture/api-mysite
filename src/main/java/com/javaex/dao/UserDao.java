package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//조회id_pw(로그인)
	public UserVo userSelectOneByIdPw(UserVo userVo) {
		System.out.println("UserDao.userSelectOneByIdPw()");
		
		UserVo authUser = sqlSession.selectOne("user.selectOneByIdPw", userVo);
		return authUser;
	}
	
	
	//조회no(회원정보수정 폼)
	public UserVo userSelectOneByNo(int no) {
		System.out.println("UserDao.userSelectOneByNo()");
		
		UserVo userVo = sqlSession.selectOne("user.selectOneByNo", no);
		return userVo;
	}
	
	/////////////////////////////////////////
	
	
	//등록(회원가입)
	public int userInsert(UserVo userVo) {
		System.out.println("UserDao.userInsert()");
		
		int count = sqlSession.insert("user.insert", userVo);
		return count;
	}
	

	//수정(회원정보수정)
	public int userUpdate(UserVo userVo) {
		System.out.println("UserDao.userUpdate()");
		
		int count = sqlSession.update("user.update", userVo);
		return count;
	}
	
	
}
