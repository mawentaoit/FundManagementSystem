package model.dao;

import controller.AssetsType;

import java.util.List;

public interface  AssetsTypeDao {
    /**
     * 保存资产类型
     * @param assetsType 资产类型
     *
     */
    int add(AssetsType assetsType) throws Exception;
    //根据类型名查询出TypeID
    int TypeIDByTypeName(String s) throws Exception;
    /**
     * 删除资产类型
     * @param id 资产类型编号
     */
    int delete(int id) throws Exception;

    /**
     * 更新资产类型
     * @param assetsType资产类型
     */
    int modify(AssetsType assetsType) throws Exception;
    /**
     * 获取指定资产大类的资产类型列表
     */
    List<AssetsType> findByBigType(String bType) throws  Exception;

    /**
     * 获取指定资产小类的资产类型
      */
    AssetsType findBySmallType(String sType)throws Exception;

    String [][] findAll() throws Exception;
}
