// LandingPage.tsx
import React, { useState } from 'react';
import SearchBar from '../SearchBar/searchBar';
import styles from './LandingPage.module.css';

const LandingPage: React.FC = () => {
    // Manage booleans or any other state you want at this "page" level
    const [searchEnabled, setSearchEnabled] = useState<boolean>(false);

    // If you want to manage whether a recipe has been selected
    // you can keep this or remove if not needed:
    const [recipeSelected, setRecipeSelected] = useState<boolean>(false);

    return (
        <div className={styles.landingWrapper}>
            <div className={styles.content}>
                <h1 className={styles.title}>Welcome to Plato</h1>

                {/* Additional landing-page elements could go here
            e.g., recommended recipes, user login, etc.
        */}

                <SearchBar
                    searchEnabled={searchEnabled}
                    setSearchEnabled={setSearchEnabled}
                    recipeSelected={recipeSelected}
                    setRecipeSelected={setRecipeSelected}
                />
            </div>
        </div>
    );
};

export default LandingPage;
