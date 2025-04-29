import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;






public class Home extends JFrame implements ActionListener {

    public Home(){
        setLayout(null);

        // getting image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/front.jpg"));

        //        add() will not work directly because it does not have any constructor to perform this operation

        JLabel image = new JLabel(i1);
        image.setBounds(0,0,1600,800);
        add(image);

        JLabel heading = new JLabel("AIR INDIA WELCOMES YOU");
        heading.setBounds(500,40,1400,40);
        heading.setForeground(Color.BLACK);
        heading.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN,36));
        image.add(heading);  // since we need to show the heading on image therefore we will call image.add rather than add() as add() will show it on the frame behind the image

        // Creating a menubar
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);

        // Setting details
        JMenu details = new JMenu("Details");
        menubar.add(details);

        // Setting menubar items
        JMenuItem flightdetails = new JMenuItem("Flight details");
        flightdetails.addActionListener(this);
        details.add(flightdetails);

        JMenuItem customerDetails = new JMenuItem("Add Customer details");
        customerDetails.addActionListener(this);
        details.add(customerDetails);

        JMenuItem bookFlight = new JMenuItem("Book Flight");
        bookFlight.addActionListener(this);
        details.add(bookFlight);

        JMenuItem journeyDetails = new JMenuItem("Journey details");
        journeyDetails.addActionListener(this);
        details.add(journeyDetails);

        JMenuItem ticketCancellation = new JMenuItem("Cancel Ticket");
        ticketCancellation.addActionListener(this);
        details.add(ticketCancellation);



        JMenu tickets = new JMenu("Tickets");
        menubar.add(tickets);

        JMenuItem boardingPass = new JMenuItem("Boarding Pass");
        boardingPass.addActionListener(this);
        tickets.add(boardingPass);



        setExtendedState(JFrame.MAXIMIZED_BOTH); // Used to full
        setVisible(true);



    }


    public void actionPerformed(ActionEvent ae){

        String text = ae.getActionCommand();

        if(text.equals("Add Customer details")){
            new AddCustomer();
        }else if (text.equals("Flight details")){
            new FlightInfo();
        }else if(text.equals("Book Flight")){
            new BookFlight();
        }else if(text.equals("Journey details")){
            new JourneyDetails();
        }else if(text.equals("Cancel Ticket")){
            new Cancel();
        } else if (text.equals("Boarding Pass")) {
            new BoardingPass();
        }

    }





    public static void main(String[] args) {
        new Home();

    }
}
