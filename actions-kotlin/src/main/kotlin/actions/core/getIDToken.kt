package actions.core

import kotlin.js.Promise

external fun getIDToken(aud: String = definedExternally): Promise<String>
