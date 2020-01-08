package model.vo;

import controller.Assets;
import model.dao.AssetsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static model.dao.DBUtil.exceuteUpdate;
import static model.dao.DBUtil.getConnection;

public class AssetsDaoJDBCImpl implements AssetsDao {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public String[][] findAll() throws Exception {
//查找
        String[][] sn = null;

        String sql = "select * from Assets order by AssetsID";
        try (Connection conn = getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            List<Assets> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Assets(rs.getInt("AssetsID"), rs.getString("name"),rs.getString("TypeID"), rs.getString("Model"), rs.getString("Price"), rs.getDate("BuyDate"), rs.getString("Status"), rs.getString("Other")));

            }
            int size = list.size();
            if (size != 0) {
                sn = new String[list.size()][8];
            }
            for (int i = 0; i < size; i++) {
                sn[i][0] = list.get(i).getAssetsID() + "";
                sn[i][1] = list.get(i).getname();
                sn[i][2] = list.get(i).getTypeID()+"";
                sn[i][3] = list.get(i).getModel();
                sn[i][4] = list.get(i).getPrice();
                sn[i][5] = list.get(i).getBuyDate()+"";
                sn[i][6] = list.get(i).getStatus();
                sn[i][7] = list.get(i).getOther();

            }

        }
        return sn;
    }

    //把资源表和资源表类型进行联表查询
    public String[][] findOverTypeID() throws Exception{
        String[][] sn = null;
        String sql = "select AssetsID, name, assetstype.B_Type,Model, Price, BuyDate, Status,Other from  assets, assetstype where assets.TypeID=assetstype.TypeID ;";
        try (Connection conn = getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            List<Assets> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Assets(
                        rs.getInt("AssetsID"),
                        rs.getString("name"),
                        rs.getString("B_Type"),
                        rs.getString("Model"),
                        rs.getString("Price"),
                        rs.getDate("BuyDate"),
                        rs.getString("Status"),
                        rs.getString("Other")));
            }
            int size = list.size();
            if (size != 0) {
                sn = new String[list.size()][8];
            }
            for (int i = 0; i < size; i++) {
                sn[i][0] = list.get(i).getAssetsID() + "";
                sn[i][1] = list.get(i).getname();
                sn[i][2] = list.get(i).getTypeID() + "";
                sn[i][3] = list.get(i).getModel();
                sn[i][4] = list.get(i).getPrice();
                sn[i][5] = list.get(i).getBuyDate()+ "";
                sn[i][6] = list.get(i).getStatus();
                sn[i][7] = list.get(i).getOther();
            }

        }
        return sn;
    }
    //把可以归还的数据查询出来
    public String[][] findAllUsedOverTypeID() throws Exception{
        String[][] sn = null;

        String sql = "select AssetsID, name, assetstype.B_Type,Model, Price, BuyDate, Status,Other from  assets, assetstype where assets.TypeID=assetstype.TypeID and Status = '领用' order by AssetsID";
        try (Connection conn = getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            List<Assets> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Assets(
                        rs.getInt("AssetsID"),
                        rs.getString("name"),
                        rs.getString("B_Type"),
                        rs.getString("Model"),
                        rs.getString("Price"),
                        rs.getDate("BuyDate"),
                        rs.getString("Status"),
                        rs.getString("Other")));
            }
            int size = list.size();
            if (size != 0) {
                sn = new String[list.size()][8];
            }
            for (int i = 0; i < size; i++) {
                sn[i][0] = list.get(i).getAssetsID() + "";
                sn[i][1] = list.get(i).getname();
                sn[i][2] = list.get(i).getTypeID() + "";
                sn[i][3] = list.get(i).getModel();
                sn[i][4] = list.get(i).getPrice();
                sn[i][5] = list.get(i).getBuyDate()+ "";
                sn[i][6] = list.get(i).getStatus();
                sn[i][7] = list.get(i).getOther();
            }

        }
        return sn;
    }
    //把可以领用的数据查询出来
    public String[][] findAllUsingOverTypeID() throws Exception {
        String[][] sn = null;

        String sql = "select AssetsID, name, assetstype.B_Type,Model, Price, BuyDate, Status,Other from  assets, assetstype where assets.TypeID=assetstype.TypeID and Status = '可用' order by AssetsID";
        try (Connection conn = getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            List<Assets> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Assets(
                        rs.getInt("AssetsID"),
                        rs.getString("name"),
                        rs.getString("B_Type"),
                        rs.getString("Model"),
                        rs.getString("Price"),
                        rs.getDate("BuyDate"),
                        rs.getString("Status"),
                        rs.getString("Other")));
            }
            int size = list.size();
            if (size != 0) {
                sn = new String[list.size()][8];
            }
            for (int i = 0; i < size; i++) {
                sn[i][0] = list.get(i).getAssetsID() + "";
                sn[i][1] = list.get(i).getname();
                sn[i][2] = list.get(i).getTypeID() + "";
                sn[i][3] = list.get(i).getModel();
                sn[i][4] = list.get(i).getPrice();
                sn[i][5] = list.get(i).getBuyDate()+ "";
                sn[i][6] = list.get(i).getStatus();
                sn[i][7] = list.get(i).getOther();
            }

        }
        return sn;
    }
    public String[][] findAllUsing() throws Exception {
//查找状态为可用的
        String[][] sn = null;

        String sql = "select * from Assets where Status = '可用' order by AssetsID";
        try (Connection conn = getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            List<Assets> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Assets(
                        rs.getInt("AssetsID"),
                        rs.getString("name"),
                        rs.getString("TypeID"),
                        rs.getString("Model"),
                        rs.getString("Price"),
                        rs.getDate("BuyDate"),
                        rs.getString("Status"),
                        rs.getString("Other")));
            }
            int size = list.size();
            if (size != 0) {
                sn = new String[list.size()][8];
            }
            for (int i = 0; i < size; i++) {
                sn[i][0] = list.get(i).getAssetsID() + "";
                sn[i][1] = list.get(i).getname();
                sn[i][2] = list.get(i).getTypeID() + "";
                sn[i][3] = list.get(i).getModel();
                sn[i][4] = list.get(i).getPrice();
                sn[i][5] = list.get(i).getBuyDate()+ "";
                sn[i][6] = list.get(i).getStatus();
                sn[i][7] = list.get(i).getOther();
            }

        }
        return sn;
    }
    //把状态为可用的查找出来,并联表查询类型
    public String[][] findAllUsingByType() throws Exception {
        String[][] sn = null;

//        String sql = "select * from Assets where Status = '领用' order by AssetsID";
        String sql = "select AssetsID, name, assetstype.B_Type,Model, Price, BuyDate, Status,Other from  assets, assetstype where assets.TypeID=assetstype.TypeID and Status = '可用' order by AssetsID";
        try (Connection conn = getConnection(); Statement st = conn.createStatement();ResultSet rs = st.executeQuery(sql)) {
            List<Assets> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Assets(
                        rs.getInt("AssetsID"),
                        rs.getString("name"),
                        rs.getString("B_Type"),
                        rs.getString("Model"),
                        rs.getString("Price"),
                        rs.getDate("BuyDate"),
                        rs.getString("Status"),
                        rs.getString("Other")));
            }
            int size = list.size();
            if (size != 0) {
                sn = new String[list.size()][8];
            }
            for (int i = 0; i < size; i++) {
                sn[i][0] = list.get(i).getAssetsID() + "";
                sn[i][1] = list.get(i).getname();
                sn[i][2] = list.get(i).getTypeID() + "";
                sn[i][3] = list.get(i).getModel();
                sn[i][4] = list.get(i).getPrice();
                sn[i][5] = list.get(i).getBuyDate()+ "";
                sn[i][6] = list.get(i).getStatus();
                sn[i][7] = list.get(i).getOther();
            }

        }
        return sn;
    }
    public void add(Assets type) throws Exception {
        //添加

        String sql = "insert into  Assets(name,TypeID,Model,Price,BuyDate,Status,Other) values(?,?,?,?,?,?,?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, type.getname());
            ps.setString(2, type.getTypeID());
            ps.setString(3, type.getModel());
            ps.setString(4, type.getPrice());
            ps.setString(5, dateFormat.format(type.getBuyDate()));
            ps.setString(6, type.getStatus());
            ps.setString(7, type.getOther());
            ps.executeUpdate();
        }
    }
    public String[][] findByAssetsID(int id) throws Exception {

        String[][] sn = null;

        String sql = "select AssetsID, name, assetstype.B_Type,Model, Price, BuyDate, Status,Other from  assets, assetstype where assets.TypeID=assetstype.TypeID and assets.AssetsID ='"+id+"'";
        try (Connection conn = getConnection(); Statement st = conn.createStatement();ResultSet rs = st.executeQuery(sql)) {
            List<Assets> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Assets(
                        rs.getInt("AssetsID"),
                        rs.getString("name"),
                        rs.getString("B_Type"),
                        rs.getString("Model"),
                        rs.getString("Price"),
                        rs.getDate("BuyDate"),
                        rs.getString("Status"),
                        rs.getString("Other")));
            }
            int size = list.size();
            if (size != 0) {
                sn = new String[list.size()][8];
            }
            for (int i = 0; i < size; i++) {
                sn[i][0] = list.get(i).getAssetsID() + "";
                sn[i][1] = list.get(i).getname();
                sn[i][2] = list.get(i).getTypeID() + "";
                sn[i][3] = list.get(i).getModel();
                sn[i][4] = list.get(i).getPrice();
                sn[i][5] = list.get(i).getBuyDate()+ "";
                sn[i][6] = list.get(i).getStatus();
                sn[i][7] = list.get(i).getOther();
            }

        }
        return sn;
    }

    public String[][] findByAssetsTypeID(int id) throws Exception {

        String[][] sn = null;

        String sql = "select AssetsID, name, assetstype.B_Type,Model, Price, BuyDate, Status,Other from  assets, assetstype where assets.TypeID=assetstype.TypeID and assets.TypeID ='"+id+"'";
        try (Connection conn = getConnection(); Statement st = conn.createStatement();ResultSet rs = st.executeQuery(sql)) {
            List<Assets> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Assets(
                        rs.getInt("AssetsID"),
                        rs.getString("name"),
                        rs.getString("B_Type"),
                        rs.getString("Model"),
                        rs.getString("Price"),
                        rs.getDate("BuyDate"),
                        rs.getString("Status"),
                        rs.getString("Other")));
            }
            int size = list.size();
            if (size != 0) {
                sn = new String[list.size()][8];
            }
            for (int i = 0; i < size; i++) {
                sn[i][0] = list.get(i).getAssetsID() + "";
                sn[i][1] = list.get(i).getname();
                sn[i][2] = list.get(i).getTypeID() + "";
                sn[i][3] = list.get(i).getModel();
                sn[i][4] = list.get(i).getPrice();
                sn[i][5] = list.get(i).getBuyDate()+ "";
                sn[i][6] = list.get(i).getStatus();
                sn[i][7] = list.get(i).getOther();
            }

        }
        return sn;
    }

    public void modify(Assets type) throws Exception {
        //修改

        String sql = "update Assets  set Name=?,TypeID=?,Model=?,Price=?,Status=?,Other=? where AssetsID=?";
        try (Connection conn = getConnection();PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, type.getname());
            ps.setString(2, type.getTypeID());
            ps.setString(3, type.getModel());
            ps.setString(4, type.getPrice());
            ps.setString(5, type.getStatus());
            ps.setString(6, type.getOther());
            ps.setInt(7, type.getAssetsID());
            System.out.println(ps);
            ps.executeUpdate();
        }
    }
    public void modifyUsing(Assets type) throws Exception {
        //修改

        String sql = "update Assets  set Status=? where AssetsID=?";
        try (Connection conn = getConnection();PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, type.getStatus());
            ps.setInt(2, type.getAssetsID());
            ps.executeUpdate();
        }
    }
    public int delete(int id) throws Exception {
        //删除
        String sql = "delete from Assets where AssetsID=?";
        System.out.println("资产信息删除的编号位: " + id);
        try (Connection conn = getConnection()) {
            return exceuteUpdate(conn, sql, new Integer[]{id});
        }
    }
}

