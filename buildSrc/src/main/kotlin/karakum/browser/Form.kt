package karakum.browser

internal const val VALIDATION_TARGET = "ValidationTarget"
internal const val FORM_CONTROL = "FormControl"

internal val WELL_KNOWN_FORM_CONTROL = listOf(
    "HTMLButtonElement",
    "HTMLFieldSetElement",
    "HTMLInputElement",
    "HTMLObjectElement",
    "HTMLOutputElement",
    "HTMLSelectElement",
    "HTMLTextAreaElement",
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
    sequenceOf(VALIDATION_TARGET_MEMBERS, FORM_CONTROL_MEMBERS)
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
    )
