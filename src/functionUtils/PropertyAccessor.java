package functionUtils;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author TeamworkGuy2
 * @since 2015-7-25
 * @param <E> this property's element type
 */
public interface PropertyAccessor<E> {

	/**
	 * @return the {@link Consumer} that sets this property's value
	 */
	public Consumer<E> getSetter();


	/**
	 * @return the {@link Supplier} that returns this property's value
	 */
	public Supplier<E> getGetter();


	public default E getValue() {
		return getGetter().get();
	}


	public default void setValue(E value) {
		getSetter().accept(value);
	}


	public static <_E> PropertyAccessor<_E> of(Supplier<_E> getter, Consumer<_E> setter) {
		return new SimplePropertyAccessor<>(getter, setter);
	}

}