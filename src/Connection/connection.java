package Connection;
import javax.swing.*;
import java.sql.*;

public class connection {
	Connection conn=null;
	public static Connection connextion() {
		try {
	Class.forName("com.mysql.cj.jdbc.Driver");
Connection  conn =DriverManager.getConnection("jdbc:mysql://localhost/gestion_etudiant","root", "");
return conn;
		}catch (Exception e) {e.printStackTrace();}
		return null;
	}
}
