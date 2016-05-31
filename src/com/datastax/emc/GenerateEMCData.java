package com.datastax.emc;

import java.util.Calendar;
import java.util.Random;


public class GenerateEMCData {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//Generate data for 10 days
		Calendar startDate = Calendar.getInstance();
		startDate.add(Calendar.DATE, -10);		
		DataFactory df = new DataFactory(startDate);

		DataStaxCluster dse = new DataStaxCluster(new String[]{"node0", "node1", "node5", "node6"}, "emc");

		Random rand = new Random();

		//Create 1000 accounts
		for (int i=0; i<1000; i++){
			//Generate account data
			Account a = df.newAccount();
			//Generate query tables
			AccountByEmail abe = df.newAccountByEmail(a);
			AccountByPhone abp = df.newAccountByPhone(a);
			//Write to database
			dse.saveAccount(a);
			dse.saveAccountByEmail(abe);
			dse.saveAccountByPhone(abp);
			
			//For each device, generate a random number of data points
			for (String device_id : a.getDevices()){
				DeviceHistory dh = df.newDeviceHistory(device_id, a.getAccount_id());
				
				for (int j=0; j<rand.nextInt(100); j++){
					dh.updateDate(df.randomDate());
					dh.setBytes_dn(df.randomValue());
					dh.setBytes_up(df.randomValue());
					dh.updateTotal();
					dse.saveDeviceHistory(dh);
				}				
			}
			
			//For each vehicle, generate random number of data points

		}
		
		System.out.println("Data load complete.");
		//Wait 10 seconds for pending callbacks to complete.
		Thread.sleep(10000);
		dse.printStats();
		System.exit(0);
	}

}
