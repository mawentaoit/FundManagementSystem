package view;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import model.dao.UserDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class signUp extends JFrame {
 public  signUp() {
    setSize(350, 300);
    setTitle("用户注册");
    JPanel panel = new JPanel();
    //添加面板
     add(panel);
     /**
      * 调用用户定义的方法并添加组件到面版
      */
     placeComponents(panel);
     setLocationRelativeTo(null);
     setVisible(true);
 }

 private  void placeComponents(JPanel panel) {
    panel.setLayout(null);
    JLabel userLabel = new JLabel("用户名: ");
    userLabel.setBounds(10,20,80,25);
    panel.add(userLabel);
     /*
      * 创建文本域用于用户输入
      */
     JTextField userText = new JTextField(20);
     userText.setBounds(100,20,165,25);
     panel.add(userText);
     // 输入密码的文本域
     JLabel passwordLabel = new JLabel("密码:");
     passwordLabel.setBounds(10,50,80,25);
     panel.add(passwordLabel);
     /*
      *这个类似用于输入的文本域
      * 但是输入的信息会以点号代替，用于包含密码的安全性
      */
     JPasswordField passwordText = new JPasswordField(20);
     passwordText.setBounds(100,50,165,25);
     panel.add(passwordText);
     // 输入密码的文本域
     JLabel repasswordLabel = new JLabel("确认密码:");
     repasswordLabel.setBounds(10,80,80,25);
     panel.add(repasswordLabel);

     /*
      *这个类似用于输入的文本域
      * 但是输入的信息会以点号代替，用于包含密码的安全性
      */
     JPasswordField repasswordText = new JPasswordField(20);
     repasswordText.setBounds(100,80,165,25);
     panel.add(repasswordText);

     //创建注册按钮
     JButton signUpButton = new JButton("注册");
     signUpButton.setBounds(10, 110, 80, 25);
     panel.add(signUpButton);
     signUpButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
//             System.out.println("被点击了");
             String userName = userText.getText();
             String passWord = passwordText.getText();
             String repassWord = repasswordText.getText();
//             System.out.println(userName);
//             System.out.println(passWord);
//             System.out.println(repassWord);
             if(passWord.equals(repassWord)) {
                 UserDao ud = new UserDao();
                 try {
                     int flag = ud.addUser(userName, passWord);
                     System.out.println(flag);
                     if(flag == 2) {
                         JOptionPane.showMessageDialog(null,"注册成功","提示",JOptionPane.PLAIN_MESSAGE);
                     } else if(flag == 0) {
                         JOptionPane.showMessageDialog(null, "注册失败", "提示",JOptionPane.PLAIN_MESSAGE);
                     } else {
                         JOptionPane.showMessageDialog(null, "用户名已存在", "提示",JOptionPane.PLAIN_MESSAGE);
                     }
                 }catch (Exception el) {
                     JOptionPane.showMessageDialog(null, "两次输入密码不一致", "提示",JOptionPane.PLAIN_MESSAGE);
                 }
                 //创建登录按钮
                 JButton loginButton = new JButton("登录");
                 loginButton.setBounds(100, 110, 80, 25);
                 panel.add(loginButton);
                 loginButton.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                         setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                         addWindowListener(new WindowAdapter() {

                             public void windowClosing(WindowEvent e) {
                                 //调用这个方法之后，frame的相关资源全都会释放。
                                 dispose();
                             }
                         });
                         Login l = new Login();
                         dispose();
                     }
                 });
             }
         }
     });
 }
}
