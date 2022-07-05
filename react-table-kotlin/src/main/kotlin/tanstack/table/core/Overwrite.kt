// Automatically generated - do not modify!

package tanstack.table.core

type Overwrite<T, U extends {
    [TKey in keyof T]
}> = Omit<T, keyof U> & U
