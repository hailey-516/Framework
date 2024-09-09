package com.kh.spring.member.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dao.MemberDao;
import com.kh.spring.member.model.vo.Member;

import lombok.RequiredArgsConstructor;

// lombok에 있는 어노테이션임. 이 방식으로 하면 롬복 사용해야 함...
@RequiredArgsConstructor		// 필드로 선언된 객체(빈)를 생성자 방식으로 주입
@Service		// Component보다 좀 더 구체화된 객체 => Service Bean으로 등록
public class MemberServiceImpl implements MemberService {

	/*
	 * 필드 주입 방식 : 스프링 컨테이너에서 객체를 생성한 후
	 * 				@Autowired 사용한 필드에 주입
	@Autowired
	private SqlSessionTemplate sqlSession;
	// SqlSessionTemplate sqlSession = new SqlSessionTemplate
	// Autowired -> Bean으로 등록된 객체를 생성해서 넘겨 줌(root-context.xml)
	*/
	private final SqlSessionTemplate sqlSession;
	private final MemberDao mDao;
	/*
	@Autowired
	public MemberServiceImpl(SqlSessionTemplate sqlSession, Member mDao) {
		this.sqlSession = sqlSession;
		this.mDao = mDao;
	}
	// => 생성자 주입 방식
	// lombok 안 쓸 때는 이 방식으로 해야 함
	*/
	
	@Override
	public Member loginMember(Member m) {
		// sqlSession 객체 생성 --> 스프링 컨테이너 생성
		// Dao 객체에 sqlSession, 전달된 데이터 전달하면서 요청
		return mDao.loginMember(sqlSession, m);
		
		// 트랜잭션 처리 (필요시)
		// sqlSession 객체 반납 --> 스프링 컨테이너에서 처리
		// 결과 반환(return)
	}

	@Override
	public int insertMember(Member m) {
		// 기존에... SqlSession 객체 생성 --> spring에서 처리
		
		// DAO 객체한테 sqlSession, 데이터 전달 => DB 작업 요청
		int result = mDao.insertMember(sqlSession, m);
		
		// DML(insert) -> 트랜잭션 처리
		// SqlSession 객체 반납(close) --> spring에서 처리
		
		// 결과를 리턴
		return result;
		
	}

	@Override
	public int idCheck(String id) {
		// TODO Auto-generated method stub
		return 0;
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
