package com.datastax.emc;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

@Table(keyspace = "emc", name = "account_by_email",
readConsistency = "ONE",
writeConsistency = "ONE",
caseSensitiveKeyspace = false,
caseSensitiveTable = false)

public class AccountByEmail {
	@PartitionKey
	private String email;
	
	@ClusteringColumn
	private String account_id;
	
    private String first_name;
    private String last_name;
    
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
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
}
