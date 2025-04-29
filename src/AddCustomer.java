import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddCustomer extends JFrame implements ActionListener {

    JTextField tfname, tfphone,tfaadhar,tfnationality,tfaddress;
    JRadioButton rbmale,rbfemale;

public AddCustomer(){

    getContentPane().setBackground(Color.WHITE);
    setLayout(null);
    setSize(900,600);
    setLocation(300,150);

    JLabel heading = new JLabel("ADD CUSTOMER DETAILS");
    heading.setBounds(220,20,500,35);
    heading.setFont(new Font("AERIAL", Font.PLAIN,32));
    heading.setForeground(Color.BLACK);
    add(heading);

    JLabel lblname = new JLabel("Name");
    lblname.setBounds(60,80,150,25);
    lblname.setFont(new Font("Tahoma", Font.PLAIN,16));
    add(lblname);

    tfname = new JTextField();
    tfname.setBounds(220,80,150,25);
    add(tfname);

    JLabel nationality = new JLabel("Nationality");
    nationality.setBounds(60,120,150,25);
    nationality.setFont(new Font("Tahoma", Font.PLAIN,16));
    add(nationality);

    tfnationality = new JTextField();
    tfnationality.setBounds(220,120,150,25);
    add(tfnationality);

    JLabel lblaadhar = new JLabel("Aadhar number");
    lblaadhar.setBounds(60,170,150,25);
    lblaadhar.setFont(new Font("Tahoma", Font.PLAIN,16));
    add(lblaadhar);

    tfaadhar = new JTextField();
    tfaadhar.setBounds(220,170,150,25);
    add(tfaadhar);

    JLabel lbladdress = new JLabel("Address");
    lbladdress.setBounds(60,220,150,25);
    lbladdress.setFont(new Font("Tahoma", Font.PLAIN,16));
    add(lbladdress);

    tfaddress = new JTextField();
    tfaddress.setBounds(220,220,150,25);
    add(tfaddress);

    JLabel lblgender =  new JLabel("Gender");
    lblgender.setBounds(60,270,150,25);
    lblgender.setFont(new Font("Tahoma",Font.PLAIN,16));
    add(lblgender);


    /*  Select only one radio button  */
    ButtonGroup genderGroup = new ButtonGroup();


    /* Radio Buttons */
    rbmale = new JRadioButton("Male");
    rbmale.setBounds(220,270,70,25);
    rbmale.setBackground(Color.WHITE);
    add(rbmale);

    rbfemale = new JRadioButton("Female");
    rbfemale.setBounds(300,270,70,25);
    rbfemale.setBackground(Color.WHITE);
    add(rbfemale);

    /* Grouping both buttons such that we can only access one at a time */
    genderGroup.add(rbfemale);
    genderGroup.add(rbmale);

    JLabel lblphone = new JLabel("Phone Number");
    lblphone.setBounds(60,320,150,25);
    lblphone.setFont(new Font("Tahoma", Font.PLAIN,16));
    add(lblphone);

    tfphone = new JTextField();
    tfphone.setBounds(220,320,150,25);
    add(tfphone);

    JButton save = new JButton("SAVE");
    save.setBackground(Color.BLACK);
    save.setForeground(Color.WHITE);
    save.setBounds(220,380,150,30);
    save.addActionListener(this);
    add(save);

    ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("icons/emp.png"));
    JLabel lblimage = new JLabel(image);
    lblimage.setBounds(450,80,280,400);
    add(lblimage);

    setVisible(true);   // set visible should be true at the end so that it can avoid any rendering issues.

}

    @Override
    public void actionPerformed(ActionEvent ae) {
    String name = tfname.getText();
    String nationality = tfnationality.getText();
    String phone = tfphone.getText();
    String address = tfaddress.getText();
    String aadhar = tfaadhar.getText();
    String gender = null;
    if(rbmale.isSelected()){
        gender = "Male";
    }
    else {
        gender = "Female";
    }

    try{
        Conn conn = new Conn();
        String query = "Insert into passenger values('"+name+"','"+nationality+"','"+phone+"','"+address+"','"+aadhar+"','"+gender+"')"; // Adding values in the database, Modifying data in the database
        // Above is the DML command and here we will use executeUpdate() method rather than executeQuery() method

        conn.s.executeUpdate(query);
        JOptionPane.showMessageDialog(null,"Customer Details Added Successfully!");
        setVisible(false);

    }
    catch(Exception e){
        e.printStackTrace();
    }

    }

    public static void main(String[] args) {
        new AddCustomer();
    }
}
