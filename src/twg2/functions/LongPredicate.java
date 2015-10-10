package twg2.functions;

import java.util.Objects;
import java.util.function.Predicate;

/** A {@link Predicate} for long
 * @author TeamworkGuy2
 * @since 2015-1-23
 */
@javax.annotation.Generated("StringTemplate")
@FunctionalInterface
public interface LongPredicate extends java.util.function.LongPredicate {


	/** Check if the long meets some condition
	 * @param value the long to check
	 * @return true if the value fulfills the condition, false if the value does not
	 */
	@Override
	public boolean test(long value);


	default LongPredicate and(LongPredicate other) {
		Objects.requireNonNull(other);
		return (value) -> test(value) && other.test(value);
	}


	@Override
	default LongPredicate negate() {
		return (value) -> !test(value);
	}


	default LongPredicate or(LongPredicate other) {
		Objects.requireNonNull(other);
		return (value) -> test(value) || other.test(value);
	}

}
