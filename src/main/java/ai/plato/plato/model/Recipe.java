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

    @Field("cooking_time")
    private Integer cookingTime;

    @Field
    private List<String> cuisines;

    @Field
    private String difficulty; // Overall difficulty: easy, medium, or hard

    @Field("difficulty_easy")
    private Long difficultyEasy;

    @Field("difficulty_medium")
    private Long difficultyMedium;

    @Field("difficulty_hard")
    private Long difficultyHard;

    @Field("difficulty_user_rated")
    private String difficultyUserRated;

    @Field
    private Long dislikes;

    @Field
    private Long likes;

    @Field("preparation_time")
    private Integer preparationTime;

    @Field("uploaded_by")
    private String uploadedBy;

    @Field
    private Long views;

    public Recipe() {
    }

    public Recipe(String id, List<String> title, List<String> ingredients, List<String> instructions, String pictureLink,
                  Integer cookingTime, List<String> cuisines, String difficulty, Long difficultyEasy,
                  Long difficultyMedium, Long difficultyHard, String difficultyUserRated, Long dislikes,
                  Long likes, Integer preparationTime, String uploadedBy, Long views) {
        this.id = id;
        this.title = title;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.pictureLink = pictureLink;
        this.cookingTime = cookingTime;
        this.cuisines = cuisines;
        this.difficulty = difficulty;
        this.difficultyEasy = difficultyEasy;
        this.difficultyMedium = difficultyMedium;
        this.difficultyHard = difficultyHard;
        this.difficultyUserRated = difficultyUserRated;
        this.dislikes = dislikes;
        this.likes = likes;
        this.preparationTime = preparationTime;
        this.uploadedBy = uploadedBy;
        this.views = views;
    }

    // Getters and setters

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

    public Integer getCookingTime() {
        return cookingTime;
    }
    public void setCookingTime(Integer cookingTime) {
        this.cookingTime = cookingTime;
    }

    public List<String> getCuisines() {
        return cuisines;
    }
    public void setCuisines(List<String> cuisines) {
        this.cuisines = cuisines;
    }

    public String getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Long getDifficultyEasy() {
        return difficultyEasy;
    }
    public void setDifficultyEasy(Long difficultyEasy) {
        this.difficultyEasy = difficultyEasy;
    }

    public Long getDifficultyMedium() {
        return difficultyMedium;
    }
    public void setDifficultyMedium(Long difficultyMedium) {
        this.difficultyMedium = difficultyMedium;
    }

    public Long getDifficultyHard() {
        return difficultyHard;
    }
    public void setDifficultyHard(Long difficultyHard) {
        this.difficultyHard = difficultyHard;
    }

    public String getDifficultyUserRated() {
        return difficultyUserRated;
    }
    public void setDifficultyUserRated(String difficultyUserRated) {
        this.difficultyUserRated = difficultyUserRated;
    }

    public Long getDislikes() {
        return dislikes;
    }
    public void setDislikes(Long dislikes) {
        this.dislikes = dislikes;
    }

    public Long getLikes() {
        return likes;
    }
    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Integer getPreparationTime() {
        return preparationTime;
    }
    public void setPreparationTime(Integer preparationTime) {
        this.preparationTime = preparationTime;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }
    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public Long getViews() {
        return views;
    }
    public void setViews(Long views) {
        this.views = views;
    }
}
