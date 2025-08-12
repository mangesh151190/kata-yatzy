## Yatzy

The problem that this code is designed to solve is
explained [here](https://sammancoaching.org/kata_descriptions/yatzy.html)

The codebase contains a Java and a Kotlin implementation. Choose one to refactor.

## Run the tests

```shell
# linux
./gradlew check
```

```shell
# windows
.\gradlew.bat check
```

## Stack

- Java 17
- Gradle
- AssertJ
- Junit5

**Objectives**

- Treat this project as a professional, production-level codebase.
- Ensure all code changes prioritize quality, maintainability, and scalability.

  > Your task is to score a GIVEN roll in a GIVEN category. You do NOT have to program the random dice rolling. The game is NOT played by letting the computer choose the
  highest scoring category for a given roll.
- **Requirements**
    - **Analyze and Improve Existing Code**
        - Apply *Clean Code* principles to refactor the code.
            - Enhance readability and structure.
            - Eliminate code smells and anti-patterns.
            - Improve testability and maintainability.
    - **Feature Addition**
        - **Implement Game Simulation**
            - Modify the application to simulate rolling dice **three times**, emulating an actual Yatzy game session.
            - After each roll, calculate the score and determine the corresponding category based on the dice outcome.
            - Output the results to the standard output stream using the following format:
            ```text
            EXAMPLE OUTPUT:
              
            1. ROLL
            You've chosen YATZY as score category
            Score: 50
            You've got a YATZY
              
            2. ROLL
            You've chosen THREE_OF_A_KIND as score category
            Score: 0
            You've got NOTHING
              
            3. ROLL
            You've chosen SMALL_STRAIGHT as score category
            Score: 15
            You've got a SMALL_STRAIGHT
            ```
        - **Enhance Output Configurability**
            - Refactor the code to allow easy modification of the output format.
            - Implement flexibility to change the output stream (e.g., console, file, network socket) with minimal code adjustments.
- **Additional Requirements**
    - **Documentation**
        - Document all code changes thoroughly.
        - Update or create a README file explaining your enhancements and instructions for running the application.
    - **Testing**
        - Write unit tests to cover both existing and new functionalities.
        - Ensure all tests pass before submission.
    - **Design Patterns**
        - Consider applying relevant design patterns to improve code structure and efficiency.
