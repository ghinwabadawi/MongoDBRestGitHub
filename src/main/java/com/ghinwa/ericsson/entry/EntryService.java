package com.ghinwa.ericsson.entry;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

import net.vz.mongodb.jackson.JacksonDBCollection;

//This Service Class starts the embedded jetty server
public class EntryService extends Service<EntryConfiguration> {

	// This main method acts as our service entry point.
	// Inside the main method, we create an instance of EntryService and call
	// the run method.
	// We pass the server command as argument.
	// The server command will start the embedded jetty server.
	public static void main(String[] args) throws Exception {
		new EntryService().run(new String[] { "server" });
	}

	@Override
	public void initialize(Bootstrap<EntryConfiguration> bootstrap) {
		bootstrap.setName("entry");
	}

	@Override
	public void run(EntryConfiguration configuration, Environment environment) throws Exception {

		//MongoDB Configuration
		
		Mongo mongo = new Mongo(configuration.mongohost, configuration.mongoport);
		MongoManaged mongoManaged = new MongoManaged(mongo);
		environment.manage(mongoManaged);

		environment.addHealthCheck(new MongoHealthCheck(mongo));
		//end MongoDB Configuration
		
		DB db = mongo.getDB(configuration.mongodb);
        JacksonDBCollection<Entry, String> entries = JacksonDBCollection.wrap(db.getCollection("entries"), Entry.class, String.class);
        
		
		// register IndexResource
		environment.addResource(new IndexResource(entries));
		
		environment.addResource(new EntryResource(entries));
	}
}
