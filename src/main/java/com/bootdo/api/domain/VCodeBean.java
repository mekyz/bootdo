package com.bootdo.api.domain;

/*
 * 项目名:    bootdo
 * 包名       com.bootdo.api.domain
 * 文件名:    VCodeBean
 * 创建者:    ZSY
 * 创建时间:  2018/3/18 on 20:51
 * 描述:     TODO
 */
public class VCodeBean {

    /**
     * reason : 短信发送成功
     * result : {"count":1,"fee":1,"sid":"23d6bc4913614919a823271d820662af"}
     * error_code : 0
     */

    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * count : 1
         * fee : 1
         * sid : 23d6bc4913614919a823271d820662af
         */

        private int count;
        private int fee;
        private String sid;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getFee() {
            return fee;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }
    }
}
