# 🧑‍💻 Contributing to Java-Programs | Hacktoberfest 2025
**Welcome!** Your high-quality Java programs are the core of this Hacktoberfest repository. By following these guidelines, you ensure a fast merge and a clean, valuable resource for everyone.

🤝 Prerequisites & Agreements
By contributing to this repository, you agree to the following:

* Contributor License Agreement (CLA): You agree to the terms defined in the [LICENSE](https://github.com/IamBisrutPyne/Java-Programs/blob/main/LICENSE) of this repository.

* Code of Conduct (CoC): You agree to respect the [Code of Conduct](https://github.com/IamBisrutPyne/Java-Programs/docs/blob/main/COC).

## ☕ In a Nutshell: Contribution Flow
1. Find an Idea: Check the Open Issues for programs to implement.

2. Fork & Clone: Fork the repository and clone your fork locally.

3. Code & Structure: Write your Java program and place it in the correct folder (see Structure).

4. Commit & PR: Commit your changes using a descriptive message and open a Pull Request (PR).

* Crucial for Hacktoberfest: Submit one new program per PR. A PR must be merged or labeled with hacktoberfest-accepted by a maintainer to count.

## 📂 Repository Structure & Naming
To maintain navigation and consistency, please adhere to these structure and naming rules.

### 1. Folder Placement
Place your ```.java file``` into the most appropriate existing subfolder. If you need to create a new folder, ensure it is logically distinct (e.g., ```Concurrency```, ```Testing```).

| Folder Name | Focus | Example Programs |
| :--- | :--- | :--- |
| **Beginner** | Simple console programs, basic syntax demos. | `HelloWorld.java`, `FactorialCalculator.java` |
| **DataStructures** | Implementations of lists, trees, graphs, etc. | `LinkedListImplementation.java`, `StackUsingArray.java` |
| **Algorithms** | Sorting, searching, pathfinding, mathematical algorithms. | `MergeSort.java`, `DijkstraAlgorithm.java` |
| **OOP** | Demonstrations of Java's core OOP concepts. | `CarClassExample.java`, `InterfaceDemo.java` |

### 2. File Naming Convention
Your Java file and the main class within it must be named descriptively using PascalCase.

* ✅ GOOD: DisariumNumber.java, BinarySearchTree.java

* ❌ BAD: disarium_number.java, disarium.java

## 📜 Code Metadata & Style
All submissions must include metadata and follow strict Java style rules for maximum clarity and learning value.

### 1. Javadocs-Style Header (Mandatory Metadata)
Every Java file must begin with a descriptive Javadocs-style header comment. This acts as the essential "metadata" for your code artifact.
```java
/**
 * Program Title: [Concise and Descriptive Title]
 * Author: [Your Name or GitHub Handle]
 * Date: [Date of Submission, YYYY-MM-DD]
 *
 * Description: [Detailed explanation of what the program does and how it works.]
 * Time Complexity: [O(n), O(log n), O(1), etc., for algorithms]
 * Space Complexity: [O(n), O(1), etc., for memory usage]
 */
public class MyProgramName {
    // ... code ...
}
```
### 2. General Java Style Guidelines
* **Clarity:** Prioritize simplicity and readability. The code's primary goal is to teach the concept.

* **Indentation:** Use 4 spaces for indentation, never tabs.

* **Brace Style**: Use K&R style (opening brace on the same line, closing brace on its own line).

* Resource Handling: Always use ```try-with-resources``` for resources (e.g., ```Scanner```, ```FileReader```) to ensure safe closure.

* Imports: Only use specific imports (e.g., ```import java.util.Scanner;```) instead of wildcards (```import java.util.*;```).

* Main Method: Include a working ```main``` method that demonstrates a functional example of the program.

### 3. Pull Request Formatting
* Single Program per PR: Submit only one new Java program per Pull Request.

* Meaningful Title: Use the Commit Formatting rules below for your PR title.

* Description Template: Use this detailed template in your PR description:
```
## Program Details

**Type of Program:** (e.g., Algorithm, Data Structure, OOP Concept)
**Concept Implemented:** (e.g., Disarium Number, Binary Search)
**Known Limitations:** (e.g., "Only works for positive integers," "Not optimized for concurrent use")

## Check List

- [ ] The file is placed in the correct subfolder.
- [ ] The file and main class are named using PascalCase.
- [ ] The code includes the required Javadocs-style header metadata.
- [ ] The code follows 4-space indentation and K&R brace style.
- [ ] A working `main` method is included to demonstrate the program.
```
### 4. Commit Formatting (Atomic Commits)
Prefer atomic commits (one commit per feature/program). Use the following format for your commit messages:

* Format: ```<type>: <description>```

* ✅ GOOD: feat: Add Binary Search Tree Implementation

* ❌ BAD: fixed bug, pr 1, new code

## 🚩 Hacktoberfest Success
We want your PRs to count!

* **Avoid Spam:** Contributions must be meaningful and adhere to all code quality standards. Low-effort or trivial PRs will be marked as ```spam``` and will not count.

* **Label Confirmation:** Your PR will be reviewed as quickly as possible. Once accepted, a maintainer will add the ```hacktoberfest-accepted``` label. Only then will it count toward your progress.

**Thank you for your contribution! Happy Hacking! 💻**
