# NetworkInformation: A Java Lesson on Classes and Objects

This project is a small Java console program for practicing classes, objects, packages, and instance methods. It prints a label from each of four classes: `Wifi`, `MobileData`, `Lan`, and `Wireless`.

The program is an object-oriented programming exercise, not a network diagnostic tool. It does not connect to Wi-Fi, inspect a local network, use mobile data, or communicate with a modem.

**Author:** Ushindi B. Victoire - Software QA Engineer | Requirements Specialist | GitHub: [@ub-victoire](https://github.com/ub-victoire) · [@ub-victor](https://github.com/ub-victor)

## What you will learn

By working through the lesson, you will learn how to:

- organize related Java classes in a package;
- define a class and an instance method;
- create objects with `new`;
- store object references in local variables;
- call an instance method with the dot operator;
- compile several source files and run a class by its fully qualified name.

## 1. Network terms used in the example

The class names refer to familiar networking terms, but each class only prints a sample name.

| Term | Meaning |
|---|---|
| Wi-Fi | A wireless way for devices to join a local network. |
| Mobile data | Internet or network access provided through a cellular mobile network. |
| LAN | A local area network connecting devices in a limited area, such as a home, school, or office. |
| Modem | A device that connects a network to an internet service, depending on the type of connection. |

These technologies are related, but they are not interchangeable. For example, Wi-Fi can connect a phone to a LAN, while mobile data uses a cellular network. This program does not model those connections; the terms are used to name the example classes.

## 2. Project structure

The Java package is `org.network`. The folder names under `src` match that package:

```text
NetworkInformation/
└── src/
    └── org/
        └── network/
            ├── Lan.java
            ├── MobileData.java
            ├── Wifi.java
            └── Wireless.java
```

Each public class is in a file with the same name, including capitalization. For example, `public class Lan` is declared in `Lan.java`.

## 3. Define the supporting classes

Each class declares a method that prints one line. These methods are instance methods: they belong to objects created from their classes.

### `Lan.java`

```java
package org.network;

public class Lan {
    public void lanName() {
        System.out.println("Lan name: Test Solution Lan");
    }
}
```

### `MobileData.java`

```java
package org.network;

public class MobileData {
    public void dataName() {
        System.out.println("Mobile Data Name: Airtel");
    }
}
```

### `Wireless.java`

```java
package org.network;

public class Wireless {
    public void modamName() {
        System.out.println("Modem Name: TSR Modem");
    }
}
```

The method is spelled `modamName()` in this exercise. Java treats it as an identifier, so its spelling in the declaration and in every call must match exactly.

## 4. Create objects and call their methods

`Wifi` contains the program's entry point, `main`, and creates one object from each class:

```java
package org.network;

public class Wifi {

    public void wifiName() {
        System.out.println("Wife name: TestSolutionRwanda");
    }

    public static void main(String[] args) {
        var wifi = new Wifi();
        var mobileData = new MobileData();
        var lan = new Lan();
        var wireless = new Wireless();

        wifi.wifiName();
        mobileData.dataName();
        lan.lanName();
        wireless.modamName();
    }
}
```

### Read the object creation statements

Consider this line:

```java
var mobileData = new MobileData();
```

- `MobileData` is the class.
- `new MobileData()` creates a `MobileData` object.
- `mobileData` is a local variable that refers to that object.
- `var` lets Java infer the variable's type from the expression on the right. Here, the inferred type is `MobileData`.

The same declaration with an explicit type is:

```java
MobileData mobileData = new MobileData();
```

Both forms create the same kind of object. This project uses `var`, which requires Java 10 or later.

### Read the method calls

The dot operator calls a method on a particular object:

```java
mobileData.dataName();
```

This calls `dataName()` on the object referenced by `mobileData`. Each method belongs to its own class; for example, `dataName()` is not a method of `Wifi`.

There are four object creations and four method calls in `main`. Creating an object and calling its method are separate actions: an object can be created without its method being called.

### Why is `main` static?

Java starts this program at:

```java
public static void main(String[] args)
```

The `main` method is `static`, so the Java runtime can call it without first creating a `Wifi` object. The `wifiName()` method is not static, so `main` creates a `Wifi` object and calls `wifiName()` on it.

The other classes are in the same package, so `Wifi` can refer to them by their simple class names. A shared package does not combine the classes or give one class access to another class's methods; each method is still called on an object of the class that defines it.

## 5. Compile and run the program

You need a JDK, version 10 or later, because the code uses local-variable type inference with `var`. Run the following commands from the project root (the directory containing `src`).

First, create an output directory and compile the Java files:

```bash
mkdir -p out
javac -d out src/org/network/*.java
```

Then run the class containing `main`:

```bash
java -cp out org.network.Wifi
```

Here is what the commands do:

- `javac` compiles the source files into Java bytecode.
- `-d out` places the generated `.class` files in `out`, keeping them separate from the source files.
- `-cp out` tells the Java runtime where to find the compiled classes.
- `org.network.Wifi` is the fully qualified class name: package name plus class name.

The output should be:

```text
Wife name: TestSolutionRwanda
Mobile Data Name: Airtel
Lan name: Test Solution Lan
Modem Name: TSR Modem
```

The first line reflects the current string in `Wifi.wifiName()`: it says `Wife name`. If the intended label is `Wi-Fi name`, change that string in the `System.out.println` call in `Wifi.java`. The other values are also example text, not information read from the computer or a network.

You can combine compilation and execution with `&&`:

```bash
javac -d out src/org/network/*.java && java -cp out org.network.Wifi
```

The runtime command runs only if compilation succeeds.

## 6. Common errors and how to fix them

| Error or symptom | What to check |
|---|---|
| `cannot find symbol` for a class | Check the class name, filename, `package org.network;` declaration, and whether the file was included in compilation. |
| `cannot find symbol` for a method | Check the method spelling and capitalization in both its declaration and its call. |
| `non-static method ... cannot be referenced from a static context` | Create an object and call the method on it, such as `wifi.wifiName()`. |
| `Main method not found` | Run `org.network.Wifi`, not one of the supporting classes. |
| No output for one of the classes | Check that its object was created and its method call is present in `main`. |
| Output does not reflect a recent edit | Save the source file, compile again, and then run the compiled class. |

## 7. Practice

Try these small changes after the program runs:

1. Change one printed sample name, then compile and run again.
2. Change `Wife name` to `Wi-Fi name` in `Wifi.java`. What changes in the output?
3. Comment out `wireless.modamName();`, then compile and run. The object is still created, but its method no longer prints a line. Restore the call afterward.
4. Replace `var lan = new Lan();` with `Lan lan = new Lan();`. Compile and confirm that the output is unchanged.
5. Add a new method to a class and call it from `main`. What must you do to make sure the method is called on an object of the correct class?

For each change, keep the Java class name, filename, package, and method spelling consistent.

## Key ideas

- A **class** defines a type; an **object** is an instance created from that type.
- A **reference variable** such as `mobileData` lets the program use an object.
- An **instance method** is called on an object, as in `mobileData.dataName()`.
- A **package** organizes related classes and corresponds to the source directory structure.
- This lesson prints fixed example text. It does not discover or connect to real networks.
