package twg2.functions;

import java.io.IOException;
import java.util.function.BiFunction;

/** A {@link BiFunction} that can throw {@link IOException}
 * @author TeamworkGuy2
 * @since 2016-1-27
 */
@FunctionalInterface
public interface BiFunctionIo<T, U, R> {

	/** {@link BiFunction#apply(Object, Object)} that can throw {@link IOException}
     * @param t the first function argument
     * @param u the second function argument
     * @return the function result
	 * @throws IOException
	 */
	public R apply(T t, U u) throws IOException;
}