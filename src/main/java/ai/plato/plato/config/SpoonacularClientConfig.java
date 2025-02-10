package ai.plato.plato.config;

import ai.plato.plato.client.SpoonacularClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootConfiguration
public class SpoonacularClientConfig {

    @Value("${spoonacular.api.key}")
    private String apiKey;

    @Bean
    public SpoonacularClient spoonacularClient() {
        String baseUrl = "https://api.spoonacular.com";
        String baseUrlWithApiKey = UriComponentsBuilder
                .fromHttpUrl(baseUrl)
                .queryParam("apiKey", apiKey)
                .toUriString();
        RestClient restClient = RestClient.create(baseUrlWithApiKey);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
        return factory.createClient(SpoonacularClient.class);
    }
}
