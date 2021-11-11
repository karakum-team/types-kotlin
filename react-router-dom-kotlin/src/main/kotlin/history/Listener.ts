/**
 * A function that receives notifications about location changes.
 */
interface Listener {
    (update: Update): void;
}