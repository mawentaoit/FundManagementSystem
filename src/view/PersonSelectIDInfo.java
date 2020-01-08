package view;

import model.dao.PersonDao;
import model.vo.PersonDaoJDBCImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonSelectIDInfo extends JPanel implements ActionListener {
    PersonDao personDao;
    Container contentPane;
    //定义所用的面板
    JPanel Panel = new JPanel();

    //框架的大小
    Dimension faceSize = new Dimension(400, 400);

    //定义图形界面元素
    JLabel jLabel = new JLabel();
    JTextField jTextField = new JTextField(15);
    JButton selectInfo = new JButton();

    public PersonSelectIDInfo(){
        personDao = new PersonDaoJDBCImpl();
        try {
            Init();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "窗口初始化错误，错误原因：" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] colName = {"人员编号", "姓名", "性别", "部门", "职位", "其他"};
        String[][] colValue = null;
        try {
            //查询
            colValue = personDao.findPersonID(Integer.parseInt(jTextField.getText()));
            JOptionPane.showMessageDialog(null, "人员信息查询成功！", "人员信息显示", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "数据库访问错误，错误原因：" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
        if (colValue == null) {
            colValue = new String[1][6];
        }
        JTable jTable = new JTable(colValue, colName);
        jTable.setPreferredScrollableViewportSize(new Dimension(1300, 600));
        jTable.setFont(new Font("Menu.font", Font.PLAIN, 16));
        jTable.getTableHeader().setFont(new Font("Dialog", 0, 19));
        JScrollPane jScrollPane1 = new JScrollPane(jTable);
//        jScrollPane1.setPreferredSize(new Dimension(600, 300));

        Panel.add(jScrollPane1);
        add(Panel, BorderLayout.NORTH);
//        this.pack();
        jLabel.setVisible(false);
        jTextField.setVisible(false);
        selectInfo.setVisible(false);
        setVisible(true);

    }


    private void Init() {
        setLayout(new BorderLayout());
        jLabel.setText("编号");
        jLabel.setFont(new Font("Dialog", 0, 16));
        Panel.add(jLabel);
        Panel.add(jTextField);
        add(Panel, BorderLayout.CENTER);
        jTextField.setEditable(true);
        selectInfo.setText("查询");
        selectInfo.setFont(new Font("Dialog", 0, 16));
        Panel.add(selectInfo);

//        add(selectInfo, BorderLayout.SOUTH);

        //添加事件侦听
        selectInfo.addActionListener(this);
        selectInfo.setEnabled(true);

    }

}