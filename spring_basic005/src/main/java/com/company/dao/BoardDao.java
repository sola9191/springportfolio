package com.company.dao;

import java.util.List;

import com.company.dto.BoardDto;

public interface BoardDao {
	public int insertSboard(BoardDto dto) throws Exception;
	public List<BoardDto> selectSboardAll()throws Exception;
	public BoardDto selectSboard(BoardDto dto) throws Exception;
	public int updateSboardBhit(BoardDto dto) throws Exception;
	public int updateSboard(BoardDto dto) throws Exception;
	public int deleteSboard(BoardDto dto) throws Exception;
	public List<BoardDto> searchSboard(BoardDto dto) throws Exception;
	
}
