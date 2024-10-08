package org.techtown.beebus;

public class Station {

    public String bstopnm;
    public String carno;


    public void clear(){

        bstopnm = "";
        carno   = "";

    }

    boolean checkRecvAllData(){
        return  bstopnm.length()  > 0
                && carno.length()    > 0;
    }
}
