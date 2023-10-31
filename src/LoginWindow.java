import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.sound.midi.Transmitter;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatDarkLaf;

import java.sql.*;

public class LoginWindow{
    JFrame LoginWindow;
    JPanel RootPanel, TransparentPanel;
    JLabel UserLabel, PassLabel, TitleLabel, subTitleLabel;
    JButton LoginButton, CloseButton;
    JTextField UserField;
    JPasswordField PassField;

    LoginWindow(){
        LoginWindow = new JFrame("Valorant Skin Catalouge and Sales");

        RootPanel = new JPanel();
        TransparentPanel = new JPanel();

        UserLabel = new JLabel();
        PassLabel = new JLabel();
        TitleLabel = new JLabel();
        subTitleLabel = new JLabel();

        LoginButton = new JButton();
        CloseButton = new JButton();
        
        UserField = new JTextField();
        PassField = new JPasswordField();

        //Setting up local variables
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        Font titlefont = (new Font("Valorant", Font.BOLD, 55));
        Font subtitlefont = (new Font("Valorant", Font.BOLD, 25));
        Font textfont = (new Font("PT Sans", Font.BOLD, 15));
        Font headfont = (new Font("PT Sans", Font.BOLD, 27));
        Color bgColor = new Color(20, 20, 20);
        Color bgColor2 = new Color(115, 115, 115,100);
        Color TitleColor = new Color(255,70,85);

        RootPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        RootPanel.setBackground(bgColor);
        RootPanel.setBounds(0,0,1080, 720);
        RootPanel.setLayout(null);

        //TransparentPanel.setBackground(bgColor2);
        TransparentPanel.setBounds(180,120,720, 480);
        TransparentPanel.setLayout(null);
        TransparentPanel.putClientProperty(FlatClientProperties.STYLE, "arc:100");

        TitleLabel.setText("VALORANT");
        TitleLabel.setFont(titlefont);
        TitleLabel.setBounds(200, 30, 720, 56);
        TitleLabel.setForeground(TitleColor);

        subTitleLabel.setText("SKIN SALES DATABASE");
        subTitleLabel.setFont(subtitlefont);
        subTitleLabel.setBounds(215, 75, 720, 50);
        subTitleLabel.setForeground(TitleColor);

        UserLabel.setText("Username:");
        UserLabel.setFont(headfont);
        UserLabel.setBounds(90, 120, 720, 50);
        UserLabel.setForeground(Color.white);

        PassLabel.setText("Password:");
        PassLabel.setFont(headfont);
        PassLabel.setBounds(90, 220, 720, 50);
        PassLabel.setForeground(Color.white);

        UserField.setText("");
        UserField.setFont(textfont);
        UserField.setBounds(90, 160, 540, 50);

        PassField.setText("");
        PassField.setFont(textfont);
        PassField.setBounds(90, 260, 540, 50);

        LoginButton.setText("Login");
        LoginButton.setBackground(TitleColor);
        LoginButton.setFont(textfont);
        LoginButton.setForeground(Color.WHITE);
        LoginButton.setBounds(280, 350, 160, 50);
        LoginButton.putClientProperty(FlatClientProperties.STYLE, "arc:30");
        LoginButton.setFocusable(false);

        CloseButton = new JButton();
        Image closeImage;
                try {
                    closeImage = ImageIO.read(LoginWindow.class.getResourceAsStream("/Images/IconsLogos/closewhite50x50.png"));
                    ImageIcon imageIcon = new ImageIcon(closeImage);
                    CloseButton.setIcon(imageIcon);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        CloseButton.setBounds(1000,5,100,50);
        CloseButton.setFocusable(false);
        CloseButton.setContentAreaFilled(false);
        CloseButton.setBorderPainted(false);
        CloseButton.setBorder(null);


        //This is the login button. Opens the main window when credentials are correct
        LoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                String ID = UserField.getText();
                String Pass = new String(PassField.getPassword());
                if(ID.equals("admin") && Pass.equals("password") ){
                    JOptionPane.showMessageDialog(null, "Welcome!");
                    TryVideo homepage = new TryVideo();
                    LoginWindow.dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Invalid Credentials");
                    System.out.println(ID);
                    System.out.println("Invalid Credentials");
                    System.out.println(Pass);
                }
            }
        });

        //This is the close button. It closes the application when pressed
        CloseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                System.exit(0);
                }
            }
        );
        
        // Adding of Components to the Panels
        TransparentPanel.add(UserLabel);
        TransparentPanel.add(PassLabel);
        TransparentPanel.add(subTitleLabel);
        TransparentPanel.add(LoginButton);
        TransparentPanel.add(UserField);
        TransparentPanel.add(PassField);
        TransparentPanel.add(TitleLabel);

        RootPanel.add(CloseButton);
        RootPanel.add(TransparentPanel);
        
        //Adding of Panels to the main window
        LoginWindow.getContentPane().add(RootPanel);

        //Window Essential
        LoginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LoginWindow.setUndecorated(true);
        LoginWindow.setSize(1080, 720);
        LoginWindow.setResizable(false);
        LoginWindow.getContentPane().setLayout(null);

        //This is the function that is responsible for making the window appear centered on startup
        int w = LoginWindow.getSize().width;
        int h = LoginWindow.getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;
        LoginWindow.setLocation(x,y);
        LoginWindow.setVisible(true);
    }

     public static void main(String[] args) {
        FlatDarkLaf.setup();
        new LoginWindow();
    }
}