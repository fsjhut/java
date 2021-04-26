package jdbc.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @className: ServerResponse
 * @description:   
 * @author SunHang
 * @createTime 2021/4/19 16:50
 */
@Getter
@Setter
@ToString
public class ServerResponse<T> implements Serializable {

    private static final long serialVersionUID = -3193672555308280491L;
    private Integer code;
    private String msg;
    private T data;

    public ServerResponse() {
    }

    public ServerResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ServerResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ServerResponse(T data) {
        this.data = data;
    }
    public static <T> ServerResponse<T> success(T data){
        return new ServerResponse<>(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMsg(), data);
    }
    public static <T> ServerResponse<T> success() {
        return new ServerResponse<>(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMsg());
    }
    public static <T> ServerResponse<T> error() {
        return new ServerResponse<>(CodeEnum.ERROR.getCode(), CodeEnum.ERROR.getMsg());
    }
}
