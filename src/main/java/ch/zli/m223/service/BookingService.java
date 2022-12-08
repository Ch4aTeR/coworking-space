package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.ApplicationUser;
import ch.zli.m223.model.Booking;

@ApplicationScoped
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
    public Booking handleBooking(Long bookingId, boolean isAccepted) {
        var bookingEntity = entityManager.find(Booking.class, bookingId);
        bookingEntity.setIsAccepted(isAccepted);
        return entityManager.merge(bookingEntity);
    }

    @Transactional
    public Booking asignToUser(Long bookingId, Long userId) {
        var bookingEntity = entityManager.find(Booking.class, bookingId);
        var userEntity = entityManager.find(ApplicationUser.class, userId);
        bookingEntity.setApplicationUser(userEntity);
        return entityManager.merge(bookingEntity);

    }
}
