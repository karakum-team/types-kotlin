/**
 * A RouteMatch contains info about how a route matched a URL.
 */
interface RouteMatch {
    /**
     * The names and values of dynamic parameters in the URL.
     */
    params: Params;
    /**
     * The portion of the URL pathname that was matched.
     */
    pathname: string;
    /**
     * The portion of the URL pathname that was matched before child routes.
     */
    pathnameBase: string;
    /**
     * The route object that was used to match.
     */
    route: RouteObject;
}