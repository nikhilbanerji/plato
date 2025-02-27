package ai.plato.plato.service;

import ai.plato.plato.exception.NotFoundException;
import ai.plato.plato.exception.RecipeSearchException;
import ai.plato.plato.helper.RecipeSolrQueryBuilder;
import ai.plato.plato.model.Recipe;
import ai.plato.plato.model.RecipePage;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

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

    // Fetch recipes
    public RecipePage searchRecipes(
            String query,
            Integer pageNumber,
            Integer pageSize,
            Set<String> matchTypes,
            String uploadedBy,
            Integer cookingTimeMin,
            Integer cookingTimeMax) {
        // Solr indexing starts from 0 so page number needs to be adjusted
        Integer solrPageNumber = pageNumber - 1;

        // Define boost functions for popularity/relevance: views, likes, like/dislike ratio
        String boostFunctions = "pow(log(sum(views,1)),0.5) pow(log(sum(likes,1)),1.0) pow(div(likes,sum(likes,dislikes,1)),1.5)";

        // Build Solr query using builder pattern
        RecipeSolrQueryBuilder builder = new RecipeSolrQueryBuilder(query)
                .applyMatchTypes(matchTypes)
                .addBoostFunctions(boostFunctions)
                .setStartAndRows(solrPageNumber * pageSize, pageSize);

        // Add additional filters (cooking time, prep time, creator, etc.)
        if (uploadedBy != null) {
            builder.addFilter("uploaded_by:\"" + uploadedBy + "\"");
        }
        if (cookingTimeMax != null) {
            builder.addFilter("cooking_time:[" + cookingTimeMin + " TO " + cookingTimeMax + "]");
        }

        final SolrQuery solrQuery = builder.build();

        try {
            log.info("RecipeService: Searching for recipes");

            final QueryResponse response = solrClient.query(solrQuery);
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
