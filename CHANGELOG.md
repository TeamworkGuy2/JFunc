# Change Log
All notable changes to this project will be documented in this file.
This project does its best to adhere to [Semantic Versioning](http://semver.org/).


--------
### [0.4.0](N/A) - 2017-11-09
#### Changed
* Flattened `twg2.functions.IoFunc` nested functions into separate files: `BiConsumerIo`, `BiFunctionIo`, `ConsumerIo`, `FunctionIo`, and `SupplierIo`

#### Fixed
* `twg2.functions.predicates` `String` and `Enum` class names fixed to `StringPredicate` and `EnumPredicate`


--------
### [0.3.0](https://github.com/TeamworkGuy2/JFunc/commit/055a39772450f412ff237069d367a913c8923ebd) - 2017-08-20
#### Added
* new `twg2.functions.consumers` package containing the interfaces that were previously nested inside `twg2.functions.Consumers`
* new `twg2.functions.predicates` package containing the interfaces that were previously nested inside `twg2.functions.Predicates`

#### Removed
Removed unused `twg2.functions` interfaces, kept generators in place incase there is a use for these interfaces in future.
* `BiConsumers`
* `BiPredicates`
* `Consumers` (split and moved to `twg2.functions.consumers`)
* `Functions`
* `Predicates` (split and moved to `twg2.functions.predicates`)
* `Suppliers`


--------
### [0.2.1](https://github.com/TeamworkGuy2/JFunc/commit/e8f020bbf15932a7b4d50f23e7e7b6309a1ce2eb) - 2016-06-26
#### Changed
* Changed from versions.md to CHANGELOG.md for change documentation, see http://keepachangelog.com/


--------
### [0.2.0](https://github.com/TeamworkGuy2/JFunc/commit/b915943992b1a651fcf58ad87bcd5e621600ae53) - 2016-02-21
#### Removed
* Removed BiFunctions interfaces and disabled generator for it


--------
### [0.1.0](https://github.com/TeamworkGuy2/JFunc/commit/1e98f3a0e3d147f5b8e729642487454213a9aad2) - 2016-01-27
#### Added
* Initial versioning of existing code, including a lot of interfaces for primitive, bi*, and tri* parameter consumer, supplier, predicate, and functions
* Added IoFunc with a few common interfaces such as Function, Consumer, BiFunction, etc. that throw IOException
* Moved `twg2.annotations` package to [JInterfaces](https://github.com/TeamworkGuy2/JInterfaces) library