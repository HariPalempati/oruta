	package Services;

	import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

	import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

	import com.fasterxml.jackson.databind.ObjectMapper;

	import command.RetrieveCommand;
import util.Constants;
@Path("Retrieve")
public class RetrieveService {
	ObjectMapper mapper = new ObjectMapper();
@GET
	@Path("DOWNLOAD/{filename}")
	@Produces(MediaType.WILDCARD)
	public Response getFile(@PathParam("filename") String filename) {
		try {
			RetrieveCommand getFile = new RetrieveCommand();
			InputStream is = getFile.execute(filename);

			ResponseBuilder response = Response.ok((Object) is);
			response.header("Content-Disposition", "attachment; filename=\""
					+ filename + "\"");
			return response.build();
		} catch (Exception e) {
			return Response.status(404).entity(e.getMessage()).build();
		}
}
}