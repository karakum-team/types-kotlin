// Automatically generated - do not modify!

package tanstack.table.core

typealias RequiredKeys<T, K extends keyof T> = Omit<T, K> & Required<Pick<T, K>>