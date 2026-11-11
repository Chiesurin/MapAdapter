# MapAdapter

A Java implementation of the **J2SE 1.4.2** `Map` Collections Framework subset (`HMap`, `HMap.HEntry`, `HSet`, `HCollection`, `HIterator`) using the **Adapter** pattern (`myAdapter.MapAdapter`) backed by Java ME **CLDC 1.1**'s `java.util.Hashtable`.

## Overview & Constraints

Because the production code (`myAdapter/` and `myExceptions/`) targets **Java ME CLDC 1.1**, it adheres strictly to CLDC 1.1 / J2SE 1.4.2 language and library constraints:

- **Language level**: No Java 5+ syntax (no generics, annotations, enhanced `for` loops, varargs, enums, or Java 7 multi-catch).
- **CLDC 1.1 APIs only**: Backed solely by `java.util.Hashtable` (`Hashtable()`, `Hashtable(int)`, `clear`, `contains`, `containsKey`, `get`, `isEmpty`, `keys`, `put`, `remove`, `size`) and `java.util.Enumeration` / `java.util.NoSuchElementException`.
- **Null restriction**: Following CLDC 1.1 `Hashtable` semantics, neither `null` keys nor `null` values are permitted; passing `null` throws `NullPointerException`.
- **Backed collection views**: `entrySet()`, `keySet()`, and `values()` (and their iterators) are dynamically backed by the underlying `MapAdapter`. Structural modifications via views or iterators (`remove`, `removeAll`, `retainAll`, `clear`, `HEntry.setValue`) reflect in the map and vice versa. `add` and `addAll` throw `myExceptions.UnsupportedOperationException`.
- **No reflection**: Because CLDC 1.1 omits `java.lang.reflect.Array`, `toArray(Object[])` allocates `new Object[size()]` when the supplied array is smaller than the collection.
- **Custom exceptions**: `myExceptions.UnsupportedOperationException` and `myExceptions.IllegalStateException` provide CLDC 1.1-compatible runtime exceptions (missing in CLDC 1.1).

## Repository Layout

```text
MapAdapter/
├── myAdapter/        # J2SE 1.4.2 collection interfaces & MapAdapter (CLDC 1.1 compatible)
├── myExceptions/     # CLDC 1.1 runtime exception classes (IllegalStateException, UnsupportedOperationException)
├── myTest/           # JUnit 4.13 test suite, runner, and dummy test helpers
├── JUnit/            # Bundled JUnit 4.13 & Hamcrest 1.3 JARs for offline compilation/execution
└── LICENSE           # MIT License
```

## Build, Test & Javadoc

All commands assume the repository root (`MapAdapter/`) as the working directory.

### Compile

```bash
mkdir -p bin
javac -d bin -cp .:JUnit/junit-4.13.jar:JUnit/hamcrest-core-1.3.jar \
  myAdapter/*.java myExceptions/*.java myTest/*.java
```

### Run the Test Suite

```bash
java -cp bin:JUnit/junit-4.13.jar:JUnit/hamcrest-core-1.3.jar myTest.TestRunner
```

### Generate Javadoc

```bash
javadoc -d doc -cp .:JUnit/junit-4.13.jar:JUnit/hamcrest-core-1.3.jar \
  myAdapter/*.java myExceptions/*.java myTest/*.java
```

## License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.
