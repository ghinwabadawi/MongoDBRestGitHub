package com.ghinwa.ericsson.entry;

import java.util.ArrayList;
import java.util.List;
 
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
 
import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.JacksonDBCollection;
 
import com.yammer.metrics.annotation.Timed;

@Path("/entries")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class EntryResource {

	private JacksonDBCollection<Entry, String> collection;
	 
    public EntryResource(JacksonDBCollection<Entry, String> entries) {
        this.collection = entries;
    }
    
    @GET
    @Path("/{id}")
    public Entry getById(@PathParam("id") String id) {
    	Entry entry = collection.findOneById(id);

    	System.out.println("HELLO FROM GETBYID");
    	if(entry!=null)
    		System.out.println("NOT NULL" + entry.getId());
    	else
    		System.out.println("NULLL");
        return entry;
    }
    
    
    
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
    	collection.removeById(id);
    	System.out.println("HELLO FROM DELETE" + id);
    	return Response.noContent().build();
    }
    
    
    
    @POST
    @Timed
    public Response publishNewBlog(@Valid Entry entry) {
        collection.insert(entry);
        System.out.println("HELLO FROM POST" + entry.getId());
        return Response.noContent().build();
    }
}
