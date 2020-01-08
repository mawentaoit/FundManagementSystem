package model.dao;

import controller.AssetsTrjn;

public interface AssetsTrjnDao {

    public String[][] AbandonfindAll() throws Exception;
    //查找所有报废信息
    public String[][] findByAbandon() throws Exception;
    //查找部分报废信息
    public String[][] findAll() throws Exception;
    //查找所有归还信息
    public String[][] ReceivefindAll() throws Exception;
    //领用信息查询
    public void add1(AssetsTrjn type) throws Exception ;
    //增加领用
    public void add2(AssetsTrjn type) throws Exception;
    //增加可用
    public void add3(AssetsTrjn type) throws Exception;
    //增加废弃
    public void modify(AssetsTrjn type) throws Exception;
    //修改
    public int delete(int id) throws Exception ;
    //删除
    public void ReceiveTrjn(AssetsTrjn type) throws Exception;
    //资产领用管理
    public void ReturnTrjn(AssetsTrjn type) throws Exception;
    //资产归还管理
    public void AbandonTrjn(AssetsTrjn type) throws Exception;
    //资产报废管理
}

