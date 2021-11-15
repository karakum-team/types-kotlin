// Automatically generated - do not modify!

@file:Suppress(
    "NOTHING_TO_INLINE",
)

package react.router

/**
 * The interface for the navigate() function returned from useNavigate().
 */
class NavigateFunction
private constructor() {
    inline operator fun invoke(to: history.To, options: NavigateOptions? = null) {
        asDynamic()(to, options)
    }

    inline operator fun invoke(delta: Int) {
        asDynamic()(delta)
    }
}
