package twg2.functions;

import java.util.Objects;
import java.util.function.Predicate;

/** A {@link Predicate} for char
 * @author TeamworkGuy2
 * @since 2015-1-23
 */
@javax.annotation.Generated("StringTemplate")
@FunctionalInterface
public interface CharPredicate {


	/** Check if the char meets some condition
	 * @param value the char to check
	 * @return true if the value fulfills the condition, false if the value does not
	 */
	public boolean test(char value);


	default CharPredicate and(CharPredicate other) {
		Objects.requireNonNull(other);
		return (value) -> test(value) && other.test(value);
	}


	default CharPredicate negate() {
		return (value) -> !test(value);
	}


	default CharPredicate or(CharPredicate other) {
		Objects.requireNonNull(other);
		return (value) -> test(value) || other.test(value);
	}

}
