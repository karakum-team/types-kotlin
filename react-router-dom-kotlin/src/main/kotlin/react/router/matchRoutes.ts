/**
 * Matches the given routes to a location and returns the match data.
 *
 * @see https://reactrouter.com/docs/en/v6/api#matchroutes
 */
function matchRoutes(routes: RouteObject[], locationArg: Partial<Location> | string, basename?: string): RouteMatch[] | null;