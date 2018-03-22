package com.bootdo.business.domain;

/*
 * 项目名:    bootdo
 * 包名       com.bootdo.business.domain
 * 文件名:    Location
 * 创建者:    ZSY
 * 创建时间:  2018/3/11 on 18:30
 * 描述:     TODO
 */
public  class Location {

    /**
     * status : 0
     * result : {"location":{"lng":113.92501354125812,"lat":22.52330979788431},"precise":1,"confidence":75,"level":"地产小区"}
     */

    private int status;
    private ResultBean result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * location : {"lng":113.92501354125812,"lat":22.52330979788431}
         * precise : 1
         * confidence : 75
         * level : 地产小区
         */

        private LocationBean location;
        private int precise;
        private int confidence;
        private String level;

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public int getPrecise() {
            return precise;
        }

        public void setPrecise(int precise) {
            this.precise = precise;
        }

        public int getConfidence() {
            return confidence;
        }

        public void setConfidence(int confidence) {
            this.confidence = confidence;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public static class LocationBean {
            /**
             * lng : 113.92501354125812
             * lat : 22.52330979788431
             */

            private double lng;
            private double lat;

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }
        }
    }
}
