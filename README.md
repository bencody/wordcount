# Word Count Assessment: Benjamin Cody

## Reference

https://ccd-school.de/coding-dojo/agility-katas/word-count-i/


## Assumptions

- Words must consist only of valid characters. "A1" would not be considered a word because the character "1" is not considered valid. 
- Average word count should be calculated on all words, not unique words.
- Average word count output should include exactly 2 decimal places and use English number formatting.
- Unknown word count should be calculated on unique words, not all words.
- Word index may be ordered by occurrence. The requirement is not explicit about the ordering.
- https://ccd-school.de/coding-dojo/agility-katas/word-count-ix/ example output for input "a bb ccc dddd" is wrong. The word "a" is a stop-word and should not be considered in the results.

## Checklist

### How often do you commit / push (more often is better) => Why is this is good? Trade-offs?

Commits should ideally represent a working (but not necessarily feature-complete) version. Commits should ideally made at least once per day. 

Since the iterations in this assessment are already very small, I did not always see a necessity for multiple commits in-between. IntelliJ and Eclipse store a local history of changes, which allows one to switch back to a previous change that was not yet committed.

Trade-offs: A feature's implementation may be split across multiple commits, making it harder to see which changes were made for a feature. If one dislikes this, one will need to execute more version control operations.  
 
### Are there automated unit- and integration-tests ( for this small hiring example we expect 100% test coverage) => Why is this is good? Trade-offs?

Yes. 100% at the moment.

If one strictly follows the principles of SoC and domain/IO separation, and starts writing tests for the smaller unit components first and later adds integration tests, some tests may be repetitive and execute the same implementation multiple times in the same way.

Example: I created `at.sitsolutions.wordcount.io.cli.StdSystem`, `at.sitsolutions.wordcount.io.cli.CliService`, and `at.sitsolutions.wordcount.io.cli.Main` in that order. `StdSystemTest` made sense at first, but after I added `CliServiceTest` and `MainTest`, `StdSystemTest` became redundant. It did not add any value, so I decided to delete it to keep the test code more maintainable.

### Check for separate by concerns => What does it mean? Why is this is good? Trade-offs?
 
Different concerns (such as making regular expressions Java 8 streamable, the logic of counting words, providing a command-line interface, etc) are implemented in separate classes. 

This separation makes the software's function more clear and easier to test.

A trade-off is that it involves more classes, and in such a small application, this subjectively increases its complexity as the reader needs to read many files. In larger applications the benefits will be more significant and obvious. As this "Word Count" application grew, I started appreciating the strict structure more and more.

### Decouple all I/O from Domain => What does it mean? Why is this good? Trade-offs? (Clean Architecture, Hexagonal Architecture, Onion Architecture)

The domain layer deals with the following:
- Identifying words in a text.
- Filtering out stop-words.
- Matching against dictionary words.
- Calculating statistics.

The IO/CLI layer deals with the following:
- Bootstrapping the CLI program and parsing arguments. 
- Reading text from either stdin or a file.
- Printing results to stdout. 

This domain/IO separation easily allows the domain's functionality to be made accessible via other IO implementations (e.g. an HTTP API). The domain layer remains free from technology-related dependencies and is therefore easier to understand and test, and it will remain unchanged if additional IO implementations are added.

Trade-offs are similar to SoC. Additionally, if one were to separate the layers more strictly with (Java 9, Maven, or OSGI) modules, the project's set-up would become more complex and the build could also take a bit longer. Modules are arguably not necessary for smaller applications, though.

### Is the UI decoupled from Domain => Why is this important? Trade-offs?

At the moment, the command-line interface is the UI, and it is decoupled. See answers as above.

### Expect you to improve your design over time by Refactoring. => Why is this important? Trade-offs? What code-smells do know?

The combination of 1) following SoC, SOLID, and domain/IO separation principles and 2) writing good tests is a recipe for code that can easily get adapted to changing business requirements.

As code changes over time, it tends to become less well-structured and therefore more difficult to understand. Refactoring is a measure against this gradual entropy and allows it to remain maintainable.

A trade-off of refactoring is that it has an implementation, code-review, and testing cost. Refactoring end-of-life software may not be economical.      
 
### Are the SOLID principles applied?

Yes.
 
### Can you find the requirements in the code?

The requirements can be found in the code, but not all parts of the code map to a requirement. At the moment the `RegexpUtils` offer functionality that is not required per se, but it is a part of the implementation that assists in making the `WordCounter` simpler.
  
### Is the code intention revealing?

Currently, I would say that it is. The principles of SoC and separation of domain from IO code are adhered to. The class and method names are clear. The unit test names follow the recommendations from Unit Testing (Khorikov, 2020) and "tell a story".

### Switching from on iteration to the next one we expect compilable code which has 100% test coverage and all tests are green and the code is "CLEAN" and requirements of iteration are fulfilled. => What does Clean Code mean to you?

A couple of "clean code" attributes:

- Principles such as SoC, SOLID, and domain/IO separation are adhered to.
- The code is self-explanatory. 
  - Comments are used only where they are valuable.
  - Artifacts have meaningful names.
  - Common architectural concepts, design patterns, language idioms, etc. are used.
- There is no waste:
  - There is no unnecessary duplication.
  - There are no unused artifacts littering the code-base (e.g. commented-out code, unused classes, unused libraries).
- One cannot tell that multiple developers worked on the code:
   - Consistent formatting.
   - Consistent use of language/framework/library features and patterns.
