package twg2.functions.predicates;

import java.util.Objects;
import java.util.function.Predicate;

/** boolean {@link Predicate} interface
 * @author TeamworkGuy2
 * @since 2015-1-23
 */
@javax.annotation.Generated("StringTemplate")
@FunctionalInterface
public interface BooleanPredicate {

	/** Check if the boolean meets some condition
	 * @param value the boolean to check
	 * @return true if the value fulfills the condition, false if the value does not
	 */
	public boolean test(boolean value);


	default BooleanPredicate and(BooleanPredicate other) {
		Objects.requireNonNull(other);
		return (value) -> test(value) && other.test(value);
	}


	default BooleanPredicate negate() {
		return (value) -> !test(value);
	}


	default BooleanPredicate or(BooleanPredicate other) {
		Objects.requireNonNull(other);
		return (value) -> test(value) || other.test(value);
	}

}
