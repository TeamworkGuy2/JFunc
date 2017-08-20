package twg2.functions.consumers;

import java.util.Objects;
import java.util.function.Consumer;

/** byte {@link Consumer} interface
 * @author TeamworkGuy2
 * @since 2015-1-25
 */
@javax.annotation.Generated("StringTemplate")
@FunctionalInterface
public interface ByteConsumer {

	/** Performs an operation on the given argument.
	 * @param value the byte argument
	 */
	public void accept(byte value);


	default ByteConsumer andThen(ByteConsumer after) {
		Objects.requireNonNull(after);
		return (byte t) -> {
			accept(t);
			after.accept(t);
		};
	}

}
