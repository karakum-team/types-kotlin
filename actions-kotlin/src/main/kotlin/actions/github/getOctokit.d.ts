function getOctokit(
    token: string,
    options?: OctokitOptions,
    ...additionalPlugins: OctokitPlugin[],
): InstanceType<typeof GitHub>;
