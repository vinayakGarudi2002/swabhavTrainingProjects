package com.aurionpro.test;

import java.sql.Connection;
import java.util.ArrayList;

import com.aurionpro.database.DbConnection;
import com.aurionpro.model.AccountOperation;

public class AccountTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  Connection connection =   DbConnection.connectToDb();

		AccountOperation accountOp = new AccountOperation(connection);
		ArrayList<Long> accountNumbers = accountOp.getAccountNumbersByUserId(1);

		for (Long accNo : accountNumbers) {
		    System.out.println("Account Number: " + accNo);
		}

	}

}
