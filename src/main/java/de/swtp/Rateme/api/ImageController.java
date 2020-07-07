package de.swtp.Rateme.api;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.swtp.Rateme.db.RatingDB;
import de.swtp.Rateme.model.Rating;

@Path("images")
@Singleton
public class ImageController {

	@Inject
	RatingDB ratingdb;

	@PUT
	@Consumes("image/png")
	@Produces(MediaType.TEXT_PLAIN)
	public Response uploadimage(InputStream imageStream) {
		// Image was sent as Binarystream. Here it will be put into a file. The name
		// will be randomized. In the Database the relative image path will be saved ("/images/******.png")
		FileOutputStream fop = null;
		File file;
		String filepath = "images/" + randomname() + ".png";
		System.out.println(filepath);
		try {

			file = new File(filepath);
			fop = new FileOutputStream(file);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// get the content in bytes
			byte[] contentInBytes = imageStream.readAllBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();

			System.out.println("Done uploading image");
			return Response.status(200).entity(filepath).build();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return Response.status(404).entity(filepath).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("image/png")
	public Response sendimage(Rating rate) throws IOException {

		String filepath = rate.getImagepath();
		System.out.println(filepath);
		try {
			File file = new File(filepath);

			System.out.println("file: " + file.length());
			return Response.status(200).entity(file).build();
		} catch (Exception e) {
			return Response.status(404).build();
		}

	}

	public String randomname() {
		int length = 10;
		Random rand = new Random();
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = chars.charAt(rand.nextInt(chars.length()));
		}
		return new String(text);
	}
}
