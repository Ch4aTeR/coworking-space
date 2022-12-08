package ch.zli.m223.service;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import ch.zli.m223.model.ApplicationUser;
import ch.zli.m223.model.Login;
import io.smallrye.jwt.build.Jwt;

@ApplicationScoped
public class SessionService {

  @Inject
  ApplicationUserService applicationUserService;

  public Response authenticate(Login login) {
    if (login == null) {

      return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).build();

    }

    Optional<ApplicationUser> principal = applicationUserService.findByEmail(login.getEmail());
    try {
      if (principal.isPresent() && principal.get().getPassword().equals(login.getPassword())) {
        String token = Jwt
            .issuer("https://zli.example.com/")
            .upn(login.getEmail())
            .groups(new HashSet<>(Arrays.asList("User", "Admin")))
            .expiresIn(Duration.ofHours(12))
            .sign();
        return Response
            .ok(principal.get())
            .cookie(new NewCookie("CoWorkSpace", token))
            .header("Authorization", "Bearer " + token)
            .build();
      }
    } catch (Exception e) {
      System.err.println("Couldn't validate password.");
    }
    return Response.status(Response.Status.FORBIDDEN).build();
  }
}
