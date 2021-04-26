package imple;

import dao.BaseDAO;
import util.JDBCUtils;
import util.Utils;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SunHang
 * @className: BaseDAOImpl
 * @description: baseDAO的实现类，考虑事务
 * @createTime 2021/4/18 20:06
 */
public class BaseDAOImpl<T> implements BaseDAO<T> {
    private PreparedStatement ps;
    private ResultSet rs;
    // 获取子类的类对象的泛型 子类对象创建时候调用
    private Class<T> clazz;

    {
        // 在子类创建对象时，子类要加载super(),并加载父类的静态代码块
        // 此时，调用子类的父类的泛型参数列表(this指的是子类)，并给父类的泛型参数赋值
        // 子类通过继承可以使用该对象
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        // 将Type类型强转为ParameterizedType
        ParameterizedType paramType = (ParameterizedType) genericSuperclass;
        // 获取父类中的泛型参数
        Type[] typeArguments = paramType.getActualTypeArguments();
        // 泛型的第一个参数，因为父类只有一个泛型
        // 强转为当前调用的子类的对象
        clazz = (Class<T>) typeArguments[0];
    }

    @Override
    public int update(Connection conn, String sql, Object... args) {

        try {
            ps = conn.prepareStatement(sql);
            // 填充占位符
//            for (int i = 0; i < args.length; i++) {
//                System.out.println(Integer.parseInt(args[i].toString()));
//            }
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            // 执行 返回的是修改的次数
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public T getInstance(Connection conn, String sql, Object... args) {
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            // 执行，获取得到的结果集
            rs = ps.executeQuery();
            // 获取结果集中的元数据
            ResultSetMetaData metaData = rs.getMetaData();
            // 获取结果集中的列数
            int columnCount = metaData.getColumnCount();

            if (rs.next()) {
                // 新建一个对象
                T t = clazz.newInstance();
                // 处理结果集中的数据，将数据赋值给一个对象
                for (int i = 0; i < columnCount; i++) {
                    // 获取列名----->别名 先全部转为小写字母
                    String columnLabel = metaData.getColumnLabel(i + 1).toLowerCase();
                    columnLabel = Utils.toJavaField(columnLabel);
                    // 获取列值
                    // 通过反射将获取到的列值给对象的属性赋值
                    Field field = clazz.getDeclaredField(columnLabel);
                    // 修改权限，可以对私有的属性赋值
                    field.setAccessible(true);
                    Class<?> type = clazz.getDeclaredField(columnLabel).getType();
                    if (type.isAssignableFrom(String.class)) {
                        field.set(t, rs.getString(i + 1));
                    } else if (type.isAssignableFrom(int.class) || type.isAssignableFrom(Integer.class)) {
                        field.set(t, rs.getInt(i + 1));
                    }else if(type.isAssignableFrom(Boolean.class) || type.isAssignableFrom(boolean.class)){
                        field.set(t, rs.getBoolean(i + 1));
                    } else if (type.isAssignableFrom(Date.class)) {
                        field.set(t, rs.getTimestamp(i + 1));
//                    field.set(t, (type)columnValue);
                    }else if (type.isAssignableFrom(BigDecimal.class)) {
                        field.set(t, rs.getBigDecimal(i + 1));
//                    field.set(t, (type)columnValue);
                    }else if (type.isAssignableFrom(float.class)|| type.isAssignableFrom(Float.class)) {
                        field.set(t, rs.getFloat(i + 1));
//                    field.set(t, (type)columnValue);
                    }else if (type.isAssignableFrom(byte.class)|| type.isAssignableFrom(Byte.class)) {
                        field.set(t, rs.getByte(i + 1));
//                    field.set(t, (type)columnValue);
                    }
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;
    }

    @Override
    public List<T> getForList(Connection conn, String sql, Object... args) {

        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            // 执行，获取得到的结果集
            rs = ps.executeQuery();
            // 获取结果集中的元数据
            ResultSetMetaData metaData = rs.getMetaData();
            // 获取结果集中的列数
            int columnCount = metaData.getColumnCount();
            // 创建集合对象
            ArrayList<T> list = new ArrayList<T>();
            while (rs.next()) {
                // 新建一个对象
                T t = clazz.newInstance();
                // 处理结果集中的数据，将数据赋值给一个对象
                for (int i = 0; i < columnCount; i++) {
                    // 获取列名----->别名 保证别名和对象的属性名一致
                    String columnLabel = metaData.getColumnLabel(i + 1).toLowerCase();
                    // 获取列值
//                    Object columnValue = rs.getObject(i + 1);
                    // 通过反射将获取到的列值给对象的属性赋值
                    columnLabel = Utils.toJavaField(columnLabel);
                    Field field = clazz.getDeclaredField(columnLabel);
                    // 修改权限，可以对私有的属性赋值
                    field.setAccessible(true);
                    Class<?> type = clazz.getDeclaredField(columnLabel).getType();
                    if (type.isAssignableFrom(String.class)) {
                        field.set(t, rs.getString(i + 1));
                    }else if(type.isAssignableFrom(byte.class)||type.isAssignableFrom(Byte.class)){
                        field.set(t, rs.getByte(i + 1));
                    }else if(type.isAssignableFrom(BigDecimal.class)){
                        field.set(t, rs.getBigDecimal(i + 1));
                    } else if (type.isAssignableFrom(int.class) || type.isAssignableFrom(Integer.class)) {
                        field.set(t, rs.getInt(i + 1));
                    } else if (type.isAssignableFrom(Date.class)) {
                        field.set(t, rs.getDate(i + 1));
//                    field.set(t, (type)columnValue);
                    }else if (type.isAssignableFrom(float.class)|| type.isAssignableFrom(Float.class)) {
                        field.set(t, rs.getFloat(i + 1));
//                    field.set(t, (type)columnValue);
                    }else if (type.isAssignableFrom(byte.class)|| type.isAssignableFrom(Byte.class)) {
                        field.set(t, rs.getByte(i + 1));
//                    field.set(t, (type)columnValue);
                    }
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;
    }

    @Override
    public <E> E getValue(Connection conn, String sql, Object... args) {

        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);

            }

            rs = ps.executeQuery();
            if (rs.next()) {
                return (E) rs.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;
    }
}
