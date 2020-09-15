package com.company.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.company.dto.BoardDto;

public interface BoardService {
	
	public int insertSboard(BoardDto dto, MultipartFile file, HttpServletRequest request) throws Exception;
	public List<BoardDto> selectSboardAll()throws Exception;
	public BoardDto selectSboard(BoardDto dto) throws Exception;
	public int updateSboardBhit(BoardDto dto) throws Exception;
	public int updateSboard(BoardDto dto, MultipartFile file, HttpServletRequest request) throws Exception;
	public int deleteSboard(BoardDto dto) throws Exception;
	public List<BoardDto> searchSboard(BoardDto dto) throws Exception;

}
