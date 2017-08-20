package twg2.functions.consumers;

import java.util.Objects;
import java.util.function.Consumer;

/** short {@link Consumer} interface
 * @author TeamworkGuy2
 * @since 2015-1-25
 */
@javax.annotation.Generated("StringTemplate")
@FunctionalInterface
public interface ShortConsumer {

	/** Performs an operation on the given argument.
	 * @param value the short argument
	 */
	public void accept(short value);


	default ShortConsumer andThen(ShortConsumer after) {
		Objects.requireNonNull(after);
		return (short t) -> {
			accept(t);
			after.accept(t);
		};
	}

}
