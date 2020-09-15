package com.company.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.dto.BoardDto;
import com.company.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	private static final Logger logger= LoggerFactory.getLogger(BoardController.class);
	@Autowired
	private BoardService service;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index () { 
		logger.info("::::::::::::::::::::::::::: index");
		return "../../index"; }
	
	@RequestMapping(value="/board_write", method=RequestMethod.GET)
	public String board_write_get() {
		logger.info(":::::::::::: BOARD 글쓰기폼");
		return "/board/board_write";		
	}
	@RequestMapping(value="/board_write", method=RequestMethod.POST)
	public String board_write_post(BoardDto dto, RedirectAttributes rttr, MultipartFile file, HttpServletRequest request) throws Exception {
		logger.info(":::::::::::: BOARD 글쓰기기능");
		String result = "fail";
		if(service.insertSboard(dto, file, request)>0) {
			result="success";  	}
		rttr.addFlashAttribute("insert", result);
		return "redirect:/board/list";		
	}	
	@RequestMapping(value="/list", method= { RequestMethod.GET, RequestMethod.POST})
	public String board_list(Model model) throws Exception {
		logger.info(":::::::::::: BOARD 리스트");
		model.addAttribute("list", service.selectSboardAll());
		return "/board/list";		
	}	
	@RequestMapping(value="/board_detail", method=RequestMethod.GET)
	public String board_detail_get(@RequestParam("bno") int bno, Model model) throws Exception {
		logger.info(":::::::::::: BOARD 상세보기폼");
		BoardDto dto = new BoardDto();
		dto.setBno(bno);
		service.updateSboardBhit(dto);
		model.addAttribute("boardVO", service.selectSboard(dto));
		return "/board/board_detail";		
	}	
	@RequestMapping(value="/board_delete", method=RequestMethod.GET)
	public String board_delete_get(@RequestParam("bno") int bno, Model model) throws Exception {
		logger.info(":::::::::::: BOARD 삭제하기폼");
		BoardDto dto = new BoardDto();
		dto.setBno(bno);
		model.addAttribute("boardVO", service.selectSboard(dto));
		return "/board/board_delete";		
	}
	@RequestMapping(value="/board_delete", method=RequestMethod.POST)
	public String board_delete_post(@RequestParam("bno") int bno, @RequestParam("bpass") String bpass,
			RedirectAttributes rttr) throws Exception {
		logger.info(":::::::::::: BOARD 삭제하기기기능");
		BoardDto dto = new BoardDto();
		dto.setBno(bno); dto.setBpass(bpass);
		String result = "fail";
		if(service.deleteSboard(dto)>0) { result="success";  }
		rttr.addFlashAttribute("delete", result); 
		return "redirect:/board/list";		
	}	

	@RequestMapping(value="/board_modify", method=RequestMethod.GET)
	public String board_modify_get(@RequestParam("bno") int bno, Model model) throws Exception {
		logger.info(":::::::::::: BOARD 수정하기폼");
		BoardDto dto = new BoardDto();
		dto.setBno(bno);
		model.addAttribute("boardVO", service.selectSboard(dto));
		return "/board/board_modify";		
	}
	@RequestMapping(value="/board_modify", method=RequestMethod.POST)
	public String board_modify_post(@RequestParam("bno") int bno, RedirectAttributes rttr, BoardDto dto,
			MultipartFile file, HttpServletRequest request) throws Exception {
		logger.info(":::::::::::: BOARD 수정하기기능");
		String result = "fail";
		if(service.updateSboard(dto, file, request)>0) { result="success"; }
		 rttr.addFlashAttribute("update", result); 
		return "redirect:/board/board_detail?bno="+bno;			
	}	
	
	@RequestMapping(value="/search")
	@ResponseBody
		public Map<String, List<BoardDto>> searchaction (@RequestParam("btitle") String search) throws Exception {
			logger.info("::::::::::::::::::::::::::: search");
			List<BoardDto> list = new ArrayList<BoardDto>();
			BoardDto dto = new BoardDto();
			dto.setBtitle(search);
			list = service.searchSboard(dto);
			Map<String, List<BoardDto>> map = new HashMap<>();
			map.put("list", list);
			return map;
	}
	
}
