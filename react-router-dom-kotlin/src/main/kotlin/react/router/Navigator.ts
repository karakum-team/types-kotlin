//--delimiter--//
/**
 * A Navigator is a "location changer"; it's how you get to different locations.
 *
 * Every history instance conforms to the Navigator interface, but the
 * distinction is useful primarily when it comes to the low-level <Router> API
 * where both the location and a navigator must be provided separately in order
 * to avoid "tearing" that may occur in a suspense-enabled app if the action
 * and/or location were to be read directly from the history instance.
 */
export declare type Navigator = Omit<History, "action" | "location" | "back" | "forward" | "listen" | "block">;

interface NavigationContextObject {
    basename: string;
    navigator: Navigator;
    static: boolean;
}

declare const NavigationContext: React.Context<NavigationContextObject>;

interface LocationContextObject {
    location: Location;
    navigationType: NavigationType;
}

declare const LocationContext: React.Context<LocationContextObject>;

interface RouteContextObject {
    outlet: React.ReactElement | null;
    matches: RouteMatch[];
}

declare const RouteContext: React.Context<RouteContextObject>;
