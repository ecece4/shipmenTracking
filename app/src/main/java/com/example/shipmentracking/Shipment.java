package com.example.shipmentracking;

import android.view.contentcapture.DataRemovalRequest;

import java.security.SecureRandom;
import java.util.Date;

public class Shipment {
    public String no;
    public String exit_branch;
    public String release_date;
    public String arrival_branch;
    public String recipient_name_surname;
    public String sender_name_surname;
    public String type;
    public String waybill_no;
    public Float kg;
    public String latest_status;
    public String receiver_name;
    public String delivery_date;
    public String delivery_datetime;


    public Shipment(String no, String exit_branch, String release_date, String arrival_branch,
                    String recipient_name_surname, String sender_name_surname, String type, String waybill_no, Float kg,
                    String latest_status, String receiver_name, String delivery_date, String delivery_datetime){

            this.no = no;
            this.exit_branch = exit_branch;
            this.release_date = release_date;
            this.arrival_branch = arrival_branch;
            this.recipient_name_surname = recipient_name_surname;
            this.sender_name_surname = sender_name_surname;
            this.type = type;
            this.waybill_no = waybill_no;
            this.kg = kg;
            this.latest_status = latest_status;
            this.receiver_name = receiver_name;
            this.delivery_date = delivery_date;
            this.delivery_datetime = delivery_datetime;

    }



    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getRecipient_name_surname() {
        return recipient_name_surname;
    }

    public void setRecipient_name_surname(String recipient_name_surname) {
        this.recipient_name_surname = recipient_name_surname;
    }

    public String getDelivery_datetime() {
        return delivery_datetime;
    }

    public void setDelivery_datetime(String delivery_datetime) {
        this.delivery_datetime = delivery_datetime;
    }

    public String getExit_branch() {
        return exit_branch;
    }

    public void setExit_branch(String exit_branch) {
        this.exit_branch = exit_branch;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public Float getKg() {
        return kg;
    }

    public void setKg(Float kg) {
        this.kg = kg;
    }

    public String getSender_name_surname() {
        return sender_name_surname;
    }

    public void setSender_name_surname(String sender_name_surname) {
        this.sender_name_surname = sender_name_surname;
    }

    public String getLatest_status() {
        return latest_status;
    }

    public void setLatest_status(String latest_status) {
        this.latest_status = latest_status;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getArrival_branch()
    {
        return arrival_branch;
    }

    public void setArrival_branch(String arrival_branch)
    {
        this.arrival_branch = arrival_branch;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getWaybill_no() {
        return waybill_no;
    }

    public void setWaybill_no(String waybill_no) {
        this.waybill_no = waybill_no;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Shipment (){

    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }
}

