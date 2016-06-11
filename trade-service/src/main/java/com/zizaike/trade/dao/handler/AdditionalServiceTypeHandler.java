package com.zizaike.trade.dao.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import com.zizaike.entity.commodity.AdditionalServiceType;

/**
 * 
 * ClassName: AdditionalServiceTypeHandler <br/>  
 * Function: 特色服务handler. <br/>  
 * date: 2016年6月7日 下午4:17:33 <br/>  
 *  
 * @author snow.zhang  
 * @version   
 * @since JDK 1.7
 */
public class AdditionalServiceTypeHandler extends BaseTypeHandler<AdditionalServiceType> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, AdditionalServiceType parameter,
        JdbcType jdbcType) throws SQLException {

      ps.setString(i, parameter.getValue());

    }

    @Override
    public AdditionalServiceType getNullableResult(ResultSet rs, String columnName) throws SQLException {

      String value = rs.getString(columnName);
      return AdditionalServiceType.findByValue(value);
    }

    @Override
    public AdditionalServiceType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {

      String value = rs.getString(columnIndex);
      return AdditionalServiceType.findByValue(value);
    }

    @Override
    public AdditionalServiceType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {

      String value = cs.getString(columnIndex);
      return AdditionalServiceType.findByValue(value);
    }


}
  
