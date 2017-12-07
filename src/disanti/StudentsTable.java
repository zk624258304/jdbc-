package disanti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentsTable {

	public static void main(String[] args) {
	Connection connection=null;
	PreparedStatement stmt=null;
	//1、加载驱动
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	//2、建立连接
	try {
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/zuoye", "zhao", "1234");
		stmt=connection.prepareStatement("insert into students values(?,?)");
		stmt.setInt(1, 8);
		stmt.setString(2, "h");
		stmt.execute();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	//关闭连接
	finally{
		try {
			if(connection!=null){
			stmt.close();
			connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	}

}
