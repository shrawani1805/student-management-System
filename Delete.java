import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


class Delete extends JFrame   
{

    Container c;
    JPanel pannavbar, pan;
    JLabel labrno,labHead,img,lab,labback,labback1;
    JTextField txtrno;
    JButton btnsave;
    Color blueC;
    

    

    Delete()
    {
        c= getContentPane();
        c.setLayout(null);
       


        Font f = new Font("TIMES NEW ROMAN",Font.BOLD,20);
        Font f2 = new Font("TIMES NEW ROMAN",Font.BOLD,60);
        Font f3 = new Font("TIMES NEW ROMAN",Font.BOLD,35);

        blueC = new Color(51,153,255);

        lab = new JLabel("Delete Student Details");
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

        btnsave= new JButton("DELETE");
        btnsave.setFont(f);
        btnsave.setForeground(Color.BLACK);
        btnsave.setBackground(blueC);
        btnsave.setBounds(460,530,150,40);
        c.add(btnsave);


        labrno = new JLabel("Enter Rollno:");
        labrno.setFont(f);
        labrno.setBounds(390,390,400,30);
        labrno.setForeground(Color.BLACK);
        c.add(labrno);

       
       
        txtrno = new JTextField();
        txtrno.setFont(f);
        txtrno.setBounds(390,420,300,40);
        c.add(txtrno);
        
        


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
                   
             else 
            {
                try 
                {
                    int result = JOptionPane.showConfirmDialog(c,"Are you sure you want to delete this Rollno?", "Swing Tester",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
                    if(result == JOptionPane.NO_OPTION)
                    {
                        JOptionPane.showMessageDialog(c,"No Rollno Deleted");

                    }
                    else if (result == JOptionPane.YES_OPTION)
                    {
                        try
                        {
                            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Task4", "root", "abc456");
                            String sql = "delete from student where Rollno = ?";
                            PreparedStatement pst = con.prepareStatement(sql);
                            pst.setInt(1, rno);
                            long rc = pst.executeUpdate();
                            JOptionPane.showMessageDialog(c, rc + " Rollno deleted");
                            
                            con.close();
                            txtrno.setText("");
                            txtrno.requestFocus();
                        }
                        catch(SQLException e)
                        {
                            JOptionPane.showMessageDialog(c, "Issue " + e);
                            txtrno.setText("");
                            txtrno.requestFocus();
                
                        }
                        
                    }
                    
                }
               catch(NumberFormatException e)
                {
                    JOptionPane.showMessageDialog(c, "Rollno/Name/Marks cannot be empty or Invalid Data");
                    txtrno.setText("");
                     txtrno.requestFocus();
                
                }
            }
        }
         catch(Exception e)
                {
                    JOptionPane.showMessageDialog(c, "Rollno/Name/Marks cannot be empty or Invalid Data");
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
    

    