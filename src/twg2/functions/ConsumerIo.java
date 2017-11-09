package twg2.functions;

import java.io.IOException;
import java.util.function.Consumer;

/** A {@link Consumer} that can throw {@link IOException}
 * @author TeamworkGuy2
 * @since 2016-1-27
 */
@FunctionalInterface
public interface ConsumerIo<T> {

	/** {@link Consumer#accept(Object)} that can throw {@link IOException}
	 * @param t the input argument
	 * @throws IOException
	 */
	public void accept(T t) throws IOException;
}