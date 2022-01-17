// Automatically generated - do not modify!

package typescript

/**
 * Given a set of options, returns the set of type directive names
 *   that should be included for this program automatically.
 * This list could either come from the config file,
 *   or from enumerating the types root + initial secondary types lookup location.
 * More type directives might appear in the program later as a result of loading actual source files;
 *   this list is only the set of defaults that are implicitly included.
 */
external interface ModeAwareCache<T> {
    /*
    get(key: string, mode: ModuleKind.CommonJS | ModuleKind.ESNext | undefined): T | undefined;
    set(key: string, mode: ModuleKind.CommonJS | ModuleKind.ESNext | undefined, value: T): this;
    delete(key: string, mode: ModuleKind.CommonJS | ModuleKind.ESNext | undefined): this;
    has(key: string, mode: ModuleKind.CommonJS | ModuleKind.ESNext | undefined): boolean;
    forEach(cb: (elem: T, key: string, mode: ModuleKind.CommonJS | ModuleKind.ESNext | undefined) => void): void;
    size(): number;
    */
}
