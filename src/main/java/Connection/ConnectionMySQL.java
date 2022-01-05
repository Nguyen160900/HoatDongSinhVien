package Connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionMySQL {

	public static Connection getConnection(){  
		String url = "jdbc:mysql://localhost:3306/sotay_v2";
        String user = "root";
        String pass = "";
        
        Connection con = null;  
        try
        {  
        	Class.forName("com.mysql.jdbc.Driver");  
            con = DriverManager.getConnection(url, user, pass);  
            System.out.println("Susscesfully!!!");
        }
        catch(Exception e){System.out.println(e);}  
        return con;  
    }  
	
}
