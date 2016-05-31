package com.datastax.emc;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.policies.RoundRobinPolicy;
import com.datastax.driver.core.policies.TokenAwarePolicy;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Mapper.Option;

public class DataStaxCluster {
	private String[] nodes;
	private String keyspace;
	private Cluster cluster;
	private Session session;
	
	private StatsFutureSet futures;
	
	//Mappers
	private Mapper<Account> account;
	private Mapper<DeviceHistory> deviceHistory;
	private Mapper<AccountByPhone> accountByPhone;
	private Mapper<AccountByEmail> accountByEmail;
	
	public DataStaxCluster(String[] nodes, String keyspace){
		this.setNodes(nodes);
		this.setKeyspace(keyspace);
		
		futures = new StatsFutureSet(80, 100000, 100000);
		
		connect();
		prepare();
	}
	
	public void printStats(){
		futures.printStats();
	}
	
	private void connect(){
		Builder builder = Cluster.builder();
		builder.addContactPoints(nodes);
		builder.withLoadBalancingPolicy(new TokenAwarePolicy(new RoundRobinPolicy()));
		cluster = builder.build();
		cluster.getConfiguration().getSocketOptions().setReadTimeoutMillis(100000);
		cluster.getConfiguration().getQueryOptions().setConsistencyLevel(ConsistencyLevel.ONE);
		session = cluster.connect(keyspace);
	}
	
	private void prepare(){
		account = new MappingManager(session).mapper(Account.class);
		account.setDefaultSaveOptions(Option.saveNullFields(false));
		
		deviceHistory = new MappingManager(session).mapper(DeviceHistory.class);
		deviceHistory.setDefaultSaveOptions(Option.saveNullFields(false));
		
		accountByEmail = new MappingManager(session).mapper(AccountByEmail.class);
		accountByEmail.setDefaultSaveOptions(Option.saveNullFields(false));
		
		accountByPhone = new MappingManager(session).mapper(AccountByPhone.class);
		accountByPhone.setDefaultSaveOptions(Option.saveNullFields(false));
	}
	
	public void saveAccount(Account a){
		futures.add(session.executeAsync(account.saveQuery(a)), "account");
	}
	
	public void saveAccountByEmail(AccountByEmail abe){
		futures.add(session.executeAsync(accountByEmail.saveQuery(abe)), "account_by_email");
	}
	
	public void saveAccountByPhone(AccountByPhone abp){
		futures.add(session.executeAsync(accountByPhone.saveQuery(abp)), "account_by_phone");
	}	
	
	public void saveDeviceHistory(DeviceHistory dh){
		futures.add(session.executeAsync(deviceHistory.saveQuery(dh)), "device_history");
	}


	public void setNodes(String[] nodes) {
		this.nodes = nodes;
	}

	public void setKeyspace(String keyspace) {
		this.keyspace = keyspace;
	}	
}
