package view;

import controller.Assets;
import model.dao.AssetsDao;
import model.dao.AssetsTypeDao;
import model.vo.AssetsDaoJDBCImpl;
import model.vo.AssetsTypeDaoJDBCImpl;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AssAddInfo  extends JPanel implements ActionListener, ListSelectionListener {
    AssetsDao assetsDao;
    AssetsTypeDao assetsTypeDao;
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
    JLabel jLabel7 = new JLabel();
    JLabel jLabel8 = new JLabel();

    JTextField jTextField1 = new JTextField(15);
    JTextField jTextField2 = new JTextField(15);
    JTextField jTextField3 = new JTextField(15);
    JTextField jTextField4 = new JTextField(15);
    JTextField jTextField5 = new JTextField(15);
    JTextField jTextField6 = new JTextField(15);
    JTextField jTextField7 = new JTextField(15);
    JTextField jTextField8 = new JTextField(15);

    JButton addInfo = new JButton();
    JButton modifyInfo = new JButton();
    JButton deleteInfo = new JButton();
    JButton clearInfo = new JButton();
    JButton saveInfo = new JButton();
    JButton eixtInfo = new JButton();
    //数据中的所有数据
    String[][] s = null;
    //定义表格
    JScrollPane jScrollPane1;
    JTable jTable;
    ListSelectionModel listSelectionModel = null;
    String[] colName = {"资产编号", "资产名称", "所属类型","型号","价格","购买日期","状态","备注"};


    GridBagLayout girdBag = new GridBagLayout();
    GridBagConstraints girdBagCon;

    JComboBox jComboBox;

    public AssAddInfo() {
        assetsDao = new AssetsDaoJDBCImpl();

        try {
            Init();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "窗口初始化错误，错误原因：" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void Init() throws Exception {
        assetsTypeDao = new AssetsTypeDaoJDBCImpl();
        System.out.println("进入了");
        setLayout(new BorderLayout());
        jComboBox = new JComboBox();   //  资产类别下拉列表
        //中部面板的布局
        String[][] colValue = assetsDao.findOverTypeID();
        if (colValue == null) {
            colValue = new String[1][8];
        }
        jTable = new JTable(colValue, colName);
        jTable.setPreferredScrollableViewportSize(new Dimension(1300, 600));
        jTable.setFont(new Font("Menu.font", Font.PLAIN, 16));
        jTable.getTableHeader().setFont(new Font("Dialog", 0, 19));
        listSelectionModel = jTable.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener(this);
        jScrollPane1 = new JScrollPane(jTable);
//        jScrollPane1.setPreferredSize(new Dimension(600, 300));

        upPanel.add(jScrollPane1);
        add(upPanel, BorderLayout.NORTH);

        jLabel1.setText("资产编号");
        jLabel1.setFont(new Font("Dialog", 0, 16));
        centerPanel.add(jLabel1);
        centerPanel.add(jTextField1);

        jLabel2.setText("资产名称");
        jLabel2.setFont(new Font("Dialog", 0, 16));
        centerPanel.add(jLabel2);
        centerPanel.add(jTextField2);
        centerPanel.add(jLabel3);
        if (assetsTypeDao.findAll() != null) {  //获取数据库中的值
            s = assetsTypeDao.findAll();
            jComboBox.addItem("请选择资产类型");
            for (int i = 0; i < s.length; i++) {
                jComboBox.addItem(s[i][1]);
//                jComboBox.addItem(s[i][0]);
            }
            centerPanel.add(jComboBox);
        } else {
            jComboBox.addItem("没有数据请添加");
            centerPanel.add(jComboBox);
        }
        jLabel3.setText("所属类型");
        jLabel3.setFont(new Font("Dialog", 0, 16));
        jComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String item = (String) jComboBox.getSelectedItem();
                if ("请选择资产编号".equals(item)) {
                    jTextField3.setText("");
                } else {
                    jTextField3.setText(item);
                }
            }
        });

        centerPanel.add(jTextField3);


        jLabel4.setText("型号");
        jLabel4.setFont(new Font("Dialog", 0, 16));
        centerPanel.add(jLabel4);
        centerPanel.add(jTextField4);


        jLabel5.setText("价格");
        jLabel5.setFont(new Font("Dialog", 0, 16));
        centerPanel.add(jLabel5);
        centerPanel.add(jTextField5);



        jLabel8.setText("备注");
        jLabel8.setFont(new Font("Dialog", 0, 16));
        centerPanel.add(jLabel8);
        centerPanel.add(jTextField8);

        add(centerPanel, BorderLayout.CENTER);


        jTextField1.setEditable(false);
        jTextField2.setEditable(true);
        jTextField3.setEditable(false);
        jTextField4.setEditable(true);
        jTextField5.setEditable(true);
        jTextField7.setEditable(true);
        jTextField8.setEditable(true);
        downInit();
    }

    /**
     * 下部面板的布局
     */
    public void downInit() {

        addInfo.setText("增加");
        addInfo.setFont(new Font("Dialog", 0, 16));
        downPanel.add(addInfo);



        add(downPanel, BorderLayout.SOUTH);

        //添加事件侦听
        addInfo.addActionListener(this);


        addInfo.setEnabled(true);
    }

    /**
     * 事件处理
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == addInfo) {
            try {
                //增加
                if(jTextField2.getText().equals("") || jTextField3.getText().equals("") || jTextField4.getText().equals("") || jTextField5.getText().equals("") ){
                    JOptionPane.showMessageDialog(null, "输入不能有空数据！请从新输入:");
                    return;
                }
                String ID = null;
//                System.out.println("类别为: " + jTextField3.getText());
                for(int i = 0; i < s.length; i++) {
                    System.out.println("类别分别有: " + s[i][1] + "ID: " + s[i][0]);
                    if(jTextField3.getText().equals(s[i][1])) {
                        ID = s[i][0];
                    }
                }
//                System.out.println("ID" + ID);
                assetsDao.add(new Assets(jTextField2.getText(),ID, jTextField4.getText(), jTextField5.getText(),"可用", jTextField8.getText()));
                JOptionPane.showMessageDialog(null, "资产类别添加成功！", "资产类别添加", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "数据库访问错误，错误原因：" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
            getParent().remove(this);
            JPanel jpa = new AssAddInfo();
            AssetsMs ms = AssetsMs.getInstance();
            ms.panel = jpa;
            ms.add(jpa);
        }

        jTable.revalidate();
    }

    /**
     * 将文本框清空
     */
    void setNull() {
        jTextField1.setText(null);
        jTextField2.setText(null);
        jTextField3.setText(null);
        jTextField4.setEditable(true);
        jTextField5.setEditable(true);
        jTextField6.setEditable(true);
        jTextField7.setEditable(true);


        addInfo.setEnabled(true);
        modifyInfo.setEnabled(false);
        deleteInfo.setEnabled(false);
        clearInfo.setEnabled(true);
    }

    /**
     * 当表格被选中时的操作
     */
    public void valueChanged(ListSelectionEvent lse) {

    }

}
