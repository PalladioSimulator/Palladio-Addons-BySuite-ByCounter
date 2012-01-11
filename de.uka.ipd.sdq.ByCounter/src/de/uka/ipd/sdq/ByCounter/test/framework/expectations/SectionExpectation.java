package de.uka.ipd.sdq.ByCounter.test.framework.expectations;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import de.uka.ipd.sdq.ByCounter.parsing.LineNumberRange;
import de.uka.ipd.sdq.ByCounter.utils.FullOpcodeMapper;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;

/**
 * Defines that a section is expected. It also defines what is inside it.
 * 
 * @author Florian Schreier
 */
public class SectionExpectation {

	/**
	 * This section's number.
	 */
	private final int sectionNumber;

	/**
	 * This section's line number range.
	 */
	private final LineNumberRange range;

	/**
	 * All expected opcodes. The key stands for the opcode number as specified in
	 * {@link Opcodes}, the value stands for the expected count number.
	 */
	private final Map<Integer, Long> opcodeExpectations;

	/**
	 * All expected method calls. The key stands for the method's name, the value
	 * stands for the expected count number.
	 */
	private final Map<String, Long> methodCallExpectations;

	/**
	 * Creates a new selection expectation.
	 * 
	 * @param sectionNumber
	 *          The new section's number. Has to be greater or equal zero.
	 * @param range
	 *          The line number range of the new section. If the range is unknown,
	 *          this should be <code>null</code>.
	 */
	protected SectionExpectation(final int sectionNumber, final LineNumberRange range) {
		assert sectionNumber >= 0 : "sectionNumber has to be greater or equal zero.";

		this.sectionNumber = sectionNumber;
		this.range = range;
		this.opcodeExpectations = new HashMap<Integer, Long>();
		this.methodCallExpectations = new HashMap<String, Long>();
	}

	/**
	 * Returns this section's number.
	 * 
	 * @return This section's number.
	 */
	protected int getSectionNumber() {
		return this.sectionNumber;
	}

	/**
	 * Returns this section's line number range.
	 * 
	 * @return This section's line number range if it is known. Otherwise
	 *         <code>null</code>.
	 */
	protected LineNumberRange getRange() {
		return this.range;
	}

	/**
	 * Adds an opcode expectation to this section. Throws an
	 * {@link IllegalArgumentException} if you want to add an opcode twice.
	 * 
	 * @param opcode
	 *          An integer value representing an opcode as specified in
	 *          {@link Opcodes}.
	 * @param number
	 *          The expected number (How often this opcode should be counted.).
	 *          Has to be greater than zero.
	 * @return This {@link SectionExpectation} object.
	 */
	public SectionExpectation add(final int opcode, final long number) {
		if (this.opcodeExpectations.containsKey(opcode)) {
			throw new IllegalArgumentException("Cannot add Opcode #" + opcode + " twice.");
		}
		if (number <= 0) {
			throw new IllegalArgumentException("The value of number has to be greater than zero.");
		}
		this.opcodeExpectations.put(opcode, number);
		return this;
	}

	/**
	 * Adds a method call expectation to this section. Throws an
	 * {@link IllegalArgumentException} if you want to add a method call twice.
	 * 
	 * @param bytecodeDescriptor
	 *          A String representing a method as used in Java Bytecode.
	 * @param number
	 *          The expected number (How often this opcode should be counted.).
	 *          Has to be greater than zero.
	 * @return This {@link SectionExpectation} object.
	 */
	public SectionExpectation add(final String bytecodeDescriptor, final long number) {
		if (this.opcodeExpectations.containsKey(bytecodeDescriptor)) {
			throw new IllegalArgumentException("Cannot store " + bytecodeDescriptor + " twice.");
		}
		if (number <= 0) {
			throw new IllegalArgumentException("The value of number has to be greater than zero.");
		}
		this.methodCallExpectations.put(bytecodeDescriptor, number);
		return this;
	}

	/**
	 * Adds a method call expectation to this section. Throws an
	 * {@link IllegalArgumentException} if you want to add a method call twice.
	 * <p>
	 * Don't use this with constructors!
	 * 
	 * @param className
	 *          The canonical name of the class declaring the method.
	 * @param signature
	 *          A string containing a standard Java method signature (i.e.
	 *          <code>public static java.lang.String valueOf(java.lang.Object obj)</code>
	 *          ). Object types need to be specified with the full canonical name.
	 *          Specifically, only the two tokens before the first '(', as well as
	 *          everything between '(' and ')' is evaluated. Whitespaces and
	 *          qualifiers like "public", "static" are ignored. Generic types may
	 *          be ommited (and are ignored) so that "List" and "List<Integer>"
	 *          are treated as the same since bytecode signatures ignore generics.
	 *          For method parameters, only one or two tokens are allowed
	 *          (example: "int[]" or "int[] abc"). It is advised to take the
	 *          method declaration from sourcecode or from documentation and only
	 *          adapt it, if necessary. The thing that needs to be adapted is type
	 *          names for object types. So instead of giving the String
	 *          "String myString", this has to be expanded to "java.lang.String".
	 *          Note that inner/nested classes need to be specified using the '$'
	 *          symbol as in the following example:
	 *          <code>my.packagename.OutClass$InnerClass</code>.
	 * @param number
	 *          The expected number (How often this opcode should be counted.).
	 *          Has to be greater than zero.
	 * @return This {@link SectionExpectation} object.
	 */
	public SectionExpectation add(final String className, final String signature, final long number) {
		return this.add(this.signatureToDescriptor(className, signature), number);
	}

