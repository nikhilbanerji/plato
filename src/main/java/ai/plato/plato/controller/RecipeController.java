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

@RestController
@RequestMapping("/plato/recipes")
public class RecipeController {
    private static final Logger log = LoggerFactory.getLogger(RecipeController.class);
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    // Fetch all recipes by page number and page size
    @GetMapping("")
    RecipePage findAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") @Positive Integer pageNumber,
            @RequestParam(name = "size", required = false, defaultValue = "20") @Range(min = 20, max = 100) Integer pageSize
    ) {
        log.info("Controller: Request to fetch " +pageSize + " recipes on page " + pageNumber);
        return recipeService.findAll(pageNumber, pageSize);
    }

    // Search for recipes
    @GetMapping("/search")
    RecipePage searchRecipes(
            @RequestParam(name = "query", required = true) @NotEmpty(message = "Query required") String query,
            @RequestParam(name = "page", required = false, defaultValue = "1") @Positive Integer pageNumber,
            @RequestParam(name = "size", required = false, defaultValue = "20") @Range(min = 20, max = 100) Integer pageSize,
            @RequestParam(name = "matchTypes", required = false) Set<String> matchTypes,
            @RequestParam(name = "uploadedBy", required = false) String uploadedBy,
            @RequestParam(name = "minCookingTime", required = false) @PositiveOrZero Integer minCookingTime,
            @RequestParam(name = "maxCookingTime", required = false) @PositiveOrZero Integer maxCookingTime) {
        log.info("Controller: Request to fetch " + pageSize + " recipes on page " + pageNumber
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

    // Fetch random recipes
    @GetMapping("/random")
    RecipePage findRandom(
            @RequestParam(name = "page", required = false, defaultValue = "1") @Positive Integer pageNumber,
            @RequestParam(name = "size", required = false, defaultValue = "20") @Range(min = 20, max = 100) Integer pageSize
    ) {
        log.info("Controller: Request to fetch " +pageSize + " random recipes on page " + pageNumber);
        return recipeService.findRandom(pageNumber, pageSize);
    }
}
