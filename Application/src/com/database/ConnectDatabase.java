package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import java.util.Vector;

public class ConnectDatabase {
	
	protected Connection con;
	protected PreparedStatement ps;
	protected ResultSet rs;
	private static ConnectDatabase connect=new ConnectDatabase();
	private ConnectDatabase() {

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			this.con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:zyoracle", "zy",
					"aini1314");
			// con.setAutoCommit(false);
			if (con != null)
				System.out.println("连接成功");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void closeall() {
		try {

			if (this.ps != null)

				this.ps.close();
			if (this.rs != null)
				this.rs.close();
			if (this.con != null)
				this.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * public int executeUpdate(String a,Object b[]) { int row=0; try {
	 * this.ps=this.con.prepareStatement(a); for(int i=0;i<b.length;i++) {
	 * ps.setObject(i+1, b[i]); } row=this.ps.executeUpdate(); } catch
	 * (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } return row;
	 * 
	 * }
	 */
	/*
	 * public Item selectcode(String a,String b,String c) { Item flag=new
	 * Item(); try { this.ps=this.con.prepareStatement(a); rs=ps.executeQuery();
	 * while(rs.next()) {
	 * if(b.equals(rs.getString("ID"))&&c.equals(rs.getString("passwd"))) {
	 * flag=new Read(rs.getString("name"),rs.getString("passwd"));
	 * flag.cardID=rs.getString("ID"); flag.b=rs.getDouble("b"); } } } catch
	 * (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } return flag;
	 * 
	 * }
	 */
	public void update(double a, String p)

	{
		String in = "update atm set b=? where ID=? ";

		try {
			ps = con.prepareStatement(in);
			ps.setDouble(1, a);
			ps.setString(2, p);
			if (this.ps.executeUpdate() > 0)
				con.commit();
			else
				con.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.closeall();
		}

		// this.closeall();
		// return f;
	}

	public List<String> selectByTableName(String a) {
		List<String> flag = new ArrayList<String>();
		try {
			this.ps = this.con.prepareStatement("select * from "+a);
			rs = ps.executeQuery();
			while (rs.next()) {
				/*
				 * if(b.equals(rs.getString("ID"))) { flag=new
				 * Read(rs.getString("name"),rs.getString("passwd"));
				 * flag.cardID=rs.getString("ID"); flag.b=rs.getDouble("B"); }
				 */
				flag.add(rs.getString("item"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;

	}
	public static ConnectDatabase getInstance(){
		return connect;
	}
}
