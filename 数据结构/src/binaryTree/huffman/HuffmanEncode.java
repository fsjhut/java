package binaryTree.huffman;

import java.nio.charset.Charset;
import java.util.*;

/**
 * @author SunHang
 * @className: HuffmanEncode
 * @description:
 * @createTime 2021/4/11 16:02
 */

/**
 * 在利用递归时，如何合理的回退呢？
 */
public class HuffmanEncode {
    public static HashMap<Character, Integer> hashMap = new HashMap<>();
    public static HashMap<Character, String> encodingMap = new HashMap<>();
    public static StringBuilder stringBuilder = new StringBuilder();
    public static StringBuilder stringBu = new StringBuilder();
    static ArrayList<String> strArrList = new ArrayList<>();
//    public static StringBuilder temp = new StringBuilder();

    public static void main(String[] args) {
        String str = "i like like like tcp do you like a tcp";
        // 统计字符出现次数
        charNumCount(str);
//        System.out.println(hashMap.keySet());
//        System.out.println(hashMap.get(' '));
        // 对字母出现次数进行赫夫曼编码
        ArrayList<CharNode> nodes = new ArrayList<>();
        CharNode root = huffmanTree(nodes);
        // 利用赫夫曼编码对字符串进行编码
//        huffmanEncoding(root, "", stringBuilder);
        huffmanEncoding(root);
        Set<Map.Entry<Character, String>> entries = encodingMap.entrySet();
        Iterator<Map.Entry<Character, String>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Character, String> next = iterator.next();
            System.out.print(next.getKey() + "\t");
            System.out.println(next.getValue());
        }
        // 根据map，将字符串转为字符串121144
        byte[] bytes = stringToBytes(str);
        for (byte aByte : bytes) {
            System.out.print(aByte+"\t");
        }
    }

//    private static byte[] huffmanZip(byte[] bytes){
//        List<CharNode> nodes =
//    }

    private static byte[] stringToBytes(String string) {
        for (char ch : string.toCharArray()) {
            strArrList.add(encodingMap.get(ch));
        }
        // 拼接所有的字符串
        strArrList.forEach((st) -> stringBu.append(st));
        System.out.println(stringBu.toString());
        System.out.println(stringBu.length());

        // 将字符串转为bytes
        //        System.out.println(strArrList);

//        int len;
//        if(string.length()%8 == 0){
//            len = string.length()/8;
//        }else{
//            len = string.length()/8 + 1;
//        }
        int len = (stringBu.length() + 7) / 8;
        System.out.println(len);
        // 创建一个压缩后的bytes数组
        byte[] by = new byte[len];
        int index = 0;// 记录是第几个byte
        // 每八位对应一个byte，+8
        String strByte;
        for (int i = 0; i < stringBu.length(); i += 8) {
            if (i + 8 > stringBu.length()) {
                // 不够八位
                strByte = stringBu.substring(i);
            } else {
                strByte = stringBu.substring(i, i + 8);
            }
            // 将strByte转为byte,放入数组中
            by[index] = (byte) Integer.parseInt(strByte,2); // 2 进制
            index++;
        }
        return by;
    }

    private static void huffmanEncoding(CharNode root) {
        huffmanEncoding(root.left, "0", stringBuilder);
        huffmanEncoding(root.right, "0", stringBuilder);

    }

    private static void huffmanEncoding(CharNode root, String str, StringBuilder stringBuilder) {
        Objects.requireNonNull(root);
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(str);
        // 获取一共创建的节点的个数
//        for (int i = 0; i < CharNode.num; i++) {
        System.out.println(root.value);
//        if(root.right==null&&root.left==null){
//            encodingMap.put(root.ch,str);
////            stringBuilder.deleteCharAt(stringBuilder.length()-1);
//////            System.out.println("----------");
////            stringBuilder = temp;
//            return;
//        }
//        if (root.left != null) {
////            stringBuilder.append("0");
////            temp = stringBuilder;
//            huffmanEncoding(root.left,"0",stringBuilder2);
////            System.out.println("-------------");
////            System.out.println(stringBuilder);
//        }
////        stringBuilder.deleteCharAt(stringBuilder.length()-1);
//        if (root.right != null) {
////            stringBuilder.append("1");
////            temp = stringBuilder;
//            huffmanEncoding(root.right,"1",stringBuilder2);
//        }
//        }

        if (root.ch == null) {
            huffmanEncoding(root.left, "0", stringBuilder2);
            huffmanEncoding(root.right, "1", stringBuilder2);

        } else {
            encodingMap.put(root.ch, stringBuilder2.toString());
        }

    }

    private static CharNode huffmanTree(ArrayList<CharNode> nodes) {
        // 构建charNode节点
        Iterator<Character> iterator = hashMap.keySet().iterator();
        while (iterator.hasNext()) {
            Character ch = iterator.next();
            int count = hashMap.get(ch);
            CharNode charNode = new CharNode(ch, count);
            nodes.add(charNode);
            System.out.println("字符：" + ch + ", 次数：" + count);
        }
        // 创建赫夫曼树
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            CharNode left = nodes.remove(0);
            CharNode right = nodes.remove(0);
            CharNode parent = new CharNode(left.value + right.value);
            parent.left = left;
            parent.right = right;
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    private static void charNumCount(String str) {
        Objects.requireNonNull(str);
        for (char ch : str.toCharArray()) {
            if (hashMap.containsKey(ch)) {
                int temp = hashMap.get(ch) + 1;
                hashMap.put(ch, temp);
            } else {
                hashMap.put(ch, 1);
            }
        }
    }
}

class CharNode implements Comparable<CharNode> {
    int value;
    Character ch;
    CharNode left;
    CharNode right;
    public static int num;

    public CharNode(char ch, int value) {
        this.value = value;
        this.ch = ch;
        num++;
    }

    public CharNode(int value) {
        this.value = value;
        num++;
    }

    @Override
    public int compareTo(CharNode o) {
        return this.value - o.value;
    }

    @Override
    public String toString() {
        return "CharNode{" +
                "value=" + value +
                ", ch=" + ch +
                '}';
    }
}