	/**
	 * Compares the predefined expectations with the actual measurement.
	 * 
	 * @param measuredOpcodeCounts
	 *          The number of opcodes counted.
	 * @param measuredMethodCallCounts
	 *          The number of method calls counted.
	 * @param zeroMethodError
	 *          <code>true</code>, if measured method call counts equal to zero
	 *          are handled as error. <code>false</code>, if not.
	 * @param round
	 *          The comparison round. Used for better human readable error
	 *          messages.
	 */
	protected void compare(final long[] measuredOpcodeCounts, final Map<String, Long> measuredMethodCallCounts,
			final boolean zeroMethodError, final int round) {
		// compare opcodes
		for (int j = 0; j < measuredOpcodeCounts.length; j++) {
			long expected = 0;
			if (this.opcodeExpectations.containsKey(j)) {
				expected = this.opcodeExpectations.get(j);
			}
			long actual = measuredOpcodeCounts[j];
			Assert.assertEquals(message(j, round), expected, actual);
		}
		// compare method calls (compare all expected with corresponding actuals)
		for (String method : this.methodCallExpectations.keySet()) {
			String message = "Expected " + message(method, round) + " not found";
			Assert.assertTrue(message, measuredMethodCallCounts.containsKey(method));
			long expected = this.methodCallExpectations.get(method);
			long actual = measuredMethodCallCounts.remove(method);
			Assert.assertEquals(message(method, round), expected, actual);
		}
		// ensure that no method not expected is measured
		for (String method : measuredMethodCallCounts.keySet()) {
			String message;
			long actual = measuredMethodCallCounts.get(method);

			message = message(method, round) + " enlisted and counted as 0";
			Assert.assertTrue(message, actual > 0 || !zeroMethodError);
			message = "Actual " + message(method, round) + " not expected but counted as " + actual;
			Assert.assertTrue(message, actual <= 0);
		}
	}

	/**
	 * Builds an error message that shows if an assertion is false. This
	 * workaround for non-assertEquals()-methods adds an additional info about the
	 * expected and actual value.
	 * 
	 * @param name
	 *          The name of the wrong opcode or method.
	 * @param round
	 *          Counting round.
	 * @param expected
	 *          The expected value.
	 * @param actual
	 *          The actual value.
	 * @return Error message.
	 */
	@SuppressWarnings("unused")
	private String message(final String name, final int round, final int expected, final int actual) {
		StringBuilder sb = new StringBuilder();
		sb.append(this.message(name, round));
		sb.append(" expected:<");
		sb.append(expected);
		sb.append("> but was:<");
		sb.append(actual);
		sb.append(">");
		return sb.toString();
	}

	/**
	 * Builds an error message that shows if an assertion is false.
	 * 
	 * @param opcode
	 *          Current opcode.
	 * @param round
	 *          Counting round.
	 * @return Error message.
	 */
	private String message(final int opcode, final int round) {
		assert opcode >= 0 && opcode < 202 : "Opcode number out of range. Was: " + opcode;
		String opString = FullOpcodeMapper.getMnemonicOfOpcode(opcode);
		return this.message(opString, round);
	}

	/**
	 * Builds an error message that shows if an assertion is false.
	 * 
	 * @param name
	 *          The name of the wrong opcode or method.
	 * @param round
	 *          Counting round.
	 * @return Error message.
	 */
	private String message(final String name, final int round) {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append(" in round ");
		sb.append(round);
		sb.append(" in ");
		sb.append(this);
		return sb.toString();
	}

	/**
	 * Converts a typical java signature to the equivalent bytecode descriptor.
	 * 
	 * For example:<br />
	 * java.lang.String valueOf(java.lang.Object obj) =>
	 * java.lang.String.valueOf(Ljava/lang/Object;)Ljava/lang/String;
	 * 
	 * @param className
	 *          The canonical name of the class declaring the method.
	 * @param signature
	 *          A string containing a standard Java method signature (i.e.
	 *          <code>public static java.lang.String valueOf(java.lang.Object obj)</code>
	 *          ). Object types need to be specified with the full canonical name.
	 *          Specifically, only the two tokens before the first '(', as well as
	 *          everything between '(' and ')' is evaluated. Whitespaces and
	 *          qualifiers like "public", "static" are ignored. Generic types may
	 *          be ommited (and are ignored) so that "List" and "List<Integer>"
	 *          are treated as the same since bytecode signatures ignore generics.
	 *          For method parameters, only one or two tokens are allowed
	 *          (example: "int[]" or "int[] abc"). It is advised to take the
	 *          method declaration from sourcecode or from documentation and only
	 *          adapt it, if necessary. The thing that needs to be adapted is type
	 *          names for object types. So instead of giving the String
	 *          "String myString", this has to be expanded to "java.lang.String".
	 *          Note that inner/nested classes need to be specified using the '$'
	 *          symbol as in the following example:
	 *          <code>my.packagename.OutClass$InnerClass</code>.
	 * @return A bytecode descriptor to uniquely identify methods consisting of
	 *         the canonical classname, the method name and the method descriptor.
	 */
	private String signatureToDescriptor(String className, String signature) {
		MethodDescriptor test = new MethodDescriptor(className, signature);
		return test.getCanonicalMethodName();
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("SectionExpectation [sectionNumber=");
		sb.append(this.sectionNumber);
		sb.append(", range=");
		sb.append(this.range);
		sb.append("]");
		return sb.toString();
	}
}
