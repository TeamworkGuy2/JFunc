package twg2.functions;

import java.util.Objects;
import java.util.function.Predicate;

/** {@link Predicate} helper interfaces for primitive types
 * @author TeamworkGuy2
 * @since 2015-1-23
 */
@javax.annotation.Generated("StringTemplate")
public interface Predicates {

	@FunctionalInterface
	public interface Boolean {

		/** Check if the boolean meets some condition
		 * @param value the boolean to check
		 * @return true if the value fulfills the condition, false if the value does not
		 */
		public boolean test(boolean value);


		default Predicates.Boolean and(Predicates.Boolean other) {
			Objects.requireNonNull(other);
			return (value) -> test(value) && other.test(value);
		}


		default Predicates.Boolean negate() {
			return (value) -> !test(value);
		}


		default Predicates.Boolean or(Predicates.Boolean other) {
			Objects.requireNonNull(other);
			return (value) -> test(value) || other.test(value);
		}

	}


	@FunctionalInterface
	public interface Byte {

		/** Check if the byte meets some condition
		 * @param value the byte to check
		 * @return true if the value fulfills the condition, false if the value does not
		 */
		public boolean test(byte value);


		default Predicates.Byte and(Predicates.Byte other) {
			Objects.requireNonNull(other);
			return (value) -> test(value) && other.test(value);
		}


		default Predicates.Byte negate() {
			return (value) -> !test(value);
		}


		default Predicates.Byte or(Predicates.Byte other) {
			Objects.requireNonNull(other);
			return (value) -> test(value) || other.test(value);
		}

	}


	@FunctionalInterface
	public interface Char {

		/** Check if the char meets some condition
		 * @param value the char to check
		 * @return true if the value fulfills the condition, false if the value does not
		 */
		public boolean test(char value);


		default Predicates.Char and(Predicates.Char other) {
			Objects.requireNonNull(other);
			return (value) -> test(value) && other.test(value);
		}


		default Predicates.Char negate() {
			return (value) -> !test(value);
		}


		default Predicates.Char or(Predicates.Char other) {
			Objects.requireNonNull(other);
			return (value) -> test(value) || other.test(value);
		}

	}


	@FunctionalInterface
	public interface Short {

		/** Check if the short meets some condition
		 * @param value the short to check
		 * @return true if the value fulfills the condition, false if the value does not
		 */
		public boolean test(short value);


		default Predicates.Short and(Predicates.Short other) {
			Objects.requireNonNull(other);
			return (value) -> test(value) && other.test(value);
		}


		default Predicates.Short negate() {
			return (value) -> !test(value);
		}


		default Predicates.Short or(Predicates.Short other) {
			Objects.requireNonNull(other);
			return (value) -> test(value) || other.test(value);
		}

	}


	@FunctionalInterface
	public interface Int extends java.util.function.IntPredicate {

		/** Check if the int meets some condition
		 * @param value the int to check
		 * @return true if the value fulfills the condition, false if the value does not
		 */
	@Override
		public boolean test(int value);


		default Predicates.Int and(Predicates.Int other) {
			Objects.requireNonNull(other);
			return (value) -> test(value) && other.test(value);
		}


	@Override
		default Predicates.Int negate() {
			return (value) -> !test(value);
		}


		default Predicates.Int or(Predicates.Int other) {
			Objects.requireNonNull(other);
			return (value) -> test(value) || other.test(value);
		}

	}


	@FunctionalInterface
	public interface Float {

		/** Check if the float meets some condition
		 * @param value the float to check
		 * @return true if the value fulfills the condition, false if the value does not
		 */
		public boolean test(float value);


		default Predicates.Float and(Predicates.Float other) {
			Objects.requireNonNull(other);
			return (value) -> test(value) && other.test(value);
		}


		default Predicates.Float negate() {
			return (value) -> !test(value);
		}


		default Predicates.Float or(Predicates.Float other) {
			Objects.requireNonNull(other);
			return (value) -> test(value) || other.test(value);
		}

	}


	@FunctionalInterface
	public interface Long extends java.util.function.LongPredicate {

		/** Check if the long meets some condition
		 * @param value the long to check
		 * @return true if the value fulfills the condition, false if the value does not
		 */
	@Override
		public boolean test(long value);


		default Predicates.Long and(Predicates.Long other) {
			Objects.requireNonNull(other);
			return (value) -> test(value) && other.test(value);
		}


	@Override
		default Predicates.Long negate() {
			return (value) -> !test(value);
		}


		default Predicates.Long or(Predicates.Long other) {
			Objects.requireNonNull(other);
			return (value) -> test(value) || other.test(value);
		}

	}


	@FunctionalInterface
	public interface Double extends java.util.function.DoublePredicate {

		/** Check if the double meets some condition
		 * @param value the double to check
		 * @return true if the value fulfills the condition, false if the value does not
		 */
	@Override
		public boolean test(double value);


		default Predicates.Double and(Predicates.Double other) {
			Objects.requireNonNull(other);
			return (value) -> test(value) && other.test(value);
		}


	@Override
		default Predicates.Double negate() {
			return (value) -> !test(value);
		}


		default Predicates.Double or(Predicates.Double other) {
			Objects.requireNonNull(other);
			return (value) -> test(value) || other.test(value);
		}

	}


	@FunctionalInterface
	public interface String {

		/** Check if the Object meets some condition
		 * @param value the Object to check
		 * @return true if the value fulfills the condition, false if the value does not
		 */
		public boolean test(Object value);


		default Predicates.String and(Predicates.String other) {
			Objects.requireNonNull(other);
			return (value) -> test(value) && other.test(value);
		}


		default Predicates.String negate() {
			return (value) -> !test(value);
		}


		default Predicates.String or(Predicates.String other) {
			Objects.requireNonNull(other);
			return (value) -> test(value) || other.test(value);
		}

	}


	@FunctionalInterface
	public interface Enum<T extends Enum<T>> {

		/** Check if the T meets some condition
		 * @param value the T to check
		 * @return true if the value fulfills the condition, false if the value does not
		 */
		public boolean test(T value);


		default Predicates.Enum<T> and(Predicates.Enum<T> other) {
			Objects.requireNonNull(other);
			return (value) -> test(value) && other.test(value);
		}


		default Predicates.Enum<T> negate() {
			return (value) -> !test(value);
		}


		default Predicates.Enum<T> or(Predicates.Enum<T> other) {
			Objects.requireNonNull(other);
			return (value) -> test(value) || other.test(value);
		}

	}



}
