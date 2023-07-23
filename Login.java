import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


class Login extends JFrame   
{

    Container c;
    JPanel pannavbar, pan;
    JLabel labun,labpw,labHead,img,labsignin,labsignup,labsignup1,img1;
    JTextField txtpw,txtun;
    JButton btnLogin,btnsignup;
    Color blueC;

    Login()
    {
        c= getContentPane();
        c.setLayout(null);

        blueC = new Color(51,153,255);

        Font f = new Font("TIMES NEW ROMAN",Font.BOLD,20);
        Font f1 = new Font("Supar Foods",Font.BOLD,15);
        Font f2 = new Font("TIMES NEW ROMAN",Font.BOLD,60);
        Font f3 = new Font("TIMES NEW ROMAN",Font.BOLD,35);

        ImageIcon i3= new ImageIcon("Icon/login.png");
        img1 = new JLabel(i3);
        img1.setBounds(390,250,300,250);
        c.add(img1);

        labsignin = new JLabel("User Login");
        labsignin.setFont(f3);
        labsignin.setBounds(450,260,450,50);
        labsignin.setForeground(Color.BLACK);
        c.add(labsignin);

        labsignup = new JLabel("Not a member?");
        labsignup.setFont(f);
        labsignup.setBounds(770,270,450,30);
        labsignup.setForeground(Color.BLACK);
        c.add(labsignup);

        labsignup1 = new JLabel("Signup");
        labsignup1.setFont(f);
        labsignup1.setBounds(910,270,450,30);
        labsignup1.setForeground(Color.blue);
        c.add(labsignup1);

        labun = new JLabel("Username:");
        labun.setFont(f);
        labun.setBounds(390,450,400,30);
        labun.setForeground(Color.BLACK);
        c.add(labun);

        labpw = new JLabel("Password:");
        labpw.setFont(f);
        labpw.setBounds(390,560,400,30);
        labpw.setForeground(Color.BLACK);
        c.add(labpw);
       
        txtun = new JTextField();
        txtun.setFont(f);
        txtun.setBounds(390,480,300,40);
        c.add(txtun);
        
        txtpw = new JPasswordField();
        txtpw.setFont(f);
        txtpw.setBounds(390,590,300,40);
        c.add(txtpw);

        btnLogin= new JButton("LOGIN");
        btnLogin.setFont(f);
        btnLogin.setForeground(Color.BLACK);
        btnLogin.setBackground(blueC);
        btnLogin.setBounds(450,670,200,40);
        c.add(btnLogin);


         labsignup1.addMouseListener(new MouseAdapter() 
        {
            public void mouseClicked(MouseEvent e) 
            {
                try 
                {
                    if(e.getSource() == labsignup1);
                    {
                        Signup su=new Signup();
                        dispose();
                    }
                } catch (Exception e1) 
                {
                    JOptionPane.showMessageDialog(c, "Issue " + e1);
                }
            }
        });

        ActionListener a2 = (ae) ->{
            try
            {
                String username = txtun.getText();
                String password = txtpw.getText();
                if(username.length()==0)
                {
                    JOptionPane.showMessageDialog(c, "Username cannot be empty");
                    
                }
                 else if(password.trim().length()==0)
                {
                    JOptionPane.showMessageDialog(c, "Password cannot be empty");
                   
                }
                
                else
                {
                
             try
                {
                    DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                    String url="jdbc:mysql://localhost:3306/Task4";
                    Connection con = DriverManager.getConnection(url,"root","abc456");


                    String sql="select * from login where username=? and password=?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1,username);
                    pst.setString(2,password);
                    ResultSet rs =pst.executeQuery();
                    if(rs.next())
                    {
                        MainFrame mf = new MainFrame();
                        dispose();  
                    }
                    else
                    {
                    JOptionPane.showConfirmDialog(c,"invalid login");
                    txtun.setText("");
                    txtpw.setText("");
                    txtun.requestFocus();
                    
                    }
                    con.close();
                }
                catch(SQLException g)
                {
                  JOptionPane.showConfirmDialog(c,"Issue "+g);
                }
            }
            }
            catch(Exception g)
                {
                  JOptionPane.showConfirmDialog(c,"Issue "+g);
                }
        };
        btnLogin.addActionListener(a2);
    

        labHead = new JLabel("Student Management System");
        labHead.setFont(f2);
        labHead.setBounds(400,30,900,80);
        labHead.setForeground(Color.BLACK);
        c.add(labHead);

        ImageIcon i6= new ImageIcon("Icon/login.jpg");
        img = new JLabel(i6);
        img.setBounds(250,170,1000,650);
        c.add(img);

       
        pannavbar=new JPanel();
        pannavbar.setBounds(05,0,1920,150);
        pannavbar.setBackground(blueC);
       
        pan = new JPanel();
        pan.setBounds(250,170,1000,650);
        
        c.add(pan);
        c.add(pannavbar);
  
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

    