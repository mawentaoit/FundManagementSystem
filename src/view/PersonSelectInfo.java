package view;

import model.dao.AssetsTypeDao;
import model.dao.PersonDao;
import model.vo.AssetsDaoJDBCImpl;
import model.vo.AssetsTypeDaoJDBCImpl;
import model.vo.PersonDaoJDBCImpl;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonSelectInfo extends JPanel implements ActionListener, ListSelectionListener {
    PersonDao personDao;
    Container contentPane;
    //定义所用的面板
    JPanel upPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    JPanel downPanel = new JPanel();

    //框架的大小
    Dimension faceSize = new Dimension(400, 400);

    //定义图形界面元素
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JLabel jLabel5 = new JLabel();
    JLabel jLabel6 = new JLabel();

    JTextField jTextField1 = new JTextField(15);
    JTextField jTextField2 = new JTextField(15);
    JTextField jTextField3 = new JTextField(15);
    JTextField jTextField4 = new JTextField(15);
    JTextField jTextField5 = new JTextField(15);
    JTextField jTextField6 = new JTextField(15);

    JButton addInfo = new JButton();
    JButton modifyInfo = new JButton();
    JButton deleteInfo = new JButton();
    JButton clearInfo = new JButton();
    JButton saveInfo = new JButton();
    JButton eixtInfo = new JButton();

    JButton JNumberFind = new JButton();
    JButton JDepatrFind = new JButton();
    JButton JPositionFind = new JButton();
    JButton findAll = new JButton();

   JTextField JTextNumber = new JTextField(15);
   JTextField JTextDepart  =  new JTextField(15);
   JTextField JTextPosition =  new JTextField(15);
    //定义表格
    JScrollPane jScrollPane1;
    JTable jTable;
    ListSelectionModel listSelectionModel = null;
    String[] colName = {"人员编号", "姓名", "性别","部门","职位","其他"};
    String[][] colValue = null;

    GridBagLayout girdBag = new GridBagLayout();
    GridBagConstraints girdBagCon;

    public PersonSelectInfo() {
        personDao = new PersonDaoJDBCImpl();
        //设置框架的大小
        this.setSize(faceSize);
        try {
            Init(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "窗口初始化错误，错误原因：" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    //根据传入信息展示页面
    public PersonSelectInfo(String[][] ss) {
        personDao = new PersonDaoJDBCImpl();

        try {
            Init(ss);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "窗口初始化错误，错误原因：" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Init(String[][] ss ) throws Exception {
        setLayout(new BorderLayout());


        //中部面板的布局
        if(ss == null) {
            colValue  = personDao.findAll();
        } else {
            colValue = ss;
        }
        //中部面板的布局
        if (colValue == null) {
            colValue = new String[1][6];
        }
        jTable = new JTable(colValue, colName);
        jTable.setPreferredScrollableViewportSize(new Dimension(1300, 600));
        jTable.setFont(new Font("Menu.font", Font.PLAIN, 16));
        jTable.getTableHeader().setFont(new Font("Dialog", 0, 19));
        listSelectionModel = jTable.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener(this);
        jScrollPane1 = new JScrollPane(jTable);
//        jScrollPane1.setPreferredSize(new Dimension(400, 300));

        upPanel.add(jScrollPane1);
        add(upPanel, BorderLayout.NORTH);

        jLabel1.setText("人员编号");
        jLabel1.setFont(new Font("Dialog", 0, 12));
        centerPanel.add(jLabel1);
        centerPanel.add(jTextField1);

        jLabel2.setText("姓名");
        jLabel2.setFont(new Font("Dialog", 0, 12));
        centerPanel.add(jLabel2);
        centerPanel.add(jTextField2);

        jLabel3.setText("性别");
        jLabel3.setFont(new Font("Dialog", 0, 12));
        centerPanel.add(jLabel3);
        centerPanel.add(jTextField3);


        jLabel4.setText("部门");
        jLabel4.setFont(new Font("Dialog", 0, 12));
        centerPanel.add(jLabel4);
        centerPanel.add(jTextField4);


        jLabel5.setText("职位");
        jLabel5.setFont(new Font("Dialog", 0, 16));
        centerPanel.add(jLabel5);
        centerPanel.add(jTextField5);


        jLabel6.setText("其他");
        jLabel6.setFont(new Font("Dialog", 0, 16));
        centerPanel.add(jLabel6);
        centerPanel.add(jTextField6);
        add(centerPanel, BorderLayout.CENTER);


        jTextField1.setEditable(false);
        jTextField2.setEditable(false);
        jTextField3.setEditable(false);
        jTextField4.setEditable(false);
        jTextField5.setEditable(false);

        downInit();
    }

    /**
     * 下部面板的布局
     */
    public void downInit() {
        JLabel JLabelNumber = new JLabel("请输入编号: ");
        JLabelNumber.setFont(new Font("Dialog", 0, 16));
        downPanel.add(JLabelNumber);
        downPanel.add(JTextNumber);
        JNumberFind.setText("按ID号查询");
        JNumberFind.setFont(new Font("Dialog", 0, 16));
        downPanel.add(JNumberFind);

        JLabel JLabelDepatr = new JLabel("请输入部门: ");
        JLabelDepatr.setFont(new Font("Dialog",0, 16));
        downPanel.add(JLabelDepatr);
        downPanel.add(JTextDepart);
        JDepatrFind.setText("按部门查询");
        JDepatrFind.setFont(new Font("Dialog",0, 16));
        downPanel.add(JDepatrFind);

        JLabel JLabelPosition = new JLabel("请输入职位:");
        JLabelPosition.setFont(new Font("Dialog",0, 16));
        downPanel.add(JLabelPosition);
        downPanel.add(JTextPosition);
        JPositionFind.setText("按职位查询");
        JPositionFind.setFont(new Font("Dialog",0, 16));
        downPanel.add(JPositionFind);

        findAll.setText("查询全部");
        findAll.setFont(new Font("Dialog",0,16));
        downPanel.add(findAll);
        //添加事件侦听
        JNumberFind.addActionListener(this);
        JDepatrFind.addActionListener(this);
        JPositionFind.addActionListener(this);
        findAll.addActionListener(this);
        add(downPanel,BorderLayout.CENTER);
    }

    /**
     * 事件处理
     */
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj == JNumberFind) {
            colValue = null;
            try {
                //查询
                colValue = personDao.findPersonID(Integer.parseInt(JTextNumber.getText()));
                if(colValue == null) {
                    JOptionPane.showMessageDialog(null, "无此ID号人员信息");
                    return;
                }
              } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "数据库访问错误，错误原因：" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(obj == JDepatrFind) {
            colValue = null;
            try {
                //查询
                colValue = personDao.findPersonDepart(JTextDepart.getText());
                if(colValue == null) {
                    JOptionPane.showMessageDialog(null, "无此部门人员信息");
                    return;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "数据库访问错误，错误原因：" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(obj == JPositionFind) {
            colValue = null;
            try {
                //查询
                colValue = personDao.findPPosition(JTextPosition.getText());
                if(colValue == null) {
                    JOptionPane.showMessageDialog(null, "无此职位作为人员信息");
                    return;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "数据库访问错误，错误原因：" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(obj == findAll) {
            colValue = null;
        }
        getParent().remove(this);
        JPanel jpa = new PersonSelectInfo(colValue);
        AssetsMs ms = AssetsMs.getInstance();
        ms.panel = jpa;
        ms.add(jpa);
        ms.revalidate();
        ms.repaint();
        jTable.revalidate();
    }
    /**
     * 当表格被选中时的操作
     */
    public void valueChanged(ListSelectionEvent lse) {
        int selectedRow = jTable.getSelectedRow();
        jTextField1.setText((String) jTable.getValueAt(selectedRow, 0));
        jTextField2.setText((String) jTable.getValueAt(selectedRow, 1));
        jTextField3.setText((String) jTable.getValueAt(selectedRow, 2));
        jTextField4.setText((String) jTable.getValueAt(selectedRow, 3));
        jTextField5.setText((String) jTable.getValueAt(selectedRow, 4));
        jTextField6.setText((String) jTable.getValueAt(selectedRow, 5));
    }
}