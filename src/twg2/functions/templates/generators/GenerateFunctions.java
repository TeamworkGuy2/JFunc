package twg2.functions.templates.generators;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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

import org.stringtemplate.v4.ST;

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
import twg2.template.codeTemplate.render.STTemplates;
import twg2.template.codeTemplate.render.StringTemplatesUtil;
import twg2.template.codeTemplate.render.TemplateImports;

/**
 * @author TeamworkGuy2
 * @since 2015-1-23
 */
public class GenerateFunctions {

	@FunctionalInterface
	public static interface TransformFuncSignature {
		public String format(GenericTypeInfo type1, GenericTypeInfo type2, GenericTypeInfo type3, GenericTypeInfo type4, GenericTypeInfo returnType, String templateStr);
	}


	@FunctionalInterface
	public static interface ConsumeFuncSignature {
		public void accept(GenericTypeInfo type1, GenericTypeInfo type2, GenericTypeInfo type3, GenericTypeInfo type4, GenericTypeInfo returnType);
	}


	static class FuncClassInfo {
		GenericTypeInfo typeInfo;
		boolean isOverride;

		public FuncClassInfo(GenericTypeInfo typeInfo, boolean isOverride) {
			this.typeInfo = typeInfo;
			this.isOverride = isOverride;
		}

	}


	private static String tmplDir = "src/twg2/functions/templates/";
	private static String pkgName = "twg2.functions";
	private static TemplateImports importsMapper = TemplateImports.fromCommonTemplateFiles("$commonTemplates.JavaClass$", true);

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

	private static TransformFuncSignature funcSignatureConverter = (type1, type2, type3, type4, typeReturn, str) -> {
		return str
				.replace("$Type1$", type1 == null || type1.isGeneric() ? "Object" : type1.getTypeShortTitleCase())
				.replace("$Type2$", type2 == null || type2.isGeneric() ? "Object" : type2.getTypeShortTitleCase())
				.replace("$Type3$", type3 == null || type3.isGeneric() ? "Object" : type3.getTypeShortTitleCase())
				.replace("$Type4$", type4 == null || type4.isGeneric() ? "Object" : type4.getTypeShortTitleCase())
				.replace("$ReturnType$", typeReturn == null || typeReturn.isGeneric() ? "Object" : typeReturn.getTypeShortTitleCase());
	};


	public static void generatePrimitivePredicates() {
		List<FunctionTypeTmpl> tmpls = TemplateInfosBuilder
				.pkgAndClassNameTemplate(pkgName, "$Type1$")
				.getPrimitiveTemplateInfos(oldArgFuncTypes, predicateImplementTmplBldrs.apply("$Type1$"), funcSignatureConverter, true, true, Arrays.asList(null, null, null, null), null);
		ClassInfo clsInfo = ClassTemplateBuilder.of("Predicates", pkgName).getTemplate();
		ST stTmpl = STTemplates.fromFile(tmplDir + "TPredicate.stg", "TPredicate", importsMapper);
		renderFuncTmpls(stTmpl, clsInfo, tmpls);
	}


	public static void generatePrimitiveConsumers() {
		List<FunctionTypeTmpl> tmpls = TemplateInfosBuilder
				.pkgAndClassNameTemplate(pkgName, "$Type1$")
				.getPrimitiveTemplateInfos(oldArgFuncTypes, consumerImplementTmplBldrs.apply("$Type1$"), funcSignatureConverter, false, false, Arrays.asList(null, null, null, null), null);
		ClassInfo clsInfo = ClassTemplateBuilder.of("Consumers", pkgName).getTemplate();
		ST stTmpl = STTemplates.fromFile(tmplDir + "TConsumer.stg", "TConsumer", importsMapper);
		renderFuncTmpls(stTmpl, clsInfo, tmpls);
	}


	public static void generatePrimitiveSuppliers() {
		List<FunctionTypeTmpl> tmpls = TemplateInfosBuilder
				.pkgAndClassNameTemplate(pkgName, "$Type1$")
				.getPrimitiveTemplateInfos(oldArgFuncTypes, supplierImplementTmplBldrs.apply("$Type1$"), funcSignatureConverter, false, false, Arrays.asList(null, null, null, null), null);
		ClassInfo clsInfo = ClassTemplateBuilder.of("Suppliers", pkgName).getTemplate();
		ST stTmpl = STTemplates.fromFile(tmplDir + "TSupplier.stg", "TSupplier", importsMapper);
		renderFuncTmpls(stTmpl, clsInfo, tmpls);
	}


