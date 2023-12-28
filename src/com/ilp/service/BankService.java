package com.ilp.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.CurrentAccount;
import com.ilp.entity.Customer;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.Product;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Service;

public class BankService {
	//function to create Service
	public static void createService(ArrayList<Service> serviceList) 
	{
		Scanner scanner=new Scanner(System.in);
		char choice;
		do {
			Service service=null;
			System.out.println("Enter the Service Code");
			String serviceCode=scanner.next();
			System.out.println("Enter the Service Name");
			String serviceName=scanner.next();
			System.out.println("Enter the Service Rate");
			double serviceRate=scanner.nextDouble();
			service = new Service(serviceCode,serviceName,serviceRate);
			serviceList.add(service);
	        System.out.println("Do you want to enter another service? (y/n)");
	        choice = scanner.next().charAt(0);
		}while(choice=='y');
	}
	
	//function to create product
	public static void createProduct(ArrayList<Product> productList, ArrayList<Service> serviceList) 
	{
		Scanner scanner=new Scanner(System.in);
		char addProductChoice;
		char productTypeChoice;
		char serviceChoice;
		do {
			
				System.out.println("Enter the Product Code");
				String productCode=scanner.next();
				System.out.println("Enter the Product Name");
				String productName=scanner.next();
				int serviceListLength=serviceList.size();
				System.out.println(serviceListLength);
				
				ArrayList<Service> productServiceList=new ArrayList<>();
				SavingsMaxAccount savingsMaxAccount=null;
				LoanAccount loanAccount=null;
				CurrentAccount currentAccount=null;
				
			do {
					int serialNo=1;
					System.out.println("Select the Service from below List");
					for(Service service:serviceList) {
						System.out.println(serialNo+service.getServiceName());
						serialNo++;
					}
					System.out.println("Select the Service");
					int serviceItemChoice=scanner.nextInt();
					productServiceList.add(serviceList.get(serviceItemChoice-1));
					serviceListLength--;
					System.out.println("Do you want to add another service? (y/n)");
					serviceChoice= scanner.next().charAt(0);
				}while(serviceChoice=='y');
			
				switch(productName) 
					{
						case "SavingsMaxAccount":
							savingsMaxAccount=new SavingsMaxAccount(productCode,productName,productServiceList,1000.0);
							productList.add(savingsMaxAccount);
							break;
						case "LoanAccount":
							loanAccount=new LoanAccount(productCode,productName,productServiceList,3);
							productList.add(loanAccount);
							
							break;
						case "CurrentAccount":
							currentAccount=new CurrentAccount(productCode,productName,productServiceList);
							productList.add(currentAccount);
							break;
					}
				for (Product product : productList) 
				{
					System.out.println(product);
				}
				System.out.println("Do you want to enter another product? (y/n)");
				addProductChoice = scanner.next().charAt(0);
				
		}while(addProductChoice=='y');
		
	}
	//function to create customer
	public static Customer createCustomer(ArrayList<Product> productList, Customer customer) {
		Account account=null;
		int accountNo=1000;
		String customerName = null;
		char addAccountChoice;
		ArrayList<Account> accountList=new ArrayList<>();
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter the Customer Code");
		String customerCode=scanner.next();
		boolean flag=false;
		if(customer==null) 
		{
			flag=true;
		}
		if(flag) 
		{
			System.out.println("Enter the customer code:");
			customerCode=scanner.next();
			System.out.println("Enter the customer Name:");
			customerName=scanner.next();
			System.out.println("Customer id not available:Create a new account");
			do {
					int accountChoice;
					double accountBalance;
					System.out.println("**Accounts Available**");
					int index=1;
					for(Product product:productList) 
					{
						System.out.println(index+product.getProductName());
						index++;
					}
					System.out.println("Enter the Choice");
					accountChoice=scanner.nextInt();
					System.out.println("Enter the Balance");
					accountBalance=scanner.nextDouble();
					account=new Account("A"+accountNo,productList.get(accountChoice-1).getProductName(),accountBalance,productList.get(accountChoice-1));
					accountNo++;
					accountList.add(account);
					System.out.println("Do you want to add another account? (y/n)");
					addAccountChoice = scanner.next().charAt(0);
				}while(addAccountChoice=='y');
			
			customer=new Customer(customerCode,customerName,accountList);
		
		}
		else 
		{
			ArrayList<Account> accountList1=new ArrayList<>();
			accountList1=customer.getAccountList();
			do {
					int accountChoice;
					double accountBalance;
					System.out.println("**Accounts Available**");
					int index=1;
					for(Product product:productList) 
					{
						System.out.println(index+product.getProductName());
						index++;
					}
					System.out.println("Enter the Choice");
					accountChoice=scanner.nextInt();
					System.out.println("Enter the Balance");
					accountBalance=scanner.nextDouble();
					account=new Account("A"+accountNo,productList.get(accountChoice-1).getProductName(),accountBalance,productList.get(accountChoice-1));
					accountNo++;
					accountList1.add(account);
					System.out.println("Do you want to add another account? (y/n)");
					addAccountChoice = scanner.next().charAt(0);
				}while(addAccountChoice=='y');
				customer.setAccountList(accountList1);
		}
		return customer;
	}
	//function for account transactions
	public static void manageAccounts(Customer customer) 
	{
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter the Customer Code");
		String customerCode=scanner.next();
		if(customer.getCustomerCode().equals(customerCode)) 
		{
			System.out.println(customer.getCustomerName()+"has the following Accounts");
			int index=1;
			for(Account account:customer.getAccountList()) 
			{
				System.out.println(index+account.getAccountType());
				index++;
			}
			char transactionchoice;
			System.out.println("Enter Your Choice");
			int choice=scanner.nextInt();
			do {
					System.out.println("1. Deposit 2. Withdraw 3.Display Balance");
					int selectOption=scanner.nextInt();
					double totalBalance=0;
					double currentBalance=customer.getAccountList().get(choice-1).getBalance();
					switch(selectOption) 
					{
						case 1:System.out.println("Enter the amount to be deposited:");
								double depositAmount=scanner.nextDouble();
								totalBalance=currentBalance+depositAmount;
								customer.getAccountList().get(choice-1).setBalance(totalBalance);
								System.out.println("Your current balance is:"+totalBalance);
								break;
						case 2:System.out.println("Enter the amount to be withdraw:");
								double withdrawAmount=scanner.nextDouble();
								double balanceMoney = currentBalance-withdrawAmount;
								if(customer.getAccountList().get(choice-1).getAccountType().equals("SavingsMaxAccount")) 
								{
									if(balanceMoney<((SavingsMaxAccount)customer.getAccountList().get(choice-1).getProduct()).getMinimumBalance()) 
									{
										System.out.println("Sorry!!!!!!!!!!!!! A mininmum balance of Rs 1000 should be mainted in the account.");
									}
									else 
									{		
										customer.getAccountList().get(choice-1).setBalance(balanceMoney);
									}
								}
								else 
								{
									customer.getAccountList().get(choice-1).setBalance(currentBalance-withdrawAmount);
								}
								break;
								
						case 3:System.out.println("Your balance amount: "+currentBalance);
								break;
						default:System.out.println("Invalid");
								break;
					}
					System.out.println("Do you want to continue:(y/n)");
					transactionchoice = scanner.next().charAt(0);
			}while( transactionchoice== 'y');
		
		}
	}
	//function to display customer account details
	public static void displayCustomer(Customer customer) 
	{
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter the customer code:");
		String customerCode = scanner.next();
		System.out.println("************************Customer Account Details****************************");
		System.out.println("Customer_id\t\tCustomer_name\t\tAccount_Type\t\tBalance");
		System.out.println("****************************************************************************");
		ArrayList<Account> accountList = customer.getAccountList();
		for(Account account:accountList)
		{
			System.out.println(customer.getCustomerCode()+"\t\t\t"+customer.getCustomerName()+"\t\t\t"+account.getAccountType()+"\t\t"+account.getBalance());
			System.out.print("Services provided:");
			ArrayList<Service> serviceList = account.getProduct().getServiceList();
			for(Service service:serviceList)
			{
				System.out.print(service.getServiceName()+",");
			}
			System.out.println();
			
		}
	}
}





