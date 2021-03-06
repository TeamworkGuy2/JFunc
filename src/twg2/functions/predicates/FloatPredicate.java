package twg2.functions.predicates;

import java.util.Objects;
import java.util.function.Predicate;

/** float {@link Predicate} interface
 * @author TeamworkGuy2
 * @since 2015-1-23
 */
@javax.annotation.Generated("StringTemplate")
@FunctionalInterface
public interface FloatPredicate {

	/** Check if the float meets some condition
	 * @param value the float to check
	 * @return true if the value fulfills the condition, false if the value does not
	 */
	public boolean test(float value);


	default FloatPredicate and(FloatPredicate other) {
		Objects.requireNonNull(other);
		return (value) -> test(value) && other.test(value);
	}


	default FloatPredicate negate() {
		return (value) -> !test(value);
	}


	default FloatPredicate or(FloatPredicate other) {
		Objects.requireNonNull(other);
		return (value) -> test(value) || other.test(value);
	}

}
