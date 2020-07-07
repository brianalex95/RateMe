package de.swtp.Rateme.api;

import java.util.Optional;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import de.swtp.Rateme.api.security.AccessManager;
import de.swtp.Rateme.api.security.AccountManager;
import de.swtp.Rateme.db.UserDB;
import de.swtp.Rateme.model.User;
//Teilweise aus der Vorlesung übernommen

@Path("login")
@Singleton
public class LoginController {
	@Inject
	AccessManager accessManager;

	@Inject
	AccountManager accountManager;

	@Inject
	UserDB userdb;

	
	//Checks if you are logged in. It is used as a verification for Rate-submit.
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response checklogin(@CookieParam("LoginID") String loginID) {
		try {
			if(this.accessManager.isLoggedIn(UUID.fromString(loginID)) == true) {
				Optional<String> loginname = this.accessManager.getLoginName(UUID.fromString(loginID));
				User loguser = userdb.loadUser(loginname.get());
				return Response.status(200).entity(loguser.getUserid()).build();
			} else {
				throw new RuntimeException("Not logged in");
			}

		} catch (Exception e) { // exce.printStackTrace();
			System.out.println("ERROR " + e.getMessage());
			return Response.status(404).build();
		}

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(User user) {
		try {
			System.out.println("User Password:	" + user.getPassword());
			Optional<User> optUser = Optional.of(userdb.loadUser(user.getUsername()));
			System.out.println("Optuser Password:	" + optUser.get().getPassword() + "		Optuser Salt:	"
					+ optUser.get().getSalt());
			if (optUser.isPresent()) {
				// Check Password -> now with SALT
				User hshuser = accountManager.checkhash(user, optUser.get().getSalt());
				System.out.println(
						"Hshuser Password:	" + hshuser.getPassword() + "		Hshuser Salt:	" + hshuser.getSalt());
				if (hshuser.getPassword().equals(optUser.get().getPassword()) == false) {
					throw new RuntimeException("Wrong Password");
				}

				// Login
				UUID uuid = this.accessManager.login(user.getUsername());
				NewCookie loginCookie = new NewCookie("LoginID", uuid.toString());
				return Response.status(200).cookie(loginCookie).build();
			} else {
				throw new RuntimeException("User not known");
			}
		} catch (Exception exce) {
			// exce.printStackTrace();
			System.out.println("ERROR " + exce.getMessage());
			return Response.status(404).build();
		}
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response logout(@CookieParam("LoginID") String loginId) {
		try {
			this.accessManager.logout(UUID.fromString(loginId));
			return Response.status(200).cookie((NewCookie) null).build();
		} catch (Exception exce) {
			System.out.println("ERROR " + exce.getMessage());
			return Response.status(404).build();
		}
	}
}
