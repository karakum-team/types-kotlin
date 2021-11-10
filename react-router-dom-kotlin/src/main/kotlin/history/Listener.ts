/**
 * A function that receives notifications about location changes.
 */
export interface Listener {
    (update: Update): void;
}