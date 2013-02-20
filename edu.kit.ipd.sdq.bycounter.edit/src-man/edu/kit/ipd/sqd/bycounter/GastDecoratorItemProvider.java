package edu.kit.ipd.sqd.bycounter;
/**
 * 
 */


import org.eclipse.emf.edit.provider.DecoratorAdapterFactory;

import de.fzi.gast.accesses.FunctionAccess;
import de.fzi.gast.core.Position;
import de.fzi.gast.functions.Function;
import de.fzi.gast.functions.Method;
import de.fzi.gast.functions.util.functionsSwitch;
import de.fzi.gast.statements.Statement;
import de.fzi.gast.variables.FormalParameter;
import de.uka.ipd.sdq.pcmbench.ui.provider.PalladioItemProvider;

/**Decorator for GAST model elements.
 * @author groenda
 *
 */
public class GastDecoratorItemProvider extends PalladioItemProvider {

	public GastDecoratorItemProvider(DecoratorAdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public String getText(Object object) {
		StringBuilder result = new StringBuilder();
		if (object instanceof Position) {
			Position pos = (Position) object;
			if (pos.getSourceFile() != null) {
				result.append(pos.getSourceFile().getSimpleName() + ": ");
			}
			result.append("(" + pos.getStartLine() +":" + pos.getStartColumn() + " - " + pos.getEndLine() + ":" + pos.getEndColumn() + ")");
		}
		if (object instanceof Statement) {
			Statement stmt = (Statement) object;
			if (stmt.getPosition() != null) {
				result.append(getText(stmt.getPosition()) + ": ");
			}
			result.append(((Statement) object).eClass().getName());
		}
		if (object instanceof FunctionAccess) {
			FunctionAccess fa = (FunctionAccess) object;
			if (fa.getAccessedClass() != null && fa.getAccessedClass().getQualifiedName() != null) {
				result.append(fa.getAccessedClass().getQualifiedName());
			}
			if (fa.getTargetFunction() != null) {
				result.append("::" + fa.getTargetFunction().getSimpleName()+ "(");
				FormalParameter param;
				for (int i=0; i < fa.getTargetFunction().getFormalParameters().size(); i++) {
					param = fa.getTargetFunction().getFormalParameters().get(i);
					result.append(param.getType().getSimpleName() + " " + param.getSimpleName());
					if (i < fa.getTargetFunction().getFormalParameters().size()-1) {
						result.append(",");
					}
				}
				result.append(")");
			}
			if (fa.getPosition() != null) {
				result.append(" in " + getText(fa.getPosition()));
			}
		}
		if (object instanceof Function) {
			Function f = (Function) object;
			result.append(new functionsSwitch<String>(){
				public String caseConstructor(de.fzi.gast.functions.Constructor object) {
					return object.getSurroundingClass().getQualifiedName();
				};
				public String caseDestructor(de.fzi.gast.functions.Destructor object) {
					return object.getSurroundingClass().getQualifiedName();
				};
				public String caseDelegate(de.fzi.gast.functions.Delegate object) {
					return object.getSurroundingClass().getQualifiedName();
				};
				public String caseGlobalFunction(de.fzi.gast.functions.GlobalFunction object) {
					return "";
				};
				public String caseMethod(Method object) {
					if (object.getSurroundingClass()!= null) {
						return object.getSurroundingClass().getQualifiedName();
					} else {
						return "<null>";
					}
				};
			}.doSwitch(f));
			result.append("::" + f.getSimpleName()+ "(");
			FormalParameter param;
			for (int i=0; i < f.getFormalParameters().size(); i++) {
				param = f.getFormalParameters().get(i);
				result.append(param.getType().getSimpleName() + " " + param.getSimpleName());
				if (i < f.getFormalParameters().size()-1) {
					result.append(",");
				}
			}
			result.append(")");
		}
		if (object instanceof FormalParameter) {
			FormalParameter p = (FormalParameter) object;
			result.append(getText(p.getSurroundingFunction()) + ": " + p.getSimpleName());
		}
		if (result.length() == 0) {
			result.append(super.getText(object));
		}
		return result.toString();
	}
}
