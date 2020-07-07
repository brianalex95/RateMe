package de.swtp.Rateme.api;


import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.swtp.Rateme.api.security.AccessManager;
import de.swtp.Rateme.db.RatingDB;
import de.swtp.Rateme.model.Rating;

@Path("poirating")
@Singleton
public class PoiRatingController {

	@Inject
	RatingDB ratingdb;

	@Inject
	AccessManager accessmanager;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createrating(Rating rate) {
		System.out.println(rate.getImagepath());
		try {
			if (ratingdb.createRating(rate) != 0) {
				return Response.status(200).build();
			}
			return Response.status(404).build();

		} catch (Exception e) {
			return Response.status(404).build();
		}
	}
}