import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemSource;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatDarkLaf;
import com.mysql.cj.x.protobuf.MysqlxCursor.Fetch;


import java.sql.*;

import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.jfree.ui.tabbedui.RootPanel;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatDarkLaf;


public class TryVideo {

    private JFrame MainWindow;
    
    private JPanel NavPanel, DashPanel, ImagePanel, HomePanel, GraphPanel, RootPanel;

    JPanel VideoPanel;
    Canvas canvas;

    private JButton DashButton, HomeButton, GraphButton, LogoutButton, GetSalesButton, GetItemsButton, FetchDataButton;

    //JComboBox GunType, SkinFamily, SortBy;

    private JTable DashTable;

    private JScrollPane TableScrollPane;

    private JLabel LabelID, LabelType, LabelFamily, LabelPoints,LabelUSD, LabelNumbers, LabelTotal, LabelDate, LabelGunImage;
    private JLabel ValueID, ValueType, ValueFamily, ValuePoints, ValueUSD, ValueNumbers, ValueTotal, ValueDate;
    private JLabel LabelAverage, LabelPopularSkin, LabelPopularFamily, LabelHighestSkin, LabelHighestFamily;
    private JLabel GraphPanelTitle, ValueAverage, ValuePopularSkin, ValuePopularFamily, ValueHighestSkin, ValueHighestFamily;

    EmbeddedMediaPlayerComponent mediaPlayerComponent;


