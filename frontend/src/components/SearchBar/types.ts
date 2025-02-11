// types.ts
// Adjust fields to match your actual JSON response from the Spring endpoint
export interface ComplexSearchResult {
    results: Array<{
        id: number;
        title: string;
        image?: string;
    }>;
    totalResults: number;
}
