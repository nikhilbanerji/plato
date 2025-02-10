package ai.plato.plato.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

// A record representation of RecipeInformation generated from the spoonacular API model.
public record RecipeInformation(
        Integer id,
        String title,
        String image,
        String imageType,
        BigDecimal servings,
        Integer readyInMinutes,
        Integer preparationMinutes,
        Integer cookingMinutes,
        String license,
        String sourceName,
        String sourceUrl,
        String spoonacularSourceUrl,
        Integer aggregateLikes,
        BigDecimal healthScore,
        BigDecimal spoonacularScore,
        BigDecimal pricePerServing,
        List<AnalyzedInstructions> analyzedInstructions,
        Boolean cheap,
        String creditsText,
        List<String> cuisines,
        Boolean dairyFree,
        List<String> diets,
        String gaps,
        Boolean glutenFree,
        String instructions,
        Boolean lowFodmap,
        List<String> occasions,
        Boolean sustainable,
        Boolean vegan,
        Boolean vegetarian,
        Boolean veryHealthy,
        Boolean veryPopular,
        BigDecimal weightWatcherSmartPoints,
        List<String> dishTypes,
        Set<Ingredient> extendedIngredients,
        String summary,
//        RecipeInformationWinePairing winePairing,
//        TasteInformation taste,
        Integer originalId
) {}

