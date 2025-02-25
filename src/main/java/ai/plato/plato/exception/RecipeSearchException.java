package ai.plato.plato.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception used to indicate an error occurred while querying Solr for recipes.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error searching recipes in Solr")
public class RecipeSearchException extends RuntimeException {

    public RecipeSearchException(String message) {
        super(message);
    }

    /**
     * Constructs a new RecipeSearchException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the throwable cause (IOException or SolrServerException)
     */
    public RecipeSearchException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new RecipeSearchException with the specified cause.
     *
     * @param cause the throwable cause (e.g., IOException or SolrServerException)
     */
    public RecipeSearchException(Throwable cause) {
        super(cause);
    }
}
