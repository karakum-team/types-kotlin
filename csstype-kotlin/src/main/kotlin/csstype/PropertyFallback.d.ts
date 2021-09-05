// Automatically generated - do not modify!

package csstype

type PropertyFallback<TLength = (string & {}) | 0, TTime = string & {}> = {
  [P in keyof Property<TLength, TTime>]: Property<TLength, TTime>[P] | Property<TLength, TTime>[P][];
};
