# MongoDBRestGitHub
MongoDBRestWS

Assignment MongoDB:
I just would like to tell you that before this weekend I have never created a Rest Service that communicates with MongoDB. 
And I have only used MongoDB a bit in 2011 not implemented it nor connected to it through a web Service. 
So it was a pretty nice challenge and fun 

1.	What tasks are done:
The Get all and the Insert a new entry are working fine.
As for the Get by ID and Delete By ID, they were not working. I tried to make them work but didn’t have enough time to fix them.

2.	How to use it: 
a.	Before starting make sure that the MongoDB is up and running. 
b.	Modify the MongoDB configuration in the class: EntryConfiguration
c.	we'll be using curl commands on command prompt to get, post, and delete:

Below are the main CRUD commands and how to write them:

Insert a new entry:
C:\>curl -i -X POST -H "Content-Type: application/json" -d "{""title"":""Day4""}
" http://localhost:8080/entries

Result:
HTTP/1.1 204 No Content
Date: Mon, 02 Nov 2015 01:19:41 GMT
Content-Type: application/json



Get all entries:
C:\> curl http://localhost:8080/

Result:
[{"id":"1","title":"Day3","publishedOn":1446410741295},{"id":"2","title":"Day4",
"publishedOn":1446415759085},{"id":"3","title":"Day5","publishedOn":144642119513
8},{"id":"4","title":"Day4","publishedOn":1446422420365},{"id":"afa4b259-c567-4e
03-8218-cc535e3d8c16","title":"Day4","publishedOn":1446426877398}]
C:\>


Get an entry by ID:
curl -H "Content-type: application/json" -X GET http://localhost:8080/entries/2

Result:
This query did not work. The object returned is null as per the sysout.

Delete an entry by ID:
curl -i -H "Accept: application/json" -X DELETE http://localhost:8080/entries/2

Result:
This query did not work. The object could not be found.


1.	Technologies Used and why: 
i.	Why I used Dropwizard:
1.	 Faster than Spring, and much lighter since we won’t be needing most of the Spring features. With Dropwizard, we just have to add one dependency in the pom.xml file, and we’re done.
2.	Embedded Jetty , JAX-WS, JSON, Hibernate Validator
ii.	why not SpringBoots: Because we would be using all the heavy Spring Suite
iii.	I used curl to test the rest webservice
iv.	I used MongoVue for my own to have a user-friendly interface to manage the DB. 

2.	Issues encountered: 
ERROR [2015-11-01 21:48:01,455] com.yammer.dropwizard.jersey.LoggingExceptionMapper: Error handling a request: 8ccc1105faab3d43

com.mongodb.MongoException$Network: can't call something :...

it was caused by unrecognized field id not marked in Entry.java. I got this error right after having added the following method to Get in IndexResource:
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

SOLUTION:
after so many trials to ignore the id field in Entry.java, I found several solutions on :
http://stackoverflow.com/questions/22173418/jackson-json-deserialisation-unrecognized-field-not-marked-as-ignorable

the solution I used is to add
@JsonIgnoreProperties(ignoreUnknown=true)
above the class name AND to import the correct 
import org.codehaus.jackson.annotate.JsonIgnoreProperties; 
instead of com.fasterxml.jackson.annotation.JsonProperty 

3.	Resources: 

DropWizard: https://blog.openshift.com/day-13-dropwizard-the-awesome-java-rest-server-stack/

DropWizard: https://github.com/rtatol/dropwizard-guice-jpa-eclipselink

Install MongoDB: http://stackoverflow.com/questions/2404742/how-to-install-mongodb-on-windows

curl: http://callejoabel.blogspot.ca/2013/09/making-curl-work-on-windows-7.html
