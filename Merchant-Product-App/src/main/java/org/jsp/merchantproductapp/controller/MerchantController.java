package org.jsp.merchantproductapp.controller;

import java.util.Scanner;

import org.jsp.merchantproductapp.dao.MerchantDao;
import org.jsp.merchantproductapp.dto.Merchant;

public class MerchantController {

	public static void main(String[] args) {
		MerchantDao merchantdao=new MerchantDao();
		Scanner sc=new Scanner(System.in);
		while(true) {
			System.out.println("1.Save Merchant\n2.Update Merchant\n3.Find Merchant By Id\n4.Verify Merchant by Phone and Password\n5.Verify Merchant by Email and Password\n6.Exit");
		    switch(sc.nextInt()) {
		    case 1:{
		    	Merchant merchant=new Merchant();
		    	System.out.println("Enter the name,phone,email,gst_number,password");
		    	merchant.setName(sc.next());
		    	merchant.setPhone(sc.nextLong());
		    	merchant.setEmail(sc.next());
		    	merchant.setGst_number(sc.next());
		    	merchant.setPassword(sc.next());
		    	merchant=merchantdao.saveMerchant(merchant);
		    	System.out.println("Merchant saved with id:"+merchant.getId());
		    	break;}
		    case 2:{
		    	Merchant merchant=new Merchant();
		    	System.out.println("Enter the id name,phone,email, gst_number and password to update Merchant");
				merchant.setId(sc.nextInt());
				merchant.setName(sc.next());
				merchant.setPhone(sc.nextLong());
				merchant.setEmail(sc.next());
				merchant.setGst_number(sc.next());
				merchant.setPassword(sc.next());
				merchant = merchantdao.updateMerchant(merchant);
				if (merchant != null)
					System.out.println("Merchant  with id:" + merchant.getId() + " updated");
				else
					System.err.println("Cannot Update Merchant as Id is Invalid");
				break;
		    }
		    case 3:{
				System.out.println("Enter the Merchant Id to display details");
				int merchant_id = sc.nextInt();
				Merchant merchant = merchantdao.findMerchantById(merchant_id);
				if (merchant != null)
					System.out.println(merchant);
				else
					System.err.println("Invalid merchant Id");
				break;
			}
		    case 4: {
				System.out.println("Enter the Phone Number and Password to verify Merchant");
				long phone = sc.nextLong();
				String password = sc.next();
				Merchant merchant = merchantdao.verifyMerchant(phone, password);
				if (merchant != null) {
					System.out.println(merchant);
				} else {
					System.err.println("Invalid Phone Number or Password");
				}
				break;
			}
		    case 5: {
				System.out.println("Enter the Email Id and Password to verify Merchant");
				String email = sc.next();
				String password = sc.next();
				Merchant merchant = merchantdao.verifyMerchant(email, password);
				if (merchant != null) {
					System.out.println(merchant);
				} else {
					System.err.println("Invalid Email Id or Password");
				}
				break;
			}
		    case 6: System.out.println("Thank you!!!");
		    	System.exit(0);
		    default: System.err.println("Invalid choice!!!");
		    }
	
		}
		}
}
