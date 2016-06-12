package com.zizaike.trade.dao.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.zizaike.entity.trade.OrderStatus;

/**
 * 
 * ClassName: OrderStatusHandler <br/>  
 * Function: 订单状态. <br/>  
 * date: 2016年6月12日 上午10:37:31 <br/>  
 *  
 * @author snow.zhang  
 * @version   
 * @since JDK 1.7
 */
public class OrderStatusHandler extends BaseTypeHandler<OrderStatus> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, OrderStatus parameter,
        JdbcType jdbcType) throws SQLException {

      ps.setInt(i, parameter.getValue());

    }

    @Override
    public OrderStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {

      int value = rs.getInt(columnName);
      return OrderStatus.findByValue(value);
    }

    @Override
    public OrderStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {

      int value = rs.getInt(columnIndex);
      return OrderStatus.findByValue(value);
    }

    @Override
    public OrderStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {

      int value = cs.getInt(columnIndex);
      return OrderStatus.findByValue(value);
    }


}
  
