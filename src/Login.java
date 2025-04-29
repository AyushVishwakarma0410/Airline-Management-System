import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;


// 1.  JFrame is present is swing, seems like the classes which starts letter J are of swing
// 2.  ActionListener is an interface which is used to perform actions, especially button clicks in GUI applications.
// 3.  When a user interacts with a component (like clicking a button), an action event is generated. The ActionListener
//     interface listens for such events and executes custom code when an event occurs.





public class Login extends JFrame implements ActionListener {


    /* Declaring globally so that we can access these varaibles in multiple methods */
    JButton reset,submit,close;
    JTextField tfUsername;
    JPasswordField tfPassword;





    public Login(){



        // Here, 1. Frame (the popup screen) and its size color etc.
        //       2. Setting the background color from default to white
        //       3. How to write something on the frame as heading (Username, Password)
        //       4. layout stuff
        //       5. Text fields and buttons, their working is defined in the next method







        /* Color of frame */
        getContentPane().setBackground(Color.WHITE);   // or we can also use new Color(r,b,g)

        /* Layout provided by system(border layout) set to null, as we are not using it */
        setLayout(null);

         /* Here we are providing label i.e. we want to write something on the frame, therefore we are using JLabel class
           present in swing */

        JLabel lblusername = new JLabel("Username");   // label named username
        lblusername.setBounds(20,20,100,20);  // position of username label
        add(lblusername);  // This method is used to provide visibility in the frame

        /* Text box  */
        tfUsername = new JTextField();  // Class JTextField is used to create box
        tfUsername.setBounds(130, 20, 200, 20);  // Parameters of positioning of the box
        add(tfUsername); // Provide visibility

        JLabel lblPassword = new JLabel("Password");   // label named Password
        lblPassword.setBounds(20,60,100,20);  // position of Password label
        add(lblPassword); // Provide visibility

        tfPassword = new JPasswordField();  // Class JPasswordField is used to create box which is specifically used for passwords
        tfPassword.setBounds(130, 60, 200, 20);  // Parameters of positioning of the box
        add(tfPassword); // Provide visibility



        /* Adding buttons */

        reset = new JButton("Reset");  // JBUTTON class is used to create buttons, here it is the reset button
        reset.setBounds(40,120,120,20 ); // setting parameters
        reset.addActionListener(this);// reset listens, passes to the listener(login) and then login goes to actionPerformed
        add(reset); // add visibility

        submit = new JButton("Submit");  // JBUTTON class is used to create buttons, here it is the submit button
        submit.setBounds(190,120,120,20 ); // setting parameters
        submit.addActionListener(this);// submit listens, passes to the listener(login) and then login goes to actionPerformed
        add(submit); // add visibility

        close = new JButton("close");  // JBUTTON class is used to create buttons, here it is the close button
        close.setBounds(115,150,120,20 ); // setting parameters
        close.addActionListener(this); // close listens, passes to the listener(login) and then login goes to actionPerformed
        add(close); // add visibility


        /* Order of methods really matters in Jframe */


//        Correct Order to Follow in Swing with null Layout
//        If using null layout, always follow this order:
//        Create all components first (JLabel, JTextField, etc.).
//        Add them to the frame using add(Component).
//        THEN call setLayout(null); (if required).
//        Set setVisible(true); at the end to avoid rendering issues.



        /* Location of frame */
        setLocation(500,250);

        /* Size of frame */
        setSize(400,250);

        /* Visibility of frame, we have to set it to visible, because it is usually invisible*/
        setVisible(true);


        }





    /* Now the working of ActionListener i.e. working of buttons */

    public void actionPerformed(ActionEvent ae){

            if(ae.getSource() == submit){
                String username = tfUsername.getText();
                String password = tfPassword.getText();   // getText is showing deprecated but we cannot use getPassword because it does not return string

                try {
                    // some jdbc thing to store data in database

                    Conn c = new Conn();

                    String query = "select * from login where username = '"+username+ "' and password = '"+password+"'"; // Taking values from the login table
                    // Above is a DDL command of SQL and while using a DDL command, always use the below executeQuery() method because we are taking values from the database.

                    ResultSet rs =  c.s.executeQuery(query);

                    if(rs.next())
                    {
                        new Home();
                        setVisible(false);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Invalid Username or Password");
                        setVisible(false);
                    }



                }catch(Exception e){
                    e.printStackTrace();
                }

            }
            else if (ae.getSource() == close) {

                setVisible(false);
            }
            else if (ae.getSource() == reset) {

                tfUsername.setText("");
                tfPassword.setText("");
            }


    }

















    public static void main(String[] args) {
        new Login();
//                The object is created without a reference, making it eligible for garbage collection.
//                This is because Swing GUI components are managed by the JVM event dispatch thread (EDT).
//                The JFrame object stays in memory even without a reference, as long as the window is visible.
//                However, the JFrame window remains open until manually closed.
//                The object gets destroyed when the window is closed and the garbage collector runs.
//                Use setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); to properly exit the program when closing the window.
        {

        }
    }
}
