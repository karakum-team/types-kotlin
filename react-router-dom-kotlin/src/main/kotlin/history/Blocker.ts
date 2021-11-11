/**
 * A function that receives transitions when navigation is blocked.
 */
interface Blocker {
    (tx: Transition): void;
}