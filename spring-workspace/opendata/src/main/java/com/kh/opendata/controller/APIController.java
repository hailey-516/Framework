package com.kh.opendata.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class APIController {
	// 서비스 키
	private final String SERVICE_KEY = "E6EEgrmIDf2HdHQwdw0Q6bKu4xOmJklT2Da4lef9XuN6x5aNM9DSw9aEzheFHN2BpZtYY4Bvs4Ur3HlpHiPxwQ%3D%3D";
	
	// air.do 요청을 받을 메소드
	@ResponseBody	// 응답을 데이터 형태로 응답할 때(없으면 페이지 형태로 응답)
	@RequestMapping(value="air.do", produces="appilcation/json;charset=UTF-8")
	public String airPollution(String location) throws IOException {
		
		// 대기오염 정보를 조회 (api 사용)
		String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty"
					+ "?serviceKey=" + SERVICE_KEY // 인코딩 안 된 것 가져오려면 URLEncoder.encode(SEVICE_KEY, "UTR-8") -> 원본 데이터(디코딩)
					+ "&sidoName=" + URLEncoder.encode(location, "UTF-8")
					+ "&returnType=json";
		
		URL reqUrl = new URL(url);		// 요청 주소로 URL 객체 생성
		HttpURLConnection urlConn = (HttpURLConnection)reqUrl.openConnection();	// URL 객체로 Connection 객체 생성
		
		urlConn.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
		
		String resText = "";	// 결과를 담을 변수
		String line;			// 한 줄씩 읽을 변수
		while((line = br.readLine()) != null) {
			resText += line;
		}
		
		br.close();
		urlConn.disconnect();
		
		System.out.println(resText);
		
		return resText;
		
	}

}
