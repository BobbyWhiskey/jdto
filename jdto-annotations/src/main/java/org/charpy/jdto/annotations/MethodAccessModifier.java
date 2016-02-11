package org.charpy.jdto.annotations;

import com.sun.codemodel.JMod;

public enum MethodAccessModifier {
	Public(JMod.PUBLIC), Protected(JMod.PROTECTED), Private(JMod.PRIVATE);

	private int jmodConstant;

	private MethodAccessModifier(int jmodConstant) {
		this.jmodConstant = jmodConstant;
	}
	
	public int getJModConstant(){
		return jmodConstant;
	}
	
	@Override
	public String toString() {
		return this.name();
	}
	
	public static MethodAccessModifier fromQDoxAnnotationString(String str){
		return MethodAccessModifier.valueOf(str.substring(str.lastIndexOf(".")+1));
	}

}
