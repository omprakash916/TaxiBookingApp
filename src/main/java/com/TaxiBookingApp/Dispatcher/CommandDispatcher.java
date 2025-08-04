package com.TaxiBookingApp.Dispatcher;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.TaxiBookingApp.Commands.BookTaxiCommand;
import com.TaxiBookingApp.Commands.DisplayCommand;
import com.TaxiBookingApp.Commands.InitTaxiCommand;
import com.TaxiBookingApp.Handlers.CommandHandler;

public class CommandDispatcher {
	private final Map<String, CommandHandler> commandMap = new HashMap<>();
	
	public CommandDispatcher() {
		// Register commands here
		commandMap.put("BOOKTAXI", new BookTaxiCommand());
		commandMap.put("DISPLAYBOOKINGS", new DisplayCommand());
		commandMap.put("INITIALIZE", new InitTaxiCommand());
	}
	
	public void dispatch(String inputLine) {
		String[] parts = inputLine.split(" ");
		String command = parts[0].toUpperCase();
		String[] args = Arrays.copyOfRange(parts, 1, parts.length);
		
		if (parts.length == 0) {
			System.out.println("No command provided.");
			return;
		}
		
		CommandHandler handler = commandMap.get(command);
		
		if (handler != null) {
			handler.execute(args);
		} else {
			System.out.println("Input Command not recognized: " + command);
		}
		
	}
}
