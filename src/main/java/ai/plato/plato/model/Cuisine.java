package ai.plato.plato.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Cuisine {

    AFRICAN("African"),
    ASIAN("Asian"),
    AMERICAN("American"),
    BRITISH("British"),
    CAJUN("Cajun"),
    CARIBBEAN("Caribbean"),
    CHINESE("Chinese"),
    EASTERN_EUROPEAN("Eastern European"),
    EUROPEAN("European"),
    FRENCH("French"),
    GERMAN("German"),
    GREEK("Greek"),
    INDIAN("Indian"),
    IRISH("Irish"),
    ITALIAN("Italian"),
    JAPANESE("Japanese"),
    JEWISH("Jewish"),
    KOREAN("Korean"),
    LATIN_AMERICAN("Latin American"),
    MEDITERRANEAN("Mediterranean"),
    MEXICAN("Mexican"),
    MIDDLE_EASTERN("Middle Eastern"),
    NORDIC("Nordic"),
    SOUTHERN("Southern"),
    SPANISH("Spanish"),
    THAI("Thai"),
    VIETNAMESE("Vietnamese");

    private final String displayName;

    Cuisine(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Jackson uses the return value of this method when serializing
     * the enum to JSON. So you'll see "African" instead of "AFRICAN".
     */
    @JsonValue
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Jackson will use this to deserialize a string like "African"
     * back into Cuisine.AFRICAN. This means your endpoint can accept
     * "African" in request bodies or query params mapped to Cuisine.
     */
    @JsonCreator
    public static Cuisine fromDisplayName(String displayName) {
        for (Cuisine c : Cuisine.values()) {
            if (c.displayName.equalsIgnoreCase(displayName)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Unknown cuisine: " + displayName);
    }
}

