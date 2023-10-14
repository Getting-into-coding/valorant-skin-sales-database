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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
    
    JPanel NavPanel, DashPanel, ImagePanel, HomePanel, GraphPanel, RootPanel;

    JButton DashButton, HomeButton, GraphButton, LogoutButton;

    //JComboBox GunType, SkinFamily, SortBy;

    JTable DashTable;

    JScrollPane TableScrollPane;

    JLabel LabelID, LabelType, LabelFamily, LabelPoints,LabelUSD, LabelNumbers, LabelTotal, LabelDate, LabelGunImage;
    JLabel ValueID, ValueType, ValueFamily, ValuePoints, ValueUSD, ValueNumbers, ValueTotal, ValueDate;

    mainpage(){
        //Instantiations
        MainWindow = new JFrame();

        NavPanel = new JPanel();
        DashPanel = new JPanel();
        ImagePanel = new JPanel();
        HomePanel = new JPanel();
        GraphPanel = new JPanel();
        RootPanel = new JPanel();

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
        Color bgColor3 = new Color(189, 57, 68, 100);
        Color TitleColor = new Color(255,70,85);
        Color TextColor = new Color(255,255,255);

        //Panel Variables
        int widthNav = 120, widthDash = 600, widthImage = 360;

        RootPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        RootPanel.setBackground(bgColor);
        RootPanel.setBounds(0,0,1080, 720);
        RootPanel.setLayout(null);
        
        //Image and Data Panel
        ImagePanel.setBorder(BorderFactory.createLineBorder(bgColor3));
        ImagePanel.setBackground(bgColor);
        ImagePanel.setBounds(widthNav+widthDash,0,widthImage, 720);
        ImagePanel.setLayout(null);
        //ImagePanel.putClientProperty(FlatClientProperties.STYLE, "arc:40");
        
        //Dashboard Panel
        //DashPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
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

        //Navigation Panel
        //NavPanel.setBorder(BorderFactory.createLineBorder(Color.red));
        NavPanel.setBackground(bgColor3);
        NavPanel.setBounds(-10,0,widthNav, 720);
        NavPanel.setLayout(null);
        NavPanel.putClientProperty(FlatClientProperties.STYLE, "arc:30");
        
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
        TableScrollPane.setBounds(5, 100, widthDash-20, 600);
        
        
        TableScrollPane.setViewportView(DashTable);

        DashPanel.add(TableScrollPane);

        //Instantiations of labels here because I'm lazy to do it on the top again
        LabelGunImage = new JLabel();
        LabelID = new JLabel();
        LabelType = new JLabel();
        LabelFamily = new JLabel();
        LabelPoints = new JLabel();
        LabelUSD = new JLabel();
        LabelNumbers = new JLabel();
        LabelTotal = new JLabel();
        LabelDate = new JLabel();

        ValueID = new JLabel();
        ValueType = new JLabel();
        ValueFamily = new JLabel();
        ValuePoints = new JLabel();
        ValueUSD = new JLabel();
        ValueNumbers = new JLabel();
        ValueTotal = new JLabel();
        ValueDate = new JLabel();

        //Features for the Image Panel
        
        // Add selection listener to table
        ListSelectionModel selectionModel = DashTable.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = DashTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String Dollar = "$";
                    String OutID = DashTable.getValueAt(selectedRow, 0).toString();
                    String OutType = DashTable.getValueAt(selectedRow, 1).toString();
                    String OutFamily = DashTable.getValueAt(selectedRow, 2).toString();
                    String OutPoints = DashTable.getValueAt(selectedRow, 3).toString();
                    String OutUSD = Dollar.concat("").concat(DashTable.getValueAt(selectedRow, 4).toString());
                    String OutNumbers = DashTable.getValueAt(selectedRow, 5).toString();
                    String OutTotal = Dollar.concat("").concat(DashTable.getValueAt(selectedRow, 6).toString());
                    String OutDate = DashTable.getValueAt(selectedRow, 7).toString();
                    ValueID.setText(OutID);
                    ValueType.setText(OutType);
                    ValueFamily.setText(OutFamily);
                    ValuePoints.setText(OutPoints);
                    ValueUSD.setText(OutUSD);
                    ValueNumbers.setText(OutNumbers);
                    ValueTotal.setText(OutTotal);
                    ValueDate.setText(OutDate);

                    String defaultlink = "/Images/GunSkins/";
                    String linktoGunImage = defaultlink.concat("").concat(OutID).concat(".png");
                    Image GunImage;
                            try {
                                GunImage = ImageIO.read(LoginWindow.class.getResourceAsStream(linktoGunImage));
                                ImageIcon imageIcon = new ImageIcon(GunImage);
                                Image resizedImage = imageIcon.getImage().getScaledInstance(275, 100, Image.SCALE_SMOOTH);
                                imageIcon = new ImageIcon(resizedImage);
                                LabelGunImage.setIcon(imageIcon);
                            } catch (IOException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }


                            }
                        }
                     });

        LabelGunImage.setFont(subtitlefont);
        LabelGunImage.setBounds((widthImage/2)-140, 10, 275, 250);
        LabelGunImage.setForeground(TitleColor);
		
        LabelID.setText("ID:");
        LabelID.setFont(subtitlefont);
        LabelID.setBounds(10, buttony-120, (widthImage/2), 50);
        LabelID.setForeground(TitleColor);

        ValueID.setFont(headfont);
        ValueID.setBounds((widthImage/2)-120, buttony-125, (widthImage/2), 50);
        ValueID.setForeground(TextColor);

        LabelType.setText("Gun Type:");
        LabelType.setFont(subtitlefont);
        LabelType.setBounds(10, buttony-60, (widthImage/2), 50);
        LabelType.setForeground(TitleColor);

        ValueType.setFont(headfont);
        ValueType.setBounds((widthImage/2)-15, buttony-65, (widthImage/2), 50);
        ValueType.setForeground(TextColor);

        LabelFamily.setText("Skin Family:");
        LabelFamily.setFont(subtitlefont);
        LabelFamily.setBounds(10, buttony, (widthImage/2)+20, 50);
        LabelFamily.setForeground(TitleColor);

        ValueFamily.setFont(headfont);
        ValueFamily.setBounds((widthImage/2)+20, buttony-5, (widthImage/2), 50);
        ValueFamily.setForeground(TextColor);

        Image ValoPoints;
                try {
                    ValoPoints = ImageIO.read(LoginWindow.class.getResourceAsStream("/Images/IconsLogos/Valopoints45x45.png"));
                    ImageIcon imageIcon = new ImageIcon(ValoPoints);
                    LabelPoints.setIcon(imageIcon);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

        LabelPoints.setText(":");
        LabelPoints.setFont(subtitlefont);
        LabelPoints.setBounds(10, buttony+60, (widthImage/2)+20, 50);
        LabelPoints.setForeground(TitleColor);

        ValuePoints.setFont(headfont);
        ValuePoints.setBounds((widthImage/2)-110, buttony+55, (widthImage/2), 50);
        ValuePoints.setForeground(TextColor);

        LabelUSD.setText("Cost (USD):");
        LabelUSD.setFont(subtitlefont);
        LabelUSD.setBounds(10, buttony+60*2, (widthImage/2)+20, 50);
        LabelUSD.setForeground(TitleColor);

        ValueUSD.setFont(headfont);
        ValueUSD.setBounds((widthImage/2), (buttony+55*2)+5, (widthImage/2), 50);
        ValueUSD.setForeground(TextColor);

        LabelNumbers.setText("Units Sold:");
        LabelNumbers.setFont(subtitlefont);
        LabelNumbers.setBounds(10, buttony+60*3, (widthImage/2)+20, 50);
        LabelNumbers.setForeground(TitleColor);

        ValueNumbers.setFont(headfont);
        ValueNumbers.setBounds((widthImage/2), (buttony+55*3)+5*2, (widthImage/2), 50);
        ValueNumbers.setForeground(TextColor);

        LabelTotal.setText("Total:");
        LabelTotal.setFont(subtitlefont);
        LabelTotal.setBounds(10, buttony+60*4, (widthImage/2)+20, 50);
        LabelTotal.setForeground(TitleColor);

        ValueTotal.setFont(headfont);
        ValueTotal.setBounds((widthImage/2)-70, (buttony+55*4)+5*3, (widthImage/2), 50);
        ValueTotal.setForeground(TextColor);
        
        LabelDate.setText("Release Date:");
        LabelDate.setFont(subtitlefont);
        LabelDate.setBounds(10, buttony+60*5, (widthImage/2)+50, 50);
        LabelDate.setForeground(TitleColor);

        ValueDate.setFont(headfont);
        ValueDate.setBounds((widthImage/2)+30, (buttony+55*5)+5*4, (widthImage/2), 50);
        ValueDate.setForeground(TextColor);
        
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

        ImagePanel.add(LabelID);
        ImagePanel.add(LabelDate);
        ImagePanel.add(LabelFamily);
        ImagePanel.add(LabelNumbers);
        ImagePanel.add(LabelPoints);
        ImagePanel.add(LabelTotal);
        ImagePanel.add(LabelType);
        ImagePanel.add(LabelUSD);
        ImagePanel.add(ValueID);
        ImagePanel.add(ValueType);
        ImagePanel.add(ValueFamily);
        ImagePanel.add(ValuePoints);
        ImagePanel.add(ValueUSD);
        ImagePanel.add(ValueNumbers);
        ImagePanel.add(ValueTotal);
        ImagePanel.add(ValueDate);
        ImagePanel.add(LabelGunImage);


        NavPanel.add(HomeButton);
        NavPanel.add(GraphButton);
        NavPanel.add(DashButton);
        NavPanel.add(LogoutButton);

        //Adding Panels to JFrame
        RootPanel.add(NavPanel);
        RootPanel.add(DashPanel);
        RootPanel.add(ImagePanel);
        RootPanel.add(HomePanel);
        RootPanel.add(GraphPanel);

        MainWindow.getContentPane().add(RootPanel);

        //Window Essential
        MainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainWindow.setBackground(bgColor);
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
