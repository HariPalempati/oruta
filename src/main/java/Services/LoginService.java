package Services;

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

import com.fasterxml.jackson.databind.ObjectMapper;

//import model.Song;
import model.Login;
import command.LoginCommand;
//import command.CreateSongCommand;
//import command.DeleteSongCommand;
//import command.GetSongCommand;
//import command.ListSongsCommand;
//import command.SearchSongCommand;
//import command.UpdateSongCommand;
import util.Constants;

  @Path("Login")
  public class LoginService {
	ObjectMapper mapper = new ObjectMapper();

		@GET
		@Path("id/{id}")
		@Produces({ MediaType.APPLICATION_JSON })
		public Response getLogin(@PathParam("id") String id) {
			LoginCommand command = new LoginCommand();
			String loginString = null;
			try {
				loginString = mapper.writeValueAsString(command.execute());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return Response.status(200).entity(loginString).build();
		}
}
//  @GET
//	@Path("metadata")
//	@Produces({ MediaType.APPLICATION_JSON })
//	public Response getLoginMeta() {
//		Login l = new Login();
//		try {
//			@SuppressWarnings("unchecked")
//			HashMap rHM = mapper.convertValue(l, HashMap.class);
//			rHM.remove("id");
//			return Response.status(200).entity(mapper.writeValueAsString(rHM)).build();
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//		return Response.status(500).build();
//	}
