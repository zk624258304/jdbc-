package diwuti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PetLogin {

	public static void main(String[] args) {
		Connection connection=null;
		PreparedStatement pstmt=null;
		ResultSet rSet=null;
		//1、加载驱动
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				//2、建立连接
				try {
					String sql="select * from pet where mastername=? and masterpaw=?";
					Scanner input=new Scanner(System.in);
					System.out.println("请输入账号：");
					String names=input.next();
					System.out.println("请输入密码：");
					String pwas=input.next();
					connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/zuoye", "zhao", "1234");
					pstmt=connection.prepareStatement(sql);
					pstmt.setString(1, names);
					pstmt.setString(2, pwas);
					rSet=pstmt.executeQuery();	
					if(rSet.next()){
						System.out.println("登录成功");
						String name=rSet.getString("name");
						System.out.println(name);
						}
					
					
				} catch (SQLException e) {
					e.printStackTrace();
				}//关闭连接
				finally{
					try {
						if(rSet!=null){
							rSet.close();
						}
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
