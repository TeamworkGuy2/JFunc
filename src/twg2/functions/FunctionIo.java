package twg2.functions;

import java.io.IOException;
import java.util.function.Function;

/** A {@link Function} that can throw {@link IOException}
 * @author TeamworkGuy2
 * @since 2016-1-27
 */
@FunctionalInterface
public interface FunctionIo<T, R> {

	/** {@link Function#apply(Object)} that can throw {@link IOException}
	 * @param t the function argument
	 * @return the function result
	 * @throws IOException
	 */
	public R apply(T t) throws IOException;
}