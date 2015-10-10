package twg2.functions;

import java.util.Objects;
import java.util.function.Consumer;

/** A {@link Consumer} for char
 * @author TeamworkGuy2
 * @since 2015-1-25
 */
@javax.annotation.Generated("StringTemplate")
@FunctionalInterface
public interface CharConsumer  {


	/** Performs an operation on the given argument.
	 * @param value the char argument
	 */
	public void accept(char value);


	default CharConsumer andThen(CharConsumer after) {
		Objects.requireNonNull(after);
		return (char t) -> {
			accept(t);
			after.accept(t);
		};
	}

}
