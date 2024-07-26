package com.example;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestGreeter2 {

    private Greeter2 greeter;

    @BeforeEach
    public void setup() {
        greeter = new Greeter2();
    }

    @Test
    public void greetShouldIncludeTheOneBeingGreeted() {
        String someone = "World";

        assertThat(greeter.greet(someone), containsString(someone));
    }

    @Test
    public void greetShouldIncludeGreetingPhrase() {
        String someone = "World";

        assertThat(greeter.greet(someone).length(), is(greaterThan(someone.length())));
    }
}
