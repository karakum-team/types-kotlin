interface PayloadRepository {
    [key: string]: any;

    full_name?: string;
    name: string;
    owner: {
        [key: string]: any;
        login: string;
        name?: string;
    };
    html_url?: string;
}