	public static void generatePrimitiveFunctions() {
		List<FunctionTypeTmpl> tmpls = TemplateInfosBuilder
				.pkgAndClassNameTemplate(pkgName, "$Type1$Return$ReturnType$")
				.getBiPrimitiveTemplateInfos(primitiveAnd1GenericFuncTypes, primitiveAndReturnFuncTypes, false, 1, genericParams, funcSignatureConverter);
		ClassInfo clsInfo = ClassTemplateBuilder.of("Functions", pkgName).getTemplate();
		ST stTmpl = STTemplates.fromFile(tmplDir + "TFunctions.stg", "TFunctions", importsMapper);
		renderFuncTmpls(stTmpl, clsInfo, tmpls);
	}


	// Bi-functions
	public static void generatePrimitiveBiConsumers() {
		List<FunctionTypeTmpl> tmpls = TemplateInfosBuilder
				.pkgAndClassNameTemplate(pkgName, "$Type1$$Type2$")
				.getBiPrimitiveTemplateInfos(primitiveFuncTypes, null, false, 2, genericParams, funcSignatureConverter);
		ClassInfo clsInfo = ClassTemplateBuilder.of("BiConsumers", pkgName).getTemplate();
		ST stTmpl = STTemplates.fromFile(tmplDir + "TBiConsumers.stg", "TBiConsumers", importsMapper);
		renderFuncTmpls(stTmpl, clsInfo, tmpls);
	}


	public static void generatePrimitiveBiFunctions() {
		List<FunctionTypeTmpl> tmpls = TemplateInfosBuilder
				.pkgAndClassNameTemplate(pkgName, "$Type1$$Type2$Return$ReturnType$")
				.getBiPrimitiveTemplateInfos(primitiveFuncTypes, primitiveAndReturnFuncTypes, false, 2, genericParams, funcSignatureConverter);
		ClassInfo clsInfo = ClassTemplateBuilder.of("BiFunctions", pkgName).getTemplate();
		ST stTmpl = STTemplates.fromFile(tmplDir + "TBiFunctions.stg", "TBiFunctions", importsMapper);
		renderFuncTmpls(stTmpl, clsInfo, tmpls);
	}


	public static void generatePrimitiveBiPredicates() {
		List<FunctionTypeTmpl> tmpls = TemplateInfosBuilder
				.pkgAndClassNameTemplate(pkgName, "$Type1$$Type2$")
				.getBiPrimitiveTemplateInfos(primitiveFuncTypes, primitiveAndReturnFuncTypes, true, 2, genericParams, funcSignatureConverter);
		ClassInfo clsInfo = ClassTemplateBuilder.of("BiPredicates", pkgName).getTemplate();
		ST stTmpl = STTemplates.fromFile(tmplDir + "TBiPredicate.stg", "TBiPredicate", importsMapper);
		renderFuncTmpls(stTmpl, clsInfo, tmpls);
	}


	public static void renderFuncTmpls(ST stTmpl, ClassInfo classInfo, List<FunctionTypeTmpl> subTemplates) {
		Map<String, Object> args = new HashMap<>();
		args.put("classInfo", classInfo);
		args.put("types", subTemplates);
		StringTemplatesUtil.renderClassArgs(stTmpl, classInfo, args);
	}



	public static class TemplateInfosBuilder {
		private String packageName;
		private String classNameTmpl;


		public List<FunctionTypeTmpl> getPrimitiveTemplateInfos(List<? extends FuncClassInfo> typeEntries,
				List<? extends ClassTemplateBuilder<? extends ClassTemplate>> tmplBuilders,
				TransformFuncSignature converter, boolean includeString, boolean includeEnum, List<GenericTypeInfo> types, HasTypeName returnType) {
			List<FunctionTypeTmpl> tmpls = new ArrayList<>();

			int i = 0;
			for(FuncClassInfo classInfo : typeEntries) {
				GenericTypeInfo t1 = types.get(0) != null ? types.get(0) : classInfo.typeInfo;
				GenericTypeInfo t2 = types.get(1) != null ? types.get(1) : classInfo.typeInfo;
				GenericTypeInfo t3 = types.get(2);
				GenericTypeInfo t4 = types.get(3);
				BiFunction<ClassTemplate, String, String> convertFunc = (clsTmpl, str) -> converter.format(t1, t2, t3, t4, null, str);
				ClassTemplateBuilder<? extends ClassTemplate> tmplBldr = (tmplBuilders != null ? tmplBuilders.get(i) : ClassTemplateBuilder.of(classNameTmpl)).setPackageName(packageName);
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

				tmpls.add(funcTmpl);
				i++;
			}

			if(includeString) {
				ClassTemplateBuilder<PrimitiveTypeClassTemplate> stringInfo = new ClassTemplateBuilder<>(new PrimitiveTypeClassTemplate(), classNameTmpl, packageName);
				stringInfo.getTemplate().type = "String";
				stringInfo.getTemplate().typeShort = "String";
				stringInfo.getTemplate().typeShortTitleCase = "String";
				stringInfo.addImportStatement(ThreadPoolExecutor.class);
				ClassTemplate clsTmpl = TemplateNames.inferClassNames(stringInfo.getTemplate(), (clsInfo, str) -> funcSignatureConverter.format(stringTmpl, null, null, null, null, str));
				FunctionTypeTmpl funcTmpl = new FunctionTypeTmpl(clsTmpl).setType1(objectTmpl);

				tmpls.add(funcTmpl);
			}

			if(includeEnum) {
				ClassTemplateBuilder<PrimitiveTypeClassTemplate> enumInfo = new ClassTemplateBuilder<>(new PrimitiveTypeClassTemplate(), classNameTmpl, packageName);
				enumInfo.addTypeParameter("T", "T extends Enum<T>");
				enumInfo.getTemplate().type = "T";
				enumInfo.getTemplate().typeShort = "Enum";
				enumInfo.getTemplate().typeShortTitleCase = "Enum";
				ClassTemplate clsTmpl = TemplateNames.inferClassNames(enumInfo.getTemplate(), (clsInfo, str) -> funcSignatureConverter.format(enumTmpl, null, null, null, null, str));
				FunctionTypeTmpl funcTmpl = new FunctionTypeTmpl(clsTmpl).setType1(genericEnumParam);

				tmpls.add(funcTmpl);
			}

			return tmpls;
		}


