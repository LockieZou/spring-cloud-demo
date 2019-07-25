package com.lockie.product.mapper;

import com.lockie.product.model.ShopProduct;
import com.lockie.product.model.ShopProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopProductMapper {
    long countByExample(ShopProductExample example);

    int deleteByExample(ShopProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopProduct record);

    int insertSelective(ShopProduct record);

    List<ShopProduct> selectByExampleWithBLOBs(ShopProductExample example);

    List<ShopProduct> selectByExample(ShopProductExample example);

    ShopProduct selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopProduct record, @Param("example") ShopProductExample example);

    int updateByExampleWithBLOBs(@Param("record") ShopProduct record, @Param("example") ShopProductExample example);

    int updateByExample(@Param("record") ShopProduct record, @Param("example") ShopProductExample example);

    int updateByPrimaryKeySelective(ShopProduct record);

    int updateByPrimaryKeyWithBLOBs(ShopProduct record);

    int updateByPrimaryKey(ShopProduct record);
}