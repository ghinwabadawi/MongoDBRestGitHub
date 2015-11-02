package com.ghinwa.ericsson.entry;

import com.yammer.dropwizard.lifecycle.Managed;
import com.mongodb.Mongo;

//This Class allows us to manage resources on application start and stop
public class MongoManaged implements Managed{
	private Mongo mongo;
	 
    public MongoManaged(Mongo mongo) {
        this.mongo = mongo;
    }
 
    public void start() throws Exception {
    }
 
    public void stop() throws Exception {
        mongo.close();
    }
}
