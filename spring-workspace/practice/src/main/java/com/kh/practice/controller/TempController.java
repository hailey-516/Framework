package com.kh.practice.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TempController {
	
	private final String SERVICE_KEY = "서비스키";

	@ResponseBody
	@RequestMapping(value="temp.do", produces="appilcation/json;charset=UTF-8")
	public String temperature(String location) throws IOException {
		
		Calendar calendar = Calendar.getInstance();
	    int hour = calendar.get(Calendar.HOUR_OF_DAY);
	    
	    String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
	    String tmFc;

	    // 시간에 따라 tmFc 결정
	    if (hour < 6) {
	        // 6시 이전: 오늘 날짜의 0600
	        tmFc = today + "0600";
	    } else if (hour < 18) {
	        // 6시에서 18시 사이: 오늘 날짜의 0600
	        tmFc = today + "0600";
	    } else {
	        // 18시 이후: 오늘 날짜의 1800
	        tmFc = today + "1800";
	    }
		
		String url = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidTa"
					+ "?ServiceKey=" + SERVICE_KEY
					+ "&pageNo=" + URLEncoder.encode("1", "UTF-8")
					+ "&numOfRows=" + URLEncoder.encode("10", "UTF-8")
					+ "&dataType=JSON"
					+ "&regId=" + URLEncoder.encode(location, "UTF-8")
					+ "&tmFc=" + URLEncoder.encode(tmFc, "UTF-8");
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
