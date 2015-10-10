package twg2.functions.templates.generators;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.BiFunction;
import java.util.function.DoubleConsumer;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.LongConsumer;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;

import twg2.functions.templates.FunctionTypeTmpl;
import twg2.template.codeTemplate.ClassInfo;
import twg2.template.codeTemplate.ClassTemplate;
import twg2.template.codeTemplate.ClassTemplateBuilder;
import twg2.template.codeTemplate.GenericTypeInfo;
import twg2.template.codeTemplate.HasTypeName;
import twg2.template.codeTemplate.TemplateNames;
import twg2.template.codeTemplate.primitiveTemplate.PrimitiveTypeClassTemplate;
import twg2.template.codeTemplate.primitiveTemplate.PrimitiveTypeInfo;
import twg2.template.codeTemplate.primitiveTemplate.PrimitiveTypeTemplate;
import twg2.template.codeTemplate.render.TemplateImports;
import twg2.template.codeTemplate.render.TemplateRenders;

/**
 * @author TeamworkGuy2
 * @since 2015-1-23
 */
public class GenerateFunctions {

	@FunctionalInterface
	public static interface TriFunc<T1, T2, T3, R> {
		public R apply(T1 t1, T2 t2, T3 t3);
	}

	@FunctionalInterface
	public static interface QuadFunc<T1, T2, T3, T4, R> {
		public R apply(T1 t1, T2 t2, T3 t3, T4 t4);
	}

	@FunctionalInterface
	public static interface QuinConsumer<T1, T2, T3, T4, T5> {
		public void accept(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5);
	}


	static class FuncClassInfo {
		GenericTypeInfo typeInfo;
		boolean isOverride;

		public FuncClassInfo(GenericTypeInfo typeInfo, boolean isOverride) {
			this.typeInfo = typeInfo;
			this.isOverride = isOverride;
		}

	}


	private static PrimitiveTypeInfo booleanTmpl = new PrimitiveTypeTemplate(Boolean.TYPE);
	private static PrimitiveTypeInfo byteTmpl = new PrimitiveTypeTemplate(Byte.TYPE);
	private static PrimitiveTypeInfo charTmpl = new PrimitiveTypeTemplate(Character.TYPE);
	private static PrimitiveTypeInfo shortTmpl = new PrimitiveTypeTemplate(Short.TYPE);
	private static PrimitiveTypeInfo intTmpl = new PrimitiveTypeTemplate(Integer.TYPE);
	private static PrimitiveTypeInfo floatTmpl = new PrimitiveTypeTemplate(Float.TYPE);
	private static PrimitiveTypeInfo longTmpl = new PrimitiveTypeTemplate(Long.TYPE);
	private static PrimitiveTypeInfo doubleTmpl = new PrimitiveTypeTemplate(Double.TYPE);
	private static GenericTypeInfo stringTmpl = GenericTypeInfo.of("String", String.class);
	private static GenericTypeInfo enumTmpl = GenericTypeInfo.of("Enum", Enum.class);
	private static GenericTypeInfo objectTmpl = GenericTypeInfo.of("Object", Object.class);
	private static GenericTypeInfo genericEnumParam = GenericTypeInfo.of("T", Object.class, true);
	private static GenericTypeInfo genericParam1 = GenericTypeInfo.of("T1", Object.class, true);
	private static GenericTypeInfo genericParam2 = GenericTypeInfo.of("T2", Object.class, true);
	private static GenericTypeInfo genericParam3 = GenericTypeInfo.of("T3", Object.class, true);
	private static GenericTypeInfo genericParam4 = GenericTypeInfo.of("T4", Object.class, true);
	private static GenericTypeInfo genericReturnVal = GenericTypeInfo.of("R", Object.class, true);
	private static List<GenericTypeInfo> genericParams = Arrays.asList(genericParam1, genericParam2, genericParam3, genericParam4);

