package Assignment2;

public class Main {
    public static void main(String[] args) {
        BookingSystem bookingSystem = new BookingSystem();

        // Booking seats
        bookingSystem.insert(133, "Ahmed");
        bookingSystem.insert(90, "Didar");
        bookingSystem.insert(120, "Hoger");
        bookingSystem.insert(144, "Zhina");
        bookingSystem.insert(150, "Nma");
        
        System.out.println("Booked Seats:");
        bookingSystem.displayBookedSeats();

        System.out.println("\nRemaining Booked Seats:");
        bookingSystem.displayBookedSeats();

        // Counting available seats
        System.out.println("\nNumber of Available Seats: " + bookingSystem.countAvailableSeats());

        // Displaying booked seats after a specific booked seat
        System.out.println("\nBooked Seats After seat 90:");
        bookingSystem.displayBookedSeatsAfter(90);

        // Deleting a booked seat
        System.out.println("\nDeleting seat 144...");
        bookingSystem.delete(144);
        System.out.println("Booked Seats:");
        bookingSystem.displayBookedSeats();
        System.out.println("\nNumber of Available Seats: " + bookingSystem.countAvailableSeats());
    }
}
