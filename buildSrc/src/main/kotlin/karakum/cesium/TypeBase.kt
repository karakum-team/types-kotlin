package karakum.cesium

import karakum.cesium.Suppress.*

internal abstract class TypeBase(
    final override val source: Definition,
) : Declaration(), IType, HasMembers {
    override val name: String =
        source.defaultName

    var parents: List<String> = emptyList()

    abstract val typeName: String
    abstract val companion: HasMembers?
    open val staticBody: Boolean = false

    private val abstract: Boolean by lazy {
        source.abstract || name == "TilingScheme"
    }

    private val sealed: Boolean by lazy {
        source.sealed
    }

    override val members by lazy {
        members(source.body, source.optionsKdocBody())
            .onEach { it.parent = this }
            .onEach { if (!it.static) it.abstract = abstract }
    }

    open fun suppresses(): List<Suppress> {
        val hasTypeAliases = sequenceOf(this, companion)
            .filterNotNull()
            .flatMap { it.members.asSequence() }
            .filterIsInstance<SimpleType>()
            .any { it.isTypeAlias }

        return mutableListOf<Suppress>().apply {
            if (parents.isNotEmpty() && name.endsWith(TERRAIN_PROVIDER)) {
                add(VAR_OVERRIDDEN_BY_VAL)
                add(VAR_TYPE_MISMATCH_ON_OVERRIDE)
            }

            val constructor = members.firstOrNull() as? Constructor
            if (hasTypeAliases || (constructor != null && constructor.hasOptions))
                add(NON_EXTERNAL_DECLARATION_IN_INAPPROPRIATE_FILE)

            if (constructor.propertyParameters().isNotEmpty())
                add(EXTERNAL_CLASS_CONSTRUCTOR_PROPERTY_PARAMETER)
        }
    }

    private fun Constructor?.propertyParameters(): List<Property> {
        this ?: return emptyList()

        if (parameters.isEmpty())
            return emptyList()

        val parameterMap = parameters
            .associate { it.name to it.type }

        return members.asSequence()
            .filterIsInstance<Property>()
            .filter { parameterMap[it.name] == it.type }
            .toList()
    }

    override fun toCode(): String =
        toCode(true)

    fun toCode(top: Boolean): String {
        val constructor = members.firstOrNull() as? Constructor
        var constructorBody = constructor?.toCode()
            ?.removePrefix("constructor")
            ?: ""

        val propertyParameters = constructor.propertyParameters()
        for (p in propertyParameters) {
            val parameter = "${p.name}: ${p.type}"
            val param = if ("$parameter?" in constructorBody) "$parameter?" else parameter
            constructorBody = constructorBody.replaceFirst(param, p.declaration)
        }

        val companionMembers = companion?.members
            ?.filterNot { it.isNestedType() }
            ?: emptyList()

        val nestedTypes = companion?.members
            ?.filter { it.isNestedType() }
            ?: emptyList()

        val bodyMembers = members
            .asSequence()
            .filter { it != constructor }
            .filter { staticBody || !it.static }
            .plus(nestedTypes)
            // WA for duplicated option types
            .distinct()
            .filter(constructor.toMemberFilter())
            .toList()

        val typeAliases = bodyMembers
            .filterIsInstance<SimpleType>()
            .filter { it.isTypeAlias }

        val aliases = typeAliases
            .filter {
                when (it.name) {
                    "RemoveCallback" -> name == "Event"
                    "DoneCallback" -> name == "KmlTourFlyTo"
                    "AnimationTimeCallback" -> name == "ModelAnimation"
                    else -> true
                }
            }
            .takeIf { it.isNotEmpty() }
            ?.joinToString("\n\n", "\n", "\n") { it.toCode() }
            ?: ""

        var body = bodyMembers
            .asSequence()
            .minus(typeAliases)
            .minus(propertyParameters)
            .map { it.toCode() }
            .filter { it.isNotEmpty() } // TEMP
            .joinToString(separator = "\n\n")

        val suppresses = suppresses()
        val suppressHeader = if (suppresses.isNotEmpty()) {
            suppresses.asSequence()
                .map { """"${it.name}",""" }
                .joinToString("\n")
                .let { "@file:Suppress(\n$it\n)\n\n" }
        } else ""

        if (!staticBody) {
            val staticMembers = members.filter { it.static }
                .plus(companionMembers)

            val packable = name != PACKABLE
                    && name != "PlaneOutlineGeometry" // WA
                    && staticMembers.any { it.name == PACKED_LENGTH }

            if (packable) {
                staticMembers.asSequence()
                    .filter { it.name in PACKABLE_MEMBERS }
                    .forEach { it.overridden = true }
            }

            val companionBody = staticMembers
                .map { it.toCode() }
                .joinToString(separator = "\n\n")

            val companionTypes = if (packable) {
                ": $PACKABLE<$name>"
            } else ""

            if (companionBody.isNotEmpty()) {
                body += "\n\ncompanion object $companionTypes {\n$companionBody\n}"
            }
        }

        val parentNames = if (parents.isNotEmpty()) {
            " : " + parents.joinToString(", ")
        } else ""

        // TODO: move cleanup to separate method
        body = "$constructorBody $parentNames {\n$body\n}\n"
            .replace(": $name.", ": ")

        val header = if (top) {
            suppressHeader +
                    DEFAULT_PACKAGE
        } else ""

        val modifiers = (if (top) "external " else "") +
                (if (sealed) "sealed " else "") +
                (if (abstract) "abstract " else "")

        val hideParams = constructor != null && !constructor.hasParameters

        val doc = propertyParameters
            .fold(source.doc(DocLink(this), hideParams)) { acc, p ->
                acc.replaceFirst("@param [${p.name}]", "@property [${p.name}]")
            }

        val declaration = when (name) {
            "Event" -> "$name<Listener : Function<Unit>>"
            else -> name
        }

        return header +
                doc +
                "\n" +
                "$modifiers $typeName $declaration $body" +
                aliases +
                (constructor?.toExtensionCode() ?: "")
    }
}

private fun Member.isNestedType(): Boolean {
    if (this is NestedNamespace)
        return true

    if (this !is SimpleType)
        return false

    return name.startsWith(CONSTRUCTOR_OPTIONS)
            || !name.endsWith("Options")
}

private fun Constructor?.toMemberFilter(): (Member) -> Boolean {
    if (this == null || !hiddenOptions)
        return { true }

    return {
        it !is SimpleType || !it.name.startsWith(CONSTRUCTOR_OPTIONS)
    }
}
