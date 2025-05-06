import java.sql.Statement;
import java.sql.*;
public class Conn {
    Connection c ;
    Statement s;
    public Conn(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql:///airlinemanagementsystem","root","password"); // for default port use /// but if not then mysql
                                                                                                                               // ://localhost:port_number/
            s= c.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
