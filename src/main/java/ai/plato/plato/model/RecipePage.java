package ai.plato.plato.model;

import java.util.List;

public class RecipePage {

    private List<Recipe> recipes;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalDocs;

    public RecipePage(List<Recipe> recipes, Integer pageNumber, Integer pageSize, Long totalDocs) {
        this.recipes = recipes;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalDocs = totalDocs;
    }

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
