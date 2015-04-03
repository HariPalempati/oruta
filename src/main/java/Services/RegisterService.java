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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Register;
import command.RegisterCommand;
import util.Constants;

@SuppressWarnings("unused")
@Path("Register")
public class RegisterService {
	ObjectMapper mapper = new ObjectMapper();

	// Registering User
		@POST
		@Produces({ MediaType.APPLICATION_JSON })
		@Consumes({ MediaType.APPLICATION_JSON })
		public Response Register(String payload) {
			RegisterCommand create = new RegisterCommand();
			Register s = null;
			String i = "";
			try {
				s = mapper.readValue(payload, Register.class);
			} catch (Exception ex) {
				ex.printStackTrace();
				Response.status(400).entity("could not read string").build();
			}
			try {
				i = create.execute(s);
			} catch (Exception e) {
				e.printStackTrace();
				Response.status(500).build();
			}
			return Response.status(200).entity(i).build();
		}
}
//	    @GET
//		@Path("metadata")
//		@Produces({ MediaType.APPLICATION_JSON })
//		public Response getRegisterMeta() {
//			Register u = new Register();
//			try {
//				@SuppressWarnings("unchecked")
//				HashMap rHM = mapper.convertValue(u, HashMap.class);
//				rHM.remove("id");
//				return Response.status(200).entity(mapper.writeValueAsString(rHM)).build();
//			} catch (JsonProcessingException e) {
//				e.printStackTrace();
//			}
//			return Response.status(500).build();
//		}

