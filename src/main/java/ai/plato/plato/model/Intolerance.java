package ai.plato.plato.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Intolerance {
    DAIRY("Dairy"),
    EGG("Egg"),
    GLUTEN("Gluten"),
    GRAIN("Grain"),
    PEANUT("Peanut"),
    SEAFOOD("Seafood"),
    SESAME("Sesame"),
    SHELLFISH("Shellfish"),
    SOY("Soy"),
    SULFITE("Sulfite"),
    TREE_NUT("Tree Nut"),
    WHEAT("Wheat");

    private final String displayName;

    Intolerance(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Jackson uses the return value of this method when serializing
     * the enum to JSON.
     */
    @JsonValue
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Jackson will use this to deserialize a string like "Gluten"
     * back into Cuisine.GLUTEN. This means this endpoint can accept
     * "Gluten" in request bodies or query params mapped to Intolerance.
     */
    @JsonCreator
    public static Intolerance fromDisplayName(String displayName) {
        for (Intolerance intolerance : Intolerance.values()) {
            if (intolerance.displayName.equalsIgnoreCase(displayName)) {
                return intolerance;
            }
        }
        throw new IllegalArgumentException("Unknown intolerance: " + displayName);
    }
}

