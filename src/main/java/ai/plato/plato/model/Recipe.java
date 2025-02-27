package ai.plato.plato.model;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import org.apache.solr.client.solrj.beans.Field;

/**
 * Recipe represents a model for storing recipe-related information.
 * It includes fields such as title, ingredients, cooking time, difficulty, and metadata like likes, dislikes, and views.
 * The class is annotated with Solr-specific indexing fields to enable search capabilities.
 */
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

    private Instant uploadTimestamp;

    @Field("last_modified")
    private Instant lastModified;

    /**
     * Default constructor for Recipe.
     * SolrJ object mapping needs an explicit default constructor because it uses reflection.
     */
    public Recipe() {
    }

    /**
     * Parameterized constructor for Recipe.
     *
     * @param id Unique recipe identifier.
     * @param title List of possible titles.
     * @param ingredients List of ingredients required.
     * @param instructions Step-by-step preparation instructions.
     * @param pictureLink URL link to an image of the recipe.
     * @param cookingTime Estimated cooking time in minutes.
     * @param cuisines List of cuisines the recipe belongs to.
     * @param difficulty Difficulty level (easy, medium, hard).
     * @param difficultyEasy User ratings for easy difficulty.
     * @param difficultyMedium User ratings for medium difficulty.
     * @param difficultyHard User ratings for hard difficulty.
     * @param difficultyUserRated The difficulty level rated by users.
     * @param dislikes Number of users who disliked the recipe.
     * @param likes Number of users who liked the recipe.
     * @param preparationTime Time required for preparation in minutes.
     * @param uploadedBy Username of the recipe uploader.
     * @param views Number of times the recipe has been viewed.
     * @param uploadTimestamp The timestamp when the recipe was uploaded.
     * @param lastModified The timestamp of the last modification.
     */
    public Recipe(String id, List<String> title, List<String> ingredients, List<String> instructions,
                  String pictureLink, Integer cookingTime, List<String> cuisines, String difficulty,
                  Long difficultyEasy, Long difficultyMedium, Long difficultyHard, String difficultyUserRated,
                  Long dislikes, Long likes, Integer preparationTime, String uploadedBy, Long views,
                  Instant uploadTimestamp, Instant lastModified) {
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
        this.uploadTimestamp = uploadTimestamp;
        this.lastModified = lastModified;
    }

    /**
     * Getter and Setter methods for Recipe attributes.
     */
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

    public Instant getUploadTimestamp() {
        return uploadTimestamp;
    }
    @Field("upload_timestamp")
    public void setUploadTimestamp(Date uploadTimestamp) {
        this.uploadTimestamp = (uploadTimestamp == null) ? null : uploadTimestamp.toInstant();
    }
    // Convenience method for setting with Instant
    public void updateUploadTimestamp(Instant instant) {
        this.uploadTimestamp = instant;
    }

    public Instant getLastModified() {
        return lastModified;
    }
    public void setLastModified(Instant lastModified) {
        this.lastModified = lastModified;
    }
}
