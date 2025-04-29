import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;

public class BoardingPass extends JFrame implements ActionListener {

    JTextField tfpnr;
    JLabel tfname,tfnationality,lblsrc,lbldest,labelfname,labelfcode,labeldate;
    JButton fetchButton;

    public BoardingPass(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(1000,450);
        setLocation(300,150);

        JLabel heading = new JLabel("AIR INDIA");
        heading.setBounds(380,  10,450,35);
        heading.setFont(new Font("AERIAL", Font.PLAIN,32));
        heading.setForeground(Color.BLACK);
        add(heading);

        JLabel subheading = new JLabel("Boarding Pass");
        subheading.setBounds(360,  50,300,30);
        subheading.setFont(new Font("AERIAL", Font.PLAIN,24));
        subheading.setForeground(Color.BLACK);
        add(subheading);

        JLabel pnrDetails = new JLabel("PNR details");
        pnrDetails.setBounds(60  ,100,150,25);
        pnrDetails.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(pnrDetails);

        tfpnr = new JTextField();
        tfpnr.setBounds(220,100,150,25);
        add(tfpnr);

        fetchButton = new JButton("Fetch");
        fetchButton.setBackground(Color.BLACK);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setBounds(380,100,120,25);
        fetchButton.addActionListener(this);
        add(fetchButton);

        JLabel lblname = new JLabel("NAME");
        lblname.setBounds(60,140,150,25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(lblname);

        tfname = new JLabel(); // going to be an unchangeable field
        tfname.setBounds(220,140,150,25);
        add(tfname);

        JLabel nationality = new JLabel("NATIONALITY");
        nationality.setBounds(60,160,150,25);
        nationality.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(nationality);

        tfnationality = new JLabel();
        tfnationality.setBounds(220,160,150,25);
        add(tfnationality);

        JLabel SRC = new JLabel("SRC");
        SRC.setBounds(60,200,150,25);
        SRC.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(SRC);

        lblsrc = new JLabel();
        lblsrc.setBounds(220,200,150,25);
        add(lblsrc);

        JLabel DEST =  new JLabel("DEST");
        DEST.setBounds(380,200,150,25);
        DEST.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(DEST);

        lbldest = new JLabel();
        lbldest.setBounds(420,200,150,25);
        add(lbldest);

        JLabel lblfname = new JLabel("Flight Name");
        lblfname.setBounds(60,260,150,25);
        lblfname.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(lblfname);

        labelfname = new JLabel();
        labelfname.setBounds(220,260,150,25);
        add(labelfname);

        JLabel lblfcode = new JLabel("Flight Code");
        lblfcode.setBounds(380,260,150,25);
        lblfcode.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(lblfcode);

        labelfcode = new JLabel();
        labelfcode.setBounds(220,240,150,25);
        add(labelfcode);

        JLabel lbldate = new JLabel("Date");
        lbldate.setBounds(60,300,150,25);
        lbldate.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(lbldate);

        labeldate = new JLabel();
        labeldate.setBounds(220,300,150,25);
        add(labeldate);

        ImageIcon image1 = new ImageIcon(ClassLoader.getSystemResource("icons/airindia.png"));
        Image image2 = image1.getImage().getScaledInstance(300,230,Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(image2);
        JLabel lblimage = new JLabel(image);
        lblimage.setBounds(600,0,300,300);
        add(lblimage);

        setVisible(true);   // set visible should be true at the end so that it can avoid any rendering issues.

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

            String pnr = tfpnr.getText();


            try {
                Conn conn = new Conn();
                String query = "select * from reservation where PNR =  '" + pnr + "'"; // Adding values in the database, Modifying data in the database

                ResultSet rs = conn.s.executeQuery(query);

                if(rs.next()){
                    tfname.setText(rs.getString("name"));
                    tfnationality.setText(rs.getString("nationality"));
                    lblsrc.setText(rs.getString("src"));
                    lbldest.setText(rs.getString("dest"));
                    labelfname.setText(rs.getString("FlightName"));
                    labelfcode.setText(rs.getString("FlightCode"));
                    labeldate.setText(rs.getString("date"));;
                }
                else{
                    JOptionPane.showMessageDialog(null,"Please enter correct PNR");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public static void main(String[] args) {
        new BoardingPass();
    }
}
