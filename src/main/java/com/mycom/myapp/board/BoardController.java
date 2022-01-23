package com.mycom.myapp.board;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Handles requests for the application home page.
 */
@Controller
public class BoardController {
	@Autowired
	BoardDAO boardDAO;
	
	
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public String boardlist(Model model) {
		System.out.println("boardlist F");
		model.addAttribute("list", boardDAO.getBoardList());
		System.out.println("boardlist in " + model.toString());
		
//		return "posts";
		return "board/list";
	}
	
	@RequestMapping(value = "/board/add", method = RequestMethod.GET)
	public String addPost() {
		return "board/addpostform";
	}
	
	@RequestMapping(value = "/board/addok", method = RequestMethod.POST)
	public String addPostOK(BoardVO vo) {
		int i = boardDAO.insertBoard(vo);
		if (i==0) {
			System.out.println("데이터 추가 실패 ");
		}
		else {
			System.out.println("데이터 추가 성공!!!");
		}
		return "redirect:list";
	}
	
	@RequestMapping(value = "/board/editpost/{id}", method = RequestMethod.GET)
	public String editPost(@PathVariable("id") int id, Model model) {
		
		System.out.println("editPost");
		
		BoardVO boardVO = boardDAO.getBoard(id);
		model.addAttribute("boardVO", boardVO);
		
		return "board/editform";
	}
	@RequestMapping(value = "/board/editok", method = RequestMethod.POST)
	public String editPostOK(BoardVO vo) {
		int i = boardDAO.updateBoard(vo);
		
		if(i == 0)
			System.out.println("데이터 수정 실패 ");
		else 
			System.out.println("데이터 수정 성공 !!! ");
	
		return "redirect:list";
	}
	@RequestMapping(value = "/board/deleteok/{id}", method = RequestMethod.GET)
	public String deletePost(@PathVariable("id") int id) {
		int i = boardDAO.deleteBoard(id);
		
		if(i == 0)
			System.out.println("데이터 삭제 실패 ");
		else 
			System.out.println("데이터 삭제 성공 !!! ");
	
		return "redirect:../list";
	}

}
