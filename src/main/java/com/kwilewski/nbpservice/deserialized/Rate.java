package com.kwilewski.nbpservice.deserialized;

import java.time.LocalDate;

public class Rate {
    private String no;
    private LocalDate effectiveDate;
    private float mid;
    private float bid;
    private float ask;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public float getMid() {
        return mid;
    }

    public void setMid(float mid) {
        this.mid = mid;
    }

    public float getBid() {
        return bid;
    }

    public void setBid(float bid) {
        this.bid = bid;
    }

    public float getAsk() {
        return ask;
    }

    public void setAsk(float ask) {
        this.ask = ask;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "no='" + no + '\'' +
                ", effectiveDate=" + effectiveDate +
                ", mid=" + mid +
                ", bid=" + bid +
                ", ask=" + ask +
                '}';
    }
}
