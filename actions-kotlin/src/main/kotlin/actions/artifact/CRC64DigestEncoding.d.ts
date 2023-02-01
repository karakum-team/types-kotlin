type CRC64DigestEncoding = 'hex' | 'base64' | 'buffer';

declare class CRC64 {
    private _crc;

    constructor();

    update(data: Buffer | string): void;

    digest(encoding?: CRC64DigestEncoding): string | Buffer;

    private toBuffer;

    static flip64Bits(n: bigint): bigint;
}