    TryVideo(){

        //Instantiations
        MainWindow = new JFrame();

        NavPanel = new JPanel();
        DashPanel = new JPanel();
        ImagePanel = new JPanel();
        HomePanel = new JPanel();
        GraphPanel = new JPanel();
        RootPanel = new JPanel();
        VideoPanel = new JPanel();

        canvas = new Canvas();

        DashButton = new JButton();
        HomeButton = new JButton();
        GraphButton = new JButton();
        LogoutButton = new JButton();

        DashTable = new JTable();

        LabelGunImage = new JLabel();
        LabelID = new JLabel();
        LabelType = new JLabel();
        LabelFamily = new JLabel();
        LabelPoints = new JLabel();
        LabelUSD = new JLabel();
        LabelNumbers = new JLabel();
        LabelTotal = new JLabel();
        LabelDate = new JLabel();

        LabelAverage = new JLabel();
        LabelPopularSkin = new JLabel();
        LabelPopularFamily = new JLabel();
        LabelHighestSkin = new JLabel();
        LabelHighestFamily = new JLabel();
        GraphPanelTitle = new JLabel();

        ValueAverage = new JLabel();
        ValuePopularSkin = new JLabel();
        ValuePopularFamily = new JLabel();
        ValueHighestSkin = new JLabel();
        ValueHighestFamily = new JLabel();

        ValueID = new JLabel();
        ValueType = new JLabel();
        ValueFamily = new JLabel();
        ValuePoints = new JLabel();
        ValueUSD = new JLabel();
        ValueNumbers = new JLabel();
        ValueTotal = new JLabel();
        ValueDate = new JLabel();

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
        //GraphPanel.setBorder(BorderFactory.createLineBorder(Color.green));
        GraphPanel.setBounds(widthNav,0,widthDash+widthImage, 720);
        GraphPanel.setLayout(null);
        GraphPanel.setBackground(bgColor);

        //Navigation Panel
        //NavPanel.setBorder(BorderFactory.createLineBorder(Color.red));
        NavPanel.setBackground(bgColor3);
        NavPanel.setBounds(-10,0,widthNav, 720);
        NavPanel.setLayout(null);
        NavPanel.putClientProperty(FlatClientProperties.STYLE, "arc:30");

        
        HomePanel.setVisible(true);
        VideoPanel.setVisible(true);
        HomePanel.setEnabled(true);

        ImagePanel.setVisible(false);
        ImagePanel.setEnabled(false);

        DashPanel.setVisible(false);
        DashPanel.setEnabled(false);

        GraphPanel.setVisible(false);
        GraphPanel.setEnabled(false);

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
        TableScrollPane.setBounds(5, 30, widthDash-20, 690);
        
        
        TableScrollPane.setViewportView(DashTable);

        DashPanel.add(TableScrollPane);

        
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

        //Features and GUI for Graph Panel

        int GraphButtonY = 600, GraphPanelWidth = 960;

        GraphPanelTitle.setText("Monthly Report");
        GraphPanelTitle.setFont(titlefont);
        GraphPanelTitle.setBounds((GraphPanelWidth/2)-250, buttony-45*8, GraphPanelWidth, 100);
        GraphPanelTitle.setForeground(TitleColor);

        LabelAverage.setText("Average Sales Per Day:");
        LabelAverage.setFont(subtitlefont);
        LabelAverage.setBounds(10, buttony-45*5, (widthImage/2)+200, 50);
        LabelAverage.setForeground(TitleColor);

        ValueAverage.setFont(headfont);
        ValueAverage.setBounds((widthImage)+30, (buttony-50*5)+5*4, (widthImage/2), 50);
        ValueAverage.setForeground(TextColor);

        LabelPopularSkin.setText("Most Popular Skin:");
        LabelPopularSkin.setFont(subtitlefont);
        LabelPopularSkin.setBounds(10, buttony-45*3, (widthImage/2)+200, 50);
        LabelPopularSkin.setForeground(TitleColor);

        ValuePopularSkin.setFont(headfont);
        ValuePopularSkin.setBounds((widthImage)+30, (buttony-50*3)+5*2, (widthImage)+200, 50);
        ValuePopularSkin.setForeground(TextColor);

        LabelPopularFamily.setText("Most Popular Family:");
        LabelPopularFamily.setFont(subtitlefont);
        LabelPopularFamily.setBounds(10, buttony-45*1, (widthImage/2)+200, 50);
        LabelPopularFamily.setForeground(TitleColor);

        ValuePopularFamily.setFont(headfont);
        ValuePopularFamily.setBounds((widthImage)+30, (buttony-50*1)+5*0, (widthImage)+200, 50);
        ValuePopularFamily.setForeground(TextColor);

        LabelHighestSkin.setText("Highest Profit Skin:");
        LabelHighestSkin.setFont(subtitlefont);
        LabelHighestSkin.setBounds(10, buttony+45*1, (widthImage/2)+200, 50);
        LabelHighestSkin.setForeground(TitleColor);

        ValueHighestSkin.setFont(headfont);
        ValueHighestSkin.setBounds((widthImage)+30, (buttony+50*1)-5*2, (widthImage)+200, 50);
        ValueHighestSkin.setForeground(TextColor);

        LabelHighestFamily.setText("Highest Profit Family:");
        LabelHighestFamily.setFont(subtitlefont);
        LabelHighestFamily.setBounds(10, buttony+45*3, (widthImage/2)+200, 50);
        LabelHighestFamily.setForeground(TitleColor);

        ValueHighestFamily.setFont(headfont);
        ValueHighestFamily.setBounds((widthImage)+30, (buttony+50*3)-5*4, (widthImage)+200, 50);
        ValueHighestFamily.setForeground(TextColor);

        GetSalesButton = new JButton();

        GetSalesButton.setText("Fetch Monthly Report");
        GetSalesButton.setBackground(TitleColor);
        GetSalesButton.setFont(buttonFont);
        GetSalesButton.setForeground(Color.WHITE);
        GetSalesButton.setBounds((GraphPanelWidth/2)-250, GraphButtonY, 150, 50);
        GetSalesButton.putClientProperty(FlatClientProperties.STYLE, "arc:30");
        GetSalesButton.setFocusable(false);

        GetSalesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ResultSet rs = null;
                Color BarColor = new Color(255,70,85);
                Color bgChartColor = new Color(80, 80, 80);
                try {
                    Connection conn = DriverManager.getConnection(url, user, password);
                    Statement stmt = conn.createStatement();
                    String sql = "SELECT Week, Weekly_Profit FROM valorantgraphsheet";
                    rs = stmt.executeQuery(sql);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                {
                    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

                    try {
                        while (rs.next()) {
                            String week = rs.getString("Week");
                            int units = rs.getInt("Weekly_Profit");
                            dataset.addValue(units, "Weekly_Profit", week);
                        }
                    } catch (SQLException e2) {
                        e2.printStackTrace();
                    }

                    JFreeChart barChart = ChartFactory.createBarChart(
                            "Profits in September",
                            "Week",
                            "USD $",
                            dataset,
                            PlotOrientation.VERTICAL,
                            true, true, false);

                    barChart.setBackgroundPaint(bgColor);
                    
                    CategoryPlot plot = barChart.getCategoryPlot();
                    
                    CategoryAxis domainAxis = plot.getDomainAxis();
                    domainAxis.setLabelPaint(Color.WHITE);
                    domainAxis.setTickLabelPaint(Color.WHITE);

                    
                    ValueAxis rangeAxis = plot.getRangeAxis();
                    rangeAxis.setLabelPaint(Color.WHITE);
                    rangeAxis.setTickLabelPaint(Color.WHITE);
                    plot.setBackgroundPaint(bgChartColor);
                    plot.setOutlinePaint(Color.WHITE);
                    barChart.getTitle().setPaint(BarColor);
                    
                    BarRenderer renderer = (BarRenderer) plot.getRenderer();
                    renderer.setSeriesPaint(0, BarColor);

                    renderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
                    
                    
                    ChartFrame frame = new ChartFrame("September Chart", barChart);
                    frame.setVisible(true);
                    frame.setSize(450, 500);
                    frame.setBackground(bgColor);

                    int w = frame.getSize().width;
                    int h = frame.getSize().height;
                    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                    int x = (dim.width-w)/2;
                    int y = (dim.height-h)/2;
                    frame.setLocation(x,y);
                    frame.setVisible(true);
                            }
                        }
            });

        GetItemsButton = new JButton();

        GetItemsButton.setText("Show Items Sold Distribution");
        GetItemsButton.setBackground(TitleColor);
        GetItemsButton.setFont(buttonFont);
        GetItemsButton.setForeground(Color.WHITE);
        GetItemsButton.setBounds((GraphPanelWidth/2)+100, GraphButtonY, 200, 50);
        GetItemsButton.putClientProperty(FlatClientProperties.STYLE, "arc:30");
        GetItemsButton.setFocusable(false);

        GetItemsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Color BarColor = new Color(255,70,85);
                Color bgChartColor = new Color(80, 80, 80);
                String chartTitle = "Best Skin Line Sold";
                DefaultPieDataset dataset = new DefaultPieDataset();
                try {
                    Connection conn = DriverManager.getConnection(url, user, password);
                    Statement st = conn.createStatement();
                    ResultSet rs = st.executeQuery("SELECT skin_family, SUM(number_sold) as total FROM valorantspreadsheet GROUP BY skin_family");

                    while (rs.next()) {
                        dataset.setValue(rs.getString("skin_family"), rs.getDouble("total"));
                    }

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                {
                    JFreeChart piechart = ChartFactory.createPieChart3D(chartTitle, dataset, true, true, false);
                    
                    PiePlot3D plot = (PiePlot3D) piechart.getPlot();
                    plot.setStartAngle(290);
                    plot.setDirection(Rotation.CLOCKWISE);
                    plot.setForegroundAlpha(1.0f);
                    plot.setBackgroundPaint(bgChartColor);
                    plot.setLabelBackgroundPaint(Color.WHITE);
                    plot.setOutlinePaint(Color.WHITE);

                    
                    piechart.setBackgroundPaint(bgColor);
                    piechart.getTitle().setPaint(BarColor);
                    
                    ChartFrame frame = new ChartFrame(chartTitle, piechart);
                    frame.setVisible(true);
                    frame.setSize(1080, 720);
                    frame.setBackground(bgColor);

                    int w = frame.getSize().width;
                    int h = frame.getSize().height;
                    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                    int x = (dim.width-w)/2;
                    int y = (dim.height-h)/2;
                    frame.setLocation(x,y);
                    frame.setVisible(true);
                            }
                        }
            });
            
        FetchDataButton = new JButton();

        FetchDataButton.setText("Fetch Data");
        FetchDataButton.setBackground(TitleColor);
        FetchDataButton.setFont(buttonFont);
        FetchDataButton.setForeground(Color.WHITE);
        FetchDataButton.setBounds((GraphPanelWidth/2)-50, GraphButtonY, 100, 50);
        FetchDataButton.putClientProperty(FlatClientProperties.STYLE, "arc:30");
        FetchDataButton.setFocusable(false);

        FetchDataButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Color BarColor = new Color(255,70,85);
                Color bgChartColor = new Color(80, 80, 80);
                String chartTitle = "Best Skin Line Sold";
                DefaultPieDataset dataset = new DefaultPieDataset();
                try {
                    Connection conn = DriverManager.getConnection(url, user, password);
                    String outAve = null;
                    String outPopSkin = null;
                    String outPopFam = null;
                    String outHighSkin = null;
                    String outHighFam = null;

                    Statement st1 = conn.createStatement();
                    Statement st2 = conn.createStatement();
                    Statement st3 = conn.createStatement();
                    Statement st4 = conn.createStatement();
                    Statement st5 = conn.createStatement();

                    ResultSet rs1 = st1.executeQuery("SELECT SUM(No_Units_Sold)/30 as total FROM valorantgraphsheet");
                    ResultSet rs2 = st2.executeQuery("SELECT skin_family, SUM(number_sold) as total FROM valorantspreadsheet GROUP BY skin_family ORDER BY total DESC LIMIT 1;");
                    ResultSet rs3 = st3.executeQuery("SELECT skin_family, SUM(total_sold_USD) as total FROM valorantspreadsheet GROUP BY skin_family ORDER BY total DESC LIMIT 1;");                  
                    ResultSet rs4 = st4.executeQuery("SELECT skin_family, gun_type, SUM(number_sold) as total FROM valorantspreadsheet GROUP BY skin_family, gun_type ORDER BY total DESC LIMIT 1;");
                    ResultSet rs5 = st5.executeQuery("SELECT skin_family, gun_type, SUM(total_sold_USD) as total FROM valorantspreadsheet GROUP BY skin_family, gun_type ORDER BY total DESC LIMIT 1;");
                    
                    while(rs1.next()){
                    outAve = String.valueOf((int)Math.round(rs1.getDouble("total")));
                    }
                    rs1.close();
                    st1.close();
                    while(rs2.next()){
                    outPopFam = rs2.getString("skin_family") + ": " + String.valueOf(rs2.getInt("total")) + " copies sold";
                    }
                    rs2.close();
                    st2.close();
                    while(rs3.next()){
                    outHighFam = rs3.getString("skin_family") + ": $" + String.valueOf((int)Math.round(rs3.getDouble("total")));
                    }
                    rs3.close();
                    st3.close();
                    while(rs4.next()){
                    outPopSkin = rs4.getString("gun_type") + " " + rs4.getString("skin_family") + ": " + String.valueOf(rs4.getInt("total") + " copies sold");
                    }
                    rs4.close();
                    st4.close();
                    while(rs5.next()){
                    outHighSkin = rs5.getString("gun_type") + " " + rs5.getString("skin_family") + ": $" + String.valueOf((int)Math.round(rs5.getDouble("total")));
                    }
                    rs5.close();
                    st5.close();

                    
                    ValueAverage.setText(outAve);
                    ValuePopularSkin.setText(outPopSkin);
                    ValuePopularFamily.setText(outPopFam);
                    ValueHighestSkin.setText(outHighSkin);
                    ValueHighestFamily.setText(outHighFam);

                } catch (SQLException e1) {
                    e1.printStackTrace();
                    }
                }
                    
        
            });
        //Defining functions for the JButtons
        HomeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                    HomePanel.setVisible(true);
                    HomePanel.setEnabled(true);

                    VideoPanel.setVisible(true);
                    VideoPanel.setEnabled(true);

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

                    VideoPanel.setVisible(false);
                    VideoPanel.setEnabled(false);

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

                    VideoPanel.setVisible(false);
                    VideoPanel.setEnabled(false);

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



            VideoPanel = new JPanel(new BorderLayout());
            canvas = new Canvas();
            VideoPanel.add(canvas);
            VideoPanel.setPreferredSize(new Dimension(1080, 720));

            

            mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
            VideoPanel.add(mediaPlayerComponent);


                    //Adding Components to Panels
        
        GraphPanel.add(GraphPanelTitle);
        GraphPanel.add(LabelAverage);
        GraphPanel.add(LabelPopularSkin);
        GraphPanel.add(LabelPopularFamily);
        GraphPanel.add(LabelHighestSkin);
        GraphPanel.add(LabelHighestFamily);

        GraphPanel.add(ValueAverage);
        GraphPanel.add(ValuePopularSkin);
        GraphPanel.add(ValuePopularFamily);
        GraphPanel.add(ValueHighestSkin);
        GraphPanel.add(ValueHighestFamily);

        GraphPanel.add(GetItemsButton);
        GraphPanel.add(GetSalesButton);
        GraphPanel.add(FetchDataButton);

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


            RootPanel.add(NavPanel);
            RootPanel.add(DashPanel);
            RootPanel.add(ImagePanel);
            //RootPanel.add(HomePanel);
            RootPanel.add(GraphPanel);
            RootPanel.add(VideoPanel);
            
            
            //VideoPanel.add(mediaPlayerComponent);

            MainWindow = new JFrame("My Video Player");
            MainWindow.setLayout(null);
            MainWindow.setSize(1080, 720);
            MainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            MainWindow.setContentPane(RootPanel);

                //Window Essential
            //MainWindow.setBackground(bgColor);
            MainWindow.setUndecorated(true);
            MainWindow.setResizable(false);
            MainWindow.getContentPane().setLayout(null);
            MainWindow.setVisible(true);
                

            VideoPanel.setBounds(0, 0, VideoPanel.getPreferredSize().width, VideoPanel.getPreferredSize().height);

            MainWindow.setVisible(true);

            mediaPlayerComponent.mediaPlayer().video().setAspectRatio("16:9");
            mediaPlayerComponent.mediaPlayer().video().setScale(1.0f);
            mediaPlayerComponent.mediaPlayer().media().play("src/Video/HomeScreen.mp4");
            mediaPlayerComponent.mediaPlayer().controls().setRepeat(true);

            //This is the function that is responsible for making the window appear centered on startup
            int w = MainWindow.getSize().width;
            int h = MainWindow.getSize().height;
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (dim.width-w)/2;
            int y = (dim.height-h)/2;
            MainWindow.setLocation(x,y);
        

    }

             public static void main(String[] args) {
        FlatDarkLaf.setup();
        new TryVideo();
    }

}
