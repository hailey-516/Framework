package com.kh.lclinic.model.vo;

import lombok.Data;

@Data
public class User {
	
	// 키값과 동일하게 필드명 지어져 있어야 객체 통해 받을 수 있다.
	private String id;
	private String password;
	
}
