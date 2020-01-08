package view;

import controller.Person;
import model.dao.PersonDao;
import model.vo.PersonDaoJDBCImpl;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonAddInfo extends JPanel implements ActionListener, ListSelectionListener {
PersonDao personDao;
//定义所用的面板
JPanel upPanel = new JPanel();
JPanel centerPanel = new JPanel();
JPanel downPanel = new JPanel();

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
    //定义表格
    JScrollPane jScrollPane1; //滚动面板
    JTable jTable;
    ListSelectionModel listSelectionModel = null;
    String[] colName = {"人员编号", "姓名", "性别","部门","职位","其他"};


    GridBagLayout girdBag = new GridBagLayout(); //网格布局
    GridBagConstraints girdBagCon;

    public PersonAddInfo() {
        personDao = new PersonDaoJDBCImpl();
        try{
            Init();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "窗口初始化错误，错误原因：" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Init() throws Exception{
        setLayout(new BorderLayout());
        //中部面板的布局
        String[][] colValue = personDao.findAll();
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
        jLabel1.setFont(new Font("Dialog", 0, 16));
        centerPanel.add(jLabel1);
        centerPanel.add(jTextField1);

        jLabel2.setText("姓名");
        jLabel2.setFont(new Font("Dialog", 0, 16));
        centerPanel.add(jLabel2);
        centerPanel.add(jTextField2);

        jLabel3.setText("性别");
        jLabel3.setFont(new Font("Dialog", 0, 16));
        centerPanel.add(jLabel3);
        centerPanel.add(jTextField3);


        jLabel4.setText("部门");
        jLabel4.setFont(new Font("Dialog", 0, 16));
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
        jTextField2.setEditable(true);
        jTextField3.setEditable(true);
        jTextField4.setEditable(true);
        jTextField5.setEditable(true);
        jTextField6.setEditable(true);
//        System.out.println("运行到了这里");
        downInit();
        setVisible(true);
    }

    /**
     * 下部面板的布局
     */
    public void downInit() {
        addInfo.setText("增加");
        addInfo.setFont(new Font("Dialog",0, 16));
        downPanel.add(addInfo);
        add(downPanel, BorderLayout.SOUTH);
        //添加事件侦听
        addInfo.addActionListener(this);
//        modifyInfo.addActionListener(this);
//        deleteInfo.addActionListener(this);
//        clearInfo.addActionListener(this);
//        eixtInfo.addActionListener(this);

        addInfo.setEnabled(true);
//         modifyInfo.setEnabled(false);
//        deleteInfo.setEnabled(false);
//         clearInfo.setEnabled(true);
    }

    /**
     * 事件处理
     */
    public void actionPerformed(ActionEvent e) { //创建监听类
        Object obj = e.getSource();
        if (obj == addInfo) {
            try {
                //增加
                if(jTextField2.getText().equals("") || jTextField3.getText().equals("") || jTextField4.getText().equals("") || jTextField5.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "输入不能有空数据！请从新输入:");
                    return;
                }
                personDao.add(new Person(jTextField1.getWidth(),jTextField2.getText(), jTextField3.getText(), jTextField4.getText(), jTextField5.getText(), jTextField6.getText()));
                JOptionPane.showMessageDialog(null, "人员信息添加成功！", "人员信息添加", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "数据库访问错误，错误原因：" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
            getParent().remove(this);
            JPanel jpa = new PersonAddInfo();
            AssetsMs ms = AssetsMs.getInstance();
            ms.panel = jpa;
            ms.add(jpa);
        }
        jTable.revalidate();
    }
    /**
     * 当表格被选中时的操作
     */
    public void valueChanged(ListSelectionEvent lse) {
//        int selectedRow = jTable.getSelectedRow();
////        jTextField1.setText((String) jTable.getValueAt(selectedRow, 0));
//        jTextField2.setText((String) jTable.getValueAt(selectedRow, 1));
//        jTextField3.setText((String) jTable.getValueAt(selectedRow, 2));
//        jTextField4.setText((String) jTable.getValueAt(selectedRow, 3));
//        jTextField5.setText((String) jTable.getValueAt(selectedRow, 4));
//        jTextField6.setText((String) jTable.getValueAt(selectedRow, 5));
//        //设置是否可操作
//        jTextField2.setEditable(true);
//        jTextField3.setEditable(true);
//        jTextField4.setEditable(true);
//        jTextField5.setEditable(true);
//        jTextField6.setEditable(true);
//
//        addInfo.setEnabled(true);

    }

}
