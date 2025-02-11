import { useEffect } from 'react';

export function useClickOutside<T extends HTMLElement>(
    ref: React.RefObject<T | null>, // Allow null
    callback: () => void
) {
    useEffect(() => {
        function handleClickOutside(event: MouseEvent) {
            if (ref.current && !ref.current.contains(event.target as Node)) {
                callback();
            }
        }

        // Only attach event listener if ref.current is not null
        if (ref.current) {
            document.addEventListener('mousedown', handleClickOutside);
        }

        return () => {
            document.removeEventListener('mousedown', handleClickOutside);
        };
    }, [ref, callback]);
}
