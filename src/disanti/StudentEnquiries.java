package disanti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentEnquiries {

	public static void main(String[] args) {
		Connection connection=null;
		Statement stmt=null;
		ResultSet rSet=null;
		//1、加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//2、建立连接
		try {
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/zuoye", "zhao", "1234");
			stmt= connection.createStatement();
			rSet=stmt.executeQuery("select id,name from students");
			while(rSet.next()){
				System.out.print(rSet.getInt("id"));
				System.out.print("\t");
				System.out.print(rSet.getString("name"));
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//关闭连接
		finally{
			try {
				if(rSet!=null){
					rSet.close();
					
				
					}
				if(stmt!=null){
					
					stmt.close();
					
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

