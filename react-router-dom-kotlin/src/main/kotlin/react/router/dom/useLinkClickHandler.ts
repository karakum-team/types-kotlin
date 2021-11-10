/**
 * Handles the click behavior for router `<Link>` components. This is useful if
 * you need to create custom `<Link>` components with the same click behavior we
 * use in our exported `<Link>`.
 */
export declare function useLinkClickHandler<E extends Element = HTMLAnchorElement>(to: To, {target, replace: replaceProp, state}?: {
    target?: React.HTMLAttributeAnchorTarget;
    replace?: boolean;
    state?: any;
}): (event: React.MouseEvent<E, MouseEvent>) => void;
