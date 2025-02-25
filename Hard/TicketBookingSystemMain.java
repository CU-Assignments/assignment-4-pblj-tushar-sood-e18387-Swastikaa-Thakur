class TicketBookingSystem {
    private boolean[] seats;

    // Constructor to initialize the seats
    public TicketBookingSystem(int totalSeats) {
        seats = new boolean[totalSeats];
    }

    // Synchronized method to book a seat
    public synchronized boolean bookSeat(int seatNumber, String customerType) {
        if (seatNumber < 0 || seatNumber >= seats.length) {
            System.out.println(customerType + " tried to book an invalid seat: " + seatNumber);
            return false;
        }

        if (!seats[seatNumber]) {
            seats[seatNumber] = true;
            System.out.println(customerType + " successfully booked seat number: " + seatNumber);
            return true;
        } else {
            System.out.println(customerType + " tried to book already booked seat: " + seatNumber);
            return false;
        }
    }
}

// BookingThread.java
class BookingThread extends Thread {
    private TicketBookingSystem system;
    private int seatNumber;
    private String customerType;

    public BookingThread(TicketBookingSystem system, int seatNumber, String customerType) {
        this.system = system;
        this.seatNumber = seatNumber;
        this.customerType = customerType;
    }

    @Override
    public void run() {
        system.bookSeat(seatNumber, customerType);
    }
}

// Main Class
public class TicketBookingSystemMain {
    public static void main(String[] args) {
        int totalSeats = 10;
        TicketBookingSystem system = new TicketBookingSystem(totalSeats);

        // VIP bookings (Higher Priority)
        BookingThread vip1 = new BookingThread(system, 3, "VIP");
        BookingThread vip2 = new BookingThread(system, 4, "VIP");

        vip1.setPriority(Thread.MAX_PRIORITY);
        vip2.setPriority(Thread.MAX_PRIORITY);

        // Regular bookings (Lower Priority)
        BookingThread regular1 = new BookingThread(system, 3, "Regular");
        BookingThread regular2 = new BookingThread(system, 4, "Regular");
        BookingThread regular3 = new BookingThread(system, 5, "Regular");

        regular1.setPriority(Thread.MIN_PRIORITY);
        regular2.setPriority(Thread.MIN_PRIORITY);
        regular3.setPriority(Thread.MIN_PRIORITY);

        // Start VIP threads first
        vip1.start();
        vip2.start();

        // Start Regular threads
        regular1.start();
        regular2.start();
        regular3.start();

        // Wait for all threads to finish
        try {
            vip1.join();
            vip2.join();
            regular1.join();
            regular2.join();
            regular3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All bookings processed!");
    }
}
