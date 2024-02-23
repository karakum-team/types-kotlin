// Automatically generated - do not modify!

@file:Suppress(
"NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

package typescript

import seskar.js.JsVirtual
import seskar.js.JsIntValue

        @JsVirtual
        external sealed interface SyntaxKind {
            companion object {
                @JsIntValue(0)
val Unknown : SyntaxKind.Unknown
@JsIntValue(1)
val EndOfFileToken : SyntaxKind.EndOfFileToken
@JsIntValue(2)
val SingleLineCommentTrivia : SyntaxKind.SingleLineCommentTrivia
@JsIntValue(3)
val MultiLineCommentTrivia : SyntaxKind.MultiLineCommentTrivia
@JsIntValue(4)
val NewLineTrivia : SyntaxKind.NewLineTrivia
@JsIntValue(5)
val WhitespaceTrivia : SyntaxKind.WhitespaceTrivia
@JsIntValue(6)
val ShebangTrivia : SyntaxKind.ShebangTrivia
@JsIntValue(7)
val ConflictMarkerTrivia : SyntaxKind.ConflictMarkerTrivia
@JsIntValue(8)
val NonTextFileMarkerTrivia : SyntaxKind.NonTextFileMarkerTrivia
@JsIntValue(9)
val NumericLiteral : SyntaxKind.NumericLiteral
@JsIntValue(10)
val BigIntLiteral : SyntaxKind.BigIntLiteral
@JsIntValue(11)
val StringLiteral : SyntaxKind.StringLiteral
@JsIntValue(12)
val JsxText : SyntaxKind.JsxText
@JsIntValue(13)
val JsxTextAllWhiteSpaces : SyntaxKind.JsxTextAllWhiteSpaces
@JsIntValue(14)
val RegularExpressionLiteral : SyntaxKind.RegularExpressionLiteral
@JsIntValue(15)
val NoSubstitutionTemplateLiteral : SyntaxKind.NoSubstitutionTemplateLiteral
@JsIntValue(16)
val TemplateHead : SyntaxKind.TemplateHead
@JsIntValue(17)
val TemplateMiddle : SyntaxKind.TemplateMiddle
@JsIntValue(18)
val TemplateTail : SyntaxKind.TemplateTail
@JsIntValue(19)
val OpenBraceToken : SyntaxKind.OpenBraceToken
@JsIntValue(20)
val CloseBraceToken : SyntaxKind.CloseBraceToken
@JsIntValue(21)
val OpenParenToken : SyntaxKind.OpenParenToken
@JsIntValue(22)
val CloseParenToken : SyntaxKind.CloseParenToken
@JsIntValue(23)
val OpenBracketToken : SyntaxKind.OpenBracketToken
@JsIntValue(24)
val CloseBracketToken : SyntaxKind.CloseBracketToken
@JsIntValue(25)
val DotToken : SyntaxKind.DotToken
@JsIntValue(26)
val DotDotDotToken : SyntaxKind.DotDotDotToken
@JsIntValue(27)
val SemicolonToken : SyntaxKind.SemicolonToken
@JsIntValue(28)
val CommaToken : SyntaxKind.CommaToken
@JsIntValue(29)
val QuestionDotToken : SyntaxKind.QuestionDotToken
@JsIntValue(30)
val LessThanToken : SyntaxKind.LessThanToken
@JsIntValue(31)
val LessThanSlashToken : SyntaxKind.LessThanSlashToken
@JsIntValue(32)
val GreaterThanToken : SyntaxKind.GreaterThanToken
@JsIntValue(33)
val LessThanEqualsToken : SyntaxKind.LessThanEqualsToken
@JsIntValue(34)
val GreaterThanEqualsToken : SyntaxKind.GreaterThanEqualsToken
@JsIntValue(35)
val EqualsEqualsToken : SyntaxKind.EqualsEqualsToken
@JsIntValue(36)
val ExclamationEqualsToken : SyntaxKind.ExclamationEqualsToken
@JsIntValue(37)
val EqualsEqualsEqualsToken : SyntaxKind.EqualsEqualsEqualsToken
@JsIntValue(38)
val ExclamationEqualsEqualsToken : SyntaxKind.ExclamationEqualsEqualsToken
@JsIntValue(39)
val EqualsGreaterThanToken : SyntaxKind.EqualsGreaterThanToken
@JsIntValue(40)
val PlusToken : SyntaxKind.PlusToken
@JsIntValue(41)
val MinusToken : SyntaxKind.MinusToken
@JsIntValue(42)
val AsteriskToken : SyntaxKind.AsteriskToken
@JsIntValue(43)
val AsteriskAsteriskToken : SyntaxKind.AsteriskAsteriskToken
@JsIntValue(44)
val SlashToken : SyntaxKind.SlashToken
@JsIntValue(45)
val PercentToken : SyntaxKind.PercentToken
@JsIntValue(46)
val PlusPlusToken : SyntaxKind.PlusPlusToken
@JsIntValue(47)
val MinusMinusToken : SyntaxKind.MinusMinusToken
@JsIntValue(48)
val LessThanLessThanToken : SyntaxKind.LessThanLessThanToken
@JsIntValue(49)
val GreaterThanGreaterThanToken : SyntaxKind.GreaterThanGreaterThanToken
@JsIntValue(50)
val GreaterThanGreaterThanGreaterThanToken : SyntaxKind.GreaterThanGreaterThanGreaterThanToken
@JsIntValue(51)
val AmpersandToken : SyntaxKind.AmpersandToken
@JsIntValue(52)
val BarToken : SyntaxKind.BarToken
@JsIntValue(53)
val CaretToken : SyntaxKind.CaretToken
@JsIntValue(54)
val ExclamationToken : SyntaxKind.ExclamationToken
@JsIntValue(55)
val TildeToken : SyntaxKind.TildeToken
@JsIntValue(56)
val AmpersandAmpersandToken : SyntaxKind.AmpersandAmpersandToken
@JsIntValue(57)
val BarBarToken : SyntaxKind.BarBarToken
@JsIntValue(58)
val QuestionToken : SyntaxKind.QuestionToken
@JsIntValue(59)
val ColonToken : SyntaxKind.ColonToken
@JsIntValue(60)
val AtToken : SyntaxKind.AtToken
@JsIntValue(61)
val QuestionQuestionToken : SyntaxKind.QuestionQuestionToken
/** Only the JSDoc scanner produces BacktickToken. The normal scanner produces NoSubstitutionTemplateLiteral and related kinds. */
@JsIntValue(62)
val BacktickToken : SyntaxKind.BacktickToken
/** Only the JSDoc scanner produces HashToken. The normal scanner produces PrivateIdentifier. */
@JsIntValue(63)
val HashToken : SyntaxKind.HashToken
@JsIntValue(64)
val EqualsToken : SyntaxKind.EqualsToken
@JsIntValue(65)
val PlusEqualsToken : SyntaxKind.PlusEqualsToken
@JsIntValue(66)
val MinusEqualsToken : SyntaxKind.MinusEqualsToken
@JsIntValue(67)
val AsteriskEqualsToken : SyntaxKind.AsteriskEqualsToken
@JsIntValue(68)
val AsteriskAsteriskEqualsToken : SyntaxKind.AsteriskAsteriskEqualsToken
@JsIntValue(69)
val SlashEqualsToken : SyntaxKind.SlashEqualsToken
@JsIntValue(70)
val PercentEqualsToken : SyntaxKind.PercentEqualsToken
@JsIntValue(71)
val LessThanLessThanEqualsToken : SyntaxKind.LessThanLessThanEqualsToken
@JsIntValue(72)
val GreaterThanGreaterThanEqualsToken : SyntaxKind.GreaterThanGreaterThanEqualsToken
@JsIntValue(73)
val GreaterThanGreaterThanGreaterThanEqualsToken : SyntaxKind.GreaterThanGreaterThanGreaterThanEqualsToken
@JsIntValue(74)
val AmpersandEqualsToken : SyntaxKind.AmpersandEqualsToken
@JsIntValue(75)
val BarEqualsToken : SyntaxKind.BarEqualsToken
@JsIntValue(76)
val BarBarEqualsToken : SyntaxKind.BarBarEqualsToken
@JsIntValue(77)
val AmpersandAmpersandEqualsToken : SyntaxKind.AmpersandAmpersandEqualsToken
@JsIntValue(78)
val QuestionQuestionEqualsToken : SyntaxKind.QuestionQuestionEqualsToken
@JsIntValue(79)
val CaretEqualsToken : SyntaxKind.CaretEqualsToken
@JsIntValue(80)
val Identifier : SyntaxKind.Identifier
@JsIntValue(81)
val PrivateIdentifier : SyntaxKind.PrivateIdentifier
@JsIntValue(83)
val BreakKeyword : SyntaxKind.BreakKeyword
@JsIntValue(84)
val CaseKeyword : SyntaxKind.CaseKeyword
@JsIntValue(85)
val CatchKeyword : SyntaxKind.CatchKeyword
@JsIntValue(86)
val ClassKeyword : SyntaxKind.ClassKeyword
@JsIntValue(87)
val ConstKeyword : SyntaxKind.ConstKeyword
@JsIntValue(88)
val ContinueKeyword : SyntaxKind.ContinueKeyword
@JsIntValue(89)
val DebuggerKeyword : SyntaxKind.DebuggerKeyword
@JsIntValue(90)
val DefaultKeyword : SyntaxKind.DefaultKeyword
@JsIntValue(91)
val DeleteKeyword : SyntaxKind.DeleteKeyword
@JsIntValue(92)
val DoKeyword : SyntaxKind.DoKeyword
@JsIntValue(93)
val ElseKeyword : SyntaxKind.ElseKeyword
@JsIntValue(94)
val EnumKeyword : SyntaxKind.EnumKeyword
@JsIntValue(95)
val ExportKeyword : SyntaxKind.ExportKeyword
@JsIntValue(96)
val ExtendsKeyword : SyntaxKind.ExtendsKeyword
@JsIntValue(97)
val FalseKeyword : SyntaxKind.FalseKeyword
@JsIntValue(98)
val FinallyKeyword : SyntaxKind.FinallyKeyword
@JsIntValue(99)
val ForKeyword : SyntaxKind.ForKeyword
@JsIntValue(100)
val FunctionKeyword : SyntaxKind.FunctionKeyword
@JsIntValue(101)
val IfKeyword : SyntaxKind.IfKeyword
@JsIntValue(102)
val ImportKeyword : SyntaxKind.ImportKeyword
@JsIntValue(103)
val InKeyword : SyntaxKind.InKeyword
@JsIntValue(104)
val InstanceOfKeyword : SyntaxKind.InstanceOfKeyword
@JsIntValue(105)
val NewKeyword : SyntaxKind.NewKeyword
@JsIntValue(106)
val NullKeyword : SyntaxKind.NullKeyword
@JsIntValue(107)
val ReturnKeyword : SyntaxKind.ReturnKeyword
@JsIntValue(108)
val SuperKeyword : SyntaxKind.SuperKeyword
@JsIntValue(109)
val SwitchKeyword : SyntaxKind.SwitchKeyword
@JsIntValue(110)
val ThisKeyword : SyntaxKind.ThisKeyword
@JsIntValue(111)
val ThrowKeyword : SyntaxKind.ThrowKeyword
@JsIntValue(112)
val TrueKeyword : SyntaxKind.TrueKeyword
@JsIntValue(113)
val TryKeyword : SyntaxKind.TryKeyword
@JsIntValue(114)
val TypeOfKeyword : SyntaxKind.TypeOfKeyword
@JsIntValue(115)
val VarKeyword : SyntaxKind.VarKeyword
@JsIntValue(116)
val VoidKeyword : SyntaxKind.VoidKeyword
@JsIntValue(117)
val WhileKeyword : SyntaxKind.WhileKeyword
@JsIntValue(118)
val WithKeyword : SyntaxKind.WithKeyword
@JsIntValue(119)
val ImplementsKeyword : SyntaxKind.ImplementsKeyword
@JsIntValue(120)
val InterfaceKeyword : SyntaxKind.InterfaceKeyword
@JsIntValue(121)
val LetKeyword : SyntaxKind.LetKeyword
@JsIntValue(122)
val PackageKeyword : SyntaxKind.PackageKeyword
@JsIntValue(123)
val PrivateKeyword : SyntaxKind.PrivateKeyword
@JsIntValue(124)
val ProtectedKeyword : SyntaxKind.ProtectedKeyword
@JsIntValue(125)
val PublicKeyword : SyntaxKind.PublicKeyword
@JsIntValue(126)
val StaticKeyword : SyntaxKind.StaticKeyword
@JsIntValue(127)
val YieldKeyword : SyntaxKind.YieldKeyword
@JsIntValue(128)
val AbstractKeyword : SyntaxKind.AbstractKeyword
@JsIntValue(129)
val AccessorKeyword : SyntaxKind.AccessorKeyword
@JsIntValue(130)
val AsKeyword : SyntaxKind.AsKeyword
@JsIntValue(131)
val AssertsKeyword : SyntaxKind.AssertsKeyword
@JsIntValue(132)
val AssertKeyword : SyntaxKind.AssertKeyword
@JsIntValue(133)
val AnyKeyword : SyntaxKind.AnyKeyword
@JsIntValue(134)
val AsyncKeyword : SyntaxKind.AsyncKeyword
@JsIntValue(135)
val AwaitKeyword : SyntaxKind.AwaitKeyword
@JsIntValue(136)
val BooleanKeyword : SyntaxKind.BooleanKeyword
@JsIntValue(137)
val ConstructorKeyword : SyntaxKind.ConstructorKeyword
@JsIntValue(138)
val DeclareKeyword : SyntaxKind.DeclareKeyword
@JsIntValue(139)
val GetKeyword : SyntaxKind.GetKeyword
@JsIntValue(140)
val InferKeyword : SyntaxKind.InferKeyword
@JsIntValue(141)
val IntrinsicKeyword : SyntaxKind.IntrinsicKeyword
@JsIntValue(142)
val IsKeyword : SyntaxKind.IsKeyword
@JsIntValue(143)
val KeyOfKeyword : SyntaxKind.KeyOfKeyword
@JsIntValue(144)
val ModuleKeyword : SyntaxKind.ModuleKeyword
@JsIntValue(145)
val NamespaceKeyword : SyntaxKind.NamespaceKeyword
@JsIntValue(146)
val NeverKeyword : SyntaxKind.NeverKeyword
@JsIntValue(147)
val OutKeyword : SyntaxKind.OutKeyword
@JsIntValue(148)
val ReadonlyKeyword : SyntaxKind.ReadonlyKeyword
@JsIntValue(149)
val RequireKeyword : SyntaxKind.RequireKeyword
@JsIntValue(150)
val NumberKeyword : SyntaxKind.NumberKeyword
@JsIntValue(151)
val ObjectKeyword : SyntaxKind.ObjectKeyword
@JsIntValue(152)
val SatisfiesKeyword : SyntaxKind.SatisfiesKeyword
@JsIntValue(153)
val SetKeyword : SyntaxKind.SetKeyword
@JsIntValue(154)
val StringKeyword : SyntaxKind.StringKeyword
@JsIntValue(155)
val SymbolKeyword : SyntaxKind.SymbolKeyword
@JsIntValue(156)
val TypeKeyword : SyntaxKind.TypeKeyword
@JsIntValue(157)
val UndefinedKeyword : SyntaxKind.UndefinedKeyword
@JsIntValue(158)
val UniqueKeyword : SyntaxKind.UniqueKeyword
@JsIntValue(159)
val UnknownKeyword : SyntaxKind.UnknownKeyword
@JsIntValue(160)
val UsingKeyword : SyntaxKind.UsingKeyword
@JsIntValue(161)
val FromKeyword : SyntaxKind.FromKeyword
@JsIntValue(162)
val GlobalKeyword : SyntaxKind.GlobalKeyword
@JsIntValue(163)
val BigIntKeyword : SyntaxKind.BigIntKeyword
@JsIntValue(164)
val OverrideKeyword : SyntaxKind.OverrideKeyword
@JsIntValue(165)
val OfKeyword : SyntaxKind.OfKeyword
@JsIntValue(166)
val QualifiedName : SyntaxKind.QualifiedName
@JsIntValue(167)
val ComputedPropertyName : SyntaxKind.ComputedPropertyName
@JsIntValue(168)
val TypeParameter : SyntaxKind.TypeParameter
@JsIntValue(169)
val Parameter : SyntaxKind.Parameter
@JsIntValue(170)
val Decorator : SyntaxKind.Decorator
@JsIntValue(171)
val PropertySignature : SyntaxKind.PropertySignature
@JsIntValue(172)
val PropertyDeclaration : SyntaxKind.PropertyDeclaration
@JsIntValue(173)
val MethodSignature : SyntaxKind.MethodSignature
@JsIntValue(174)
val MethodDeclaration : SyntaxKind.MethodDeclaration
@JsIntValue(175)
val ClassStaticBlockDeclaration : SyntaxKind.ClassStaticBlockDeclaration
@JsIntValue(176)
val Constructor : SyntaxKind.Constructor
@JsIntValue(177)
val GetAccessor : SyntaxKind.GetAccessor
@JsIntValue(178)
val SetAccessor : SyntaxKind.SetAccessor
@JsIntValue(179)
val CallSignature : SyntaxKind.CallSignature
@JsIntValue(180)
val ConstructSignature : SyntaxKind.ConstructSignature
@JsIntValue(181)
val IndexSignature : SyntaxKind.IndexSignature
@JsIntValue(182)
val TypePredicate : SyntaxKind.TypePredicate
@JsIntValue(183)
val TypeReference : SyntaxKind.TypeReference
@JsIntValue(184)
val FunctionType : SyntaxKind.FunctionType
@JsIntValue(185)
val ConstructorType : SyntaxKind.ConstructorType
@JsIntValue(186)
val TypeQuery : SyntaxKind.TypeQuery
@JsIntValue(187)
val TypeLiteral : SyntaxKind.TypeLiteral
@JsIntValue(188)
val ArrayType : SyntaxKind.ArrayType
@JsIntValue(189)
val TupleType : SyntaxKind.TupleType
@JsIntValue(190)
val OptionalType : SyntaxKind.OptionalType
@JsIntValue(191)
val RestType : SyntaxKind.RestType
@JsIntValue(192)
val UnionType : SyntaxKind.UnionType
@JsIntValue(193)
val IntersectionType : SyntaxKind.IntersectionType
@JsIntValue(194)
val ConditionalType : SyntaxKind.ConditionalType
@JsIntValue(195)
val InferType : SyntaxKind.InferType
@JsIntValue(196)
val ParenthesizedType : SyntaxKind.ParenthesizedType
@JsIntValue(197)
val ThisType : SyntaxKind.ThisType
@JsIntValue(198)
val TypeOperator : SyntaxKind.TypeOperator
@JsIntValue(199)
val IndexedAccessType : SyntaxKind.IndexedAccessType
@JsIntValue(200)
val MappedType : SyntaxKind.MappedType
@JsIntValue(201)
val LiteralType : SyntaxKind.LiteralType
@JsIntValue(202)
val NamedTupleMember : SyntaxKind.NamedTupleMember
@JsIntValue(203)
val TemplateLiteralType : SyntaxKind.TemplateLiteralType
@JsIntValue(204)
val TemplateLiteralTypeSpan : SyntaxKind.TemplateLiteralTypeSpan
@JsIntValue(205)
val ImportType : SyntaxKind.ImportType
@JsIntValue(206)
val ObjectBindingPattern : SyntaxKind.ObjectBindingPattern
@JsIntValue(207)
val ArrayBindingPattern : SyntaxKind.ArrayBindingPattern
@JsIntValue(208)
val BindingElement : SyntaxKind.BindingElement
@JsIntValue(209)
val ArrayLiteralExpression : SyntaxKind.ArrayLiteralExpression
@JsIntValue(210)
val ObjectLiteralExpression : SyntaxKind.ObjectLiteralExpression
@JsIntValue(211)
val PropertyAccessExpression : SyntaxKind.PropertyAccessExpression
@JsIntValue(212)
val ElementAccessExpression : SyntaxKind.ElementAccessExpression
@JsIntValue(213)
val CallExpression : SyntaxKind.CallExpression
@JsIntValue(214)
val NewExpression : SyntaxKind.NewExpression
@JsIntValue(215)
val TaggedTemplateExpression : SyntaxKind.TaggedTemplateExpression
@JsIntValue(216)
val TypeAssertionExpression : SyntaxKind.TypeAssertionExpression
@JsIntValue(217)
val ParenthesizedExpression : SyntaxKind.ParenthesizedExpression
@JsIntValue(218)
val FunctionExpression : SyntaxKind.FunctionExpression
@JsIntValue(219)
val ArrowFunction : SyntaxKind.ArrowFunction
@JsIntValue(220)
val DeleteExpression : SyntaxKind.DeleteExpression
@JsIntValue(221)
val TypeOfExpression : SyntaxKind.TypeOfExpression
@JsIntValue(222)
val VoidExpression : SyntaxKind.VoidExpression
@JsIntValue(223)
val AwaitExpression : SyntaxKind.AwaitExpression
@JsIntValue(224)
val PrefixUnaryExpression : SyntaxKind.PrefixUnaryExpression
@JsIntValue(225)
val PostfixUnaryExpression : SyntaxKind.PostfixUnaryExpression
@JsIntValue(226)
val BinaryExpression : SyntaxKind.BinaryExpression
@JsIntValue(227)
val ConditionalExpression : SyntaxKind.ConditionalExpression
@JsIntValue(228)
val TemplateExpression : SyntaxKind.TemplateExpression
@JsIntValue(229)
val YieldExpression : SyntaxKind.YieldExpression
@JsIntValue(230)
val SpreadElement : SyntaxKind.SpreadElement
@JsIntValue(231)
val ClassExpression : SyntaxKind.ClassExpression
@JsIntValue(232)
val OmittedExpression : SyntaxKind.OmittedExpression
@JsIntValue(233)
val ExpressionWithTypeArguments : SyntaxKind.ExpressionWithTypeArguments
@JsIntValue(234)
val AsExpression : SyntaxKind.AsExpression
@JsIntValue(235)
val NonNullExpression : SyntaxKind.NonNullExpression
@JsIntValue(236)
val MetaProperty : SyntaxKind.MetaProperty
@JsIntValue(237)
val SyntheticExpression : SyntaxKind.SyntheticExpression
@JsIntValue(238)
val SatisfiesExpression : SyntaxKind.SatisfiesExpression
@JsIntValue(239)
val TemplateSpan : SyntaxKind.TemplateSpan
@JsIntValue(240)
val SemicolonClassElement : SyntaxKind.SemicolonClassElement
@JsIntValue(241)
val Block : SyntaxKind.Block
@JsIntValue(242)
val EmptyStatement : SyntaxKind.EmptyStatement
@JsIntValue(243)
val VariableStatement : SyntaxKind.VariableStatement
@JsIntValue(244)
val ExpressionStatement : SyntaxKind.ExpressionStatement
@JsIntValue(245)
val IfStatement : SyntaxKind.IfStatement
@JsIntValue(246)
val DoStatement : SyntaxKind.DoStatement
@JsIntValue(247)
val WhileStatement : SyntaxKind.WhileStatement
@JsIntValue(248)
val ForStatement : SyntaxKind.ForStatement
@JsIntValue(249)
val ForInStatement : SyntaxKind.ForInStatement
@JsIntValue(250)
val ForOfStatement : SyntaxKind.ForOfStatement
@JsIntValue(251)
val ContinueStatement : SyntaxKind.ContinueStatement
@JsIntValue(252)
val BreakStatement : SyntaxKind.BreakStatement
@JsIntValue(253)
val ReturnStatement : SyntaxKind.ReturnStatement
@JsIntValue(254)
val WithStatement : SyntaxKind.WithStatement
@JsIntValue(255)
val SwitchStatement : SyntaxKind.SwitchStatement
@JsIntValue(256)
val LabeledStatement : SyntaxKind.LabeledStatement
@JsIntValue(257)
val ThrowStatement : SyntaxKind.ThrowStatement
@JsIntValue(258)
val TryStatement : SyntaxKind.TryStatement
@JsIntValue(259)
val DebuggerStatement : SyntaxKind.DebuggerStatement
@JsIntValue(260)
val VariableDeclaration : SyntaxKind.VariableDeclaration
@JsIntValue(261)
val VariableDeclarationList : SyntaxKind.VariableDeclarationList
@JsIntValue(262)
val FunctionDeclaration : SyntaxKind.FunctionDeclaration
@JsIntValue(263)
val ClassDeclaration : SyntaxKind.ClassDeclaration
@JsIntValue(264)
val InterfaceDeclaration : SyntaxKind.InterfaceDeclaration
@JsIntValue(265)
val TypeAliasDeclaration : SyntaxKind.TypeAliasDeclaration
@JsIntValue(266)
val EnumDeclaration : SyntaxKind.EnumDeclaration
@JsIntValue(267)
val ModuleDeclaration : SyntaxKind.ModuleDeclaration
@JsIntValue(268)
val ModuleBlock : SyntaxKind.ModuleBlock
@JsIntValue(269)
val CaseBlock : SyntaxKind.CaseBlock
@JsIntValue(270)
val NamespaceExportDeclaration : SyntaxKind.NamespaceExportDeclaration
@JsIntValue(271)
val ImportEqualsDeclaration : SyntaxKind.ImportEqualsDeclaration
@JsIntValue(272)
val ImportDeclaration : SyntaxKind.ImportDeclaration
@JsIntValue(273)
val ImportClause : SyntaxKind.ImportClause
@JsIntValue(274)
val NamespaceImport : SyntaxKind.NamespaceImport
@JsIntValue(275)
val NamedImports : SyntaxKind.NamedImports
@JsIntValue(276)
val ImportSpecifier : SyntaxKind.ImportSpecifier
@JsIntValue(277)
val ExportAssignment : SyntaxKind.ExportAssignment
@JsIntValue(278)
val ExportDeclaration : SyntaxKind.ExportDeclaration
@JsIntValue(279)
val NamedExports : SyntaxKind.NamedExports
@JsIntValue(280)
val NamespaceExport : SyntaxKind.NamespaceExport
@JsIntValue(281)
val ExportSpecifier : SyntaxKind.ExportSpecifier
@JsIntValue(282)
val MissingDeclaration : SyntaxKind.MissingDeclaration
@JsIntValue(283)
val ExternalModuleReference : SyntaxKind.ExternalModuleReference
@JsIntValue(284)
val JsxElement : SyntaxKind.JsxElement
@JsIntValue(285)
val JsxSelfClosingElement : SyntaxKind.JsxSelfClosingElement
@JsIntValue(286)
val JsxOpeningElement : SyntaxKind.JsxOpeningElement
@JsIntValue(287)
val JsxClosingElement : SyntaxKind.JsxClosingElement
@JsIntValue(288)
val JsxFragment : SyntaxKind.JsxFragment
@JsIntValue(289)
val JsxOpeningFragment : SyntaxKind.JsxOpeningFragment
@JsIntValue(290)
val JsxClosingFragment : SyntaxKind.JsxClosingFragment
@JsIntValue(291)
val JsxAttribute : SyntaxKind.JsxAttribute
@JsIntValue(292)
val JsxAttributes : SyntaxKind.JsxAttributes
@JsIntValue(293)
val JsxSpreadAttribute : SyntaxKind.JsxSpreadAttribute
@JsIntValue(294)
val JsxExpression : SyntaxKind.JsxExpression
@JsIntValue(295)
val JsxNamespacedName : SyntaxKind.JsxNamespacedName
@JsIntValue(296)
val CaseClause : SyntaxKind.CaseClause
@JsIntValue(297)
val DefaultClause : SyntaxKind.DefaultClause
@JsIntValue(298)
val HeritageClause : SyntaxKind.HeritageClause
@JsIntValue(299)
val CatchClause : SyntaxKind.CatchClause
@JsIntValue(300)
val ImportAttributes : SyntaxKind.ImportAttributes
@JsIntValue(301)
val ImportAttribute : SyntaxKind.ImportAttribute
@JsIntValue(303)
val PropertyAssignment : SyntaxKind.PropertyAssignment
@JsIntValue(304)
val ShorthandPropertyAssignment : SyntaxKind.ShorthandPropertyAssignment
@JsIntValue(305)
val SpreadAssignment : SyntaxKind.SpreadAssignment
@JsIntValue(306)
val EnumMember : SyntaxKind.EnumMember
@JsIntValue(312)
val SourceFile : SyntaxKind.SourceFile
@JsIntValue(313)
val Bundle : SyntaxKind.Bundle
@JsIntValue(316)
val JSDocTypeExpression : SyntaxKind.JSDocTypeExpression
@JsIntValue(317)
val JSDocNameReference : SyntaxKind.JSDocNameReference
@JsIntValue(318)
val JSDocMemberName : SyntaxKind.JSDocMemberName
@JsIntValue(319)
val JSDocAllType : SyntaxKind.JSDocAllType
@JsIntValue(320)
val JSDocUnknownType : SyntaxKind.JSDocUnknownType
@JsIntValue(321)
val JSDocNullableType : SyntaxKind.JSDocNullableType
@JsIntValue(322)
val JSDocNonNullableType : SyntaxKind.JSDocNonNullableType
@JsIntValue(323)
val JSDocOptionalType : SyntaxKind.JSDocOptionalType
@JsIntValue(324)
val JSDocFunctionType : SyntaxKind.JSDocFunctionType
@JsIntValue(325)
val JSDocVariadicType : SyntaxKind.JSDocVariadicType
@JsIntValue(326)
val JSDocNamepathType : SyntaxKind.JSDocNamepathType
@JsIntValue(327)
val JSDoc : SyntaxKind.JSDoc
@JsIntValue(328)
val JSDocText : SyntaxKind.JSDocText
@JsIntValue(329)
val JSDocTypeLiteral : SyntaxKind.JSDocTypeLiteral
@JsIntValue(330)
val JSDocSignature : SyntaxKind.JSDocSignature
@JsIntValue(331)
val JSDocLink : SyntaxKind.JSDocLink
@JsIntValue(332)
val JSDocLinkCode : SyntaxKind.JSDocLinkCode
@JsIntValue(333)
val JSDocLinkPlain : SyntaxKind.JSDocLinkPlain
@JsIntValue(334)
val JSDocTag : SyntaxKind.JSDocTag
@JsIntValue(335)
val JSDocAugmentsTag : SyntaxKind.JSDocAugmentsTag
@JsIntValue(336)
val JSDocImplementsTag : SyntaxKind.JSDocImplementsTag
@JsIntValue(337)
val JSDocAuthorTag : SyntaxKind.JSDocAuthorTag
@JsIntValue(338)
val JSDocDeprecatedTag : SyntaxKind.JSDocDeprecatedTag
@JsIntValue(339)
val JSDocClassTag : SyntaxKind.JSDocClassTag
@JsIntValue(340)
val JSDocPublicTag : SyntaxKind.JSDocPublicTag
@JsIntValue(341)
val JSDocPrivateTag : SyntaxKind.JSDocPrivateTag
@JsIntValue(342)
val JSDocProtectedTag : SyntaxKind.JSDocProtectedTag
@JsIntValue(343)
val JSDocReadonlyTag : SyntaxKind.JSDocReadonlyTag
@JsIntValue(344)
val JSDocOverrideTag : SyntaxKind.JSDocOverrideTag
@JsIntValue(345)
val JSDocCallbackTag : SyntaxKind.JSDocCallbackTag
@JsIntValue(346)
val JSDocOverloadTag : SyntaxKind.JSDocOverloadTag
@JsIntValue(347)
val JSDocEnumTag : SyntaxKind.JSDocEnumTag
@JsIntValue(348)
val JSDocParameterTag : SyntaxKind.JSDocParameterTag
@JsIntValue(349)
val JSDocReturnTag : SyntaxKind.JSDocReturnTag
@JsIntValue(350)
val JSDocThisTag : SyntaxKind.JSDocThisTag
@JsIntValue(351)
val JSDocTypeTag : SyntaxKind.JSDocTypeTag
@JsIntValue(352)
val JSDocTemplateTag : SyntaxKind.JSDocTemplateTag
@JsIntValue(353)
val JSDocTypedefTag : SyntaxKind.JSDocTypedefTag
@JsIntValue(354)
val JSDocSeeTag : SyntaxKind.JSDocSeeTag
@JsIntValue(355)
val JSDocPropertyTag : SyntaxKind.JSDocPropertyTag
@JsIntValue(356)
val JSDocThrowsTag : SyntaxKind.JSDocThrowsTag
@JsIntValue(357)
val JSDocSatisfiesTag : SyntaxKind.JSDocSatisfiesTag
@JsIntValue(358)
val SyntaxList : SyntaxKind.SyntaxList
@JsIntValue(359)
val NotEmittedStatement : SyntaxKind.NotEmittedStatement
@JsIntValue(360)
val PartiallyEmittedExpression : SyntaxKind.PartiallyEmittedExpression
@JsIntValue(361)
val CommaListExpression : SyntaxKind.CommaListExpression
@JsIntValue(362)
val SyntheticReferenceExpression : SyntaxKind.SyntheticReferenceExpression
@JsIntValue(363)
val Count : SyntaxKind.Count
@JsIntValue(64)
val FirstAssignment : SyntaxKind.FirstAssignment
@JsIntValue(79)
val LastAssignment : SyntaxKind.LastAssignment
@JsIntValue(65)
val FirstCompoundAssignment : SyntaxKind.FirstCompoundAssignment
@JsIntValue(79)
val LastCompoundAssignment : SyntaxKind.LastCompoundAssignment
@JsIntValue(83)
val FirstReservedWord : SyntaxKind.FirstReservedWord
@JsIntValue(118)
val LastReservedWord : SyntaxKind.LastReservedWord
@JsIntValue(83)
val FirstKeyword : SyntaxKind.FirstKeyword
@JsIntValue(165)
val LastKeyword : SyntaxKind.LastKeyword
@JsIntValue(119)
val FirstFutureReservedWord : SyntaxKind.FirstFutureReservedWord
@JsIntValue(127)
val LastFutureReservedWord : SyntaxKind.LastFutureReservedWord
@JsIntValue(182)
val FirstTypeNode : SyntaxKind.FirstTypeNode
@JsIntValue(205)
val LastTypeNode : SyntaxKind.LastTypeNode
@JsIntValue(19)
val FirstPunctuation : SyntaxKind.FirstPunctuation
@JsIntValue(79)
val LastPunctuation : SyntaxKind.LastPunctuation
@JsIntValue(0)
val FirstToken : SyntaxKind.FirstToken
@JsIntValue(165)
val LastToken : SyntaxKind.LastToken
@JsIntValue(2)
val FirstTriviaToken : SyntaxKind.FirstTriviaToken
@JsIntValue(7)
val LastTriviaToken : SyntaxKind.LastTriviaToken
@JsIntValue(9)
val FirstLiteralToken : SyntaxKind.FirstLiteralToken
@JsIntValue(15)
val LastLiteralToken : SyntaxKind.LastLiteralToken
@JsIntValue(15)
val FirstTemplateToken : SyntaxKind.FirstTemplateToken
@JsIntValue(18)
val LastTemplateToken : SyntaxKind.LastTemplateToken
@JsIntValue(30)
val FirstBinaryOperator : SyntaxKind.FirstBinaryOperator
@JsIntValue(79)
val LastBinaryOperator : SyntaxKind.LastBinaryOperator
@JsIntValue(243)
val FirstStatement : SyntaxKind.FirstStatement
@JsIntValue(259)
val LastStatement : SyntaxKind.LastStatement
@JsIntValue(166)
val FirstNode : SyntaxKind.FirstNode
@JsIntValue(316)
val FirstJSDocNode : SyntaxKind.FirstJSDocNode
@JsIntValue(357)
val LastJSDocNode : SyntaxKind.LastJSDocNode
@JsIntValue(334)
val FirstJSDocTagNode : SyntaxKind.FirstJSDocTagNode
@JsIntValue(357)
val LastJSDocTagNode : SyntaxKind.LastJSDocTagNode
            }
            
            sealed interface Unknown : SyntaxKind, Union.SyntaxKind_Unknown
sealed interface EndOfFileToken : SyntaxKind, Union.SyntaxKind_EndOfFileToken
sealed interface SingleLineCommentTrivia : SyntaxKind, Union.SyntaxKind_SingleLineCommentTrivia
sealed interface MultiLineCommentTrivia : SyntaxKind, Union.SyntaxKind_MultiLineCommentTrivia
sealed interface NewLineTrivia : SyntaxKind, Union.SyntaxKind_NewLineTrivia
sealed interface WhitespaceTrivia : SyntaxKind, Union.SyntaxKind_WhitespaceTrivia
sealed interface ShebangTrivia : SyntaxKind, Union.SyntaxKind_ShebangTrivia
sealed interface ConflictMarkerTrivia : SyntaxKind, Union.SyntaxKind_ConflictMarkerTrivia
sealed interface NonTextFileMarkerTrivia : SyntaxKind
sealed interface NumericLiteral : SyntaxKind, Union.SyntaxKind_NumericLiteral
sealed interface BigIntLiteral : SyntaxKind, Union.SyntaxKind_BigIntLiteral
sealed interface StringLiteral : SyntaxKind, Union.SyntaxKind_StringLiteral
sealed interface JsxText : SyntaxKind, Union.SyntaxKind_JsxText
sealed interface JsxTextAllWhiteSpaces : SyntaxKind, Union.SyntaxKind_JsxTextAllWhiteSpaces
sealed interface RegularExpressionLiteral : SyntaxKind, Union.SyntaxKind_RegularExpressionLiteral
sealed interface NoSubstitutionTemplateLiteral : SyntaxKind, Union.SyntaxKind_NoSubstitutionTemplateLiteral
sealed interface TemplateHead : SyntaxKind, Union.SyntaxKind_TemplateHead
sealed interface TemplateMiddle : SyntaxKind, Union.SyntaxKind_TemplateMiddle
sealed interface TemplateTail : SyntaxKind, Union.SyntaxKind_TemplateTail
sealed interface OpenBraceToken : SyntaxKind, Union.SyntaxKind_OpenBraceToken
sealed interface CloseBraceToken : SyntaxKind, Union.SyntaxKind_CloseBraceToken
sealed interface OpenParenToken : SyntaxKind, Union.SyntaxKind_OpenParenToken
sealed interface CloseParenToken : SyntaxKind, Union.SyntaxKind_CloseParenToken
sealed interface OpenBracketToken : SyntaxKind, Union.SyntaxKind_OpenBracketToken
sealed interface CloseBracketToken : SyntaxKind, Union.SyntaxKind_CloseBracketToken
sealed interface DotToken : SyntaxKind, Union.SyntaxKind_DotToken
sealed interface DotDotDotToken : SyntaxKind, Union.SyntaxKind_DotDotDotToken
sealed interface SemicolonToken : SyntaxKind, Union.SyntaxKind_SemicolonToken
sealed interface CommaToken : SyntaxKind, Union.SyntaxKind_CommaToken
sealed interface QuestionDotToken : SyntaxKind, Union.SyntaxKind_QuestionDotToken
sealed interface LessThanToken : SyntaxKind, Union.SyntaxKind_LessThanToken
sealed interface LessThanSlashToken : SyntaxKind, Union.SyntaxKind_LessThanSlashToken
sealed interface GreaterThanToken : SyntaxKind, Union.SyntaxKind_GreaterThanToken
sealed interface LessThanEqualsToken : SyntaxKind, Union.SyntaxKind_LessThanEqualsToken
sealed interface GreaterThanEqualsToken : SyntaxKind, Union.SyntaxKind_GreaterThanEqualsToken
sealed interface EqualsEqualsToken : SyntaxKind, Union.SyntaxKind_EqualsEqualsToken
sealed interface ExclamationEqualsToken : SyntaxKind, Union.SyntaxKind_ExclamationEqualsToken
sealed interface EqualsEqualsEqualsToken : SyntaxKind, Union.SyntaxKind_EqualsEqualsEqualsToken
sealed interface ExclamationEqualsEqualsToken : SyntaxKind, Union.SyntaxKind_ExclamationEqualsEqualsToken
sealed interface EqualsGreaterThanToken : SyntaxKind, Union.SyntaxKind_EqualsGreaterThanToken
sealed interface PlusToken : SyntaxKind, Union.SyntaxKind_PlusToken
sealed interface MinusToken : SyntaxKind, Union.SyntaxKind_MinusToken
sealed interface AsteriskToken : SyntaxKind, Union.SyntaxKind_AsteriskToken
sealed interface AsteriskAsteriskToken : SyntaxKind, Union.SyntaxKind_AsteriskAsteriskToken
sealed interface SlashToken : SyntaxKind, Union.SyntaxKind_SlashToken
sealed interface PercentToken : SyntaxKind, Union.SyntaxKind_PercentToken
sealed interface PlusPlusToken : SyntaxKind, Union.SyntaxKind_PlusPlusToken
sealed interface MinusMinusToken : SyntaxKind, Union.SyntaxKind_MinusMinusToken
sealed interface LessThanLessThanToken : SyntaxKind, Union.SyntaxKind_LessThanLessThanToken
sealed interface GreaterThanGreaterThanToken : SyntaxKind, Union.SyntaxKind_GreaterThanGreaterThanToken
sealed interface GreaterThanGreaterThanGreaterThanToken : SyntaxKind, Union.SyntaxKind_GreaterThanGreaterThanGreaterThanToken
sealed interface AmpersandToken : SyntaxKind, Union.SyntaxKind_AmpersandToken
sealed interface BarToken : SyntaxKind, Union.SyntaxKind_BarToken
sealed interface CaretToken : SyntaxKind, Union.SyntaxKind_CaretToken
sealed interface ExclamationToken : SyntaxKind, Union.SyntaxKind_ExclamationToken
sealed interface TildeToken : SyntaxKind, Union.SyntaxKind_TildeToken
sealed interface AmpersandAmpersandToken : SyntaxKind, Union.SyntaxKind_AmpersandAmpersandToken
sealed interface BarBarToken : SyntaxKind, Union.SyntaxKind_BarBarToken
sealed interface QuestionToken : SyntaxKind, Union.SyntaxKind_QuestionToken
sealed interface ColonToken : SyntaxKind, Union.SyntaxKind_ColonToken
sealed interface AtToken : SyntaxKind, Union.SyntaxKind_AtToken
sealed interface QuestionQuestionToken : SyntaxKind, Union.SyntaxKind_QuestionQuestionToken
sealed interface BacktickToken : SyntaxKind, Union.SyntaxKind_BacktickToken
sealed interface HashToken : SyntaxKind, Union.SyntaxKind_HashToken
sealed interface EqualsToken : SyntaxKind, Union.SyntaxKind_EqualsToken
sealed interface PlusEqualsToken : SyntaxKind, Union.SyntaxKind_PlusEqualsToken
sealed interface MinusEqualsToken : SyntaxKind, Union.SyntaxKind_MinusEqualsToken
sealed interface AsteriskEqualsToken : SyntaxKind, Union.SyntaxKind_AsteriskEqualsToken
sealed interface AsteriskAsteriskEqualsToken : SyntaxKind, Union.SyntaxKind_AsteriskAsteriskEqualsToken
sealed interface SlashEqualsToken : SyntaxKind, Union.SyntaxKind_SlashEqualsToken
sealed interface PercentEqualsToken : SyntaxKind, Union.SyntaxKind_PercentEqualsToken
sealed interface LessThanLessThanEqualsToken : SyntaxKind, Union.SyntaxKind_LessThanLessThanEqualsToken
sealed interface GreaterThanGreaterThanEqualsToken : SyntaxKind, Union.SyntaxKind_GreaterThanGreaterThanEqualsToken
sealed interface GreaterThanGreaterThanGreaterThanEqualsToken : SyntaxKind, Union.SyntaxKind_GreaterThanGreaterThanGreaterThanEqualsToken
sealed interface AmpersandEqualsToken : SyntaxKind, Union.SyntaxKind_AmpersandEqualsToken
sealed interface BarEqualsToken : SyntaxKind, Union.SyntaxKind_BarEqualsToken
sealed interface BarBarEqualsToken : SyntaxKind, Union.SyntaxKind_BarBarEqualsToken
sealed interface AmpersandAmpersandEqualsToken : SyntaxKind, Union.SyntaxKind_AmpersandAmpersandEqualsToken
sealed interface QuestionQuestionEqualsToken : SyntaxKind, Union.SyntaxKind_QuestionQuestionEqualsToken
sealed interface CaretEqualsToken : SyntaxKind, Union.SyntaxKind_CaretEqualsToken
sealed interface Identifier : SyntaxKind, Union.SyntaxKind_Identifier
sealed interface PrivateIdentifier : SyntaxKind
sealed interface BreakKeyword : SyntaxKind, Union.SyntaxKind_BreakKeyword
sealed interface CaseKeyword : SyntaxKind, Union.SyntaxKind_CaseKeyword
sealed interface CatchKeyword : SyntaxKind, Union.SyntaxKind_CatchKeyword
sealed interface ClassKeyword : SyntaxKind, Union.SyntaxKind_ClassKeyword
sealed interface ConstKeyword : SyntaxKind, Union.SyntaxKind_ConstKeyword
sealed interface ContinueKeyword : SyntaxKind, Union.SyntaxKind_ContinueKeyword
sealed interface DebuggerKeyword : SyntaxKind, Union.SyntaxKind_DebuggerKeyword
sealed interface DefaultKeyword : SyntaxKind, Union.SyntaxKind_DefaultKeyword
sealed interface DeleteKeyword : SyntaxKind, Union.SyntaxKind_DeleteKeyword
sealed interface DoKeyword : SyntaxKind, Union.SyntaxKind_DoKeyword
sealed interface ElseKeyword : SyntaxKind, Union.SyntaxKind_ElseKeyword
sealed interface EnumKeyword : SyntaxKind, Union.SyntaxKind_EnumKeyword
sealed interface ExportKeyword : SyntaxKind, Union.SyntaxKind_ExportKeyword
sealed interface ExtendsKeyword : SyntaxKind, Union.SyntaxKind_ExtendsKeyword
sealed interface FalseKeyword : SyntaxKind, Union.SyntaxKind_FalseKeyword
sealed interface FinallyKeyword : SyntaxKind, Union.SyntaxKind_FinallyKeyword
sealed interface ForKeyword : SyntaxKind, Union.SyntaxKind_ForKeyword
sealed interface FunctionKeyword : SyntaxKind, Union.SyntaxKind_FunctionKeyword
sealed interface IfKeyword : SyntaxKind, Union.SyntaxKind_IfKeyword
sealed interface ImportKeyword : SyntaxKind, Union.SyntaxKind_ImportKeyword
sealed interface InKeyword : SyntaxKind, Union.SyntaxKind_InKeyword
sealed interface InstanceOfKeyword : SyntaxKind, Union.SyntaxKind_InstanceOfKeyword
sealed interface NewKeyword : SyntaxKind, Union.SyntaxKind_NewKeyword
sealed interface NullKeyword : SyntaxKind, Union.SyntaxKind_NullKeyword
sealed interface ReturnKeyword : SyntaxKind, Union.SyntaxKind_ReturnKeyword
sealed interface SuperKeyword : SyntaxKind, Union.SyntaxKind_SuperKeyword
sealed interface SwitchKeyword : SyntaxKind, Union.SyntaxKind_SwitchKeyword
sealed interface ThisKeyword : SyntaxKind, Union.SyntaxKind_ThisKeyword
sealed interface ThrowKeyword : SyntaxKind, Union.SyntaxKind_ThrowKeyword
sealed interface TrueKeyword : SyntaxKind, Union.SyntaxKind_TrueKeyword
sealed interface TryKeyword : SyntaxKind, Union.SyntaxKind_TryKeyword
sealed interface TypeOfKeyword : SyntaxKind, Union.SyntaxKind_TypeOfKeyword
sealed interface VarKeyword : SyntaxKind, Union.SyntaxKind_VarKeyword
sealed interface VoidKeyword : SyntaxKind, Union.SyntaxKind_VoidKeyword
sealed interface WhileKeyword : SyntaxKind, Union.SyntaxKind_WhileKeyword
sealed interface WithKeyword : SyntaxKind, Union.SyntaxKind_WithKeyword
sealed interface ImplementsKeyword : SyntaxKind, Union.SyntaxKind_ImplementsKeyword
sealed interface InterfaceKeyword : SyntaxKind, Union.SyntaxKind_InterfaceKeyword
sealed interface LetKeyword : SyntaxKind, Union.SyntaxKind_LetKeyword
sealed interface PackageKeyword : SyntaxKind, Union.SyntaxKind_PackageKeyword
sealed interface PrivateKeyword : SyntaxKind, Union.SyntaxKind_PrivateKeyword
sealed interface ProtectedKeyword : SyntaxKind, Union.SyntaxKind_ProtectedKeyword
sealed interface PublicKeyword : SyntaxKind, Union.SyntaxKind_PublicKeyword
sealed interface StaticKeyword : SyntaxKind, Union.SyntaxKind_StaticKeyword
sealed interface YieldKeyword : SyntaxKind, Union.SyntaxKind_YieldKeyword
sealed interface AbstractKeyword : SyntaxKind, Union.SyntaxKind_AbstractKeyword
sealed interface AccessorKeyword : SyntaxKind, Union.SyntaxKind_AccessorKeyword
sealed interface AsKeyword : SyntaxKind, Union.SyntaxKind_AsKeyword
sealed interface AssertsKeyword : SyntaxKind, Union.SyntaxKind_AssertsKeyword
sealed interface AssertKeyword : SyntaxKind, Union.SyntaxKind_AssertKeyword
sealed interface AnyKeyword : SyntaxKind, Union.SyntaxKind_AnyKeyword
sealed interface AsyncKeyword : SyntaxKind, Union.SyntaxKind_AsyncKeyword
sealed interface AwaitKeyword : SyntaxKind, Union.SyntaxKind_AwaitKeyword
sealed interface BooleanKeyword : SyntaxKind, Union.SyntaxKind_BooleanKeyword
sealed interface ConstructorKeyword : SyntaxKind, Union.SyntaxKind_ConstructorKeyword
sealed interface DeclareKeyword : SyntaxKind, Union.SyntaxKind_DeclareKeyword
sealed interface GetKeyword : SyntaxKind, Union.SyntaxKind_GetKeyword
sealed interface InferKeyword : SyntaxKind, Union.SyntaxKind_InferKeyword
sealed interface IntrinsicKeyword : SyntaxKind, Union.SyntaxKind_IntrinsicKeyword
sealed interface IsKeyword : SyntaxKind, Union.SyntaxKind_IsKeyword
sealed interface KeyOfKeyword : SyntaxKind, Union.SyntaxKind_KeyOfKeyword
sealed interface ModuleKeyword : SyntaxKind, Union.SyntaxKind_ModuleKeyword
sealed interface NamespaceKeyword : SyntaxKind, Union.SyntaxKind_NamespaceKeyword
sealed interface NeverKeyword : SyntaxKind, Union.SyntaxKind_NeverKeyword
sealed interface OutKeyword : SyntaxKind, Union.SyntaxKind_OutKeyword
sealed interface ReadonlyKeyword : SyntaxKind, Union.SyntaxKind_ReadonlyKeyword
sealed interface RequireKeyword : SyntaxKind, Union.SyntaxKind_RequireKeyword
sealed interface NumberKeyword : SyntaxKind, Union.SyntaxKind_NumberKeyword
sealed interface ObjectKeyword : SyntaxKind, Union.SyntaxKind_ObjectKeyword
sealed interface SatisfiesKeyword : SyntaxKind, Union.SyntaxKind_SatisfiesKeyword
sealed interface SetKeyword : SyntaxKind, Union.SyntaxKind_SetKeyword
sealed interface StringKeyword : SyntaxKind, Union.SyntaxKind_StringKeyword
sealed interface SymbolKeyword : SyntaxKind, Union.SyntaxKind_SymbolKeyword
sealed interface TypeKeyword : SyntaxKind, Union.SyntaxKind_TypeKeyword
sealed interface UndefinedKeyword : SyntaxKind, Union.SyntaxKind_UndefinedKeyword
sealed interface UniqueKeyword : SyntaxKind, Union.SyntaxKind_UniqueKeyword
sealed interface UnknownKeyword : SyntaxKind, Union.SyntaxKind_UnknownKeyword
sealed interface UsingKeyword : SyntaxKind, Union.SyntaxKind_UsingKeyword
sealed interface FromKeyword : SyntaxKind, Union.SyntaxKind_FromKeyword
sealed interface GlobalKeyword : SyntaxKind, Union.SyntaxKind_GlobalKeyword
sealed interface BigIntKeyword : SyntaxKind, Union.SyntaxKind_BigIntKeyword
sealed interface OverrideKeyword : SyntaxKind, Union.SyntaxKind_OverrideKeyword
sealed interface OfKeyword : SyntaxKind, Union.SyntaxKind_OfKeyword
sealed interface QualifiedName : SyntaxKind
sealed interface ComputedPropertyName : SyntaxKind
sealed interface TypeParameter : SyntaxKind
sealed interface Parameter : SyntaxKind
sealed interface Decorator : SyntaxKind
sealed interface PropertySignature : SyntaxKind
sealed interface PropertyDeclaration : SyntaxKind
sealed interface MethodSignature : SyntaxKind
sealed interface MethodDeclaration : SyntaxKind
sealed interface ClassStaticBlockDeclaration : SyntaxKind
sealed interface Constructor : SyntaxKind
sealed interface GetAccessor : SyntaxKind
sealed interface SetAccessor : SyntaxKind
sealed interface CallSignature : SyntaxKind
sealed interface ConstructSignature : SyntaxKind
sealed interface IndexSignature : SyntaxKind
sealed interface TypePredicate : SyntaxKind
sealed interface TypeReference : SyntaxKind
sealed interface FunctionType : SyntaxKind, Union.SyntaxKind_FunctionType
sealed interface ConstructorType : SyntaxKind, Union.SyntaxKind_ConstructorType
sealed interface TypeQuery : SyntaxKind
sealed interface TypeLiteral : SyntaxKind
sealed interface ArrayType : SyntaxKind
sealed interface TupleType : SyntaxKind
sealed interface OptionalType : SyntaxKind
sealed interface RestType : SyntaxKind
sealed interface UnionType : SyntaxKind
sealed interface IntersectionType : SyntaxKind
sealed interface ConditionalType : SyntaxKind
sealed interface InferType : SyntaxKind
sealed interface ParenthesizedType : SyntaxKind
sealed interface ThisType : SyntaxKind
sealed interface TypeOperator : SyntaxKind
sealed interface IndexedAccessType : SyntaxKind
sealed interface MappedType : SyntaxKind
sealed interface LiteralType : SyntaxKind
sealed interface NamedTupleMember : SyntaxKind
sealed interface TemplateLiteralType : SyntaxKind
sealed interface TemplateLiteralTypeSpan : SyntaxKind
sealed interface ImportType : SyntaxKind
sealed interface ObjectBindingPattern : SyntaxKind
sealed interface ArrayBindingPattern : SyntaxKind
sealed interface BindingElement : SyntaxKind
sealed interface ArrayLiteralExpression : SyntaxKind
sealed interface ObjectLiteralExpression : SyntaxKind
sealed interface PropertyAccessExpression : SyntaxKind
sealed interface ElementAccessExpression : SyntaxKind
sealed interface CallExpression : SyntaxKind
sealed interface NewExpression : SyntaxKind
sealed interface TaggedTemplateExpression : SyntaxKind
sealed interface TypeAssertionExpression : SyntaxKind
sealed interface ParenthesizedExpression : SyntaxKind
sealed interface FunctionExpression : SyntaxKind
sealed interface ArrowFunction : SyntaxKind
sealed interface DeleteExpression : SyntaxKind
sealed interface TypeOfExpression : SyntaxKind
sealed interface VoidExpression : SyntaxKind
sealed interface AwaitExpression : SyntaxKind
sealed interface PrefixUnaryExpression : SyntaxKind
sealed interface PostfixUnaryExpression : SyntaxKind
sealed interface BinaryExpression : SyntaxKind
sealed interface ConditionalExpression : SyntaxKind
sealed interface TemplateExpression : SyntaxKind
sealed interface YieldExpression : SyntaxKind
sealed interface SpreadElement : SyntaxKind
sealed interface ClassExpression : SyntaxKind, Union.SyntaxKind_ClassExpression
sealed interface OmittedExpression : SyntaxKind
sealed interface ExpressionWithTypeArguments : SyntaxKind
sealed interface AsExpression : SyntaxKind
sealed interface NonNullExpression : SyntaxKind
sealed interface MetaProperty : SyntaxKind
sealed interface SyntheticExpression : SyntaxKind
sealed interface SatisfiesExpression : SyntaxKind
sealed interface TemplateSpan : SyntaxKind
sealed interface SemicolonClassElement : SyntaxKind
sealed interface Block : SyntaxKind
sealed interface EmptyStatement : SyntaxKind
sealed interface VariableStatement : SyntaxKind
sealed interface ExpressionStatement : SyntaxKind
sealed interface IfStatement : SyntaxKind
sealed interface DoStatement : SyntaxKind
sealed interface WhileStatement : SyntaxKind
sealed interface ForStatement : SyntaxKind
sealed interface ForInStatement : SyntaxKind
sealed interface ForOfStatement : SyntaxKind
sealed interface ContinueStatement : SyntaxKind
sealed interface BreakStatement : SyntaxKind
sealed interface ReturnStatement : SyntaxKind
sealed interface WithStatement : SyntaxKind
sealed interface SwitchStatement : SyntaxKind
sealed interface LabeledStatement : SyntaxKind
sealed interface ThrowStatement : SyntaxKind
sealed interface TryStatement : SyntaxKind
sealed interface DebuggerStatement : SyntaxKind
sealed interface VariableDeclaration : SyntaxKind
sealed interface VariableDeclarationList : SyntaxKind
sealed interface FunctionDeclaration : SyntaxKind
sealed interface ClassDeclaration : SyntaxKind, Union.SyntaxKind_ClassDeclaration
sealed interface InterfaceDeclaration : SyntaxKind
sealed interface TypeAliasDeclaration : SyntaxKind
sealed interface EnumDeclaration : SyntaxKind
sealed interface ModuleDeclaration : SyntaxKind
sealed interface ModuleBlock : SyntaxKind
sealed interface CaseBlock : SyntaxKind
sealed interface NamespaceExportDeclaration : SyntaxKind
sealed interface ImportEqualsDeclaration : SyntaxKind
sealed interface ImportDeclaration : SyntaxKind
sealed interface ImportClause : SyntaxKind
sealed interface NamespaceImport : SyntaxKind
sealed interface NamedImports : SyntaxKind
sealed interface ImportSpecifier : SyntaxKind
sealed interface ExportAssignment : SyntaxKind
sealed interface ExportDeclaration : SyntaxKind
sealed interface NamedExports : SyntaxKind
sealed interface NamespaceExport : SyntaxKind
sealed interface ExportSpecifier : SyntaxKind
sealed interface MissingDeclaration : SyntaxKind
sealed interface ExternalModuleReference : SyntaxKind
sealed interface JsxElement : SyntaxKind
sealed interface JsxSelfClosingElement : SyntaxKind
sealed interface JsxOpeningElement : SyntaxKind
sealed interface JsxClosingElement : SyntaxKind
sealed interface JsxFragment : SyntaxKind
sealed interface JsxOpeningFragment : SyntaxKind
sealed interface JsxClosingFragment : SyntaxKind
sealed interface JsxAttribute : SyntaxKind
sealed interface JsxAttributes : SyntaxKind
sealed interface JsxSpreadAttribute : SyntaxKind
sealed interface JsxExpression : SyntaxKind
sealed interface JsxNamespacedName : SyntaxKind
sealed interface CaseClause : SyntaxKind
sealed interface DefaultClause : SyntaxKind
sealed interface HeritageClause : SyntaxKind
sealed interface CatchClause : SyntaxKind
sealed interface ImportAttributes : SyntaxKind
sealed interface ImportAttribute : SyntaxKind
sealed interface PropertyAssignment : SyntaxKind
sealed interface ShorthandPropertyAssignment : SyntaxKind
sealed interface SpreadAssignment : SyntaxKind
sealed interface EnumMember : SyntaxKind
sealed interface SourceFile : SyntaxKind
sealed interface Bundle : SyntaxKind
sealed interface JSDocTypeExpression : SyntaxKind
sealed interface JSDocNameReference : SyntaxKind
sealed interface JSDocMemberName : SyntaxKind
sealed interface JSDocAllType : SyntaxKind
sealed interface JSDocUnknownType : SyntaxKind
sealed interface JSDocNullableType : SyntaxKind
sealed interface JSDocNonNullableType : SyntaxKind
sealed interface JSDocOptionalType : SyntaxKind
sealed interface JSDocFunctionType : SyntaxKind
sealed interface JSDocVariadicType : SyntaxKind
sealed interface JSDocNamepathType : SyntaxKind
sealed interface JSDoc : SyntaxKind
sealed interface JSDocText : SyntaxKind
sealed interface JSDocTypeLiteral : SyntaxKind
sealed interface JSDocSignature : SyntaxKind
sealed interface JSDocLink : SyntaxKind
sealed interface JSDocLinkCode : SyntaxKind
sealed interface JSDocLinkPlain : SyntaxKind
sealed interface JSDocTag : SyntaxKind
sealed interface JSDocAugmentsTag : SyntaxKind
sealed interface JSDocImplementsTag : SyntaxKind
sealed interface JSDocAuthorTag : SyntaxKind
sealed interface JSDocDeprecatedTag : SyntaxKind
sealed interface JSDocClassTag : SyntaxKind
sealed interface JSDocPublicTag : SyntaxKind
sealed interface JSDocPrivateTag : SyntaxKind
sealed interface JSDocProtectedTag : SyntaxKind
sealed interface JSDocReadonlyTag : SyntaxKind
sealed interface JSDocOverrideTag : SyntaxKind
sealed interface JSDocCallbackTag : SyntaxKind
sealed interface JSDocOverloadTag : SyntaxKind
sealed interface JSDocEnumTag : SyntaxKind
sealed interface JSDocParameterTag : SyntaxKind
sealed interface JSDocReturnTag : SyntaxKind
sealed interface JSDocThisTag : SyntaxKind
sealed interface JSDocTypeTag : SyntaxKind
sealed interface JSDocTemplateTag : SyntaxKind
sealed interface JSDocTypedefTag : SyntaxKind
sealed interface JSDocSeeTag : SyntaxKind
sealed interface JSDocPropertyTag : SyntaxKind
sealed interface JSDocThrowsTag : SyntaxKind
sealed interface JSDocSatisfiesTag : SyntaxKind
sealed interface SyntaxList : SyntaxKind
sealed interface NotEmittedStatement : SyntaxKind
sealed interface PartiallyEmittedExpression : SyntaxKind
sealed interface CommaListExpression : SyntaxKind
sealed interface SyntheticReferenceExpression : SyntaxKind
sealed interface Count : SyntaxKind
sealed interface FirstAssignment : SyntaxKind
sealed interface LastAssignment : SyntaxKind
sealed interface FirstCompoundAssignment : SyntaxKind
sealed interface LastCompoundAssignment : SyntaxKind
sealed interface FirstReservedWord : SyntaxKind
sealed interface LastReservedWord : SyntaxKind
sealed interface FirstKeyword : SyntaxKind
sealed interface LastKeyword : SyntaxKind
sealed interface FirstFutureReservedWord : SyntaxKind
sealed interface LastFutureReservedWord : SyntaxKind
sealed interface FirstTypeNode : SyntaxKind
sealed interface LastTypeNode : SyntaxKind
sealed interface FirstPunctuation : SyntaxKind
sealed interface LastPunctuation : SyntaxKind
sealed interface FirstToken : SyntaxKind
sealed interface LastToken : SyntaxKind
sealed interface FirstTriviaToken : SyntaxKind
sealed interface LastTriviaToken : SyntaxKind
sealed interface FirstLiteralToken : SyntaxKind
sealed interface LastLiteralToken : SyntaxKind
sealed interface FirstTemplateToken : SyntaxKind
sealed interface LastTemplateToken : SyntaxKind
sealed interface FirstBinaryOperator : SyntaxKind
sealed interface LastBinaryOperator : SyntaxKind
sealed interface FirstStatement : SyntaxKind
sealed interface LastStatement : SyntaxKind
sealed interface FirstNode : SyntaxKind
sealed interface FirstJSDocNode : SyntaxKind
sealed interface LastJSDocNode : SyntaxKind
sealed interface FirstJSDocTagNode : SyntaxKind
sealed interface LastJSDocTagNode : SyntaxKind
        }
