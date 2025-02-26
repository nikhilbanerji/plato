package ai.plato.plato.model;

import java.util.List;

/**
 * RecipePage represents a paginated response containing a list of recipes.
 * It includes information about the page number, page size, and total number of available documents.
 */
public class RecipePage {

    private List<Recipe> recipes;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalDocs;

    /**
     * Constructs a RecipePage with the specified parameters.
     *
     * @param recipes The list of recipes on the current page.
     * @param pageNumber The current page number.
     * @param pageSize The number of recipes per page.
     * @param totalDocs The total number of documents available.
     */
    public RecipePage(List<Recipe> recipes, Integer pageNumber, Integer pageSize, Long totalDocs) {
        this.recipes = recipes;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalDocs = totalDocs;
    }

    /**
     * Getter and Setter methods for RecipePage attributes.
     */
    public List<Recipe> getRecipes() {
        return recipes;
    }
    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }
    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalDocs() {
        return totalDocs;
    }
    public void setTotalDocs(Long totalDocs) {
        this.totalDocs = totalDocs;
    }
}
