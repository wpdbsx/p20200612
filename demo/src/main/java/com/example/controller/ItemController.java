package com.example.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dao.BoardDAO;
import com.example.dao.ItemDAO;
import com.example.vo.BoardVO;
import com.example.vo.ItemVO;

@Controller
@RequestMapping(value = "/item")
public class ItemController {
	@Autowired // @Bean으로 만들어진 객체를 받아옴.
	private ItemDAO iDAO = null;
	@RequestMapping(value = "/list" , method= RequestMethod.GET)
	public String list (HttpSession httpsession,Model model, @RequestParam(value="page", defaultValue="1",required= false) int page) {
		HashMap <String,Object> map = new HashMap<String, Object>() ;
		map.put("start", page*10-9);
		map.put("end", page*10);
		//page
		//1 -> 1 , 10
		//2 -> 11 ,20
		httpsession.setAttribute("SESSION_BOARD_HIT_CHECK",1);
		//목록
		List<ItemVO> list= iDAO.selectItem(map);
		//개수
		//System.out.println( (int) Math.ceil(n/10.0));
		int cnt = (iDAO.countBoard()-1)/10+1;
		model.addAttribute("list",list);
		model.addAttribute("cnt",cnt);
		
		return "/item/itemlist";
		
	}
}
