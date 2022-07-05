// Automatically generated - do not modify!

package tanstack.table.core

typealias Overwrite<
        T,
        U :

{
    [TKey in keyof T]
}> = Omit<T, keyof U> & U
