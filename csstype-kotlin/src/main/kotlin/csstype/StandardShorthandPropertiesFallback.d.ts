// Automatically generated - do not modify!

package csstype

type StandardShorthandPropertiesFallback<TLength = (string & {}) | 0, TTime = string & {}> = {
  [P in keyof StandardShorthandProperties<TLength, TTime>]: StandardShorthandProperties<TLength, TTime>[P] | StandardShorthandProperties<TLength, TTime>[P][];
};
