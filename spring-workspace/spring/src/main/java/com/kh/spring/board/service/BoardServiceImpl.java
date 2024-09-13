package com.kh.spring.board.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.common.model.vo.PageInfo;
import com.kh.spring.board.model.dao.BoardDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	// sqlSession, bDao 객체 DI
	private final SqlSessionTemplate sqlSession;
	private final BoardDao bDao;
/*
 * 	// @RequiredArgsConstructor 로도 처리 가능(lombok 이용)
	public BoardServiceImpl(SqlSessionTemplate sqlSession, BoardDao bDao) {
		this.sqlSession = sqlSession;
		this.bDao = bDao;
	}
*/

	@Override
	public int selectListCount() {
		return bDao.selectListCount(sqlSession);
	}

	@Override
	public ArrayList<Board> selectList(PageInfo pi) {
		return bDao.selectList(sqlSession, pi);
	}

	@Override
	public int insertBoard(Board b) {
		
		return bDao.insertBoard(sqlSession, b);
	}

	@Override
	public int increaseCount(int boardNo) {
		
		return bDao.increaseCount(sqlSession, boardNo);
	}

	@Override
	public Board selectBoard(int boardNo) {
		
		return bDao.selectBoard(sqlSession, boardNo);
	}

	@Override
	public int updateBoard(Board b) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBoard(int boardNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Reply> selectReplyList(int boardNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertReply(Reply r) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
