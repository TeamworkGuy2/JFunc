package twg2.functions;

import java.io.IOException;
import java.util.function.BiConsumer;

/** A {@link BiConsumer} that can throw {@link IOException}
 * @author TeamworkGuy2
 * @since 2016-1-27
 */
@FunctionalInterface
public interface BiConsumerIo<T, U> {

	/** {@link BiConsumer#accept(Object, Object)} that can throw {@link IOException}
     * @param t the first input argument
     * @param u the second input argument
	 * @throws IOException
	 */
	public void accept(T t, U u) throws IOException;
}