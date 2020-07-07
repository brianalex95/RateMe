package de.swtp.Rateme.api;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.swtp.Rateme.db.RatingDB;
import de.swtp.Rateme.db.UserDB;
import de.swtp.Rateme.model.Rating;
import de.swtp.Rateme.model.User;

@Path("myrating")
@Singleton
public class MyRatingController {
	
	@Inject
	RatingDB ratingdb;
	
	@Inject
	UserDB userdb;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response loadratings(User user) {
		System.out.println("user " + user);
		User myuser = userdb.loadUser(user.getUsername());
		System.out.println("myuser " + myuser);
		Collection<Rating> myratings = ratingdb.loadRatingsForUser(myuser.getUserid());
		System.out.println("myratings " + myratings);
		return Response.ok().entity(myratings).build();
		
	}
	
}
