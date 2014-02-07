package io.airlift.example;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestHelloResource
{
    @Test
    public void testNormalResponse()
    {
        HelloResource resource = new HelloResource();
        String response = resource.sayHello("David");
        assertEquals(response, "hello, David");
    }

    @Test
    public void testNullInput()
    {
        HelloResource resource = new HelloResource();
        String response = resource.sayHello(null);
        assertEquals(response, "hello, null");
    }
}
