
// File Name: EmployeeManager.java

import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] commandLineArguments) {

        if (commandLineArguments.length != 1) {
            System.out.println("Error: Exactly one argument is required.");
            System.out.println("Usage: java EmployeeManager <l|s|+Name|?Name|c|uName>");
            return;
        }

        // Check arguments
        if (commandLineArguments[0].equals("l")) {
            System.out.println("Loading data ...");

            try {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );

                String employeeLine = reader.readLine();
                String employeeNames[] = employeeLine.split(",");

                for (String employeeName : employeeNames) {
                    System.out.println(employeeName);
                }

            } catch (Exception e) {
            }

            System.out.println("Data Loaded.");

        } else if (commandLineArguments[0].equals("s")) {
            System.out.println("Loading data ...");

            try {
                BufferedReader r = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );

                String l = r.readLine();
                System.out.println(l);

                String e[] = l.split(",");
                Random randomgenerator = new Random();
                int randomIndex = randomgenerator.nextInt(e.length);

                System.out.println(e[randomIndex]);

            } catch (Exception e) {
            }

            System.out.println("Data Loaded.");

        } else if (commandLineArguments[0].contains("+")) {
            System.out.println("Loading data ...");

            try {
                BufferedWriter writer = new BufferedWriter(
                    new FileWriter("employees.txt", true)
                );

                String n = commandLineArguments[0].substring(1);
                writer.write(", " + n);
                writer.close();

            } catch (Exception e) {
            }

            System.out.println("Data Loaded.");

        } else if (commandLineArguments[0].contains("?")) {
            System.out.println("Loading data ...");

            try {
                BufferedReader r = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );

                String l = r.readLine();
                String e[] = l.split(",");
                boolean found = false;
                String searchName = commandLineArguments[0].substring(1);

                for (int employeeIndex = 0; employeeIndex < e.length && !found; employeeIndex++) {
                    if (e[employeeIndex].equals(searchName)) {
                        System.out.println("Employee found!");
                        found = true;
                    }
                }

            } catch (Exception e) {
            }

            System.out.println("Data Loaded.");

        } else if (commandLineArguments[0].contains("c")) {
            System.out.println("Loading data ...");

            try {
                BufferedReader r = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );

                String l = r.readLine();
                char[] chars = l.toCharArray();
                boolean inWord = false;
                int count = 0;

                for (char character : chars) {
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
                    count + " word(s) found " + chars.length
                );

            } catch (Exception e) {
            }

            System.out.println("Data Loaded.");

        } else if (commandLineArguments[0].contains("u")) {
            System.out.println("Loading data ...");

            try {
                BufferedReader r = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );

                String l = r.readLine();
                String e[] = l.split(",");
                String employeeName = commandLineArguments[0].substring(1);

                for (int i = 0; i < e.length; i++) {
                    if (e[i].equals(employeeName)) {
                        e[i] = "Updated";
                    }
                }

                BufferedWriter w = new BufferedWriter(
                    new FileWriter("employees.txt")
                );

                w.write(String.join(",", e));
                w.close();

            } catch (Exception e) {
            }

            System.out.println("Data Updated.");

        } else if (commandLineArguments[0].contains("d")) {
            System.out.println("Loading data ...");

            try {
                BufferedReader r = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );

                String l = r.readLine();
                String e[] = l.split(",");
                String n = commandLineArguments[0].substring(1);

                List<String> list = new ArrayList<>(Arrays.asList(e));
                list.remove(n);

                BufferedWriter w = new BufferedWriter(
                    new FileWriter("employees.txt")
                );

                w.write(String.join(",", list));
                w.close();

            } catch (Exception e) {
            }

            System.out.println("Data Deleted.");
        }
    }
}
