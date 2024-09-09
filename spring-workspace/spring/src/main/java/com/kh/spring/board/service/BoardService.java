package com.kh.spring.board.service;

import java.util.ArrayList;

import com.kh.spring.board.model.vo.Board;

public interface BoardService {
	/* 게시판 목록 조회 (+ 페이징 처리) R (select) */
	int selectListCount();
	
	ArrayList<Board> selectList(/* 페이징 관련 데이터 전달받을 예정 */);
	
	/* 게시글 작성 --> C (insert) */
	int insertBoard(Board b);
	
	/* 게시글 상세 조회 --> R (select) + U (update) */
	int increaseCount(int BoardNo);		// 조회수 증가
	Board selectBoard(int BoardNo);
	
	/* 게시글 수정 --> U (update) */
	int updateBoard(Board b);
	
	/* 게시글 삭제 --> U (update) */
	int deleteBoard(int boardNo);
	
	/* 댓글 목록 조회 --> (ajax), R(select) */
	ArrayList</* 댓글 관련 객체 */> selectReplyList(int boardNo);
	
	/* 댓글 등록(작성) --> (ajax), C(insert) */
	int insertReply(/* 댓글 관련 객체 */);
}
