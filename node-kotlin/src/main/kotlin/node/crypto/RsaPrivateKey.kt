// Automatically generated - do not modify!

package node.crypto

sealed external interface RsaPrivateKey {
    var key: KeyLike
    var passphrase: String?

    /**
     * @default 'sha1'
     */
    var oaepHash: String?
    var oaepLabel: NodeJS.TypedArray?
    var padding: Number?
}
