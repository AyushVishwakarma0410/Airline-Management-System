import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;


import com.toedter.calendar.JDateChooser;

public class BookFlight extends JFrame implements ActionListener {

    JTextField tfaadhar;
    JLabel tfname,tfnationality,tfaddress,labelgender,labelfname,labelfcode;
    JButton bookFlight,fetchButton,Flight;
    Choice source,Destination;
    JDateChooser dcdate;

    public BookFlight(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(1100,600);
        setLocation(120,90);

        JLabel heading = new JLabel("BOOK FLIGHT");
        heading.setBounds(340,  20,500,35);
        heading.setFont(new Font("AERIAL", Font.PLAIN,32));
        heading.setForeground(Color.BLACK);
        add(heading);

        JLabel lblaadhar = new JLabel("Aadhar");
        lblaadhar.setBounds(60  ,80,150,25);
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(lblaadhar);

        tfaadhar = new JTextField();
        tfaadhar.setBounds(220,80,150,25);
        add(tfaadhar);

        fetchButton = new JButton("Fetch");
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

        JLabel nationality = new JLabel("Nationality");
        nationality.setBounds(60,160,150,25);
        nationality.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(nationality);

        tfnationality = new JLabel();
        tfnationality.setBounds(220,160,150,25);
        add(tfnationality);

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(60,200,150,25);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(lbladdress);

        tfaddress = new JLabel();
        tfaddress.setBounds(220,200,150,25);
        add(tfaddress);

        JLabel gender =  new JLabel("Gender");
        gender.setBounds(60,240,150,25);
        gender.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(gender);

        labelgender = new JLabel();
        labelgender.setBounds(220,240,150,25);
        add(labelgender);

        JLabel lblsource = new JLabel("Source");
        lblsource.setBounds(60,280,150,25);
        lblsource.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(lblsource);

        source = new Choice();
        source.setBounds(220,280,150,25 );
        source.add("Choose Your Destiny");

        /*Destination.add("Choose Your Destiny"); Hardcode can be done like this for drop down menu, but that not we are going to do
        Destination.add("Choose Your Destiny");
        Destination.add("Choose Your Destiny");
        Destination.add("Choose Your Destiny");*/

        add(source);

        JLabel lbldest = new JLabel("Destination");
        lbldest.setBounds(60,320,150,25);
        lbldest.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(lbldest);


        Destination = new Choice();
        Destination.setBounds(220,320,150,25 );
        Destination.add("Choose Your Destiny");
        add(Destination);

        try{
            Conn conn = new Conn();
            String query = "Select * from flight";
            ResultSet rs = conn.s.executeQuery(query);

            while (rs.next()){
                source.add(rs.getString("source"));
                Destination.add(rs.getString("destination"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        Flight = new JButton("Fetch Flights");
        Flight.setBackground(Color.BLACK);
        Flight.setForeground(Color.WHITE);
        Flight.setBounds(380,320,120,23);
        Flight.addActionListener(this);
        add(Flight);

        JLabel lblfname = new JLabel("Flight Name");
        lblfname.setBounds(60,360,150,25);
        lblfname.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(lblfname);

        labelfname = new JLabel();
        labelfname.setBounds(220,360,150,25);
        add(labelfname);

        JLabel lblfcode = new JLabel("Flight Code");
        lblfcode.setBounds(60,400,150,25);
        lblfcode.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(lblfcode);

        labelfcode = new JLabel();
        labelfcode.setBounds(220,400,150,25);
        add(labelfcode);

        JLabel lbldate = new JLabel("Date of Travel");
        lbldate.setBounds(60,440,150,25);
        lbldate.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(lbldate);

        dcdate = new JDateChooser();
        dcdate.setBounds(220,440,150,25);
        add(dcdate);

        ImageIcon image1 = new ImageIcon(ClassLoader.getSystemResource("icons/details.jpg"));
        Image image2 = image1.getImage().getScaledInstance(450,320,Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(image2);
        JLabel lblimage = new JLabel(image);
        lblimage.setBounds(550,80,500,410);
        add(lblimage);

        bookFlight = new JButton("Book Flight");
        bookFlight.setBackground(Color.BLACK);
        bookFlight.setForeground(Color.WHITE);
        bookFlight.setBounds(220,500,150,30);
        bookFlight.addActionListener(this);
        add(bookFlight);

        setVisible(true);   // set visible should be true at the end so that it can avoid any rendering issues.

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==fetchButton) {

            String aadhar = tfaadhar.getText();


            try {
                Conn conn = new Conn();
                String query = "select * from passenger where aadhar =  '" + aadhar + "'"; // Adding values in the database, Modifying data in the database

                ResultSet rs = conn.s.executeQuery(query);

                if(rs.next()){
                    tfname.setText(rs.getString("name"));
                    tfnationality.setText(rs.getString("nationality"));
                    tfaddress.setText(rs.getString("address"));
                    labelgender.setText(rs.getString("gender"));

                }
                else{
                    JOptionPane.showMessageDialog(null,"User not found, Please insert Valid aadhar Number");
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    else if(ae.getSource()==Flight) {

        String src = source.getSelectedItem();
        String dest = Destination.getSelectedItem();


        try {
            Conn conn = new Conn();
            String query = "select * from flight where source =  '" + src + "' and destination ='"+dest+"'"; // Adding values in the database, Modifying data in the database

            ResultSet rs = conn.s.executeQuery(query);

            if(rs.next()){
                labelfname.setText(rs.getString("f_name"));
                labelfcode.setText(rs.getString("f_code"));

            }
            else{
                JOptionPane.showMessageDialog(null,"No Flights Found! ");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }  else {
            Random random = new Random();
            String aadhar = tfaadhar.getText();
            String name = tfname.getText();
            String nationality = tfnationality.getText();
            String address = tfaddress.getText();
            String gender = labelgender.getText();
            String FlightName = labelfname.getText();
            String FlightCode = labelfcode.getText();
            String src = source.getSelectedItem();
            String des = Destination.getSelectedItem();
            String date = ((JTextField)dcdate.getDateEditor().getUiComponent()).getText();


            try {
                // Adding values in the database, Modifying data in the database
                Conn conn = new Conn();
                String query = "INSERT INTO reservation VALUES ('PNR-"+random.nextInt(1000000) + "','TIC-" + random.nextInt(10000)+"','"+aadhar+"','"+name+"','"+nationality+"','"+FlightName+"','"+FlightCode+"','"+src+"','"+des+"','"+date+"')";


             conn.s.executeUpdate(query);


                    JOptionPane.showMessageDialog(null,"Ticket Booked Successfully!");
                    setVisible(false);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
}

    public static void main(String[] args) {
        new BookFlight();
    }
}
