package com.ghinwa.ericsson.entry;

import com.mongodb.Mongo;
import com.yammer.metrics.core.HealthCheck;
 

//This Class checks if MongoDB is connected or not
public class MongoHealthCheck extends HealthCheck {
 
    private Mongo mongo;
 
    protected MongoHealthCheck(Mongo mongo) {
        super("MongoDBHealthCheck");
        this.mongo = mongo;
    }
 
    @Override
    protected Result check() throws Exception {
        mongo.getDatabaseNames();
        return Result.healthy();
    }
 
}