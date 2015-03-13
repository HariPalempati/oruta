package Services;

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

import command.LoginCheckCommand;
import command.RegisterNewUserCommand;
import command.UploadFileCommand;
import model.Users;

import java.io.File;


@Path("user")
public class Services {
    ObjectMapper mapper = new ObjectMapper();

    // Register new user.
    @POST
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response RegisterNewUser() {
        RegisterNewUserCommand newUser = new RegisterNewUserCommand();
        Users users = new Users();
        int i = 0;
        /*try {
            users = mapper.readValue(payload, Users.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            Response.status(400).entity("could not read string").build();
        }*/
        try {
            i = newUser.execute(users);
        } catch (Exception ex) {
            ex.printStackTrace();
            Response.status(400).entity("could not insert user details").build();
        }

        return Response.status(200).entity(i).build();
    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response UploadFile(@QueryParam("filename") File f,
                               @QueryParam("privateKey") String prikey,
                               @QueryParam("publicKey") String pubkey){
        UploadFileCommand uploadFile = new UploadFileCommand();
        boolean flag = false;
        /*try {
            users = mapper.readValue(payload, Users.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            Response.status(400).entity("could not read string").build();
        }*/
        try {
            flag = uploadFile.execute(f, prikey, pubkey);
        } catch (Exception ex) {
            ex.printStackTrace();
            Response.status(400).entity("could not insert File details").build();
        }

        return Response.status(200).entity(flag).build();
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Response loginCheck() {
        LoginCheckCommand command = new LoginCheckCommand();
        String songString = null;
        try {
            songString = mapper.writeValueAsString(command.execute());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(200).entity(songString).build();
    }
}

