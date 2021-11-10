/**
 * A function that receives transitions when navigation is blocked.
 */
export interface Blocker {
    (tx: Transition): void;
}