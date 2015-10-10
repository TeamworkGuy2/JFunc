package twg2.functions;

import java.util.Objects;
import java.util.function.Predicate;

/** A {@link Predicate} for T
 * @author TeamworkGuy2
 * @since 2015-1-23
 */
@javax.annotation.Generated("StringTemplate")
@FunctionalInterface
public interface EnumPredicate<T extends Enum<T>> {


	/** Check if the T meets some condition
	 * @param value the T to check
	 * @return true if the value fulfills the condition, false if the value does not
	 */
	public boolean test(T value);


	default EnumPredicate<T> and(EnumPredicate<T> other) {
		Objects.requireNonNull(other);
		return (value) -> test(value) && other.test(value);
	}


	default EnumPredicate<T> negate() {
		return (value) -> !test(value);
	}


	default EnumPredicate<T> or(EnumPredicate<T> other) {
		Objects.requireNonNull(other);
		return (value) -> test(value) || other.test(value);
	}

}
