
import java.sql.*;

import javax.swing.JOptionPane;

public class Conn {
	
	Connection c;
	Statement s;

	public Conn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql:///employeemanagementsystem", "root", "123");
			s = c.createStatement();
		} catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Database connection failed:\n" + e.getMessage());
			e.printStackTrace();
		}
	}
}
