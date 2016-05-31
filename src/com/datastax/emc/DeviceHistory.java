package com.datastax.emc;

import java.util.Calendar;
import java.util.Date;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

@Table(keyspace = "emc", name = "device_history",
readConsistency = "ONE",
writeConsistency = "ONE",
caseSensitiveKeyspace = false,
caseSensitiveTable = false)

public class DeviceHistory {
	@PartitionKey
	private String device_id;
	
	@ClusteringColumn
	private Date date;
	
	private String account_id;
    private double bytes_dn;
    private double bytes_total;
    private double bytes_up;
    private int day;
    private int hour;
    private int month;
    private int year;
    
	public void updateDate(Date newdate) {
		Calendar day = Calendar.getInstance();
		day.setTime(newdate);
		
		this.date = newdate;

		int year = day.get(Calendar.YEAR);
		int month = year * 100 + day.get(Calendar.MONTH)+1;
		int date = month * 100 + day.get(Calendar.DATE);
		int hour = date * 100 + day.get(Calendar.HOUR_OF_DAY);
		
		this.year = year;
		this.month = month;
		this.day = date;
		this.hour = hour;
	}
	
	public void updateTotal(){
		bytes_total = bytes_up + bytes_dn;
	}
	
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
    public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getBytes_dn() {
		return bytes_dn;
	}
	public void setBytes_dn(double bytes_dn) {
		this.bytes_dn = bytes_dn;
	}
	public double getBytes_total() {
		return bytes_total;
	}
	public void setBytes_total(double bytes_total) {
		this.bytes_total = bytes_total;
	}
	public double getBytes_up() {
		return bytes_up;
	}
	public void setBytes_up(double bytes_up) {
		this.bytes_up = bytes_up;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}

    
}
