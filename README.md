# Library Management System

A lightweight, high-performance web enterprise application designed to automate day-to-day library workflows such as book archiving, indexing, and real-time inventory tracking. This project is built entirely on a decoupled Model-View-Controller (MVC) architecture using the modern Jakarta EE 11 specification.

## 🚀 Technology Stack

- **Backend Runtime:** Oracle OpenJDK 25 (LTS)
- **Enterprise Specification:** Jakarta EE 11 (`jakarta.servlet` namespace)
- **Web Application Container:** Apache Tomcat 11.0.22
- **Database Engine:** MySQL Community Server 8.0 / 8.4
- **Database Driver:** MySQL Connector/J 8.4.0 LTS
- **IDE:** Visual Studio Code (VS Code)
- **Build Methodology:** Pure manual terminal compilation (Zero-dependency build pipeline via raw `javac` and `jar` utilities).

---

## 📁 Project Structure

```text
LibraryManagement/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── library/
│                   ├── model/
│                   │   └── Book.java
│                   ├── dao/
│                   │   └── BookDAO.java
│                   └── servlet/
│                       ├── AddBookServlet.java
│                       └── IssueBookServlet.java
└── webapp/
    ├── WEB-INF/
    │   ├── web.xml
    │   └── classes/
    ├── index.jsp
    ├── addBook.jsp
    └── issueBook.jsp

🛠️ Installation & Local Setup
1. Prerequisites

Ensure that you have installed and configured the following environments locally:

    Java 25 configured in your environment variables (JAVA_HOME).

    MySQL Server installed at C:\mysql (or your preferred directory).

    Apache Tomcat 11 installed at C:\tomcat\apache-tomcat-11.0.22.

2. Database Initialization

    Log into your MySQL instance via the command line or a workbench utility.

    Execute the database initialization script to provision the schema and tracking tables:

SQL

CREATE DATABASE IF NOT EXISTS library_db;
USE library_db;

CREATE TABLE IF NOT EXISTS books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    genre VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    status VARCHAR(50) DEFAULT 'AVAILABLE',
    issued_to VARCHAR(50) DEFAULT NULL
);

    Update your database configuration (username and password credentials) directly inside src/main/java/com/library/dao/BookDAO.java.

3. Adding Driver Libraries

Copy your mysql-connector-j-8.x.x.jar file and paste it directly into Tomcat's global library folder:
C:\tomcat\apache-tomcat-11.0.22\lib\
⚙️ Compilation & Deployment Workflow

Execute all build operations from the integrated VS Code terminal within your project root folder:
Step 1: Initialize Bytecode Destination

Create the required directory tree inside your web application container to hold the compiled .class bytecode files:
Bash

mkdir webapp\WEB-INF\classes

Step 2: Compile Java Source Code

Invoke the Java standard compiler, referencing Tomcat's structural libraries to resolve the modern jakarta.* dependencies:
Bash

javac -d webapp/WEB-INF/classes -cp "C:\tomcat\apache-tomcat-11.0.22\lib\*" src/main/java/com/library/model/Book.java src/main/java/com/library/dao/BookDAO.java src/main/java/com/library/servlet/*.java

Step 3: Package into a Web Application Archive (WAR)

Navigate into your web resource folder and bundle the web application using Java's archival tool:
Bash

cd webapp
jar -cvf ..\LibraryManagement.war *
cd ..

Step 4: Deploy onto Apache Tomcat

Copy the generated deployment artifact straight into Tomcat's deployment folder:
Bash

copy LibraryManagement.war C:\tomcat\apache-tomcat-11.0.22\webapps\

🚦 Running the Application

    Open a terminal and fire up your Apache Tomcat server instance:
    Bash

    C:\tomcat\apache-tomcat-11.0.22\bin\startup.bat

    Open your preferred web browser and navigate to the application context path (note that URLs are case-sensitive):
    👉 http://localhost:8080/LibraryManagement/

(To stop the server later, execute C:\tomcat\apache-tomcat-11.0.22\bin\shutdown.bat)
👥 Team Contribution Roles

This project was built as a collaborative effort splitting ownership across the MVC architecture layers:

    Database Infrastructure Engineer: Handled relational data schema optimizations, data-access connectivity configurations (BookDAO.java), and secure connection parameters.

    Backend Core Developer: Engineered transaction routing structures, input parameter evaluations, and migrated system classes natively to the modern Jakarta EE 11 specification.

    Frontend UI Engineer: Formulated the dynamic frontend environment using HTML5 forms and dynamic loop scriptlets inside JavaServer Pages (JSP).

    DevOps Deployment & QA Specialist: Supervised final environment configurations, web.xml structure maps, VS Code terminal compilation procedures, WAR packaging, and quality testing.