package twg2.functions.templates;

import twg2.template.codeTemplate.ClassInfo;
import twg2.template.codeTemplate.HasTypeName;

/**
 * @author TeamworkGuy2
 * @since 2015-8-24
 */
public class FunctionTypeTmpl {
	public ClassInfo classInfo;
	public boolean override;
	public HasTypeName type1;
	public HasTypeName type2;
	public HasTypeName type3;
	public HasTypeName type4;
	public HasTypeName typeReturn;


	public FunctionTypeTmpl(ClassInfo type) {
		this.classInfo = type;
	}


	public FunctionTypeTmpl copy() {
		FunctionTypeTmpl copy = new FunctionTypeTmpl(this.classInfo);
		copy.override = this.override;
		copy.type1 = this.type1;
		copy.type2 = this.type2;
		copy.type3 = this.type3;
		copy.type4 = this.type4;
		copy.typeReturn = this.typeReturn;
		return copy;
	}


	public FunctionTypeTmpl setOverride(boolean override) {
		this.override = override;
		return this;
	}


	public FunctionTypeTmpl setType1(HasTypeName type1) {
		this.type1 = type1;
		return this;
	}

	public FunctionTypeTmpl setType2(HasTypeName type2) {
		this.type2 = type2;
		return this;
	}

	public FunctionTypeTmpl setType3(HasTypeName type3) {
		this.type3 = type3;
		return this;
	}

	public FunctionTypeTmpl setType4(HasTypeName type4) {
		this.type4 = type4;
		return this;
	}

	public FunctionTypeTmpl setTypeReturn(HasTypeName typeRet) {
		this.typeReturn = typeRet;
		return this;
	}
}
