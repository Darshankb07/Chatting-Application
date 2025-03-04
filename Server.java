
package Chattingapplication;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;


public class Server  implements ActionListener{
     JTextField text;
     JButton send;
     JPanel p2;
     static Box vertical = Box.createVerticalBox();
     static JFrame f = new JFrame();
     static DataOutputStream dout;
     Server(){
         
     f.setLayout(null); 
     
     JPanel p1 = new JPanel();
     p1.setBackground(new Color(7,94,84));
     p1.setBounds(0,0,450,70);
     p1.setLayout(null);
     f.add(p1);
     
     ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
     Image i2 = i1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
     ImageIcon i3 = new ImageIcon(i2);
     JLabel back = new JLabel(i3);
     back.setBounds(5,20,25,25);
     p1.add(back);
     
     back.addMouseListener(new MouseAdapter(){
       public  void mouseClicked(MouseEvent me){
           System.exit(0);
           
       } 
     });
     
     ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/1.png"));
     Image i5 = i4.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
     ImageIcon i6 = new ImageIcon(i5);
     JLabel profile = new JLabel(i6);
     profile.setBounds(40,10,50,50);
     p1.add(profile);
     
     
     ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
     Image i8 = i7.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
     ImageIcon i9 = new ImageIcon(i8);
     JLabel vidio = new JLabel(i9);
     vidio.setBounds(300,20,30,30);
     p1.add(vidio);
     
     ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
     Image i11 = i10.getImage().getScaledInstance(35,30,Image.SCALE_DEFAULT);
     ImageIcon i12 = new ImageIcon(i11);
     JLabel telephone = new JLabel(i12);
     telephone.setBounds(360,20,35,30);
     p1.add(telephone);
     
     ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
     Image i14 = i13.getImage().getScaledInstance(10,25,Image.SCALE_DEFAULT);
     ImageIcon i15 = new ImageIcon(i14);
     JLabel menu = new JLabel(i15);
     menu.setBounds(420,20,10,25);
     p1.add(menu);
      
     JLabel name = new JLabel("Darshan1");
     name.setBounds(110,15,100,18);
     name.setForeground(Color.white);
     name.setFont(new Font("SAN_SARIF", Font.BOLD, 18));
     p1.add(name);
     
     JLabel status = new JLabel("Active now");
     status.setBounds(110,35,100,18);
     status.setForeground(Color.white);
     status.setFont(new Font("SAN_SARIF", Font.BOLD, 14));
     p1.add(status);
         
     p2 = new JPanel();
     p2.setBounds(5,75,440,570);
     f.add(p2);
     
     text = new JTextField();
     text.setBounds(5,655,310,40);
     text.setFont(new Font("SAN_SARIF",Font.PLAIN,16));
     f.add(text);
     
     send = new JButton("Send");
     send.setBounds(320,655,123,40);
     send.setBackground(new Color(7,94,84));
     send.setForeground(Color.white);
     f.add(send);
     send.addActionListener(this);
         
         
     f.setSize(450,700); 
     f.setLocation(200,50);
     f.setUndecorated(true);
     f.getContentPane().setBackground(Color.white);
     
     
     
     
      f.setVisible(true);
     
     }
   
    public void actionPerformed(ActionEvent e) {
       try{
      String out = text.getText(); 
     
      JPanel p3 = formatLabel(out);
     
      
      p2.setLayout(new BorderLayout());
      JPanel right=new JPanel(new BorderLayout());
      right.add(p3,BorderLayout.LINE_END);
      vertical.add(right);
      vertical.add(Box.createVerticalStrut(15));
      p2.add(vertical,BorderLayout.PAGE_START);
      
      dout.writeUTF(out);
      text.setText("");
      
      f.repaint();
      f.invalidate();
      f.validate();
       } catch(Exception e1){
           e1.printStackTrace();
       }
        
    }
    public static JPanel formatLabel(String out){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        
        JLabel output = new JLabel("<html><p style=\"width:150px\">" + out + "</p></html>");
        output.setFont(new Font("Tahoma",Font.PLAIN,16));
        output.setBackground(new Color(37,211,102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,50));
        
        panel.add(output);
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);
        
        return panel;
    }
        
    public static void main(String args[]){
             new Server();
        try{
            ServerSocket skt = new ServerSocket(6001);
            while(true){
                Socket s= skt.accept();
                DataInputStream din = new DataInputStream( s.getInputStream());
                 dout = new DataOutputStream(s.getOutputStream());
                
                while(true){
                    String msg =  din.readUTF();
                    JPanel panel = formatLabel(msg);
                    
                    JPanel left = new JPanel(new BorderLayout());
                    left.add(panel,BorderLayout.LINE_START);
                    vertical.add(left);
                    f.validate();
                }
            }
        } catch(Exception e){
           e.printStackTrace();
        }
    }  
}

