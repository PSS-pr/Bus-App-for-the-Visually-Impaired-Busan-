package org.techtown.beebus;

public class DataModel {
    String bstopnm;
    String carno;

    public String getBstopnm() {
        return bstopnm;
    }

    public void setBstopnm(String bstopnm) {
        this.bstopnm = bstopnm;
    }

    public String getCarno() {
        return carno;
    }

    public void setCarno(String carno) {
        this.carno = carno;
    }


    public DataModel(String bstopnm, String carno) {
        this.carno = carno;
        this.bstopnm = bstopnm;
    }
}
