package karakum.browser

import karakum.common.unionBody

internal const val VALIDATION_TARGET = "ValidationTarget"
internal const val FORM_CONTROL = "FormControl"

internal const val CUSTOM_FORM_CONTROL = "CustomFormControl"
internal const val CUSTOM_FORM_CONTROL_CALLBACKS = "CustomFormControlCallbacks"

internal const val FORM_STATE_RESTORE_MODE = "FormStateRestoreMode"

internal val WELL_KNOWN_FORM_CONTROL = listOf(
    "HTMLButtonElement",
    "HTMLFieldSetElement",
    "HTMLInputElement",
    "HTMLObjectElement",
    "HTMLOutputElement",
    "HTMLSelectElement",
    "HTMLTextAreaElement",
)

private val CALLBACKS = mapOf(
    "formAssociatedCallback" to "(form: HTMLFormElement?) -> Unit",
    "formDisabledCallback" to "(disabled: Boolean) -> Unit",
    "formResetCallback" to "() -> Unit",
    "formStateRestoreCallback" to "(state: Any? /* File | string | FormData */, mode: $FORM_STATE_RESTORE_MODE) -> Unit",
)

private val VALIDATION_TARGET_MEMBERS = """
val validationMessage: String
val validity: ValidityState
val willValidate: Boolean
fun checkValidity(): Boolean
fun reportValidity(): Boolean
fun setCustomValidity(error: String)
""".trimIndent()

private val FORM_CONTROL_MEMBERS = """
val form: HTMLFormElement?
var name: String
""".trimIndent()

internal fun String.applyFormControlPatch(): String =
    applyPatch(VALIDATION_TARGET_MEMBERS, FORM_CONTROL_MEMBERS)

internal fun String.applyValidationTargetPatch(): String =
    applyPatch(VALIDATION_TARGET_MEMBERS)

private fun String.applyPatch(
    vararg members: String,
): String =
    members.asSequence()
        .flatMap { it.splitToSequence("\n") }
        .fold(this) { acc, member ->
            acc.replaceFirst(member, "override $member")
        }

internal fun formTypes(): Sequence<ConversionResult> =
    sequenceOf(
        ConversionResult(
            name = VALIDATION_TARGET,
            body = """
            external interface $VALIDATION_TARGET {
                $VALIDATION_TARGET_MEMBERS
            }
            """.trimIndent(),
            pkg = "web.validation"
        ),
        ConversionResult(
            name = FORM_CONTROL,
            body = """
            external interface $FORM_CONTROL:
                $VALIDATION_TARGET {
                $FORM_CONTROL_MEMBERS
            }
            """.trimIndent(),
            pkg = "web.form"
        ),
        ConversionResult(
            name = FORM_STATE_RESTORE_MODE,
            body = unionBody(
                FORM_STATE_RESTORE_MODE,
                listOf("restore", "autocomplete")
            ),
            pkg = "web.form"
        ),
        CustomFormControlCallbacks(),
        ConversionResult(
            name = CUSTOM_FORM_CONTROL,
            body = """
            external interface $CUSTOM_FORM_CONTROL:
                $FORM_CONTROL,
                $CUSTOM_FORM_CONTROL_CALLBACKS
            """.trimIndent(),
            pkg = "web.form"
        ),
    ).plus(
        CALLBACKS.map { (propertyName, typeSource) ->
            val aliasName = propertyName.replaceFirstChar(Char::uppercase)
            val aliasBody = if ("()" !in typeSource) {
                typeSource.replaceFirst("(", "(\n")
                    .replaceFirst(", ", ",\n")
            } else typeSource

            ConversionResult(
                name = aliasName,
                body = "typealias $aliasName = " + aliasBody,
                pkg = "web.form",
            )
        }
    )

private fun CustomFormControlCallbacks(): ConversionResult {
    val members = CALLBACKS.keys.joinToString("\n") { name ->
        """
        var $name: ${name.replaceFirstChar(Char::uppercase)}?
            get() = definedExternally
            set(value) = definedExternally
        """.trimIndent()
    }

    val body = "external interface $CUSTOM_FORM_CONTROL_CALLBACKS {\n" +
            members +
            "\n}"

    return ConversionResult(
        name = CUSTOM_FORM_CONTROL_CALLBACKS,
        body = body,
        pkg = "web.form"
    )
}
