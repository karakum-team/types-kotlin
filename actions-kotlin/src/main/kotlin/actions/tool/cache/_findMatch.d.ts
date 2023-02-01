function _findMatch(
    versionSpec: string,
    stable: boolean,
    candidates: IToolRelease[],
    archFilter: string,
): Promise<IToolRelease | undefined>;
