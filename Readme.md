# Session 5 — NetworkInformation

## 1. Translate the question into a plan

Create four public classes, all in the **same package**, `org.network`:

| Class | Required instance method | Example object-reference name |
|---|---|---|
| `Wifi` | `wifiName()` | `wifi` |
| `MobileData` | `dataName()` | `mobileData` |
| `Lan` | `lanName()` | `lan` |
| `Wireless` | `modamName()` | `wireless` |

Inside `Wifi.main`:

1. Create one object of each class, including `Wifi` itself.
2. Call each object's required method.

**Important naming detail:** the exercise specifies `modamName()`. Keep that exact spelling for the method, even though the usual English word is “modem.” The displayed label can correctly say “Modem Name.” Similarly, preserve the class names `Wifi` and `Lan` exactly as given.

We assume the methods should print fictional network names because the question specifies no input or network-discovery requirement. This program will not read your actual Wi-Fi configuration, connect to a network, or request passwords.

### What you are practicing

This is a consolidation exercise: the same-package pattern from Question 04, expanded from two classes to four. It does not introduce inheritance, multithreading, or real networking APIs.

## 2. Create a separate sibling project

From your current `PhoneDetails` directory:

```bash
cd ..
pwd
mkdir -p NetworkInformation/src/org/network NetworkInformation/.vscode
cd NetworkInformation
code .
```

Your source structure will be:

```text
NetworkInformation/
├── .gitignore
├── README.md
├── .vscode/
│   └── settings.json
└── src/
    └── org/
        └── network/
            ├── Wifi.java
            ├── MobileData.java
            ├── Lan.java
            └── Wireless.java
```

All four Java files go directly inside `src/org/network/`. Do not create a separate package for each class.

### VS Code configuration

Create `.vscode/settings.json`:

```json
{
    "java.project.sourcePaths": ["src"],
    "java.project.outputPath": "out"
}
```

Keep using your installed JDK 25. No additional tools are needed.

### Ignore generated files and backups

Create `.gitignore`:

```gitignore
# Compiled Java files and build output
*.class
out/
bin/
target/

# Editor backups
*~
*.java.save

# Logs
*.log
```

## 3. Write the supporting classes

Each source file begins with the same package declaration. Each public class name must match its filename exactly, including capitalization.

### MobileData.java

Location: `src/org/network/MobileData.java`

```java
package org.network;

public class MobileData {

    public void dataName() {
        System.out.println("Mobile Data Name: Example Mobile");
    }
}
```

### Lan.java

Location: `src/org/network/Lan.java`

```java
package org.network;

public class Lan {

    public void lanName() {
        System.out.println("LAN Name: Example Office LAN");
    }
}
```

### Wireless.java

Location: `src/org/network/Wireless.java`

```java
package org.network;

public class Wireless {

    public void modamName() {
        System.out.println("Modem Name: Example Modem");
    }
}
```

These classes do not need their own `main` methods.

## 4. Write the entry-point class

Create `src/org/network/Wifi.java`:

