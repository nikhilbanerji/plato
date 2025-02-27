package ai.plato.plato.controller;

import ai.plato.plato.model.*;
import ai.plato.plato.service.RecipeService;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * RecipeController is responsible for handling HTTP requests related to recipes.
 * It provides endpoints to fetch all recipes, search for recipes, and retrieve random recipes.
 * <p>
 * The controller communicates with the RecipeService to perform the required operations.
 * It also logs incoming requests to facilitate debugging and tracking of API usage.
 * </p>
 */
@RestController
@RequestMapping("/plato/recipes")
public class RecipeController {
    private static final Logger log = LoggerFactory.getLogger(RecipeController.class);
    private final RecipeService recipeService;

    /**
     * Constructor for RecipeController.
     *
     * @param recipeService The service layer handling recipe-related business logic.
     */
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    /**
     * Fetches a paginated list of all available recipes.
     *
     * @param pageNumber The page number to retrieve (must be positive). Default is 1.
     * @param pageSize The number of recipes per page (must be between 20 and 100). Default is 20.
     * @return A paginated list of recipes.
     */
    @GetMapping("")
    RecipePage findAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") @Positive Integer pageNumber,
            @RequestParam(name = "size", required = false, defaultValue = "20") @Range(min = 20, max = 100) Integer pageSize
    ) {
        log.info("Request to fetch " +pageSize + " recipes on page " + pageNumber);
        return recipeService.findAll(pageNumber, pageSize);
    }

    /**
     * Searches for recipes based on a query and optional filters.
     *
     * @param query The search query string (must not be empty).
     * @param pageNumber The page number to retrieve (must be positive). Default is 1.
     * @param pageSize The number of recipes per page (must be between 20 and 100). Default is 20.
     * @param matchTypes A set of match types (optional filter).
     * @param uploadedBy The username of the uploader to filter results (optional).
     * @param minCookingTime The minimum cooking time in minutes (must be zero or positive, optional).
     * @param maxCookingTime The maximum cooking time in minutes (must be zero or positive, optional).
     * @return A paginated list of recipes that match the search criteria.
     */
    @GetMapping("/search")
    RecipePage searchRecipes(
            @RequestParam(name = "query", required = true) @NotEmpty(message = "Query required") String query,
            @RequestParam(name = "page", required = false, defaultValue = "1") @Positive Integer pageNumber,
            @RequestParam(name = "size", required = false, defaultValue = "20") @Range(min = 20, max = 100) Integer pageSize,
            @RequestParam(name = "matchTypes", required = false) Set<String> matchTypes,
            @RequestParam(name = "uploadedBy", required = false) String uploadedBy,
            @RequestParam(name = "minCookingTime", required = false) @PositiveOrZero Integer minCookingTime,
            @RequestParam(name = "maxCookingTime", required = false) @PositiveOrZero Integer maxCookingTime) {
        log.info("Request to fetch " + pageSize + " recipes on page " + pageNumber
                + "with matches on " + matchTypes + " for query: " + query);
        return recipeService.searchRecipes(
                query,
                pageNumber,
                pageSize,
                matchTypes,
                uploadedBy,
                minCookingTime,
                maxCookingTime);
    }

    /**
     * Fetches a paginated list of random recipes.
     *
     * @param pageNumber The page number to retrieve (must be positive). Default is 1.
     * @param pageSize The number of recipes per page (must be between 20 and 100). Default is 20.
     * @return A paginated list of randomly selected recipes.
     */
    @GetMapping("/random")
    RecipePage findRandom(
            @RequestParam(name = "page", required = false, defaultValue = "1") @Positive Integer pageNumber,
            @RequestParam(name = "size", required = false, defaultValue = "20") @Range(min = 20, max = 100) Integer pageSize
    ) {
        log.info("Request to fetch " +pageSize + " random recipes on page " + pageNumber);
        return recipeService.findRandom(pageNumber, pageSize);
    }
}
