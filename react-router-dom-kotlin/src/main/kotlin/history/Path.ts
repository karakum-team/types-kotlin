/**
 * The pathname, search, and hash values of a URL.
 */
interface Path {
    /**
     * A URL pathname, beginning with a /.
     *
     * @see https://github.com/ReactTraining/history/tree/master/docs/api-reference.md#location.pathname
     */
    pathname: Pathname;
    /**
     * A URL search string, beginning with a ?.
     *
     * @see https://github.com/ReactTraining/history/tree/master/docs/api-reference.md#location.search
     */
    search: Search;
    /**
     * A URL fragment identifier, beginning with a #.
     *
     * @see https://github.com/ReactTraining/history/tree/master/docs/api-reference.md#location.hash
     */
    hash: Hash;
}