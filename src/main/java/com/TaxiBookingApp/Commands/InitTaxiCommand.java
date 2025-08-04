package com.TaxiBookingApp.Commands;

import com.TaxiBookingApp.Handlers.CommandHandler;
import com.TaxiBookingApp.Model.Location;
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
			TaxiService.getInstance().initializeTaxi(numberOfTaxis);
		}
		
		if (args.length == 2 && args[1].length() != 1) {
			char locationNames[] = Location.values()[0].name().toCharArray();
			System.out.println("Invalid location format. Please provide a single letter ("+locationNames[0]+"-"+locationNames[Location.values().length-1]+") for the initial location.");
			return;			
		}
		
		if (args.length == 2 && args[1].length() == 1) {
			
			char location = Character.toUpperCase(args[1].charAt(0));
						
			if (numberOfTaxis <= 0) {
				System.out.println("Invalid number of taxis. Please provide a positive integer.");
				return;
			}
			
			TaxiService.getInstance().initializeTaxi(numberOfTaxis, Location.valueOf(String.valueOf(location)));
			
		}
		
		System.out.println("Initialized " + numberOfTaxis);
	}
}
