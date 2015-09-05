package functionUtils;

import java.util.Objects;
import java.util.function.Consumer;

/** A {@link Consumer} for boolean
 * @author TeamworkGuy2
 * @since 2015-1-25
 */
@javax.annotation.Generated("StringTemplate")
@FunctionalInterface
public interface BooleanConsumer  {


	/** Performs an operation on the given argument.
	 * @param value the boolean argument
	 */
	public void accept(boolean value);


	default BooleanConsumer andThen(BooleanConsumer after) {
		Objects.requireNonNull(after);
		return (boolean t) -> {
			accept(t);
			after.accept(t);
		};
	}

}
