package twg2.functions.predicates;

import java.util.Objects;
import java.util.function.Predicate;

/** byte {@link Predicate} interface
 * @author TeamworkGuy2
 * @since 2015-1-23
 */
@javax.annotation.Generated("StringTemplate")
@FunctionalInterface
public interface BytePredicate {

	/** Check if the byte meets some condition
	 * @param value the byte to check
	 * @return true if the value fulfills the condition, false if the value does not
	 */
	public boolean test(byte value);


	default BytePredicate and(BytePredicate other) {
		Objects.requireNonNull(other);
		return (value) -> test(value) && other.test(value);
	}


	default BytePredicate negate() {
		return (value) -> !test(value);
	}


	default BytePredicate or(BytePredicate other) {
		Objects.requireNonNull(other);
		return (value) -> test(value) || other.test(value);
	}

}
