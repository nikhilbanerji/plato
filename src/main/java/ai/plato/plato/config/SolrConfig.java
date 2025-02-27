package ai.plato.plato.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.LBHttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.Optional;

/**
 * SolrConfig provides the configuration for connecting to a Solr instance.
 * It defines the SolrClient bean required for interacting with Solr.
 *
 * <p>
 * Initially, CloudSolrClient was considered for a SolrCloud setup. However, it relies entirely on HTTP/2,
 * which caused issues due to missing required Jetty classes. Additionally, Solr pulled in conflicting versions
 * of Jetty (Jetty 10 and Jetty 12), leading to class-loading errors and runtime failures.
 * </p>
 *
 * <p>
 * To resolve these issues, LBHttpSolrClient was chosen as a fallback. This allows us to stay compatible with the
 * latest versions of Spring and SolrJ while ensuring stability. The tradeoff is that we lose HTTP/2 support,
 * but this configuration avoids dependency conflicts and ensures a reliable connection to Solr.
 * </p>
 */
@Configuration
public class SolrConfig {

    /**
     * Creates and returns a SolrClient bean configured to connect to a Solr instance.
     *
     * <p>
     * The CloudSolrClient configuration has been commented out due to its strict HTTP/2 dependency
     * and the requirement for missing Jetty classes. Additionally, conflicts between Jetty 10 and
     * Jetty 12 caused instability in the application.
     * </p>
     *
     * <p>
     * Instead, LBHttpSolrClient is used as it supports HTTP/1.1 and does not introduce these conflicts.
     * This ensures stable integration with Solr while maintaining compatibility with the rest of our stack.
     * </p>
     *
     * @return A configured SolrClient instance.
     */
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
