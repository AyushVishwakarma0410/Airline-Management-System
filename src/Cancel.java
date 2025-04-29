import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;



public class Cancel extends JFrame implements ActionListener {

    JTextField tfpnr;
    JLabel lblDateOfTravel,tfname,cancellationNum,lblfcode,labelfname,labelfcode;
    JButton fetchButton,Flight;

    public Cancel(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(800,450);
        setLocation(300,150);

        Random random = new Random();

        JLabel heading = new JLabel("CANCELLATION");
        heading.setBounds(180,  20,250,35);
        heading.setFont(new Font("AERIAL", Font.PLAIN,32));
        add(heading);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/Cancel.jpg"));
        Image i2 = i1.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(470,120,250,250);
        add(image);

        JLabel lblaadhar = new JLabel("Aadhar");
        lblaadhar.setBounds(60  ,80,150,25);
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(lblaadhar);

        tfpnr = new JTextField();
        tfpnr.setBounds(220,80,150,25);
        add(tfpnr);

        fetchButton = new JButton("Show Details");
        fetchButton.setBackground(Color.BLACK);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setBounds(380,80,120,25);
        fetchButton.addActionListener(this);
        add(fetchButton);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(60,120,150,25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(lblname);

        tfname = new JLabel(); // going to be an unchangeable field
        tfname.setBounds(220,120,150,25);
        add(tfname);

        JLabel CancellationNumber = new JLabel("Cancellation Number");
        CancellationNumber.setFont(new Font("Tahoma", Font.PLAIN,16));
        CancellationNumber.setBounds(60,160,150,25);
        add(CancellationNumber);

        cancellationNum = new JLabel(""+random.nextInt(1000000));
        cancellationNum.setBounds(220,160,150,25);
        add(cancellationNum);

        JLabel FlightCode = new JLabel("Flight Code");
        FlightCode.setBounds(60,200,150,25);
        FlightCode.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(FlightCode);

        lblfcode = new JLabel();
        lblfcode.setBounds(220,200,150,25);
        add(lblfcode);

        JLabel Date =  new JLabel("Date");
        Date.setBounds(60,240,150,25);
        Date.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(Date);

        lblDateOfTravel = new JLabel();
        lblDateOfTravel.setBounds(220,280,150,25);
        add(lblDateOfTravel);

        Flight = new JButton("Cancel");
        Flight.setBackground(Color.BLACK);
        Flight.setForeground(Color.WHITE);
        Flight.setBounds(380,320,120,23);
        Flight.addActionListener(this);
        add(Flight);

        setVisible(true);   // set visible should be true at the end so that it can avoid any rendering issues.

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==fetchButton) {

            String pnr = tfpnr.getText();


            try {
                Conn conn = new Conn();
                String query = "select * from reservation where PNR =  '" + pnr + "'"; // Adding values in the database, Modifying data in the database

                ResultSet rs = conn.s.executeQuery(query);

                if(rs.next()){
                    tfname.setText(rs.getString("name"));
                    lblfcode.setText(rs.getString("nationality"));
                    lblDateOfTravel.setText(rs.getString("date"));
                }
                else{
                    JOptionPane.showMessageDialog(null,"Please enter correct PNR");
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        else if(ae.getSource()==Flight) {

            String name = tfname.getText();
            String pnr = tfpnr.getText();
            String cancelno = cancellationNum.getText();
            String fcode = lblfcode.getText();
            String date = lblDateOfTravel.getText();


            try {
                Conn conn = new Conn();
                String query = "insert into cancel values('"+pnr+"','"+name+"','"+cancelno+"','"+fcode+"','"+date+"')";

                conn.s.executeUpdate(query);
                conn.s.executeUpdate("delete from reservation where PNR = '"+ pnr +"'");

                    JOptionPane.showMessageDialog(null,"Ticket Cancelled");
                    setVisible(false);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Cancel();
    }
}
