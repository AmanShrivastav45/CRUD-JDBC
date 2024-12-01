# JDBC CRUD Operations with MySQL

This project demonstrates how to perform CRUD (Create, Read, Update, Delete) operations using Java with a MySQL database. The application allows users to interact with the `students` table in a MySQL database, enabling the retrieval, insertion, deletion, and updating of student data.

## Features

- **Retrieve Data**: View all student records in the `students` table.
- **Insert Data**: Add a new student with a name and marks to the database.
- **Update Data**: Modify an existing student's name and marks using their `ID`.
- **Delete Data**: Remove a student from the database based on their `ID`.

## Requirements

- Java 8 or higher
- MySQL Database
- JDBC Driver for MySQL (`com.mysql.cj.jdbc.Driver`)
- A MySQL database with a `students` table.

### MySQL Table Structure

```sql
CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    marks DOUBLE
);
```
## Sample output

```yaml
Connected to the database!

Choose an operation:
1. Retrieve Data
2. Insert Data
3. Delete Data
4. Update Data
5. Exit
Enter your choice: 1
Retrieving data from the table:
ID: 1, NAME: John Doe, MARKS: 85.5
ID: 2, NAME: Jane Doe, MARKS: 92.0
...

Choose an operation:
1. Retrieve Data
2. Insert Data
3. Delete Data
4. Update Data
5. Exit
Enter your choice: 2
Enter name: Alice
Enter marks: 89.5
Inserted successfully, 1 rows affected.
```
