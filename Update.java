import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


class Update extends JFrame   
{

    Container c;
    JPanel pannavbar, pan;
    JLabel labrno,labname,labHead,img,lab,labback,labback1,labmarks;
    JTextField txtrno,txtname,txtmarks;
    JButton btnsave,btabout;
    Color pinkC, blueC;
    

    

    Update()
    {
        c= getContentPane();
        c.setLayout(null);
       
        Font f = new Font("TIMES NEW ROMAN",Font.BOLD,20);
        Font f2 = new Font("TIMES NEW ROMAN",Font.BOLD,60);
        Font f3 = new Font("TIMES NEW ROMAN",Font.BOLD,35);

        blueC = new Color(51,153,255);

        lab = new JLabel("Update Student Details");
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

        btnsave= new JButton("UPDATE");
        btnsave.setFont(f);
        btnsave.setForeground(Color.black);
        btnsave.setBackground(blueC);
        btnsave.setBounds(460,670,150,40);
        c.add(btnsave);

        labrno = new JLabel("Enter Rollno:");
        labrno.setFont(f);
        labrno.setBounds(390,340,400,30);
        labrno.setForeground(Color.BLACK);
        c.add(labrno);

        labname = new JLabel("Enter Name:");
        labname.setFont(f);
        labname.setBounds(390,450,400,30);
        labname.setForeground(Color.BLACK);
        c.add(labname);

        labmarks = new JLabel("Enter Marks:");
        labmarks.setFont(f);
        labmarks.setBounds(390,560,400,30);
        labmarks.setForeground(Color.BLACK);
        c.add(labmarks);
       
       
        txtrno = new JTextField();
        txtrno.setFont(f);
        txtrno.setBounds(390,370,300,40);
        c.add(txtrno);
        
        txtname = new JTextField();
        txtname.setFont(f);
        txtname.setBounds(390,480,300,40);
        c.add(txtname);

        txtmarks = new JTextField();
        txtmarks.setFont(f);
        txtmarks.setBounds(390,590,300,40);
        c.add(txtmarks);

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

        ActionListener a1 = (ie) ->
        {
            try
            {
                int  rno = Integer.parseInt(txtrno.getText());
                String name = txtname.getText();
                int marks=Integer.parseInt (txtmarks.getText());

                if(rno == 0)
             {
                JOptionPane.showMessageDialog(c," Rollno cannot be 0");
                 txtrno.setText("");
                txtrno.requestFocus();
             }
              else if(rno<0)
             {
              JOptionPane.showMessageDialog(c, "Rollno cannot be negative");
              txtrno.setText("");
              txtrno.requestFocus();
             }
                
                else if(name.trim().length() < 2)
                {
                    JOptionPane.showMessageDialog(c, "Name should atleast contain 2 alphabets");
                    txtname.setText("");
                    txtname.requestFocus();
                }
                else if(! name.matches("^[A-Za-z]+"))
                {
                    JOptionPane.showMessageDialog(c, "Alphabets Only!");
                    txtname.setText("");
                    txtname.requestFocus();
                }
                
                
                else if(marks < 0)
                {
                   JOptionPane.showMessageDialog(c, "Marks cannot be negative");
                   txtmarks.setText("");
                   txtmarks.requestFocus();
                }
                else if(marks>100)
                {
                   JOptionPane.showMessageDialog(c, "<html>marks should be in range of 0 to 100</html>");
                   txtmarks.setText("");
                   txtmarks.requestFocus();
                }
                
             else 
            {
                try 
                {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Task4", "root", "abc456");
                String query = "update student set name=?, marks=? where Rollno=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, name);
                pst.setInt(2,marks);
                pst.setInt(3, rno);
                long rc = pst.executeUpdate();
                JOptionPane.showMessageDialog(c, rc +" Record updated");
                txtmarks.setText("");
                txtname.setText("");
                txtrno.setText("");
                txtrno.requestFocus();
                }
                catch(SQLException e)
                {
                    JOptionPane.showMessageDialog(c, " Rollno already Exits");
                    txtmarks.setText("");
                    txtname.setText("");
                    txtrno.setText("");
                    txtrno.requestFocus();
                
                }
            
            }
        }
            catch(NumberFormatException e)
                {
                    JOptionPane.showMessageDialog(c, "Rollno/Name/Marks cannot be empty or Invalid Data");
                    txtmarks.setText("");
                    txtname.setText("");
                    txtrno.setText("");
                     txtrno.requestFocus();
                
                }
            
        };
        btnsave.addActionListener(a1);

       
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

    