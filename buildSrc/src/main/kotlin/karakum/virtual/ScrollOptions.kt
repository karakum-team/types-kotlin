package karakum.virtual

internal const val SCROLL_OPTIONS = "ScrollOptions"

// language=typescript
internal const val SCROLL_OPTIONS_BODY = """{
    adjustments?: number | undefined;
    behavior?: ScrollBehavior | undefined;
    sync: boolean;
}"""

internal const val SCROLL_BY_OPTIONS = "ScrollByOptions"

// language=typescript
internal const val SCROLL_BY_OPTIONS_BODY = """{
    behavior: ScrollBehavior;
}"""
