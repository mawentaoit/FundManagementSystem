package view;

import controller.Assets;
import controller.AssetsTrjn;
import model.dao.AssetsDao;
import model.dao.AssetsTrjnDao;
import model.dao.AssetsTypeDao;
import model.dao.PersonDao;
import model.vo.AssetsDaoJDBCImpl;
import model.vo.AssetsTrjnDaoJDBCImpl;
import model.vo.AssetsTypeDaoJDBCImpl;
import model.vo.PersonDaoJDBCImpl;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AssetsBack extends JPanel implements ActionListener, ListSelectionListener {
    PersonDao personDao = new PersonDaoJDBCImpl();
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

    JButton receiveInfo = new JButton();
    JButton eixtInfo = new JButton();

    //定义表格
    JScrollPane jScrollPane1;
    JTable jTable;
    ListSelectionModel listSelectionModel = null;
    String[] colName = {"资产编号", "资产名称", "所属类型","型号","价格","购买日期","状态","备注"};


    String[][] s1;

    GridBagLayout girdBag = new GridBagLayout();
    GridBagConstraints girdBagCon;

    JComboBox jComboBox;
    JComboBox jComboBox1;

    public AssetsBack() {
        assetsDao = new AssetsDaoJDBCImpl();
        //设置框架的大小
        this.setSize(faceSize);
        try {
            Init();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "窗口初始化错误，错误原因：" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void Init() throws Exception {
        assetsTypeDao = new AssetsTypeDaoJDBCImpl();
        setLayout(new BorderLayout());
        jComboBox = new JComboBox();   //  资产类别下拉列表
        jComboBox1 = new JComboBox();
        //中部面板的布局
        String[][] colValue = assetsDao.findAllUsedOverTypeID();
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

        if (personDao.findAll() != null) {  //获取数据库中的值
            s1  = personDao.findAll();
            jComboBox1.addItem("请选择归还人");
            for (int i = 0; i < s1.length; i++) {
//                jComboBox1.addItem(s1[i][1]);
                jComboBox1.addItem(s1[i][1]);
            }
            centerPanel.add(jComboBox1);
        } else {
            jComboBox1.addItem("没有数据请添加");
            centerPanel.add(jComboBox1);
        }
        jLabel3.setText("归还人");
        jLabel3.setFont(new Font("Dialog", 0, 16));
        centerPanel.add(jLabel3);
        /*
        对选择的选项添加到文本框中
         */
        jComboBox1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String item1 = (String) jComboBox1.getSelectedItem();
                if ("请选择归还人".equals(item1)) {
                    jTextField3.setText("");
                } else {
                    jTextField3.setText(item1);
                }
            }
        });

        centerPanel.add(jTextField3);

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
        receiveInfo.setText("归还");
        receiveInfo.setFont(new Font("Dialog", 0, 16));
        downPanel.add(receiveInfo);
        downPanel.add(receiveInfo);
        add(downPanel, BorderLayout.SOUTH);

        //添加事件侦听
        receiveInfo.addActionListener(this);

        receiveInfo.setEnabled(true);
    }

    /**
     * 事件处理
     */
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        int selectedRow = jTable.getSelectedRow();
        AssetsDao assetsDao=new AssetsDaoJDBCImpl();
        AssetsTrjnDao assetsTrjnDao=new AssetsTrjnDaoJDBCImpl();
        if (obj == receiveInfo) {
            try {
                //领用,

                String ID = null;
                for(int i = 0; i < s1.length; i++) {
                    System.out.println("类别分别有: " + s1[i][1] + "ID: " + s1[i][0]);
                    if(jTextField3.getText().equals(s1[i][1])) {
                        ID = s1[i][0];
                    }
                }
                assetsDao.modifyUsing(new Assets("可用",Integer.parseInt((String)jTable.getValueAt(selectedRow,0))));
                assetsTrjnDao.add2(new AssetsTrjn(Integer.parseInt((String)jTable.getValueAt(selectedRow,0)),
                        Integer.parseInt(ID),
                        jTextField5.getText(), jTextField8.getText()));

                JOptionPane.showMessageDialog(null, "资产归还成功！", "资产归还", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "数据库访问错误，错误原因：" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
            getParent().remove(this);
            JPanel jpa = new AssetsBack();
            AssetsMs ms = AssetsMs.getInstance();
            ms.panel = jpa;
            ms.add(jpa);
        }
    }



    /**
     * 当表格被选中时的操作
     */
    public void valueChanged(ListSelectionEvent lse) {



        //设置是否可操作
        jTextField2.setEditable(true);
        jTextField3.setEditable(true);
        jTextField4.setEditable(true);
        jTextField5.setEditable(true);

        receiveInfo.setEnabled(true);

    }

}
