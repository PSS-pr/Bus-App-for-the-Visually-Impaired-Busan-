package org.techtown.beebus;

public class Item {
    String bstopnm;
    String carno;
    String bstdid;
    int image;
    String busnum;

    public String getBusnum() {
        return busnum;
    }

    public void setBusnum(String busnum) {
        this.busnum = busnum;
    }

    public String getBstdid() {
        return bstdid;
    }

    public void setBstdid(String bstdid) {
        this.bstdid = bstdid;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

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
}
