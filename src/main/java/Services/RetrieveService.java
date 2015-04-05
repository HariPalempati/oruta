	package Services;

	import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import command.RetrieveCommand;
@Path("Retrieve")
public class RetrieveService {
	ObjectMapper mapper = new ObjectMapper();
	
	@GET
	@Path("get/{Username}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getRetrieve(@PathParam("Username") String Username) {
		RetrieveCommand command = new RetrieveCommand();
		String FileString = null;
		try {
			FileString = mapper.writeValueAsString(command.execute(null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(FileString).build();
	}

	
	
@GET
	@Path("DOWNLOAD/{filename}")
	@Produces(MediaType.WILDCARD)
	public Response getFile(@PathParam("filename") Byte filename) {
		try {
			RetrieveCommand getFile = new RetrieveCommand();
			ArrayList is = getFile.execute(filename);

			ResponseBuilder response = Response.ok((Object) is);
			response.header("Content-Disposition", "attachment; filename=\""
					+ filename + "\"");
			return response.build();
		} catch (Exception e) {
			return Response.status(404).entity(e.getMessage()).build();
		}
}
}
//@GET
//@Path("metadata")
//@Produces({ MediaType.APPLICATION_JSON })
//public Response getRetrieveMeta() {
//	Retrieve u = new Retrieve();
//	try {
//		@SuppressWarnings("unchecked")
//		HashMap rHM = mapper.convertValue(u, HashMap.class);
//		rHM.remove("id");
//		return Response.status(200).entity(mapper.writeValueAsString(rHM)).build();
//	} catch (JsonProcessingException e) {
//		e.printStackTrace();
//	}
//	return Response.status(500).build();
//}
