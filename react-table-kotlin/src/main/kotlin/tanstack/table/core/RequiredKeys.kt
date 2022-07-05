// Automatically generated - do not modify!

package tanstack.table.core

type RequiredKeys<T, K extends keyof T> = Omit<T, K> & Required<Pick<T, K>>
