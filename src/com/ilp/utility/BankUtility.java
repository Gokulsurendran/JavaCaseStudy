package com.ilp.utility;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Customer;
import com.ilp.entity.Product;
import com.ilp.entity.Service;
import com.ilp.service.BankService;

public class BankUtility {

	public static void main(String[] args) {
		Scanner scanner =new Scanner(System.in);
		ArrayList<Service> serviceList=new ArrayList<>();
		ArrayList<Product> productList=new ArrayList<>();
		Customer customer = null;
		char choice;
		do {
		
		System.out.println("1.Create Service 2.Create Product 3.Create Customer 4.Manage Accounts 5.Display Customer 6.Exit");
		int mainchoice=scanner.nextInt();
		switch(mainchoice) {
		case 1:BankService.createService(serviceList);
				break;
		case 2:BankService.createProduct(productList,serviceList);	

				break;
		case 3:customer=BankService.createCustomer(productList,customer);
				break;
		case 4:BankService.manageAccounts(customer);
				break;
		case 5:BankService.displayCustomer(customer);
				break;
		case 6:break;
		}
		 System.out.println("Continue to Main Menu? (y/n)");
	        choice = scanner.next().charAt(0);
	}while(choice=='y');
	}

}
