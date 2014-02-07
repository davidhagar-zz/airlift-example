package io.airlift.example;

import com.google.inject.Injector;
import io.airlift.bootstrap.Bootstrap;
import io.airlift.bootstrap.LifeCycleManager;
import io.airlift.http.client.ApacheHttpClient;
import io.airlift.http.client.FullJsonResponseHandler;
import io.airlift.http.client.HttpClient;
import io.airlift.http.client.StatusResponseHandler.StatusResponse;
import io.airlift.http.client.StringResponseHandler;
import io.airlift.http.server.testing.TestingHttpServer;
import io.airlift.http.server.testing.TestingHttpServerModule;
import io.airlift.jaxrs.JaxrsModule;
import io.airlift.jmx.JmxHttpModule;
import io.airlift.jmx.JmxModule;
import io.airlift.json.JsonCodec;
import io.airlift.json.JsonModule;
import io.airlift.node.testing.TestingNodeModule;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.URI;
import java.util.Map;

import static io.airlift.http.client.FullJsonResponseHandler.JsonResponse;
import static io.airlift.http.client.FullJsonResponseHandler.createFullJsonResponseHandler;
import static io.airlift.http.client.Request.Builder.prepareGet;
import static io.airlift.http.client.StatusResponseHandler.createStatusResponseHandler;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.OK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class TestServer
{
    private HttpClient client;
    private TestingHttpServer server;
    private LifeCycleManager lifeCycleManager;

    @BeforeMethod
    public void setup()
            throws Exception
    {
        Bootstrap app = new Bootstrap(
                new TestingNodeModule(),
                new TestingHttpServerModule(),
                new JsonModule(),
                new JaxrsModule(),
                new JmxHttpModule(),
                new JmxModule(),
                new MainModule());

        Injector injector = app
                .strictConfig()
                .doNotInitializeLogging()
                .initialize();

        lifeCycleManager = injector.getInstance(LifeCycleManager.class);
        server = injector.getInstance(TestingHttpServer.class);
        client = new ApacheHttpClient();
    }

    @AfterMethod
    public void teardown()
            throws Exception
    {
        if (lifeCycleManager != null) {
            lifeCycleManager.stop();
        }
    }

    @Test
    public void testVersion2HelloExpectedResponse()
            throws Exception
    {
        JsonResponse<Map<String, Object>> response =
            client.execute(prepareGet().setUri(uriFor("/v2/hello/david")).build(),
                    createFullJsonResponseHandler(JsonCodec.mapJsonCodec(String.class, Object.class)));
        Map<String, Object> responseBody = response.getValue();
        assertEquals(response.getStatusCode(), OK.getStatusCode());
        assertTrue(responseBody.containsKey("salutations"));
        assertEquals(responseBody.get("salutations"), "hello, david");
    }

    @Test
    public void testVersion3HelloExpectedResponse()
            throws Exception
    {
        JsonResponse<Map<String, Object>> response =
                client.execute(prepareGet().setUri(uriFor("/v3/hello/david")).build(),
                        createFullJsonResponseHandler(JsonCodec.mapJsonCodec(String.class, Object.class)));
        Map<String, Object> responseBody = response.getValue();
        assertEquals(response.getStatusCode(), OK.getStatusCode());
        assertTrue(responseBody.containsKey("salutations"));
        assertEquals(responseBody.get("salutations"), "hello, david");
    }

    @Test
    public void testVersion3HelloErrorResponse()
            throws Exception
    {
        JsonResponse<Map<String, Object>> response =
                client.execute(prepareGet().setUri(uriFor("/v3/hello/aaron")).build(),
                        createFullJsonResponseHandler(JsonCodec.mapJsonCodec(String.class, Object.class)));
        Map<String, Object> responseBody = response.getValue();
        assertEquals(response.getStatusCode(), BAD_REQUEST.getStatusCode());
        assertTrue(responseBody.containsKey("error"));
        assertEquals(responseBody.get("error"), "We don't like aaron");
    }

    private URI uriFor(String path)
    {
        return server.getBaseUrl().resolve(path);
    }
}
