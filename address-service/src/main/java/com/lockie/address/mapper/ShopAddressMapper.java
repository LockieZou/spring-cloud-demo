package com.lockie.address.mapper;

import com.lockie.address.model.ShopAddressExample;
import com.lockie.address.model.ShopAddress;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ShopAddressMapper {
    int countByExample(ShopAddressExample example);

    int deleteByExample(ShopAddressExample example);

    @Delete({
        "delete from shop_address",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into shop_address (order_id, email, ",
        "first_name, last_name, ",
        "postcode, fax, telephone, ",
        "country_id, region_id, ",
        "city, address1, ",
        "address2, create_by, ",
        "create_date, update_by, ",
        "update_date)",
        "values (#{orderId,jdbcType=INTEGER}, #{email,jdbcType=VARCHAR}, ",
        "#{firstName,jdbcType=VARCHAR}, #{lastName,jdbcType=VARCHAR}, ",
        "#{postcode,jdbcType=VARCHAR}, #{fax,jdbcType=VARCHAR}, #{telephone,jdbcType=VARCHAR}, ",
        "#{countryId,jdbcType=INTEGER}, #{regionId,jdbcType=INTEGER}, ",
        "#{city,jdbcType=VARCHAR}, #{address1,jdbcType=VARCHAR}, ",
        "#{address2,jdbcType=VARCHAR}, #{createBy,jdbcType=INTEGER}, ",
        "#{createDate,jdbcType=TIMESTAMP}, #{updateBy,jdbcType=INTEGER}, ",
        "#{updateDate,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(ShopAddress record);

    int insertSelective(ShopAddress record);

    List<ShopAddress> selectByExample(ShopAddressExample example);

    @Select({
        "select",
        "id, order_id, email, first_name, last_name, postcode, fax, telephone, country_id, ",
        "region_id, city, address1, address2, create_by, create_date, update_by, update_date",
        "from shop_address",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    ShopAddress selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopAddress record, @Param("example") ShopAddressExample example);

    int updateByExample(@Param("record") ShopAddress record, @Param("example") ShopAddressExample example);

    int updateByPrimaryKeySelective(ShopAddress record);

    @Update({
        "update shop_address",
        "set order_id = #{orderId,jdbcType=INTEGER},",
          "email = #{email,jdbcType=VARCHAR},",
          "first_name = #{firstName,jdbcType=VARCHAR},",
          "last_name = #{lastName,jdbcType=VARCHAR},",
          "postcode = #{postcode,jdbcType=VARCHAR},",
          "fax = #{fax,jdbcType=VARCHAR},",
          "telephone = #{telephone,jdbcType=VARCHAR},",
          "country_id = #{countryId,jdbcType=INTEGER},",
          "region_id = #{regionId,jdbcType=INTEGER},",
          "city = #{city,jdbcType=VARCHAR},",
          "address1 = #{address1,jdbcType=VARCHAR},",
          "address2 = #{address2,jdbcType=VARCHAR},",
          "create_by = #{createBy,jdbcType=INTEGER},",
          "create_date = #{createDate,jdbcType=TIMESTAMP},",
          "update_by = #{updateBy,jdbcType=INTEGER},",
          "update_date = #{updateDate,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ShopAddress record);

    List<ShopAddress> selectPage(ShopAddressExample example);

    int insertBatch(List<ShopAddress> list);
}