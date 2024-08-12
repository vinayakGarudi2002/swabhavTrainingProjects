package com.aurionpro.test;

import java.sql.Connection;

import com.aurionpro.database.DbConnection;
import com.aurionpro.model.AccountOperation;
import com.aurionpro.model.TransactionView;
import com.aurionpro.operation.TransactionViewOperation;

public class Test {

	public static void main(String[] args) {
	
		
		 DbConnection dbConnection = new DbConnection();
	        Connection connection = dbConnection.connectToDb();

	      
	           
//	        	TransactionViewOperation ob = new TransactionViewOperation(connection);
//	        
//	        	for(TransactionView t:ob.fetchTransactionsByAccountNumber(840426263779l)) {
//	        		System.out.println(t);
//	        	}


	        AccountOperation accountOperation = new AccountOperation(connection);
	        
		System.out.println(accountOperation.accountAlreadyExist(737057459831l));
		

	}

}
