package ch.zli.m223.controller;

import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;

import ch.zli.m223.model.ApplicationUser;
import ch.zli.m223.model.Booking;
import ch.zli.m223.service.BookingService;

@Path("/bookings")
public class BookingController {
  @Inject
  BookingService bookingService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Operation()
  @RolesAllowed({ "Admin", "User", "Visitor" })
  public List<Booking> index() {
    return bookingService.findAll();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation()
  @RolesAllowed({ "Admin", "User" })
  public Booking create(Booking booking) {
    return bookingService.createBooking(booking);
  }

  @Path("/{id}")
  @DELETE
  @Operation()
  @RolesAllowed({ "Admin" })
  public void delete(@PathParam("id") Long id) {
    bookingService.deleteBooking(id);
  }

  @Path("/{id}")
  @PUT
  @Operation()
  @RolesAllowed({ "Admin" })
  public Booking update(@PathParam("id") Long id, Booking booking) {
    return bookingService.updateBooking(id, booking);
  }

  @Path("/{id}")
  @PUT
  @Operation()
  @RolesAllowed({ "Admin" })
  public Booking handleBooking(@PathParam("id") Long id, Boolean isAccepted, Booking booking) {
    return bookingService.handleBooking(id, isAccepted, booking);
  }

  @Path("/{id}")
  @PUT
  @Operation()
  @RolesAllowed({ "Admin" })
  public Booking asignToUser(@PathParam("id") Long id, Booking booking, ApplicationUser applicationUser) {
    return bookingService.asignToUser(id, booking, applicationUser);
  }
}
