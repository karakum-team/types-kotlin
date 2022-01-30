// Automatically generated - do not modify!

package typescript

sealed external interface Union {

    sealed interface AbstractKeyword : Modifier

    sealed interface AccessibilityModifier : ParameterPropertyModifier, ClassMemberModifier

    sealed interface AccessorDeclaration : HasJSDoc, SignatureDeclaration, ObjectLiteralElementLike

    sealed interface AdditiveOperator : AdditiveOperatorOrHigher

    sealed interface AdditiveOperatorOrHigher : ShiftOperatorOrHigher

    sealed interface ArrayBindingOrAssignmentElement : BindingOrAssignmentElement

    sealed interface ArrayBindingOrAssignmentPattern : BindingOrAssignmentPattern

    sealed interface ArrayBindingPattern : BindingPattern, ArrayBindingOrAssignmentPattern

    sealed interface ArrayBindingPattern_parent

    sealed interface ArrayDestructuringAssignment : DestructuringAssignment

    sealed interface ArrayLiteralExpression : ArrayBindingOrAssignmentPattern, AssignmentPattern, JsonObjectExpression, DestructuringPattern, SpreadElement_parent

    sealed interface ArrayTypeNode : TypeReference_node

    sealed interface ArrowFunction : HasJSDoc, SignatureDeclaration, FunctionLikeDeclaration, FlowStart_node

    sealed interface AsExpression : AssertionExpression

    sealed interface AssertClause_parent

    sealed interface AssertionExpression : HasType

    sealed interface AssertsIdentifierTypePredicate : TypePredicate

    sealed interface AssertsThisTypePredicate : TypePredicate

    sealed interface AssignmentOperator : AssignmentOperatorOrHigher

    sealed interface AssignmentOperatorOrHigher : BinaryOperator

    sealed interface AsyncKeyword : Modifier

    sealed interface BigIntLiteral : LiteralToken

    sealed interface BinaryExpression : FlowArrayMutation_node

    sealed interface BindingElement : HasExpressionInitializer, VariableLikeDeclaration, ArrayBindingElement, ObjectBindingOrAssignmentElement, ObjectBindingPattern_parent, ArrayBindingPattern_parent,
        FlowAssignment_node

    sealed interface BindingOrAssignmentPattern : BindingOrAssignmentElementTarget

    sealed interface BindingPattern : DeclarationName, BindingName, DestructuringPattern

    sealed interface BitwiseOperator : BitwiseOperatorOrHigher

    sealed interface BitwiseOperatorOrHigher : LogicalOperatorOrHigher

    sealed interface Block : HasJSDoc, BlockLike, FunctionLikeDeclarationBase_body

    sealed interface BooleanLiteral : JsonObjectExpression, LiteralTypeNode_literal

    sealed interface BreakStatement : HasJSDoc, BreakOrContinueStatement

    sealed interface CallChain : OptionalChain

    sealed interface CallExpression : HasTypeArguments, CallLikeExpression, SpreadElement_parent, FlowArrayMutation_node

    sealed interface CallSignatureDeclaration : HasJSDoc, SignatureDeclaration

    sealed interface CaseClause : CaseOrDefaultClause

    sealed interface CaseOrDefaultClause : BlockLike

    sealed interface CatchClause : VariableDeclaration_parent

    sealed interface ClassDeclaration : ClassLikeDeclaration, ClassStaticBlockDeclaration_parent

    sealed interface ClassExpression : ClassLikeDeclaration, ClassStaticBlockDeclaration_parent

    sealed interface ClassLikeDeclaration : HasJSDoc, ObjectTypeDeclaration, DeclarationWithTypeParameterChildren, MethodDeclaration_parent, GetAccessorDeclaration_parent,
        SetAccessorDeclaration_parent, HeritageClause_parent

    sealed interface ClassLikeDeclarationBase_kind

    sealed interface ClassStaticBlockDeclaration : HasJSDoc

    sealed interface ClassStaticBlockDeclaration_parent

    sealed interface CompletionEntryDataResolved : CompletionEntryData

    sealed interface CompletionEntryDataUnresolved : CompletionEntryData

    sealed interface CompoundAssignmentOperator : AssignmentOperator

    sealed interface ComputedPropertyName : PropertyName, DeclarationName

    sealed interface ConstKeyword : Modifier

    sealed interface ConstructSignatureDeclaration : HasJSDoc, SignatureDeclaration

    sealed interface ConstructorDeclaration : HasJSDoc, SignatureDeclaration, FunctionLikeDeclaration

    sealed interface ConstructorTypeNode : HasJSDoc, SignatureDeclaration, FunctionOrConstructorTypeNode

    sealed interface ContinueStatement : HasJSDoc, BreakOrContinueStatement

    sealed interface DebuggerStatement : HasJSDoc

    sealed interface DeclarationStatement_name

    sealed interface DeclarationWithTypeParameterChildren : DeclarationWithTypeParameters, TypeParameterDeclaration_parent

    sealed interface DeclareKeyword : Modifier

    sealed interface Decorator : CallLikeExpression

    sealed interface DefaultClause : CaseOrDefaultClause

    sealed interface DefaultKeyword : Modifier

    sealed interface DoStatement : HasJSDoc

    sealed interface DotDotDotToken : BindingOrAssignmentElementRestIndicator

    sealed interface ElementAccessChain : OptionalChain

    sealed interface ElementAccessExpression : DeclarationName, BindingOrAssignmentElementTarget, AccessExpression

    sealed interface EmptyStatement : HasJSDoc

    sealed interface EndOfFileToken : HasJSDoc

    sealed interface EntityName : EntityNameOrEntityNameExpression, ModuleReference, JSDocNameReference_name, JSDocMemberName_left, JSDocLink_name, JSDocLinkCode_name, JSDocLinkPlain_name

    sealed interface EntityNameExpression : DeclarationName, EntityNameOrEntityNameExpression

    sealed interface EnumDeclaration : HasJSDoc

    sealed interface EnumMember : HasJSDoc, HasExpressionInitializer, VariableLikeDeclaration

    sealed interface EqualityOperator : EqualityOperatorOrHigher

    sealed interface EqualityOperatorOrHigher : BitwiseOperatorOrHigher

    sealed interface ExponentiationOperator : MultiplicativeOperatorOrHigher

    sealed interface ExportAssignment : HasJSDoc

    sealed interface ExportDeclaration : HasJSDoc, AssertClause_parent

    sealed interface ExportDeclaration_parent

    sealed interface ExportKeyword : Modifier

    sealed interface ExportSpecifier : ImportOrExportSpecifier

    sealed interface Expression : ConciseBody, ForInitializer, FunctionLikeDeclarationBase_body, FlowAssignment_node

    sealed interface ExpressionStatement : HasJSDoc

    sealed interface ExpressionWithTypeArguments : TypeReferenceType

    sealed interface ExpressionWithTypeArguments_parent

    sealed interface ExternalModuleReference : ModuleReference

    sealed interface FalseLiteral : BooleanLiteral

    sealed interface FlowArrayMutation : FlowNode

    sealed interface FlowArrayMutation_node

    sealed interface FlowAssignment : FlowNode

    sealed interface FlowAssignment_node

    sealed interface FlowCall : FlowNode

    sealed interface FlowCondition : FlowNode

    sealed interface FlowLabel : FlowNode

    sealed interface FlowReduceLabel : FlowNode

    sealed interface FlowStart : FlowNode

    sealed interface FlowStart_node

    sealed interface FlowSwitchClause : FlowNode

    sealed interface ForInStatement : HasJSDoc, HasInitializer, ForInOrOfStatement, VariableDeclarationList_parent

    sealed interface ForOfStatement : HasJSDoc, HasInitializer, ForInOrOfStatement, VariableDeclarationList_parent

    sealed interface ForStatement : HasJSDoc, HasInitializer, VariableDeclarationList_parent

    sealed interface FunctionBody : ConciseBody

    sealed interface FunctionDeclaration : HasJSDoc, SignatureDeclaration, FunctionLikeDeclaration

    sealed interface FunctionExpression : HasJSDoc, SignatureDeclaration, FunctionLikeDeclaration, FlowStart_node

    sealed interface FunctionLikeDeclarationBase_body

    sealed interface FunctionOrConstructorTypeNodeBase_kind

    sealed interface FunctionTypeNode : HasJSDoc, SignatureDeclaration, FunctionOrConstructorTypeNode

    sealed interface GetAccessorDeclaration : FunctionLikeDeclaration, AccessorDeclaration, FlowStart_node

    sealed interface GetAccessorDeclaration_parent

    sealed interface HasExpressionInitializer : HasInitializer

    sealed interface HeritageClause : ExpressionWithTypeArguments_parent

    sealed interface HeritageClause_parent

    sealed interface HeritageClause_token

    sealed interface Identifier : EntityName, PropertyName, MemberName, DeclarationName, BindingName, PropertyNameLiteral, BindingOrAssignmentElementTarget, EntityNameExpression, JsxTagNameExpression,
        ModuleName, JSDocNamespaceBody, AssertionKey, DeclarationStatement_name, TypePredicateNode_parameterName, JSDocTypedefTag_fullName, JSDocCallbackTag_fullName

    sealed interface IdentifierTypePredicate : TypePredicate

    sealed interface IfStatement : HasJSDoc

    sealed interface ImportClause : TypeOnlyCompatibleAliasDeclaration

    sealed interface ImportDeclaration : HasJSDoc, AssertClause_parent

    sealed interface ImportDeclaration_parent

    sealed interface ImportEqualsDeclaration : HasJSDoc, TypeOnlyCompatibleAliasDeclaration

    sealed interface ImportEqualsDeclaration_parent

    sealed interface ImportOrExportSpecifier : TypeOnlyCompatibleAliasDeclaration

    sealed interface ImportSpecifier : ImportOrExportSpecifier

    sealed interface IncompleteType : FlowType

    sealed interface IndexSignatureDeclaration : HasJSDoc, SignatureDeclaration

    sealed interface IndexType_type

    sealed interface IndexedAccessType : TypeVariable

    sealed interface InferTypeNode : TypeParameterDeclaration_parent

    sealed interface InstantiableType : IndexType_type

    sealed interface InterfaceDeclaration : HasJSDoc, ObjectTypeDeclaration, DeclarationWithTypeParameterChildren, GetAccessorDeclaration_parent, SetAccessorDeclaration_parent, HeritageClause_parent

    sealed interface IntersectionType : BaseType, StructuredType

    sealed interface IntersectionTypeNode : UnionOrIntersectionTypeNode

    sealed interface JSDoc : JSDocTag_parent

    sealed interface JSDocAugmentsTag : ExpressionWithTypeArguments_parent

    sealed interface JSDocCallbackTag : DeclarationWithTypeParameters

    sealed interface JSDocCallbackTag_fullName

    sealed interface JSDocFunctionType : HasJSDoc, SignatureDeclaration

    sealed interface JSDocImplementsTag : ExpressionWithTypeArguments_parent

    sealed interface JSDocLink : JSDocComment

    sealed interface JSDocLink_name

    sealed interface JSDocLinkCode : JSDocComment

    sealed interface JSDocLinkCode_name

    sealed interface JSDocLinkPlain : JSDocComment

    sealed interface JSDocLinkPlain_name

    sealed interface JSDocMemberName : JSDocNameReference_name, JSDocMemberName_left, JSDocLink_name, JSDocLinkCode_name, JSDocLinkPlain_name

    sealed interface JSDocMemberName_left

    sealed interface JSDocNameReference_name

    sealed interface JSDocNamespaceBody : ModuleBody

    sealed interface JSDocNamespaceDeclaration : JSDocNamespaceBody, ModuleDeclaration_body, JSDocTypedefTag_fullName, JSDocCallbackTag_fullName

    sealed interface JSDocNonNullableType : HasType, JSDocTypeReferencingNode

    sealed interface JSDocNullableType : HasType, JSDocTypeReferencingNode

    sealed interface JSDocOptionalType : HasType, JSDocTypeReferencingNode

    sealed interface JSDocParameterTag : VariableLikeDeclaration

    sealed interface JSDocPropertyTag : VariableLikeDeclaration

    sealed interface JSDocSignature : DeclarationWithTypeParameters, Signature_declaration

    sealed interface JSDocTag_parent

    sealed interface JSDocTemplateTag : DeclarationWithTypeParameterChildren

    sealed interface JSDocText : JSDocComment

    sealed interface JSDocTypeExpression : HasType, TypePredicateNode_parent, JSDocTypedefTag_typeExpression

    sealed interface JSDocTypeLiteral : JSDocTag_parent, JSDocTypedefTag_typeExpression

    sealed interface JSDocTypedefTag : DeclarationWithTypeParameters

    sealed interface JSDocTypedefTag_fullName

    sealed interface JSDocTypedefTag_typeExpression

    sealed interface JSDocVariadicType : HasType, JSDocTypeReferencingNode

    sealed interface JsonMinusNumericLiteral : JsonObjectExpression

    sealed interface JsxAttribute : HasInitializer, VariableLikeDeclaration, JsxAttributeLike

    sealed interface JsxAttribute_initializer

    sealed interface JsxAttributeLike : JsxExpression_parent

    sealed interface JsxElement : JsxChild, JsxExpression_parent, JsxText_parent

    sealed interface JsxExpression : JsxChild, JsxAttribute_initializer

    sealed interface JsxExpression_parent

    sealed interface JsxFragment : JsxChild, JsxExpression_parent, JsxText_parent

    sealed interface JsxOpeningElement : HasTypeArguments, JsxOpeningLikeElement

    sealed interface JsxOpeningLikeElement : CallLikeExpression

    sealed interface JsxSelfClosingElement : HasTypeArguments, JsxOpeningLikeElement, JsxChild

    sealed interface JsxSpreadAttribute : JsxAttributeLike

    sealed interface JsxTagNamePropertyAccess : JsxTagNameExpression

    sealed interface JsxText : LiteralToken, JsxChild

    sealed interface JsxText_parent

    sealed interface KeywordSyntaxKind : TokenSyntaxKind, JSDocSyntaxKind

    sealed interface LabeledStatement : HasJSDoc

    sealed interface LiteralExpression : LiteralTypeNode_literal

    sealed interface LiteralSyntaxKind : TokenSyntaxKind

    sealed interface LiteralTypeNode_literal

    sealed interface LogicalOperator : LogicalOperatorOrHigher

    sealed interface LogicalOperatorOrHigher : AssignmentOperatorOrHigher

    sealed interface MappedTypeNode : HasType

    sealed interface MappedTypeNode_questionToken

    sealed interface MappedTypeNode_readonlyToken

    sealed interface MetaProperty_keywordToken

    sealed interface MethodDeclaration : HasJSDoc, SignatureDeclaration, ObjectLiteralElementLike, FunctionLikeDeclaration, FlowStart_node

    sealed interface MethodDeclaration_parent

    sealed interface MethodSignature : HasJSDoc, SignatureDeclaration

    sealed interface MinusToken : MappedTypeNode_readonlyToken, MappedTypeNode_questionToken

    sealed interface ModuleBlock : BlockLike, NamespaceBody, ImportEqualsDeclaration_parent, ImportDeclaration_parent, ExportDeclaration_parent

    sealed interface ModuleBody : ModuleDeclaration_parent, ModuleDeclaration_body

    sealed interface ModuleDeclaration : HasJSDoc

    sealed interface ModuleDeclaration_body

    sealed interface ModuleDeclaration_parent

    sealed interface MultiplicativeOperator : MultiplicativeOperatorOrHigher

    sealed interface MultiplicativeOperatorOrHigher : AdditiveOperatorOrHigher

    sealed interface NamedExports : NamedExportBindings, NamedImportsOrExports

    sealed interface NamedImports : NamedImportBindings, NamedImportsOrExports

    sealed interface NamedTupleMember : HasJSDoc, SyntheticExpression_tupleNameSource

    sealed interface NamespaceBody : ModuleBody

    sealed interface NamespaceDeclaration : NamespaceBody

    sealed interface NamespaceExport : NamedExportBindings

    sealed interface NamespaceExportDeclaration : HasJSDoc

    sealed interface NamespaceImport : NamedImportBindings, TypeOnlyCompatibleAliasDeclaration

    sealed interface NewExpression : HasTypeArguments, CallLikeExpression, SpreadElement_parent

    sealed interface NoSubstitutionTemplateLiteral : StringLiteralLike, LiteralToken, TemplateLiteralToken, TemplateLiteral

    sealed interface NonNullChain : OptionalChain

    sealed interface NullLiteral : JsonObjectExpression, LiteralTypeNode_literal

    sealed interface NumericLiteral : PropertyName, DeclarationName, PropertyNameLiteral, LiteralToken, JsonObjectExpression, DeclarationStatement_name

    sealed interface ObjectBindingOrAssignmentElement : BindingOrAssignmentElement

    sealed interface ObjectBindingOrAssignmentPattern : BindingOrAssignmentPattern

    sealed interface ObjectBindingPattern : BindingPattern, ObjectBindingOrAssignmentPattern

    sealed interface ObjectBindingPattern_parent

    sealed interface ObjectDestructuringAssignment : DestructuringAssignment

    sealed interface ObjectLiteralExpression : ObjectBindingOrAssignmentPattern, AssignmentPattern, JsonObjectExpression, DestructuringPattern, MethodDeclaration_parent, GetAccessorDeclaration_parent,
        SetAccessorDeclaration_parent

    sealed interface ObjectType : BaseType, StructuredType

    sealed interface OmittedExpression : ArrayBindingElement, BindingOrAssignmentElementTarget

    sealed interface OverrideKeyword : Modifier

    sealed interface ParameterDeclaration : HasJSDoc, HasType, HasExpressionInitializer, VariableLikeDeclaration, BindingOrAssignmentElement, ObjectBindingPattern_parent, ArrayBindingPattern_parent,
        SyntheticExpression_tupleNameSource

    sealed interface ParenthesizedExpression : HasJSDoc

    sealed interface ParenthesizedTypeNode : HasType

    sealed interface PlusToken : MappedTypeNode_readonlyToken, MappedTypeNode_questionToken

    sealed interface PrefixUnaryExpression : LiteralTypeNode_literal

    sealed interface PrivateIdentifier : PropertyName, MemberName, DeclarationName

    sealed interface PrivateKeyword : Modifier, AccessibilityModifier

    sealed interface PropertyAccessChain : OptionalChain

    sealed interface PropertyAccessEntityNameExpression : EntityNameExpression

    sealed interface PropertyAccessExpression : BindingOrAssignmentElementTarget, AccessExpression

    sealed interface PropertyAssignment : HasJSDoc, HasExpressionInitializer, ObjectLiteralElementLike, VariableLikeDeclaration, ObjectBindingOrAssignmentElement

    sealed interface PropertyDeclaration : HasJSDoc, HasType, HasExpressionInitializer, VariableLikeDeclaration

    sealed interface PropertySignature : HasJSDoc, HasType, HasExpressionInitializer, VariableLikeDeclaration

    sealed interface ProtectedKeyword : Modifier, AccessibilityModifier

    sealed interface PseudoLiteralSyntaxKind : TokenSyntaxKind

    sealed interface PseudoLiteralToken : TemplateLiteralToken

    sealed interface PublicKeyword : Modifier, AccessibilityModifier

    sealed interface PunctuationSyntaxKind : TokenSyntaxKind

    sealed interface QualifiedName : EntityName

    sealed interface QuestionToken : MappedTypeNode_questionToken

    sealed interface ReadonlyKeyword : Modifier, ParameterPropertyModifier, ClassMemberModifier

    sealed interface ReadonlyToken : MappedTypeNode_readonlyToken

    sealed interface RegularExpressionLiteral : LiteralToken

    sealed interface RelationalOperator : RelationalOperatorOrHigher

    sealed interface RelationalOperatorOrHigher : EqualityOperatorOrHigher

    sealed interface RenameInfoFailure : RenameInfo

    sealed interface RenameInfoSuccess : RenameInfo

    sealed interface ReturnStatement : HasJSDoc

    sealed interface ScopedEmitHelper : EmitHelper

    sealed interface SetAccessorDeclaration : FunctionLikeDeclaration, AccessorDeclaration, FlowStart_node

    sealed interface SetAccessorDeclaration_parent

    sealed interface ShiftOperator : ShiftOperatorOrHigher

    sealed interface ShiftOperatorOrHigher : RelationalOperatorOrHigher

    sealed interface ShorthandPropertyAssignment : HasJSDoc, ObjectLiteralElementLike, VariableLikeDeclaration, ObjectBindingOrAssignmentElement

    sealed interface Signature_declaration

    sealed interface SignatureDeclaration : HasType, DeclarationWithTypeParameterChildren, TypePredicateNode_parent, Signature_declaration

    sealed interface SignatureHelpCharacterTypedReason : SignatureHelpTriggerReason

    sealed interface SignatureHelpInvokedReason : SignatureHelpTriggerReason

    sealed interface SignatureHelpRetriggeredReason : SignatureHelpTriggerReason

    sealed interface SourceFile : BlockLike, ModuleDeclaration_parent, ImportEqualsDeclaration_parent, ImportDeclaration_parent, ExportDeclaration_parent

    sealed interface SpreadAssignment : HasJSDoc, ObjectLiteralElementLike, ObjectBindingOrAssignmentElement, BindingOrAssignmentElementRestIndicator

    sealed interface SpreadElement : BindingOrAssignmentElementRestIndicator

    sealed interface SpreadElement_parent

    sealed interface StaticKeyword : Modifier, ClassMemberModifier

    sealed interface StringLiteral : PropertyName, StringLiteralLike, LiteralToken, ModuleName, AssertionKey, JsonObjectExpression, DeclarationStatement_name, JsxAttribute_initializer

    sealed interface StringLiteralLike : DeclarationName, PropertyNameLiteral

    sealed interface SuperElementAccessExpression : SuperProperty

    sealed interface SuperPropertyAccessExpression : SuperProperty

    sealed interface SwitchStatement : HasJSDoc

    sealed interface SyntaxKind_AbstractKeyword : KeywordSyntaxKind, ModifierSyntaxKind

    sealed interface SyntaxKind_AmpersandAmpersandEqualsToken : CompoundAssignmentOperator, LogicalOrCoalescingAssignmentOperator

    sealed interface SyntaxKind_AmpersandAmpersandToken : PunctuationSyntaxKind, LogicalOperator

    sealed interface SyntaxKind_AmpersandEqualsToken : PunctuationSyntaxKind, CompoundAssignmentOperator

    sealed interface SyntaxKind_AmpersandToken : PunctuationSyntaxKind, BitwiseOperator

    sealed interface SyntaxKind_AnyKeyword : KeywordSyntaxKind, KeywordTypeSyntaxKind

    sealed interface SyntaxKind_AsKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_AssertKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_AssertsKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_AsteriskAsteriskEqualsToken : PunctuationSyntaxKind, CompoundAssignmentOperator

    sealed interface SyntaxKind_AsteriskAsteriskToken : PunctuationSyntaxKind

    sealed interface SyntaxKind_AsteriskEqualsToken : PunctuationSyntaxKind, CompoundAssignmentOperator

    sealed interface SyntaxKind_AsteriskToken : PunctuationSyntaxKind, JSDocSyntaxKind, MultiplicativeOperator

    sealed interface SyntaxKind_AsyncKeyword : KeywordSyntaxKind, ModifierSyntaxKind

    sealed interface SyntaxKind_AtToken : PunctuationSyntaxKind, JSDocSyntaxKind

    sealed interface SyntaxKind_AwaitKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_BacktickToken : PunctuationSyntaxKind, JSDocSyntaxKind

    sealed interface SyntaxKind_BarBarEqualsToken : CompoundAssignmentOperator, LogicalOrCoalescingAssignmentOperator

    sealed interface SyntaxKind_BarBarToken : PunctuationSyntaxKind, LogicalOperator

    sealed interface SyntaxKind_BarEqualsToken : PunctuationSyntaxKind, CompoundAssignmentOperator

    sealed interface SyntaxKind_BarToken : PunctuationSyntaxKind, BitwiseOperator

    sealed interface SyntaxKind_BigIntKeyword : KeywordSyntaxKind, KeywordTypeSyntaxKind

    sealed interface SyntaxKind_BigIntLiteral : LiteralSyntaxKind

    sealed interface SyntaxKind_BooleanKeyword : KeywordSyntaxKind, KeywordTypeSyntaxKind

    sealed interface SyntaxKind_BreakKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_CaretEqualsToken : PunctuationSyntaxKind, CompoundAssignmentOperator

    sealed interface SyntaxKind_CaretToken : PunctuationSyntaxKind, BitwiseOperator

    sealed interface SyntaxKind_CaseKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_CatchKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_ClassDeclaration : ClassLikeDeclarationBase_kind

    sealed interface SyntaxKind_ClassExpression : ClassLikeDeclarationBase_kind

    sealed interface SyntaxKind_ClassKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_CloseBraceToken : PunctuationSyntaxKind, JSDocSyntaxKind

    sealed interface SyntaxKind_CloseBracketToken : PunctuationSyntaxKind, JSDocSyntaxKind

    sealed interface SyntaxKind_CloseParenToken : PunctuationSyntaxKind

    sealed interface SyntaxKind_ColonToken : PunctuationSyntaxKind

    sealed interface SyntaxKind_CommaToken : PunctuationSyntaxKind, JSDocSyntaxKind, BinaryOperator

    sealed interface SyntaxKind_ConflictMarkerTrivia : TriviaSyntaxKind, JsxTokenSyntaxKind

    sealed interface SyntaxKind_ConstKeyword : KeywordSyntaxKind, ModifierSyntaxKind

    sealed interface SyntaxKind_ConstructorKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_ConstructorType : FunctionOrConstructorTypeNodeBase_kind

    sealed interface SyntaxKind_ContinueKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_DebuggerKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_DeclareKeyword : KeywordSyntaxKind, ModifierSyntaxKind

    sealed interface SyntaxKind_DefaultKeyword : KeywordSyntaxKind, ModifierSyntaxKind

    sealed interface SyntaxKind_DeleteKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_DoKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_DotDotDotToken : PunctuationSyntaxKind

    sealed interface SyntaxKind_DotToken : PunctuationSyntaxKind, JSDocSyntaxKind

    sealed interface SyntaxKind_ElseKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_EndOfFileToken : TokenSyntaxKind, JsxTokenSyntaxKind, JSDocSyntaxKind

    sealed interface SyntaxKind_EnumKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_EqualsEqualsEqualsToken : PunctuationSyntaxKind, EqualityOperator

    sealed interface SyntaxKind_EqualsEqualsToken : PunctuationSyntaxKind, EqualityOperator

    sealed interface SyntaxKind_EqualsGreaterThanToken : PunctuationSyntaxKind

    sealed interface SyntaxKind_EqualsToken : PunctuationSyntaxKind, JSDocSyntaxKind, AssignmentOperator

    sealed interface SyntaxKind_ExclamationEqualsEqualsToken : PunctuationSyntaxKind, EqualityOperator

    sealed interface SyntaxKind_ExclamationEqualsToken : PunctuationSyntaxKind, EqualityOperator

    sealed interface SyntaxKind_ExclamationToken : PunctuationSyntaxKind, PrefixUnaryOperator

    sealed interface SyntaxKind_ExportKeyword : KeywordSyntaxKind, ModifierSyntaxKind

    sealed interface SyntaxKind_ExtendsKeyword : KeywordSyntaxKind, HeritageClause_token

    sealed interface SyntaxKind_FalseKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_FinallyKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_ForKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_FromKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_FunctionKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_FunctionType : FunctionOrConstructorTypeNodeBase_kind

    sealed interface SyntaxKind_GetKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_GlobalKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_GreaterThanEqualsToken : PunctuationSyntaxKind, RelationalOperator

    sealed interface SyntaxKind_GreaterThanGreaterThanEqualsToken : PunctuationSyntaxKind, CompoundAssignmentOperator

    sealed interface SyntaxKind_GreaterThanGreaterThanGreaterThanEqualsToken : PunctuationSyntaxKind, CompoundAssignmentOperator

    sealed interface SyntaxKind_GreaterThanGreaterThanGreaterThanToken : PunctuationSyntaxKind, ShiftOperator

    sealed interface SyntaxKind_GreaterThanGreaterThanToken : PunctuationSyntaxKind, ShiftOperator

    sealed interface SyntaxKind_GreaterThanToken : PunctuationSyntaxKind, JSDocSyntaxKind, RelationalOperator

    sealed interface SyntaxKind_HashToken : PunctuationSyntaxKind, JSDocSyntaxKind

    sealed interface SyntaxKind_Identifier : TokenSyntaxKind, JSDocSyntaxKind

    sealed interface SyntaxKind_IfKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_ImplementsKeyword : KeywordSyntaxKind, HeritageClause_token

    sealed interface SyntaxKind_ImportKeyword : KeywordSyntaxKind, MetaProperty_keywordToken

    sealed interface SyntaxKind_InKeyword : KeywordSyntaxKind, RelationalOperator

    sealed interface SyntaxKind_InferKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_InstanceOfKeyword : KeywordSyntaxKind, RelationalOperator

    sealed interface SyntaxKind_InterfaceKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_IntrinsicKeyword : KeywordSyntaxKind, KeywordTypeSyntaxKind

    sealed interface SyntaxKind_IsKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_JsxText : LiteralSyntaxKind, JsxTokenSyntaxKind

    sealed interface SyntaxKind_JsxTextAllWhiteSpaces : LiteralSyntaxKind, JsxTokenSyntaxKind

    sealed interface SyntaxKind_KeyOfKeyword : KeywordSyntaxKind, TypeOperatorNode_operator

    sealed interface SyntaxKind_LessThanEqualsToken : PunctuationSyntaxKind, RelationalOperator

    sealed interface SyntaxKind_LessThanLessThanEqualsToken : PunctuationSyntaxKind, CompoundAssignmentOperator

    sealed interface SyntaxKind_LessThanLessThanToken : PunctuationSyntaxKind, ShiftOperator

    sealed interface SyntaxKind_LessThanSlashToken : PunctuationSyntaxKind, JsxTokenSyntaxKind

    sealed interface SyntaxKind_LessThanToken : PunctuationSyntaxKind, JsxTokenSyntaxKind, JSDocSyntaxKind, RelationalOperator

    sealed interface SyntaxKind_LetKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_MinusEqualsToken : PunctuationSyntaxKind, CompoundAssignmentOperator

    sealed interface SyntaxKind_MinusMinusToken : PunctuationSyntaxKind, PrefixUnaryOperator, PostfixUnaryOperator

    sealed interface SyntaxKind_MinusToken : PunctuationSyntaxKind, PrefixUnaryOperator, AdditiveOperator

    sealed interface SyntaxKind_ModuleKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_MultiLineCommentTrivia : TriviaSyntaxKind, CommentKind

    sealed interface SyntaxKind_NamespaceKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_NeverKeyword : KeywordSyntaxKind, KeywordTypeSyntaxKind

    sealed interface SyntaxKind_NewKeyword : KeywordSyntaxKind, MetaProperty_keywordToken

    sealed interface SyntaxKind_NewLineTrivia : TriviaSyntaxKind, JSDocSyntaxKind

    sealed interface SyntaxKind_NoSubstitutionTemplateLiteral : LiteralSyntaxKind

    sealed interface SyntaxKind_NullKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_NumberKeyword : KeywordSyntaxKind, KeywordTypeSyntaxKind

    sealed interface SyntaxKind_NumericLiteral : LiteralSyntaxKind

    sealed interface SyntaxKind_ObjectKeyword : KeywordSyntaxKind, KeywordTypeSyntaxKind

    sealed interface SyntaxKind_OfKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_OpenBraceToken : PunctuationSyntaxKind, JsxTokenSyntaxKind, JSDocSyntaxKind

    sealed interface SyntaxKind_OpenBracketToken : PunctuationSyntaxKind, JSDocSyntaxKind

    sealed interface SyntaxKind_OpenParenToken : PunctuationSyntaxKind

    sealed interface SyntaxKind_OverrideKeyword : KeywordSyntaxKind, ModifierSyntaxKind

    sealed interface SyntaxKind_PackageKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_PercentEqualsToken : PunctuationSyntaxKind, CompoundAssignmentOperator

    sealed interface SyntaxKind_PercentToken : PunctuationSyntaxKind, MultiplicativeOperator

    sealed interface SyntaxKind_PlusEqualsToken : PunctuationSyntaxKind, CompoundAssignmentOperator

    sealed interface SyntaxKind_PlusPlusToken : PunctuationSyntaxKind, PrefixUnaryOperator, PostfixUnaryOperator

    sealed interface SyntaxKind_PlusToken : PunctuationSyntaxKind, PrefixUnaryOperator, AdditiveOperator

    sealed interface SyntaxKind_PrivateKeyword : KeywordSyntaxKind, ModifierSyntaxKind

    sealed interface SyntaxKind_ProtectedKeyword : KeywordSyntaxKind, ModifierSyntaxKind

    sealed interface SyntaxKind_PublicKeyword : KeywordSyntaxKind, ModifierSyntaxKind

    sealed interface SyntaxKind_QuestionDotToken : PunctuationSyntaxKind

    sealed interface SyntaxKind_QuestionQuestionEqualsToken : CompoundAssignmentOperator, LogicalOrCoalescingAssignmentOperator

    sealed interface SyntaxKind_QuestionQuestionToken : PunctuationSyntaxKind, AssignmentOperatorOrHigher

    sealed interface SyntaxKind_QuestionToken : PunctuationSyntaxKind

    sealed interface SyntaxKind_ReadonlyKeyword : KeywordSyntaxKind, ModifierSyntaxKind, TypeOperatorNode_operator

    sealed interface SyntaxKind_RegularExpressionLiteral : LiteralSyntaxKind

    sealed interface SyntaxKind_RequireKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_ReturnKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_SemicolonToken : PunctuationSyntaxKind

    sealed interface SyntaxKind_SetKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_ShebangTrivia : TriviaSyntaxKind

    sealed interface SyntaxKind_SingleLineCommentTrivia : TriviaSyntaxKind, CommentKind

    sealed interface SyntaxKind_SlashEqualsToken : PunctuationSyntaxKind, CompoundAssignmentOperator

    sealed interface SyntaxKind_SlashToken : PunctuationSyntaxKind, MultiplicativeOperator

    sealed interface SyntaxKind_StaticKeyword : KeywordSyntaxKind, ModifierSyntaxKind

    sealed interface SyntaxKind_StringKeyword : KeywordSyntaxKind, KeywordTypeSyntaxKind

    sealed interface SyntaxKind_StringLiteral : LiteralSyntaxKind

    sealed interface SyntaxKind_SuperKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_SwitchKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_SymbolKeyword : KeywordSyntaxKind, KeywordTypeSyntaxKind

    sealed interface SyntaxKind_TemplateHead : PseudoLiteralSyntaxKind

    sealed interface SyntaxKind_TemplateMiddle : PseudoLiteralSyntaxKind

    sealed interface SyntaxKind_TemplateTail : PseudoLiteralSyntaxKind

    sealed interface SyntaxKind_ThisKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_ThrowKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_TildeToken : PunctuationSyntaxKind, PrefixUnaryOperator

    sealed interface SyntaxKind_TrueKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_TryKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_TypeKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_TypeOfKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_UndefinedKeyword : KeywordSyntaxKind, KeywordTypeSyntaxKind

    sealed interface SyntaxKind_UniqueKeyword : KeywordSyntaxKind, TypeOperatorNode_operator

    sealed interface SyntaxKind_Unknown : TokenSyntaxKind, JSDocSyntaxKind

    sealed interface SyntaxKind_UnknownKeyword : KeywordSyntaxKind, KeywordTypeSyntaxKind

    sealed interface SyntaxKind_UnparsedInternalText : UnparsedTextLike_kind

    sealed interface SyntaxKind_UnparsedText : UnparsedTextLike_kind

    sealed interface SyntaxKind_VarKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_VoidKeyword : KeywordSyntaxKind, KeywordTypeSyntaxKind

    sealed interface SyntaxKind_WhileKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_WhitespaceTrivia : TriviaSyntaxKind, JSDocSyntaxKind

    sealed interface SyntaxKind_WithKeyword : KeywordSyntaxKind

    sealed interface SyntaxKind_YieldKeyword : KeywordSyntaxKind

    sealed interface SyntheticExpression_tupleNameSource

    sealed interface TaggedTemplateExpression : HasTypeArguments, CallLikeExpression

    sealed interface TemplateExpression : TemplateLiteral, TemplateHead_parent

    sealed interface TemplateHead : PseudoLiteralToken

    sealed interface TemplateHead_parent

    sealed interface TemplateLiteralTypeNode : TemplateHead_parent

    sealed interface TemplateLiteralTypeSpan : TemplateMiddle_parent, TemplateTail_parent

    sealed interface TemplateLiteralTypeSpan_literal

    sealed interface TemplateMiddle : PseudoLiteralToken, TemplateLiteralTypeSpan_literal, TemplateSpan_literal

    sealed interface TemplateMiddle_parent

    sealed interface TemplateSpan : TemplateMiddle_parent, TemplateTail_parent

    sealed interface TemplateSpan_literal

    sealed interface TemplateTail : PseudoLiteralToken, TemplateLiteralTypeSpan_literal, TemplateSpan_literal

    sealed interface TemplateTail_parent

    sealed interface ThisExpression : JsxTagNameExpression

    sealed interface ThisTypeNode : TypePredicateNode_parameterName

    sealed interface ThisTypePredicate : TypePredicate

    sealed interface ThrowStatement : HasJSDoc

    sealed interface TriviaSyntaxKind : TokenSyntaxKind

    sealed interface TrueLiteral : BooleanLiteral

    sealed interface TryStatement : HasJSDoc

    sealed interface TupleTypeNode : TypeReference_node

    sealed interface Type : FlowType

    sealed interface TypeAliasDeclaration : HasJSDoc, HasType, DeclarationWithTypeParameterChildren

    sealed interface TypeAssertion : AssertionExpression

    sealed interface TypeLiteralNode : ObjectTypeDeclaration, GetAccessorDeclaration_parent, SetAccessorDeclaration_parent

    sealed interface TypeOperatorNode : HasType

    sealed interface TypeOperatorNode_operator

    sealed interface TypeParameter : TypeVariable

    sealed interface TypeParameterDeclaration_parent

    sealed interface TypePredicateNode : HasType

    sealed interface TypePredicateNode_parameterName

    sealed interface TypePredicateNode_parent

    sealed interface TypeReference_node

    sealed interface TypeReferenceNode : TypeReferenceType, TypeReference_node

    sealed interface TypeVariable : BaseType

    sealed interface UnionOrIntersectionType : IndexType_type

    sealed interface UnionType : StructuredType

    sealed interface UnionTypeNode : UnionOrIntersectionTypeNode

    sealed interface UnparsedPrepend : UnparsedSourceText

    sealed interface UnparsedPrologue : UnparsedNode

    sealed interface UnparsedSourceText : UnparsedNode

    sealed interface UnparsedSyntheticReference : UnparsedNode

    sealed interface UnparsedTextLike : UnparsedSourceText

    sealed interface UnparsedTextLike_kind

    sealed interface UnscopedEmitHelper : EmitHelper

    sealed interface VariableDeclaration : HasJSDoc, HasType, HasExpressionInitializer, VariableLikeDeclaration, BindingOrAssignmentElement, ObjectBindingPattern_parent, ArrayBindingPattern_parent,
        FlowAssignment_node

    sealed interface VariableDeclaration_parent

    sealed interface VariableDeclarationList : ForInitializer, VariableDeclaration_parent

    sealed interface VariableDeclarationList_parent

    sealed interface VariableStatement : HasJSDoc, VariableDeclarationList_parent

    sealed interface WhileStatement : HasJSDoc

    sealed interface WithStatement : HasJSDoc

}
