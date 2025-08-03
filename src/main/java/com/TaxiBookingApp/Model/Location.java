package com.TaxiBookingApp.Model;

public enum Location {
	A,B,C,D,E,F;

    public static boolean isValidLocation(char locationChar) {
        try {
            // 1. Convert the input char to an uppercase String.
            String locationName = String.valueOf(locationChar).toUpperCase();

            // 2. Try to get the enum constant for that String.
            //    If this fails, it throws an IllegalArgumentException.
            Location.valueOf(locationName);

            // 3. If no exception was thrown, the location is valid.
            return true;
        } catch (IllegalArgumentException e) {
            // 4. If valueOf fails, it means the location is not valid.
            return false;
        }
    }
    
    public static Location getDefaultLocation() {
		return A; // Default location can be set to any valid enum value
	}
    
}
