/**
 * 
 */
package de.fzi.se.bycountertest;

/**Implementation used to test Loops (containing an external action).
 * @author groenda
 * 
 * 
 */
public class TestLoopExternalActionStackOverflow implements ISimple {
	/**Connected external component.
	 */
	protected ISimple requiredComponent = new DoNothing();

	/**
	 * @param requiredComponent the requiredComponent to set
	 */
	public void setRequiredComponent(ISimple requiredComponent) {
		this.requiredComponent = requiredComponent;
	}

	/* (non-Javadoc)
	 * @see de.fzi.se.validation.tests.ISimple#process()
	 */
	@Override
	public void process() {
		for (	int i = 0; 
				i < 5; 
				i++) {
			requiredComponent.process();
		}
	}

}
