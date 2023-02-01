interface IToolRelease {
    version: string;
    stable: boolean;
    release_url: string;
    files: IToolReleaseFile[];
}