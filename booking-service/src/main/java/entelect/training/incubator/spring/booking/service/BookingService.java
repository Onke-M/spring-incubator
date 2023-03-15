package entelect.training.incubator.spring.booking.service;

import entelect.training.incubator.spring.booking.model.Booking;
import entelect.training.incubator.spring.booking.repository.BookingRepository;
import entelect.training.incubator.spring.customer.model.Customer;
import entelect.training.incubator.spring.customer.service.CustomersService;
import entelect.training.incubator.spring.flight.model.Flight;
import entelect.training.incubator.spring.flight.service.FlightsService;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking createBooking(Integer customerID, Integer flightID) {
        RestTemplate restTemplate = new RestTemplate();
        String customerEndpoint = "http://localhost:8205/customers/" + customerID;
        String flightEndpoint = "http://localhost:8202/flights/" + flightID;
        Customer customer = restTemplate.getForObject(customerEndpoint, Customer.class);
        Flight flight = restTemplate.getForObject(flightEndpoint, Flight.class);
        if(customer == null || flight == null)
        {
            return null;
        }
        Booking booking = new Booking(customerID, flightID, LocalDateTime.now(),  "ABC123");
        return bookingRepository.save(booking);
    }

    public List<Booking> getBookings() {
        Iterable<Booking> bookingIterable = bookingRepository.findAll();

        List<Booking> result = new ArrayList<>();
        bookingIterable.forEach(result::add);

        return result;
    }

    public Booking getBooking(Integer id) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        return bookingOptional.orElse(null);
    }
}
