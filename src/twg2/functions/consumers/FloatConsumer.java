package twg2.functions.consumers;

import java.util.Objects;
import java.util.function.Consumer;

/** float {@link Consumer} interface
 * @author TeamworkGuy2
 * @since 2015-1-25
 */
@javax.annotation.Generated("StringTemplate")
@FunctionalInterface
public interface FloatConsumer {

	/** Performs an operation on the given argument.
	 * @param value the float argument
	 */
	public void accept(float value);


	default FloatConsumer andThen(FloatConsumer after) {
		Objects.requireNonNull(after);
		return (float t) -> {
			accept(t);
			after.accept(t);
		};
	}

}
