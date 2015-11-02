package com.ghinwa.ericsson.entry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yammer.metrics.annotation.Timed;

import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.JacksonDBCollection;

// JAX-RS resource class
// The @Timed annotation makes sure that Dropwizard infrastructure time this method.
@Path("/")
public class IndexResource {
 
	private JacksonDBCollection<Entry, String> collection;
	 
    public IndexResource(JacksonDBCollection<Entry, String> entries) {
        this.collection = entries;
    }
 
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Timed
    public List<Entry> index() {
    	DBCursor<Entry> dbCursor = collection.find();
        List<Entry> entries = new ArrayList<Entry>();
        while (dbCursor.hasNext()) {
            Entry entry = dbCursor.next();
            entries.add(entry);
        }
        return entries;
    }
}