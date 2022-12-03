// Automatically generated - do not modify!

package web.idb

sealed external class IDBIndex {
    val keyPath: Any /* string | string[] */
    val multiEntry: Boolean

    /** Returns the name of the index. */
    var name: String

    /** Returns the IDBObjectStore the index belongs to. */
    val objectStore: IDBObjectStore
    val unique: Boolean

    /**
     * Retrieves the number of records matching the given key or key range in query.
     *
     * If successful, request's result will be the count.
     */
    fun count(query: IDBValidKey | IDBKeyRange = definedExternally): IDBRequest<number>
    /**
     * Retrieves the value of the first record matching the given key or key range in query.
     *
     * If successful, request's result will be the value, or undefined if there was no matching record.
     */
    fun get(query: IDBValidKey | IDBKeyRange): IDBRequest<any>
    /**
     * Retrieves the values of the records matching the given key or key range in query (up to count if given).
     *
     * If successful, request's result will be an Array of the values.
     */
    fun getAll(
        query: IDBValidKey | IDBKeyRange? = definedExternally,
    count: Number = definedExternally,
    ): IDBRequest<any[]>
    /**
     * Retrieves the keys of records matching the given key or key range in query (up to count if given).
     *
     * If successful, request's result will be an Array of the keys.
     */
    fun getAllKeys(
        query: IDBValidKey | IDBKeyRange? = definedExternally,
    count: Number = definedExternally,
    ): IDBRequest<IDBValidKey[]>
    /**
     * Retrieves the key of the first record matching the given key or key range in query.
     *
     * If successful, request's result will be the key, or undefined if there was no matching record.
     */
    fun getKey(query: IDBValidKey | IDBKeyRange): IDBRequest<IDBValidKey?>
    /**
     * Opens a cursor over the records matching query, ordered by direction. If query is null, all records in index are matched.
     *
     * If successful, request's result will be an IDBCursorWithValue, or null if there were no matching records.
     */
    fun openCursor(
        query: IDBValidKey | IDBKeyRange? = definedExternally,
    direction: IDBCursorDirection = definedExternally,
    ): IDBRequest<IDBCursorWithValue?>
    /**
     * Opens a cursor with key only flag set over the records matching query, ordered by direction. If query is null, all records in index are matched.
     *
     * If successful, request's result will be an IDBCursor, or null if there were no matching records.
     */
    fun openKeyCursor(
        query: IDBValidKey | IDBKeyRange? = definedExternally,
    direction: IDBCursorDirection = definedExternally,
    ): IDBRequest<IDBCursor?>
}
