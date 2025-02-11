// // LandingPage.tsx
// import React, { useState } from 'react';
// import SearchBar from '../SearchBar/searchBar';
// import styles from './LandingPage.module.css';
//
// const LandingPage: React.FC = () => {
//     // Manage booleans or any other state you want at this "page" level
//     const [searchEnabled, setSearchEnabled] = useState<boolean>(false);
//
//     // If you want to manage whether a recipe has been selected
//     // you can keep this or remove if not needed:
//     const [recipeSelected, setRecipeSelected] = useState<boolean>(false);
//
//     return (
//         <div className={styles.landingWrapper}>
//             <div className={styles.content}>
//                 <h1 className={styles.title}>Welcome to Plato</h1>
//
//                 {/* Additional landing-page elements could go here
//             e.g., recommended recipes, user login, etc.
//         */}
//
//                 <SearchBar
//                     searchEnabled={searchEnabled}
//                     setSearchEnabled={setSearchEnabled}
//                     recipeSelected={recipeSelected}
//                     setRecipeSelected={setRecipeSelected}
//                 />
//             </div>
//         </div>
//     );
// };
//
// export default LandingPage;

import React, { useState, useEffect } from 'react';
import SearchBar from '../SearchBar/SearchBar';
import styles from './LandingPage.module.css';

const LandingPage: React.FC = () => {
    const [recipes, setRecipes] = useState<Array<{ id: number; title: string; image?: string; ingredients?: string }>>([]);

    useEffect(() => {
        fetchRandomRecipes();
    }, []);

    async function fetchRandomRecipes() {
        try {
            const response = await fetch(`http://localhost:8080/plato/recipes/random?number=10`);
            if (!response.ok) {
                throw new Error(`Server responded with ${response.status}`);
            }
            const data = await response.json();
            setRecipes(data.recipes);
        } catch (error) {
            console.error(error);
        }
    }

    return (
        <div className={styles.landingWrapper}>
            <div className={styles.navbar}>
                <div className={styles.brand}>Plato</div>
                <SearchBar setRecipes={setRecipes} />
            </div>

            <div className={styles.recipeGrid}>
                {recipes.map((recipe) => (
                    <div key={recipe.id} className={styles.recipeCard}>
                        <img src={recipe.image || 'default-image.jpg'} alt={recipe.title} className={styles.recipeImage} />
                        <h3 className={styles.recipeTitle}>{recipe.title}</h3>
                        <p className={styles.recipeIngredients}>
                            {recipe.ingredients ? recipe.ingredients.slice(0, 50) + '...' : 'Ingredients not available'}
                        </p>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default LandingPage;

