package ai.plato.plato.service;

import ai.plato.plato.exception.NotFoundException;
import ai.plato.plato.exception.RecipeSearchException;
import ai.plato.plato.model.Recipe;
import ai.plato.plato.model.RecipePage;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    private static final Logger log = LoggerFactory.getLogger(RecipeService.class);
    private final SolrClient solrClient;

    public RecipeService(SolrClient solrClient) {
        this.solrClient = solrClient;
    }

    // Fetch all recipes by page number and size of page
    public RecipePage findAll(Integer pageNumber, Integer pageSize) {
        // Solr indexing starts from 0 so page number needs to be adjusted
        Integer solrPageNumber = pageNumber - 1;

        final SolrQuery query = new SolrQuery("*:*");
        query.setStart(solrPageNumber * pageSize);
        query.setRows(pageSize);

        try {
            log.info("Fetching " +pageSize + " recipes on solr page " + solrPageNumber);

            final QueryResponse response = solrClient.query(query);

            long totalDocs = response.getResults().getNumFound();
            boolean exact = response.getResults().getNumFoundExact();
            List<Recipe> recipes = response.getBeans(Recipe.class);

            if (recipes == null || recipes.isEmpty()) {
                throw new NotFoundException("No recipes found");
            }

            return new RecipePage(recipes, pageNumber, pageSize, totalDocs);
        } catch (IOException | SolrServerException e) {
            throw new RecipeSearchException("Failed to query Solr", e);
        }
    }

    // Fetch recipes by ingredients
    public RecipePage findByIngredients(List<String> ingredients, Integer pageNumber, Integer pageSize, String matchType) {
        // Solr indexing starts from 0 so page number needs to be adjusted
        Integer solrPageNumber = pageNumber - 1;

        String queryString;
        switch(matchType) {
            case "exact":
                // Match recipes that have ALL ingredients
                queryString = ingredients.stream()
                        .map(ingredient -> "ingredients:\"" + ingredient + "\"")
                        .collect(Collectors.joining(" AND "));
                break;
            case "partial":
                // Match recipes that have MOST ingredients
                queryString = "{!edismax qf=ingredients pf=ingredients}" + String.join(" ", ingredients);
                break;
            default:
                // Return recipes that contain ANY of the ingredients
                queryString = ingredients.stream()
                        .map(ingredient -> "ingredients:\"" + ingredient + "\"")
                        .collect(Collectors.joining(" OR "));
                break;
        }

        final SolrQuery query = new SolrQuery(queryString);
        query.setStart(solrPageNumber * pageSize);
        query.setRows(pageSize);

        try {
            log.info("Fetching " + pageSize + " recipes using " + matchType + " match on solr page " + solrPageNumber + " for ingredients: " + ingredients);

            final QueryResponse response = solrClient.query(query);

            long totalDocs = response.getResults().getNumFound();
            List<Recipe> recipes = response.getBeans(Recipe.class);

            if (recipes == null || recipes.isEmpty()) {
                throw new NotFoundException("No recipes found");
            }

            return new RecipePage(recipes, pageNumber, pageSize, totalDocs);
        } catch (IOException | SolrServerException e) {
            throw new RecipeSearchException("Failed to query Solr", e);
        }
    }

    // Fetch random recipes by page number and size of page
    public RecipePage findRandom(Integer pageNumber, Integer pageSize) {
        // Solr indexing starts from 0 so page number needs to be adjusted
        Integer solrPageNumber = pageNumber - 1;

        final SolrQuery query = new SolrQuery("*:*");
        query.setStart(solrPageNumber * pageSize);
        query.setRows(pageSize);
        query.addSort("random_" + System.currentTimeMillis(), SolrQuery.ORDER.asc);

        try {
            log.info("Fetching " +pageSize + " random recipes on solr page " + solrPageNumber);

            final QueryResponse response = solrClient.query(query);

            long totalDocs = response.getResults().getNumFound();
            List<Recipe> recipes = response.getBeans(Recipe.class);

            if (recipes == null || recipes.isEmpty()) {
                throw new NotFoundException("No recipes found");
            }

            return new RecipePage(recipes, pageNumber, pageSize, totalDocs);
        } catch (IOException | SolrServerException e) {
            throw new RecipeSearchException("Failed to query Solr", e);
        }
    }
}
