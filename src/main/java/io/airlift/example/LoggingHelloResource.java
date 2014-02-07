package io.airlift.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import io.airlift.http.client.FullJsonResponseHandler;
import io.airlift.http.client.ResponseHandler;
import io.airlift.json.JsonCodec;
import io.airlift.log.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import static io.airlift.http.client.FullJsonResponseHandler.createFullJsonResponseHandler;

@Path("/v3/hello")
public class LoggingHelloResource
{
    private static final Logger log = Logger.get(LoggingHelloResource.class);


    public static class HelloContainer
    {
        private final String salutations;

        public HelloContainer(String salutations)
        {
            this.salutations = salutations;
        }

        @JsonProperty("salutations")
        public String getSalutations()
        {
            return salutations;
        }
    }

    public static class ErrorContainer
    {
        private final String error;

        public ErrorContainer(String error)
        {
            this.error = error;
        }

        @JsonProperty("error")
        public String getError()
        {
            return error;
        }
    }

    private final String hello;

    @Inject
    public LoggingHelloResource(HelloConfiguration config)
    {
        hello = config.getHello();
        log.info("Using hello text %s", hello);
    }

    @GET
    @Produces("application/json")
    @Path("/{name}")
    public Response sayHello(@PathParam("name") String name)
    {
        log.debug("Call made to sayHello, name was %s", name);
        ResponseHandler handler = createFullJsonResponseHandler(JsonCodec.jsonCodec(HelloContainer.class));
        if ("aaron".equalsIgnoreCase(name)) {
            log.error("{\"error\" : \"We don't like %s!\"}", name);

            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorContainer(String.format("We don't like %s", name))).build();
        }
        log.info("Said hello to %s", name);
        return Response.ok(new HelloContainer(Joiner.on(", ").join(ImmutableList.of(hello, name)))).build();
    }
}
