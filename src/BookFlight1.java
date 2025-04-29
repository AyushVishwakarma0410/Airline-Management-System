//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.sql.*;
//import com.toedter.calendar.JDateChooser;
//
//public class BookFlight1 extends JFrame implements ActionListener {
//
//    protected JTextField tfaadhar, tfname, tfnationality, tfaddress, labelfname, labelfcode;
//    protected JLabel genderLabel; // Renamed to genderLabel for clarity
//    protected JButton bookFlight, fetchButton, Flight;
//    protected Choice source, Destination;
//    protected JDateChooser dcdate;
//
//    public BookFlight1() {
//        getContentPane().setBackground(Color.WHITE);
//        setLayout(null);
//        setSize(1100, 600);
//        setLocation(120, 90);
//
//        // Heading
//        JLabel heading = new JLabel("BOOK FLIGHT");
//        heading.setBounds(340, 20, 500, 35);
//        heading.setFont(new Font("AERIAL", Font.PLAIN, 32));
//        heading.setForeground(Color.BLACK);
//        add(heading);
//
//        // Aadhar
//        tfaadhar = addLabelWithTextField("Aadhar", 60, 80, 150, 25);
//        fetchButton = createButton("Fetch", 380, 80, 120, 25);
//        fetchButton.addActionListener(this);
//
//        // Name
//        tfname = addLabelWithTextField("Name", 60, 120, 150, 25);
//        tfname.setEditable(false);
//
//        // Nationality
//        tfnationality = addLabelWithTextField("Nationality", 60, 160, 150, 25);
//        tfnationality.setEditable(false);
//
//        // Address
//        tfaddress = addLabelWithTextField("Address", 60, 200, 150, 25);
//        tfaddress.setEditable(false);
//
//        // Gender
//        // Gender
//        genderLabel = new JLabel();
//        genderLabel.setBounds(220, 240, 150, 25);
//        addLabelWithTextField("Gender", 60, 240, 150, 25);
//        genderLabel.setForeground(Color.GRAY); // Make it appear disabled
//        add(genderLabel);
//
//        // Source
//        source = createChoice("Source", 60, 280, 220, 280);
//
//        // Destination
//        Destination = createChoice("Destination", 60, 320, 220, 320);
//
//        loadFlightData();
//
//        Flight = createButton("Fetch Flights", 380, 320, 120, 23);
//        Flight.addActionListener(this);
//
//        // Flight Name
//        labelfname = addLabelWithTextField("Flight Name", 60, 360, 150, 25);
//        labelfname.setEditable(false);
//
//        // Flight Code
//        labelfcode = addLabelWithTextField("Flight Code", 60, 400, 150, 25);
//        labelfcode.setEditable(false);
//
//        // Date of Travel
//        dcdate = createDateChooser("Date of Travel", 60, 440, 220, 440);
//
//        // Image
//        addImage("icons/details.jpg", 550, 80, 500, 410);
//
//        // Book Flight Button
//        bookFlight = createButton("Book Flight", 220, 500, 150, 30);
//        bookFlight.addActionListener(this);
//
//        setVisible(true);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent ae) {
//        if (ae.getSource() == fetchButton) {
//            fetchPassengerData();
//        }
//    }
//
//    private void fetchPassengerData() {
//        String aadhar = tfaadhar.getText();
//        try {
//            Conn conn = new Conn();
//            String query = "select * from passenger where aadhar = '" + aadhar + "'";
//            ResultSet rs = conn.s.executeQuery(query);
//
//            if (rs.next()) {
//                tfname.setText(rs.getString("name"));
//                tfnationality.setText(rs.getString("nationality"));
//                tfaddress.setText(rs.getString("address"));
//                genderLabel.setText(rs.getString("gender"));
//            } else {
//                JOptionPane.showMessageDialog(null, "User not found, Please insert Valid aadhar Number");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void loadFlightData() {
//        try {
//            Conn conn = new Conn();
//            String query = "Select * from flight";
//            ResultSet rs = conn.s.executeQuery(query);
//            while (rs.next()) {
//                source.add(rs.getString("source"));
//                Destination.add(rs.getString("destination"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private JTextField addLabelWithTextField(String labelText, int x, int y, int labelWidth, int textFieldHeight) {
//        JLabel label = createLabel(labelText, x, y, labelWidth, textFieldHeight);
//        JTextField textField = new JTextField();
//        textField.setBounds(x + labelWidth + 10, y, 150, textFieldHeight);
//        add(textField);
//        return textField;
//    }
//
//    private JLabel createLabel(String text, int x, int y, int width, int height) {
//        JLabel label = new JLabel(text);
//        label.setFont(new Font("Tahoma", Font.PLAIN, 16));
//        label.setBounds(x, y, width, height);
//        add(label);
//        return label;
//    }
//
//    private JButton createButton(String text, int x, int y, int width, int height) {
//        JButton button = new JButton(text);
//        button.setBackground(Color.BLACK);
//        button.setForeground(Color.WHITE);
//        button.setBounds(x, y, width, height);
//        add(button);
//        return button;
//    }
//
//    private Choice createChoice(String labelText, int labelX, int labelY, int choiceX, int choiceY) {
//        createLabel(labelText, labelX, labelY, 150, 25);
//        Choice choice = new Choice();
//        choice.setBounds(choiceX, choiceY, 150, 25);
//        choice.add("Choose Your Destiny");
//        add(choice);
//        return choice;
//    }
//
//    private JDateChooser createDateChooser(String labelText, int labelX, int labelY, int dateX, int dateY) {
//        createLabel(labelText, labelX, labelY, 150, 25);
//        JDateChooser dateChooser = new JDateChooser();
//        dateChooser.setBounds(dateX, dateY, 150, 25);
//        add(dateChooser);
//        return dateChooser;
//    }
//
//    private void addImage(String imagePath, int x, int y, int width, int height) {
//        ImageIcon image1 = new ImageIcon(ClassLoader.getSystemResource(imagePath));
//        Image image2 = image1.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
//        ImageIcon image = new ImageIcon(image2);
//        JLabel imageLabel = new JLabel(image);
//        imageLabel.setBounds(x, y, width, height);
//        add(imageLabel);
//    }
//
//    public static void main(String[] args) {
//        new BookFlight1();
//    }
//}