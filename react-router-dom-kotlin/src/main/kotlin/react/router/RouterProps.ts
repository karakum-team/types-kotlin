interface RouterProps {
    basename?: string;
    children?: React.ReactNode;
    location: Partial<Location> | string;
    navigationType?: NavigationType;
    navigator: Navigator;
    static?: boolean;
}
