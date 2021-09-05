// Automatically generated - do not modify!

package csstype

type ViewportFallback<TLength = (string & {}) | 0, TTime = string & {}> = {
  [P in keyof Viewport<TLength, TTime>]: Viewport<TLength, TTime>[P] | Viewport<TLength, TTime>[P][];
};
