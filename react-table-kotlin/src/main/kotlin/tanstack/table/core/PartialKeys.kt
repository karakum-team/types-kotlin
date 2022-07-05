// Automatically generated - do not modify!

package tanstack.table.core

typealias PartialKeys<T, K : keyof T> = Omit<T, K> & Partial<Pick<T, K>>