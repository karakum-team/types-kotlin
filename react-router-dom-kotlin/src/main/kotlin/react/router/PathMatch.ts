/**
 * A PathMatch contains info about how a PathPattern matched on a URL pathname.
 */
interface PathMatch {
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
     * The pattern that was used to match.
     */
    pattern: PathPattern;
}