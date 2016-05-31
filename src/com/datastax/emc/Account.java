package com.datastax.emc;

import java.util.HashSet;
import java.util.Set;

import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

@Table(keyspace = "emc", name = "account",
readConsistency = "ONE",
writeConsistency = "ONE",
caseSensitiveKeyspace = false,
caseSensitiveTable = false)

public class Account {
	@PartitionKey
	private String account_id;
	
    private String city;
    private String email;
    private String phone_number;
    private String first_name;
    private String last_name;
    private String name_prefix;
    private String name_suffix;
    private String state;
    private String street_address;
    private Set<String> devices;
    private String zip;
    
    public Account(){
    	devices = new HashSet<String>();
    }
    
    public void addDevice(String device_id){
    	devices.add(device_id);
    }
    
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getName_prefix() {
		return name_prefix;
	}
	public void setName_prefix(String name_prefix) {
		this.name_prefix = name_prefix;
	}
	public String getName_suffix() {
		return name_suffix;
	}
	public void setName_suffix(String name_suffix) {
		this.name_suffix = name_suffix;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStreet_address() {
		return street_address;
	}
	public void setStreet_address(String street_address) {
		this.street_address = street_address;
	}
	public Set<String> getDevices() {
		return devices;
	}
	public void setDevices(Set<String> devices) {
		this.devices = devices;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

}
