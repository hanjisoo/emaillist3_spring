package com.emaillist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emaillist.dao.EmaillistDao;
import com.emaillist.vo.EmaillistVo;

@Controller
public class EmaillistController {
	
	@Autowired//new해줘
	private EmaillistDao dao;
	
	@RequestMapping("/list")
	public String list(Model model) {
		
		/*EmaillistDao dao=new EmaillistDao();삭제해버렷*/
		List<EmaillistVo> list=dao.getlist();
		System.out.println(list.toString());
		model.addAttribute("list", list);//위에 모델써주고 데이터 받아줌
		return"list";
		/*return"WEB-INF/views/list.jsp";*/
		//포워드
	}
	
	@RequestMapping("/add")
	public String add(@ModelAttribute EmaillistVo vo) {
		System.out.println(vo.toString());
		dao.insert(vo);
		return"redirect:/list";
		//리다이랙트
	}
	
	@RequestMapping("/form")
	//form	리턴형이 스트링이야
	public String form() {
		return"form";
		/*return"/WEB-INF/views/form.jsp";*/
	}
}
