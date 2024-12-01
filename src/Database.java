import java.sql.*;
import java.util.Scanner;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/practice";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Samsung e7 aman";

    // RETRIEVAL
    public static void retrieveTable(Connection connection) {
        String showTableQuery = "SELECT * FROM students";
        try (PreparedStatement statement = connection.prepareStatement(showTableQuery);
             ResultSet result = statement.executeQuery()) {
            System.out.println("Retrieving data from the table:");
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                double marks = result.getDouble("marks");
                System.out.println("ID: " + id + ", NAME: " + name + ", MARKS: " + marks);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving data:");
            e.printStackTrace();
        }
    }

    // INSERTION
    public static int insertData(Connection connection, String name, double marks) {
        int rowsAffected = 0;
        String insertQuery = "INSERT INTO students (name, marks) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setString(1, name);
            statement.setDouble(2, marks);
            rowsAffected = statement.executeUpdate();
            System.out.println("Inserted successfully, " + rowsAffected + " rows affected.");
        } catch (SQLException e) {
            System.err.println("Error inserting data:");
            e.printStackTrace();
        }
        return rowsAffected;
    }

    // UPDATION
    public static void updateData(Connection connection, int id, String newName, double newMarks) {
        String updateQuery = "UPDATE students SET name = ?, marks = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setString(1, newName);
            statement.setDouble(2, newMarks);
            statement.setInt(3, id);
            int rowsAffected = statement.executeUpdate();
            System.out.println("Updated successfully, " + rowsAffected + " rows affected.");
        } catch (SQLException e) {
            System.err.println("Error updating data:");
            e.printStackTrace();
        }
    }

    // DELETION
    public static void deleteData(Connection connection, int id) {
        String deleteQuery = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            System.out.println("Deleted successfully, " + rowsAffected + " rows affected.");
        } catch (SQLException e) {
            System.err.println("Error deleting data:");
            e.printStackTrace();
        }
    }

    // Main method
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL Driver not found!");
            e.printStackTrace();
            return;
        }

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Scanner scanner = new Scanner(System.in)) {
            System.out.println("Connected to the database!");

            while (true) {
                System.out.println("\nChoose an operation:");
                System.out.println("1. Retrieve Data");
                System.out.println("2. Insert Data");
                System.out.println("3. Delete Data");
                System.out.println("4. Update Data");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        retrieveTable(connection);
                        break;
                    case 2:
                        System.out.print("Enter name: ");
                        scanner.nextLine(); // Consume newline
                        String name = scanner.nextLine();
                        System.out.print("Enter marks: ");
                        double marks = scanner.nextDouble();
                        insertData(connection, name, marks);
                        break;
                    case 3:
                        System.out.print("Enter ID to delete: ");
                        int deleteId = scanner.nextInt();
                        deleteData(connection, deleteId);
                        break;
                    case 4:
                        System.out.print("Enter ID to update: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter new name: ");
                        String newName = scanner.nextLine();
                        System.out.print("Enter new marks: ");
                        double newMarks = scanner.nextDouble();
                        updateData(connection, updateId, newName, newMarks);
                        break;
                    case 5:
                        System.out.println("Exiting the program. Bye!");
                        return;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Database connection failed!");
            e.printStackTrace();
        }
    }
}