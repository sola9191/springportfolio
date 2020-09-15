package com.company.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.dto.BoardDto;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Autowired
	private SqlSession session;
	private static final String namespace="com.company.mapper.BoardMapper";
	@Override
	public int insertSboard(BoardDto dto) throws Exception {
		return session.insert(namespace + ".insertSboard", dto);
	}
	@Override
	public List<BoardDto> selectSboardAll() throws Exception {
		return session.selectList(namespace + ".selectSboardAll");
	}
	@Override
	public BoardDto selectSboard(BoardDto dto) throws Exception {
		return session.selectOne(namespace + ".selectSboard", dto);
	}
	@Override
	public int updateSboardBhit(BoardDto dto) throws Exception {
		return session.update(namespace + ".updateSboardBhit", dto);
	}
	@Override
	public int updateSboard(BoardDto dto) throws Exception {
		return session.update(namespace + ".updateSboard", dto);
	}	
	@Override
	public int deleteSboard(BoardDto dto) throws Exception {
		return session.delete(namespace + ".deleteSboard", dto);
	}
	@Override
	public List<BoardDto> searchSboard(BoardDto dto) throws Exception {		
		return session.selectList(namespace + ".searchSboard", dto);
	}


}
