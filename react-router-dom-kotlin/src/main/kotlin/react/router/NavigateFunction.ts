/**
 * The interface for the navigate() function returned from useNavigate().
 */
export interface NavigateFunction {
    (to: To, options?: NavigateOptions): void;

    (delta: number): void;
}
