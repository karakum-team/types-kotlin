// Automatically generated - do not modify!

package csstype

type PropertyValue<TValue> = TValue extends Array<infer AValue>
  ? Array<AValue extends infer TUnpacked & {} ? TUnpacked : AValue>
  : TValue extends infer TUnpacked & {}
  ? TUnpacked
  : TValue;
