package ai.plato.plato.helper;

import org.apache.solr.client.solrj.SolrQuery;

import java.util.HashSet;
import java.util.Set;

public class RecipeSolrQueryBuilder {

    private final SolrQuery solrQuery;
    private final Set<String> additionalFilters = new HashSet<>();

    public RecipeSolrQueryBuilder(String query) {
        solrQuery = new SolrQuery();
        solrQuery.setQuery(query);
        solrQuery.set("defType", "edismax");
    }

    /**
     * Applies match types to determine which fields are searched.
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
     * Adds additional filter clauses.
     * eg: cuisines:"Italian" or cooking_time:[10 TO 30]
     */
    public RecipeSolrQueryBuilder addFilter(String filterClause) {
        if (filterClause != null && !filterClause.trim().isEmpty()) {
            additionalFilters.add(filterClause);
        }
        return this;
    }

    /**
     * Sets boost functions.
     */
    public RecipeSolrQueryBuilder addBoostFunctions(String bf) {
        solrQuery.set("bf", bf);
        return this;
    }

    /**
     * Sets pagination parameters.
     */
    public RecipeSolrQueryBuilder setStartAndRows(int start, int rows) {
        solrQuery.setStart(start);
        solrQuery.setRows(rows);
        return this;
    }

    /**
     * Combines any additional filters with the main query.
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