	private static List<FuncClassInfo> oldArgFuncTypes = Arrays.asList(
			new FuncClassInfo(booleanTmpl, false),
			new FuncClassInfo(byteTmpl, false),
			new FuncClassInfo(charTmpl, false),
			new FuncClassInfo(shortTmpl, false),
			new FuncClassInfo(intTmpl, true),
			new FuncClassInfo(floatTmpl, false),
			new FuncClassInfo(longTmpl, true),
			new FuncClassInfo(doubleTmpl, true)
	);

	private static Function<String, List<ClassTemplateBuilder<ClassTemplate>>> consumerImplementTmplBldrs = (classNameTmpl) -> Arrays.asList(
			ClassTemplateBuilder.of(classNameTmpl), // boolean
			ClassTemplateBuilder.of(classNameTmpl), // byte
			ClassTemplateBuilder.of(classNameTmpl), // char
			ClassTemplateBuilder.of(classNameTmpl), // short
			ClassTemplateBuilder.of(classNameTmpl).extend(IntConsumer.class), // int
			ClassTemplateBuilder.of(classNameTmpl), // float
			ClassTemplateBuilder.of(classNameTmpl).extend(LongConsumer.class), // long
			ClassTemplateBuilder.of(classNameTmpl).extend(DoubleConsumer.class) // double
	);

	private static Function<String, List<ClassTemplateBuilder<ClassTemplate>>> supplierImplementTmplBldrs = (classNameTmpl) -> Arrays.asList(
			ClassTemplateBuilder.of(classNameTmpl), // boolean
			ClassTemplateBuilder.of(classNameTmpl), // byte
			ClassTemplateBuilder.of(classNameTmpl), // char
			ClassTemplateBuilder.of(classNameTmpl), // short
			ClassTemplateBuilder.of(classNameTmpl).extend(IntSupplier.class), // int
			ClassTemplateBuilder.of(classNameTmpl), // float
			ClassTemplateBuilder.of(classNameTmpl).extend(LongSupplier.class), // long
			ClassTemplateBuilder.of(classNameTmpl).extend(DoubleSupplier.class) // double
	);

	private static Function<String, List<ClassTemplateBuilder<ClassTemplate>>> predicateImplementTmplBldrs = (classNameTmpl) -> Arrays.asList(
			ClassTemplateBuilder.of(classNameTmpl), // boolean
			ClassTemplateBuilder.of(classNameTmpl), // byte
			ClassTemplateBuilder.of(classNameTmpl), // char
			ClassTemplateBuilder.of(classNameTmpl), // short
			ClassTemplateBuilder.of(classNameTmpl).extend(IntPredicate.class), // int
			ClassTemplateBuilder.of(classNameTmpl), // float
			ClassTemplateBuilder.of(classNameTmpl).extend(LongPredicate.class), // long
			ClassTemplateBuilder.of(classNameTmpl).extend(DoublePredicate.class) // double
	);

	private static List<GenericTypeInfo> primitiveFuncTypes = Arrays.asList(booleanTmpl, byteTmpl, charTmpl, shortTmpl, intTmpl, floatTmpl, longTmpl, doubleTmpl);
	private static List<GenericTypeInfo> primitiveAnd1GenericFuncTypes = Arrays.asList(booleanTmpl, byteTmpl, charTmpl, shortTmpl, intTmpl, floatTmpl, longTmpl, doubleTmpl, genericParam1);
	private static List<GenericTypeInfo> primitiveAndReturnFuncTypes = Arrays.asList(booleanTmpl, byteTmpl, charTmpl, shortTmpl, intTmpl, floatTmpl, longTmpl, doubleTmpl, genericReturnVal);

	private static String baseDir = "src/twg2.functions.templates/";
	private static TemplateImports importsMapper = TemplateImports.fromCommonTemplateFiles("$commonTemplates.JavaClass$", true);

	private static BiFunction<GenericTypeInfo, String, String> oneTypeNameConverter = (type, str) -> {
		return str.replace("$Type1$", type.isGeneric() ? "Object" : type.getTypeShortTitleCase());
	};

