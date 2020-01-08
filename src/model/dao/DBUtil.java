package model.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 数据库连接工具类
 */
public class DBUtil {
    private static String driver; //数据库驱动字符串
    private static String url; //连接URL字符串
    private static String username; //数据库用户命
    private static String password; //用户密码
    static {
        try {
            //静态代码块，在类加载的时候执行
            init();
        }catch(IOException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * 初始化连接，从配置文件里获得
     */
    public static void init() throws IOException {
        Properties params = new Properties();
        String configFile = "database.properties";
        //配置文件路径
        //加载配置文件到输入流中
        InputStream is = DBUtil.class.getClassLoader().getResourceAsStream(configFile);
        //从输入流中读取属性列表
        params.load(is);
        //根据指定的获取对应的值
        driver = params.getProperty("driver");
        url = params.getProperty("url");
        username = params.getProperty("username");
        password = params.getProperty("password");
    };

    /**
     * 获取数据库连接对象
     */
    public static Connection getConnection() throws Exception {
        Connection conn;
        Class.forName(driver);
        conn = DriverManager.getConnection(url, username, password);
        return conn;//返回连接对象
    }

    public static int exceuteUpdate(Connection conn, String preparedSql, Object[] param) throws SQLException{
        int num;
        PreparedStatement stat = conn.prepareStatement("set foreign_key_checks = 0;");
        System.out.println(stat);
        stat.execute();
        System.out.println("进入删除");
        try (Connection connection = conn;
             PreparedStatement pstmt = connection.prepareStatement(preparedSql);
             ) {
            if(param != null) {
                for (int i = 0; i < param.length; i++) {
                    pstmt.setObject(i+1, param[i]);
                     //为预编译sql设置参数
                }
            }
            num = pstmt.executeUpdate();
            stat = connection.prepareStatement("set foreign_key_checks = 1;");
            return num;
        }
    }
}
