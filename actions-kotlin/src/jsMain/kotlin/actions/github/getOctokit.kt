// Automatically generated - do not modify!

@file:JsModule("@actions/github")

package actions.github

import js.array.ReadonlyArray
import js.objects.ReadonlyRecord

external fun getOctokit(
    token: String,
    options: ReadonlyRecord<String, Any> /* OctokitOptions */ = definedExternally,
    ...additionalPlugins: ReadonlyArray<Function<Any> /* OctokitPlugin */>,
): InstanceType<typeof GitHub>
