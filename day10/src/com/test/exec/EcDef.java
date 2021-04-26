package com.test.exec;
/**
 * @className: EcDef
 * @description:   
 * @author SunHang
 * @createTime 2021/3/28 16:03
 */
public class EcDef extends Exception {
    static final long serialVersionUID = -3387516993124229940L;
    public EcDef(){

    }
    public EcDef(String msg){
        super(msg);
    }

}
