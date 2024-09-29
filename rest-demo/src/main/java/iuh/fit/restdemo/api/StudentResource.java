package iuh.fit.restdemo.api;

import iuh.fit.restdemo.controller.StudentController;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/std")
public class StudentResource {
    @Inject
    private StudentController process;
    @GET
//    @Produces(MediaType.APPLICATION_JSON)
    public Response getallStudent(){
        Response.ResponseBuilder builder = Response.ok();
        builder.entity(process.getStudentList());
        return builder.build();
    }
}
