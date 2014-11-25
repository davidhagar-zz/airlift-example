package io.airlift.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HelloPerson
{
    private final String greeting;

    public HelloPerson(Person person) {
        this.greeting = "Hello " + person.getFirstName() + " " + person.getLastName();
    }

    @JsonProperty
    public String getGreeting() {
        return greeting;
    }

}
