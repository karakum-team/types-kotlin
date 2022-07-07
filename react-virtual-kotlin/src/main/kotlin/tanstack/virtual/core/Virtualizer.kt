// Automatically generated - do not modify!

package tanstack.virtual.core

external interface Virtualizer<TScrollElement, TItemElement> {
    var options: Required<VirtualizerOptions<TScrollElement, TItemElement>>
    var scrollElement: TScrollElement | null
    var constructor(opts: VirtualizerOptions<TScrollElement, TItemElement>)
    var setOptions: (opts: VirtualizerOptions<TScrollElement, TItemElement>) -> Unit
    var getVirtualItems: () -> VirtualItem<TItemElement>[]
    var scrollToOffset: (toOffset: Int, options: ScrollToOffsetOptions?) -> Unit
    var scrollToIndex: (index: Int, options: ScrollToIndexOptions?) -> Unit
    var getTotalSize: () -> Int
    var measure: () -> Unit
}
