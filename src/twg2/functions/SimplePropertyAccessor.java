package twg2.functions;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author TeamworkGuy2
 * @since 2015-7-25
 * @see PropertyAccessor
 */
public class SimplePropertyAccessor<E> implements PropertyAccessor<E> {
	private Supplier<E> getter;
	private Consumer<E> setter;


	public SimplePropertyAccessor(Supplier<E> getter, Consumer<E> setter) {
		this.getter = getter;
		this.setter = setter;
	}


	@Override
	public Supplier<E> getGetter() {
		return getter;
	}


	@Override
	public Consumer<E> getSetter() {
		return setter;
	}


	public static <_E> SimplePropertyAccessor<_E> of(Supplier<_E> getter, Consumer<_E> setter) {
		return new SimplePropertyAccessor<>(getter, setter);
	}

}
