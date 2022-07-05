// Automatically generated - do not modify!

package tanstack.table.core

function memo<TDeps extends readonly any[], TResult>(getDeps: () => [...TDeps], fn: (...args: NoInfer<[...TDeps]>) => TResult, opts: {
    key: any
    debug ?: () => any
    onChange ?: (result: TResult) => void
}): () => TResult
