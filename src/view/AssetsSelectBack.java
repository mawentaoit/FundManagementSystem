package view;

import model.dao.AssetsTrjnDao;
import model.vo.AssetsTrjnDaoJDBCImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AssetsSelectBack extends JPanel implements ActionListener {

    AssetsTrjnDao assetsTrjnDao;
    Container contentPane;
    //定义所用的面板
    JPanel upPanel = new JPanel();

    //框架的大小
    Dimension faceSize = new Dimension(700, 400);

    //定义图形界面元素
    //创建一个没有图像的JaLabel实例，标题为空字符串
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JLabel jLabel5 = new JLabel();
    JLabel jLabel6 = new JLabel();
    JLabel jLabel7 = new JLabel();
    JLabel jLabel8 = new JLabel();

    //构造一个新的空的 TextField与指定的列数。
    JTextField jTextField1 = new JTextField(15);
    JTextField jTextField2 = new JTextField(15);
    JTextField jTextField3 = new JTextField(15);
    JTextField jTextField4 = new JTextField(15);
    JTextField jTextField5 = new JTextField(15);
    JTextField jTextField6 = new JTextField(15);
    JTextField jTextField7 = new JTextField(15);
    JTextField jTextField8 = new JTextField(15);

    //定义表格
    JScrollPane jScrollPane1;
    JTable jTable;  //显示和编辑单元格的常规二维表
    ListSelectionModel listSelectionModel = null;
    String[] colName = {"编号", "操作类型", "资产编号", "归还时间", "归还人","用途","备注"};

    //网格包布局管理
    GridBagLayout girdBag = new GridBagLayout();
    GridBagConstraints girdBagCon;

    public AssetsSelectBack() {
        assetsTrjnDao = new AssetsTrjnDaoJDBCImpl();
        //设置框架的大小
        this.setSize(faceSize);
        try {
            Init();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "窗口初始化错误，错误原因：" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Init() throws Exception {
        setLayout(new BorderLayout()); //设置边界布局管理

        //中部面板的布局
        String[][] colValue = assetsTrjnDao.findAll(); //获取数据库中的值
        if (colValue == null) {
            colValue = new String[1][7];
        }
        jTable = new JTable(colValue, colName); //构造一个JTable以显示二维数组中的值colValue,列的名称为colName
        jTable.setPreferredScrollableViewportSize(new Dimension(1300, 600));
        jTable.setFont(new Font("Menu.font", Font.PLAIN, 16));
        jTable.getTableHeader().setFont(new Font("Dialog", 0, 19));
        jScrollPane1 = new JScrollPane(jTable); //为2维列表设置滚动面板
//        jScrollPane1.setPreferredSize(new Dimension(600, 400));
        upPanel.add(jScrollPane1);
        add(upPanel, BorderLayout.NORTH);
    }
    /**
     * 事件处理
     */
    public void actionPerformed(ActionEvent e) {
        getParent().remove(this);
        JPanel jpa = new AssetsManage();
        AssetsMs ms = AssetsMs.getInstance();
        ms.panel = jpa;
        ms.add(jpa);
    }
}
