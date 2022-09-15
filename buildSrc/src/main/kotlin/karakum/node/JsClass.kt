package karakum.node

private val REQUEST = "Request extends typeof IncomingMessage = typeof IncomingMessage"
private val RESPONSE = "Response extends typeof ServerResponse = typeof ServerResponse"

internal fun String.addClassPatch(): String {
    var result = this

    if (REQUEST in result)
        result = result
            .replace(REQUEST, "Request extends IncomingMessage")
            .replace(": Request ", ": JsClass<Request> ")
            .replace(": Request\n", ": JsClass<Request>\n")
            .replace(": Request,", ": JsClass<Request>,")
            .replace(": Request)", ": JsClass<Request>)")
            .replace(": InstanceType<Request> ", ": Request ")
            .replace(": InstanceType<Request>\n", ": Request\n")
            .replace(": InstanceType<Request>,", ": Request,")
            .replace(": InstanceType<Request>)", ": Request)")


    if (RESPONSE in result)
        result = result
            .replace(RESPONSE, "Response extends ServerResponse<*>")
            .replace(": Response ", ": JsClass<Response> ")
            .replace(": Response\n", ": JsClass<Response>\n")
            .replace(": Response,", ": JsClass<Response>,")
            .replace(": Response)", ": JsClass<Response>)")
            .replace(": InstanceType<Response> ", ": Response ")
            .replace(": InstanceType<Response>\n", ": Response\n")
            .replace(": InstanceType<Response>,", ": Response,")
            .replace(": InstanceType<Response>)", ": Response)")

    return result
}
