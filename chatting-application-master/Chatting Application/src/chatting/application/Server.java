package chatting.application;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server implements ActionListener {

    JTextField text;
    JPanel a1;
    static Box vertical = Box.createVerticalBox();
    static JFrame f = new JFrame();
    static DataOutputStream dout;

    // Constructor for setting up the GUI
    Server() {
        f.setLayout(null);

        // Header panel
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 450, 70);
        p1.setLayout(null);
        f.add(p1);

        // Back button
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(5, 20, 25, 25);
        p1.add(back);

        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }
        });

        // Profile picture
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/1.png"));
        Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile = new JLabel(i6);
        profile.setBounds(40, 10, 50, 50);
        p1.add(profile);

        // Click profile to open profile window
        profile.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                openProfileWindow();  // Open the profile window
            }
        });

        // Status
        JLabel name = new JLabel("Server");
        name.setBounds(110, 15, 100, 18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        p1.add(name);

        JLabel status = new JLabel("Active Now");
        status.setBounds(110, 35, 100, 18);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF", Font.BOLD, 14));
        p1.add(status);

        // Chat panel
        a1 = new JPanel();
        a1.setBounds(5, 75, 440, 570);
        a1.setLayout(new BoxLayout(a1, BoxLayout.Y_AXIS));  // Using BoxLayout for dynamic message display
        JScrollPane scroll = new JScrollPane(a1);
        scroll.setBounds(5, 75, 440, 570);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        f.add(scroll);

        // Text field for input
        text = new JTextField();
        text.setBounds(5, 655, 310, 40);
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(text);

        // Send button
        JButton send = new JButton("Send");
        send.setBounds(320, 655, 123, 40);
        send.setBackground(new Color(7, 94, 84));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        send.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(send);

        // Frame settings
        f.setSize(450, 700);
        f.setLocation(200, 50);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.WHITE);

        f.setVisible(true);
    }

    // Action performed when the send button is clicked
    public void actionPerformed(ActionEvent ae) {
        try {
            String out = text.getText();

            // Avoid sending empty messages
            if (!out.trim().isEmpty()) {
                JPanel p2 = formatLabel(out);

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // Create message panel and add to the chat window
                        JPanel right = new JPanel(new BorderLayout());
                        right.add(p2, BorderLayout.LINE_END);
                        vertical.add(right);
                        vertical.add(Box.createVerticalStrut(15));
                        a1.add(vertical, BorderLayout.PAGE_START);

                        try {
                            dout.writeUTF(out);  // Send message to client
                        } catch (IOException ex) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        text.setText("");  // Clear the input field

                        // Refresh the layout
                        f.validate();
                        f.repaint(); // To refresh the frame and show the new message
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Format the message with time and sender styling
    public static JPanel formatLabel(String out) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");
        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(new Color(37, 211, 102));  // Green for outgoing message
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15, 15, 15, 50));

        panel.add(output);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        time.setForeground(new Color(100, 100, 100));
        time.setFont(new Font("Tahoma", Font.ITALIC, 12));

        panel.add(time);

        return panel;
    }

    // Method to open the profile window
    private void openProfileWindow() {
        JFrame profileFrame = new JFrame("Profile");

        // Setting the profile window layout
        profileFrame.setLayout(new BorderLayout());
        profileFrame.setSize(400, 300);
        profileFrame.setLocation(250, 200);

        // Profile Picture
        JLabel profilePic = new JLabel(new ImageIcon(ClassLoader.getSystemResource("icons/1.png")));
        profilePic.setHorizontalAlignment(JLabel.CENTER);
        profileFrame.add(profilePic, BorderLayout.CENTER);

        // User details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));

        JLabel userName = new JLabel("Username: Server");
        userName.setFont(new Font("Arial", Font.PLAIN, 16));
        detailsPanel.add(userName);

        JLabel userStatus = new JLabel("Status: Active Now");
        userStatus.setFont(new Font("Arial", Font.PLAIN, 16));
        detailsPanel.add(userStatus);

        profileFrame.add(detailsPanel, BorderLayout.SOUTH);

        // Close button
        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(e -> profileFrame.dispose());  // Close the profile window
        profileFrame.add(closeBtn, BorderLayout.NORTH);

        profileFrame.setVisible(true);
    }

    // Main method for running the server
    public static void main(String[] args) {
        new Server();

        try {
            ServerSocket skt = new ServerSocket(6001);
            while (true) {
                Socket s = skt.accept();
                DataInputStream din = new DataInputStream(s.getInputStream());
                dout = new DataOutputStream(s.getOutputStream());

                while (true) {
                    String msg = din.readUTF();
                    JPanel panel = formatLabel(msg);

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            JPanel left = new JPanel(new BorderLayout());
                            left.add(panel, BorderLayout.LINE_START);
                            vertical.add(left);
                            f.validate();  // Revalidate to update the chat window
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
