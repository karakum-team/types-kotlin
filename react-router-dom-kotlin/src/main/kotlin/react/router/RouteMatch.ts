/**
 * A RouteMatch contains info about how a route matched a URL.
 */
export interface RouteMatch<ParamKey extends string = string> {
    //--delimiter--//
    /**
     * The names and values of dynamic parameters in the URL.
     */
    params: Params<ParamKey>;
    //--delimiter--//
    /**
     * The portion of the URL pathname that was matched.
     */
    pathname: string;
    //--delimiter--//
    /**
     * The portion of the URL pathname that was matched before child routes.
     */
    pathnameBase: string;
    //--delimiter--//
    /**
     * The route object that was used to match.
     */
    route: RouteObject;
}