```java
package org.network;

public class Wifi {

    public void wifiName() {
        System.out.println("Wi-Fi Name: Example Learning Network");
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

There are **four explicit object creations and four instance-method calls**, all inside `Wifi.main`.

No imports are required between these classes because they all belong to `org.network`.

We continue using `var` for clearly initialized local variables and the conventional static entry point. Both are valid on your Java 25 installation; no preview features are required.

## 5. Understand the relationships

### The class and the reference are different

```java
var mobileData = new MobileData();
```

- `MobileData` is the class name.
- `new MobileData()` creates an object and invokes its no-argument constructor.
- `mobileData` is the local variable holding a reference to that object.
- The compiler infers the variable's type as `MobileData`.

The explicit-type equivalent is:

```java
MobileData mobileData = new MobileData();
```

Use either declaration, not both in the same scope.

### An object does not acquire other classes' methods

This works:

```java
mobileData.dataName();
```

This does not:

```java
wifi.dataName();
```

`Wifi` does not define or inherit `dataName()`. Sharing a package allows simple-name access to classes; it does not merge their methods.

### Why create a Wifi object inside Wifi?

`main` is static, while `wifiName()` is an instance method. Creating a `Wifi` object gives us an instance on which to call `wifiName()` and satisfies the requirement to create objects of **all four classes**.

Do not make every method static just to avoid object creation. The assignment is specifically practicing objects and instance methods.

### No inheritance is required

There is no need for `extends`. `Wifi.main` uses the other classes by constructing their objects and invoking their methods.

## 6. Compile and test the compiled output

Save all files. From the `NetworkInformation` project root, run:

```bash
javac --release 25 -d out src/org/network/*.java
java -cp out org.network.Wifi
```

- `--release 25` explicitly sets the Java target.
- Bash expands `*.java` into the Java files directly inside this source directory.
- `-d out` keeps generated bytecode outside `src/`.
- `org.network.Wifi` identifies the compiled entry-point class.

Expected generated files:

```text
out/org/network/Wifi.class
out/org/network/MobileData.class
out/org/network/Lan.class
out/org/network/Wireless.class
```

Expected console output:

```text
Wi-Fi Name: Example Learning Network
Mobile Data Name: Example Mobile
LAN Name: Example Office LAN
Modem Name: Example Modem
```

You can compile and run as a single guarded command:

```bash
javac --release 25 -d out src/org/network/*.java && java -cp out org.network.Wifi
```

The run step happens only if compilation succeeds.

### Use the class-launch form for this checkpoint

In your previous project, you passed a `.java` file to `java`, selecting source-file launch mode. That is a valid alternative workflow. For this checkpoint, use the command above with **`org.network.Wifi`**, so you specifically test the compiled classes under `out/`.

Your usual command also compiles these files successfully for the simple paths used here:

```bash
javac -d out $(find src -name "*.java")
```

The directory wildcard is just shorter for this one-package project. Do not run both compile commands unless you have a reason to rebuild.

## 7. Add a README

Create `README.md`:

````markdown
# NetworkInformation

Core Java OOP — Question 05.

## Objective

Create Wifi, MobileData, Lan, and Wireless objects inside Wifi.main,
then call each object's required instance method.

All classes belong to org.network.

## Classes and methods

| Class | Method |
|---|---|
| Wifi | wifiName() |
| MobileData | dataName() |
| Lan | lanName() |
| Wireless | modamName() |

The method name modamName() intentionally follows the assignment spelling.

## Requirements

JDK 25. No external dependencies or build tools are required.

## Compile and run

From the project root:

```bash
javac --release 25 -d out src/org/network/*.java
java -cp out org.network.Wifi
```

## Expected output

```text
Wi-Fi Name: Example Learning Network
Mobile Data Name: Example Mobile
LAN Name: Example Office LAN
Modem Name: Example Modem
```

## Concepts practiced

- Multiple classes in one package
- Object creation and references
- Instance-method calls
- Local-variable type inference with var
- Multi-file compilation and class launching

All network names are fictional. This program does not access real networks.
````

## 8. Review, commit, and publish

First check that you are not accidentally inside another repository:

```bash
git rev-parse --show-toplevel
```

- If it reports **not a git repository**, initialize below.
- If it prints the `NetworkInformation` directory, skip initialization.
- If it prints a parent directory, resolve the repository layout before creating a nested repository.

For a new independent repository:

```bash
git init -b main
```

Stage only the intended files:

```bash
git add src/org/network/Wifi.java src/org/network/MobileData.java src/org/network/Lan.java src/org/network/Wireless.java .vscode/settings.json .gitignore README.md
git diff --cached --stat
git diff --cached
```

Confirm that there are no `.class`, `~`, or `.java.save` files in the staged changes. Then commit:

```bash
git commit -m "Complete NetworkInformation OOP exercise"
```

Create an empty GitHub repository named `NetworkInformation` under your account. Do not initialize it with another README or ignore file.

If you use the same account as your previous projects, and no `origin` is configured yet:

```bash
git remote add origin https://github.com/ub-victoire/NetworkInformation.git
git push -u origin main
git status
```

Use the actual repository URL if you choose another account or name. Inspect the hosted repository before submitting its URL.

## 9. Common mistakes

| Symptom | Check |
|---|---|
| `cannot find symbol: class MobileData` | Filename, class spelling, shared package declaration, and inclusion in compilation. |
| `cannot find symbol: method modemName()` | The required method is spelled `modamName()` in this assignment. Declaration and call must match. |
| `non-static method ... cannot be referenced from a static context` | Call the method on its object, such as `wifi.wifiName()`. |
| `Main method not found` when launching a supporting class | Launch `org.network.Wifi`. |
| Only three objects are created | Remember the `Wifi` object too. |
| Output is outdated | Save all files, recompile, and launch the compiled class again. |
| Generated files appear in Git | Check ignore rules and whether the files were tracked before those rules were added. |

## 10. Final checklist

- [ ] Separate project/repository named `NetworkInformation`.
- [ ] Four correctly named public classes in `org.network`.
- [ ] Required methods use their exact specified names.
- [ ] Four objects are created inside `Wifi.main`.
- [ ] Four methods are called through the correct object references.
- [ ] No unnecessary imports, inheritance, or static replacements for instance methods.
- [ ] Class-launch command prints the four expected lines.
- [ ] README explains how to build and run.
- [ ] Generated files and backups are not staged.
- [ ] Intended files are pushed to the remote repository.

## 11. Small experiment before submitting

Temporarily comment out this call:

```java
wireless.modamName();
```

Save, recompile, and run. You should see only three output lines even though the `Wireless` object is still created. This demonstrates that **creating an object and calling its method are separate operations**.

Restore the call, rebuild, and verify all four lines before committing.

**Next checkpoint:** share your four source files and the output from `java -cp out org.network.Wifi`. Output checks the visible behavior; source review checks the required structure.
