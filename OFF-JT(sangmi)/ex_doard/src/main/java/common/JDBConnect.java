package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;

public class JDBConnect {
	/*
	 * 이 클래스는 총 4개의 멤버변수를 선언함. Connection 데이터베이스와 연결을 담당 Statement 인파라미터가 없는 정적 쿼리문을
	 * 실행할 때 사용 PreparedStatement 인파라미터가 있는 동적 쿼리문을 실행할 때 사용, 인파라미터는 쿼리문 작성 시 매개변수로
	 * 전달된 값을 설정할 때 사용 ?(물음표)로 포현하는데, 다시한번 뒤에서 설명함 ResultSet Select 쿼리문의 결과를 저장할 때
	 * 사용
	 */

	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;

	// 기본 생성자
	public JDBConnect() {
		try {
			// JDBC 드라이버 로드
			Class.forName("oracle.jdbc.OracleDriver");

			// DB에 연결
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			// jdbc:oracle:thin 오라클 프로토콜
			// @localhost 호스트명(혹은IP주소)
			// 1521 포트번호
			// xe SID
			String id = "system";
			String pwd = "1234";
			con = DriverManager.getConnection(url, id, pwd);
			System.out.println("DB 연결 성공 (기본 생성자)");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// 두 번쨰 생성자
	public JDBConnect(String driver, String url, String id, String pwd) {
		try {
			// JDBC 드라이버 로드
			Class.forName(driver);

			// DB에 연결
			con = DriverManager.getConnection(url, id, pwd);
			System.out.println("DB 연결 성공 (인수 생성자 1)");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// 세 번쨰 생성자
	public JDBConnect(ServletContext applicaion) {
		try {
			// JDBC 드라이버 로드
			String driver = applicaion.getInitParameter("OracleDriver");
			Class.forName(driver);

			String url = applicaion.getInitParameter("OracleURL");
			String id = applicaion.getInitParameter("OracleId");
			String pwd = applicaion.getInitParameter("OraclePwd");
			// DB에 연결
			con = DriverManager.getConnection(url, id, pwd);
			System.out.println("DB 연결 성공 (인수 생성자 2)");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// 연결 해제 (자원 반납)
	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (psmt != null)
				psmt.close();
			if (con != null)
				con.close();

			System.out.println("JDBC 자원 해제");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
