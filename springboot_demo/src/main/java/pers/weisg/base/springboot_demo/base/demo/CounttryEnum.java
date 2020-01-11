package pers.weisg.base.springboot_demo.base.demo;

import lombok.Data;

/**
 * @author weisg
 * @description TODO
 * @date 2019/11/25 0025
 */

public enum  CounttryEnum {
    DNE(1,"齐"),TWO(2,"楚"),THREE(3,"韩"),FOUR(4, "赵"),FIVE(5,"魏"),S工X(6,"燕");
    private Integer code;
    private String msg;

    CounttryEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public static String getMsgByCode(Integer code){
        for (CounttryEnum counttryEnum : CounttryEnum.values()){
            if(counttryEnum.getCode().equals(code)){
                return counttryEnum.getMsg();
            }
        }
        return "";
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
