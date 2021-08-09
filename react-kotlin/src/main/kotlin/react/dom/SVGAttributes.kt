// Automatically generated - do not modify!

package react.dom

external interface SVGAttributes<T> : AriaAttributes, DOMAttributes<T> {
    // Attributes which also defined in HTMLAttributes
// See comment in SVGDOMPropertyConfig.js
    var className: String
    var color: String
    var height: Double
    var id: String
    var lang: String
    var max: Double
    var media: String
    var method: String
    var min: Double
    var name: String
    var style: dynamic // CSSProperties
    var target: String
    var type: String
    var width: Double

    // Other HTML properties supported by SVG elements in browsers
    var role: String
    var tabIndex: Int
    var crossOrigin: String // "anonymous" | "use-credentials" | ""

    // SVG Specific attributes
    var accentHeight: Double
    var accumulate: String // "none" | "sum"
    var additive: String // "replace" | "sum"
    var alignmentBaseline: String // "auto" | "baseline" | "before-edge" | "text-before-edge" | "middle" | "central" | "after-edge" |"text-after-edge" | "ideographic" | "alphabetic" | "hanging" | "mathematical" | "inherit"
    var allowReorder: String // "no" | "yes"
    var alphabetic: Number
    var amplitude: Number
    var arabicForm: String // "initial" | "medial" | "terminal" | "isolated"
    var ascent: Number
    var attributeName: String
    var attributeType: String
    var autoReverse: Boolean
    var azimuth: Number
    var baseFrequency: Number
    var baselineShift: Number
    var baseProfile: Number
    var bbox: Number
    var begin: Number
    var bias: Number
    var by: Number
    var calcMode: Number
    var capHeight: Double
    var clip: Number
    var clipPath: String
    var clipPathUnits: Number
    var clipRule: Number
    var colorInterpolation: Number
    var colorInterpolationFilters: String // "auto" | "sRGB" | "linearRGB" | "inherit"
    var colorProfile: Number
    var colorRendering: Number
    var contentScriptType: Number
    var contentStyleType: Number
    var cursor: Number
    var cx: Double
    var cy: Double
    var d: String
    var decelerate: Number
    var descent: Number
    var diffuseConstant: Number
    var direction: Number
    var display: Number
    var divisor: Number
    var dominantBaseline: Number
    var dur: Number
    var dx: Double
    var dy: Double
    var edgeMode: Number
    var elevation: Number
    var enableBackground: Number
    var end: Number
    var exponent: Number
    var externalResourcesRequired: Boolean
    var fill: String
    var fillOpacity: Number
    var fillRule: String // "nonzero" | "evenodd" | "inherit"
    var filter: String
    var filterRes: Number
    var filterUnits: Number
    var floodColor: Number
    var floodOpacity: Number
    var focusable: dynamic // Booleanish | "auto"
    var fontFamily: String
    var fontSize: Number
    var fontSizeAdjust: Number
    var fontStretch: Number
    var fontStyle: Number
    var fontVariant: Number
    var fontWeight: Number
    var format: Number
    var from: Number
    var fx: Double
    var fy: Double
    var g1: Number
    var g2: Number
    var glyphName: Number
    var glyphOrientationHorizontal: Number
    var glyphOrientationVertical: Number
    var glyphRef: Number
    var gradientTransform: String
    var gradientUnits: String
    var hanging: Number
    var horizAdvX: Double
    var horizOriginX: Double
    var href: String
    var ideographic: Number
    var imageRendering: Number
    var in2: Number
    var `in`: String
    var intercept: Number
    var k1: Number
    var k2: Number
    var k3: Number
    var k4: Number
    var k: Number
    var kernelMatrix: Number
    var kernelUnitLength: Number
    var kerning: Number
    var keyPoints: Number
    var keySplines: Number
    var keyTimes: Number
    var lengthAdjust: Number
    var letterSpacing: Number
    var lightingColor: Number
    var limitingConeAngle: Number
    var local: Number
    var markerEnd: String
    var markerHeight: Double
    var markerMid: String
    var markerStart: String
    var markerUnits: Number
    var markerWidth: Double
    var mask: String
    var maskContentUnits: Number
    var maskUnits: Number
    var mathematical: Number
    var mode: Number
    var numOctaves: Number
    var offset: Number
    var opacity: Number
    var operator: Number
    var order: Number
    var orient: Number
    var orientation: Number
    var origin: Number
    var overflow: Number
    var overlinePosition: Number
    var overlineThickness: Number
    var paintOrder: Number
    var panose1: Number
    var path: String
    var pathLength: Number
    var patternContentUnits: String
    var patternTransform: Number
    var patternUnits: String
    var pointerEvents: Number
    var points: String
    var pointsAtX: Double
    var pointsAtY: Double
    var pointsAtZ: Double
    var preserveAlpha: Boolean
    var preserveAspectRatio: String
    var primitiveUnits: Number
    var r: Double
    var radius: Double
    var refX: Double
    var refY: Double
    var renderingIntent: Number
    var repeatCount: Number
    var repeatDur: Number
    var requiredExtensions: Number
    var requiredFeatures: Number
    var restart: Number
    var result: String
    var rotate: Number
    var rx: Double
    var ry: Double
    var scale: Number
    var seed: Number
    var shapeRendering: Number
    var slope: Number
    var spacing: Number
    var specularConstant: Number
    var specularExponent: Number
    var speed: Number
    var spreadMethod: String
    var startOffset: Number
    var stdDeviation: Number
    var stemh: Number
    var stemv: Number
    var stitchTiles: Number
    var stopColor: String
    var stopOpacity: Number
    var strikethroughPosition: Number
    var strikethroughThickness: Number
    var string: Number
    var stroke: String
    var strokeDasharray: dynamic // string | number
    var strokeDashoffset: dynamic // string | number
    var strokeLinecap: String // "butt" | "round" | "square" | "inherit"
    var strokeLinejoin: String // "miter" | "round" | "bevel" | "inherit"
    var strokeMiterlimit: Number
    var strokeOpacity: Number
    var strokeWidth: Double
    var surfaceScale: Number
    var systemLanguage: Number
    var tableValues: Number
    var targetX: Double
    var targetY: Double
    var textAnchor: String
    var textDecoration: Number
    var textLength: Number
    var textRendering: Number
    var to: Number
    var transform: String
    var u1: Number
    var u2: Number
    var underlinePosition: Number
    var underlineThickness: Number
    var unicode: Number
    var unicodeBidi: Number
    var unicodeRange: Number
    var unitsPerEm: Number
    var vAlphabetic: Number
    var values: String
    var vectorEffect: Number
    var version: String
    var vertAdvY: Double
    var vertOriginX: Double
    var vertOriginY: Double
    var vHanging: Number
    var vIdeographic: Number
    var viewBox: String
    var viewTarget: Number
    var visibility: Number
    var vMathematical: Number
    var widths: Number
    var wordSpacing: Number
    var writingMode: Number
    var x1: Double
    var x2: Double
    var x: Double
    var xChannelSelector: String
    var xHeight: Double
    var xlinkActuate: String
    var xlinkArcrole: String
    var xlinkHref: String
    var xlinkRole: String
    var xlinkShow: String
    var xlinkTitle: String
    var xlinkType: String
    var xmlBase: String
    var xmlLang: String
    var xmlns: String
    var xmlnsXlink: String
    var xmlSpace: String
    var y1: Double
    var y2: Double
    var y: Double
    var yChannelSelector: String
    var z: Double
    var zoomAndPan: String
}
