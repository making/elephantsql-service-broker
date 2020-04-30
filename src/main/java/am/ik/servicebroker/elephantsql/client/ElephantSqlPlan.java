package am.ik.servicebroker.elephantsql.client;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum ElephantSqlPlan {
    TURTLE;

    @JsonCreator
    public static ElephantSqlPlan of(String value) {
        return Arrays.stream(values()).filter(v -> v.name().equalsIgnoreCase(value))
                .findAny().orElse(null);
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
