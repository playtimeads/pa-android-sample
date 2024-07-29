package com.zodiacscope.zenith.astroverse.Model;

import java.util.Date;


public class Muhurat_Model {
    private Date end_date;
    private String muhrat;
    private Date start_date;

    public String getMuhurat() {
        return this.muhrat;
    }

    public void setMuhurat(String str) {
        this.muhrat = str;
    }

    public Date getEnd() {
        return this.end_date;
    }

    public void setEnd(Date date) {
        this.end_date = date;
    }

    public Date getStart() {
        return this.start_date;
    }

    public void setStart(Date date) {
        this.start_date = date;
    }

}