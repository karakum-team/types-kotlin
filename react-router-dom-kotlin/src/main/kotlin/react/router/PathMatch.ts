/**
 * A PathMatch contains info about how a PathPattern matched on a URL pathname.
 */
export interface PathMatch<ParamKey extends string = string> {
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
     * The pattern that was used to match.
     */
    pattern: PathPattern;
}
