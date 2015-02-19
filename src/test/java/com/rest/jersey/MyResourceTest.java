package com.rest.jersey;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.rest.jersey.Main;
import com.rest.jersey.Person;

public class MyResourceTest {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();
        c.register(JacksonFeature.class);
        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
         List<Person>  src = target.path("myresource/xml").request().accept(MediaType.APPLICATION_XML).get(new GenericType<List<Person>>(){});
         for(Person p:src){
        	 System.out.println(p.getName() +" " +p.getAge());
         }
    }
    
    @Test
    public void testGetItBean() {
    	Person person = new Person("rod",45);
        List<Person>  src = target.path("myresource/bean").queryParam("name",  "frfrf").queryParam("age",  87)
        	.request().accept(MediaType.APPLICATION_XML).get(new GenericType<List<Person>>(){});
        for(Person p:src){
       	 System.out.println(p.getName() +" " +p.getAge());
        }
   }
    
    @Test
    public void testGetItBeanAsJson() {
    	
        Person  p = target.path("myresource/bean").queryParam("name",  "test1").queryParam("age",  97)
        	.request().accept(MediaType.APPLICATION_JSON).get(new GenericType<Person>(){});      
       	 System.out.println(p.getName() +" " +p.getAge());
       
   }
    
    @Test
    public void testPost(){
    	Person person = new Person("adsf",20);
        Entity<Person> entity = Entity.entity(person, MediaType.APPLICATION_XML);
        person = target.path("myresource").request().post(entity,Person.class);
        System.out.println(person.getName() +" " +person.getAge());
    }
    
    @Test
    public void testGetWithJson(){
    	 List<Person>  src = target.path("myresource/json").request().accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Person>>(){});
         for(Person p:src){
        	 System.out.println(p.getName() +" " +p.getAge());
         }
    }
    
    
    @Test
    public void testGetWithParam(){
    	Person person = new Person("adsf",20);
        Entity<Person> entity = Entity.entity(person, MediaType.APPLICATION_XML);
        person = target.path("myresource").request().post(entity,Person.class);
        System.out.println(person.getName() +" " +person.getAge());
    }
        
}
