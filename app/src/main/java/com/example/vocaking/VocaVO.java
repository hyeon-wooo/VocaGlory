package com.example.vocaking;


public class VocaVO {
    private String voca;
    private String mean;
    private String pronounce;

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getVoca() {
        return voca;
    }

    public void setVoca(String voca) {
        this.voca = voca;
    }

    public String getPronounce() { return pronounce; }

    public void setPronounce(String pronounce) { this.pronounce = pronounce; }

    @Override
    public String toString() {
        return "VocaVO {" +
                "voca:" + voca +
                ", mean:" + mean +
                ", pronounce:" + pronounce +"}";

    }

}
