package jdbc.common;
/**
 * @className: CodeEnum
 * @description:   
 * @author SunHang
 * @createTime 2021/4/19 16:50
 */
public enum  CodeEnum {
    SUCCESS(200,"SUCCESS"),
    ERROR(400,"ERROR");

    private Integer code;
    private String msg;

    CodeEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
