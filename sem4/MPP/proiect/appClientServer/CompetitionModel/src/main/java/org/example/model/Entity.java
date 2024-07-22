package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class Entity<Tid> implements Serializable {
    @java.io.Serial
    private static final long serialVersionUID = 123456789L;

    @JsonProperty("id")
    private Tid id;

    public Tid getId() {
        return id;
    }

    public void setId(Tid id) {
        this.id = id;
    }
}
