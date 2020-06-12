package com.example.rest_controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.ItemDAO;
import com.example.vo.ItemVO;
@CrossOrigin("*") //CROES 해제    서버에 json문서를 만들어줘도 다른 디바이스에선 접속이 안됨 크로스도메인
@RestController  //json 문서를 만들기 위해선 이게 필요하다....
public class RestItemController {
	@Autowired // @Bean으로 만들어진 객체를 받아옴.
	private ItemDAO iDAO = null;
	
	@RequestMapping(value = "/rest/itemsearch.json", method = {RequestMethod.GET, RequestMethod.POST},
			produces= MediaType.APPLICATION_JSON_VALUE)

	public @ResponseBody HashMap<String,Object> itemSearch(
			@RequestParam("txt") String txt,
			@RequestParam("key") String key ){
		
		HashMap<String , Object> map = new HashMap<String,Object>();
		if(key.equals("abc")) {
			List<ItemVO> obj = iDAO.selectItemSearch(txt);
			map.put("ret", 1);
			map.put("data", obj);

		}
		else {
			map.put("ret", 0);
			map.put("error", "유효하지 않는 키입니다.");
		}
				return map;

		
	}
	@RequestMapping(value = "/rest/itemone.json", method = {RequestMethod.GET, RequestMethod.POST},
			produces= MediaType.APPLICATION_JSON_VALUE)

	public @ResponseBody HashMap<String,Object> itemOne(
			@RequestParam("no") int no,
			@RequestParam("key") String key ){
		
		HashMap<String, Object> map = new HashMap<String,Object>();
		
		map.put("ret", 0); //{"ref":0} 실패시
		//{"ref":1, data: "{ " } }
		if (key.equals("abc")) {
			ItemVO obj = iDAO.selectItemOnt(no);
			map.put("ret", 1);
			map.put("data", obj);
		}
		else { //{"ret" :0}
			
			
		 map.put("ret", 0);
		 map.put("error", "유효하지 않은 키입니다.");
		}
		ItemVO obj = iDAO.selectItemOnt(no);
		return map;
	}

}