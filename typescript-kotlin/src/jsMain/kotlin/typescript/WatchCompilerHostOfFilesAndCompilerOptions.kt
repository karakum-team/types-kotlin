// Automatically generated - do not modify!

package typescript

import js.array.ReadonlyArray

/**
 * Host to create watch with root files and options
 */
external sealed interface WatchCompilerHostOfFilesAndCompilerOptions<T : BuilderProgram> : WatchCompilerHost<T> {
/** root files to use to generate program */
var rootFiles: ReadonlyArray<String>
/** Compiler options */
var options: CompilerOptions
var watchOptions: WatchOptions?
/** Project References */
var projectReferences: ReadonlyArray<ProjectReference>?
}
