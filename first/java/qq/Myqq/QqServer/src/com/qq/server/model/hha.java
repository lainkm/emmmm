/*
 * 
 * */

package com.qq.server.db;

import java.sql.*;

import com.qq.common.User;


public class SqlHelper {
	public static final String url = "jdbc:mysql://127.0.0.1/qqUser";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "666666";
	
	public static Connection conn = null;
	public static PreparedStatement pst = null; 
	
	public static void main(String[] args)
	{
		SqlHelper sh = new SqlHelper();

		System.out.println("haha");
		try {
			System.out.println(sh.query(new User("2","hah","123")));
			System.out.println(sh.query(new User("1","hahah","123")));
			int id = sh.register(new User("0","hah","123"));
			System.out.println(sh.query(new User("2","hah","123")));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public SqlHelper()
	{
		try {
			Class.forName(name);//指定连接类型
			conn = DriverManager.getConnection(url, user, password);//获取连接
			 if(!conn.isClosed()) 
	             System.out.println("Succeeded connecting to the Database!");

			} catch (Exception e) {
			e.printStackTrace();
			}  
	}
	
	public boolean query(User u) throws SQLException
	{
		 Statement statement = conn.createStatement();
         String sql = "select * from qq where qq_num = '"+u.getId()+"' and qq_pw = '"+u.getPw()+"'";
         ResultSet rs = statement.executeQuery(sql);
         boolean flag = false;
         if (rs.next())
         {
        	 flag = true;
         }
         else{
        	 flag = false;
         }
         rs.close();
         conn.close();
         return flag;
	}
	
	public int register(User u) throws SQLException
	{
		int id = 0;
		Statement statement = conn.createStatement();
        String sql_findId = "select max(qq_num) from qq";
        ResultSet rs = statement.executeQuery(sql_findId);
        id = rs.getInt(1) + 1;
        String sql_insert = "insert into qq values("+"'"+id+"'"+","+"'"+u.getName()+"'"+","+"'"+u.getPw()+"'"+","+")";
        boolean b = statement.execute(sql_insert);
        if (b == true)
        {
        	System.out.println(sql_insert + "\n insert success!");
        }

        rs.close();
        conn.close();
		return id;
	}
	
}