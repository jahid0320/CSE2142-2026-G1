
import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] commandLineArguments) {

        if (commandLineArguments.length != 1) {
            System.out.println("Error: Exactly one argument is required.");
            System.out.println("Usage: java EmployeeManager <l|s|+Name|?Name|c|uName>");
            return;
        }

        String command = commandLineArguments[0];

        if (command.equals("l")) {
            System.out.println("Loading data ...");

            String[] employeeNames = readEmployees();

            if (employeeNames != null) {
                for (String employeeName : employeeNames) {
                    System.out.println(employeeName);
                }
            }

            System.out.println("Data Loaded.");

        } else if (command.equals("s")) {
            System.out.println("Loading data ...");

            String[] employeeNames = readEmployees();

            if (employeeNames != null && employeeNames.length > 0) {
                System.out.println(String.join(",", employeeNames));

                Random randomGenerator = new Random();
                int randomIndex = randomGenerator.nextInt(employeeNames.length);

                System.out.println(employeeNames[randomIndex]);
            }

            System.out.println("Data Loaded.");

        } else if (command.contains("+")) {
            System.out.println("Loading data ...");

            String newEmployeeName = command.substring(1);

            if (appendEmployee(newEmployeeName)) {
                System.out.println("Employee Added.");
            }

            System.out.println("Data Loaded.");

        } else if (command.contains("?")) {
            System.out.println("Loading data ...");

            String[] employeeNames = readEmployees();

            if (employeeNames != null) {
                String searchName = command.substring(1);
                boolean found = false;

                for (String employeeName : employeeNames) {
                    if (employeeName.equals(searchName)) {
                        System.out.println("Employee found!");
                        found = true;
                        break;
                    }
                }
            }

            System.out.println("Data Loaded.");

        } else if (command.contains("c")) {
            System.out.println("Loading data ...");

            String[] employeeNames = readEmployees();

            if (employeeNames != null) {
                String employeeLine = String.join(",", employeeNames);
                char[] characters = employeeLine.toCharArray();

                boolean inWord = false;
                int count = 0;

                for (char character : characters) {
                    if (character == ' ') {
                        if (!inWord) {
                            count++;
                            inWord = true;
                        } else {
                            inWord = false;
                        }
                    }
                }

                System.out.println(
                    count + " word(s) found " + characters.length
                );
            }

            System.out.println("Data Loaded.");

        } else if (command.contains("u")) {
            System.out.println("Loading data ...");

            String[] employeeNames = readEmployees();

            if (employeeNames != null) {
                String targetEmployeeName = command.substring(1);

                for (int employeeIndex = 0;
                     employeeIndex < employeeNames.length;
                     employeeIndex++) {

                    if (employeeNames[employeeIndex].equals(targetEmployeeName)) {
                        employeeNames[employeeIndex] = "Updated";
                    }
                }

                writeEmployees(employeeNames);
            }

            System.out.println("Data Updated.");

        } else if (command.contains("d")) {
            System.out.println("Loading data ...");

            String[] employeeNames = readEmployees();

            if (employeeNames != null) {
                String targetEmployeeName = command.substring(1);

                List<String> employeeList =
                    new ArrayList<>(Arrays.asList(employeeNames));

                employeeList.remove(targetEmployeeName);

                writeEmployees(employeeList.toArray(new String[0]));
            }

            System.out.println("Data Deleted.");
        }
    }

    // Reads employee names from the file.
    private static String[] readEmployees() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream("employees.txt")
                ))) {

            String employeeLine = reader.readLine();

            if (employeeLine == null || employeeLine.isEmpty()) {
                return new String[0];
            }

            return employeeLine.split(",");

        } catch (IOException exception) {
            System.out.println("Error reading employee file.");
            return null;
        }
    }

    // Writes employee names to the file.
    private static boolean writeEmployees(String[] employeeNames) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("employees.txt"))) {

            writer.write(String.join(",", employeeNames));
            return true;

        } catch (IOException exception) {
            System.out.println("Error writing employee file.");
            return false;
        }
    }

    // Adds a new employee name at the end of the file.
    private static boolean appendEmployee(String employeeName) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("employees.txt", true))) {

            writer.write(", " + employeeName);
            return true;

        } catch (IOException exception) {
            System.out.println("Error adding employee.");
            return false;
        }
    }
}
