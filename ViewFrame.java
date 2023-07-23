import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


class ViewFrame extends JFrame   
{

    Container c;
    JPanel pannavbar, pan;
    JLabel labHead,img,lab,labback,labback1;
    JButton btnsave;
    Color blueC;
    DefaultTableModel model;
    JTable table;

    
    ViewFrame()
    {
        c= getContentPane();
        c.setLayout(null);
       


        Font f = new Font("TIMES NEW ROMAN",Font.BOLD,20);
        Font f2 = new Font("TIMES NEW ROMAN",Font.BOLD,60);
        Font f3 = new Font("TIMES NEW ROMAN",Font.BOLD,35);

        blueC = new Color(51,153,255);

         model = new DefaultTableModel();
         table = new JTable(model);
         JScrollPane scrollPane = new JScrollPane(table);
         scrollPane.setBounds(340,330,400, 400);
         c.add(scrollPane, BorderLayout.CENTER);
          try
            {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
    
            String url = "jdbc:mysql://localhost:3306/Task4";
            Connection con = DriverManager.getConnection(url, "root", "abc456");
    
            String sql="select * from student";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
             ResultSetMetaData metaData = rs.getMetaData();
    
             
                int columnCount = metaData.getColumnCount();
                String[] columnNames = new String[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    columnNames[i - 1] = metaData.getColumnName(i);
                }
               model .setColumnIdentifiers(columnNames);

                while (rs.next()) {
                    Object[] rowData = new Object[columnCount];
                    for (int g = 1; g <= columnCount; g++)
                    {
                        rowData[g - 1] = rs.getObject(g);
                    }
                    model.addRow(rowData);
                }
    
            rs.close();
            con.close();
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(c, "issue"+e);
            
            }
    

        lab = new JLabel("View Student Details");
        lab.setFont(f3);
        lab.setBounds(390,260,450,50);
        lab.setForeground(Color.BLACK);
        c.add(lab);

        labback = new JLabel("Want to go Back?");
        labback.setFont(f);
        labback.setBounds(770,270,450,30);
        labback.setForeground(Color.BLACK);
        c.add(labback);

        labback1 = new JLabel("Back");
        labback1.setFont(f);
        labback1.setBounds(930,270,450,30);
        labback1.setForeground(Color.blue);
        c.add(labback1);


        
        labHead = new JLabel("Student Management System");
        labHead.setFont(f2);
        labHead.setBounds(400,30,900,80);
        labHead.setForeground(Color.BLACK);
        c.add(labHead);

         ImageIcon i3= new ImageIcon("Icon/login.jpg");
         img = new JLabel(i3);
         img.setBounds(250,170,1000,650);
         c.add(img);

        pannavbar = new JPanel();
        pannavbar.setBounds(05,0,1920,150);
        pannavbar.setBackground(blueC);
       
         pan = new JPanel();
         pan.setBounds(250,170,1000,650);
        
        c.add(pan);
        c.add(pannavbar);

        labback1.addMouseListener(new MouseAdapter() 
        {
            public void mouseClicked(MouseEvent e) 
            {
                try 
                {
                    if(e.getSource() == labback1);
                    {
                        MainFrame su=new MainFrame();
                        dispose();
                    }
                } catch (Exception e1) 
                {
                    JOptionPane.showMessageDialog(c, "Issue " + e1);
                }
            }
        });
   
        setIconImage(new ImageIcon("icon/th.jpg").getImage());
        setSize(1600,950);
        setLocationRelativeTo(null);
        setTitle("Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        addWindowListener(new WindowAdapter() 
        {
            @Override
            public void windowClosing(WindowEvent e)
        {
                    int option = JOptionPane.showConfirmDialog(c,"Are you sure you want to close the app?", "Close Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
                    if(option==JOptionPane.YES_OPTION)
                    System.exit(0);            
        }
           
        });
       
    }
}


    