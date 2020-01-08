package view;

import controller.Assets;
import model.dao.UserDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Login extends JFrame {
    int index;
//    new ImageIcon(getImage().getScaledInstance(getWidth(), getHeight()-25, Image.SCALE_DEFAULT));
    ImageIcon[] imgs = {
            new ImageIcon("src/resource/images/1.jpg"),
            new ImageIcon("src/resource/images/2.jpg"),
            new ImageIcon("src/resource/images/3.jpg"),
            new ImageIcon("src/resource/images/4.jpg"),
            new ImageIcon("src/resource/images/5.jpg"),
            new ImageIcon("src/resource/images/6.jpg"),
            new ImageIcon("src/resource/images/7.jpg"),
    };

    class MyJpanel extends JPanel{
        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(imgs[index%imgs.length].getImage(), 0,-10, this);
            index++;
        }
    }
    public Login() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setFrame();
        setVisible(true);
    }
    public void setFrame() {
        setSize(450, 500);
        setTitle("用户登陆");
        JPanel panel = new MyJpanel();
        //添加面板
        add(panel);
        Timer timer = new Timer(2000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.repaint();
            }
        });
        timer.start();
        panel.setLayout(null);
        //用户名字样标签
        Font font = new Font(Font.SERIF, Font.PLAIN, 18);
        JLabel userLabel = new JLabel("用户名: ");
        userLabel.setFont(font);
        userLabel.setBounds(55,300, 100, 25);
        panel.add(userLabel);
        //用户名文本域
        JTextField userText = new JTextField(20);
        userText.setBounds(120, 300, 185, 25);
        panel.add(userText);
        //密码字样标签
        JLabel passwordLabel = new JLabel("密   码:");
        passwordLabel.setFont(font);
        passwordLabel.setBounds(55, 335, 100, 25);
        panel.add(passwordLabel);
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(120, 335, 185, 25);
        panel.add(passwordText);
        //创建登陆按钮
        JButton loginButton = new JButton("登陆");
        loginButton.setFont(font);
        loginButton.setBounds(100, 380, 80, 25);
        panel.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = userText.getText();
                String passWord = passwordText.getText();
                //创建用户数据层对象
                UserDao ud = new UserDao();
                if((ud.validateLogin(userName, passWord)) && (!userName.equals(""))) {
                    //如果查询成功并且数据库中有此账号，那么释放这个页面的资源
                    //new 出控制页面组件
                    dispose();
//                    System.out.println("登陆成功");
                    //跳转页面,跳转到资产管理系统主页
                    AssetsMs.getInstance();
                } else {
                    JOptionPane.showMessageDialog(null, "用户名密码错误","提示",JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        //创建注册按钮
        JButton signUpButton = new JButton("注册");
        signUpButton.setFont(font);
        signUpButton.setBounds(200, 380,80,25);
        panel.add(signUpButton);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        dispose();
                    }
                });
                //创建注册组件
                new signUp();
                dispose();
            }

        });
    }
    public static void main(String[] args) {
        try
        {
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        }
        catch(Exception e)
        {
            //TODO exception
            System.out.println("加载皮肤失败");
        }
        AssetsMs.getInstance();
    }
}












































































