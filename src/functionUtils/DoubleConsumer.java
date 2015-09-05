package functionUtils;

import java.util.Objects;
import java.util.function.Consumer;

/** A {@link Consumer} for double
 * @author TeamworkGuy2
 * @since 2015-1-25
 */
@javax.annotation.Generated("StringTemplate")
@FunctionalInterface
public interface DoubleConsumer extends java.util.function.DoubleConsumer {


	/** Performs an operation on the given argument.
	 * @param value the double argument
	 */
	public void accept(double value);


	default DoubleConsumer andThen(DoubleConsumer after) {
		Objects.requireNonNull(after);
		return (double t) -> {
			accept(t);
			after.accept(t);
		};
	}

}
