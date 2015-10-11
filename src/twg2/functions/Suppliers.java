package twg2.functions;

import java.util.function.Supplier;

/** {@link Supplier} helper interfaces for primitive types
 * @author TeamworkGuy2
 * @since 2015-1-25
 */
@javax.annotation.Generated("StringTemplate")
public interface Suppliers {

	@FunctionalInterface
	public interface Boolean {


		/** Gets a result.
		 * @return the result boolean value
		 */
		public boolean getAsBoolean();

	}

	@FunctionalInterface
	public interface Byte {


		/** Gets a result.
		 * @return the result byte value
		 */
		public byte getAsByte();

	}

	@FunctionalInterface
	public interface Char {


		/** Gets a result.
		 * @return the result char value
		 */
		public char getAsChar();

	}

	@FunctionalInterface
	public interface Short {


		/** Gets a result.
		 * @return the result short value
		 */
		public short getAsShort();

	}

	@FunctionalInterface
	public interface Int extends java.util.function.IntSupplier{


		/** Gets a result.
		 * @return the result int value
		 */
		@Override
		public int getAsInt();

	}

	@FunctionalInterface
	public interface Float {


		/** Gets a result.
		 * @return the result float value
		 */
		public float getAsFloat();

	}

	@FunctionalInterface
	public interface Long extends java.util.function.LongSupplier{


		/** Gets a result.
		 * @return the result long value
		 */
		@Override
		public long getAsLong();

	}

	@FunctionalInterface
	public interface Double extends java.util.function.DoubleSupplier{


		/** Gets a result.
		 * @return the result double value
		 */
		@Override
		public double getAsDouble();

	}


}