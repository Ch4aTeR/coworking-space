package ch.zli.m223;

import java.time.LocalDate;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;


import ch.zli.m223.model.ApplicationUser;
import ch.zli.m223.model.Booking;
import ch.zli.m223.model.BookingTime;
import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.runtime.StartupEvent;

@IfBuildProfile("test")
@ApplicationScoped
public class TestDataService {

  @Inject
  EntityManager entityManager;

  @Transactional
  void generateTestData(@Observes StartupEvent event) {

    //Bookings
    var firstBooking = new Booking();
    firstBooking.setDate(LocalDate.parse("'2004-10-01'"));
    firstBooking.setBookingTime(BookingTime.MORNING);
    entityManager.persist(firstBooking);

    var secondBooking = new Booking();
    secondBooking.setDate(LocalDate.parse("'2004-12-29'"));
    secondBooking.setBookingTime(BookingTime.DAY);
    entityManager.persist(secondBooking);

    var thirdBooking = new Booking();
    thirdBooking.setDate(LocalDate.parse("'2022-03-25'"));
    thirdBooking.setBookingTime(BookingTime.AFTERNOON);
    entityManager.persist(thirdBooking);

    
    //Users
    var firstUser = new ApplicationUser();
    firstUser.setEmail("cedric.markstaller@gmail.com");
    firstUser.setPassword("123456789");
    firstUser.setName("Cedric");
    firstUser.setLastname("Markstaller");
    entityManager.persist(firstUser);

    var secondUser = new ApplicationUser();
    secondUser.setEmail("hans.muster@gmail.com");
    secondUser.setPassword("987654321");
    secondUser.setName("Hans");
    secondUser.setLastname("Muster");
    entityManager.persist(secondUser);

    var thirdUser = new ApplicationUser();
    thirdUser.setEmail("laura.horta@gmail.com");
    thirdUser.setPassword("lololilly");
    thirdUser.setName("Laura");
    thirdUser.setLastname("Horta");
    entityManager.persist(thirdUser);
  }
}
