package com.rest.jersey;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/myresource")
public class MyResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Hello!";
    }
    
    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getItAsJson() {
    	List<Person> persons = new ArrayList<>();
    	persons.add(new Person("abba", 30));
    	persons.add(new Person("anna", 40));
    	persons.add(new Person("david", 10));
    	return persons;
    }
    
    @GET
    @Path("/xml")
    @Produces(MediaType.APPLICATION_XML)
    public List<Person> getItAsXML() {
    	List<Person> persons = new ArrayList<>();
    	persons.add(new Person("abba", 30));
    	persons.add(new Person("anna", 40));
    	persons.add(new Person("david", 10));
    	return persons;
    }
    
    
    @GET
    @Path("/bean")
    @Produces(MediaType.APPLICATION_XML)
    public List<Person> getItAsXML(@QueryParam("name")String name,@QueryParam("age") int age) {
    	List<Person> persons = new ArrayList<>();
    	Person person =new Person(name, age);
    	persons.add(person);
    	return persons;
    }
    
    
    @GET
    @Path("/bean")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getItAsXML(@BeanParam Person person) {    	
    	return person;
    }
    
    @POST
    @Produces(MediaType.APPLICATION_XML)
    public Response savePerson(Person person) {
    	person.setName("sdasd");
    	person.setAge(48);
    	Response resp = Response.status(Status.OK).entity(person).build();
    	return resp;
    }

}
