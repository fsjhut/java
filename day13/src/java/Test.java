package java;

import javax.xml.transform.Source;
import java.nio.charset.Charset;
import java.text.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Consumer;

/**
 * @author SunHang
 * @className: 工具类
 * @description: 上课例子测试
 * @createTime 2021/4/1 21:01
 */
public class Test {
    public static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final DateFormat dateFormat = new SimpleDateFormat(FORMAT);
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
//        test7();
        Collection collection = new ArrayList();
        collection.add("100"); // 自动装箱
        collection.add("hellpo");
        collection.add("100");
        collection.add("100");
//        test8(collection);
        demo3(collection);
    }

    private static void demo3(Collection<String> collection) {
//        collection.removeIf(str -> Objects.equals("a", str));
//        System.out.println(collection);

        //集合转数组
        String[] str = new String[collection.size()];
        String[] str1 = collection.toArray(new String[0]);
        System.out.println(Arrays.toString(str1));


        Object[] array = collection.toArray();
        System.out.println("array:" + Arrays.toString(array));
        for (Object o : array) {
            if ("a".equals(o)) {
                String str2 = (String) o;
                str2 = str2.concat("bc");
                System.out.println(str2);
            }
        }
    }

    private static void test8(Collection<String> collection) {
        String[] str1 = collection.toArray(new String[0]);
        System.out.println(Arrays.toString(str1));
//说明：使用 toArray 带参方法，数组空间大小的 length：
//1） 等于 0，动态创建与 size 相同的数组，性能最好。
//2） 大于 0 但小于 size，重新创建大小等于 size 的数组，增加 GC 负担。
//3） 等于 size，在高并发情况下，数组创建完成之后，size 正在变大的情况下，负面影响与 2 相同。
//4） 大于 size，空间浪费，且在 size 处插入 null 值，存在 NPE 隐患。

    }

    private static void test7() {
        // 集合
        Collection collection = new ArrayList();
        collection.add(100); // 自动装箱
        collection.add("hellpo");
        collection.add(100);
        collection.add(100);
        System.out.println(Arrays.toString(collection.toArray()));
        collection.remove(100);
        System.out.println(Arrays.toString(collection.toArray()));
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            System.out.println(obj);
        }
        collection.forEach(new Consumer() {
            @Override
            public void accept(Object obj) {
                System.out.println(obj);
            }
        });
        collection.forEach(obj -> System.out.println(obj));
        collection.forEach(System.out::println);
        System.out.println(collection.size());

    }

    private static void test6() {
        // 获得解码器 私有的构造器
        // 只可以通过公有方法调用
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode("cXdlMTIzNDVpb3A=");
        String str = new String(decode, Charset.forName("utf-8"));
        System.out.println(str);
        // 编码
        Base64.Encoder encoder = Base64.getEncoder();
        String pass = encoder.encodeToString("wrer".getBytes());
        System.out.println(pass);
    }

    private static void test5() {
        //格式化数字
        double num = 0.234242;
        NumberFormat numberFormat = new DecimalFormat(".###%");
        String result = numberFormat.format(num);
        System.out.println(result);
        // 格式化金钱
        double money = 187346343763.5325647;
        NumberFormat numberFormat1 = new DecimalFormat("#,###.####");
        System.out.println(numberFormat1.format(money));
    }

    private static void test4() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.toLocalTime());
        String format = FORMATTER.format(localDateTime);
        System.out.println(format);
        LocalDateTime localDateTime1 = LocalDateTime.parse(format, FORMATTER);
        System.out.println(localDateTime1);
    }

    private static void test3() {
        // LocalDate/LocalDateTime
        LocalDate now = LocalDate.now();
        LocalDate localDate = LocalDate.of(2022, Month.APRIL, 20);
        System.out.println(now);
        System.out.println(localDate);
        // Period 类的使用
        // 日期的差值
        Period period = now.until(localDate);
        System.out.println(period.getDays());
        long days = now.until(localDate, ChronoUnit.DAYS);
        System.out.println(days);
        now = now.plusDays(20);
        System.out.println(now);
    }

    private static void test2() {
        // Calendar类的方法操作
        // Calendar是一个抽象类，切不可进行初始化
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 10);
        System.out.println(calendar);
        System.out.println(calendar.toInstant());
        System.out.println(calendar.get(Calendar.YEAR));
        // 国外的月份是从12月份开始的
        System.out.println((calendar.get(Calendar.MONTH) + 1));
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.get(Calendar.DATE));
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
        // Calendar类型数据与date之间的转换
        Date time = calendar.getTime();
        System.out.println(time.toInstant());
        calendar.setTime(time);
        calendar.set(2021, Calendar.THURSDAY, 1, 12, 30, 30);
        System.out.println(calendar.toInstant());
        System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
    }

    private static void test1() {
        Date date = new Date();
        System.out.println(date);
        // date----->String
        String str = dateFormat.format(date);
        System.out.println(str);
        // String----->date 会有编译时异常
        // 需要对异常进行处理
        Date date1 = null;
        try {
            date1 = dateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date1);
        // date----->Long(时间戳)
        Long time = date.getTime();
        System.out.println(time);
        // 时间戳---->date类型
        Date date2 = new Date(time);
        System.out.println(date2);
        // Date重写了equals和hash方法
        System.out.println(date.equals(date2));
        // instant 时间格式：2021-04-01T13:26:12.118Z
        Instant instant = date.toInstant();
        System.out.println(instant.toString());
    }
}
