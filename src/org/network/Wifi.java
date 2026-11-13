package org.network;

public Wifi {

    public void wifeName(){
        System.out.println("Wife name: TestSolutionRwanda");
    } 

    public static void main(String[] args){
        var wifi = new Wifi();
        var mobileData = new MobileData();
        var lan = new Lan();
        var wireless = new Wireless();


        wifi.wifiName();
        mobileData.dataName();
        lan.lanName();
        wireless.modamName();
    }

}
