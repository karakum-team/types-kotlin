class CRC64 {
    constructor();

    update(data: Buffer | string): void;

    digest(encoding?: CRC64DigestEncoding): string | Buffer;

    private toBuffer;

    static flip64Bits(n: bigint): bigint;
}
