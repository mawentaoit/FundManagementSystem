package view;

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

public class AssSelectInfo extends JPanel implements ActionListener, ListSelectionListener {
    AssetsDao assetsDao;
    Container contentPane;
    //定义所用的面板
    JPanel upPanel = new JPanel();
    JPanel downPanel = new JPanel();



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

    JButton AllFind = new JButton();
    JButton FindByID = new JButton();
    JButton FindByB_Type = new JButton();
    JButton FindByS_Type = new JButton();
    JButton JLabelBType = new JButton();
    //定义文本域
    JTextField JLabelTextId = new JTextField(15);
    JTextField JLabelTextType = new JTextField(15);
    JComboBox jComboBox = new JComboBox();
    //定义表格
    JScrollPane jScrollPane1;
    JTable jTable;
    ListSelectionModel listSelectionModel = null;
    String[] colName = {"资产编号", "资产名称", "所属类型","型号","价格","购买日期","状态","备注"};


    GridBagLayout girdBag = new GridBagLayout();
    GridBagConstraints girdBagCon;

    public AssSelectInfo() {
        assetsDao = new AssetsDaoJDBCImpl();

        try {
            Init(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "窗口初始化错误，错误原因：" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }

    }

    //根据传入信息展示页面
    public AssSelectInfo(String[][] ss) {
        assetsDao = new AssetsDaoJDBCImpl();

        try {
            Init(ss);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "窗口初始化错误，错误原因：" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void Init(String[][] ss ) throws Exception {
        setLayout(new BorderLayout());
        String[][] colValue;
        //中部面板的布局
        if(ss == null) {
          colValue  = assetsDao.findOverTypeID();
        } else {
            colValue = ss;
        }

        if (colValue == null) {
            colValue = new String[1][8];
        }
        jTable = new JTable(colValue, colName);
        jTable.setPreferredScrollableViewportSize(new Dimension(1300, 600));
        listSelectionModel = jTable.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener(this);
        jScrollPane1 = new JScrollPane(jTable);
        jTable.setFont(new Font("Menu.font", Font.PLAIN, 16));
        jTable.getTableHeader().setFont(new Font("Dialog", 0, 19));
        upPanel.add(jScrollPane1);
        add(upPanel, BorderLayout.NORTH);

        downInit();
    }

    /**
     * 下部面板的布局
     */
    public void downInit() throws Exception{
        JLabel JLabelId = new JLabel("请输入ID号: ");
        JLabelId.setFont(new Font("Dialog", 0, 16));
        downPanel.add(JLabelId);
        downPanel.add(JLabelTextId);
        FindByID.setText("按ID号查询");
        FindByID.setFont(new Font("Dialog", 0, 16));
        downPanel.add(FindByID);
        JLabelId.setFont(new Font("Dialog", 0, 16));


        AssetsTypeDao assetsTypeDao = new AssetsTypeDaoJDBCImpl();
        if (assetsTypeDao.findAll() != null) {  //获取数据库中的值
            String[][] s = assetsTypeDao.findAll();
            jComboBox.addItem("请选择资产类型");
            for (int i = 0; i < s.length; i++) {
                jComboBox.addItem(s[i][1]);
//                jComboBox.addItem(s[i][0]);
            }
            downPanel.add(jComboBox);
        } else {
            jComboBox.addItem("没有数据请添加");
            downPanel.add(jComboBox);
        }


        FindByB_Type.setText("按类型查询");
        FindByB_Type.setFont(new Font("Dialog", 0, 16));
        downPanel.add(FindByB_Type);
        FindByS_Type.setText("查询全部");
        FindByS_Type.setFont(new Font("Dialog", 0, 16));
        downPanel.add(FindByS_Type);

        //添加事件侦听
        FindByID.addActionListener(this);
        FindByB_Type.addActionListener(this);
        FindByS_Type.addActionListener(this);
        add(downPanel,BorderLayout.CENTER);
    }

    /**
     * 事件处理
     */
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        AssetsDao assetsDao = new AssetsDaoJDBCImpl();
        String[][]   colValue = null;
        if(obj == FindByID) {
            try {
                colValue = assetsDao.findByAssetsID(Integer.parseInt(JLabelTextId.getText()));
                if(colValue == null) {
                    JOptionPane.showMessageDialog(null, "无此ID号作为资产信息");
                    return;
                }
            } catch (Exception ex) {
            }
            getParent().remove(this);
            JPanel jpa = new AssSelectInfo(colValue);
            AssetsMs ms = AssetsMs.getInstance();
            ms.panel = jpa;
            ms.add(jpa);
            ms.revalidate();
            ms.repaint();
        }
        if(obj == FindByB_Type) {
            AssetsTypeDao assetsType = new AssetsTypeDaoJDBCImpl();
            try {
                colValue  =  assetsDao.findByAssetsTypeID(assetsType.TypeIDByTypeName((String) jComboBox.getSelectedItem()));
                if(colValue == null) {
                    JOptionPane.showMessageDialog(null, "无此类别作为资产信息");
                    return;
                }
            } catch (Exception ex) {
            }
            getParent().remove(this);
            JPanel jpa = new AssSelectInfo(colValue);
            AssetsMs ms = AssetsMs.getInstance();
            ms.panel = jpa;
            ms.add(jpa);
            ms.revalidate();
            ms.repaint();
        }
        if(obj == FindByS_Type) {
            getParent().remove(this);
            JPanel jpa = new AssSelectInfo(null);
            AssetsMs ms = AssetsMs.getInstance();
            ms.panel = jpa;
            ms.add(jpa);
            ms.revalidate();
            ms.repaint();
        }
        jTable.revalidate();
    }

    /**
     * 将文本框清空
     */
    void setNull() {
        jTextField1.setText(null);
        jTextField2.setText(null);
        jTextField4.setText(null);
        jTextField5.setEditable(true);
        jTextField6.setEditable(true);
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
        jTextField7.setText((String) jTable.getValueAt(selectedRow, 6));
        jTextField8.setText((String) jTable.getValueAt(selectedRow, 7));
    }
}
