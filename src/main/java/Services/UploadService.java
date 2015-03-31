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

//import model.Song;
	import model.Register;
	//import command.CreateSongCommand;
	//import command.DeleteSongCommand;
	//import command.GetSongCommand;
	//import command.ListSongsCommand;
	//import command.SearchSongCommand;
	//import command.UpdateSongCommand;
	import command.UploadCommand;
	import command.RetrieveCommand;
import util.Constants;

	@Path("song")
	public class UploadService {
		ObjectMapper mapper = new ObjectMapper();

		@GET
		@Path("inline/{filename}")
		@Produces("image/*")
		public Response renderFile(@PathParam("filename") String filename) {
			try {
				RetrieveCommand getFile = new RetrieveCommand();
				InputStream is = getFile.execute(filename);

				ResponseBuilder response = Response.ok((Object) is);
				response.header("Content-Disposition", "inline; filename=\""
						+ filename + "\"");
				return response.build();
			} catch (Exception e) {
				return Response.status(404).entity(e.getMessage()).build();
			}
		}

		
		@POST
		@Path("/upload/db")
		@Consumes(MediaType.MULTIPART_FORM_DATA)
		public Response uploadDBFile(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
	 
			UploadCommand cmd = new UploadCommand();
			cmd.execute(fileDetail.getFileName(), uploadedInputStream, fileDetail.getSize());
			return Response.status(200).build();
		}
	}