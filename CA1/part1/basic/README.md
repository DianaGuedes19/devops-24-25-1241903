# CA1 Part1 - Technical Report

**Author:** Diana Guedes

**Date:** 24/02/2025

**Discipline:** DevOps

**Program:** SWitCH DEV

**Institution:** Instituto Superior de Engenharia/ Instituto Politécnico do Porto

## Introduction
This document addresses the Version Control with Git assignment for the DevOps discipline. The work is organized into two main parts: first, a straightforward use of version control without branching, and second, an extension that introduces new features and fixes bugs through branching. In the Final Results section, you’ll find a visual representation of how the application has evolved after merging all enhancements and corrections. Additionally, an Alternative Solution is explored—Subversion (SVN)—to discuss its characteristics and evaluate its suitability for the objectives of this assignment.

## Part 1: Initial Setup

**Objectives and Requirements**
The first segment of this assignment concentrates on grasping essential version control processes without the use of branches. You’ll begin by preparing the project environment, making modifications directly on the master branch, and committing those updates. A primary requirement is to implement a new feature (for instance, a jobYears property in an Employee object) and then correctly handle version tags—starting with an initial version and updating it once the feature is added. The focus here is on practicing commits, understanding the commit history, and applying tags for effective versioning.

**Creating My Repository:** I created a new folder on my local machine for the DevOps class assignments and initialized it as a Git repository. This was the first step in establishing my workspace for the project.
```sh
mkdir ~/devops-24-25-1241903
cd ~/devops-24-25-1241903
mkdir CA1
cd CA1
mkdir part1
cd part1
git init
```

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
In this initial phase, the main objective involved introducing a new feature by adding a jobYears field to track how long an employee has worked at the company. Alongside this, I implemented unit tests to confirm that Employees are correctly created and their attributes are properly validated. Special attention was given to ensuring that jobYears only accepts integer values, and that String fields cannot be null or empty.

**Files Updated:**
   - Employee.java: This class, which represents the employee model, was modified to include a new integer field called jobYears. Getter and setter methods were introduced for this field, and parameter validation was integrated to maintain data integrity. Below is an outline of the key updates made to support the new functionality and enforce thorough data checks.

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
**EmployeeTest.java:** To confirm that the newly introduced jobYears field works correctly, this class incorporates a set of unit tests covering multiple scenarios:

- Validation Tests: Verified that both the constructor and the setters reject invalid inputs (e.g., null or empty strings, negative jobYears), ensuring objects are never created with faulty data.
- Positive Scenarios: Confirmed that valid inputs result in successful object creation without exceptions, demonstrating that the Employee class behaves correctly under proper usage.
- Equality and Hashing: Tested the equals and hashCode implementations to guarantee consistent comparisons between Employee objects.
- String Representation: Checked the toString method to make sure it accurately reflects the state of the Employee object, aiding in debugging and logging.
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

**app.js:**
To accommodate the newly added jobYears attribute, the React components in app.js were revised to show the field within the employee list. Both EmployeeList and Employee now include a “Job Years” column in the displayed table, enabling users to easily see how long each employee has worked for the company alongside their other information. Below is a code sample that demonstrates how jobYears was incorporated into the application’s frontend.
```java
// tag::employee-list[]
class EmployeeList extends React.Component{
   render() {
		const employees = this.props.employees.map(employee =>
			<Employee key={employee._links.self.href} employee={employee}/>
		);
      return (
              <table>
				<tbody>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Description</th>
					<th>Job Years</th>
				</tr>
              {employees}
              </tbody>
			</table>
		)
   }
}
```
```java
class Employee extends React.Component{
   render() {
      return (
              <tr>
				<td>{this.props.employee.firstName}</td>
				<td>{this.props.employee.lastName}</td>
				<td>{this.props.employee.description}</td>
				<td>{this.props.employee.jobYears}</td>
			</tr>
		)
   }
}
```


