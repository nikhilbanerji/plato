package ai.plato.plato.model;

import java.util.List;

public record Ingredient(
        Integer id,
        String image,
        String name,
        String aisle,
        Double amount,
        List<String> meta,
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

record Measures(
        Measure us,
        Measure metric
) {}

record Measure(
        double amount,
        String unitShort,
        String unitLong
) {}
