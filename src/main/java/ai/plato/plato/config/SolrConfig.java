package ai.plato.plato.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.LBHttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.Optional;

@Configuration
public class SolrConfig {

    @Bean
    public SolrClient solrClient() {
//        return new CloudSolrClient.Builder(
//                Collections.singletonList("localhost:9983"),
//                Optional.empty())
//                .withDefaultCollection("recipes")
//                .build();
        return new LBHttpSolrClient.Builder()
                .withBaseSolrUrls(
                        "http://localhost:8983/solr/recipes"
                )
                .build();

    }
}
