package functionUtils;

import java.util.Objects;
import java.util.function.Predicate;

/** A {@link Predicate} for short
 * @author TeamworkGuy2
 * @since 2015-1-23
 */
@javax.annotation.Generated("StringTemplate")
@FunctionalInterface
public interface ShortPredicate {


	/** Check if the short meets some condition
	 * @param value the short to check
	 * @return true if the value fulfills the condition, false if the value does not
	 */
	public boolean test(short value);


	default ShortPredicate and(ShortPredicate other) {
		Objects.requireNonNull(other);
		return (value) -> test(value) && other.test(value);
	}


	default ShortPredicate negate() {
		return (value) -> !test(value);
	}


	default ShortPredicate or(ShortPredicate other) {
		Objects.requireNonNull(other);
		return (value) -> test(value) || other.test(value);
	}

}
