// Automatically generated - do not modify!

package csstype

type CounterStyleFallback<TLength = (string & {}) | 0, TTime = string & {}> = {
  [P in keyof CounterStyle<TLength, TTime>]: CounterStyle<TLength, TTime>[P] | CounterStyle<TLength, TTime>[P][];
};
