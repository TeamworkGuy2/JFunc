package twg2.functions.consumers;

import java.util.Objects;
import java.util.function.Consumer;

/** int {@link Consumer} interface
 * @author TeamworkGuy2
 * @since 2015-1-25
 */
@javax.annotation.Generated("StringTemplate")
@FunctionalInterface
public interface IntConsumer extends java.util.function.IntConsumer {

	/** Performs an operation on the given argument.
	 * @param value the int argument
	 */
	@Override
	public void accept(int value);


	default IntConsumer andThen(IntConsumer after) {
		Objects.requireNonNull(after);
		return (int t) -> {
			accept(t);
			after.accept(t);
		};
	}

}
