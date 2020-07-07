package de.swtp.Rateme.api;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.swtp.Rateme.db.PoiDB;
import de.swtp.Rateme.db.RatingDB;
import de.swtp.Rateme.model.Poi;
import de.swtp.Rateme.model.Rating;

//Codebasis wurde aus Skript übernommen
@Path("poi")
@Singleton
public class PoiController {

	@Inject
	PoiDB poiDB;
	
	@Inject
	RatingDB ratingDB;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllPoi() {
		System.out.println("getAllPoi");
		Collection<Poi> allPoi = poiDB.loadPois();
		return Response.ok().entity(allPoi).build();
	}


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response loadpoirating(Poi poi) {
		System.out.println(poi);
		Collection<Rating> allratings = ratingDB.loadRatingsForPoi(poi.getOsmId());
		System.out.println(allratings.toString());
		return Response.ok().entity(allratings).build();
	}

}


