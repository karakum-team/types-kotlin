/**
 * An entry in a history stack. A location contains information about the
 * URL path, as well as possibly some arbitrary state and a key.
 *
 * @see https://github.com/ReactTraining/history/tree/master/docs/api-reference.md#location
 */
export interface Location extends Path {
    /**
     * A value of arbitrary data associated with this location.
     *
     * @see https://github.com/ReactTraining/history/tree/master/docs/api-reference.md#location.state
     */
    state: any;
    /**
     * A unique string associated with this location. May be used to safely store
     * and retrieve data in some other storage API, like `localStorage`.
     *
     * Note: This value is always "default" on the initial location.
     *
     * @see https://github.com/ReactTraining/history/tree/master/docs/api-reference.md#location.key
     */
    key: Key;
}