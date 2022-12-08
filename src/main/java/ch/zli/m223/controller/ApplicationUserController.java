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
import ch.zli.m223.service.ApplicationUserService;

@Path("/users")
public class ApplicationUserController {

    @Inject
    ApplicationUserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all users.", description = "Returns a list of all users.")
    @RolesAllowed({ "Admin" })
    public List<ApplicationUser> index() {
        return userService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new user. Also known as registration.", description = "Creates a new user and returns the newly added user.")
    @RolesAllowed({ "Admin", "Visitor" })
    public ApplicationUser create(ApplicationUser user) {
        return userService.createUser(user);
    }

    @Path("/{id}")
    @DELETE
    @Operation(summary = "Deletes an user.", description = "Deletes an user by its id.")
    @RolesAllowed({ "Admin" })
    public void delete(@PathParam("id") Long id) {
        userService.deleteUser(id);
    }

    @Path("/{id}")
    @PUT
    @Operation(summary = "Updates an user.", description = "Updates an user by its id.")
    @RolesAllowed({ "Admin" })
    public ApplicationUser update(@PathParam("id") Long id, ApplicationUser user) {
        return userService.updateUser(id, user);
    }

    @Path("/{userId}/{newPassword}")
    @PUT
    @Operation(summary = "Change Password.", description = "Changes the Password of the User by id.")
    @RolesAllowed({ "Admin", "User" })
    public ApplicationUser changePassword(@PathParam("userId") Long userId,
            @PathParam("newPassword") String newPassword) {
        return userService.changePassword(userId, newPassword);
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "List bookings.", description = "Shows all bookings of user by id.")
    @RolesAllowed({ "User", "Admin" })
    public List<ApplicationUser> getBookings(@PathParam("id") Long userId) {
        return userService.getBookings(userId);
    }

    @Path("/{bookingId}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Cancel booking.", description = "Cancels a booking of user by id.")
    @RolesAllowed({ "User", "Admin" })
    public Booking cancelBooking(@PathParam("bookingId") Long bookingId) {
        return userService.cancelBooking(bookingId);
    }
}
