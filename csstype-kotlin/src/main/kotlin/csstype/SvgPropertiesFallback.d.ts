// Automatically generated - do not modify!

package csstype

type SvgPropertiesFallback<TLength = (string & {}) | 0, TTime = string & {}> = {
  [P in keyof SvgProperties<TLength, TTime>]: SvgProperties<TLength, TTime>[P] | SvgProperties<TLength, TTime>[P][];
};
