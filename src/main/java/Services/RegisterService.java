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

import model.Song;
import model.Register;
import command.CreateSongCommand;
import command.DeleteSongCommand;
import command.GetSongCommand;
import command.ListSongsCommand;
import command.SearchSongCommand;
import command.UpdateSongCommand;
import command.RegisterCommand;
import util.Constants;

@Path("song")
public class RegisterService {
	ObjectMapper mapper = new ObjectMapper();

	// Add a song
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
	// Browse all songs
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response browseSongs(@QueryParam("offset") int offset,
			@QueryParam("count") int count) {
		ListSongsCommand command = new ListSongsCommand();
		ArrayList<Song> list = command.execute();
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put(Constants.Pagination.DATA, list);
		hm.put(Constants.Pagination.OFFSET, offset);
		hm.put(Constants.Pagination.COUNT, count);
		String songString = null;
		try {
			songString = mapper.writeValueAsString(hm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(songString).build();
	}

	// get song by Id
	@GET
	@Path("get/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getSong(@PathParam("id") int id) {
		GetSongCommand command = new GetSongCommand();
		String songString = null;
		try {
			songString = mapper.writeValueAsString(command.execute(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(songString).build();
	}

	
	// Update a song
	 @POST
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response updateSongs(String payload, @PathParam("id") int id) {
		UpdateSongCommand update = new UpdateSongCommand();
		Song s = null;
		try {
			s = mapper.readValue(payload, Song.class);
			s.setId(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			Response.status(400).entity("could not read string").build();
		}
		try {
			update.execute(s);
		} catch (Exception e) {
			e.printStackTrace();
			Response.status(500).build();
		}
		return Response.status(200).build();
	}
// Delete a song
		@DELETE
		@Path("{id}")
		public Response deleteSongs(@PathParam("id") int id) {
			DeleteSongCommand delete = new DeleteSongCommand();
			Song s = new Song();
			s.setId(id);
			try {
				delete.execute(id);
			} catch (Exception e) {
				e.printStackTrace();
				Response.status(500).build();
			}
			return Response.status(200).build();
		}
 // Search songs
		@GET
		@Path("title/{title}")
		@Produces({ MediaType.APPLICATION_JSON })
		public Response getSong(@PathParam("title") String title) {
			SearchSongCommand command = new SearchSongCommand();
			String songString = null;
			try {
				songString = mapper.writeValueAsString(command.execute(title));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return Response.status(200).entity(songString).build();
		}
}
