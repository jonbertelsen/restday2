package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.EmployeeDTO;
import utils.EMF_Creator;
import facades.EmployeeFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

//Todo Remove or change relevant parts before ACTUAL use
@Path("employee")
public class EmployeeResource
{

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final EmployeeFacade FACADE =  EmployeeFacade.getEmployeeFacadeInstance(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello Employee World\"}";
    }

    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
        long count = FACADE.getEmployeeCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAll()
    {
        System.out.println("Get all");
        List<EmployeeDTO> employeeDTOS = FACADE.getAll();
        return GSON.toJson(employeeDTOS);
    }

    @Path("populate")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String populate()
    {
        System.out.println("Populate through api");
        FACADE.populate();
        return "{\"msg\":\"Database populated\"}";
    }
}
