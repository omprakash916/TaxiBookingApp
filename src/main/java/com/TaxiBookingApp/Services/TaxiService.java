package com.TaxiBookingApp.Services;

import java.util.ArrayList;
import java.util.List;

import com.TaxiBookingApp.Entity.Booking;
import com.TaxiBookingApp.Model.Location;
import com.TaxiBookingApp.Model.Taxi;

public class TaxiService {
	private static TaxiService instance;
	
	private List<Taxi> taxis = new ArrayList<>();
	
	private TaxiService() {
		// Private constructor to prevent instantiation
	}
	
	public static TaxiService getInstance() {
		if(instance == null) {
			instance = new TaxiService();
		}
		return instance;
	}
	
	public void addTaxi(Taxi taxi) {
		// Add a taxi to the service
		taxis.add(taxi);
	}
	
	public List<Taxi> getTaxis() {
		// Get the list of taxis
		return taxis;
	}
	
	public boolean initializeTaxi(int taxiCount) {
		// Initialize the taxi service with a default location
		System.out.println("Taxi defaulted to default location.");
		
		return initializeTaxi(taxiCount, Location.getDefaultLocation());
	}
	
	public boolean initializeTaxi(int taxiCount, Location location) {
		// Initialize the taxi service, e.g., load taxis from a database or create default taxis
		try {
			for(int i=1; i<=taxiCount; i++) {
				getInstance().getTaxis().add(new Taxi(location));
			}
			System.out.println("Taxi initialized.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void removeTaxi(int taxiId) { // optional
		// Remove a taxi from the service
		getInstance().getTaxis().removeIf(taxi -> taxi.getTaxiId() == taxiId);
		System.out.println("Taxi with ID " + taxiId + " removed.");
	}
	
	public boolean isTaxiAvailable(Taxi taxi, int requestTime) {
        if (taxi.getBookings().isEmpty()) 
        	return true;
        Booking lastBooking = taxi.getBookings().get(taxi.getBookings().size() - 1);
        return lastBooking.getDropTime() <= requestTime;
    }
	
	public Taxi isTaxisAvailable(int requestTime, char pickup, char drop) {
		
		Taxi selectedTaxi = null;
        int minDistance = Integer.MAX_VALUE;
        
        for (Taxi taxi : taxis) {
            if (isTaxiAvailable(taxi,requestTime)) {
                int distance = Math.abs(taxi.getLocation().toString().charAt(0) - pickup) * 15; // Assuming distance is calculated based on the difference in enum values
                if (distance < minDistance ||
                        (distance == minDistance && taxi.getTotalEarnings() < selectedTaxi.getTotalEarnings())) {
                    selectedTaxi = taxi;
                    minDistance = distance;
                }
            }
        }
        
        if (selectedTaxi == null) {
            System.out.println("Booking rejected. No taxis available.");
        }
        
		return selectedTaxi;
     }
	
	 public void bookTaxi(int requestTime, char pickup, char drop, int customerId) {
	        // Book a taxi for the customer
	        Taxi selectedTaxi = isTaxisAvailable(requestTime, pickup, drop);
	        
	        if (selectedTaxi == null) {
	            System.out.println("No taxis available for booking.");
	            return;
	        }

	        int pickupTime = requestTime; // Assuming the pickup time is the request time
	        if (pickupTime < 0 || pickupTime > 23) {
	            System.out.println("Invalid pickup time. Please provide a time between 0 and 23.");
	            return;
	        }

	        if (!Location.isValidLocation(pickup) || !Location.isValidLocation(drop)) {
	            System.out.println("Invalid pickup or drop location.");
	            return;
	        }

	        if (pickup == drop) {
	            System.out.println("Pickup and drop locations cannot be the same.");
	            return;
	        }
	        
	        int dropTime = pickupTime + Math.abs(drop - pickup);
	        int amount = new BookingService().calculateFare(pickup, drop);
	        int bookingId = selectedTaxi.getBookings().size() + 1;

	        Booking booking = new Booking(bookingId, customerId, pickup, drop, pickupTime, dropTime, amount);
	        selectedTaxi.addBooking(booking);
	    }	
		
}