	private static TriFunc<GenericTypeInfo, GenericTypeInfo, String, String> twoTypeNameConverter = (type1, type2, str) -> {
		return str
				.replace("$Type1$", type1.isGeneric() ? "Object" : type1.getTypeShortTitleCase())
				.replace("$Type2$", type2.isGeneric() ? "Object" : type2.getTypeShortTitleCase());
	};

	private static QuadFunc<GenericTypeInfo, GenericTypeInfo, GenericTypeInfo, String, String> twoTypeReturnNameConverter = (type1, type2, type3, str) -> {
		return str
				.replace("$Type1$", type1 == null || type1.isGeneric() ? "Object" : type1.getTypeShortTitleCase())
				.replace("$Type2$", type2 == null || type2.isGeneric() ? "Object" : type2.getTypeShortTitleCase())
				.replace("$ReturnType$", type3 == null || type3.isGeneric() ? "Object" : type3.getTypeShortTitleCase());
	};


	public static void generatePrimitivePredicates() {
		List<Map.Entry<ClassInfo, FunctionTypeTmpl>> tmpls = getPrimitiveTemplateInfos("$Type1$Predicate", oldArgFuncTypes, predicateImplementTmplBldrs.apply("$Type1$Predicate"), twoTypeNameConverter,
				true, true, Arrays.asList(null, null, null, null), null);
		TemplateRenders.renderClassTemplateEntries(baseDir + "TPredicate.stg", "TPredicate", importsMapper, tmpls);
	}


	public static void generatePrimitiveConsumers() {
		List<Map.Entry<ClassInfo, FunctionTypeTmpl>> tmpls = getPrimitiveTemplateInfos("$Type1$Consumer", oldArgFuncTypes, consumerImplementTmplBldrs.apply("$Type1$Consumer"), twoTypeNameConverter,
				false, false, Arrays.asList(null, null, null, null), null);
		TemplateRenders.renderClassTemplateEntries(baseDir + "TConsumer.stg", "TConsumer", importsMapper, tmpls);		
	}


	public static void generatePrimitiveSuppliers() {
		List<Map.Entry<ClassInfo, FunctionTypeTmpl>> tmpls = getPrimitiveTemplateInfos("$Type1$Supplier", oldArgFuncTypes, supplierImplementTmplBldrs.apply("$Type1$Supplier"), twoTypeNameConverter,
				false, false, Arrays.asList(null, null, null, null), null);
		TemplateRenders.renderClassTemplateEntries(baseDir + "TSupplier.stg", "TSupplier", importsMapper, tmpls);
	}


	// Bi-functions
	public static void generatePrimitiveBiConsumers() {
		TemplateRenders.renderClassTemplateVarsEntries(baseDir + "TBiConsumers.stg", "TBiConsumers", importsMapper,
				getBiPrimitiveTemplateInfos("BiConsumers", "$Type1$$Type2$", primitiveFuncTypes, null, 2, genericParams, twoTypeReturnNameConverter));
	}


	public static void generatePrimitiveFunctions() {
		TemplateRenders.renderClassTemplateVarsEntries(baseDir + "TFunctions.stg", "TFunctions", importsMapper,
				getBiPrimitiveTemplateInfos("Functions", "$Type1$Return$ReturnType$", primitiveAnd1GenericFuncTypes, primitiveAndReturnFuncTypes, 1, genericParams, twoTypeReturnNameConverter));
	}


	public static void generatePrimitiveBiFunctions() {
		TemplateRenders.renderClassTemplateVarsEntries(baseDir + "TBiFunctions.stg", "TBiFunctions", importsMapper,
				getBiPrimitiveTemplateInfos("BiFunctions", "$Type1$$Type2$Return$ReturnType$", primitiveFuncTypes, primitiveAndReturnFuncTypes, 2, genericParams, twoTypeReturnNameConverter));
	}


