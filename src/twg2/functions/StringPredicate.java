package twg2.functions;

import java.util.Objects;
import java.util.function.Predicate;

/** A {@link Predicate} for Object
 * @author TeamworkGuy2
 * @since 2015-1-23
 */
@javax.annotation.Generated("StringTemplate")
@FunctionalInterface
public interface StringPredicate {


	/** Check if the Object meets some condition
	 * @param value the Object to check
	 * @return true if the value fulfills the condition, false if the value does not
	 */
	public boolean test(Object value);


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
