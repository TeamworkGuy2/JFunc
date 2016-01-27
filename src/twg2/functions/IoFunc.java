package twg2.functions;

import java.io.IOException;

/**
 * @author TeamworkGuy2
 * @since 2016-1-27
 */
public interface IoFunc {


	@FunctionalInterface
	public static interface SupplierIo<T, R> {
		public R get() throws IOException;
	}


	@FunctionalInterface
	public static interface ConsumerIo<T> {
		public void accept(T t) throws IOException;
	}


	@FunctionalInterface
	public static interface FunctionIo<T, R> {
		public R apply(T t) throws IOException;
	}


	@FunctionalInterface
	public static interface BiConsumerIo<T, U> {
		public void accept(T t, U u) throws IOException;
	}


	@FunctionalInterface
	public static interface BiFunctionIo<T, U, R> {
		public R apply(T t, U u) throws IOException;
	}

}
