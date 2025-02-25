import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import styles from './RecipeDetails.module.css';

interface Ingredient {
    id: number;
    image: string;
    name: string;
    amount: number;
    unit: string;
}

interface AnalyzedInstruction {
    name: string;
    steps: { number: number; step: string }[];
}

interface RecipeInformation {
    id: number;
    title: string;
    image: string;
    readyInMinutes: number;
    extendedIngredients: Ingredient[];
    analyzedInstructions: AnalyzedInstruction[];
}

const RecipeDetails: React.FC = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const [recipe, setRecipe] = useState<RecipeInformation | null>(null);

    useEffect(() => {
        async function fetchRecipeDetails() {
            try {
                const response = await fetch(`http://localhost:8080/plato/recipes/${id}/information`);
                if (!response.ok) {
                    throw new Error(`Server responded with ${response.status}`);
                }
                const data: RecipeInformation = await response.json();
                setRecipe(data);
            } catch (error) {
                console.error(error);
            }
        }
        fetchRecipeDetails();
    }, [id]);

    if (!recipe) {
        return <p className={styles.loadingText}>Loading...</p>;
    }

    return (
        <div className={styles.recipeDetailsContainer}>
            <button className={styles.backButton} onClick={() => navigate('/')}>← Back</button>
            <img src={recipe.image} alt={recipe.title} className={styles.recipeImage} />
            <h1 className={styles.recipeTitle}>{recipe.title}</h1>
            <p className={styles.infoText}><strong>Ready in minutes:</strong> {recipe.readyInMinutes}</p>

            <h2 className={styles.sectionTitle}>Ingredients</h2>
            <ul className={styles.ingredientsList}>
                {recipe.extendedIngredients.map((ingredient) => (
                    <li key={ingredient.id} className={styles.ingredientItem}>
                        <img src={`https://spoonacular.com/cdn/ingredients_100x100/${ingredient.image}`} alt={ingredient.name} className={styles.ingredientImage} />
                        <span>{ingredient.amount} {ingredient.unit} - {ingredient.name}</span>
                    </li>
                ))}
            </ul>

            <h2 className={styles.sectionTitle}>Instructions</h2>
            <ol className={styles.instructionsList}>
                {recipe.analyzedInstructions.length > 0 ? (
                    recipe.analyzedInstructions.flatMap((instruction) =>
                        instruction.steps.map((step) => (
                            <li key={step.number} className={styles.instructionStep}>{step.step}</li>
                        ))
                    )
                ) : (
                    <p>No instructions available.</p>
                )}
            </ol>
        </div>
    );
};

export default RecipeDetails;
