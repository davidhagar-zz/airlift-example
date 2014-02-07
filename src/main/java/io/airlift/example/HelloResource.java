package io.airlift.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/v1/hello")
public class HelloResource
{
    @GET
    @Path("/{name}")
    public String sayHello(@PathParam("name") String name)
    {
        return String.format("hello, %s", name);
    }
}
