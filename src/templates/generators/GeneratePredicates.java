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
import codeTemplate.PrimitiveClassTemplateDeprecated;
import codeTemplate.TemplateRender;

/**
 * @author TeamworkGuy2
 * @since 2015-1-23
 */
public class GeneratePredicates {


	public static void generatePrimitivePredicates() {
		TemplateRender.renderClassTemplates("src/templates/TPredicate.stg", "TPredicate", getPrimitiveTemplateInfos("$Type$Predicate", true, true));
	}


	public static void generatePrimitiveConsumers() {
		TemplateRender.renderClassTemplates("src/templates/TConsumer.stg", "TConsumer", getPrimitiveTemplateInfos("$Type$Consumer", false, false));		
	}


	public static void generatePrimitiveSuppliers() {
		TemplateRender.renderClassTemplates("src/templates/TSupplier.stg", "TSupplier", getPrimitiveTemplateInfos("$Type$Supplier", false, false));
	}


	public static List<PrimitiveClassTemplateDeprecated> getPrimitiveTemplateInfos(String classNameTmpl, boolean includeString, boolean includeEnum) {
		List<PrimitiveClassTemplateDeprecated> tmpls = new ArrayList<>();
		tmpls.add( inferPropertyNames(newByteTemplate(new PrimitiveClassTemplateDeprecated(), classNameTmpl, "functionUtils")) );
		tmpls.add( inferPropertyNames(newCharTemplate(new PrimitiveClassTemplateDeprecated(), classNameTmpl, "functionUtils")) );
		tmpls.add( inferPropertyNames(newShortTemplate(new PrimitiveClassTemplateDeprecated(), classNameTmpl, "functionUtils")) );
		tmpls.add( inferPropertyNames(newFloatTemplate(new PrimitiveClassTemplateDeprecated(), classNameTmpl, "functionUtils")) );

		if(includeString) {
			ClassTemplateBuilder<PrimitiveClassTemplateDeprecated> stringInfo = new ClassTemplateBuilder<>(new PrimitiveClassTemplateDeprecated(), classNameTmpl, "functionUtils");
			stringInfo.getTemplate().type = "String";
			stringInfo.getTemplate().typeShort = "String";
			stringInfo.getTemplate().typeShortTitleCase = "String";
			stringInfo.addImportStatement(ThreadPoolExecutor.class);
			tmpls.add( inferPropertyNames(stringInfo.getTemplate()) );
		}

		if(includeEnum) {
			ClassTemplateBuilder<PrimitiveClassTemplateDeprecated> enumInfo = new ClassTemplateBuilder<>(new PrimitiveClassTemplateDeprecated(), classNameTmpl, "functionUtils");
			enumInfo.addTypeParameter("T", "T extends Enum<T>");
			enumInfo.getTemplate().type = "T";
			enumInfo.getTemplate().typeShort = "Enum";
			enumInfo.getTemplate().typeShortTitleCase = "Enum";
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
