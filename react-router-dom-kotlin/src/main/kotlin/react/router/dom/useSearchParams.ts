/**
 * A convenient wrapper for reading and writing search parameters via the
 * URLSearchParams interface.
 */
export declare function useSearchParams(defaultInit?: URLSearchParamsInit): readonly [URLSearchParams, (nextInit: URLSearchParamsInit, navigateOptions?: {
    replace?: boolean | undefined;
    state?: any;
} | undefined) => void];