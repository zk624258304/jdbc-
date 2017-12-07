package lianxi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class BaseJDBC {
	static Scanner input=new Scanner(System.in);
	static boolean flag=true;
	public static void main(String[] arge){
		//主菜单
		while(flag){
			showFirstMenu();
		}
		
	}

	private static void showFirstMenu() {
		System.out.println("******************欢迎*************");
		System.out.println("1、用户注册");
		System.out.println("2、用户登录");
		System.out.println("3、退出");
		int choose=input.nextInt();
		switch (choose) {
		case 1:
			Regist();
			break;
		case 2:
			Login();
			break;
		case 3:
			System.out.println("欢迎下次光临");
			flag=false;
			break;

		
		}
	}

	private static void Login() {
		System.out.println("请输入用户名");
		String name=input.next();
		System.out.println("请输入密码");
		String password=input.next();
		//通过反射机制加载数据库启动包
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//2创建连接
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/easybuy", "zhao", "1234");
			String sql="select * from easybuy_user where loginName=? and password=?";
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, password);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				String names=resultSet.getString("loginName");
				System.out.println("欢迎"+names+"登录");
			}else{
				System.out.println("登录失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		secondMenu();
	}

	private static void secondMenu() {
		System.out.println("1修改用户");
		System.out.println("2删除用户");
		System.out.println("3返回上一级菜单");
		System.out.println("请选择");
		int choose=input.nextInt();
		switch (choose) {
		case 1://修改
			update();
			break;
	case 2://删除
			deldete();
			break;
	case 3://返回
		showFirstMenu();
		break;

		
		}
	}
	//根据昵称修改loginName，修改对于的信息
	private static void update() {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//2创建连接
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/easybuy", "zhao", "1234");
			System.out.println("请输入修改的呢城");
			String loginName=input.next();
			System.out.println("请输入修改真实姓名");
			String userName=input.next();
			System.out.println("请输入修改密码");
			String password=input.next();
			System.out.println("请输修改入性别：1男0女");
			int sex=input.nextInt();
			//sql语句
			String sql="update easybuy_user set userName=?,password=?,sex=? where loginName=?";
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1,userName);
			preparedStatement.setString(2, password);
			preparedStatement.setInt(3, sex);
			preparedStatement.setString(4,loginName);
			int rowNum=preparedStatement.executeUpdate();
			if(rowNum>0){
				System.out.println("更新成功");
			}else{
				
				System.out.println("更新失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			//关闭资源
		}finally{
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		secondMenu();
	}
		
	

	private static void deldete() {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//2创建连接
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/easybuy", "zhao", "1234");
			System.out.println("请输入需要删除的呢城");
			String loginName=input.next();
			//sql语句
			String sql="delete from easybuy_user where loginName=?";
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1,loginName);
			int rowNum=preparedStatement.executeUpdate();
			if(rowNum>0){
				System.out.println("删除成功");
			}else{
				
				System.out.println("删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			//关闭资源
		}finally{
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		secondMenu();
	}
		

	private static void Regist() {
		//通过反射机制加载数据库启动包
			Connection connection=null;
			Statement statement=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//2创建连接
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/easybuy", "zhao", "1234");
			System.out.println("请输入呢城");
			String loginName=input.next();
			System.out.println("请输入真实姓名");
			String userName=input.next();
			System.out.println("请输入密码");
			String password=input.next();
			System.out.println("请输入性别：1男0女");
			int sex=input.nextInt();
			//sql语句
			String sql="insert into easybuy_user(loginName,userName,password,sex)"
					+ "values('"+loginName+"','"+userName+"','"+password+"','"+sex+"')";
			statement=connection.createStatement();
			int rowNum=statement.executeUpdate(sql);
			if(rowNum>0){
				System.out.println("注册成功");
			}else{
				System.out.println("注册失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			//关闭资源
		}finally{
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
