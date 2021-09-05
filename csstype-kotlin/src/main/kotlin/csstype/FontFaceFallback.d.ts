// Automatically generated - do not modify!

package csstype

type FontFaceFallback<TLength = (string & {}) | 0, TTime = string & {}> = {
  [P in keyof FontFace<TLength, TTime>]: FontFace<TLength, TTime>[P] | FontFace<TLength, TTime>[P][];
};
