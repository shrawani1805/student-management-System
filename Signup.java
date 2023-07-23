import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class Signup extends JFrame   
{

    Container c;
    JPanel pannavbar, pan;
    JLabel labun,labpw,labHead,img,labsignin,labsignup,labsignup1,labsignup2,labpw1,labsuggesion,labresult;
    JTextField txtpw,txtun,txtpw1;
    JButton btnsignup;
    Color  blueC;
    
    Signup()
    {
        c= getContentPane();
        c.setLayout(null);

        blueC = new Color(51,153,255);

       
        Font f = new Font("TIMES NEW ROMAN",Font.BOLD,20);
        Font f2 = new Font("TIMES NEW ROMAN",Font.BOLD,60);
        Font f3 = new Font("TIMES NEW ROMAN",Font.BOLD,35);

        
       
        labsignin = new JLabel("Create Account");
        labsignin.setFont(f3);
        labsignin.setBounds(390,260,450,50);
        labsignin.setForeground(Color.BLACK);
        c.add(labsignin);

        labsuggesion = new JLabel();
        labsuggesion.setFont(f);
        labsuggesion.setBounds(390,700,450,50);
        labsuggesion.setForeground(Color.red);
        c.add(labsuggesion);

        labresult = new JLabel();
        labresult.setFont(f);
        labresult.setBounds(430,740,450,50);
        labresult.setForeground(Color.red);
        c.add(labresult);


       


        labsignup = new JLabel("Already a member?");
        labsignup.setFont(f);
        labsignup.setBounds(770,270,450,30);
        labsignup.setForeground(Color.BLACK);
        c.add(labsignup);

        labsignup1 = new JLabel("Login");
        labsignup1.setFont(f);
        labsignup1.setBounds(940,270,450,30);
        labsignup1.setForeground(Color.blue);
        c.add(labsignup1);

        btnsignup= new JButton("SIGNUP");
        btnsignup.setFont(f);
        btnsignup.setForeground(Color.BLACK);
        btnsignup.setBackground(blueC);
        btnsignup.setBounds(450,670,150,40);
        c.add(btnsignup);


        labun = new JLabel("Username:");
        labun.setFont(f);
        labun.setBounds(390,340,400,30);
        labun.setForeground(Color.BLACK);
        c.add(labun);

        labpw = new JLabel("Password:");
        labpw.setFont(f);
        labpw.setBounds(390,450,400,30);
        labpw.setForeground(Color.BLACK);
        c.add(labpw);

        labpw1 = new JLabel("Confirm Password:");
        labpw1.setFont(f);
        labpw1.setBounds(390,560,400,30);
        labpw1.setForeground(Color.BLACK);
        c.add(labpw1);
       
       
        txtun = new JTextField();
        txtun.setFont(f);
        txtun.setBounds(390,370,300,40);
        c.add(txtun);
        
        txtpw = new JTextField();
        txtpw.setFont(f);
        txtpw.setBounds(390,480,300,40);
        c.add(txtpw);

        txtpw1 = new JTextField();
        txtpw1.setFont(f);
        txtpw1.setBounds(390,590,300,40);
        c.add(txtpw1);


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

        labsignup1.addMouseListener(new MouseAdapter() 
        {
            public void mouseClicked(MouseEvent e) 
            {
                try 
                {
                    if(e.getSource() == labsignup1);
                    {
                        Login l=new Login();
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
                String un = txtun.getText();
                String pw = txtpw.getText();
                String pw1 = txtpw1.getText();

                 if(! pw.equals(pw1))
                {
                    JOptionPane.showMessageDialog(c, "Password does not match");
                    return;
                }
                else if(un.trim().length()==0)
                {
                    JOptionPane.showMessageDialog(c, "Username cannot be empty");
                                    }
                 else if(pw.trim().length()==0)
                {
                    JOptionPane.showMessageDialog(c, "Password cannot be empty");
                   
                }
                 else if(pw1.trim().length()==0)
                {
                    JOptionPane.showMessageDialog(c, "Password  cannot be empty");
                    txtun.setText("");
                    txtun.requestFocus();
                }
               
               else  if(!pw.matches("^(?=.*[0-9])"
               + "(?=.*[a-z])(?=.*[A-Z])"
               + "(?=.*[@#$%^&+=])"
               + "(?=\\S+$).{8,20}$"))
               {
                JOptionPane.showMessageDialog(c, "Enter strong password");    
                    
                }
                else
                {

                try
                {
                    DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                    String url="jdbc:mysql://localhost:3306/Task4";
                    Connection con = DriverManager.getConnection(url,"root","abc456");


                    String sql="insert into login values(?,?)";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1,un);
                    pst.setString(2,pw);

                    Login l= new Login();

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(c, "Registered");
                    txtun.setText("");
                    txtpw.setText("");
                    txtpw1.setText("");
                        
                    con.close();
                }
                catch(SQLException e)
                {
                    JOptionPane.showMessageDialog(c, "Username already Exits");
                    
                }

            }
        }
            catch(Exception e)
             {
                JOptionPane.showMessageDialog(c, "Invalid Input");
                   
             } 
                
        };
        btnsignup.addActionListener(a2);
       
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

    