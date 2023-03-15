package entelect.training.incubator.spring.booking.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer customer_id;

    private Integer flight_id;

    private LocalDateTime booking_date;

    private String referenceNumber;

    public Booking(Integer customer_id, Integer flight_id, LocalDateTime booking_date, String reference_number) {
        this.customer_id = customer_id;
        this.flight_id = flight_id;
        this.booking_date = booking_date;
        this.referenceNumber = reference_number;
    }
}
