/**
 * The parameters that were parsed from the URL path.
 */
export declare type Params<Key extends string = string> = {
    readonly [key in Key]: string | undefined;
};