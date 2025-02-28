# CA1 Part1 - Technical Report

## Introduction
This report documents the technical steps taken to complete the DevOps assignment for CA1 part1. It includes an analysis of the tools and methodologies used, details of the implementation, and justifications for the choices made. The project focuses on utilizing Git for version control, running Maven to manage dependencies and tests, and debugging the application to ensure its correctness.

## Part 1: Initial Setup
### Cloning the Repository
The first step, after creating the folders and the github repository, was to clone the **Tutorial React.js and Spring Data REST** application from the official repository:

```sh
git clone https://github.com/spring-attic/tut-react-and-spring-data-rest
```

Steps:
1. Navigate to the desired directory:
   ```sh
   cd ~/Documents/devops-24-25-1241903/CA1/part1/
   ```
2. Clone the repository:
   ```sh
   git clone https://github.com/spring-attic/tut-react-and-spring-data-rest 
   ```
3. Navigate to the cloned directory:
   ```sh
   cd CA1/part1
   ```
4. Verify the setup:
   ```sh
   ls -la
   ```

### Running the Project Locally
After cloning, the next step was to verify if the project ran locally:

```sh
./mvnw spring-boot:run
```

Once confirmed that the application was running on localhost, the next step was to move the `basic` folder into **IntelliJ IDEA** to start implementing the required modifications. The other folders were deleted for this assigment.

### Creating the .gitignore File

To avoid committing unnecessary files, a .gitignore file was created and customized to exclude unwanted files from version control. The file was generated using gitignore.io and manually supplemented.

Steps:

Created the file using the terminal:

```sh
touch .gitignore
```

Generated an initial configuration using Gitignore.io:

# Created by https://www.toptal.com/developers/gitignore/api/node,react,maven,java

Manually added additional entries to ignore logs, IDE-specific files, and system-generated artifacts:

### Java ###
*.class
*.log
.mtj.tmp/
target/
.mvn/
.gradle/
bin/

### Node ###
node_modules/
logs/
*.log
coverage/
.next/

### IDEs ###
.idea/
.vscode/
*.iml
*.swp

### macOS ###
.DS_Store
.AppleDouble
.Trashes

The .gitignore ensured that only relevant files were tracked by Git, improving repository cleanliness and maintainability.

### Committing the Initial Version
Once the repository was cloned, it was necessary to commit and push the changes to ensure tracking from the beginning.

Steps:
```sh
   git add .
   git commit -m "Created CA1 and part1 folder and added tutorial React.js + spring data rest code in CA1/par1 and checked if this run and open in localhost"
   git push origin main
```

### Versioning with Git Tags
To properly manage versions, a tagging system was implemented to mark different phases of development. The initial version was tagged as `v1.1.0`.

Steps:
```sh
   git tag v1.1.0
```

## Part 2: Adding a New Feature
### Implementing `jobYears` Field
A new feature was introduced to track the number of years an employee has worked in the company (`jobYears`). This was implemented by:

1. Adding a **Integer** type attribute `jobYears`.
2. Including it in the constructor.
3. Implementing necessary validations.
4. Updating the **JavaScript frontend** to display the new field.

Example code:
```java
@Entity 
public class Employee {

    private @Id
    @GeneratedValue Long id; // <2>
    private String firstName;
    private String lastName;
    private String description;
    private Integer jobYears;

    public  Employee() {
    }

    public Employee(String firstName, String lastName, String description, Integer jobYears) {
        if(!isParametersInvalid(firstName)){
            this.firstName = firstName;}
        if(!isParametersInvalid(lastName)){
            this.lastName = lastName;}
        if(!isParametersInvalid(description)){
            this.description = description; }
        if(!isjobYearsInvalid(jobYears)){
            this.jobYears = jobYears; }

    }
}
```

### Unit Testing
Unit tests were written to ensure the correctness of the new feature.

- Ensured `jobYears` cannot be null or empty.
- Verified that only integer values are allowed.
- Ran tests using Maven:

```sh
   ./mvnw test
```

Example test:
```java
 @Test
public void testSetJobYears_ValidValue() {
    Employee emp = new Employee("John", "Doe", "Developer", 5);
    emp.setJobYears(10);
    assertEquals(10, emp.getJobYears()); 
}
```

### Debugging
Debugging was performed in two phases:
1. **Server-side debugging**: Used breakpoints in IntelliJ to analyze the request processing in Spring Boot.
2. **Client-side debugging**: Used **React Developer Tools** to inspect UI changes and validate the integration of `jobYears` in the frontend.

## Commit History
The development process was carefully tracked through commits, ensuring step-by-step documentation of changes. Some key commits include:

- **Initial Setup**: Created CA1 and part1 folder, added tutorial React.js + Spring Data REST code, and verified it ran on localhost.
- **Git Management**: Added and updated `.gitignore` multiple times to ensure proper tracking of necessary files.
- **Feature Implementation**: Updated `Employee` class with the `jobYears` attribute, new methods, and corresponding tests.
- **Frontend Integration**: Modified the JavaScript files to display `jobYears` for the client.
- **Validation and Debugging**: Implemented additional validation methods for `jobYears` and debugged both backend and frontend using appropriate tools.

## Screenshots and Debugging Results
To provide a better understanding of the debugging and testing process, the following images illustrate key steps:

### Maven Build and Test Execution
![Maven SpringBoot Run](https://imgur.com/a/2xnhN2G)
![Maven Test Results](https://imgur.com/a/55zZGMc)

### Client-Side Debugging with React Developer Tools
![React Developer Tools](https://imgur.com/a/bBWQnIn)

### Committing and Tagging the Feature
Once the feature was successfully implemented and tested, it was committed and tagged as version `v1.2.0`.

Steps:
```sh
   git add .
   git commit -m "Added ReadMe file and finish the first week"
   git push origin main
   git tag v1.2.0
   git push origin v1.2.0
```

## Part 3: Finalizing the Assignment
### Repository Marking
To complete the first week assignment, the repository was marked with the required tag.

Steps:
```sh
   git tag ca1-part1.1
   git push origin ca1-part1.1
```


## Conclusion
This assignment successfully demonstrated core DevOps practices, including:
- **Version control with Git**, ensuring traceability of changes.
- **Automated testing with Maven**, verifying application correctness.
- **Application debugging**, identifying and resolving issues at both server and client levels.
- **Frontend integration**, ensuring `jobYears` was displayed correctly on the UI.

By following structured DevOps principles, the project maintains high reproducibility, efficiency, and maintainability.

