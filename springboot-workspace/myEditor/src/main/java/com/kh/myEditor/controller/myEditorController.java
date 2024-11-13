package com.kh.myEditor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.myEditor.board.model.vo.Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class myEditorController {
	
	@GetMapping("/board")
	public String showEnrollBoard() {
		return "board/enrollBoard";
	}
	
	// -------------------------------
	@ResponseBody
	@PostMapping("/board")
	public String insertBoard(Board board) {
		log.info("data --> {}", board);
		
		return "ok";
	}

}
