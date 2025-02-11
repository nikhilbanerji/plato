package ai.plato.plato.controller;

import ai.plato.plato.model.Ingredient;
import ai.plato.plato.model.IngredientSearchResult;
import ai.plato.plato.model.Intolerance;
import ai.plato.plato.service.IngredientService;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/plato/ingredients")
public class IngredientController {

    private static final Logger log = LoggerFactory.getLogger(RecipeController.class);
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    // Search for ingredients
    @GetMapping("/search")
    public IngredientSearchResult searchIngredients(
            @RequestParam(name = "query", required = true) @NotBlank(message = "Ingredient query is required") String query,
            @RequestParam(name = "addChildren", required = false, defaultValue = "false") Boolean addChildren,
            @RequestParam(name = "minProteinPercent", required = false) Integer minProteinPercent,
            @RequestParam(name = "maxProteinPercent", required = false) Integer maxProteinPercent,
            @RequestParam(name = "minFatPercent", required = false) Integer minFatPercent,
            @RequestParam(name = "maxFatPercent", required = false) Integer maxFatPercent,
            @RequestParam(name = "minCarbsPercent", required = false) Integer minCarbsPercent,
            @RequestParam(name = "maxCarbsPercent", required = false) Integer maxCarbsPercent,
            @RequestParam(name = "metaInformation", required = false, defaultValue = "false") Boolean metaInformation,
            @RequestParam(name = "intolerances", required = false) String intolerances,
            @RequestParam(name = "sort", required = false) String sort,
            @RequestParam(name = "sortDirection", required = false, defaultValue = "asc") String sortDirection,
            @RequestParam(name = "language", required = false, defaultValue = "en") String language,
            @RequestParam(name = "offset", required = false, defaultValue = "0") Integer offset,
            @RequestParam(name = "number", required = false, defaultValue = "10") @Range(min = 1, max = 100) Integer number
    ) {
        log.info("Controller: Request to search ingredients with query: {}", query);
        return ingredientService.searchIngredients(query, addChildren, minProteinPercent, maxProteinPercent,
                minFatPercent, maxFatPercent, minCarbsPercent, maxCarbsPercent,
                metaInformation, intolerances, sort, sortDirection, language, offset, number);
    }

    // Autocomplete ingredient search
    @GetMapping("/autocomplete")
    Set<Ingredient> autocompleteIngredientSearch(
            @RequestParam(name= "query", required = true) @NotBlank(message = "Ingredient query is required") String query,
            @RequestParam(name = "number", required = false, defaultValue = "10") @Range(min = 1, max = 100) Integer number,
            @RequestParam(name = "language", required = false, defaultValue = "en") String language,
            @RequestParam(name = "metaInformation", required = false, defaultValue = "true") Boolean metaInformation,
            @RequestParam(name = "intolerances", required = false) List<Intolerance> intolerances
    ) {
        log.info("Controller: Request to autocomplete ingredient search with query: {}", query);
        return ingredientService.autocompleteIngredientSearch(query, number, language, metaInformation, intolerances);
    }
}
