package impl;

import dao.BaseDao;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SunHang
 * @className: BaseDaoImpl
 * @description:
 * @createTime 2021/4/27 22:30
 */
public class BaseDaoImpl<T> implements BaseDao<T> {

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
    public boolean add(T t, List<T> ts) {
        ts.add(t);
        return true;
    }

    @Override
    public T[] findAll(List<T> ts) {
        T[] tArray = (T[]) Array.newInstance(clazz, ts.size());
        for (int i = 0; i < ts.size(); i++) {
            tArray[i] = ts.get(i);
        }
        return tArray;
    }

    @Override
    public void deleteById(String delId, List<T> ts) throws Exception {
        int index = getIndex(delId,ts,"id").get(0);
        ts.remove(index);
    }

    @Override
    public ArrayList<Integer> getIndex(String nameOrPhone, List<T> ts, String name) throws Exception {
        ArrayList<Integer> idList = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        String substring = name.substring(0, 1);
        builder.append("get");
        builder.append(name.replaceFirst(substring, substring.toUpperCase()));
        Method method = clazz.getDeclaredMethod(builder.toString());
        for (int i = 0; i < ts.size(); i++) {
            T t = ts.get(i);
            String invokeName = (String) method.invoke(t);
            if (t != null && nameOrPhone.equals(invokeName)) {
                idList.add(i);
            }
        }
        return idList;
    }
    public int getIndex(String id, List<T> ts) throws Exception {

        for (int i = 0; i < ts.size(); i++) {
            T t = ts.get(i);
            Method method = clazz.getDeclaredMethod("getId");
            String invokeId = (String) method.invoke(t);
            if (t != null && id.equals(invokeId)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void update(String updateId, T t, List<T> ts) throws Exception {
        int index = getIndex(updateId, ts);
        if(index==-1){
            System.out.println("没有找到该索引的元素");
        }else{
            ts.remove(index);
            ts.add(t);
        }
    }
}
