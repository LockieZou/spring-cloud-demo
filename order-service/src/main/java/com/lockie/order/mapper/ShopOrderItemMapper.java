package com.lockie.order.mapper;

import com.lockie.order.model.ShopOrderItem;
import com.lockie.order.model.ShopOrderItemExample;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ShopOrderItemMapper {
    int countByExample(ShopOrderItemExample example);

    int deleteByExample(ShopOrderItemExample example);

    @Delete({
        "delete from shop_order_item",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into shop_order_item (order_id, product_id, ",
        "qty_ordered, price, ",
        " total_price, ",
        "create_by, ",
        "create_date, update_by, ",
        "update_date)",
        "values (#{orderId,jdbcType=INTEGER}, #{productId,jdbcType=INTEGER}, ",
        "#{qtyOrdered,jdbcType=INTEGER}, #{price,jdbcType=DECIMAL}, ",
        " #{totalPrice,jdbcType=DECIMAL}, ",
        "#{createBy,jdbcType=INTEGER}, ",
        "#{createDate,jdbcType=TIMESTAMP}, #{updateBy,jdbcType=INTEGER}, ",
        "#{updateDate,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(ShopOrderItem record);

    int insertSelective(ShopOrderItem record);

    List<ShopOrderItem> selectByExample(ShopOrderItemExample example);

    @Select({
        "select",
        "id, order_id, product_id, qty_ordered, price,  total_price,  ",
        " create_by, create_date, update_by, update_date",
        "from shop_order_item",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    ShopOrderItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopOrderItem record, @Param("example") ShopOrderItemExample example);

    int updateByExample(@Param("record") ShopOrderItem record, @Param("example") ShopOrderItemExample example);

    int updateByPrimaryKeySelective(ShopOrderItem record);

    @Update({
        "update shop_order_item",
        "set order_id = #{orderId,jdbcType=INTEGER},",
          "product_id = #{productId,jdbcType=INTEGER},",
          "qty_ordered = #{qtyOrdered,jdbcType=INTEGER},",
          "price = #{price,jdbcType=DECIMAL},",
          "total_price = #{totalPrice,jdbcType=DECIMAL},",
          "create_by = #{createBy,jdbcType=INTEGER},",
          "create_date = #{createDate,jdbcType=TIMESTAMP},",
          "update_by = #{updateBy,jdbcType=INTEGER},",
          "update_date = #{updateDate,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ShopOrderItem record);

    List<ShopOrderItem> selectPage(ShopOrderItemExample example);

    int insertBatch(List<ShopOrderItem> list);
}