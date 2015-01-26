package functionUtils;

import java.util.Objects;
import java.util.function.Predicate;

/** A {@link Predicate} for String
 * @author TeamworkGuy2
 * @since 2015-1-23
 */
@javax.annotation.Generated("StringTemplate")
@FunctionalInterface
public interface StringPredicate {


	/** Check if the String meets some condition
	 * @param value the String to check
	 * @return true if the value fulfills the condition, false if the value does not
	 */
	public boolean test(String value);


	default StringPredicate and(StringPredicate other) {
		Objects.requireNonNull(other);
		return (value) -> test(value) && other.test(value);
	}


	default StringPredicate negate() {
		return (value) -> !test(value);
	}


	default StringPredicate or(StringPredicate other) {
		Objects.requireNonNull(other);
		return (value) -> test(value) || other.test(value);
	}

}
