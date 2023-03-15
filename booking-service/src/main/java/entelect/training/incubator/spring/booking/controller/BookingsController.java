package entelect.training.incubator.spring.booking.controller;

import entelect.training.incubator.spring.booking.model.Booking;
import entelect.training.incubator.spring.booking.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bookings")
public class BookingsController {

    private final Logger LOGGER = LoggerFactory.getLogger(BookingsController.class);

    private final BookingService bookingService;

    public BookingsController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestParam Integer customerID, @RequestParam Integer flightID) {
        LOGGER.info("Processing booking creation request for customer={}", customerID);

        final Booking savedBooking = bookingService.createBooking(customerID, flightID);

        LOGGER.trace("Booking created");
        return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getBookings() {
        LOGGER.info("Fetching all bookings");
        List<Booking> bookings = bookingService.getBookings();

        if (!bookings.isEmpty()) {
            LOGGER.trace("Found bookings");
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        }

        LOGGER.info("No bookings could be found");
        return ResponseEntity.notFound().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getBookingById(@PathVariable Integer id) {
        LOGGER.info("Processing booking search request for booking id={}", id);
        Booking booking = this.bookingService.getBooking(id);

        if (booking != null) {
            LOGGER.trace("Found booking");
            return new ResponseEntity<>(booking, HttpStatus.OK);
        }

        LOGGER.trace("Booking not found");
        return ResponseEntity.notFound().build();
    }

//    @PostMapping("/search")
//    public ResponseEntity<?> searchCustomers(@RequestBody CustomerSearchRequest searchRequest) {
//        LOGGER.info("Processing customer search request for request {}", searchRequest);
//
//        Customer customer = customersService.searchCustomers(searchRequest);
//
//        if (customer != null) {
//            return ResponseEntity.ok(customer);
//        }
//
//        LOGGER.trace("Customer not found");
//        return ResponseEntity.notFound().build();
//    }
}