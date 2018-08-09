package com.sunvalley.order.mapper;

import com.sunvalley.order.model.ShopOrder;
import com.sunvalley.order.model.ShopOrderExample;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ShopOrderMapper {
    int countByExample(ShopOrderExample example);

    int deleteByExample(ShopOrderExample example);

    @Delete({
        "delete from shop_order",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into shop_order (user_id, order_no, ",
        "order_date, order_status, ",
        "order_total,order_currency_code, ",
        "total_qty_ordered, total_item_count, ",
        "order_type, order_source, ",
        "order_email_flag, order_unpay_email_flag, ",
        "create_by, create_date, ",
        "update_by, update_date)",
        "values (#{userId,jdbcType=INTEGER}, #{orderNo,jdbcType=VARCHAR}, ",
        "#{orderDate,jdbcType=TIMESTAMP}, #{orderStatus,jdbcType=INTEGER}, ",
        "#{orderTotal,jdbcType=DECIMAL}, #{orderCurrencyCode,jdbcType=CHAR}, ",
        "#{totalQtyOrdered,jdbcType=INTEGER}, #{totalItemCount,jdbcType=INTEGER}, ",
        "#{orderType,jdbcType=VARCHAR}, #{orderSource,jdbcType=VARCHAR}, ",
        "#{orderEmailFlag,jdbcType=INTEGER}, #{orderUnpayEmailFlag,jdbcType=INTEGER}, ",
        "#{createBy,jdbcType=INTEGER}, #{createDate,jdbcType=TIMESTAMP}, ",
        "#{updateBy,jdbcType=INTEGER}, #{updateDate,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(ShopOrder record);

    int insertSelective(ShopOrder record);

    List<ShopOrder> selectByExample(ShopOrderExample example);

    @Select({
        "select",
        "id, user_id, order_no, order_date, order_status, order_total, ",
        "order_currency_code, total_qty_ordered, total_item_count, order_type, ",
        "order_source, order_email_flag, order_unpay_email_flag, create_by, create_date, ",
        "update_by, update_date",
        "from shop_order",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    ShopOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopOrder record, @Param("example") ShopOrderExample example);

    int updateByExample(@Param("record") ShopOrder record, @Param("example") ShopOrderExample example);

    int updateByPrimaryKeySelective(ShopOrder record);

    @Update({
        "update shop_order",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "order_no = #{orderNo,jdbcType=VARCHAR},",
          "order_date = #{orderDate,jdbcType=TIMESTAMP},",
          "order_status = #{orderStatus,jdbcType=INTEGER},",
          "order_total = #{orderTotal,jdbcType=DECIMAL},",
          "order_currency_code = #{orderCurrencyCode,jdbcType=CHAR},",
          "total_qty_ordered = #{totalQtyOrdered,jdbcType=INTEGER},",
          "total_item_count = #{totalItemCount,jdbcType=INTEGER},",
          "order_type = #{orderType,jdbcType=VARCHAR},",
          "order_source = #{orderSource,jdbcType=VARCHAR},",
          "order_email_flag = #{orderEmailFlag,jdbcType=INTEGER},",
          "order_unpay_email_flag = #{orderUnpayEmailFlag,jdbcType=INTEGER},",
          "create_by = #{createBy,jdbcType=INTEGER},",
          "create_date = #{createDate,jdbcType=TIMESTAMP},",
          "update_by = #{updateBy,jdbcType=INTEGER},",
          "update_date = #{updateDate,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ShopOrder record);

    List<ShopOrder> selectPage(ShopOrderExample example);

    int insertBatch(List<ShopOrder> list);
}