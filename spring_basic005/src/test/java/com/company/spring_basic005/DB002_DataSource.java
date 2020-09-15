package com.company.spring_basic005;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DB002_DataSource {
	
	@Autowired
	private DataSource ds;
	
	@Test
	public void testDataSource() throws Exception{
		
		try {
		Connection conn = ds.getConnection();
		System.out.println("basic005 conn: " +conn);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
