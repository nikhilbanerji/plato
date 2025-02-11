import React, { useState, useEffect, useRef } from 'react';
import { useClickOutside } from '../../hooks/useClickOutside';
import { ComplexSearchResult } from './types';
import styles from './SearchBar.module.css';

interface SearchBarProps {
    searchEnabled: boolean;
    setSearchEnabled: (val: boolean) => void;
}

const SearchBar: React.FC<SearchBarProps> = ({ searchEnabled, setSearchEnabled }) => {
    const [query, setQuery] = useState('');
    const [searchResults, setSearchResults] = useState<ComplexSearchResult | null>(null);
    const [error, setError] = useState<string | null>(null);

    const searchContainerRef = useRef<HTMLDivElement>(null);
    // Use our custom hook to collapse results when clicking outside
    useClickOutside(searchContainerRef, () => setSearchEnabled(false));

    useEffect(() => {
        // If search is disabled or query is empty, reset results
        if (!searchEnabled || !query) {
            setSearchResults(null);
            return;
        }

        // Debounce logic: wait 300ms after typing stops
        const delay = setTimeout(() => {
            fetchRecipes(query);
        }, 300);

        // Cleanup function called before the effect re-runs
        // (e.g., user types another character) or component unmounts
        return () => clearTimeout(delay);

        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [query, searchEnabled]);
    // ^ NOTE: We don't want to re-run on fetchRecipes changes, so ignore that dependency

    async function fetchRecipes(searchTerm: string) {
        try {
            const response = await fetch(
                `http://localhost:8080/plato/recipes/complexSearch?query=${encodeURIComponent(searchTerm)}&number=5`
            );
            if (!response.ok) {
                throw new Error(`Server responded with ${response.status}`);
            }
            const data: ComplexSearchResult = await response.json();
            setSearchResults(data);
            setError(null);
        } catch (error) {
            console.error(error);
            setSearchResults(null);
            setError("Failed to load search results.");
        }
    }

    const handleFocus = () => {
        setSearchEnabled(true);
    };

    return (
        <div ref={searchContainerRef} className={styles.searchContainer}>
            <div className={styles.inputGroup}>
                <input
                    type="text"
                    placeholder="Search recipes..."
                    className={styles.searchInput}
                    value={query}
                    onChange={(e) => setQuery(e.target.value)}
                    onFocus={handleFocus}
                />
                <button className={styles.searchButton}>Search</button>
            </div>

            {/* Show results only if search is enabled */}
            {searchEnabled && searchResults && (
                <div className={styles.resultsContainer}>
                    {error ? (
                        <p>{error}</p>
                    ) : (
                        <>
                            <p>Total Results: {searchResults.totalResults}</p>
                            <ul style={{ listStyle: 'none', margin: 0, padding: 0 }}>
                                {searchResults.results.map((item) => (
                                    <li key={item.id} className={styles.resultItem}>
                                        {item.title}
                                    </li>
                                ))}
                            </ul>
                        </>
                    )}
                </div>
            )}
        </div>
    );
};

export default SearchBar;
