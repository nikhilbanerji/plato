package ai.plato.plato.service;

import ai.plato.plato.client.SpoonacularClient;
import ai.plato.plato.exception.NotFoundException;
import ai.plato.plato.model.Ingredient;
import ai.plato.plato.model.IngredientSearchResult;
import ai.plato.plato.model.Intolerance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class IngredientService {

    private static final Logger log = LoggerFactory.getLogger(RecipeService.class);
    private final SpoonacularClient spoonacularClient;

    public IngredientService(SpoonacularClient spoonacularClient) {
        this.spoonacularClient = spoonacularClient;
    }

    // Search for ingredients
    public IngredientSearchResult searchIngredients(
            String query,
            Boolean addChildren,
            Integer minProteinPercent,
            Integer maxProteinPercent,
            Integer minFatPercent,
            Integer maxFatPercent,
            Integer minCarbsPercent,
            Integer maxCarbsPercent,
            Boolean metaInformation,
            Set<Intolerance> intolerances,
            String sort,
            String sortDirection,
            String language,
            Integer offset,
            Integer number
    ) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("query", query); // required
        queryParams.put("metaInformation", metaInformation.toString());
        queryParams.put("sortDirection", sortDirection);
        queryParams.put("number", number.toString());
        queryParams.put("offset", offset.toString());
        queryParams.put("language", language);

        if (addChildren != null) {
            queryParams.put("addChildren", addChildren.toString());
        }
        if (minProteinPercent != null) {
            queryParams.put("minProteinPercent", minProteinPercent.toString());
        }
        if (maxProteinPercent != null) {
            queryParams.put("maxProteinPercent", maxProteinPercent.toString());
        }
        if (minFatPercent != null) {
            queryParams.put("minFatPercent", minFatPercent.toString());
        }
        if (maxFatPercent != null) {
            queryParams.put("maxFatPercent", maxFatPercent.toString());
        }
        if (minCarbsPercent != null) {
            queryParams.put("minCarbsPercent", minCarbsPercent.toString());
        }
        if (maxCarbsPercent != null) {
            queryParams.put("maxCarbsPercent", maxCarbsPercent.toString());
        }
        if (intolerances != null && !intolerances.isEmpty()) {
            // Convert enum list to comma-separated string
            String intoleranceParam = intolerances.stream()
                    .map(Enum::name)
                    .collect(Collectors.joining(","));
            queryParams.put("intolerances", intoleranceParam);
        }
        if (sort != null && !sort.isEmpty()) {
            queryParams.put("sort", sort);
        }

        try {
            log.info("Fetching ingredients with query: {}", query);
            IngredientSearchResult response = spoonacularClient.getIngredients(queryParams);
            if (response == null || response.results() == null || response.results().isEmpty()) {
                throw new NotFoundException("Ingredients with query " + query + " not found.");
            }
            return response;
        } catch (HttpClientErrorException.NotFound e) {
            throw new NotFoundException("Ingredients with query " + query + " not found.", e);
        } catch (Exception e) {
            log.error("Error calling Spoonacular API for ingredient search", e);
            throw new RuntimeException("Error calling Spoonacular API", e);
        }
    }

    public Set<Ingredient> autocompleteIngredientSearch(
            String query,
            Integer number,
            String language,
            Boolean metaInformation,
            Set<Intolerance> intolerances
    ) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("query", query);
        queryParams.put("number", number.toString());
        queryParams.put("language", language);
        queryParams.put("metaInformation", metaInformation.toString());

        if (intolerances != null && !intolerances.isEmpty()) {
            // Convert enum list to comma-separated string
            String intoleranceParam = intolerances.stream()
                    .map(Enum::name)
                    .collect(Collectors.joining(","));
            queryParams.put("intolerances", intoleranceParam);
        }

        try {
            log.info("Fetching autocomplete ingredients for query: {}", query);
            Set<Ingredient> response = spoonacularClient.autocompleteIngredientSearch(queryParams);
            if (response == null || response.isEmpty()) {
                throw new NotFoundException("Ingredients matching query " + query + " not found.");
            }
            return response;
        } catch (HttpClientErrorException.NotFound e) {
            throw new NotFoundException("Ingredients matching query " + query + " not found.", e);
        } catch (Exception e) {
            log.error("Error calling Spoonacular API for ingredient autocomplete", e);
            throw new RuntimeException("Error calling Spoonacular API", e);
        }
    }
}
