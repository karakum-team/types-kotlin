// Automatically generated - do not modify!

package typescript

external sealed interface CancellationToken {
fun  isCancellationRequested(): Boolean
/** @throws OperationCanceledException if isCancellationRequested is true */
fun  throwIfCancellationRequested()
}
