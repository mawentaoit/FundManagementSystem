package view;

import model.dao.AssetsDao;
import model.vo.AssetsDaoJDBCImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AssSelectIDInfo extends JPanel implements ActionListener {
    AssetsDao assetsDao;
    Container contentPane;
    //定义所用的面板
    JPanel Panel = new JPanel();

    //框架的大小
    Dimension faceSize = new Dimension(400, 400);

    //定义图形界面元素
    JLabel jLabel = new JLabel();
    JTextField jTextField = new JTextField(15);
    JButton selectInfo = new JButton();

    public AssSelectIDInfo(){
        System.out.println("进入编号查询界面");
        assetsDao = new AssetsDaoJDBCImpl();
        //设置框架的大小
        this.setSize(faceSize);
        try {

            Init();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "窗口初始化错误，错误原因：" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        Container contentPane = null;
//        contentPane = this.getContentPane();
        setLayout(new BorderLayout());
        String[] colName = {"资产编号", "资产名称", "所属类型", "型号", "价格", "购买日期", "状态", "备注"};
        String[][] colValue = null;
        try {
            //查询
            colValue = assetsDao.findByAssetsID(Integer.parseInt(jTextField.getText()));
            JOptionPane.showMessageDialog(null, "资产信息查询成功！", "资产类别添加", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "数据库访问错误，错误原因：" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
        if (colValue == null) {
            colValue = new String[1][8];
        }
        JTable jTable = new JTable(colValue, colName);
        jTable.setPreferredScrollableViewportSize(new Dimension(1300, 600));
        jTable.setFont(new Font("Menu.font", Font.PLAIN, 16));
        jTable.getTableHeader().setFont(new Font("Dialog", 0, 19));
        JScrollPane jScrollPane1 = new JScrollPane(jTable);
//        jScrollPane1.setPreferredSize(new Dimension(800, 300));

        Panel.add(jScrollPane1);
        add(Panel, BorderLayout.NORTH);
        jTable.revalidate();
        jLabel.setVisible(false);
        jTextField.setVisible(false);
        selectInfo.setVisible(false);
        setVisible(true);
    }


    private void Init() {
//        setLayout(new BorderLayout());

        jLabel.setText("编号");
        jLabel.setFont(new Font("Dialog", 0, 16));
        Panel.add(jLabel);
        Panel.add(jTextField);
        add(Panel, BorderLayout.CENTER);
        jTextField.setEditable(true);

        selectInfo.setText("查询");
        selectInfo.setFont(new Font("Dialog", 0, 16));
        Panel.add(selectInfo);

        add(selectInfo, BorderLayout.SOUTH);

        //添加事件侦听
        selectInfo.addActionListener(this);

        selectInfo.setEnabled(true);

    }
}
