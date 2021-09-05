// Automatically generated - do not modify!

package csstype

type StandardLonghandPropertiesFallback<TLength = (string & {}) | 0, TTime = string & {}> = {
  [P in keyof StandardLonghandProperties<TLength, TTime>]: StandardLonghandProperties<TLength, TTime>[P] | StandardLonghandProperties<TLength, TTime>[P][];
};
