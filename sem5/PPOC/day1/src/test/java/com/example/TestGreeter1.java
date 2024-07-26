package com.example;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestGreeter1 {

    public Greeter1 greeter1;

    @BeforeEach
    public void setup() {
        greeter1 = new Greeter1();
    }

    @Test
    public void greetShouldIncludeTheOneBeingGreeted() {
        String someone = "World";

        MatcherAssert.assertThat(greeter1.greet(someone), containsString(someone));
    }

    @Test
    public void greetShouldIncludeGreetingPhrase() {
        String someone = "World";

        MatcherAssert.assertThat(greeter1.greet(someone).length(), is(greaterThan(someone.length())));
    }
}
