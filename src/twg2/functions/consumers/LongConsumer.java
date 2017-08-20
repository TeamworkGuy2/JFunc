package twg2.functions.consumers;

import java.util.Objects;
import java.util.function.Consumer;

/** long {@link Consumer} interface
 * @author TeamworkGuy2
 * @since 2015-1-25
 */
@javax.annotation.Generated("StringTemplate")
@FunctionalInterface
public interface LongConsumer extends java.util.function.LongConsumer {

	/** Performs an operation on the given argument.
	 * @param value the long argument
	 */
	@Override
	public void accept(long value);


	default LongConsumer andThen(LongConsumer after) {
		Objects.requireNonNull(after);
		return (long t) -> {
			accept(t);
			after.accept(t);
		};
	}

}
