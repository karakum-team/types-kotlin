// Automatically generated - do not modify!

package web.errors

import js.errors.JsError
import js.errors.JsErrorName
import seskar.js.JsValue

/**
 * An abnormal event (called an exception) which occurs as a result of calling a method or accessing a property of a web API.
 *
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/DOMException)
 */
open external class DOMException(
    message: String = definedExternally,
    name: JsErrorName = definedExternally,
) : JsError {
    /** [MDN Reference](https://developer.mozilla.org/docs/Web/API/DOMException/message) */
    override val message: String

    /** [MDN Reference](https://developer.mozilla.org/docs/Web/API/DOMException/name) */
    val name: String

    companion object {
        /**
         * The index is not in the allowed range. For example, this can be thrown by the `Range` object. (Legacy code value: `1` and legacy constant name: `INDEX_SIZE_ERR`)
         */
        @JsValue("IndexSizeError")
        val IndexSizeError: JsErrorName

        /**
         * The node tree hierarchy is not correct. (Legacy code value: `3` and legacy constant name: `HIERARCHY_REQUEST_ERR`)
         */
        @JsValue("HierarchyRequestError")
        val HierarchyRequestError: JsErrorName

        /**
         * The object is in the wrong `Document`. (Legacy code value: `4` and legacy constant name: `WRONG_DOCUMENT_ERR`)
         */
        @JsValue("WrongDocumentError")
        val WrongDocumentError: JsErrorName

        /**
         * The string contains invalid characters. (Legacy code value: `5` and legacy constant name: `INVALID_CHARACTER_ERR`)
         */
        @JsValue("InvalidCharacterError")
        val InvalidCharacterError: JsErrorName

        /**
         * The object cannot be modified. (Legacy code value: `7` and legacy constant name: `NO_MODIFICATION_ALLOWED_ERR`)
         */
        @JsValue("NoModificationAllowedError")
        val NoModificationAllowedError: JsErrorName

        /**
         * The object cannot be found here. (Legacy code value: `8` and legacy constant name: `NOT_FOUND_ERR`)
         */
        @JsValue("NotFoundError")
        val NotFoundError: JsErrorName

        /**
         * The operation is not supported. (Legacy code value: `9` and legacy constant name: `NOT_SUPPORTED_ERR`)
         */
        @JsValue("NotSupportedError")
        val NotSupportedError: JsErrorName

        /**
         * The object is in an invalid state. (Legacy code value: `11` and legacy constant name: `INVALID_STATE_ERR`)
         */
        @JsValue("InvalidStateError")
        val InvalidStateError: JsErrorName

        /**
         * The attribute is in use. (Legacy code value: `10` and legacy constant name: `INUSE_ATTRIBUTE_ERR`)
         */
        @JsValue("InUseAttributeError")
        val InUseAttributeError: JsErrorName

        /**
         * The string did not match the expected pattern. (Legacy code value: `12` and legacy constant name: `SYNTAX_ERR`)
         */
        @JsValue("SyntaxError")
        val SyntaxError: JsErrorName

        /**
         * The object cannot be modified in this way. (Legacy code value: `13` and legacy constant name: `INVALID_MODIFICATION_ERR`)
         */
        @JsValue("InvalidModificationError")
        val InvalidModificationError: JsErrorName

        /**
         * The operation is not allowed by Namespaces in XML. (Legacy code value: `14` and legacy constant name: `NAMESPACE_ERR`)
         */
        @JsValue("NamespaceError")
        val NamespaceError: JsErrorName

        /**
         * The object does not support the operation or argument. (Legacy code value: `15` and legacy constant name: `INVALID_ACCESS_ERR`)
         */
        @JsValue("InvalidAccessError")
        val InvalidAccessError: JsErrorName

        /**
         * The operation is insecure. (Legacy code value: `18` and legacy constant name: `SECURITY_ERR`)
         */
        @JsValue("SecurityError")
        val SecurityError: JsErrorName

        /**
         * A network error occurred. (Legacy code value: `19` and legacy constant name: `NETWORK_ERR`)
         */
        @JsValue("NetworkError")
        val NetworkError: JsErrorName

        /**
         * The operation was aborted. (Legacy code value: `20` and legacy constant name: `ABORT_ERR`)
         */
        @JsValue("AbortError")
        val AbortError: JsErrorName

        /**
         * The given URL does not match another URL. (Legacy code value: `21` and legacy constant name: `URL_MISMATCH_ERR`)
         */
        @JsValue("URLMismatchError")
        val URLMismatchError: JsErrorName

        /**
         * The quota has been exceeded. (Legacy code value: `22` and legacy constant name: `QUOTA_EXCEEDED_ERR`)
         */
        @JsValue("QuotaExceededError")
        val QuotaExceededError: JsErrorName

        /**
         * The operation timed out. (Legacy code value: `23` and legacy constant name: `TIMEOUT_ERR`)
         */
        @JsValue("TimeoutError")
        val TimeoutError: JsErrorName

        /**
         * The node is incorrect or has an incorrect ancestor for this operation. (Legacy code value: `24` and legacy constant name: `INVALID_NODE_TYPE_ERR`)
         */
        @JsValue("InvalidNodeTypeError")
        val InvalidNodeTypeError: JsErrorName

        /**
         * The object can not be cloned. (Legacy code value: `25` and legacy constant name: `DATA_CLONE_ERR`)
         */
        @JsValue("DataCloneError")
        val DataCloneError: JsErrorName

        /**
         * The encoding or decoding operation failed (No legacy code value and constant name).
         */
        @JsValue("EncodingError")
        val EncodingError: JsErrorName

        /**
         * The input/output read operation failed (No legacy code value and constant name).
         */
        @JsValue("NotReadableError")
        val NotReadableError: JsErrorName

        /**
         * The operation failed for an unknown transient reason (e.g. out of memory) (No legacy code value and constant name).
         */
        @JsValue("UnknownError")
        val UnknownError: JsErrorName

        /**
         * A mutation operation in a transaction failed because a constraint was not satisfied (No legacy code value and constant name).
         */
        @JsValue("ConstraintError")
        val ConstraintError: JsErrorName

        /**
         * Provided data is inadequate (No legacy code value and constant name).
         */
        @JsValue("DataError")
        val DataError: JsErrorName

        /**
         * A request was placed against a transaction that is currently not active or is finished (No legacy code value and constant name).
         */
        @JsValue("TransactionInactiveError")
        val TransactionInactiveError: JsErrorName

        /**
         * The mutating operation was attempted in a "readonly" transaction (No legacy code value and constant name).
         */
        @JsValue("ReadOnlyError")
        val ReadOnlyError: JsErrorName

        /**
         * An attempt was made to open a database using a lower version than the existing version (No legacy code value and constant name).
         */
        @JsValue("VersionError")
        val VersionError: JsErrorName

        /**
         * The operation failed for an operation-specific reason (No legacy code value and constant name).
         */
        @JsValue("OperationError")
        val OperationError: JsErrorName

        /**
         * The request is not allowed by the user agent or the platform in the current context, possibly because the user denied permission (No legacy code value and constant name).
         */
        @JsValue("NotAllowedError")
        val NotAllowedError: JsErrorName
    }
}
