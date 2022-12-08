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
  @Operation()
  @RolesAllowed({"Admin"})
  public List<ApplicationUser> index() {
      return userService.findAll();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation()
  @RolesAllowed({"Admin", "Visitor"})
  public ApplicationUser create(ApplicationUser user) {
     return userService.createUser(user);
  }

  @Path("/{id}")
  @DELETE
  @Operation()
  @RolesAllowed({"Admin"})
  public void delete(@PathParam("id") Long id) {
      userService.deleteUser(id);
  }

  @Path("/{id}")
  @PUT
  @Operation()
  @RolesAllowed({"Admin"})
  public ApplicationUser update(@PathParam("id") Long id, ApplicationUser user) {
      return userService.updateUser(id, user);
  }

  @Path("/{id}")
  @PUT
  @Operation()
  @RolesAllowed({"Admin", "User"})
  public ApplicationUser changePassword(@PathParam("id") Long id, ApplicationUser user, String newPassword) {
      return userService.changePassword(id, user, newPassword);
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Operation()
  @RolesAllowed({"User"})
  public List<ApplicationUser> getBookings(@PathParam("id") Long id, ApplicationUser user) {
      return userService.getBookings(id, user);
  }

  @Path("/{id}")
  @PUT
  @Operation()
  @RolesAllowed({"User"})
  public Booking cancelBooking(Booking booking, @PathParam("id") Long id) {
      return userService.cancelBooking(booking, id);
  }
}
