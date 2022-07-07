// Automatically generated - do not modify!

package tanstack.virtual.core

external interface Virtualizer<TScrollElement = unknown, TItemElement = unknown>{
    var options: Required<VirtualizerOptions<TScrollElement, TItemElement>>
    var scrollElement: TScrollElement | null
    var constructor(opts: VirtualizerOptions<TScrollElement, TItemElement>)
    var setOptions: (opts: VirtualizerOptions<TScrollElement, TItemElement>) -> Unit
    var getVirtualItems: () -> VirtualItem<TItemElement>[]
    var scrollToOffset: (toOffset: Int, { align } ?: ScrollToOffsetOptions) -> Unit
    var scrollToIndex: (index: Int, { align, ...rest } ?: ScrollToIndexOptions) -> Unit
    var getTotalSize: () -> Int
    var measure: () -> Unit
}
