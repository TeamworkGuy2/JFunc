package templates.generators;

import static codeTemplate.PrimitiveTemplates.inferPropertyNames;
import static codeTemplate.PrimitiveTemplates.newByteTemplate;
import static codeTemplate.PrimitiveTemplates.newCharTemplate;
import static codeTemplate.PrimitiveTemplates.newFloatTemplate;
import static codeTemplate.PrimitiveTemplates.newShortTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import codeTemplate.ClassTemplateBuilder;
import codeTemplate.PrimitiveClassTemplate;
import codeTemplate.TemplateUtil;

/**
 * @author TeamworkGuy2
 * @since 2015-1-23
 */
public class GeneratePredicates {


	public static void generatePrimitivePredicates() {
		TemplateUtil.renderTemplates("src/templates/TPredicate.stg", "TPredicate", getPrimitiveTemplateInfos("$Type$Predicate", true, true));
	}


	public static void generatePrimitiveConsumers() {
		TemplateUtil.renderTemplates("src/templates/TConsumer.stg", "TConsumer", getPrimitiveTemplateInfos("$Type$Consumer", false, false));		
	}


	public static void generatePrimitiveSuppliers() {
		TemplateUtil.renderTemplates("src/templates/TSupplier.stg", "TSupplier", getPrimitiveTemplateInfos("$Type$Supplier", false, false));
	}


	public static List<PrimitiveClassTemplate> getPrimitiveTemplateInfos(String classNameTmpl, boolean includeString, boolean includeEnum) {
		List<PrimitiveClassTemplate> tmpls = new ArrayList<>();
		tmpls.add( inferPropertyNames(newByteTemplate(new PrimitiveClassTemplate(), classNameTmpl, "functionUtils")) );
		tmpls.add( inferPropertyNames(newCharTemplate(new PrimitiveClassTemplate(), classNameTmpl, "functionUtils")) );
		tmpls.add( inferPropertyNames(newShortTemplate(new PrimitiveClassTemplate(), classNameTmpl, "functionUtils")) );
		tmpls.add( inferPropertyNames(newFloatTemplate(new PrimitiveClassTemplate(), classNameTmpl, "functionUtils")) );

		if(includeString) {
			ClassTemplateBuilder<PrimitiveClassTemplate> stringInfo = new ClassTemplateBuilder<>(new PrimitiveClassTemplate(), classNameTmpl, "functionUtils");
			stringInfo.getTemplate().type = "String";
			stringInfo.getTemplate().typeShort = "String";
			stringInfo.getTemplate().typeShortUpperCase = "String";
			stringInfo.addImportStatement(ThreadPoolExecutor.class);
			tmpls.add( inferPropertyNames(stringInfo.getTemplate()) );
		}

		if(includeEnum) {
			ClassTemplateBuilder<PrimitiveClassTemplate> enumInfo = new ClassTemplateBuilder<>(new PrimitiveClassTemplate(), classNameTmpl, "functionUtils");
			enumInfo.addTypeParameter("T", "T extends Enum<T>");
			enumInfo.getTemplate().type = "T";
			enumInfo.getTemplate().typeShort = "Enum";
			enumInfo.getTemplate().typeShortUpperCase = "Enum";
			tmpls.add( inferPropertyNames(enumInfo.getTemplate()) );
		}

		return tmpls;
	}


	public static void main(String[] args) throws IOException {
		generatePrimitivePredicates();
		generatePrimitiveSuppliers();
		generatePrimitiveConsumers();
	}

}
