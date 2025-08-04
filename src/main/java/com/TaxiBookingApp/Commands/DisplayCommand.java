package com.TaxiBookingApp.Commands;

import com.TaxiBookingApp.Handlers.CommandHandler;
import com.TaxiBookingApp.Services.BookingService;

public class DisplayCommand implements CommandHandler {
	@Override
	public void execute(String[] args) {
		
		BookingService.getInstance().displayBookings();
		
		System.out.println("Displaying all bookings.");
	}
}