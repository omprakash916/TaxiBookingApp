package com.TaxiBookingApp.Model;

import com.TaxiBookingApp.Entity.Booking;
import java.util.ArrayList;
import java.util.List;

public class Taxi {
	    private static int taxiIdGenerator = 0; // Static variable to keep track of the last assigned taxi ID
		private int taxiId = 0; 
		private Location location = Location.getDefaultLocation(); // Default location
		private int totalEarnings = 0;
		
		List<Booking> bookings = new ArrayList<>();
		
		public Taxi() {
			this.taxiId = taxiIdGenerator++; // Increment the taxiId for each new Taxi instance
		}
		
		public Taxi(Location location) {
			this.taxiId = taxiIdGenerator++;
			this.location = location;
		}
		
		public List<Booking> getBookings() {
			return bookings;
		}

		public void addBooking(Booking booking) {
			bookings.add(booking);
			totalEarnings += booking.getAmount(); // Update total earnings when a booking is added
			location = Location.valueOf(String.valueOf(booking.getTo())) ; // Update current location to the pickup location of the booking
		}
		
		public int getTaxiId() {
			return taxiId;
		}

		public Location getLocation() {
			return location;
		}

		public void setLocation(Location location) {
			this.location = location;
		}

		public int getTotalEarnings() {
			return totalEarnings;
		}

		public void setTotalEarnings(int totalEarnings) {
			this.totalEarnings = totalEarnings;
		} 
		
}
