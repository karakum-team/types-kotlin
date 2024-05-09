// Automatically generated - do not modify!

package web.crypto

import js.array.ReadonlyArray
import js.buffer.ArrayBuffer
import js.buffer.BufferSource
import js.promise.Promise

/**
 * This Web Crypto API interface provides a number of low-level cryptographic functions. It is accessed via the Crypto.subtle properties available in a window context (via Window.crypto).
 * Available only in secure contexts.
 *
 * [MDN Reference](https://developer.mozilla.org/docs/Web/API/SubtleCrypto)
 */
sealed external class SubtleCrypto {
    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/SubtleCrypto/decrypt)
     */
    @JsName("decrypt")
    fun decryptAsync(
        algorithm: Algorithm,
        key: CryptoKey,
        data: BufferSource,
    ): Promise<ArrayBuffer>

    @JsName("decrypt")
    fun decryptAsync(
        algorithm: String,
        key: CryptoKey,
        data: BufferSource,
    ): Promise<ArrayBuffer>

    @JsName("decrypt")
    fun decryptAsync(
        algorithm: RsaOaepParams,
        key: CryptoKey,
        data: BufferSource,
    ): Promise<ArrayBuffer>

    @JsName("decrypt")
    fun decryptAsync(
        algorithm: AesCtrParams,
        key: CryptoKey,
        data: BufferSource,
    ): Promise<ArrayBuffer>

    @JsName("decrypt")
    fun decryptAsync(
        algorithm: AesCbcParams,
        key: CryptoKey,
        data: BufferSource,
    ): Promise<ArrayBuffer>

    @JsName("decrypt")
    fun decryptAsync(
        algorithm: AesGcmParams,
        key: CryptoKey,
        data: BufferSource,
    ): Promise<ArrayBuffer>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/SubtleCrypto/deriveBits)
     */
    @JsName("deriveBits")
    fun deriveBitsAsync(
        algorithm: Algorithm,
        baseKey: CryptoKey,
        length: Int,
    ): Promise<ArrayBuffer>

    @JsName("deriveBits")
    fun deriveBitsAsync(
        algorithm: String,
        baseKey: CryptoKey,
        length: Int,
    ): Promise<ArrayBuffer>

    @JsName("deriveBits")
    fun deriveBitsAsync(
        algorithm: EcdhKeyDeriveParams,
        baseKey: CryptoKey,
        length: Int,
    ): Promise<ArrayBuffer>

    @JsName("deriveBits")
    fun deriveBitsAsync(
        algorithm: HkdfParams,
        baseKey: CryptoKey,
        length: Int,
    ): Promise<ArrayBuffer>

    @JsName("deriveBits")
    fun deriveBitsAsync(
        algorithm: Pbkdf2Params,
        baseKey: CryptoKey,
        length: Int,
    ): Promise<ArrayBuffer>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/SubtleCrypto/deriveKey)
     */
    @JsName("deriveKey")
    fun deriveKeyAsync(
        algorithm: Algorithm,
        baseKey: CryptoKey,
        derivedKeyType: Algorithm,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("deriveKey")
    fun deriveKeyAsync(
        algorithm: String,
        baseKey: CryptoKey,
        derivedKeyType: String,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("deriveKey")
    fun deriveKeyAsync(
        algorithm: EcdhKeyDeriveParams,
        baseKey: CryptoKey,
        derivedKeyType: Algorithm,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("deriveKey")
    fun deriveKeyAsync(
        algorithm: EcdhKeyDeriveParams,
        baseKey: CryptoKey,
        derivedKeyType: String,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("deriveKey")
    fun deriveKeyAsync(
        algorithm: HkdfParams,
        baseKey: CryptoKey,
        derivedKeyType: Algorithm,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("deriveKey")
    fun deriveKeyAsync(
        algorithm: HkdfParams,
        baseKey: CryptoKey,
        derivedKeyType: String,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("deriveKey")
    fun deriveKeyAsync(
        algorithm: Pbkdf2Params,
        baseKey: CryptoKey,
        derivedKeyType: Algorithm,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("deriveKey")
    fun deriveKeyAsync(
        algorithm: Pbkdf2Params,
        baseKey: CryptoKey,
        derivedKeyType: String,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("deriveKey")
    fun deriveKeyAsync(
        algorithm: Algorithm,
        baseKey: CryptoKey,
        derivedKeyType: AesDerivedKeyParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("deriveKey")
    fun deriveKeyAsync(
        algorithm: String,
        baseKey: CryptoKey,
        derivedKeyType: AesDerivedKeyParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("deriveKey")
    fun deriveKeyAsync(
        algorithm: EcdhKeyDeriveParams,
        baseKey: CryptoKey,
        derivedKeyType: AesDerivedKeyParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("deriveKey")
    fun deriveKeyAsync(
        algorithm: HkdfParams,
        baseKey: CryptoKey,
        derivedKeyType: AesDerivedKeyParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("deriveKey")
    fun deriveKeyAsync(
        algorithm: Pbkdf2Params,
        baseKey: CryptoKey,
        derivedKeyType: AesDerivedKeyParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("deriveKey")
    fun deriveKeyAsync(
        algorithm: Algorithm,
        baseKey: CryptoKey,
        derivedKeyType: HmacImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("deriveKey")
    fun deriveKeyAsync(
        algorithm: String,
        baseKey: CryptoKey,
        derivedKeyType: HmacImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("deriveKey")
    fun deriveKeyAsync(
        algorithm: EcdhKeyDeriveParams,
        baseKey: CryptoKey,
        derivedKeyType: HmacImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("deriveKey")
    fun deriveKeyAsync(
        algorithm: HkdfParams,
        baseKey: CryptoKey,
        derivedKeyType: HmacImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("deriveKey")
    fun deriveKeyAsync(
        algorithm: Pbkdf2Params,
        baseKey: CryptoKey,
        derivedKeyType: HmacImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("deriveKey")
    fun deriveKeyAsync(
        algorithm: Algorithm,
        baseKey: CryptoKey,
        derivedKeyType: HkdfParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("deriveKey")
    fun deriveKeyAsync(
        algorithm: String,
        baseKey: CryptoKey,
        derivedKeyType: HkdfParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("deriveKey")
    fun deriveKeyAsync(
        algorithm: EcdhKeyDeriveParams,
        baseKey: CryptoKey,
        derivedKeyType: HkdfParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("deriveKey")
    fun deriveKeyAsync(
        algorithm: HkdfParams,
        baseKey: CryptoKey,
        derivedKeyType: HkdfParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("deriveKey")
    fun deriveKeyAsync(
        algorithm: Pbkdf2Params,
        baseKey: CryptoKey,
        derivedKeyType: HkdfParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("deriveKey")
    fun deriveKeyAsync(
        algorithm: Algorithm,
        baseKey: CryptoKey,
        derivedKeyType: Pbkdf2Params,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("deriveKey")
    fun deriveKeyAsync(
        algorithm: String,
        baseKey: CryptoKey,
        derivedKeyType: Pbkdf2Params,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("deriveKey")
    fun deriveKeyAsync(
        algorithm: EcdhKeyDeriveParams,
        baseKey: CryptoKey,
        derivedKeyType: Pbkdf2Params,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("deriveKey")
    fun deriveKeyAsync(
        algorithm: HkdfParams,
        baseKey: CryptoKey,
        derivedKeyType: Pbkdf2Params,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("deriveKey")
    fun deriveKeyAsync(
        algorithm: Pbkdf2Params,
        baseKey: CryptoKey,
        derivedKeyType: Pbkdf2Params,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/SubtleCrypto/digest)
     */
    @JsName("digest")
    fun digestAsync(
        algorithm: Algorithm,
        data: BufferSource,
    ): Promise<ArrayBuffer>

    @JsName("digest")
    fun digestAsync(
        algorithm: String,
        data: BufferSource,
    ): Promise<ArrayBuffer>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/SubtleCrypto/encrypt)
     */
    @JsName("encrypt")
    fun encryptAsync(
        algorithm: Algorithm,
        key: CryptoKey,
        data: BufferSource,
    ): Promise<ArrayBuffer>

    @JsName("encrypt")
    fun encryptAsync(
        algorithm: String,
        key: CryptoKey,
        data: BufferSource,
    ): Promise<ArrayBuffer>

    @JsName("encrypt")
    fun encryptAsync(
        algorithm: RsaOaepParams,
        key: CryptoKey,
        data: BufferSource,
    ): Promise<ArrayBuffer>

    @JsName("encrypt")
    fun encryptAsync(
        algorithm: AesCtrParams,
        key: CryptoKey,
        data: BufferSource,
    ): Promise<ArrayBuffer>

    @JsName("encrypt")
    fun encryptAsync(
        algorithm: AesCbcParams,
        key: CryptoKey,
        data: BufferSource,
    ): Promise<ArrayBuffer>

    @JsName("encrypt")
    fun encryptAsync(
        algorithm: AesGcmParams,
        key: CryptoKey,
        data: BufferSource,
    ): Promise<ArrayBuffer>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/SubtleCrypto/exportKey)
     */
    @JsName("exportKey")
    fun exportKeyAsync(
        format: KeyFormat.jwk,
        key: CryptoKey,
    ): Promise<JsonWebKey>

    @JsName("exportKey")
    fun exportKeyAsync(
        format: KeyFormat,
        key: CryptoKey,
    ): Promise<ArrayBuffer>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/SubtleCrypto/generateKey)
     */
    @JsName("generateKey")
    fun generateKeyAsync(
        algorithm: Ed25519,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage /* "sign" | "verify" */>,
    ): Promise<CryptoKeyPair>

    @JsName("generateKey")
    fun generateKeyAsync(
        algorithm: RsaHashedKeyGenParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKeyPair>

    @JsName("generateKey")
    fun generateKeyAsync(
        algorithm: EcKeyGenParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKeyPair>

    @JsName("generateKey")
    fun generateKeyAsync(
        algorithm: AesKeyGenParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("generateKey")
    fun generateKeyAsync(
        algorithm: HmacKeyGenParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("generateKey")
    fun generateKeyAsync(
        algorithm: Pbkdf2Params,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("generateKey")
    fun generateKeyAsync(
        algorithm: Algorithm,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<Any /* CryptoKeyPair | CryptoKey */>

    @JsName("generateKey")
    fun generateKeyAsync(
        algorithm: String,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<Any /* CryptoKeyPair | CryptoKey */>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/SubtleCrypto/importKey)
     */
    @JsName("importKey")
    fun importKeyAsync(
        format: KeyFormat.jwk,
        keyData: JsonWebKey,
        algorithm: Algorithm,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("importKey")
    fun importKeyAsync(
        format: KeyFormat.jwk,
        keyData: JsonWebKey,
        algorithm: String,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("importKey")
    fun importKeyAsync(
        format: KeyFormat.jwk,
        keyData: JsonWebKey,
        algorithm: RsaHashedImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("importKey")
    fun importKeyAsync(
        format: KeyFormat.jwk,
        keyData: JsonWebKey,
        algorithm: EcKeyImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("importKey")
    fun importKeyAsync(
        format: KeyFormat.jwk,
        keyData: JsonWebKey,
        algorithm: HmacImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("importKey")
    fun importKeyAsync(
        format: KeyFormat.jwk,
        keyData: JsonWebKey,
        algorithm: AesKeyAlgorithm,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("importKey")
    fun importKeyAsync(
        format: KeyFormat,
        keyData: BufferSource,
        algorithm: Algorithm,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("importKey")
    fun importKeyAsync(
        format: KeyFormat,
        keyData: BufferSource,
        algorithm: String,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("importKey")
    fun importKeyAsync(
        format: KeyFormat,
        keyData: BufferSource,
        algorithm: RsaHashedImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("importKey")
    fun importKeyAsync(
        format: KeyFormat,
        keyData: BufferSource,
        algorithm: EcKeyImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("importKey")
    fun importKeyAsync(
        format: KeyFormat,
        keyData: BufferSource,
        algorithm: HmacImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("importKey")
    fun importKeyAsync(
        format: KeyFormat,
        keyData: BufferSource,
        algorithm: AesKeyAlgorithm,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/SubtleCrypto/sign)
     */
    @JsName("sign")
    fun signAsync(
        algorithm: Algorithm,
        key: CryptoKey,
        data: BufferSource,
    ): Promise<ArrayBuffer>

    @JsName("sign")
    fun signAsync(
        algorithm: String,
        key: CryptoKey,
        data: BufferSource,
    ): Promise<ArrayBuffer>

    @JsName("sign")
    fun signAsync(
        algorithm: RsaPssParams,
        key: CryptoKey,
        data: BufferSource,
    ): Promise<ArrayBuffer>

    @JsName("sign")
    fun signAsync(
        algorithm: EcdsaParams,
        key: CryptoKey,
        data: BufferSource,
    ): Promise<ArrayBuffer>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/SubtleCrypto/unwrapKey)
     */
    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: Algorithm,
        unwrappedKeyAlgorithm: Algorithm,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: String,
        unwrappedKeyAlgorithm: String,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: RsaOaepParams,
        unwrappedKeyAlgorithm: Algorithm,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: RsaOaepParams,
        unwrappedKeyAlgorithm: String,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: AesCtrParams,
        unwrappedKeyAlgorithm: Algorithm,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: AesCtrParams,
        unwrappedKeyAlgorithm: String,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: AesCbcParams,
        unwrappedKeyAlgorithm: Algorithm,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: AesCbcParams,
        unwrappedKeyAlgorithm: String,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: AesGcmParams,
        unwrappedKeyAlgorithm: Algorithm,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: AesGcmParams,
        unwrappedKeyAlgorithm: String,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: Algorithm,
        unwrappedKeyAlgorithm: RsaHashedImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: String,
        unwrappedKeyAlgorithm: RsaHashedImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: RsaOaepParams,
        unwrappedKeyAlgorithm: RsaHashedImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: AesCtrParams,
        unwrappedKeyAlgorithm: RsaHashedImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: AesCbcParams,
        unwrappedKeyAlgorithm: RsaHashedImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: AesGcmParams,
        unwrappedKeyAlgorithm: RsaHashedImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: Algorithm,
        unwrappedKeyAlgorithm: EcKeyImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: String,
        unwrappedKeyAlgorithm: EcKeyImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: RsaOaepParams,
        unwrappedKeyAlgorithm: EcKeyImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: AesCtrParams,
        unwrappedKeyAlgorithm: EcKeyImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: AesCbcParams,
        unwrappedKeyAlgorithm: EcKeyImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: AesGcmParams,
        unwrappedKeyAlgorithm: EcKeyImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: Algorithm,
        unwrappedKeyAlgorithm: HmacImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: String,
        unwrappedKeyAlgorithm: HmacImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: RsaOaepParams,
        unwrappedKeyAlgorithm: HmacImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: AesCtrParams,
        unwrappedKeyAlgorithm: HmacImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: AesCbcParams,
        unwrappedKeyAlgorithm: HmacImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: AesGcmParams,
        unwrappedKeyAlgorithm: HmacImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: Algorithm,
        unwrappedKeyAlgorithm: AesKeyAlgorithm,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: String,
        unwrappedKeyAlgorithm: AesKeyAlgorithm,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: RsaOaepParams,
        unwrappedKeyAlgorithm: AesKeyAlgorithm,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: AesCtrParams,
        unwrappedKeyAlgorithm: AesKeyAlgorithm,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: AesCbcParams,
        unwrappedKeyAlgorithm: AesKeyAlgorithm,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    @JsName("unwrapKey")
    fun unwrapKeyAsync(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: AesGcmParams,
        unwrappedKeyAlgorithm: AesKeyAlgorithm,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/SubtleCrypto/verify)
     */
    @JsName("verify")
    fun verifyAsync(
        algorithm: Algorithm,
        key: CryptoKey,
        signature: BufferSource,
        data: BufferSource,
    ): Promise<Boolean>

    @JsName("verify")
    fun verifyAsync(
        algorithm: String,
        key: CryptoKey,
        signature: BufferSource,
        data: BufferSource,
    ): Promise<Boolean>

    @JsName("verify")
    fun verifyAsync(
        algorithm: RsaPssParams,
        key: CryptoKey,
        signature: BufferSource,
        data: BufferSource,
    ): Promise<Boolean>

    @JsName("verify")
    fun verifyAsync(
        algorithm: EcdsaParams,
        key: CryptoKey,
        signature: BufferSource,
        data: BufferSource,
    ): Promise<Boolean>

    /**
     * [MDN Reference](https://developer.mozilla.org/docs/Web/API/SubtleCrypto/wrapKey)
     */
    @JsName("wrapKey")
    fun wrapKeyAsync(
        format: KeyFormat,
        key: CryptoKey,
        wrappingKey: CryptoKey,
        wrapAlgorithm: Algorithm,
    ): Promise<ArrayBuffer>

    @JsName("wrapKey")
    fun wrapKeyAsync(
        format: KeyFormat,
        key: CryptoKey,
        wrappingKey: CryptoKey,
        wrapAlgorithm: String,
    ): Promise<ArrayBuffer>

    @JsName("wrapKey")
    fun wrapKeyAsync(
        format: KeyFormat,
        key: CryptoKey,
        wrappingKey: CryptoKey,
        wrapAlgorithm: RsaOaepParams,
    ): Promise<ArrayBuffer>

    @JsName("wrapKey")
    fun wrapKeyAsync(
        format: KeyFormat,
        key: CryptoKey,
        wrappingKey: CryptoKey,
        wrapAlgorithm: AesCtrParams,
    ): Promise<ArrayBuffer>

    @JsName("wrapKey")
    fun wrapKeyAsync(
        format: KeyFormat,
        key: CryptoKey,
        wrappingKey: CryptoKey,
        wrapAlgorithm: AesCbcParams,
    ): Promise<ArrayBuffer>

    @JsName("wrapKey")
    fun wrapKeyAsync(
        format: KeyFormat,
        key: CryptoKey,
        wrappingKey: CryptoKey,
        wrapAlgorithm: AesGcmParams,
    ): Promise<ArrayBuffer>
}
