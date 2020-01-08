package model.vo;

import controller.AssetsTrjn;
import model.dao.AssetsTrjnDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static model.dao.DBUtil.exceuteUpdate;
import static model.dao.DBUtil.getConnection;

/**
 *
 * @author Administrator
 */
public class AssetsTrjnDaoJDBCImpl implements AssetsTrjnDao {


    //转换提日期输出格式
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public String[][] AbandonfindAll() throws Exception {

        String[][] sn = null;

        String sql = "select * from AssetsTrjn where FromAcc='可用' order by JourNo ";
        //createStatement():创建一个 Statement对象，用于将SQL语句发送到数据库。       executeQuery()：执行给定的SQL语句，该语句返回单个 ResultSet对象。
        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            //ResultSet对象保持一个光标指向其当前的数据行。 最初，光标位于第一行之前。 next方法将光标移动到下一行，并且由于在ResultSet对象中没有更多行时返回false ，因此可以在while循环中使用循环来遍历结果集。
            List<AssetsTrjn> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new AssetsTrjn(rs.getInt("JourNo"), rs.getString("FromAcc"), rs.getInt("AssetsID"), rs.getDate("RegDate"), rs.getInt("PersonID"), rs.getString("purpose"), rs.getString("Other")));

            }
            int size = list.size();
            if (size != 0) {
                sn = new String[list.size()][7];
            }
            for (int i = 0; i < size; i++) {
                sn[i][0] = list.get(i).getJourNo() + "";
                sn[i][1] = list.get(i).getFromAcc();
                sn[i][2] = list.get(i).getAssetsID() + "";
                sn[i][3] = list.get(i).getRegDate() + "";
                sn[i][4] = list.get(i).getPersonID() + "";
                sn[i][5] = list.get(i).getPurpose();
                sn[i][6] = list.get(i).getOther();
            }
        }
        return sn;
    }

    public String[][] findByAbandon() throws Exception {
            String[][] sn = null;

            String sql = "select JourNo, FromAcc, Assets.name, RegDate, person.name, purpose, assetstrjn.Other from  assetstrjn left join assets on assets.AssetsID = assetstrjn.AssetsID left join person on assetstrjn.PersonID = person.PersonID where FromAcc = '报废' order by JourNo; ";
            //createStatement():创建一个 Statement对象，用于将SQL语句发送到数据库。       executeQuery()：执行给定的SQL语句，该语句返回单个 ResultSet对象。
            try (Connection conn =getConnection();
                 Statement st = conn.createStatement();
                 ResultSet rs = st.executeQuery(sql)) {
                //ResultSet对象保持一个光标指向其当前的数据行。 最初，光标位于第一行之前。 next方法将光标移动到下一行，并且由于在ResultSet对象中没有更多行时返回false ，因此可以在while循环中使用循环来遍历结果集。
                List<AssetsTrjn> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(new AssetsTrjn(rs.getInt("JourNo"), rs.getString("FromAcc"), rs.getString("Assets.name"), rs.getDate("RegDate"), rs.getString("person.name"), rs.getString("purpose"), rs.getString("assetstrjn.Other")));

                }
                int size = list.size();
                if (size != 0) {
                    sn = new String[list.size()][7];
                }
                for (int i = 0; i < size; i++) {
                    sn[i][0] = list.get(i).getJourNo() + "";
                    sn[i][1] = list.get(i).getFromAcc();
                    sn[i][2] = list.get(i).getsAssetsID() + "";
                    sn[i][3] = list.get(i).getRegDate() + "";
                    sn[i][4] = list.get(i).getsPersonID() + "";
                    sn[i][5] = list.get(i).getPurpose();
                    sn[i][6] = list.get(i).getOther();
                }
            }
            return sn;
    }

    public String[][] findAll() throws Exception {

        String[][] sn = null;

        String sql = "select JourNo, FromAcc, Assets.name, RegDate, person.name, purpose, assetstrjn.Other from  assetstrjn left join assets on assets.AssetsID = assetstrjn.AssetsID left join person on assetstrjn.PersonID = person.PersonID where FromAcc = '可用' order by JourNo; ";
        //createStatement():创建一个 Statement对象，用于将SQL语句发送到数据库。       executeQuery()：执行给定的SQL语句，该语句返回单个 ResultSet对象。
        try (Connection conn =getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            //ResultSet对象保持一个光标指向其当前的数据行。 最初，光标位于第一行之前。 next方法将光标移动到下一行，并且由于在ResultSet对象中没有更多行时返回false ，因此可以在while循环中使用循环来遍历结果集。
            List<AssetsTrjn> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new AssetsTrjn(rs.getInt("JourNo"), rs.getString("FromAcc"), rs.getString("Assets.name"), rs.getDate("RegDate"), rs.getString("person.name"), rs.getString("purpose"), rs.getString("assetstrjn.Other")));

            }
            int size = list.size();
            if (size != 0) {
                sn = new String[list.size()][7];
            }
            for (int i = 0; i < size; i++) {
                sn[i][0] = list.get(i).getJourNo() + "";
                sn[i][1] = list.get(i).getFromAcc();
                sn[i][2] = list.get(i).getsAssetsID() + "";
                sn[i][3] = list.get(i).getRegDate() + "";
                sn[i][4] = list.get(i).getsPersonID() + "";
                sn[i][5] = list.get(i).getPurpose();
                sn[i][6] = list.get(i).getOther();
            }
        }
        return sn;
    }

    public String[][] ReceivefindAll() throws Exception {

        String[][] sn = null;

        String sql = "select JourNo, FromAcc, Assets.name, RegDate, person.name, purpose, assetstrjn.Other from  assetstrjn left join assets on assets.AssetsID = assetstrjn.AssetsID left join person on assetstrjn.PersonID = person.PersonID where FromAcc = '领用' order by JourNo; ";
        //createStatement():创建一个 Statement对象，用于将SQL语句发送到数据库。       executeQuery()：执行给定的SQL语句，该语句返回单个 ResultSet对象。
        System.out.println("领取信息查询语句为: " + sql);
        try (Connection conn =getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            //ResultSet对象保持一个光标指向其当前的数据行。 最初，光标位于第一行之前。 next方法将光标移动到下一行，并且由于在ResultSet对象中没有更多行时返回false ，因此可以在while循环中使用循环来遍历结果集。
            List<AssetsTrjn> list = new ArrayList<>();
            System.out.println(rs);
            while (rs.next()) {
                list.add(new AssetsTrjn(rs.getInt("JourNo"), rs.getString("FromAcc"), rs.getString("Assets.name"), rs.getDate("RegDate"), rs.getString("person.name"), rs.getString("purpose"), rs.getString("assetstrjn.Other")));

            }
            int size = list.size();
            if (size != 0) {
                sn = new String[list.size()][7];
            }
            for (int i = 0; i < size; i++) {
                sn[i][0] = list.get(i).getJourNo() + "";
                sn[i][1] = list.get(i).getFromAcc();
                sn[i][2] = list.get(i).getsAssetsID() + "";
                sn[i][3] = list.get(i).getRegDate() + "";
                sn[i][4] = list.get(i).getsPersonID() + "";
                sn[i][5] = list.get(i).getPurpose();
                sn[i][6] = list.get(i).getOther();
            }
        }
        return sn;
    }

    public void add1(AssetsTrjn type) throws Exception {
        String sql = "insert into AssetsTrjn (FromAcc,AssetsID,RegDate,PersonID,purpose,Other) values('领用',?,?,?,?,?)";
        System.out.println("进入执行语句为: " + sql);
        try (Connection conn =getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, type.getAssetsID());
            ps.setString(2, dateFormat.format(type.getRegDate()));
            ps.setInt(3, type.getPersonID());
            ps.setString(4, type.getPurpose());
            ps.setString(5, type.getOther());
            System.out.println(ps); //测试代码
            ps.executeUpdate();
        }
    }
    public void add2(AssetsTrjn type) throws Exception {
        String sql = "insert into AssetsTrjn (FromAcc,AssetsID,RegDate,PersonID,purpose,Other) values('可用',?,?,?,?,?)";
        try (Connection conn =getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, type.getAssetsID());
            ps.setString(2, dateFormat.format(type.getRegDate()));
            ps.setInt(3, type.getPersonID());
            ps.setString(4, type.getPurpose());
            ps.setString(5, type.getOther());
            ps.executeUpdate();
        }
    }
    public void add3(AssetsTrjn type) throws Exception {
        String sql = "insert into AssetsTrjn (FromAcc,AssetsID,RegDate,PersonID,purpose,Other) values('报废',?,?,?,?,?)";
        try (Connection conn =getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, type.getAssetsID());
            ps.setString(2, dateFormat.format(type.getRegDate()));
            ps.setInt(3, type.getPersonID());
            ps.setString(4, type.getPurpose());
            ps.setString(5, type.getOther());
            ps.executeUpdate();
        }
    }
    public void Returnadd(AssetsTrjn type) throws Exception {
        String sql = "insert into AssetsTrjn (FromAcc,AssetsID,RegDate,PersonID,purpose,Other) values('领用',?,?,?,?,?)";
        try (Connection conn =getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, type.getAssetsID());
            ps.setString(2, dateFormat.format(type.getRegDate()));
            ps.setInt(3, type.getPersonID());
            ps.setString(4, type.getPurpose());
            ps.setString(5, type.getOther());
            ps.executeUpdate();
        }
    }

    public void modify(AssetsTrjn type) throws Exception {
        String sql = "update AssetsTrjn set AssetsID=?,RegDate=?,PersonID=?,purpose=?,Other=? where JourNo=?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, type.getAssetsID());
            ps.setString(2, dateFormat.format(type.getRegDate()));
            ps.setInt(3, type.getPersonID());
            ps.setString(4, type.getPurpose());
            ps.setString(5, type.getOther());
            ps.setInt(6, type.getJourNo());
            ps.executeUpdate();
        }
    }

    public int delete(int id) throws Exception {
        String sql = "delete from AssetsTrjn where JourNo=?";
        try (Connection conn = getConnection(); ) {
            return exceuteUpdate(conn, sql, new Integer[]{id});
        }
    }




    public void ReceiveTrjn(AssetsTrjn type) throws Exception {
        String sql = "update AssetsTrjn set FromAcc='领用' where JourNo=?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, type.getJourNo());
            ps.executeUpdate();
        }
    }


    public void ReturnTrjn(AssetsTrjn type) throws Exception {
        String sql = " update AssetsTrjn set FromAcc ='可用' where JourNo=? ";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, type.getJourNo());
            ps.executeUpdate();
        }
    }

    public void AbandonTrjn(AssetsTrjn type) throws Exception {
        String sql = "update AssetsTrjn set FromAcc='报废' where JourNo=?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, type.getJourNo());
            ps.executeUpdate();
        }
    }
}
