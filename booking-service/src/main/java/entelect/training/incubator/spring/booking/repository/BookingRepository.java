package main.java.entelect.training.incubator.spring.booking.repository;

import main.java.entelect.training.incubator.spring.booking.model.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {
    Optional<Booking> findByReferenceNumber(String referenceNumber);
}
