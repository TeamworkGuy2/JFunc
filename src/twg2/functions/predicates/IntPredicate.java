package twg2.functions.predicates;

import java.util.Objects;
import java.util.function.Predicate;

/** int {@link Predicate} interface
 * @author TeamworkGuy2
 * @since 2015-1-23
 */
@javax.annotation.Generated("StringTemplate")
@FunctionalInterface
public interface IntPredicate extends java.util.function.IntPredicate {

	/** Check if the int meets some condition
	 * @param value the int to check
	 * @return true if the value fulfills the condition, false if the value does not
	 */
	@Override
	public boolean test(int value);


	default IntPredicate and(IntPredicate other) {
		Objects.requireNonNull(other);
		return (value) -> test(value) && other.test(value);
	}


	@Override
	default IntPredicate negate() {
		return (value) -> !test(value);
	}


	default IntPredicate or(IntPredicate other) {
		Objects.requireNonNull(other);
		return (value) -> test(value) || other.test(value);
	}

}
