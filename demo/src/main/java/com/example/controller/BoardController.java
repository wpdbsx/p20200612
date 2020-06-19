package com.example.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.BoardDAO;
import com.example.mapper.BoardMapper;
import com.example.vo.BoardVO;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
	@Autowired // @Bean으로 만들어진 객체를 받아옴.
	private BoardDAO bDAO = null;
	
	
	//DAO +XML 통합형
	@Autowired
	private BoardMapper boardMapper = null;
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam(value= "no",defaultValue = "0") int no
		 , HttpServletRequest request) {
	
		//int ret =bDAO.deleteBoard(no);
		int ret = boardMapper.deleteBoard(no);
		if (ret> 0) {
		return "redirect:"+request.getContextPath()+"/board/list"; //성공하면 리스트로
		}
		return "redirect:"+request.getContextPath()+"/board/content?no="+no; //실패하면 이전화면
		}
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(@RequestParam(value= "no",defaultValue = "0") int no
		 , HttpServletRequest request, Model model) {
		BoardVO vo = bDAO.selectBoardOne(no);
		
		
		
		String[] check = {"java", "jst1", "spring"};
		vo.setTmp(check);
		
		model.addAttribute("vo",vo);
		List<String> selectList =new ArrayList<String>();
		selectList.add("java");
		selectList.add("jsp");
		selectList.add("spring");
		selectList.add("jst1");
		selectList.add("mybatis");
		model.addAttribute("slist",selectList);
		return "/board/update";
		}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updatepost(@RequestParam(value= "no",defaultValue = "0") int no
		 , HttpServletRequest request,
		   @ModelAttribute BoardVO obj,
		   @RequestParam MultipartFile[] img) throws IOException { //변수명 == <input name== "img"
		
		
		//이미지는 수동으로 obj에 추가함.
		if (img != null ) {
			// 이미지가 첨부되었다면
			for (MultipartFile one : img) {
				if(one.getSize()>0) {
				//if(!one.getOriginalFilename().equals("")) {
				obj.setBrd_img(one.getBytes());
				}
			}

		}
		bDAO.updateBoard(obj);
			
		
		return "redirect:" +request.getContextPath() + "/board/content?no="+obj.getBrd_no();
	}
		
	@RequestMapping(value = "/getimg")
	public ResponseEntity<byte[]> getimg(@RequestParam("no") int no, HttpServletRequest request) {
		BoardVO obj = bDAO.selectBoardImg(no);
		try {
			if (obj.getBrd_img().length > 0) {
				HttpHeaders header = new HttpHeaders();
				header.setContentType(MediaType.IMAGE_JPEG);
				ResponseEntity<byte[]> ret = new ResponseEntity<byte[]>(obj.getBrd_img(), header, HttpStatus.OK);
				return ret;
			}
			return null;

		} catch (Exception e) {

			try {
				// request.getServletContext().getResourceAsStream
				// src/ main / webapp 까지 자동으로 찾아진다.
				InputStream in = request.getServletContext().getResourceAsStream("/resources/img/default.jpg");
				HttpHeaders header = new HttpHeaders();
				header.setContentType(MediaType.IMAGE_JPEG);
				ResponseEntity<byte[]> ret = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), header, HttpStatus.OK);
				return ret;
			} catch (Exception e1) {
				return null;
			}

		}
		
	}
	// 127.0.0.1:8080/board/list?page=55

	@RequestMapping(value = "/insertbatch", method = RequestMethod.GET)
	public String insertbatch(HttpSession httpsession, Model model) {
		String userid = (String) httpsession.getAttribute("SESSION_ID");
		if (userid == null) { // 아이디 값이 없다면 로그인되지 않은 상태
			return "redirect:/member/login"; // 로그인 페이지로 이동
		}
		model.addAttribute("userid", userid);
		return "/board/boardinsert";
	}

	@RequestMapping(value = "/insertbatch", method = RequestMethod.POST)
	public String insertbatchPost(@RequestParam("title[]") String[] title, @RequestParam("content[]") String[] content,
			HttpSession httpSession) {

		List<BoardVO> list = new ArrayList<BoardVO>();
		for (int i = 0; i < title.length; i++) {
			BoardVO obj = new BoardVO();
			obj.setBrd_title(title[i]);
			obj.setBrd_content(content[i]);
			obj.setBrd_id((String) httpSession.getAttribute("SESSION_ID"));

			list.add(obj);
		}
		// DAO로 obj값 전달하기

		bDAO.insertBatch(list);

		return "redirect:/board/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpSession httpsession, Model model,
			HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "0", required = false) int page,
			@RequestParam(value = "text", defaultValue = "", required = false) String text) {
		
			
		if(page == 0) {
			return "redirect:" + request.getContextPath() + "/board/list?page=1"; 
		}
		
		httpsession.setAttribute("SESSION_BOARD_HIT_CHECK", 1);

		// 목록
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", page*7-9); 	//시작위치
		map.put("end", page*7);		//종료위치	
		map.put("text", text);			//검색어
		List<BoardVO> list = bDAO.selectBoard(map);
		model.addAttribute("list", list);
		
		// 게시물 개수
		int cnt = bDAO.countBoard(text); //검색어를 넘겨줌.
		//System.out.println( (int) Math.ceil(n/10.0) );
		model.addAttribute("cnt", (cnt-1)/10+1);
		
		return "/board/boardlist";

	}

	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public String content(HttpSession httpsession, Model model,
			@RequestParam(value = "no", defaultValue = "0", required = false) int no) {
		HashMap<String, Object> map = new HashMap<String, Object>();

		if (no == 0) {
			return "redirect:/board/list";
		}
		Integer chk = (Integer) httpsession.getAttribute("SESSION_BOARD_HIT_CHECK");

		if (chk != null) {
			if (chk == 1) {
				bDAO.updateHit(no);
				httpsession.setAttribute("SESSION_BOARD_HIT_CHECK", 0);
			}

		}
	
		int prev = bDAO.selectBoardPrev(no);
		model.addAttribute("prev",prev); 
		int next =bDAO.selectBoardNext(no);
		model.addAttribute("next",next);
		BoardVO obj = bDAO.selectBoardOne(no);
		obj.setBrd_date(obj.getBrd_date().split(" ", 6)[0]);
		model.addAttribute("obj", obj);
		map.put("no", no);

		//
		return "/board/content";

	}

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insertBoard(HttpSession httpSession, Model model) {
		// 세션에서 로그인한 사용자의 아이디 값을 가져옴.
		String userid = (String) httpSession.getAttribute("SESSION_ID");
		if (userid == null) { // 아이디 값이 없다면 로그인되지 않은 상태
			return "redirect:/member/login"; // 로그인 페이지로 이동
		}
		// 그렇지 않다면 게시판 글쓰기 화면 표시
		model.addAttribute("userid", userid);
		return "/board/insert";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insertBoardPost(@ModelAttribute BoardVO obj, @RequestParam MultipartFile[] imgs,
			HttpSession httpSession) throws IOException {
		if (imgs != null && imgs.length > 0) {
			// 이미지가 첨부되었다면
			for (MultipartFile one : imgs) {
				obj.setBrd_img(one.getBytes());
			}

		}
		// DAO로 obj값 전달하기
		obj.setBrd_id((String) httpSession.getAttribute("SESSION_ID"));

		
		bDAO.insertBoard(obj);

		return "redirect:/";
	}
}
