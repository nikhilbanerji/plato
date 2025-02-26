package ai.plato.plato.controller;

import ai.plato.plato.model.*;
import ai.plato.plato.service.RecipeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // Search for recipes by ingredients
    @GetMapping("/searchByIngredients")
    RecipePage findByIngredients (
            @RequestParam(name = "ingredients", required = true) @NotEmpty(message = "Ingredients required") List<String> ingredients,
            @RequestParam(name = "page", required = false, defaultValue = "1") @Positive Integer pageNumber,
            @RequestParam(name = "size", required = false, defaultValue = "20") @Range(min = 20, max = 100) Integer pageSize,
            @RequestParam(name = "matchType", required = false, defaultValue = "partial") String matchType
    ) {
        log.info("Controller: Request to fetch " + pageSize + " recipes with " + matchType + " match on page " + pageNumber + " for ingredients: " + ingredients);
        return recipeService.findByIngredients(ingredients, pageNumber, pageSize, matchType);
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
