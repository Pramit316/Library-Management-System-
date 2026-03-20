<div align="center">

# Library Management System

A console-based **Library Management System** built with **Java**, **JDBC**, and **PostgreSQL**.  
This project is designed to manage users, books, memberships, and borrowing operations in a simple, structured, and database-driven way.

<br>

![Java](https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=openjdk)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue?style=for-the-badge&logo=postgresql)
![JDBC](https://img.shields.io/badge/JDBC-Connectivity-green?style=for-the-badge)
![Status](https://img.shields.io/badge/Project-Active-success?style=for-the-badge)

</div>

---

## Overview

This project is a **menu-driven library management application** developed in Java. It uses **JDBC** to connect to a **PostgreSQL** database and supports core library operations such as:

- managing users and members
- storing and viewing books
- borrowing books
- tracking active borrowed books
- handling membership approval logic

The application demonstrates practical concepts including:

- object-oriented programming
- layered architecture
- database connectivity with JDBC
- CRUD operations
- relational database design
- user-specific data handling

---

## Features

### User Management
- Add new users or members
- View all members
- Process membership requests
- Delete members
- Login using username

### Book Management
- Add new books
- View all books
- Search books by ID or title
- Maintain available book quantity

### Borrowing System
- Borrow books by **Book ID**
- Borrow books by **Book Title**
- View books currently borrowed by a specific user
- Automatically reduce book quantity when borrowed
- Store borrowing records separately for proper tracking

### Validation and Rules
- Only approved members can borrow books
- Users with pending membership cannot access borrowing features
- Books cannot be borrowed when stock is unavailable
- Borrowing records are linked to individual users

---

## Tech Stack

- **Language:** Java
- **Database:** PostgreSQL
- **Database Connectivity:** JDBC
- **Architecture Style:** Layered Architecture
  - Controller Layer
  - Service Layer
  - Repository/DAO Layer
  - Model Layer

---

## Database Design

The project uses relational tables to keep the system structured and consistent.

### `users` table

Stores information about system users and members.

**Fields**
- `id`
- `name`
- `role`
- `age`
- `ismember`
- `phone_number`
- `address`

### `book` table

Stores book details available in the library.

**Fields**
- `id`
- `title`
- `author`
- `quantity`

### `borrowing` table

Stores borrowing records separately from books and users.

**Fields**
- `id`
- `user_id`
- `book_id`
- `borrow_date`
- `due_date`
- `return_date`
- `status`

---

## Why the `borrowing` Table Matters

Instead of only reducing the number of books in stock, the project uses a separate `borrowing` table. This makes the system more realistic and allows it to:

- track which user borrowed a book
- record borrow and due dates
- display currently borrowed books
- maintain borrowing history
- separate active borrowings from returned records

This design reflects how a proper library system should be structured in a relational database.

---

## Core Functional Flow

### User Authentication

The system allows users to log in using their username. During login, the application checks whether the user exists in the database and whether their membership has been approved. Only approved members are allowed to access borrowing features.

### Book Borrowing

Once logged in, a user can borrow a book by selecting it through its ID or title. The system verifies that the selected book exists and that there is sufficient quantity available. If valid, a borrowing record is inserted into the `borrowing` table and the book quantity is reduced accordingly.

### User-Specific Borrowed Books

The system supports user-specific borrowing records. Each logged-in user can view only the books they have personally borrowed. Borrowed book details are displayed by joining the `borrowing` and `book` tables and filtering the results by the current user's `user_id`.

### Multi-User Support

The application supports multiple users independently. Since each borrowing record is linked to a specific user, different users can log in separately, borrow different books, and view their own borrowed books without interfering with other users’ records.

---

## Sample SQL Query for Viewing Borrowed Books

```sql
SELECT br.id AS borrowing_id,
       b.id AS book_id,
       b.title,
       b.author,
       br.borrow_date,
       br.due_date,
       br.status
FROM borrowing br
JOIN book b ON br.book_id = b.id
WHERE br.user_id = ? AND br.status = 'BORROWED';
```


---

## Getting Started

### Prerequisites

Make sure you have the following installed:

- Java JDK 17+
- PostgreSQL
- An IDE such as IntelliJ IDEA, Eclipse, or VS Code
- PostgreSQL JDBC Driver

---

## Installation and Setup

### 1. Clone the repository

```bash
git clone https://github.com/Pramit316/Library-Management-System-.git 
cd Library-Management-System-
```

### 2. Create the PostgreSQL database

```bash
CREATE DATABASE library_management;
```

### 3. Create the required tables
You can create the tables manually or by using a schema.sql file.

```sql
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    role VARCHAR(50),
    age INT,
    ismember BOOLEAN DEFAULT FALSE,
    phone_number BIGINT,
    address VARCHAR(255)
);

CREATE TABLE book (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    quantity INT NOT NULL
);

CREATE TABLE borrowing (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    book_id INT NOT NULL,
    borrow_date DATE NOT NULL,
    due_date DATE NOT NULL,
    return_date DATE,
    status VARCHAR(20) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (book_id) REFERENCES book(id)
);
```

### 4. Configure database connection
Update your connection class with your PostgreSQL credentials.

```Java
String url = "jdbc:postgresql://localhost:5432/library_management";
String username = "your_postgres_username";
String password = "your_postgres_password";
```

### 5. Run the application
Run the Main.java file from your IDE.

---

## Author

**Pramit Bhattarai**  
Bachelor of Computer Engineering student passionate about software development, backend systems, and full-stack engineering. This project reflects practical learning in Java, JDBC, PostgreSQL, and database-driven application design.

