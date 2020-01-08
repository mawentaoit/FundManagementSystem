package model.dao;
import static model.dao.DBUtil.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;
    //验证用户
    public boolean validateLogin(String userName, String passWord) {
        String sql = "select passWord from users where userName=?";
        boolean flag = false;
        try {
            conn = getConnection();
            System.out.println("数据库连接");
            System.out.println(conn);
            pst = conn.prepareStatement(sql);
            pst.setString(1, userName);
            System.out.println(pst);
            rs = pst.executeQuery();
            String p = new String();
            if(rs.next()) {
                p = rs.getString("passWord");
            }
            flag = passWord.equals(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    //注册用户
    public int addUser(String userName, String passWord) throws Exception {
        String sql = "insert into users(userName, passWord) values(?, ?)";
        String sql2 = "select userName from users";
        int flag = 0;
        try {
            conn = getConnection();
            pst = conn.prepareStatement(sql2);
            System.out.println(pst);
            rs = pst.executeQuery();
            List<String> userNameList = new ArrayList<>();
            while(rs.next()) {
                userNameList.add(rs.getString("userName"));
            }
            if(userNameList.contains(userName)) {
                flag=1;
            }else {
                pst=conn.prepareStatement(sql);
                System.out.println(pst);
                pst.setString(1,userName);
                pst.setString(2, passWord);;
                pst.executeUpdate();
                flag=2;
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return flag;
    }
}
