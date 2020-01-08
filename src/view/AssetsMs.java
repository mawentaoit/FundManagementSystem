package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 资产管理系统主类
 */
public class  AssetsMs extends JFrame implements ActionListener {
//    boolean packFrame = false;
    Dimension faceSize = new Dimension(1400, 900);
    //建立菜单栏

    JMenuBar mainMenu = new JMenuBar();
    //建立“系统管理”菜单组
    JMenu menuSystem = new JMenu();
    JMenuItem itemTypeMan = new JMenuItem();
    JMenuItem itemExit = new JMenuItem();
    //建立“资产信息管理”菜单组
    JMenu menuAssets = new JMenu();
    JMenuItem itemAddAssets = new JMenuItem();
    JMenuItem itemModifyAssets = new JMenuItem();
    JMenuItem itemDeleteAssets = new JMenuItem();
    JMenuItem itemSelectAssets = new JMenuItem();

    //建立“人员信息管理”菜单组
    JMenu menuPerson = new JMenu();
    JMenuItem itemAddPerson = new JMenuItem();
    JMenuItem itemModifyPerson = new JMenuItem();
    JMenuItem itemDeletePerson = new JMenuItem();
    JMenuItem itemSelectPerson = new JMenuItem();

    //建立“资产领用”菜单组
    JMenu menuUsing = new JMenu();
    JMenuItem itemUsing = new JMenuItem();
    JMenuItem itemSelectUsing = new JMenuItem();
    //建立“资产归还”菜单组
    JMenu menuBack = new JMenu();
    JMenuItem itemBack = new JMenuItem();
    JMenuItem itemSelectBack = new JMenuItem();
    //建立“资产报废”菜单组
    JMenu menuInvalid = new JMenu();
    JMenuItem itemInvalid = new JMenuItem();
    JMenuItem itemSelectInvalid = new JMenuItem();
    public   JPanel panel = new JPanel();
    private static AssetsMs singleton = null;
    //实现单例模式
    public static AssetsMs getInstance() {
        if(singleton == null) {
            singleton = new AssetsMs();
        }
        return singleton;
    }
    //程序初始化
    private AssetsMs() {
        try{
            this.init();
        }catch(Exception ex) {
            Logger.getLogger(AssetsMs.class.getName()).log(Level.SEVERE, null, ex);
        }
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void init() throws Exception {
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        //设置框架大小
        this.setSize(faceSize);

        //设置标题
        this.setTitle("资产管理系统");
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        panel = new JPanel();
        contentPane.add(panel);
        //添加菜单组
        menuSystem.setText("系统管理");
        menuSystem.setFont(new Font("Dialog", 0, 18));
        menuAssets.setText("资产信息管理");
        menuAssets.setFont(new Font("Dialog", 0, 18));
        menuPerson.setText("人员信息管理");
        menuPerson.setFont(new Font("Dialog", 0, 18));
        menuUsing.setText("资产领用");
        menuUsing.setFont(new Font("Dialog", 0, 18));
        menuBack.setText("资产归还");
        menuBack.setFont(new Font("Dialog", 0, 18));
        menuInvalid.setText("资产报废");
        menuInvalid.setFont(new Font("Dialog", 0, 18));

        //生成“系统管理”菜单组的选项
        itemTypeMan.setText("类别管理");
        itemTypeMan.setFont(new Font("Dialog", 0, 18));
        itemExit.setText("退出");
        itemExit.setFont(new Font("Dialog", 0, 18));
        //生成“资产信息管理”菜单组的选项
        itemAddAssets.setText("增加");
        itemAddAssets.setFont(new Font("Dialog", 0, 18));
        itemModifyAssets.setText("修改");
        itemModifyAssets.setFont(new Font("Dialog", 0, 18));
        itemDeleteAssets.setText("删除");
        itemDeleteAssets.setFont(new Font("Dialog", 0, 18));
        itemSelectAssets.setText("查询");
        itemSelectAssets.setFont(new Font("Dialog", 0, 18));
        //生成“人员信息管理”菜单组的选项
        itemAddPerson.setText("人员信息增加");
        itemAddPerson.setFont(new Font("Dialog", 0, 18));
        itemModifyPerson.setText("人员信息修改");
        itemModifyPerson.setFont(new Font("Dialog", 0, 18));
        itemDeletePerson.setText("人员信息删除");
        itemDeletePerson.setFont(new Font("Dialog", 0, 18));
        itemSelectPerson.setText("查询人员信息");
        itemSelectPerson.setFont(new Font("Dialog", 0, 18));
        //生成“资产领用”菜单组的选项
        itemUsing.setText("资产领用管理");
        itemUsing.setFont(new Font("Dialog", 0, 18));
        itemSelectUsing.setText("领用信息查询");
        itemSelectUsing.setFont(new Font("Dialog", 0, 18));
        //生成“资产归还”菜单组的选项
        itemBack.setText("资产归还管理");
        itemBack.setFont(new Font("Dialog", 0, 18));
        itemSelectBack.setText("归还信息查询");
        itemSelectBack.setFont(new Font("Dialog", 0, 18));
        //生成“资产报废”菜单组的选项
        itemInvalid.setText("资产报废管理");
        itemInvalid.setFont(new Font("Dialog", 0, 18));
        itemSelectInvalid.setText("报废信息查询");
        itemSelectInvalid.setFont(new Font("Dialog", 0, 18));

        //添加“系统管理”菜单组
        menuSystem.add(itemTypeMan);
        menuSystem.add(itemExit);
        //添加“资产信息管理”菜单组
        menuAssets.add(itemAddAssets);
        menuAssets.add(itemModifyAssets);
        menuAssets.add(itemDeleteAssets);
        menuAssets.add(itemSelectAssets);

        //添加“人员信息管理”菜单组
        menuPerson.add(itemAddPerson);
        menuPerson.add(itemModifyPerson);
        menuPerson.add(itemDeletePerson);
        menuPerson.add(itemSelectPerson);
        //添加“资产领用”菜单组
        menuUsing.add(itemUsing);
        menuUsing.add(itemSelectUsing);
        //添加“资产归还”菜单组
        menuBack.add(itemBack);
        menuBack.add(itemSelectBack);
        //添加“资产报废”菜单组
        menuInvalid.add(itemInvalid);
        menuInvalid.add(itemSelectInvalid);

        //添加所有的菜单组
        mainMenu.add(menuSystem);
        mainMenu.add(menuAssets);
        mainMenu.add(menuPerson);
        mainMenu.add(menuUsing);
        mainMenu.add(menuBack);
        mainMenu.add(menuInvalid);
        this.setJMenuBar(mainMenu);

        //添加主页
        showIndex();
        //添加事件侦听
        itemTypeMan.addActionListener(this);
        itemExit.addActionListener(this);
        itemAddAssets.addActionListener(this);
        itemModifyAssets.addActionListener(this);
        itemDeleteAssets.addActionListener(this);
        itemSelectAssets.addActionListener(this);

        itemAddPerson.addActionListener(this);
        itemModifyPerson.addActionListener(this);
        itemDeletePerson.addActionListener(this);
        itemSelectPerson.addActionListener(this);

        itemUsing.addActionListener(this);
        itemSelectUsing.addActionListener(this);
        itemBack.addActionListener(this);
        itemSelectBack.addActionListener(this);
        itemInvalid.addActionListener(this);
        itemSelectInvalid.addActionListener(this);
        //关闭程序时的操作
        this.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                }
        );
    };

    private void showIndex(){
        this.remove(panel);
        panel = new TypeInfo();
        add(panel);
    }
    public JFrame getMyFrame() {
        return this;
    }

    /**
     * 事件处理
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == itemExit) { //退出
            System.exit(0);
        }
        //类别管理
        if(obj == itemTypeMan) {
            //资产类别管理
            remove(panel);
            System.out.println("执行了界面");
            panel = new TypeInfo();
            add(panel);
            panel.revalidate();
            panel.repaint();
        }


        if(obj == itemAddPerson) {
            //人员信息增加
            remove(panel);
//            System.out.println("执行了界面");
            panel = new PersonAddInfo();
            add(panel);
//            System.out.println("添加人员");
            panel.revalidate();
            panel.repaint();
        }


        //人员管理
        if (obj == itemModifyPerson) { //人员信息修改
            remove(panel);
            panel = new PersonModifyInfo();
            add(panel);
            panel.revalidate();
            panel.repaint();
        }
        if (obj == itemDeletePerson) { //人员信息删除
            remove(panel);
            panel = new PersonDeleteInfo();
            add(panel);
            panel.revalidate();
            panel.repaint();
        }
        if (obj == itemSelectPerson) { //人员信息查询
            remove(panel);
            panel = new PersonSelectInfo();
            add(panel);
            panel.revalidate();
            panel.repaint();
        }



        //资产信息管理
        if (obj == itemAddAssets) { //资产信息增加
            remove(panel);
            panel = new AssAddInfo();
            add(panel);
            panel.revalidate();
            panel.repaint();
        }


        if (obj ==  itemModifyAssets) { //资产信息修改
            remove(panel);
            panel = new AssModifyInfo();
            add(panel);
            panel.revalidate();
            panel.repaint();
        }

        if (obj ==  itemDeleteAssets) { //资产信息删除
            remove(panel);
            panel = new AssDeleteInfo();
            add(panel);
            panel.revalidate();
            panel.repaint();
        }
        if (obj ==  itemSelectAssets) { //资产信息查询全部
            remove(panel);
            panel = new AssSelectInfo();
            add(panel);
            panel.revalidate();
            panel.repaint();
        }



        //资产信息领用管理
        if (obj == itemUsing) {//资产信息领用管理
            remove(panel);
            panel = new AssetsUsing();
            add(panel);
            panel.revalidate();
            panel.repaint();
        }

        if (obj == itemSelectUsing) {//资产信息领用查询
            remove(panel);
            panel = new AssetsSelectUsing();
            add(panel);
            panel.revalidate();
            panel.repaint();
        }

        //资产归还
        if (obj == itemBack) {//资产归还管理
            remove(panel);
            panel = new AssetsBack();
            add(panel);
            panel.revalidate();
            panel.repaint();
        }
        if (obj == itemSelectBack) {//资产归还查询
            remove(panel);
            panel = new AssetsSelectBack();
            add(panel);
            panel.revalidate();
            panel.repaint();
        }

        if (obj == itemInvalid) {//资产报废管理
            remove(panel);
            panel = new AssetsInvalid();
            add(panel);
            panel.revalidate();
            panel.repaint();
        }
        if (obj == itemSelectInvalid) {//资产报废查询
            remove(panel);
            panel = new AssetsSelectInvalid();
            add(panel);
            panel.revalidate();
            panel.repaint();
        }
    }
}


