package io.airlift.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import io.airlift.log.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/v2/hello")
public class ConfigurableHelloResource
{
    private static final Logger log = Logger.get(ConfigurableHelloResource.class);

    public static class HelloContainer
    {
        private final String salutations;

        public HelloContainer(String salutations)
        {
            this.salutations = salutations;
        }

        @JsonProperty
        public String getSalutations()
        {
            return salutations;
        }
    }

    private final String hello;

    @Inject
    public ConfigurableHelloResource(HelloConfiguration config)
    {
        hello = config.getHello();
        log.info("Using hello text %s", hello);
    }

    @GET
    @Produces("application/json")
    @Path("/{name}")
    public HelloContainer sayHello(@PathParam("name") String name)
    {
        return new HelloContainer(Joiner.on(", ").join(ImmutableList.of(hello, name)));
    }
}
