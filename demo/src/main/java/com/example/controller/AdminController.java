package com.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.example.dao.ItemDAO;
import com.example.dao.MemberDAO;
import com.example.vo.ItemVO;
import com.example.vo.MemberVO;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	@Autowired // @Bean으로 만들어진 객체를 받아옴.
	private ItemDAO iDAO = null;
	@Autowired
	private MemberDAO mDAO =null;
	
	@RequestMapping(value = "/itemorder")
	public String itemorder() {
		return "/admin/itemorder";
	}
	@RequestMapping(value = "/home")
	public String home() {
		return "/admin/home";
	}

	@RequestMapping(value = "/iteminsert")
	public String iteminsert() {
		return "/admin/iteminsert";
	}

	@RequestMapping(value = "/iteminsert", method = RequestMethod.POST)
	public String inteminsertpost(@RequestParam("name[]") String[] name, @RequestParam("price[]") int[] price,
			@RequestParam("qty[]") int[] qty, @RequestParam("content[]") String[] content) {

		List<ItemVO> list = new ArrayList<ItemVO>();

		for (int i = 0; i < name.length; i++) {
			ItemVO obj = new ItemVO();
			obj.setItemname(name[i]);
			obj.setItemprice(price[i]);
			obj.setItemqty(qty[i]);
			obj.setItemdes(content[i]);
			list.add(obj);
		}
		
		iDAO.insertItemBatch(list);
		return "redirect:/admin/home";   //<a href= "/admin/home"> 자동화</a>
	}
	@RequestMapping(value = "/item" , method= RequestMethod.GET)
	public String list (HttpSession httpsession,Model model) {
		
		//목록
		List<ItemVO> list= iDAO.selectItemList();
		//개수
		//System.out.println( (int) Math.ceil(n/10.0));
	
		model.addAttribute("list",list);
		
		
		return "/admin/item";
		
	}
	//일괄수정, 일괄삭제를 같이 처리할곳
	@RequestMapping(value = "/item" , method= RequestMethod.POST)
	public String itembatch(@RequestParam("btn") String btn
			,RedirectAttributes redirectAttributes,
			@RequestParam(value= "chk[]", required= false) int[] itemno)  {
	
		if(itemno !=null) {
		if(btn.equals("일괄삭제")) {
		 iDAO.deleteItemBatch(itemno);
		}
		else if (btn.equals("일괄수정")) {
			redirectAttributes.addFlashAttribute("abc",itemno);
			return "redirect:/admin/itemupdate";
		}
		
		
		}
		return "redirect:/admin/home";
		
	}
	
	@RequestMapping(value = "/itemupdate" , method= RequestMethod.GET)
	public String itemupdate (HttpSession httpsession,Model model, HttpServletRequest req) {
		Map<String,?> map = RequestContextUtils.getInputFlashMap(req);
		if(map!=null) {
			int [] tmp = (int[]) map.get("abc");
//			for(int i=0 ; i<tmp.length;i++) {
//			System.out.println(tmp[i]);
//			}
//			//tmp변수를 dao에 전달하여 물품번호에 해당하는 목록만 가져오기
			//jsp로 전달 후 화면 표시
			//select * from item where itemno in (1,2,3)
			List<ItemVO> list = iDAO.selectItemWhere(tmp);
			model.addAttribute("list",list);
			return "/admin/itemupdate";
		}
		else {
		return "redirect:/admin/item";
		}
	}
	@RequestMapping(value = "/itemupdate" , method= RequestMethod.POST)
	public String itemupdatePost (
			@RequestParam("no[]") int[] no,
			@RequestParam("name[]") String[] name,
			@RequestParam("price[]") int[] price,
			@RequestParam("qty[]") int[] qty,
			@RequestParam("des[]") String[] des) {
		List<ItemVO> list = new ArrayList<ItemVO>();
		for (int i =0; i<no.length;i++) {
			ItemVO obj = new ItemVO();
			obj.setItemno(no[i]);
			obj.setItemname(name[i]);
			obj.setItemprice(price[i]);
			obj.setItemqty(qty[i]);
			obj.setItemdes(des[i]);
			list.add(obj);
		}
		iDAO.updateItemBatch(list);
		return "redirect:/admin/item";
	
	}
	
	@RequestMapping(value = "/member")
	public String memberlist(Model model) {
		List<MemberVO> list = mDAO.selectMemberList();
		//개수
		//System.out.println( (int) Math.ceil(n/10.0));
	
		model.addAttribute("lista",list);
		
		
		return "/admin/member";
		
	}
	@RequestMapping(value = "/member" , method= RequestMethod.POST)
	public String memberlistPost(Model model, @RequestParam("chk[]") String[] id, 
			@RequestParam("btn") String btn,
			RedirectAttributes redirectAttributes) {
		
		if(btn.equals("일괄삭제")){
		mDAO.deleteMemberList(id);
		//개수
		//System.out.println( (int) Math.ceil(n/10.0));
		
		}
		else if(btn.equals("일괄수정")){
			
			redirectAttributes.addFlashAttribute("abc",id);
			return "redirect:/admin/memberupdate";
		}
		
		List<MemberVO> list = mDAO.selectMemberList();
		model.addAttribute("lista",list);
		return "/admin/member";
		
	}
	
	@RequestMapping(value = "/memberupdate" , method= RequestMethod.GET)
	public String memberupdate (HttpSession httpsession,Model model, HttpServletRequest req) {
		Map<String,?> map = RequestContextUtils.getInputFlashMap(req);
		if(map!=null) {
			String [] tmp = (String[]) map.get("abc");
//			for(int i=0 ; i<tmp.length;i++) {
//			System.out.println(tmp[i]);
//			}
//			//tmp변수를 dao에 전달하여 물품번호에 해당하는 목록만 가져오기
			//jsp로 전달 후 화면 표시
			//select * from item where itemno in (1,2,3)
		
			List<MemberVO> list = mDAO.selectMemberID(tmp);
			model.addAttribute("list",list);
			return "/admin/memberupdate";
		}
		else {
		return "redirect:/admin/member";
		}
	}
	@RequestMapping(value = "/memberupdate" , method= RequestMethod.POST)
	public String memberupdatePost (HttpSession httpsession,Model model, HttpServletRequest req,
			@RequestParam("id[]") String[] id,
			@RequestParam("password[]") String[] password,
			@RequestParam("name[]") String[] name,
			@RequestParam("phone[]") String[] phone,
			@RequestParam("age[]") int[] age
			
			
			) {
		
		

//			for(int i=0 ; i<tmp.length;i++) {
//			System.out.println(tmp[i]);
//			}
//			//tmp변수를 dao에 전달하여 물품번호에 해당하는 목록만 가져오기
			//jsp로 전달 후 화면 표시
			//select * from item where itemno in (1,2,3)
			 
			List<MemberVO> list = new ArrayList<MemberVO>();
			for (int i =0; i<id.length;i++) {
				MemberVO obj = new MemberVO();
				obj.setUserid(id[i]);
				obj.setPassword(password[i]);
				obj.setUsername(name[i]);
				obj.setPhone(phone[i]);
				obj.setUserage(age[i]);
				list.add(obj);
			}
			mDAO.updateMeberList(list);
		
			
		
		
		return "redirect:/admin/member";
		
	}
}
