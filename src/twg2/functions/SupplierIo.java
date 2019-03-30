package twg2.functions;

import java.io.IOException;
import java.util.function.Supplier;

/** A {@link Supplier} that can throw {@link IOException}
 * @author TeamworkGuy2
 * @since 2016-1-27
 */
@FunctionalInterface
public interface SupplierIo<T> {

	/** {@link Supplier#get()} that can throw {@link IOException}
	 * @return a result
	 * @throws IOException
	 */
	public T get() throws IOException;
}