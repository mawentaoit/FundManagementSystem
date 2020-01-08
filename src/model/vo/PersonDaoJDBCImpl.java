package model.vo;

import controller.Person;
import model.dao.DBUtil;
import model.dao.PersonDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static model.dao.DBUtil.exceuteUpdate;
import static model.dao.DBUtil.getConnection;

/**
 *
 * @author lenovo
 */
public class PersonDaoJDBCImpl implements PersonDao {
    public int add(Person type) throws Exception {
//添加
        String sql = "insert into  Person(Name,Sex,Dept,Job,Other) values(?,?,?,?,?)";
        try (Connection conn = getConnection();) {
            return exceuteUpdate(conn, sql, new String[]{type.getName(),type.getSex(),type.getDept(),type.getJob(),type.getOther()});
        }
    }

    public int delete(int id) throws Exception {
//删除
        String sql = "delete from Person where PersonID=?";

        try (Connection conn = getConnection();) {

            return exceuteUpdate(conn, sql, new Integer[]{id});
        }
    }

    public int modify(Person type) throws Exception {
//修改
        String sql = "update Person set Name=?,Sex=?,Dept=?,Job=?,Other=? where PersonID=?";
        try (Connection conn = getConnection(); ) {
            return exceuteUpdate(conn, sql, new Object[]{type.getName(),type.getSex(),type.getDept(),type.getJob(),type.getOther(),type.getPersonID()});
        }}

    public String[][] findAll() throws Exception {
//查找所有
        String[][] sn = null;
        String sql = "select * from Person order by PersonID";
        System.out.println(sql);
        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            List<Person> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Person(rs.getInt("PersonID"), rs.getString("Name"), rs.getString("Sex"), rs.getString("Dept"), rs.getString("Job"), rs.getString("Other")));

            }
            int size = list.size();
            if (size != 0) {
                sn = new String[list.size()][6];
            }
            for (int i = 0; i < size; i++) {
                sn[i][0] = list.get(i).getPersonID() + "";
                sn[i][1] = list.get(i).getName();
                sn[i][2] = list.get(i).getSex();
                sn[i][3] = list.get(i).getDept();
                sn[i][4] = list.get(i).getJob();
                sn[i][5] = list.get(i).getOther();
            }
        }
        return sn;
    }


    public String[][] findPersonID(int id) throws Exception {
//根据ID查找
        String[][] sn = null;

        String sql = "SELECT * FROM Person WHERE PersonID = '"+id+"'";
        try (Connection conn = getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            List<Person> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Person(
                        rs.getInt("PersonID"),
                        rs.getString("Name"),
                        rs.getString("Sex"),
                        rs.getString("Dept"),
                        rs.getString("Job"),
                        rs.getString("Other")));
            }
            int size = list.size();
            if (size != 0) {
                sn = new String[list.size()][6];
            }
            for (int i = 0; i < size; i++) {
                sn[i][0] = list.get(i).getPersonID() + "";
                sn[i][1] = list.get(i).getName();
                sn[i][2] = list.get(i).getSex();
                sn[i][3] = list.get(i).getDept();
                sn[i][4] = list.get(i).getJob();
                sn[i][5] = list.get(i).getOther();
            }
        }
        return sn;
    }
    public String[][] findPPosition(String s) throws Exception {
//根据ID查找
        String[][] sn = null;

        String sql = "SELECT * FROM Person WHERE Job = '"+s+"'";
        try (Connection conn = getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            List<Person> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Person(
                        rs.getInt("PersonID"),
                        rs.getString("Name"),
                        rs.getString("Sex"),
                        rs.getString("Dept"),
                        rs.getString("Job"),
                        rs.getString("Other")));
            }
            int size = list.size();
            if (size != 0) {
                sn = new String[list.size()][6];
            }
            for (int i = 0; i < size; i++) {
                sn[i][0] = list.get(i).getPersonID() + "";
                sn[i][1] = list.get(i).getName();
                sn[i][2] = list.get(i).getSex();
                sn[i][3] = list.get(i).getDept();
                sn[i][4] = list.get(i).getJob();
                sn[i][5] = list.get(i).getOther();
            }
        }
        return sn;
    }
    public String[][] findPersonDepart(String s) throws Exception {
//根据ID查找
        String[][] sn = null;

        String sql = "SELECT * FROM Person WHERE Dept = '"+s+"'";
        try (Connection conn = getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            List<Person> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Person(
                        rs.getInt("PersonID"),
                        rs.getString("Name"),
                        rs.getString("Sex"),
                        rs.getString("Dept"),
                        rs.getString("Job"),
                        rs.getString("Other")));
            }
            int size = list.size();
            if (size != 0) {
                sn = new String[list.size()][6];
            }
            for (int i = 0; i < size; i++) {
                sn[i][0] = list.get(i).getPersonID() + "";
                sn[i][1] = list.get(i).getName();
                sn[i][2] = list.get(i).getSex();
                sn[i][3] = list.get(i).getDept();
                sn[i][4] = list.get(i).getJob();
                sn[i][5] = list.get(i).getOther();
            }
        }
        return sn;
    }
}
