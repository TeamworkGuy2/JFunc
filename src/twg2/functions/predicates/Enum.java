package twg2.functions.predicates;

import java.util.Objects;
import java.util.function.Predicate;

/** T {@link Predicate} interface
 * @author TeamworkGuy2
 * @since 2015-1-23
 */
@javax.annotation.Generated("StringTemplate")
@FunctionalInterface
public interface Enum<T extends Enum<T>> {

	/** Check if the T meets some condition
	 * @param value the T to check
	 * @return true if the value fulfills the condition, false if the value does not
	 */
	public boolean test(T value);


	default Enum<T> and(Enum<T> other) {
		Objects.requireNonNull(other);
		return (value) -> test(value) && other.test(value);
	}


	default Enum<T> negate() {
		return (value) -> !test(value);
	}


	default Enum<T> or(Enum<T> other) {
		Objects.requireNonNull(other);
		return (value) -> test(value) || other.test(value);
	}

}
