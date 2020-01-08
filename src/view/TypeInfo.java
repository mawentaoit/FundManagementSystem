package view;

import controller.AssetsType;
import model.dao.AssetsTypeDao;
import model.vo.AssetsTypeDaoJDBCImpl;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TypeInfo extends JPanel implements ActionListener, ListSelectionListener {
    AssetsTypeDao assetsTypeDao;
    Container contentPane;
    //定义所使用的面板
    JPanel upPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    JPanel downPanel = new JPanel();
    //定义图形界面元素
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JTextField jTextField1 = new JTextField(15);
    JTextField jTextField2 = new JTextField(15);
    JTextField jTextField3 = new JTextField(15);

    JButton addInfo = new JButton();
    JButton modifyInfo = new JButton();
    JButton deleteInfo = new JButton();
    JButton clearInfo = new JButton();

    //定义表格
    JScrollPane jScrollPane1;
    JTable jTable;
    DefaultTableModel tableModel;
    ListSelectionModel listSelectionModel = null;
//    Dimension faceSize = new Dimension(1200, 800);
//    GridBagLayout girdBag = new GridBagLayout();
//    GridBagConstraints girdBagCon;

    public TypeInfo() {
        assetsTypeDao = new AssetsTypeDaoJDBCImpl();
        //设置框架的大小
        try {
            init();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "窗口初始化错误，错误原因：" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void valueChanged(ListSelectionEvent lse){
        int selectedRow = jTable.getSelectedRow();
        jTextField1.setText((String) jTable.getValueAt(selectedRow, 0));
        jTextField2.setText((String) jTable.getValueAt(selectedRow, 1));
        jTextField3.setText((String) jTable.getValueAt(selectedRow, 2));
        //设置是否可操作
        jTextField2.setEditable(true);
        jTextField3.setEditable(true);
        deleteInfo.setEnabled(true);
    }
    private void init() throws Exception {
        System.out.println("进入了");
        setLayout(new BorderLayout());
        String[][] colValue = assetsTypeDao.findAll();
        if (colValue == null) {
            colValue = new String[1][3];
        }
        String[] colName = {"资产类别编号", "资产大类", "资产小类"};
        tableModel = new DefaultTableModel(colValue, colName);
        jTable = new JTable(tableModel);
        jTable.setPreferredScrollableViewportSize(new Dimension(1150, 600));
        jTable.setFont(new Font("Menu.font", Font.PLAIN, 16));
        jTable.getTableHeader().setFont(new Font("Dialog", 0, 19));
        listSelectionModel = jTable.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener(this);
        jScrollPane1 = new JScrollPane(jTable);
//        jScrollPane1.setPreferredSize(new Dimension(400, 300));

        upPanel.add(jScrollPane1);
        add(upPanel, BorderLayout.NORTH);
        jLabel1.setText("编号");
        jLabel1.setFont(new Font("Dialog", 0, 16));
        centerPanel.add(jLabel1);
        centerPanel.add(jTextField1);

        jLabel2.setText("大类");
        jLabel2.setFont(new Font("Dialog", 0, 16));
        centerPanel.add(jLabel2);
        centerPanel.add(jTextField2);

        jLabel3.setText("小类");
        jLabel3.setFont(new Font("Dialog", 0, 16));
        centerPanel.add(jLabel3);
        centerPanel.add(jTextField3);
        add(centerPanel, BorderLayout.CENTER);
        jTextField1.setEditable(false);
        jTextField2.setEditable(true);
        jTextField3.setEditable(true);
        downInit();
    }

    public void downInit() {
        addInfo.setText("增加");
        addInfo.setFont(new Font("Dialog", 0, 16));
        downPanel.add(addInfo);
        modifyInfo.setText("修改");
        modifyInfo.setFont(new Font("Dialog", 0, 16));
        downPanel.add(modifyInfo);
        deleteInfo.setText("删除");
        deleteInfo.setFont(new Font("Dialog", 0, 16));
        clearInfo.setText("清空");
        clearInfo.setFont(new Font("Dialog",0,16));
        downPanel.add(deleteInfo);

        add(downPanel, BorderLayout.SOUTH);
        //添加事件侦听
        addInfo.addActionListener(this);
        modifyInfo.addActionListener(this);
        deleteInfo.addActionListener(this);
        clearInfo.addActionListener(this);
        addInfo.setEnabled(true);
        modifyInfo.setEnabled(true);
        deleteInfo.setEnabled(true);
        clearInfo.setEnabled(true);
    }


    /**
     * 事件处理
     */
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == addInfo) {
            try {
                //增加
                if(!jTextField1.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "已有数据不能重复添加！:");
                    return;
                }
                if (jTextField2.getText().equals("") || jTextField3.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "输入不能有空数据！请从新输入:");
                    return;
                }
                assetsTypeDao.add(new AssetsType(jTextField2.getText(), jTextField3.getText()));
                JOptionPane.showMessageDialog(null, "资产类别添加成功！", "资产类别添加", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "数据库访问错误，错误原因：" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }

            getParent().remove(this);
            JPanel jpa = new TypeInfo();
            AssetsMs ms = AssetsMs.getInstance();
            ms.panel = jpa;
            ms.add(jpa);
        } else if (obj == modifyInfo) {
            try {
                //修改
                if (jTextField2.getText().equals("") || jTextField3.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "输入不能有空数据！请从新输入:");
                    return;
                }
                assetsTypeDao.modify(new AssetsType(Integer.parseInt(jTextField1.getText()), jTextField2.getText(), jTextField3.getText()));
                JOptionPane.showMessageDialog(null, "资产类别修改成功！", "资产类别修改", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "数据库访问错误，错误原因：" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
            getParent().remove(this);
            JPanel jpa = new TypeInfo();
            AssetsMs ms = AssetsMs.getInstance();
            ms.panel = jpa;
            ms.add(jpa);
        } else if (obj == deleteInfo) {

            int rowcount = tableModel.getRowCount() - 1;//getRowCount返回行数，rowcount<0代表已经没有任何行了。
            if (rowcount >= 0) {
                try {
                    //删除
                    int i = JOptionPane.showConfirmDialog(null, "确定要删除吗?", null, JOptionPane.YES_NO_CANCEL_OPTION);//单击"确定"按钮,则返回值为0.
                    if (i == 0) {
                        System.out.println("进入了删除行" + jTextField1.getText());
//                        tableModel.removeRow(jTable.getSelectedRow());
//                        tableModel.setRowCount(rowcount);//删除行比较简单，只要用DefaultTableModel的removeRow()方法即可。删除行完毕后必须重新设置行数，也就是使用DefaultTableModel的setRowCount()方法来设置当前行。
                        System.out.println("要删除的文字" + Integer.parseInt(jTextField1.getText()));
                        assetsTypeDao.delete(Integer.parseInt(jTextField1.getText()));
                    } else {
                        return;
                    }

                    JOptionPane.showMessageDialog(null, "资产类别删除成功！", "资产类别删除", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "数据库访问错误，错误原因：" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                }
                getParent().remove(this);
                JPanel jpa = new TypeInfo();
                AssetsMs ms = AssetsMs.getInstance();
                ms.panel = jpa;
                ms.add(jpa);
            }

        } else if (obj == clearInfo) { //清空
            setNull();
        }
            jTable.revalidate();
        }

    /**
     * 将文本清空
     */
    void setNull() {
        jTextField1.setText(null);
        jTextField2.setText(null);
        jTextField3.setText(null);
        jTextField2.setEditable(true);
        jTextField3.setEditable(true);
        addInfo.setEnabled(true);
        modifyInfo.setEnabled(true);
        deleteInfo.setEnabled(true);
        clearInfo.setEnabled(true);
    }

}