	public static List<Map.Entry<ClassInfo, FunctionTypeTmpl>> getPrimitiveTemplateInfos(String classNameTmpl, List<? extends FuncClassInfo> typeEntries,
			List<? extends ClassTemplateBuilder<? extends ClassTemplate>> tmplBuilders,
			TriFunc<GenericTypeInfo, GenericTypeInfo, String, String> converter, boolean includeString, boolean includeEnum, List<GenericTypeInfo> types, HasTypeName returnType) {
		List<Map.Entry<ClassInfo, FunctionTypeTmpl>> tmpls = new ArrayList<>();

		int i = 0;
		for(FuncClassInfo classInfo : typeEntries) {
			GenericTypeInfo t1 = types.get(0) != null ? types.get(0) : classInfo.typeInfo;
			GenericTypeInfo t2 = types.get(1) != null ? types.get(1) : classInfo.typeInfo;
			GenericTypeInfo t3 = types.get(2);
			GenericTypeInfo t4 = types.get(3);
			BiFunction<ClassTemplate, String, String> convertFunc = (clsTmpl, str) -> converter.apply(t1, t2, str);
			ClassTemplateBuilder<? extends ClassTemplate> tmplBldr = (tmplBuilders != null ? tmplBuilders.get(i) : ClassTemplateBuilder.of(classNameTmpl)).setPackageName("twg2.functions");
			for(GenericTypeInfo t : types) {
				if(t != null) {
					tmplBldr.addTypeParameter(t.getType(), t.getType());
				}
			}
			if(returnType != null) {
				tmplBldr.addTypeParameter(returnType.getType(), returnType.getType());
			}

			ClassTemplate clsTmpl = TemplateNames.inferClassNames(tmplBldr.getTemplate(), convertFunc);
			FunctionTypeTmpl funcTmpl = new FunctionTypeTmpl(clsTmpl)
				.setOverride(classInfo.isOverride).setType1(t1).setType2(t2).setType3(t3).setType4(t4).setTypeReturn(returnType);

			tmpls.add(new AbstractMap.SimpleImmutableEntry<>(funcTmpl.classInfo, funcTmpl));
			i++;
		}

		if(includeString) {
			ClassTemplateBuilder<PrimitiveTypeClassTemplate> stringInfo = new ClassTemplateBuilder<>(new PrimitiveTypeClassTemplate(), classNameTmpl, "twg2.functions");
			stringInfo.getTemplate().type = "String";
			stringInfo.getTemplate().typeShort = "String";
			stringInfo.getTemplate().typeShortTitleCase = "String";
			stringInfo.addImportStatement(ThreadPoolExecutor.class);
			ClassTemplate clsTmpl = TemplateNames.inferClassNames(stringInfo.getTemplate(), (clsInfo, str) -> oneTypeNameConverter.apply(stringTmpl, str));
			FunctionTypeTmpl funcTmpl = new FunctionTypeTmpl(clsTmpl).setType1(objectTmpl);
			tmpls.add( new AbstractMap.SimpleImmutableEntry<>(funcTmpl.classInfo, funcTmpl) );
		}

		if(includeEnum) {
			ClassTemplateBuilder<PrimitiveTypeClassTemplate> enumInfo = new ClassTemplateBuilder<>(new PrimitiveTypeClassTemplate(), classNameTmpl, "twg2.functions");
			enumInfo.addTypeParameter("T", "T extends Enum<T>");
			enumInfo.getTemplate().type = "T";
			enumInfo.getTemplate().typeShort = "Enum";
			enumInfo.getTemplate().typeShortTitleCase = "Enum";
			ClassTemplate clsTmpl = TemplateNames.inferClassNames(enumInfo.getTemplate(), (clsInfo, str) -> oneTypeNameConverter.apply(enumTmpl, str));
			FunctionTypeTmpl funcTmpl = new FunctionTypeTmpl(clsTmpl).setType1(genericEnumParam);
			tmpls.add( new AbstractMap.SimpleImmutableEntry<>(funcTmpl.classInfo, funcTmpl) );
		}

		return tmpls;
	}


