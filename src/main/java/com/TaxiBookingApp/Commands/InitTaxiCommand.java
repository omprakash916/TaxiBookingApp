package com.TaxiBookingApp.Commands;

import com.TaxiBookingApp.Handlers.CommandHandler;
import com.TaxiBookingApp.Services.TaxiService;

public class InitTaxiCommand implements CommandHandler {
	@Override
	public void execute(String[] args) {
		
		if(args.length < 1) {
			System.out.println("Usage: INITTAXI <numberOfTaxis> <initialLocation(optional)>");
			return;
		}
		
		int numberOfTaxis = Integer.parseInt(args[0]);
		
		if (args.length < 2) {
			System.out.println("No initial location provided. Defaulting to 'A'.");
			args = new String[] { String.valueOf(numberOfTaxis), "A" };
		}
		
		char location = Character.toUpperCase(args[1].charAt(0));
		
		if (!Character.isLetter(location) || location < 'A' || location > 'Z') {
			System.out.println("Invalid location. Please provide a valid letter (A-Z).");
			return;
		}
		
		if (numberOfTaxis <= 0) {
			System.out.println("Invalid number of taxis. Please provide a positive integer.");
			return;
		}
		
		TaxiService.getInstance().initializeTaxi(numberOfTaxis);
		System.out.println("Initialized " + numberOfTaxis);
	}
}
