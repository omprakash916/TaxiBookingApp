package com.TaxiBookingApp.Main;

import com.TaxiBookingApp.Dispatcher.CommandDispatcher;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        
    	java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
    	
    	System.out.println("Welcome to the Taxi Booking App!"); 
		System.out.println("Type 'exit' to quit the application.");
		
		String inputLine;
		while (true) {
			try {
				System.out.print("Enter command: ");
				inputLine = reader.readLine();
				if (inputLine == null || inputLine.equalsIgnoreCase("EXIT")) {
					break;
				}

				// Dispatch the command
				CommandDispatcher dispatcher = new CommandDispatcher();
				dispatcher.dispatch(inputLine);

			} catch (Exception e) { // Catch all exceptions
                System.err.println("An error occurred: " + e.getMessage());
                e.printStackTrace(); // Optional: print stack trace for debugging
                System.out.println("Please try again.");
            }
		}
		
		System.out.println("Thank you for using the Taxi Booking App!");
    }
}