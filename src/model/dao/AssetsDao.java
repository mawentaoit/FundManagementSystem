package model.dao;

import controller.Assets;

public interface AssetsDao {
    public String[][] findAll() throws Exception;
    //查找所有
    public void add(Assets type) throws Exception ;
    //添加
    public String[][] findByAssetsID(int id) throws Exception;
    //根据ID查找
    public void modify(Assets type) throws Exception;
    //修改
    public int delete(int id) throws Exception ;
    //根据TypeID查询
    public String[][] findByAssetsTypeID(int id) throws Exception;
    //删除
    public String[][] findAllUsing() throws Exception; //查找状态为领用的
    public void modifyUsing(Assets type) throws Exception;
    public String[][] findAllUsingByType() throws Exception ; //查找状态为可用的
    public String[][] findOverTypeID() throws  Exception; //连类别表进行连表查询
    public String[][] findAllUsingOverTypeID() throws Exception;
    public String[][] findAllUsedOverTypeID() throws Exception;
}