		public List<FunctionTypeTmpl> getBiPrimitiveTemplateInfos(List<? extends GenericTypeInfo> types, List<? extends GenericTypeInfo> returnTypes, boolean staticReturnType, int paramCount,
				List<? extends GenericTypeInfo> genericParams, TransformFuncSignature converter) {
			boolean hasReturnType = returnTypes != null && returnTypes.size() > 0 && !staticReturnType;

			List<FunctionTypeTmpl> combos = new ArrayList<>();
			// unused
			List<String> classNames = new ArrayList<>();

			ConsumeFuncSignature addFuncImpls = (param1, param2, param3, param4, retType) -> {
				int genericParamCount = (isGeneric(param1) ? 1 : 0) + (isGeneric(param2) ? 1 : 0) + (isGeneric(param3) ? 1 : 0) + (isGeneric(param4) ? 1 : 0);
				List<Entry<String, String>> classParams = new ArrayList<>();
				for(int i = 0; i < genericParamCount; i++) {
					GenericTypeInfo param = genericParams.get(i);
					addIf(param, classParams);
				}
				addIf(retType, classParams);

				// for each primitive type combo, generate two interfaces: 1 with the primitive type first arg and 1 with the primitive type as the second arg
				ClassTemplate classInfo1 = ClassTemplateBuilder.of(classNameTmpl, packageName).addTypeParameters(classParams).getTemplate();
				TemplateNames.inferClassNames(classInfo1, (clsInfo, str) -> converter.format(param1, param2, param3, param4, retType, str));
				combos.add(new FunctionTypeTmpl(classInfo1).setType1(param1).setType2(param2).setType3(param3).setType4(param4).setTypeReturn(retType));
				classNames.add(classInfo1.getClassName());
			};

			GenericTypeInfo returnType0 = returnTypes != null && returnTypes.size() > 0 && staticReturnType ? returnTypes.get(0) : null;

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
						addFuncImpls.accept(param1, genericParams.get(0), null, null, returnType0);
						addFuncImpls.accept(genericParams.get(0), param1, null, null, returnType0);
					}
					else {
						addFuncImpls.accept(param1, null, null, null, returnType0);
					}
				}
			}

			return combos;
		}


		public static TemplateInfosBuilder pkgAndClassNameTemplate(String pkgName, String classNameTmpl) {
			TemplateInfosBuilder inst = new TemplateInfosBuilder();
			inst.classNameTmpl = classNameTmpl;
			inst.packageName = pkgName;
			return inst;
		}

	}




	public static boolean isGeneric(GenericTypeInfo obj) {
		return obj != null && obj.isGeneric();
	}


	public static boolean addIf(GenericTypeInfo obj, List<? super Entry<String, String>> dst) {
		if(isGeneric(obj)) {
			dst.add(pair(obj.getType(), obj.getType()));
			return true;
		}
		return false;
	}


	public static <K, V> Entry<K, V> pair(K key, V value) {
		return new AbstractMap.SimpleImmutableEntry<>(key, value);
	}


	public static void main(String[] args) throws IOException {
		//TemplateFilesIo.setGlobalWriteFilesFlag(false);
		generatePrimitivePredicates();
		generatePrimitiveSuppliers();
		generatePrimitiveConsumers();
		generatePrimitiveFunctions();
		generatePrimitiveBiPredicates();
		generatePrimitiveBiConsumers();
		//generatePrimitiveBiFunctions();
	}

}
