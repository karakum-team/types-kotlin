// Automatically generated - do not modify!

@file:JsModule("@actions/artifact")

package actions.artifact

import node.buffer.Buffer

external class CRC64 {
    // constructor()
    fun update(data: Buffer | string)
    fun digest(encoding: CRC64DigestEncoding = definedExternally): string | Buffer
    // private toBuffer
    fun /* static */ flip64Bits(n: bigint): bigint
}
