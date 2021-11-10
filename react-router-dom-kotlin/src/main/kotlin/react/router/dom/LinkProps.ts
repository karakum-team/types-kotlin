export interface LinkProps extends Omit<React.AnchorHTMLAttributes<HTMLAnchorElement>, "href"> {
    replace?: boolean;
    state?: any;
    to: To;
}