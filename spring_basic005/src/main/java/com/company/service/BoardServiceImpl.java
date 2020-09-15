package com.company.service;

import java.io.File;
import java.net.InetAddress;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.company.dao.BoardDao;
import com.company.dto.BoardDto;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao dao;
	
	@Override public int insertSboard(BoardDto dto, MultipartFile file, HttpServletRequest request) throws Exception { 
		System.out.println("ㅁ name: " + file.getOriginalFilename());
		System.out.println("ㅁ size: " + file.getSize());
		System.out.println("ㅁ contentType: " + file.getContentType());
		//1. 파일이름 중복되지 않게 처리
		if(file.getSize()==0) {
			dto.setBfile("noimg.PNG");
		}
		else {
		UUID uid = UUID.randomUUID();
		System.out.println("ㅁ UID: " +uid);
		String filename = uid.toString() + "_" + file.getOriginalFilename();
		String uploadpath = request.getSession().getServletContext().getRealPath("/") + "upload";
		System.out.println("ㅁuploadpath: " + uploadpath);
		System.out.println("ㅁfilename: " +filename);
		File target = new File(uploadpath, filename);
		FileCopyUtils.copy(file.getBytes(), target);
		dto.setBfile(filename);
		}
		dto.setBip(InetAddress.getLocalHost().getHostAddress());
		return dao.insertSboard(dto); }  
	@Override public List<BoardDto> selectSboardAll() throws Exception {  return dao.selectSboardAll(); } 
	@Override public BoardDto selectSboard(BoardDto dto) throws Exception { return dao.selectSboard(dto); } 
	@Override public int updateSboardBhit(BoardDto dto) throws Exception {  return dao.updateSboardBhit(dto); }  
	@Override public int updateSboard(BoardDto dto, MultipartFile file, HttpServletRequest request) throws Exception { 
		System.out.println("ㅁ name: " + file.getOriginalFilename());
		System.out.println("ㅁ size: " + file.getSize());
		System.out.println("ㅁ contentType: " + file.getContentType());
		if(file.getSize()==0) {
			dto.setBfile("noimg.PNG");
		}
		else {
		//1. 파일이름 중복되지 않게 처리
		UUID uid = UUID.randomUUID();
		String filename = uid.toString() + "_" + file.getOriginalFilename();
		String uploadpath = request.getSession().getServletContext().getRealPath("/") + "upload";
		System.out.println("ㅁuploadpath: " + uploadpath);
		System.out.println("ㅁfilename: " +filename);
		File target = new File(uploadpath, filename);
		FileCopyUtils.copy(file.getBytes(), target);
		dto.setBfile(filename);
		}
		return dao.updateSboard(dto); }  
	@Override public int deleteSboard(BoardDto dto) throws Exception {  return dao.deleteSboard(dto); }
	@Override
	public List<BoardDto> searchSboard(BoardDto dto) throws Exception { return dao.searchSboard(dto); }
}
