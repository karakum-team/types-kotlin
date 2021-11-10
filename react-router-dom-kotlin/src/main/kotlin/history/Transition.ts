/**
 * A change to the current location that was blocked. May be retried
 * after obtaining user confirmation.
 */
export interface Transition extends Update {
    /**
     * Retries the update to the current location.
     */
    retry(): void;
}