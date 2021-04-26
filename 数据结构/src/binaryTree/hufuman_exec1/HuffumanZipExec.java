package binaryTree.hufuman_exec1;
/**
 * @className: HuffumanExec
 * @description:   
 * @author SunHang
 * @createTime 2021/4/12 13:04
 */

import java.util.List;
import java.util.Map;

/**
 * 需要实现压缩功能
 * 1. 读取文件(字节流？还是字符流呢？)
 * 2. 统计文件中字符的出现次数，保存到HashMap中
 * 3. 根据HashMap中的出现次数，建立Node的节点集合ArrayList<Node>
 * 4. 根据赫夫曼树的规则，将集合nodes化为node的一个root节点
 * 5. 根据源数据进行赫夫曼编码，赫夫曼编码对应的二进制的字符串"110001011011"
 * 6. 将字符串编码压缩为byte[]数组，返回一个byte[]
 * 7. 将压缩后的字节数组写入到压缩文件进行保存
 * 8. 将HashMap编码表保存到压缩文件相同的目录
 */
public class HuffumanZipExec {
    public static void main(String[] args) {
        String srcFile = "d://Uninstall.xml";
    }

    /**
     * @param srcFile 传入的希望压缩的文件的路径
     * @param dstFile 压缩后的文件的存放目录
     */
    public static void zipFile(String srcFile, String dstFile) {

    }

    /**
     * @param bytes 原始数据对应的字节数组
     * @return 经过赫夫曼编码处理后的字节数组(压缩后的字节数组)
     */
//    private static byte[] huffmanZip(byte[] bytes) {
//
//    }

    /**
     * 编写一个方法，将字符串对应的byte[] 数组，
     * 通过生成的赫夫曼编码表，返回一个赫夫曼编码 压缩后的byte[]
     * @param bytes 原始数据对应的byte[]
     * @param huffmanCodes：生成的赫夫曼编码map
     * @return 返回是经过赫夫曼编码处理后的byte[]
     */
//    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
//
//    }

    /**
     * 为了方便调用，重载的getCodes
     * @param root
     * @return
     */
//    private static Map<Byte, String> getCodes(Node root) {}

    /**
     * 得到node节点的所有叶子结点的赫夫曼编码
     * 并放入到huffmanCodes集合
     * @param node：传入的节点
     * @param code： 路径：左子节点:0 右子节点：1
     * @param stringBuilder：用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
    }

    /**
     * 前序遍历赫夫曼树
     * @param root: 传入的根节点
     */
    private static void preOrder(Node root){

    }

    /**
     * 将原始的数据传入后，得到一个nodes集合。
     * @param bytes: 接受字节数组
     * @return 返回List形式
     */
//    private static List<Node> getNodes(byte[] bytes) {
//
//    }

    /**
     * 通过List 创建对应的赫夫曼树
     * @param nodes
     * @return 返回的是包含整个树结构的node根节点
     */
//    private static Node createHuffmanTree(List<Node> nodes) {
//
//    }
}






