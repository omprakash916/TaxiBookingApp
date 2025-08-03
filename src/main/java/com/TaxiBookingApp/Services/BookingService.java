package com.TaxiBookingApp.Services;

import com.TaxiBookingApp.Entity.Booking;
import com.TaxiBookingApp.Model.Taxi;
public class BookingService {
	
	public static void displayBookings() {
		// Display all bookings
		System.out.println("Displaying all bookings:");
		for (Taxi taxi : TaxiService.getInstance().getTaxis()) {
			System.out.println("Taxi ID: " + taxi.getTaxiId());
			for (Booking booking : taxi.getBookings()) {
				System.out.println("Booking ID: " + booking.getBookingId() + ", From: " + booking.getFrom() + ", To: " + booking.getTo() +"Pickup Time: "+ booking.getPickupTime() +"DropTime: "+booking.getDropTime() +", Total Earnings: " + taxi.getTotalEarnings());
			}
		}
	}
	
	public int calculateFare(char from, char to) {
		int distance = Math.abs(to - from)*15;
		return 100 + Math.max(0, distance - 5) * 10;
		}
}


