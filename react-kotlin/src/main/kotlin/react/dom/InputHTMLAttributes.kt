// Automatically generated - do not modify!

package react.dom

external interface InputHTMLAttributes<T> : HTMLAttributes<T> {
    var accept: String
    var alt: String
    var autoComplete: String
    var autoFocus: Boolean
    var capture: boolean | string // https://www.w3.org/TR/html-media-capture/#the-capture-attributechecked?: boolean
    var crossOrigin: String
    var disabled: Boolean
    var enterKeyHint: String /* 'enter' | 'done' | 'go' | 'next' | 'previous' | 'search' | 'send' */
    var form: String
    var formAction: String
    var formEncType: String
    var formMethod: String
    var formNoValidate: Boolean
    var formTarget: String
    var height: Number
    var list: String
    var max: Number
    var maxLength: Number
    var min: Number
    var minLength: Number
    var multiple: Boolean
    var name: String
    var pattern: String
    var placeholder: String
    var readOnly: Boolean
    var required: Boolean
    var size: Number
    var src: String
    var step: Number
    var type: String
    var value: String // string | ReadonlyArray<string> | number
    var width: Number
    var onChange: ChangeEventHandler<T>
}