### Debugging
Once the integration of the jobYears field was confirmed, I launched the application with ./mvnw spring-boot:run and tested its live functionality at http://localhost:8080/. This hands-on review was vital to ensure seamless performance alongside existing features. In parallel, I performed a detailed code review to verify data handling on the server side and the accurate display of jobYears on the client side, maintaining both feature correctness and high code quality.
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


### Part 2: Branch-Based Development

### Objectives and Requirements

This section focuses on utilizing branches for feature development and bug fixes, highlighting the importance of isolated workspaces and efficient merging techniques.

Key requirements include creating dedicated branches for new functionalities or corrections, ensuring that modifications remain separate from the main codebase until they are ready for integration.

The section concludes with tagging the main branch following successful merges to indicate new application versions, demonstrating effective branch organization and version control practices.

### Key Developments

In this phase, development was structured around branching strategies to introduce new features and address existing issues while maintaining the stability of the main branch. This ensured that stable releases of the **Tutorial React.js and Spring Data REST Application** remained unaffected by ongoing changes.

Since the approach for implementing new features and resolving bugs aligns with the methodology used in Part 1, repetitive code demonstrations have been omitted. The primary distinction in this section is the incorporation of branching. Below are the core steps:

#### Working with the Main Branch

To confirm the correct working branch, particularly when preparing stable releases, I used the `git branch` command. This was a critical step in validating the current branch, which is marked with an asterisk (*) in the command output.

#### Developing Features in Separate Branches

During the implementation of an **email field**, effective branch management played a crucial role. The development began by creating a dedicated feature branch and switching to it for all related updates. The process followed these steps:

1. A new branch named `email-field` was created to house all developments concerning the email feature.
2. The active workspace was switched to this branch to commence development.
3. The `git branch` command was executed again to confirm the switch was successful.

Commands used:
```sh
# Create and switch to the feature branch
git branch email-field
git checkout email-field
git branch
```

#### Integrating and Testing the Email Field

The process of adding and validating the email field mirrored the approach used in **Part 1** for the `jobYears` field. The key steps included:

- **Code Implementation:** Expanding the `Employee` class to include the new `email` field along with appropriate getter and setter methods. The update extended to models, forms, and views, ensuring full integration across both frontend and backend.
- **Unit Testing:** Writing comprehensive test cases to confirm correct creation of `Employee` instances with the email field, enforcing validation rules such as non-null and properly formatted values.
- **Debugging:** Conducting thorough debugging on both the server and client sides to identify and fix any issues introduced by the new feature.

#### Merging the Feature into Main

Once development of the email field was complete, a structured process was followed to merge the changes into the main branch and update the application's version:

1. Committing finalized modifications.
2. Pushing the feature branch to the remote repository.
3. Switching to the main branch and performing a **no-fast-forward** merge.
4. Updating the remote main branch.
5. Tagging the new version to mark this release.

Commands executed:
```sh
# Commit the new feature
git add .
git commit -m "Added new field called email in Employee + tests in EmployeeTest and DatabaseLoader.java"
git push -u origin email-field

# Switch to main branch and merge
git checkout main
git merge --no-ff email-field

# Push the merged changes to update the main branch
git push

# Tag the new version and push it
git tag -a v1.3.0 
git push origin v1.3.0
```

#### Creating a Bug Fix Branch

To resolve an issue with email validation in the `Employee` class, a separate branch named `fix-invalid-email` was created following the standard workflow. The process adhered to the structured development, testing, and merging practices used previously, ensuring stability and code integrity.

The primary fix involved enhancing the `Employee` class with validation logic to enforce the presence of an `@` symbol in email addresses:
```java
public boolean isEmailInvalid(String email){
   return  email == null || email.isBlank() || !email.contains("@") ;
}
```

### Assignment Completion

After implementing and validating the fix, the changes were merged into the main branch. The application version was then updated to `v1.3.1` to reflect this minor improvement. To mark the end of the assignment, the repository was tagged as `ca1-part2` to indicate the completion of this phase.


