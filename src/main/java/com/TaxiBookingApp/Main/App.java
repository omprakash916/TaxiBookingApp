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
				
			} catch (java.io.IOException e) {
				System.err.println("Error reading input: " + e.getMessage());
			}
		}
		
		System.out.println("Thank you for using the Taxi Booking App!");
    }
}
