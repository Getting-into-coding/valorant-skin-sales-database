import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class mainpage {

    //Establishing connection with SQL server
	String sqlForName = "com.mysql.cj.jdbc.Driver";
	String sqlURL = "jdbc:mysql://localhost/ValorantSkinDatabase";
    String user = "root";
    String password = "59gb363WvvDNk1nuW";
	Connection con;
	PreparedStatement pst;
	ResultSet rs;

    //Declaration of Components
    JFrame MainWindow;
    
    JPanel NavPanel, DashPanel, ImagePanel, HomePanel, GraphPanel;

    JButton DashButton, HomeButton, GraphButton, LogoutButton;

    //JComboBox GunType, SkinFamily, SortBy;

    JTable DashTable;

    JScrollPane TableScrollPane;


    mainpage(){
        //Instantiations
        MainWindow = new JFrame();

        NavPanel = new JPanel();
        DashPanel = new JPanel();
        ImagePanel = new JPanel();
        HomePanel = new JPanel();
        GraphPanel = new JPanel();

        DashButton = new JButton();
        HomeButton = new JButton();
        GraphButton = new JButton();
        LogoutButton = new JButton();

        DashTable = new JTable();

        //Setting up local variables
        Font titlefont = (new Font("Valorant", Font.BOLD, 55));
        Font subtitlefont = (new Font("Valorant", Font.BOLD, 25));
        Font textfont = (new Font("PT Sans", Font.BOLD, 15));
        Font buttonFont = (new Font("PT Sans", Font.BOLD, 12));
        Font headfont = (new Font("PT Sans", Font.BOLD, 27));
        Color bgColor = new Color(20, 20, 20);
        Color bgColor2 = new Color(115, 115, 115,100);
        Color TitleColor = new Color(255,70,85);

        //Panel Variables
        int widthNav = 120, widthDash = 600, widthImage = 360;

        //Navigation Panel
        NavPanel.setBorder(BorderFactory.createLineBorder(Color.red));
        NavPanel.setBackground(bgColor);
        NavPanel.setBounds(0,0,widthNav, 720);
        NavPanel.setLayout(null);
        NavPanel.putClientProperty(FlatClientProperties.STYLE, "arc:40");
        
        //Image and Data Panel
        ImagePanel.setBorder(BorderFactory.createLineBorder(Color.blue));
        ImagePanel.setBackground(bgColor);
        ImagePanel.setBounds(widthNav+widthDash,0,widthImage, 720);
        ImagePanel.setLayout(null);
        ImagePanel.putClientProperty(FlatClientProperties.STYLE, "arc:40");
        
        //Dashboard Panel
        DashPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
        DashPanel.setBounds(widthNav,0,widthDash, 720);
        DashPanel.setLayout(null);
        DashPanel.setBackground(bgColor);

        //Home Panel
        HomePanel.setBorder(BorderFactory.createLineBorder(Color.PINK));
        HomePanel.setBounds(widthNav,0,widthDash+widthImage, 720);
        HomePanel.setLayout(null);
        HomePanel.setBackground(bgColor);

        //Graph Panel
        GraphPanel.setBorder(BorderFactory.createLineBorder(Color.green));
        GraphPanel.setBounds(widthNav,0,widthDash+widthImage, 720);
        GraphPanel.setLayout(null);
        GraphPanel.setBackground(bgColor);

        //Button Variables
        int buttonx = (widthNav - 70)/2, buttonspacing = 30, buttony = (720/2);
        int buttonpos1 = buttony - (buttonspacing*3);
        int buttonpos2 = buttony - (buttonspacing*1);
        int buttonpos3 = buttony + (buttonspacing*1);
        int buttonpos4 = buttony + (buttonspacing*3);
        
        //Buttons
        HomeButton.setText("Home");
        HomeButton.setBackground(TitleColor);
        HomeButton.setFont(buttonFont);
        HomeButton.setForeground(Color.WHITE);
        HomeButton.setBounds(buttonx, buttonpos1, 70, 50);
        HomeButton.putClientProperty(FlatClientProperties.STYLE, "arc:30");
        HomeButton.setFocusable(false);

        DashButton.setText("Dash");
        DashButton.setBackground(TitleColor);
        DashButton.setFont(buttonFont);
        DashButton.setForeground(Color.WHITE);
        DashButton.setBounds(buttonx, buttonpos2, 70, 50);
        DashButton.putClientProperty(FlatClientProperties.STYLE, "arc:30");
        DashButton.setFocusable(false);

        GraphButton.setText("Graph");
        GraphButton.setBackground(TitleColor);
        GraphButton.setFont(buttonFont);
        GraphButton.setForeground(Color.WHITE);
        GraphButton.setBounds(buttonx, buttonpos3, 70, 50);
        GraphButton.putClientProperty(FlatClientProperties.STYLE, "arc:30");
        GraphButton.setFocusable(false);

        LogoutButton.setText("Logout");
        LogoutButton.setBackground(TitleColor);
        LogoutButton.setFont(buttonFont);
        LogoutButton.setForeground(Color.WHITE);
        LogoutButton.setBounds(buttonx, buttonpos4, 70, 50);
        LogoutButton.putClientProperty(FlatClientProperties.STYLE, "arc:30");
        LogoutButton.setFocusable(false);

        //JTable
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/valorantskins";
        String user = "root";
        String password = "59gb363WvvDNk1nuW";

        // SQL query
        String query = "SELECT * FROM valorantspreadsheet";

        try {
            // Establish database connection
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();

            // Execute query and get the result set
            ResultSet rs = stmt.executeQuery(query);

            // Get metadata of the result set
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            // Create a new JTable model
            DefaultTableModel model = new DefaultTableModel(
                new Object[][] {
                    },
                    new String[] {
                            "ID","Gun Type","Skin Family","Cost (Points)","Cost ($)", "No. Sold", "Total Sold", "Release Date"
                    }
                );
            //Table  
            DashTable = new JTable(model){
                private static final long serialVersionUID = 1L; 
                public boolean isCellEditable(int row, int column) { 
                    return false;
                };
            };

            // Add rows from the database to the model
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        TableScrollPane = new JScrollPane();
        TableScrollPane.setBounds(10, 100, widthDash-20, 600);
        
        
        TableScrollPane.setViewportView(DashTable);

        DashPanel.add(TableScrollPane);
        //Defining functions for the JButtons
        HomeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                    HomePanel.setVisible(true);
                    HomePanel.setEnabled(true);

                    ImagePanel.setVisible(false);
                    ImagePanel.setEnabled(false);

                    DashPanel.setVisible(false);
                    DashPanel.setEnabled(false);

                    GraphPanel.setVisible(false);
                    GraphPanel.setEnabled(false);
                }
        });

        DashButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                    HomePanel.setVisible(false);
                    HomePanel.setEnabled(false);

                    ImagePanel.setVisible(true);
                    ImagePanel.setEnabled(true);

                    DashPanel.setVisible(true);
                    DashPanel.setEnabled(true);

                    GraphPanel.setVisible(false);
                    GraphPanel.setEnabled(false);
                }
        });

        GraphButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                    HomePanel.setVisible(false);
                    HomePanel.setEnabled(false);

                    ImagePanel.setVisible(false);
                    ImagePanel.setEnabled(false);

                    DashPanel.setVisible(false);
                    DashPanel.setEnabled(false);

                    GraphPanel.setVisible(true);
                    GraphPanel.setEnabled(true);
                }
        });

        LogoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                    System.out.println("Goodbye!");
                    LoginWindow loginWin = new LoginWindow();
                    MainWindow.dispose();
                    
                }
        });

        
        //Adding Components to Panels
        NavPanel.add(HomeButton);
        NavPanel.add(GraphButton);
        NavPanel.add(DashButton);
        NavPanel.add(LogoutButton);

        //Adding Panels to JFrame
        MainWindow.getContentPane().add(NavPanel);
        MainWindow.getContentPane().add(DashPanel);
        MainWindow.getContentPane().add(ImagePanel);
        MainWindow.getContentPane().add(HomePanel);
        MainWindow.getContentPane().add(GraphPanel);

        //Window Essential
        MainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainWindow.setUndecorated(true);
        MainWindow.setSize(1080, 720);
        MainWindow.setResizable(false);
        MainWindow.getContentPane().setLayout(null);

        //This is the function that is responsible for making the window appear centered on startup
        int w = MainWindow.getSize().width;
        int h = MainWindow.getSize().height;
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;
        MainWindow.setLocation(x,y);
        MainWindow.setVisible(true);
    }

         public static void main(String[] args) {
        FlatDarkLaf.setup();
        new mainpage();
    }

}
