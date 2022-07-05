// Automatically generated - do not modify!

package tanstack.table.core

type PartialKeys<T, K extends keyof T> = Omit<T, K> & Partial<Pick<T, K>>
