package de.uka.ipd.sdq.ByCounter.test.helpers;

/**
 * Class that references a different class that can be defined as external
 * in the instrumenting code.
 * 
 * @author Martin Krogmann
 *
 */
public class ReferenceExternalClass {
	
	/**
	 * Reference to a {@link TestSubject} instance.
	 */
	public TestSubject subject;

	/**
	 * Initialize {@link #subject}.
	 */
	public ReferenceExternalClass() {
		subject = new TestSubject();
	}
	
	public TestSubject execute() {
		for(int i = 0; i < 2; i++) {
			subject.printTest();
		}
		return subject;
	}
}
