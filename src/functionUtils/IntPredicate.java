package functionUtils;

import java.util.Objects;
import java.util.function.Predicate;

/** A {@link Predicate} for int
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
	public boolean test(int value);


	default IntPredicate and(IntPredicate other) {
		Objects.requireNonNull(other);
		return (value) -> test(value) && other.test(value);
	}


	default IntPredicate negate() {
		return (value) -> !test(value);
	}


	default IntPredicate or(IntPredicate other) {
		Objects.requireNonNull(other);
		return (value) -> test(value) || other.test(value);
	}

}
