package com.kh.practice.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class TempController {
	
	private final String SERVICE_KEY = "서비스키";
	
	@ResponseBody
	@RequestMapping(value="temp.do", produces="appilcation/json;charset=UTF-8")
	public String temperature(String location) throws IOException {
		
		String url = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidTa"
					+ "?serviceKey=" + SERVICE_KEY
					+ "&pageNo=" + URLEncoder.encode("1", "UTF-8")
					+ "&numOfRows=" + URLEncoder.encode("10", "UTF-8")
					+ "&dataType=JSON"
					+ "&regId=" + URLEncoder.encode(location, "UTF-8")
					+ "tmFc=" + URLEncoder.encode("202410110600", "UTF-8");
		URL reqUrl = new URL(url);
		HttpURLConnection urlConn = (HttpURLConnection)reqUrl.openConnection();
		
		urlConn.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
		
		String resText = "";
		String line;
		while((line = br.readLine()) != null) {
			resText += line;
		}
		
		br.close();
		urlConn.disconnect();
		
		System.out.println(resText);
		
		return resText;
		
	}

}
