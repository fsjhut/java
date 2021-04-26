package exec;

/**
 * @author : SunHang
 * @className: StudentTest
 * @description:
 * @date: 2021/3/17 10:16
 * @version: 0.1
 * @since: 1.8
 */

public class StudentTest {
    public static void main(String[] args) {
        Student[] arr = new Student[20];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Student();
            arr[i].number = i + 1;
            arr[i].score = (int) (Math.random() * 101);
            arr[i].state = (int) (Math.random() * 3 + 1);
        }
        // 生成对象
        StudentTest studentTest = new StudentTest();
        //调用遍历方法
        System.out.println("******************************");
        studentTest.print(arr);
        // 调用查找班级的方法
        int state = 3;
        System.out.println("******************************");
        studentTest.searchState(arr, state);
        // 对成绩进行冒泡排序
//        studentTest.bubbleSort(arr);
//        // 打印排序后的结果
//        System.out.println("******************************");
//        studentTest.print(arr);

        // 对成绩进行选择排序
//        studentTest.selectSort(arr);
//        // 打印排序后的结果
//        System.out.println("******************************");
//        studentTest.print(arr);

        // 对成绩进行插入排序
        studentTest.insertSort(arr);
        // 打印排序后的结果
        System.out.println("******************************");
        studentTest.print(arr);
    }


    private void print(Student[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i].info());
        }
    }

    /**
     *
     * @param arr
     * @param state
     */
    private void searchState(Student[] arr, int state) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].state == state) {
                System.out.println(arr[i].info());
            }
        }
    }

    /**
     * 对成绩进行排序
     * 利用冒泡排序法
     * 利用中间的变量
     *
     * @param arr
     */
    private void bubbleSort(Student[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                // 定义临时变量，用于交换
                Student temp;
                if (arr[j].score > arr[j + 1].score) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 选择排序
     *
     * @param arr
     */
    public void selectSort(Student[] arr) {
        for (int i = 0; i < arr.length; i++) {
            Student min = arr[i];
            int minIndex = i;
            // 找到第i+1个数到最后一个数中的最小值
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].score < min.score) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            // 将最小值与arr[i]进行交换
            arr[minIndex] = arr[i];
            arr[i] = min;
//            System.out.print(arr[i].score + "\t");
//            System.out.println();
        }
    }

    /**
     * 插入排序
     *
     * @param arr
     */
    public void insertSort(Student[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int num = -1;
            Student temp = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i].score >= arr[j].score) {
                    num = j;
                    break;
                }
            }
            // 利用临时变量保存当前的值
            // 从第i个开始，将前一个值赋值给后一个
            // 截至条件为 num+1的后一个位置
            for (int k = i; k > num + 1; k--) {
                arr[k] = arr[k - 1];
            }
            // 将临时变量的值赋值给num+1的位置
            arr[num + 1] = temp;
//            if (num == -1) { // arr[i]是最小的一个，原数组所有成员后移一个单位
//                Student temp = arr[i];
//                for (int k = i; k >= 1; k--) { // 如何实现数组元素后移一个单位呢？
//                    arr[k] = arr[k - 1];
//                }
//                arr[0] = temp;
//            } else {  // 如何实现数组的插入操作呢？
//                Student temp = arr[i];
//                for (int k = i; k > num + 1; k--) {
//                    arr[k] = arr[k - 1];
//                }
//                arr[num + 1] = temp;
//            }
        }
    }
}

