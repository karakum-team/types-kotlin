package actions.io

external interface CopyOptions {
    /** Optional. Whether to recursively copy all subdirectories. Defaults to false */
    // recursive?: boolean
    /** Optional. Whether to overwrite existing files in the destination. Defaults to true */
    // force?: boolean
    /** Optional. Whether to copy the source directory along with all the files. Only takes effect when recursive=true and copying a directory. Default is true*/
    // copySourceDirectory?: boolean
}