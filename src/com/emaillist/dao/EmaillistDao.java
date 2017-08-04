package com.emaillist.dao;//getlist만들꺼야


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.emaillist.vo.EmaillistVo;

@Repository
public class EmaillistDao {

	
	public int insert(EmaillistVo vo) {//-2
		Connection conn=null;
		PreparedStatement pstmt = null;
		int count=0;
		try {

			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			conn=DriverManager.getConnection(url,"webdb","webdb");
			// 3. SQL문 준비 / 바인딩 / 실행
			String query="insert into emaillist values(seq_emaillist_no.nextval,?,?,?)";
			pstmt=conn.prepareStatement(query);
			
			pstmt.setString(1,vo.getLastName());
			pstmt.setString(2,vo.getFirstName());
			pstmt.setString(3,vo.getEmail());
			
			count=pstmt.executeUpdate();
			
			
			// 4.결과처리
			
			System.out.println(count+"건 등록");
			

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리

			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}

		
		
		return count;
	}
	
	
	
	public List<EmaillistVo> getlist() {
		//각 칸에 테이블이 들어가야겠지-1
	
		// 0. import java.sql.*;

		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<EmaillistVo> list=new ArrayList<EmaillistVo>();
		
		try {

			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			conn=DriverManager.getConnection(url,"webdb","webdb");
			// 3. SQL문 준비 / 바인딩 / 실행
			String query="select no,last_name,first_name,email from emaillist order by no desc";
			pstmt=conn.prepareStatement(query);
			rs=pstmt.executeQuery();
			// 4.결과처리
			while(rs.next()) {
				int no=rs.getInt("no");
				String lastName=rs.getString("last_name");
				String firstName=rs.getString("first_name");
				String email=rs.getString("email");
			
				EmaillistVo vo=new EmaillistVo(no,lastName,firstName,email);
				list.add(vo);
			
			}
			

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리

			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}

		return list;
	}

}
