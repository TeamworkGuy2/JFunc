package twg2.functions.predicates;

import java.util.Objects;
import java.util.function.Predicate;

/** Object {@link Predicate} interface
 * @author TeamworkGuy2
 * @since 2015-1-23
 */
@javax.annotation.Generated("StringTemplate")
@FunctionalInterface
public interface String {

	/** Check if the Object meets some condition
	 * @param value the Object to check
	 * @return true if the value fulfills the condition, false if the value does not
	 */
	public boolean test(Object value);


	default String and(String other) {
		Objects.requireNonNull(other);
		return (value) -> test(value) && other.test(value);
	}


	default String negate() {
		return (value) -> !test(value);
	}


	default String or(String other) {
		Objects.requireNonNull(other);
		return (value) -> test(value) || other.test(value);
	}

}
