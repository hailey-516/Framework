package com.kh.mybatis.member.service;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.member.model.dao.MemberDao;
import com.kh.mybatis.member.model.vo.Member;
import com.kh.mybatis.template.MybatisTemplate;

public class MemberServiceImpl implements MemberService {
	
	private MemberDao mDao = new MemberDao();

	@Override
	public Member loginMember(Member m) {
		Member loginUser = null;
		
		/*
		 * * 기존 JDBC 방식
		 *   - Connection 객체 생성
		 *   - DAO 객체에게 전달 (Connection, 요청된 데이터)
		 *   - 결과에 따라 트랜잭션 처리, Connection 객체 반납(close)
		 *   - 결과를 반환
		 */
		
		SqlSession sqlSession = MybatisTemplate.getSqlSession();
		
		// loginUser = new MemberDao().loginMember(sqlSession, m);
		loginUser = mDao.loginMember(sqlSession, m);
		
		sqlSession.close();
		
		return loginUser;
	}

	@Override
	public int insertMember(Member m) {
		SqlSession sqlSession = MybatisTemplate.getSqlSession();		
		
		int result = mDao.insertMember(sqlSession, m);
		
		if (result > 0) {	// 회원 등록 성공
			sqlSession.commit();
		}
		// rollback : 여러 개의 DML문을 실행시키는 경우 필수로 작성해야 하고, 단일행일 경우 생략 가능
		//				ex) 재고 관리, 게시글 등록 (게시글 정보+첨부파일)
		
		sqlSession.close();
		
		return result;
	}

	@Override
	public Member updateMember(Member m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteMember(String id, String pwd) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
