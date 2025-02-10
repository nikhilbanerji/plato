package ai.plato.plato.model;

import java.util.List;

/**
 * Represents a single analyzed instruction set for a recipe.
 * Typically, the API returns an array of these objects.
 */
public record AnalyzedInstructions(
        String name,                   // The name of this instruction set (may be empty).
        List<AnalyzedStep> steps       // A list of individual steps.
) {}

/**
 * Represents a single step within an analyzed instruction.
 */
record AnalyzedStep(
        List<Equipment> equipment,         // The equipment used in this step.
        List<AnalyzedIngredient> ingredients,  // The ingredients used in this step.
        Integer number,                    // The step number.
        String step,                       // The instruction text.
        StepLength length                // (Optional) The duration of this step (if provided).
) {}

/**
 * Represents a piece of equipment used in a step.
 */
record Equipment(
        Integer id,                // Unique identifier of the equipment.
        String image,              // Image filename (e.g., "oven.jpg").
        String name,               // Name of the equipment (e.g., "oven").
        Temperature temperature    // (Optional) Temperature details if available.
) {}

/**
 * Represents the temperature details for a piece of equipment.
 */
record Temperature(
        Double number,    // The temperature value (e.g., 200.0).
        String unit       // The unit of temperature (e.g., "Fahrenheit").
) {}

/**
 * Represents an ingredient used in a step.
 */
record AnalyzedIngredient(
        Integer id,       // Unique identifier of the ingredient.
        String image,     // Image filename for the ingredient.
        String name       // Name of the ingredient.
) {}


/**
 * Represents the duration (length) of a step.
 */
record StepLength(
        Double number,    // The numeric duration (e.g., 15).
        String unit       // The unit of duration (e.g., "minutes").
) {}
