package java;
/**
 * @className: MapTest
 * @description:   
 * @author SunHang
 * @createTime 2021/4/2 21:24
 */

import java.util.*;

/**
 * Map实现类的层级结构：
 * |------Map:双列数据，存储key-value对的数据
 *          |------HashMap：作为Map的主要实现类：线程不安全
 *                          可以存储null的key或者value
 *              |-----LinkedHashMap:可以保证在遍历map元素时，可以
 *              按照添加的顺序实现遍历，原因：在原有的HashMap底层结构基础上，
 *              添加了一对指针，指向前一个和后一个元素。对于频繁的遍历操作
 *              则该类的效率高于HashMap
 *          |-------TreeMap：按照添加的key-value对进行排序，实现排序遍历
 *           根据key值进行排序。底层使用红黑树。
 *          |-------Hashtable：作为古老的实现类；线程安全
 *                              不可以存储null的key或者value
 *              |------Properties：常用来处理配置文件。key和value都是String类型
 *
 * 1. HashMap的底层实现原理？
 * 2. HashMap和Hashtable的异同
 * 3. CurrentHashMap与Hashtable的异同？
 * 二：Map实现类的理解
 * 所有的key都是用Set进行保存，无序且不可重复---->
 * key---->所在的类需要重写equals()和hashCode()(以HashMap为例)
 * values，无序，可重复，使用Collection存储所有的value
 * values-----> 所在的类要重写equals()方法
 * Entry：一个个的键值对，key-value对，无序且不可重复
 *        使用Set存储所有的entry
 * 三：HashMap的底层实现原理？
 * jdk7-为例：HashMap map = new HashMap();
 * 在实例化以后，底层创建了一个长度为16的一维数组Entry[] table.
 * 可能已经执行多次put运算了
 * map.put(key1,value1)：
 * 首先调用key1所在类的hashCode()方法计算key1的哈希值，此哈希值经过某种
 * 算法计算以后，得到在Entry数组中的存放位置。
 *      如果此位置上的数据为空，则此时的(entry)key1-value1添加成功
 *      如果此位置上的数据不为空(意味着该位置上存在一个或多个数据(以链表形式存在)
 *      比较key1和已经存在的一个或者多个数据的哈希值
 *          如果key1的哈希与已经值存在的数据的哈希值都不相同，此时key1-value1添加成功
 *          如果key1的哈希值和已存在的某一个数据(key2-value2)的哈希值相同，则继续比较equals方法
 *          调用key1所在类的equals()方法
 *              如果equals()返回false：此时key1-value1添加成功
 *              如果equals()返回true：使用value1替换相同key下的value2值
 *  *  在不断的添加过程中，会涉及到扩容问题，默认的扩容方式：扩容为原来的2倍
 *  *  并就原来的数据复制过来。
 *  jdk1.8+ 在实现上的不同
 *  1. new HashMap()底层没有创建一个长度为16的数组
 *  2. jdk8底层的数组是：Node[]，而非Entry[]
 *  3. 首次调用put()方法时，底层创建长度为16的数组
 *  4. jdk7底层结构只有：数组+链表，jdk8底层结构：数组+链表+红黑树
 *  5. 当数组的某一个索引位置上的元素以链表上的所有数据形式存在的数据的个数>8
 *  *     且当前数组的长度>64时，此时此索引位置改为使用红黑树存储。
 */

public class MapTest {
    public static void main(String[] args) {
//        test1();
        test2();
    }
    public static void test1(){
        Map map = new HashMap();
        map.put("AA",123);
        map.put(45,123);
        map.put("BB",22);
        // Set keySet() ：返回所有key构成的Set集合
        // Collection values() :返回所有value构成的Collections集合
        // Set entrySet(): 返回所有key-value所构成的Set集合。
        // 遍历所有的key集合，keySet()
        Set set = map.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next()+"\t");
        }
        // 遍历所有的values()
        System.out.println();
        Collection values = map.values();
        for(Object obj : values){
            System.out.print(obj+"\t");
        }
        System.out.println();
        //遍历所有的key-values entrySet()
        Set entrySet = map.entrySet();
        Iterator iterator1 = entrySet.iterator();
        while (iterator1.hasNext()){
            Object obj = iterator1.next();
            // entrySet集合中的元素都是entry
            Map.Entry entry = (Map.Entry) obj;
            System.out.println(entry.getKey()+"---->"+entry.getValue());
        }
        // 方式二
        System.out.println("-------------");
        Set set1 = map.keySet();
        Iterator iterator2 = set1.iterator();
        while (iterator2.hasNext()){
            Object key = iterator2.next();
            Object value = map.get(key);
            System.out.println(key+"---->"+value);
        }
    }
    protected static void test2(){
        TreeMap map = new TreeMap();
//        map.put();
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(23);
        arrayList.add(33);
        arrayList.add(43);
        // 地址值一致
        ArrayList arrayList1 = new ArrayList(arrayList);
        System.out.println(Arrays.toString(arrayList1.toArray()));
        arrayList1.trimToSize();
//        arrayList1.remove(Integer.valueOf(43));
        System.out.println(Arrays.toString(arrayList1.toArray()));
        Collection collection1 = new LinkedList();
        Collection collection2 = new HashSet();
        Collection collection3 = new Vector();
//        Collection collection4 = new HashMap();

    }
}
