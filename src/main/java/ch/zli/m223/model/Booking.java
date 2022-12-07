package ch.zli.m223.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(readOnly = true)
    private Long id;

    @Column
    private LocalDate date;

    @ManyToOne(optional = false)
    @Fetch(FetchMode.JOIN)
    private ApplicationUser applicationUser;

    @Enumerated(EnumType.STRING)
    private BookingTime bookingTime;

    public Long getId() {
        return id;
      }
    
      public void setId(Long id) {
        this.id = id;
      }

      public LocalDate getDate() {
        return date;
      }
    
      public void setDate(LocalDate date) {
        this.date = date;
      }

      public BookingTime getBookingTime() {
        return bookingTime;
      }
    
      public void setBookingTime(BookingTime bookingTime) {
        this.bookingTime = bookingTime;
      }
}
