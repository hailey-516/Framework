package com.kh.spring.common.template;

import com.kh.spring.common.model.vo.PageInfo;

public class Pagination {
	
	// ----- 페이징 처리 ------
	public static PageInfo getPageInfo(int listCount
										, int currentPage
										, int pageLimit
										, int boardLimit) {
		int maxPage = (int)Math.ceil((double)listCount / boardLimit);
		int startPage = ((currentPage - 1) / pageLimit) * pageLimit + 1;
		int endPage = startPage + pageLimit - 1;
		
		endPage = endPage > maxPage ? maxPage : endPage;
	
		return new PageInfo(listCount, currentPage, pageLimit, boardLimit
								, maxPage, startPage, endPage);
		
	}
	/*
	 * * maxPage // => 가장 마지막 페이지 listCount, boardLimit

		총 게시글 수 			boardLimit (10)
		100						10				=> 10
		101						10				=> 10.1			11
		105						10				=> 10.5			11
		
		총 게시글 수 / boardLimit => 올림처리
	 * 
	 */
	
	/*
	 * startPage => 페이징바의 시작번호 (pageLimit, currentPage)
	 * * 페이징바의 목록 10개씩 표시한다는 가정 하에
	 * startPage: 1, 11, 21, 31, ...
	 * 
	 * 1 ~ 10		n => 0
	 * 11 ~ 20		n => 1
	 * 21 ~ 30		n => 2
	 * 
	 * => n*10 + 1
	 * 
	 * currentPage		pageLimit		startPage
	 * 		1				10		=>		1			0 * pageLimit + 1
	 * 		5				10		=>		1			0 * pageLimit + 1
	 * 		11				10		=>		11			1 * pageLimit + 1
	 * 
	 * currentPage - 1	/ pageLimit
	 * 	0 ~ 9			/ 	10		=>	0
	 * 10 ~ 19			/	10		=>	1
	 * 21 ~ 29			/	10		=> 2
	 */
	
	/*
	 * endPage => 페이징바의 끝번호
	 * * pageLimit: 10
	 * 
	 * startPage: 1 => endPage: 10
	 * 			  11 =>			20
	 * 			  21 =>			30
	 * if) maxPage: 25라면...?
	 * startPage + pageLimit - 1
	 */

}
