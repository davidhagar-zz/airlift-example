package io.airlift.example;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/v1/helloJson")
public class JsonConsumingResource
{
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public HelloPerson getGreeting(Person person) {
        return new HelloPerson(person);
    }
}
