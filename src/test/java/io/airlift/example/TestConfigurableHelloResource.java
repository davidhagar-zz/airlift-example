package io.airlift.example;

import org.testng.annotations.Test;

import javax.ws.rs.core.Response;

import static org.testng.Assert.assertEquals;

public class TestConfigurableHelloResource
{
    @Test
    public void testNormalResponse()
    {
        HelloConfiguration configuration = new HelloConfiguration().setHello("ni hao");
        ConfigurableHelloResource configurableHello = new ConfigurableHelloResource(configuration);
        ConfigurableHelloResource.HelloContainer hello = configurableHello.sayHello("david");
        assertEquals(hello.getSalutations(), "ni hao, david");
    }
}
