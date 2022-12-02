// Automatically generated - do not modify!

package web.crypto

import js.buffer.ArrayBuffer
import js.buffer.BufferSource
import js.core.ReadonlyArray
import kotlin.js.Promise

sealed external class SubtleCrypto {
    fun decrypt(
        algorithm: Algorithm,
        key: CryptoKey,
        data: BufferSource,
    ): Promise<ArrayBuffer>

    fun decrypt(
        algorithm: String,
        key: CryptoKey,
        data: BufferSource,
    ): Promise<ArrayBuffer>

    fun decrypt(
        algorithm: RsaOaepParams,
        key: CryptoKey,
        data: BufferSource,
    ): Promise<ArrayBuffer>

    fun deriveBits(
        algorithm: Algorithm,
        baseKey: CryptoKey,
        length: Number,
    ): Promise<ArrayBuffer>

    fun deriveBits(
        algorithm: String,
        baseKey: CryptoKey,
        length: Number,
    ): Promise<ArrayBuffer>

    fun deriveBits(
        algorithm: EcdhKeyDeriveParams,
        baseKey: CryptoKey,
        length: Number,
    ): Promise<ArrayBuffer>

    fun deriveKey(
        algorithm: Algorithm,
        baseKey: CryptoKey,
        derivedKeyType: Algorithm,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    fun deriveKey(
        algorithm: String,
        baseKey: CryptoKey,
        derivedKeyType: String,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    fun deriveKey(
        algorithm: EcdhKeyDeriveParams,
        baseKey: CryptoKey,
        derivedKeyType: Algorithm,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    fun deriveKey(
        algorithm: EcdhKeyDeriveParams,
        baseKey: CryptoKey,
        derivedKeyType: String,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    fun deriveKey(
        algorithm: Algorithm,
        baseKey: CryptoKey,
        derivedKeyType: AesDerivedKeyParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    fun deriveKey(
        algorithm: String,
        baseKey: CryptoKey,
        derivedKeyType: AesDerivedKeyParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    fun deriveKey(
        algorithm: EcdhKeyDeriveParams,
        baseKey: CryptoKey,
        derivedKeyType: AesDerivedKeyParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    fun digest(
        algorithm: Algorithm,
        data: BufferSource,
    ): Promise<ArrayBuffer>

    fun digest(
        algorithm: String,
        data: BufferSource,
    ): Promise<ArrayBuffer>

    fun encrypt(
        algorithm: Algorithm,
        key: CryptoKey,
        data: BufferSource,
    ): Promise<ArrayBuffer>

    fun encrypt(
        algorithm: String,
        key: CryptoKey,
        data: BufferSource,
    ): Promise<ArrayBuffer>

    fun encrypt(
        algorithm: RsaOaepParams,
        key: CryptoKey,
        data: BufferSource,
    ): Promise<ArrayBuffer>

    fun exportKey(
        format: KeyFormat.jwk,
        key: CryptoKey,
    ): Promise<JsonWebKey>

    fun exportKey(
        format: KeyFormat,
        key: CryptoKey,
    ): Promise<ArrayBuffer>

    fun generateKey(
        algorithm: RsaHashedKeyGenParams | EcKeyGenParams,
    extractable: Boolean,
    keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKeyPair>
    fun generateKey(
        algorithm: AesKeyGenParams | HmacKeyGenParams | Pbkdf2Params,
    extractable: Boolean,
    keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>
    fun generateKey(
        algorithm: Algorithm,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<Any /* CryptoKeyPair | CryptoKey */>

    fun generateKey(
        algorithm: String,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<Any /* CryptoKeyPair | CryptoKey */>

    fun importKey(
        format: KeyFormat.jwk,
        keyData: JsonWebKey,
        algorithm: Algorithm,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    fun importKey(
        format: KeyFormat.jwk,
        keyData: JsonWebKey,
        algorithm: String,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    fun importKey(
        format: KeyFormat.jwk,
        keyData: JsonWebKey,
        algorithm: RsaHashedImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    fun importKey(
        format: KeyFormat,
        keyData: BufferSource,
        algorithm: Algorithm,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    fun importKey(
        format: KeyFormat,
        keyData: BufferSource,
        algorithm: String,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    fun importKey(
        format: KeyFormat,
        keyData: BufferSource,
        algorithm: RsaHashedImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    fun sign(
        algorithm: Algorithm,
        key: CryptoKey,
        data: BufferSource,
    ): Promise<ArrayBuffer>

    fun sign(
        algorithm: String,
        key: CryptoKey,
        data: BufferSource,
    ): Promise<ArrayBuffer>

    fun sign(
        algorithm: RsaPssParams,
        key: CryptoKey,
        data: BufferSource,
    ): Promise<ArrayBuffer>

    fun unwrapKey(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: Algorithm,
        unwrappedKeyAlgorithm: Algorithm,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    fun unwrapKey(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: String,
        unwrappedKeyAlgorithm: String,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    fun unwrapKey(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: RsaOaepParams,
        unwrappedKeyAlgorithm: Algorithm,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    fun unwrapKey(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: RsaOaepParams,
        unwrappedKeyAlgorithm: String,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    fun unwrapKey(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: Algorithm,
        unwrappedKeyAlgorithm: RsaHashedImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    fun unwrapKey(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: String,
        unwrappedKeyAlgorithm: RsaHashedImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    fun unwrapKey(
        format: KeyFormat,
        wrappedKey: BufferSource,
        unwrappingKey: CryptoKey,
        unwrapAlgorithm: RsaOaepParams,
        unwrappedKeyAlgorithm: RsaHashedImportParams,
        extractable: Boolean,
        keyUsages: ReadonlyArray<KeyUsage>,
    ): Promise<CryptoKey>

    fun verify(
        algorithm: Algorithm,
        key: CryptoKey,
        signature: BufferSource,
        data: BufferSource,
    ): Promise<Boolean>

    fun verify(
        algorithm: String,
        key: CryptoKey,
        signature: BufferSource,
        data: BufferSource,
    ): Promise<Boolean>

    fun verify(
        algorithm: RsaPssParams,
        key: CryptoKey,
        signature: BufferSource,
        data: BufferSource,
    ): Promise<Boolean>

    fun wrapKey(
        format: KeyFormat,
        key: CryptoKey,
        wrappingKey: CryptoKey,
        wrapAlgorithm: Algorithm,
    ): Promise<ArrayBuffer>

    fun wrapKey(
        format: KeyFormat,
        key: CryptoKey,
        wrappingKey: CryptoKey,
        wrapAlgorithm: String,
    ): Promise<ArrayBuffer>

    fun wrapKey(
        format: KeyFormat,
        key: CryptoKey,
        wrappingKey: CryptoKey,
        wrapAlgorithm: RsaOaepParams,
    ): Promise<ArrayBuffer>
}
