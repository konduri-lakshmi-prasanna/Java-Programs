# ☕ Java-Programs | Hacktoberfest 2025 🎉

[![GitHub contributors](https://img.shields.io/github/contributors/IamBisrutPyne/Java-Programs.svg)](https://github.com/IamBisrutPyne/Java-Programs/graphs/contributors)
[![GitHub issues](https://img.shields.io/github/issues/IamBisrutPyne/Java-Programs.svg)](https://github.com/IamBisrutPyne/Java-Programs/issues)
[![GitHub pull requests](https://img.shields.io/github/issues-pr/IamBisrutPyne/Java-Programs.svg)](https://github.com/IamBisrutPyne/Java-Programs/pulls)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Hacktoberfest 2025](https://img.shields.io/badge/Hacktoberfest-2025-orange.svg)](https://hacktoberfest.com/)

---

<img width="400" height="300" alt="Hacktoberfest-Logo" src="https://github.com/user-attachments/assets/5f9a47d2-e571-485a-9b69-25d6e9f8ddd9" />


## 🎯 Welcome to Java-Programs!

This repository is a **hand-picked collection of Java programs**, designed specifically for new and seasoned contributors to participate in **Hacktoberfest 2025**!

Whether you're looking for simple **introductory programs** to make your first commit or searching for **challenging algorithms**, this is the perfect place to contribute and level up your Java skills.

Dive into the code, fix bugs, add new features, or contribute your own unique Java program. Let's make this the most vibrant Java resource for Hacktoberfest!

### 💡 What You'll Find Here

* **Beginner Programs:** Simple programs (e.g., Prime Number Checker, Factorial).
* **Data Structures & Algorithms:** Implementations of common DS&A in Java.
* **Object-Oriented Programming (OOP) Examples:** Clear demonstrations of Java OOP principles.
* **Utility Snippets:** Useful Java code for common tasks.

---

## 🚀 Getting Started

Ready to contribute? It's simple! Follow these steps to get your first contribution in:

1.  **Star** ⭐ and **Fork** 🍴 this repository.
2.  **Clone** your forked repository to your local machine:
    ```bash
    git clone [https://github.com/](https://github.com/)IamBisrutPyne/Java-Programs.git
    ```
3.  **Create a new branch** for your contribution:
    ```bash
    git checkout -b feature/<your-feature-name>
    ```
4.  **Add your Java program** (e.g., `MyAwesomeProgram.java`) into the appropriate subfolder (e.g., `Beginner/` or `Algorithms/`).
5.  **Commit** your changes:
    ```bash
    git commit -m "feat: Add My Awesome Program for Hacktoberfest"
    ```
6.  **Push** your branch to your forked repository:
    ```bash
    git push origin feature/<your-feature-name>
    ```
7.  **Create a Pull Request (PR)** from your forked repository back to the main `Java-Programs` repository.

> **❗ Important:** Please ensure your code follows our [Contributing Guidelines](#📜-contributing-guidelines)!

---

## 💻 Sneak Peek: Code Examples

To give contributors a taste of the content and required coding style, here is a quick example of a clean Java program you might find or contribute.

### Example: Disarium Number
A clean, commented, and well-formatted Java file is key!

```java
/**
 * Program to check if a given number is a Disarium Number.
 * A Disarium Number is a number where the sum of its digits raised to their respective positions equals the number itself.
 */

import java.util.*;

public class DisariumNumber {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter a number: ");
            int a = sc.nextInt();

            if (isDisarium(a)) {
                System.out.println("Disarium");
            } else {
                System.out.println("Not Disarium");
            }
        }
    }

    public static boolean isDisarium(int n) {
        int sum = 0;
        String numStr = String.valueOf(n);

        for (int i = 0; i < numStr.length(); i++) {
            int digit = numStr.charAt(i) - '0';
            sum += (int) Math.pow(digit, i + 1);
        }

        return sum == n;
    }
}
