// Automatically generated - do not modify!

@file:JsModule("react-router")
@file:JsNonModule

package react.router

/**
 * Matches the given routes to a location and returns the match data.
 *
 * @see https://reactrouter.com/docs/en/v6/api#matchroutes
 */
external fun matchRoutes(
    routes: js.core.ReadonlyArray<RouteObject>,
    locationArg: history.Location,
    basename: String = definedExternally,
): js.core.ReadonlyArray<RouteMatch>?