	public static List<Map.Entry<ClassInfo, Map<String, Object>>> getBiPrimitiveTemplateInfos(String containerClassName, String classNameTmpl,
			List<? extends GenericTypeInfo> types, List<? extends GenericTypeInfo> returnTypes, int paramCount, List<? extends GenericTypeInfo> genericParams, QuadFunc<GenericTypeInfo, GenericTypeInfo, GenericTypeInfo, String, String> converter) {
		String packageName = "twg2.functions";

		boolean hasReturnType = returnTypes != null && returnTypes.size() > 0;

		ClassTemplate globalClassInfo = ClassTemplateBuilder.of(containerClassName, packageName).getTemplate();

		List<FunctionTypeTmpl> combos = new ArrayList<>();
		// unused
		List<String> classNames = new ArrayList<>();

		QuinConsumer<GenericTypeInfo, GenericTypeInfo, GenericTypeInfo, GenericTypeInfo, GenericTypeInfo> addFuncImpls = (param1, param2, param3, param4, retType) -> {
			int genericParamCount = (isGeneric(param1) ? 1 : 0) + (isGeneric(param2) ? 1 : 0) + (isGeneric(param3) ? 1 : 0) + (isGeneric(param4) ? 1 : 0);
			List<Map.Entry<String, String>> classParams = new ArrayList<>();
			for(int i = 0; i < genericParamCount; i++) {
				GenericTypeInfo param = genericParams.get(i);
				addIf(param, classParams);
			}
			addIf(retType, classParams);

			// for each primitive type combo, generate two interfaces: 1 with the primitive type first arg and 1 with the primitive type as the second arg
			ClassTemplate classInfo1 = ClassTemplateBuilder.of(classNameTmpl, packageName).addTypeParameters(classParams).getTemplate();
			TemplateNames.inferClassNames(classInfo1, (clsInfo, str) -> converter.apply(param1, param2, retType, str));
			combos.add(new FunctionTypeTmpl(classInfo1).setType1(param1).setType2(param2).setType3(param3).setType4(param4).setTypeReturn(retType));
			classNames.add(classInfo1.getClassName());
		};

		for(GenericTypeInfo param1 : types) {
			if(hasReturnType) {
				for(GenericTypeInfo returnType : returnTypes) {
					if(paramCount > 1) {
						addFuncImpls.accept(param1, genericParams.get(0), null, null, returnType);
						addFuncImpls.accept(genericParams.get(0), param1, null, null, returnType);
					}
					else {
						addFuncImpls.accept(param1, null, null, null, returnType);
					}
				}
			}
			else {
				if(paramCount > 1) {
					addFuncImpls.accept(param1, genericParams.get(0), null, null, null);
					addFuncImpls.accept(genericParams.get(0), param1, null, null, null);
				}
				else {
					addFuncImpls.accept(param1, null, null, null, null);
				}
			}
		}

		Map<String, Object> args = new HashMap<>();
		args.put("classInfo", globalClassInfo);
		args.put("types", combos);

		List<Map.Entry<ClassInfo, Map<String, Object>>> tmpls = new ArrayList<>();
		tmpls.add(new AbstractMap.SimpleImmutableEntry<>(globalClassInfo, args));

		return tmpls;
	}


	public static boolean isGeneric(GenericTypeInfo obj) {
		return obj != null && obj.isGeneric();
	}


	public static boolean addIf(GenericTypeInfo obj, List<? super Map.Entry<String, String>> dst) {
		if(isGeneric(obj)) {
			dst.add(pair(obj.getType(), obj.getType()));
			return true;
		}
		return false;
	}


	public static <K, V> Map.Entry<K, V> pair(K key, V value) {
		return new AbstractMap.SimpleImmutableEntry<>(key, value);
	}


	public static void main(String[] args) throws IOException {
		generatePrimitivePredicates();
		generatePrimitiveSuppliers();
		generatePrimitiveConsumers();
		generatePrimitiveFunctions();
		generatePrimitiveBiConsumers();
		generatePrimitiveBiFunctions();
	}

}