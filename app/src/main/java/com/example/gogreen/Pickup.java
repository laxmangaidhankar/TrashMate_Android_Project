package com.example.gogreen;

public class Pickup {
    private String pickupAddress, wasteType, date, time, paymentStatus, scheduleStatus, paymentId;
    private int generalQty, organicQty, totalAmount;

    public Pickup() {} // Needed for Firestore

    public Pickup(String pickupAddress, String wasteType, String date, String time,
                  String paymentStatus, String scheduleStatus, String paymentId,
                  int generalQty, int organicQty, int totalAmount) {
        this.pickupAddress = pickupAddress;
        this.wasteType = wasteType;
        this.date = date;
        this.time = time;
        this.paymentStatus = paymentStatus;
        this.scheduleStatus = scheduleStatus;
        this.paymentId = paymentId;
        this.generalQty = generalQty;
        this.organicQty = organicQty;
        this.totalAmount = totalAmount;
    }

    public String getPickupAddress() { return pickupAddress; }
    public String getWasteType() { return wasteType; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getPaymentStatus() { return paymentStatus; }
    public String getScheduleStatus() { return scheduleStatus; }
    public String getPaymentId() { return paymentId; }
    public int getGeneralQty() { return generalQty; }
    public int getOrganicQty() { return organicQty; }
    public int getTotalAmount() { return totalAmount; }
}
