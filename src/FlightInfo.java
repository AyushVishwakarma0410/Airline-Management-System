import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;   // A jar file named rs2xml is added in the library section after than we can import this

public class FlightInfo extends JFrame {

    public FlightInfo(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JTable table = new JTable();

        try{
            Conn conn =  new Conn();
            ResultSet rs = conn.s.executeQuery("select * from flight");
            table.setModel(DbUtils.resultSetToTableModel(rs));  // this will create a table but since the data is added to the JTable variable "table", therefore we will call this method
        }
        catch(Exception e){
            e.printStackTrace();
        }

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0,0,800,500);
        add(jsp);


        setSize(800,500);
        setLocation(400,200);
        setVisible(true);

    }



    public static void main(String[] args) {
        new FlightInfo();
    }
}
