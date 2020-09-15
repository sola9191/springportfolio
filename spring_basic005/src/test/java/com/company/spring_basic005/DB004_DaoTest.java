package com.company.spring_basic005;

import java.net.InetAddress;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.company.dao.BoardDao;
import com.company.dto.BoardDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DB004_DaoTest {
	
	@Autowired
	private BoardDao dao;
////	insert 
//	@Test
//	public void insert_test() throws Exception{
//		BoardDto dto = new BoardDto();
//		dto.setBname("abc");
//		dto.setBpass("abc");
//		dto.setBtitle("abc");
//		dto.setBcontent("abc");
//		dto.setBip(InetAddress.getLocalHost().getHostAddress());
//		System.out.println(dao.insertSboard(dto));	
//	}
////	select
//	@Test
//	public void selectall() throws Exception {
//		System.out.println(dao.selectSboardAll());
//		
//	}
////	selectone
//	@Test
//	public void test_selectone() throws Exception {
//		BoardDto dto = new BoardDto();
//		dto.setBno(18);
//		System.out.println(dao.selectSboard(dto));
//	}
//	
//// bhit update
//	@Test
//	public void test_updatehit() throws Exception {
//		BoardDto dto = new BoardDto();
//		dto.setBno(18);
//		System.out.println(dao.updateSboardBhit(dto));
//		System.out.println(dao.selectSboard(dto));		
//	}
//	
//// update test
//	@Test
//	public void test_update() throws Exception {
//		BoardDto dto = new BoardDto();
//		
//		dto.setBtitle("new");
//		dto.setBcontent("new");
//		dto.setBno(18);
//		dto.setBpass("abc");
//		System.out.println(dao.updateSboard(dto));	
//	}
////delete test
//	@Test
//	public void test_delete() throws Exception {
//	 	BoardDto dto = new BoardDto();
//		dto.setBno(18);
//		dto.setBpass("abc");
//		System.out.println(dao.deleteSboard(dto));
//	}
//	
	//트랜잭션 테스트
	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Test
	@Rollback(false)
	public void test_tx() throws Exception{
		BoardDto dto = new BoardDto();
		dto.setBno(12);
		dao.updateSboardBhit(dto);
		dao.selectSboard(dto);		
	}
}
