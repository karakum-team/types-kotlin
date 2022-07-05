// Automatically generated - do not modify!

package tanstack.table.core

typealias RequiredKeys<T, K : keyof T> = Omit<T, K> & Required<Pick<T, K>>