package model.dao;

import controller.Person;

public interface PersonDao {
    int add(Person type) throws Exception; //添加人员信息
    int delete(int id) throws Exception; //删除人员信息
    int modify(Person type) throws Exception; //修改人员信息
    String [][] findAll() throws Exception; //获取所有的人员信息列表
    String [][] findPersonID(int id) throws Exception; //获取指定ID的人员信息
    public String[][] findPPosition(String s) throws Exception; //获取指定职位的人员信息
    public String[][] findPersonDepart(String s) throws Exception; //获取指定部门的人员信息
}