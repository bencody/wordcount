# Word Count Assessment: Benjamin Cody

## Reference

https://ccd-school.de/coding-dojo/agility-katas/word-count-i/


## Assumptions

- Words must consist only of valid characters. "A1" would not be considered a word because the character "1" is not considered valid. 
- Average word count is calculated based on all words, not unique words.
- Average word count output should include exactly 2 decimal places and use English number formatting.

## Checklist

### How often do you commit / push (more often is better) => Why is this is good? Trade-offs?

Commits should ideally represent a working (but not necessarily complete) version. 

Since the iterations in this assessment are already very small, I don't always see a necessity for commits in-between. IntelliJ and Eclipse store a local history of changes, which allows one to switch back to a previous change that was not yet committed.
 
### Are there automated unit- and integration-tests ( for this small hiring example we expect 100% test coverage) => Why is this is good? Trade-offs?

Yes. 100% at the moment.

If one strictly follows the principles of SoC and domain/IO separation, and starts writing tests for the smaller unit components first and later adds integration tests, some tests may be repetitive and execute the same implementation multiple times in the same way.

Example: I created `at.sitsolutions.wordcount.io.cli.StdSystem`, `at.sitsolutions.wordcount.io.cli.CliService`, and `at.sitsolutions.wordcount.io.cli.Main` in that order. `StdSystemTest` made sense at first, but after I added `CliServiceTest` and `MainTest`, `StdSystemTest` became redundant. It did not add any value, so I decided to delete it to keep the test code more maintainable.

### Check for separate by concerns => What does it mean? Why is this is good? Trade-offs?
 
Different concerns (such as making regular expressions Java 8 streamable, the logic of counting words, providing a command-line interface, etc) are implemented in separate classes. 

This separation makes the separate concerns more clear, easier to test, which is a good thing.

A trade-off is that it involves more classes, and in such a small application, this subjectively increases its complexity. In larger applications the benefits will be more significant and obvious.

### Decouple all I/O from Domain => What does it mean? Why is this good? Trade-offs? (Clean Architecture, Hexagonal Architecture, Onion Architecture)

The domain logic of this application is the counting of words in a given text, and the IO logic deals with the command-line interface. Splitting these layers is currently done by having separate Java packages.

The IO and domain separation allows the domain functionality to be made accessible via other ports (e.g. an HTTP API), also keeps the domain free from technology-related dependencies and therefore easier to understand.

Trade-offs are similar to SoC. Additionally, if one were to separate the layers more strictly with (Java 9, Maven, or OSGI) modules, the project's set-up would become more complex and the build could also take a bit longer. Modules are arguably not necessary for smaller applications, though.

### Is the UI decoupled from Domain => Why is this important? Trade-offs?

At the moment, the command-line interface is the UI, and it is decoupled. Similar answers as above.

### Expect you to improve your design over time by Refactoring. => Why is this important? Trade-offs? What code-smells do know?

The combination of 1) following SoC, SOLID, and domain/IO separation principles and 2) writing good tests is a recipe for code that is easy to change as business requirements change.

As code changes over time, it tends to become less well-structured and therefore more difficult to understand. Refactoring is a measure against this gradual entropy and allows it to remain maintainable and modifyable.

A trade-off of refactoring is that it has an implementation, code-review, and testing cost. Refactoring end-of-life software may not be economical.      
 
### Are the SOLID principles applied?

Yes.
 
### Can you find the requirements in the code?

The requirements can be found in the code, but not all parts of the code map to a requirement. At the moment the `RegexpUtils` offer functionality that is not required per se, but it is a part of the implementation that assists in making the `WordCounter` simpler.
  
### Is the code intention revealing?

Currently, I would say that it is. The principles of SoC and separation of domain from IO code are adhered to. The class and method names are clear. The unit test names follow the recommendations from Unit Testing (Khorikov, 2020) and "tell a story".

### Switching from on iteration to the next one we expect compilable code which has 100% test coverage and all tests are green and the code is "CLEAN" and requirements of iteration are fulfilled. => What does Clean Code mean to you?

Answer:  
