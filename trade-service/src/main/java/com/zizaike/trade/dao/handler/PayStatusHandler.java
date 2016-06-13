package com.zizaike.trade.dao.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.zizaike.entity.trade.PayStatus;

/**
 * 
 * ClassName: PayStatusHandler <br/>  
 * Function: 订单状态. <br/>  
 * date: 2016年6月12日 上午10:37:31 <br/>  
 *  
 * @author snow.zhang  
 * @version   
 * @since JDK 1.7
 */
public class PayStatusHandler extends BaseTypeHandler<PayStatus> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, PayStatus parameter,
        JdbcType jdbcType) throws SQLException {

      ps.setInt(i, parameter.getValue());

    }

    @Override
    public PayStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {

      int value = rs.getInt(columnName);
      return PayStatus.findByValue(value);
    }

    @Override
    public PayStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {

      int value = rs.getInt(columnIndex);
      return PayStatus.findByValue(value);
    }

    @Override
    public PayStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {

      int value = cs.getInt(columnIndex);
      return PayStatus.findByValue(value);
    }


}
  
