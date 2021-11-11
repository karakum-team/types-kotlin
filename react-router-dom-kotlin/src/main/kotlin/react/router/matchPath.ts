/**
 * Performs pattern matching on a URL pathname and returns information about
 * the match.
 *
 * @see https://reactrouter.com/docs/en/v6/api#matchpath
 */
function matchPath<ParamKey extends string = string>(pattern: PathPattern | string, pathname: string): PathMatch<ParamKey> | null;