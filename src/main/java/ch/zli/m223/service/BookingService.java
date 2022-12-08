package ch.zli.m223.service;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.ApplicationUser;
import ch.zli.m223.model.Booking;

public class BookingService {
    @Inject
    EntityManager entityManager;

    @Transactional
    public Booking createBooking(Booking booking) {
        return entityManager.merge(booking);
    }

    @Transactional
    public void deleteBooking(Long id) {
        var entity = entityManager.find(Booking.class, id);
        entityManager.remove(entity);
    }

    @Transactional
    public Booking updateBooking(Long id, Booking booking) {
        booking.setId(id);
        return entityManager.merge(booking);
    }

    @Transactional
    public List<Booking> findAll() {
        var query = entityManager.createQuery("FROM Booking", Booking.class);
        return query.getResultList();
    }

    @Transactional
    public Booking handleBooking(Long id, boolean isAccepted, Booking booking) {
        booking.setIsAccepted(isAccepted);
        return entityManager.merge(booking);
    }

    @Transactional
    public Booking asignToUser(Long id, Booking booking, ApplicationUser applicationUser){
        booking.setApplicationUser(applicationUser);
        return entityManager.merge(booking);
    }
}
