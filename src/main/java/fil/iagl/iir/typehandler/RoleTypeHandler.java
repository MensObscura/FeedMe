package fil.iagl.iir.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import fil.iagl.iir.entite.Role;

public class RoleTypeHandler implements TypeHandler<Role> {

  @Override
  public Role getResult(ResultSet rs, String param) throws SQLException {
    return Role.findById(rs.getInt(param));
  }

  @Override
  public Role getResult(ResultSet rs, int idx) throws SQLException {
    return Role.findById(rs.getInt(idx));
  }

  @Override
  public Role getResult(CallableStatement cs, int col) throws SQLException {
    return Role.findById(cs.getInt(col));
  }

  @Override
  public void setParameter(PreparedStatement ps, int paramInt, Role paramType, JdbcType jdbctype)
    throws SQLException {
    ps.setInt(paramInt, paramType.getId());
  }

}
