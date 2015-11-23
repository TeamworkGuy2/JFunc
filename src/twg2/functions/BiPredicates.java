package twg2.functions;

import java.util.function.BiPredicate;

/** {@link BiPredicate} helpers for primitive types
 * @author TeamworkGuy2
 * @since 2015-11-22
 */
@javax.annotation.Generated("StringTemplate")
public interface BiPredicates {

	@FunctionalInterface
	public interface BooleanObject<T1> {

		/** Performs an operation on the given argument.
		 * @param value1 the boolean argument
		 * @param value2 the T1 argument
		 */
		public boolean test(boolean value1, T1 value2);

	}


	@FunctionalInterface
	public interface ObjectBoolean<T1> {

		/** Performs an operation on the given argument.
		 * @param value1 the T1 argument
		 * @param value2 the boolean argument
		 */
		public boolean test(T1 value1, boolean value2);

	}


	@FunctionalInterface
	public interface ByteObject<T1> {

		/** Performs an operation on the given argument.
		 * @param value1 the byte argument
		 * @param value2 the T1 argument
		 */
		public boolean test(byte value1, T1 value2);

	}


	@FunctionalInterface
	public interface ObjectByte<T1> {

		/** Performs an operation on the given argument.
		 * @param value1 the T1 argument
		 * @param value2 the byte argument
		 */
		public boolean test(T1 value1, byte value2);

	}


	@FunctionalInterface
	public interface CharObject<T1> {

		/** Performs an operation on the given argument.
		 * @param value1 the char argument
		 * @param value2 the T1 argument
		 */
		public boolean test(char value1, T1 value2);

	}


	@FunctionalInterface
	public interface ObjectChar<T1> {

		/** Performs an operation on the given argument.
		 * @param value1 the T1 argument
		 * @param value2 the char argument
		 */
		public boolean test(T1 value1, char value2);

	}


	@FunctionalInterface
	public interface ShortObject<T1> {

		/** Performs an operation on the given argument.
		 * @param value1 the short argument
		 * @param value2 the T1 argument
		 */
		public boolean test(short value1, T1 value2);

	}


	@FunctionalInterface
	public interface ObjectShort<T1> {

		/** Performs an operation on the given argument.
		 * @param value1 the T1 argument
		 * @param value2 the short argument
		 */
		public boolean test(T1 value1, short value2);

	}


	@FunctionalInterface
	public interface IntObject<T1> {

		/** Performs an operation on the given argument.
		 * @param value1 the int argument
		 * @param value2 the T1 argument
		 */
		public boolean test(int value1, T1 value2);

	}


	@FunctionalInterface
	public interface ObjectInt<T1> {

		/** Performs an operation on the given argument.
		 * @param value1 the T1 argument
		 * @param value2 the int argument
		 */
		public boolean test(T1 value1, int value2);

	}


	@FunctionalInterface
	public interface FloatObject<T1> {

		/** Performs an operation on the given argument.
		 * @param value1 the float argument
		 * @param value2 the T1 argument
		 */
		public boolean test(float value1, T1 value2);

	}


	@FunctionalInterface
	public interface ObjectFloat<T1> {

		/** Performs an operation on the given argument.
		 * @param value1 the T1 argument
		 * @param value2 the float argument
		 */
		public boolean test(T1 value1, float value2);

	}


	@FunctionalInterface
	public interface LongObject<T1> {

		/** Performs an operation on the given argument.
		 * @param value1 the long argument
		 * @param value2 the T1 argument
		 */
		public boolean test(long value1, T1 value2);

	}


	@FunctionalInterface
	public interface ObjectLong<T1> {

		/** Performs an operation on the given argument.
		 * @param value1 the T1 argument
		 * @param value2 the long argument
		 */
		public boolean test(T1 value1, long value2);

	}


	@FunctionalInterface
	public interface DoubleObject<T1> {

		/** Performs an operation on the given argument.
		 * @param value1 the double argument
		 * @param value2 the T1 argument
		 */
		public boolean test(double value1, T1 value2);

	}


	@FunctionalInterface
	public interface ObjectDouble<T1> {

		/** Performs an operation on the given argument.
		 * @param value1 the T1 argument
		 * @param value2 the double argument
		 */
		public boolean test(T1 value1, double value2);

	}



}
