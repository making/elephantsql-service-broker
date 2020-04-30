package am.ik.servicebroker.elephantsql.client;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@ConfigurationProperties(prefix = "elephantsql")
@Component
public class ElephantSql implements Validator {
    private String uri = "https://customer.elephantsql.com";
    private String apiKey = "";

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ElephantSql.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        final ElephantSql elephantSql = (ElephantSql) target;
        if (StringUtils.isEmpty(elephantSql.getApiKey())) {
            errors.rejectValue("apiKey", "NotEmpty", "'apiKey' must not be empty.");
        }
    }
}