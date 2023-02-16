package com.example.consumingrest.responsemodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class Quote {
    private final String type;
    private final Value value;

    public Quote(String type, Value value) {
        this.type = type;
        this.value = value;
    }

    public String type() {
        return type;
    }

    public Value value() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Quote) obj;
        return Objects.equals(this.type, that.type) &&
                Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }

    @Override
    public String toString() {
        return "Quote[" +
                "type=" + type + ", " +
                "value=" + value + ']';
    }

}
/*
{
    "type": "success",
    "value": {
        "id": 7,
        "quote": "The real benefit of Boot, however, is that it's just Spring. That means any direction the code takes, regardless of complexity, I know it's a safe bet."
    }
}

 */