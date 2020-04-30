package am.ik.servicebroker.elephantsql.client;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.Objects;

public enum ElephantSqlRegion {
    GOOGLE_COMPUTE_ENGINE_US_CENTRAL1("google-compute-engine", "us-central1"),
    GOOGLE_COMPUTE_ENGINE_SOUTHAMERICA_EAST1("google-compute-engine", "southamerica-east1"),
    GOOGLE_COMPUTE_ENGINE_EUROPE_WEST2("google-compute-engine", "europe-west2"),
    GOOGLE_COMPUTE_ENGINE_ASIA_EAST2("google-compute-engine", "asia-east2"),
    GOOGLE_COMPUTE_ENGINE_AUSTRALIA_SOUTHEAST1("google-compute-engine", "australia-southeast1"),
    AMAZON_WEB_SERVICES_US_EAST_1("amazon-web-services", "us-east-1"),
    AMAZON_WEB_SERVICES_EU_NORTH_1("amazon-web-services", "eu-north-1"),
    AMAZON_WEB_SERVICES_EU_WEST_1("amazon-web-services", "eu-west-1"),
    AMAZON_WEB_SERVICES_AP_SOUTHEAST_2("amazon-web-services", "ap-southeast-2"),
    AMAZON_WEB_SERVICES_AP_NORTHEAST_1("amazon-web-services", "ap-northeast-1"),
    AMAZON_WEB_SERVICES_AP_EAST_1("amazon-web-services", "ap-east-1"),
    AMAZON_WEB_SERVICES_SA_EAST_1("amazon-web-services", "sa-east-1"),
    AZURE_CENTRAL_US("azure-arm", "centralus"),
    AZURE_WEST_EUROPE("azure-arm", "westeurope"),
    //
    ;
    private final String provider;
    private final String region;

    ElephantSqlRegion(String provider, String region) {
        this.provider = provider;
        this.region = region;
    }

    public String getProvider() {
        return provider;
    }

    public String getRegion() {
        return region;
    }

    private static final String SEPARATOR = "::";

    @JsonCreator
    public static ElephantSqlRegion of(String region) {
        if (region == null) {
            return null;
        }
        final String[] split = region.split(SEPARATOR);
        return (split.length == 2) ? Arrays.stream(values())
                .filter(v -> Objects.equals(split[0], v.provider) && Objects.equals(split[1], v.region))
                .findAny().orElse(null) : null;
    }

    @Override
    public String toString() {
        return this.provider + SEPARATOR + this.region;
    }

    public static void main(String[] args) {
        for (final ElephantSqlRegion region : ElephantSqlRegion.values()) {
            System.out.println(region);
        }
    }
}
