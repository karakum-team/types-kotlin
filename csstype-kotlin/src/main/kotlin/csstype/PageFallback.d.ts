// Automatically generated - do not modify!

package csstype

type PageFallback<TLength = (string & {}) | 0, TTime = string & {}> = {
  [P in keyof Page<TLength, TTime>]: Page<TLength, TTime>[P] | Page<TLength, TTime>[P][];
};
