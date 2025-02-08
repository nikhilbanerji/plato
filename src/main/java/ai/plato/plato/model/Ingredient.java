package ai.plato.plato.model;

import java.util.List;

public record Ingredient(
        Integer id,
        String aisle,
        Double amount,
        String image,
        List<String> meta,
        String name,
        String original,
        String originalName,
        String unit,
        String unitLong,
        String unitShort,
        String extendedName, // Used only for search results
        // Fields below are specific to extended ingredient details:
        String consistency, // e.g., "SOLID"
        String nameClean,   // e.g., "boquerones"
        Measures measures   // Contains detailed measurements in different systems
) {}
