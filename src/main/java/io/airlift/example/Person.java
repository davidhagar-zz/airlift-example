package io.airlift.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Person
{
    private final String firstName;
    private final String lastName;

    @JsonCreator
    public Person(@JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @JsonProperty
    public String getFirstName()
    {
        return firstName;
    }

    @JsonProperty
    public String getLastName()
    {
        return lastName;
    }
}
