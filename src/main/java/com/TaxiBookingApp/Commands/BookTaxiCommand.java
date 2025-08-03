package com.TaxiBookingApp.Commands;

import com.TaxiBookingApp.Handlers.CommandHandler;
import com.TaxiBookingApp.Services.TaxiService;

public class BookTaxiCommand implements CommandHandler {
	@Override
	public void execute(String[] args) {
		
		if (args.length < 4) {
			System.out.println("Usage: BOOKTAXI <pickupTime> <pickupLocation> <dropLocation> <customerId>");
			return;
		}
		
		int requestTime= Integer.parseInt(args[0]);
		char pickupLocation = Character.toUpperCase(args[1].charAt(0));
		char dropLocation = Character.toUpperCase(args[2].charAt(0));
		int customerId = Integer.parseInt(args[3]);
		
		TaxiService.getInstance().bookTaxi(requestTime, pickupLocation, dropLocation, customerId);
		System.out.println("Booking a taxi with provided locations.");
	}

}
