# Task Manager CLI

A simple command-line task manager application built in Java with MySQL integration.  
Manage your tasks with options to add, list, update, delete, and mark tasks as completed.

## Features

- Add new tasks  
- List all tasks  
- Update existing tasks  
- Delete tasks  
- Mark tasks as done  
- List incomplete tasks  

## Technologies Used

- Java  
- JDBC  
- MySQL  
- Command-line Interface  

## Setup Instructions

1. **Clone the repository**

```bash
git clone https://github.com/YOUR_GITHUB_USERNAME/task-manager-cli.git
cd task-manager-cli
```

2. **Set up MySQL database**

Create a database and table with the following structure:

```sql
CREATE DATABASE task_manager;

USE task_manager;

CREATE TABLE tasks (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(50) NOT NULL,
  is_completed BOOLEAN DEFAULT FALSE
);
```

3. **Configure database credentials**

Update the database URL, username, and password in `SqlConnect.java` to match your MySQL setup.

4. **Add MySQL JDBC Driver**

Make sure the MySQL Connector/J driver is added to your project’s classpath.

5. **Run the program**

Run the `TaskManager` main class from your IDE or command line.

## Usage

Follow the on-screen menu to manage your tasks.

## License

This project is licensed under the MIT License.
