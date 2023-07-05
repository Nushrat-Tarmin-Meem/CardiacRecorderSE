package com.example.cardiacrecorder;

public class Records {
    String mail,sis,dias,rate,comment,d,t;
    public Records() {
    }

    public Records(String mail, String sis, String dias, String rate, String comment, String d, String t) {
        this.mail = mail;
        this.sis = sis;
        this.dias = dias;
        this.rate = rate;
        this.comment = comment;
        this.d = d;
        this.t = t;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSis() {
        return sis;
    }

    public void setSis(String sis) {
        this.sis = sis;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

}
