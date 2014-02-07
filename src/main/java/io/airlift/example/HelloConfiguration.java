package io.airlift.example;

import io.airlift.configuration.Config;
import io.airlift.configuration.ConfigDescription;

import javax.validation.constraints.NotNull;

public class HelloConfiguration
{
    private String hello = "hello";

    @NotNull
    public String getHello() {
        return hello;
    }

    @Config("hello.text")
    @ConfigDescription("Phrase that means hello")
    public HelloConfiguration setHello(String hello) {
        this.hello = hello;
        return this;
    }
}
