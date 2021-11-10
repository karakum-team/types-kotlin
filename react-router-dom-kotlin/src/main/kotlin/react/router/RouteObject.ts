/**
 * A route object represents a logical route, with (optionally) its child
 * routes organized in a tree-like structure.
 */
export interface RouteObject {
    caseSensitive?: boolean;
    children?: RouteObject[];
    element?: React.ReactNode;
    index?: boolean;
    path?: string;
}