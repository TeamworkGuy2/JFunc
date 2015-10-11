package twg2.functions;

import java.util.Objects;
import java.util.function.Consumer;

/** {@link Consumer} helper interfaces for primitive types
 * @author TeamworkGuy2
 * @since 2015-1-25
 */
@javax.annotation.Generated("StringTemplate")
public interface Consumers {

	@FunctionalInterface
	public interface Boolean {

		/** Performs an operation on the given argument.
		 * @param value the boolean argument
		 */
		public void accept(boolean value);


		default Consumers.Boolean andThen(Consumers.Boolean after) {
			Objects.requireNonNull(after);
			return (boolean t) -> {
				accept(t);
				after.accept(t);
			};
		}

	}


	@FunctionalInterface
	public interface Byte {

		/** Performs an operation on the given argument.
		 * @param value the byte argument
		 */
		public void accept(byte value);


		default Consumers.Byte andThen(Consumers.Byte after) {
			Objects.requireNonNull(after);
			return (byte t) -> {
				accept(t);
				after.accept(t);
			};
		}

	}


	@FunctionalInterface
	public interface Char {

		/** Performs an operation on the given argument.
		 * @param value the char argument
		 */
		public void accept(char value);


		default Consumers.Char andThen(Consumers.Char after) {
			Objects.requireNonNull(after);
			return (char t) -> {
				accept(t);
				after.accept(t);
			};
		}

	}


	@FunctionalInterface
	public interface Short {

		/** Performs an operation on the given argument.
		 * @param value the short argument
		 */
		public void accept(short value);


		default Consumers.Short andThen(Consumers.Short after) {
			Objects.requireNonNull(after);
			return (short t) -> {
				accept(t);
				after.accept(t);
			};
		}

	}


	@FunctionalInterface
	public interface Int extends java.util.function.IntConsumer{

		/** Performs an operation on the given argument.
		 * @param value the int argument
		 */
		@Override
		public void accept(int value);


		default Consumers.Int andThen(Consumers.Int after) {
			Objects.requireNonNull(after);
			return (int t) -> {
				accept(t);
				after.accept(t);
			};
		}

	}


	@FunctionalInterface
	public interface Float {

		/** Performs an operation on the given argument.
		 * @param value the float argument
		 */
		public void accept(float value);


		default Consumers.Float andThen(Consumers.Float after) {
			Objects.requireNonNull(after);
			return (float t) -> {
				accept(t);
				after.accept(t);
			};
		}

	}


	@FunctionalInterface
	public interface Long extends java.util.function.LongConsumer{

		/** Performs an operation on the given argument.
		 * @param value the long argument
		 */
		@Override
		public void accept(long value);


		default Consumers.Long andThen(Consumers.Long after) {
			Objects.requireNonNull(after);
			return (long t) -> {
				accept(t);
				after.accept(t);
			};
		}

	}


	@FunctionalInterface
	public interface Double extends java.util.function.DoubleConsumer{

		/** Performs an operation on the given argument.
		 * @param value the double argument
		 */
		@Override
		public void accept(double value);


		default Consumers.Double andThen(Consumers.Double after) {
			Objects.requireNonNull(after);
			return (double t) -> {
				accept(t);
				after.accept(t);
			};
		}

	}



}
