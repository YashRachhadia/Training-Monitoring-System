package mymvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Developer_Login {
	
	public static boolean checkUser(String name, String pass) {
		boolean b = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
			PreparedStatement ps = con.prepareStatement("select * from new_registered_developers where Name=? and Password=?");
			ps.setString(1,name);
			ps.setString(2, pass);
			
			ResultSet rs = ps.executeQuery();
			b = rs.next();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return b;
	}
}
