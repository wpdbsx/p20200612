package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Student;
import com.example.repository.StudentRepository;

@Controller
@RequestMapping(value = "/student")
public class StudentConroller {
	@Autowired
	private StudentRepository sRepository;
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home() {
		return "/student/home";// view의 student 폴더 밑에 insert.jsp를 표시
	}
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insertget() {
		return "/student/insert";// view의 student 폴더 밑에 insert.jsp를 표시
	}
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insertpost(@ModelAttribute Student obj) {
		sRepository.save(obj); //insert ,update 끝
		
		
		return "redirect:/student/home";// 현제 홈페이지가 없음.
	}
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public String selectget(Model model) {
		Iterable<Student> list= sRepository.selectStudentQuery(1);
		model.addAttribute("list",list);
		return "/student/select";// view의 student 폴더 밑에 insert.jsp를 표시
		
	}
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deletepost(@RequestParam("id") String id) {
		Student obj = new Student();
		obj.setId(id);
		sRepository.delete(obj); //insert ,update 끝
		
		
		return "redirect:/student/home";// 현제 홈페이지가 없음.
	}
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateget(@RequestParam("id") String id,Model model) {
		
		Student obj = sRepository.findById(id); //insert ,update 끝
		
		model.addAttribute("obj",obj);
		return "/student/update";// 현제 홈페이지가 없음.
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateepost(@ModelAttribute Student obj) {
		
		//기본자료를 가져옴
		Student obj1 = sRepository.findById(obj.getId());
		//바꿀 자료값을 변경함
		obj1.setName(obj.getName());
		obj1.setKor(obj.getKor());
		obj1.setEng(obj.getEng());
		obj1.setMath(obj.getMath());
		obj.setDate(obj1.getDate());
		sRepository.save(obj);
		return "redirect:/student/update";// 현제 홈페이지가 없음.
	}
}
