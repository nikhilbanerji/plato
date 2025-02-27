package ai.plato.plato.helper;

import org.apache.solr.client.solrj.SolrQuery;

import java.util.HashSet;
import java.util.Set;

/**
 * RecipeSolrQueryBuilder is responsible for constructing Solr queries for recipe search.
 * It allows customization of search parameters, filtering, boosting, and pagination.
 * <p>
 * The builder pattern is used to create and refine the SolrQuery object step by step.
 * </p>
 */
public class RecipeSolrQueryBuilder {

    private final SolrQuery solrQuery;
    private final Set<String> additionalFilters = new HashSet<>();

    /**
     * Initializes the query builder with a base query string.
     *
     * @param query The search query string.
     */
    public RecipeSolrQueryBuilder(String query) {
        solrQuery = new SolrQuery();
        solrQuery.setQuery(query);
        solrQuery.set("defType", "edismax");
    }

    /**
     * Applies match types to determine which fields are searched.
     *
     * @param matchTypes A set of field names to prioritize in search.
     * @return The updated RecipeSolrQueryBuilder instance.
     */
    public RecipeSolrQueryBuilder applyMatchTypes(Set<String> matchTypes) {
        StringBuilder qfBuilder = new StringBuilder();

        if (matchTypes != null && !matchTypes.isEmpty()) {
            if (matchTypes.contains("title")) {
                qfBuilder.append("title^2 ");
            }
            if (matchTypes.contains("ingredients")) {
                qfBuilder.append("ingredients ");
            }
            if (matchTypes.contains("cuisines")) {
                qfBuilder.append("cuisines ");
            }
            if (matchTypes.contains("uploadedBy")) {
                qfBuilder.append("uploaded_by ");
            }
        } else {
            // If no match types specified, default to title and ingredients
            qfBuilder.append("title^2 ingredients");
        }

        solrQuery.set("qf", qfBuilder.toString().trim());
        return this;
    }

    /**
     * Adds a filter clause to refine search results.
     * Example: cuisines:"Italian" or cooking_time:[10 TO 30]
     *
     * @param filterClause The Solr filter clause.
     * @return The updated RecipeSolrQueryBuilder instance.
     */
    public RecipeSolrQueryBuilder addFilter(String filterClause) {
        if (filterClause != null && !filterClause.trim().isEmpty()) {
            additionalFilters.add(filterClause);
        }
        return this;
    }

    /**
     * Sets a boost function to influence search ranking.
     *
     * @param bf The boost function string.
     * @return The updated RecipeSolrQueryBuilder instance.
     */
    public RecipeSolrQueryBuilder addBoostFunctions(String bf) {
        solrQuery.set("bf", bf);
        return this;
    }

    /**
     * Sets the pagination parameters for the query.
     *
     * @param start The starting index of results.
     * @param rows The number of results per page.
     * @return The updated RecipeSolrQueryBuilder instance.
     */
    public RecipeSolrQueryBuilder setStartAndRows(int start, int rows) {
        solrQuery.setStart(start);
        solrQuery.setRows(rows);
        return this;
    }

    /**
     * Combines additional filters with the main query and builds the final SolrQuery object.
     *
     * @return The constructed SolrQuery object ready for execution.
     */
    public SolrQuery build() {
        if (!additionalFilters.isEmpty()) {
            // Join filters with AND and add to the main query
            String filtersCombined = String.join(" AND ", additionalFilters);
            // Append filters to the main query
            solrQuery.setFilterQueries(filtersCombined);
        }
        return solrQuery;
    }
}
