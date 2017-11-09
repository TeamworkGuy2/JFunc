JFunc
==============
version: 0.4.0

Symmetric set of interfaces for java.util.function, including Tri-functions and primitive consumers and predicates.
Useful for simplifying type signatures by converting generic types like `Consumer<Short>` to `ShortConsumer`.
Includes `IoFunc` container class containing interfaces such as `FunctionIo` and `SupplierIo` which implement the same signatures as their java.util.function counter-parts except that they throw IOException.
Also includes `PropertyAccessor` interface and SimplePropertyAccessor class for proxy access to a value/field.