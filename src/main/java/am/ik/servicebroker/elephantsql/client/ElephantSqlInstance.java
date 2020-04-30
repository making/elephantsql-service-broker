package am.ik.servicebroker.elephantsql.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URI;

public class ElephantSqlInstance {
    private final Integer id;
    private final ElephantSqlPlan plan;
    private final ElephantSqlRegion region;
    private final String name;
    private final URI url;
    private final boolean ready;

    @JsonCreator
    public ElephantSqlInstance(@JsonProperty("id") Integer id,
                               @JsonProperty("plan") ElephantSqlPlan plan,
                               @JsonProperty("region") ElephantSqlRegion region,
                               @JsonProperty("name") String name,
                               @JsonProperty("url") URI url,
                               @JsonProperty("ready") boolean ready) {
        this.id = id;
        this.plan = plan;
        this.region = region;
        this.name = name;
        this.url = url;
        this.ready = ready;
    }

    public Integer getId() {
        return id;
    }

    public ElephantSqlPlan getPlan() {
        return plan;
    }

    public ElephantSqlRegion getRegion() {
        return region;
    }

    public String getName() {
        return name;
    }

    public URI getUrl() {
        return url;
    }

    public boolean isReady() {
        return ready;
    }

    @Override
    public String toString() {
        return "ElephantSqlInstance{" +
                "id=" + id +
                ", plan=" + plan +
                ", region=" + region +
                ", name='" + name + '\'' +
                ", url=" + url +
                ", ready=" + ready +
                '}';
    }
}
