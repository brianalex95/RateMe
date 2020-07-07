package de.swtp.Rateme.api;

import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import de.swtp.Rateme.api.security.AccessManager;
import de.swtp.Rateme.api.security.AccountManager;
import de.swtp.Rateme.db.UserDB;
import de.swtp.Rateme.model.User;
@Path("/register")
@Singleton
public class RegisterController {
	@Inject
	AccessManager accessController;
	
	@Inject
	AccountManager accountController;

	@Inject
	UserDB userdb;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response register(User user) {
		try {
			User hshuser = accountController.hashpassword(user);
			// this.userManager.register(user);
			if (userdb.createUser(hshuser) != 0) {
				UUID uuid = this.accessController.login(hshuser.getUsername());
				NewCookie loginCookie = new NewCookie("LoginID", uuid.toString());
				return Response.status(200).cookie(loginCookie).build();
			}
			return Response.status(404).build();
		} catch (Exception exce) {
			System.out.println("ERROR " + exce.getMessage());
			return Response.status(404).build();
		}
	}
}
