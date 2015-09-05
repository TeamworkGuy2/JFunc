package functionUtils;

import java.util.Objects;
import java.util.function.Consumer;

/** A {@link Consumer} for float
 * @author TeamworkGuy2
 * @since 2015-1-25
 */
@javax.annotation.Generated("StringTemplate")
@FunctionalInterface
public interface FloatConsumer  {


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
