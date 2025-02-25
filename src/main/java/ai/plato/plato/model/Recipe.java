package ai.plato.plato.model;

import java.util.List;
import org.apache.solr.client.solrj.beans.Field;

public class Recipe {

    @Field
    private String id;

    @Field
    private List<String> title;

    @Field
    private List<String> ingredients;

    @Field
    private List<String> instructions;

    @Field("picture_link")
    private String pictureLink;

    public Recipe() {
    }

    public Recipe(String id, List<String> title, List<String> ingredients, List<String> instructions, String pictureLink) {
        this.id = id;
        this.title = title;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.pictureLink = pictureLink;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }
}
