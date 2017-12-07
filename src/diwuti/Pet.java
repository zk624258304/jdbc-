package diwuti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class Pet {

	public static void main(String[] args) {
		Connection connection=null;
		PreparedStatement pstmt=null;
		//1、加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//2、建立连接
		try {
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/zuoye", "zhao", "1234");
			pstmt=connection.prepareStatement("insert into pet(id,name,health,love,mastername,masterpaw) values(?,?,?,?,?,?)");
			pstmt.setInt(1,1);
			pstmt.setString(2,"hh");
			pstmt.setInt(3,70);
			pstmt.setInt(4,50);
			pstmt.setString(5,"li");
			pstmt.setString(6,"qwe123");
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}//关闭连接
		finally{
			try {
				if(pstmt!=null){
					pstmt.close();
				}
				if(connection!=null){
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
