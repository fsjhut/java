package imple;

import bean.MemberInfo;
import common.ServerResponse;
import dao.MemberDAO;
import util.JDBCUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MemberDAOImpl extends BaseDAOImpl<MemberInfo> implements MemberDAO<MemberInfo> {

    private String sql;
    private Connection conn = JDBCUtils.getConnection();

    @Override
    public ServerResponse addMember(MemberInfo member) {
        sql = "insert into memberinfo(name,pass,photo,phone,score,balance) values (?,?,?,?,?,?)";
        int result = super.update(conn, sql, member.getName(), member.getPass(), member.getPhoto(),
                member.getPhone(), member.getScore(), member.getBalance());
        if (result == 0) {
            return ServerResponse.error();
        }
        return ServerResponse.success();
    }

    @Override
    public ServerResponse deleteMemberById(int id) {
        sql = "delete from memberinfo where id = ?";
        int result = super.update(conn, sql, id);
        if (result == 0) {
            return ServerResponse.error();
        }
        return ServerResponse.success();
    }

    @Override
    public ServerResponse selectMemberById(int id) {
        sql = "select * from memberinfo where id = ?";
        MemberInfo memberInfo = super.getInstance(conn, sql, id);
        if (memberInfo == null) {
            return ServerResponse.error();
        }
        return ServerResponse.success(memberInfo);
    }

    @Override
    public ServerResponse selectMemberByIdAndPass(int id, String pass) {
        sql = "select * from memberinfo where id = ? and pass = ?";
        MemberInfo memberInfo = super.getInstance(conn, sql, id, pass);
        if (memberInfo == null) {
            return ServerResponse.error();
        }
        return ServerResponse.success(memberInfo);
    }

    @Override
    public ServerResponse selectAllMember() {
        sql = "select * from memberinfo";
        List<MemberInfo> goodsInfoList = super.getForList(conn, sql);
        if (goodsInfoList == null) {
            return ServerResponse.error();
        }
        return ServerResponse.success(goodsInfoList);
    }

    @Override
    public ServerResponse updateMemberById(MemberInfo member) {
        sql = "update memberinfo set name=?,pass=?,photo=?,phone=?,score=?,balance=? where id=?";
        int result = super.update(conn, sql, member.getName(), member.getPass(), member.getPhoto(),
                member.getPhone(), member.getScore(), member.getBalance(), member.getId());
        if (result == 0) {
            return ServerResponse.error();
        }
        return ServerResponse.success(selectMemberById(member.getId()));
    }

    @Override
    public int addMoney(int id, BigDecimal bigMoney) {
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            // 先查询余额，然后再把钱相加
            String sqlQuery = "select balance from memberinfo where id = ?";
            ps = conn.prepareStatement(sqlQuery);
            ps.setObject(1, id);
            resultSet = ps.executeQuery();
            resultSet.next();
            BigDecimal balance = resultSet.getBigDecimal(1);
            BigDecimal newMoney = bigMoney.add(balance);
            // 禁止自动提交 执行数据库的更新操作
            conn.setAutoCommit(false);
            String sqlUpdate = "update memberinfo set balance = ? where id=?";
            ps = conn.prepareStatement(sqlUpdate);
            ps.setObject(1, newMoney);
            ps.setObject(2, id);
            conn.commit();
            conn.setAutoCommit(true);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, resultSet);
        }
        return 0;
    }

    public int addMoney(Connection connection, int id, BigDecimal bigMoney) {
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            String sqlQuery = "select balance from memberinfo where id = ?";
            ps = connection.prepareStatement(sqlQuery);
            ps.setObject(1, id);
            resultSet = ps.executeQuery();
            resultSet.next();
            BigDecimal balance = resultSet.getBigDecimal(1);
            BigDecimal newMoney = bigMoney.add(balance);
            // 禁止自动提交 执行数据库的更新操作
            connection.setAutoCommit(false);
            String sqlUpdate = "update memberinfo set balance = ? where id=?";
            ps = connection.prepareStatement(sqlUpdate);
            ps.setObject(1, newMoney);
            ps.setObject(2, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, resultSet);
        }
        return 0;
    }
}
