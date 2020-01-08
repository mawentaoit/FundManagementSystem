package model.vo;

import controller.AssetsType;
import model.dao.AssetsTypeDao;
import model.dao.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static model.dao.DBUtil.exceuteUpdate;
import static model.dao.DBUtil.getConnection;

public class AssetsTypeDaoJDBCImpl implements AssetsTypeDao {

    @Override
    public int add(AssetsType assetsType) throws Exception {
        String sql = "insert into assetstype(b_type,s_type) values(?,?)";
        try (Connection conn = getConnection();) {
            return exceuteUpdate(conn, sql, new String[]{assetsType.getBigType(), assetsType.getSmallType()});
        }
    }

    @Override

    public int delete(int id) throws Exception {
        String sql = "delete from assetstype where typeid=?";
        try (Connection conn = getConnection();) {
            return exceuteUpdate(conn, sql, new Integer[]{id});
        }
    }

    @Override
    public int modify(AssetsType assetsType) throws Exception {
        String sql = "update assetstype set b_type=?,s_type=? where typeid=?";
        try (Connection conn = getConnection();) {
            return exceuteUpdate(conn, sql, new Object[]{assetsType.getBigType(), assetsType.getSmallType(), assetsType.getTypeID()});
        }
    }

    @Override
    public List<AssetsType> findByBigType(String bType) throws Exception {
        String sql = "select * from assetstype where b_type=?";
        List<AssetsType> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, bType);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    list.add(new AssetsType(rs.getInt("typeid"), rs.getString("b_type"), rs.getString("s_type")));
                }
                return list;
            }
        }

    }

    @Override
    public AssetsType findBySmallType(String sType) throws Exception {
        String sql = "select * from assetstype where s_type=?";
        AssetsType result = null;
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, sType);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    result = new AssetsType(rs.getInt("typeid"), rs.getString("b_type"), rs.getString("s_type"));
                }
                return result;
            }
        }
    }

    @Override
    public String[][] findAll() throws Exception {
        String sql = "select * from assetstype";
        List<AssetsType> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql);) {
            while (rs.next()) {
                list.add(new AssetsType(rs.getInt("typeid"), rs.getString("b_type"), rs.getString("s_type")));
            }
        }
        String[][] sn = null;
        int size = list.size();
        if (!list.isEmpty()) {
            sn = new String[list.size()][3];
        }
        for (int i = 0; i < size; i++) {
            sn[i][0] = Integer.toString(list.get(i).getTypeID());
            sn[i][1] = list.get(i).getBigType();
            sn[i][2] = list.get(i).getSmallType();
        }
        return sn;
    }

    public int TypeIDByTypeName(String bType) throws Exception {
        String sql = "select TypeID from assetstype  where B_Type =?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, bType);
            try (ResultSet rs = pst.executeQuery()) {
                while(rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

}

