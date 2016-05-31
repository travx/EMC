package com.datastax.emc;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.github.javafaker.Faker;

public final class DataFactory {
	private final Faker fakedata;
	private Random rand;
	private Date startDate;
	private Date endDate;
	
	public DataFactory(Calendar day){
		fakedata = new Faker();
		rand = new Random();
		
		startDate = day.getTime();
		endDate = new Date();
	}
	
	public Account newAccount(){
		Account a = new Account();
		a.setAccount_id(fakedata.bothify("??####?##"));
		a.setCity(fakedata.address().city());
		a.setEmail(fakedata.internet().emailAddress());
		a.setPhone_number(fakedata.phoneNumber().phoneNumber());
		a.setFirst_name(fakedata.name().firstName());
		a.setLast_name(fakedata.name().lastName());
		a.setName_prefix(fakedata.name().prefix());
		a.setName_suffix(fakedata.name().suffix());
		a.setState(fakedata.address().state());
		a.setStreet_address(fakedata.address().streetAddress(false));
		a.setZip(fakedata.address().zipCode());
		
		//How many devices should this account have?
		int t = 1000;
		int n = (int) Math.floor(Math.log((Math.random() * t) + 1)/Math.log(2));
		for (int i=0; i<n; i++){
			a.addDevice(fakedata.bothify("#?###???###"));
		}
		
		return a;
	}
	
	public AccountByPhone newAccountByPhone(Account a){
		AccountByPhone abp = new AccountByPhone();
		abp.setAccount_id(a.getAccount_id());
		abp.setFirst_name(a.getFirst_name());
		abp.setLast_name(a.getLast_name());
		abp.setPhone_number(a.getPhone_number());
		return abp;
	}
	
	public AccountByEmail newAccountByEmail(Account a){
		AccountByEmail abe = new AccountByEmail();
		abe.setAccount_id(a.getAccount_id());
		abe.setFirst_name(a.getFirst_name());
		abe.setLast_name(a.getLast_name());
		abe.setEmail(a.getEmail());
		return abe;
	}	
	
	
	public DeviceHistory newDeviceHistory(String device_id, String account_id){
		DeviceHistory dh = new DeviceHistory();
		dh.setAccount_id(account_id);
		dh.setDevice_id(device_id);
		return dh;
	}
	
	public Date randomDate(){
		return fakedata.date().between(startDate, endDate);
	}
	
	public double randomValue(){
		return rand.nextDouble() * 12;
	}

}
