export interface NavLinkProps extends Omit<LinkProps, "className" | "style"> {
    caseSensitive?: boolean;
    className?: string | ((props: {
        isActive: boolean;
    }) => string);
    end?: boolean;
    style?: React.CSSProperties | ((props: {
        isActive: boolean;
    }) => React.CSSProperties);
}