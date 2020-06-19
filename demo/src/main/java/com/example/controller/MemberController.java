package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.dao.MemberDAO;
import com.example.vo.MemberVO;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	@Autowired
	private MemberDAO mDAO = null; // memberDAO mDAO = new MberDAO()

	@RequestMapping(value = "/memberlist")
	public String memberlist(Model model) {
		List<MemberVO> list=mDAO.selectMemberList();
		model.addAttribute("name","가나다");
		model.addAttribute("lista",list); //hashmap<kley, value> C- >v로 값전달 하는 방법
		
		return "memberlist";
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession httpSession) {
		//httpSession.setAttribute("SESSION_ID", null);
		httpSession.invalidate();
		return "redirect:/";
	}
	

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginpost(@ModelAttribute MemberVO obj, 
			HttpSession httpSession  ,
			HttpServletRequest request) {
		//DAO로 전달
		MemberVO obj1 = mDAO.selectMemberLogin(obj);
		if (obj1 != null) { //로그인 성공
			httpSession.setAttribute("SESSION_ID", obj.getUserid());
			String backURL = (String) httpSession.getAttribute("CURRPAGE");
			System.out.println(backURL);
			return "redirect:"+backURL;	    //고정되면 안됨!! 마지막 페이지로 가야됨
		}
		
		//로그인 실패 /member/login GET방식으로 전송
		return "redirect:"+request.getContextPath()+"/member/login";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "join"; // join.jsp
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST) // 사용자의 데이터를 전달하기 위한 용도
	public String joinpost(@ModelAttribute MemberVO obj) {
		System.out.println(obj.toString());
		// DB로 전달해서 추가해야 함.
		int ret = mDAO.insertMember(obj);
		if (ret > 0) { // 회원 가입 성공시
			return "redirect:/";
		}
		// 회원가입 실패시
		return "redirect:/member/join"; // 주소창을 바꿔라

	}

}

//단축키 ctrl+shift +? 주석문 
