package com.hotel;

public class RoomServiceTask implements Runnable {

    private String serviceName;
    private String roomNo;

    public RoomServiceTask(String serviceName, String roomNo) {
        this.serviceName = serviceName;
        this.roomNo = roomNo;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 3; i++) {
                System.out.println(serviceName + " for Room " + roomNo + " - Step " + i);
                Thread.sleep(1000);
            }
            System.out.println(serviceName + " completed for Room " + roomNo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
