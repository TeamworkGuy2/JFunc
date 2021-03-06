package twg2.functions.predicates;

import java.util.Objects;
import java.util.function.Predicate;

/** double {@link Predicate} interface
 * @author TeamworkGuy2
 * @since 2015-1-23
 */
@javax.annotation.Generated("StringTemplate")
@FunctionalInterface
public interface DoublePredicate extends java.util.function.DoublePredicate {

	/** Check if the double meets some condition
	 * @param value the double to check
	 * @return true if the value fulfills the condition, false if the value does not
	 */
	@Override
	public boolean test(double value);


	default DoublePredicate and(DoublePredicate other) {
		Objects.requireNonNull(other);
		return (value) -> test(value) && other.test(value);
	}


	@Override
	default DoublePredicate negate() {
		return (value) -> !test(value);
	}


	default DoublePredicate or(DoublePredicate other) {
		Objects.requireNonNull(other);
		return (value) -> test(value) || other.test(value);
	}

}
