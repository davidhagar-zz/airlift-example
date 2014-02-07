package io.airlift.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/v1/hello")
public class HelloResource
{
    public class HelloContainer
    {
        private final String name;

        public HelloContainer(String name)
        {
            this.name = name;
        }

        @JsonProperty
        public String getName()
        {
            return name;
        }
    }

    @GET
    @Produces("application/json")
    @Path("/{name}")
    public HelloContainer sayHello(@PathParam("name") String name)
    {
        HelloContainer hello = new HelloContainer(name);
        return hello;
    }
}
