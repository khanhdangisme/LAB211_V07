/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ce180954_v07;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * V07 - File Handling
 *
 * @author Le Khanh Dang - CE180954 16/02/2025
 */
public class MyProgram {

    private Scanner sc = new Scanner(System.in);  // Create a scanner object to read user input
    IO io = new IO();  // Create an IO object to interact with the user
    private File folder = new File("D:\\CodeJava\\JavaForNeatBeans\\LAB211\\Code\\CE180954_V07\\srcFile");  // Path to the folder containing files

    /**
     * This method displays a menu and handles user input for various file
     * operations. The program continues to run until the user chooses to exit.
     */
    public void manageFile() {
        // Display menu options for the user to select
        System.out.println("1) Read file");
        System.out.println("2) Copy files");
        System.out.println("3) Merge two files");
        System.out.println("4) List files in a directory");
        System.out.println("5) Delete file");
        System.out.println("6) Create file");
        System.out.println("7) Exits");

        while (true) {  // Infinite loop to keep the program running until user chooses to exit
            System.out.println(" ");
            int choice = io.checkChoice();  // Get the user's menu choice

            switch (choice) {  // Handle the user's choice
                case 1:
                    if (!checkFolderEmpty()) {  // Check if folder is not empty
                        readFile();  // Read a file
                    } else {
                        System.out.println("Folder is empty"); //Print message
                    }
                    break;
                case 2:
                    if (!checkFolderEmpty()) {  // Check if folder is not empty
                        copyFile();  // Copy a file
                    } else {
                        System.out.println("Folder is empty"); //Print message
                    }
                    break;
                case 3:
                    if (!checkFolderEmpty()) {  // Check if folder is not empty
                        mergeFile();  // Merge two files
                    } else {
                        System.out.println("Folder is empty"); //Print message
                    }
                    break;
                case 4:
                    if (!checkFolderEmpty()) {  // Check if folder is not empty
                        displayListFile();  // Display the list of files
                    } else {
                        System.out.println("Folder is empty"); //Print message
                    }
                    break;
                case 5:
                    if (!checkFolderEmpty()) {  // Check if folder is not empty
                        deleteFile();  // Delete a file
                    } else {
                        System.out.println("Folder is empty"); //Print message
                    }
                    break;
                case 6:
                    createFile();  // Create a new file
                    break;
                case 7:
                    System.out.println("Exits");  // Exit the program
                    return;
            }
        }
    }

    /**
     * Checks if the specified file exists in the folder.
     *
     * @param fileName The name of the file to check.
     * @return true if the file exists, false otherwise.
     */
    public boolean checkExist(String fileName) {
        File file = new File(folder, fileName);  // Create a File object for the specified file
        return file.exists();  // Return true if the file exists, false otherwise
    }

    /**
     * Checks if the folder is empty.
     *
     * @return true if the folder is empty, false otherwise.
     */
    public boolean checkFolderEmpty() {
        File[] files = folder.listFiles();  // List all files in the folder
        return files == null || files.length == 0;  // Return true if the folder is empty
    }

    /**
     * Prompts the user with a message and returns a boolean based on their
     * response.
     *
     * @param msg The message to display to the user.
     * @return True if the user responds 'yes', false if 'no'.
     */
    public boolean checkYesNo(String msg) {
        return io.checkYN(msg); // Get a 'yes' or 'no' response from the user
    }

    /**
     * Reads and displays the content of a file.
     */
    public void readFile() {
        String fileName = io.checkNameFile("Enter the name of file you wish to see: ");  // Get the file name from the user
        File file = new File(folder, fileName);  // Create a File object for the specified file

        if (checkExist(fileName)) {  // Check if the file exists
            try {
                if (file.length() == 0) {  // Check if the file is empty
                    System.out.println("File is empty"); //Print message
                    return;
                }
                FileReader reader = new FileReader(file);  // Create a FileReader to read the file
                int character;
                while ((character = reader.read()) != -1) {  // Read each character from the file
                    System.out.print((char) character);  // Print the character to the console
                }
                System.out.println(" ");
                reader.close();  // Close the reader after reading the file
            } catch (IOException e) {
                System.out.println("File error");  // Print error message if file reading fails
            }
        } else {
            System.out.println("File doesn't exist");  // Print message if file does not exist
        }
    }

    /**
     * Copies the content of one file to another.
     */
    public void copyFile() {
        String fileNameCopy = io.checkNameFile("Enter name of file to copy: ");  // Get the file name to copy
        File fileCopy = new File(folder, fileNameCopy);  // Create a File object for the source file

        String fileNameTarget = io.checkNameFile("Enter name of target file: ");  // Get the target file name
        File fileTarget = new File(folder, fileNameTarget);  // Create a File object for the target file

        if (checkExist(fileNameCopy) && checkExist(fileNameTarget)) {  // Check if both files exist
            try {
                FileReader reader = new FileReader(fileCopy);  // Create a FileReader to read the source file
                FileWriter writer = new FileWriter(fileTarget, true);  // Create a FileWriter to write to the target file in append mode

                int character;
                while ((character = reader.read()) != -1) {  // Read each character from the source file
                    writer.write(character);  // Write the character to the target file
                }
                reader.close();  // Close the reader
                writer.close();  // Close the writer
                System.out.println("File copied successfully!!"); //Print message
            } catch (IOException e) {
                System.out.println("File error");  // Print error message if file copying fails
            }
        } else {
            System.out.println("One of the two files does not exist");  // Print message if any of the files does not exist
        }
    }

    /**
     * Merges the contents of two files into a third file.
     */
    public void mergeFile() {
        String fileFirst = io.checkNameFile("Enter name of first file: ");  // Get the first file name
        File fileF = new File(folder, fileFirst);  // Create a File object for the first file

        String fileSecond = io.checkNameFile("Enter name of second file: ");  // Get the second file name
        File fileS = new File(folder, fileSecond);  // Create a File object for the second file

        String fileResult = io.checkNameFile("Enter name of file which will store contents of two files: ");  // Get the result file name
        File fileR = new File(folder, fileResult);  // Create a File object for the result file

        if (checkExist(fileFirst) && checkExist(fileSecond)) {  // Check if both source files exist
            try {
                FileReader readerF = new FileReader(fileF);  // Create a FileReader for the first file
                FileReader readerS = new FileReader(fileS);  // Create a FileReader for the second file
                if (checkExist(fileResult)) {  // Check if the result file exists
                    if (checkYesNo("The file already exists. Do you want to overwrite it? (Y/N): ")) {  // Ask user if they want to overwrite
                        FileWriter writerR = new FileWriter(fileR);  // Create a FileWriter for the result file
                        int character;
                        while ((character = readerF.read()) != -1) {  // Read from the first file
                            writerR.write(character);  // Write to the result file
                        }
                        while ((character = readerS.read()) != -1) {  // Read from the second file
                            writerR.write(character);  // Write to the result file
                        }
                        readerF.close();  // Close the reader for the first file
                        readerS.close();  // Close the reader for the second file
                        writerR.close();  // Close the writer for the result file
                        System.out.println("Two files were merged into " + fileResult + " file successfully!!"); //Print message
                    } else {
                        System.out.println("File merge failed");  // Print message if user does not want to overwrite
                        return;
                    }
                } else {
                    fileR.createNewFile();  // Create the result file if it does not exist
                    FileWriter writerR = new FileWriter(fileR);  // Create a FileWriter for the result file
                    int character;
                    while ((character = readerF.read()) != -1) {  // Read from the first file
                        writerR.write(character);  // Write to the result file
                    }
                    while ((character = readerS.read()) != -1) {  // Read from the second file
                        writerR.write(character);  // Write to the result file
                    }
                    readerF.close();  // Close the reader for the first file
                    readerS.close();  // Close the reader for the second file
                    writerR.close();  // Close the writer for the result file
                    System.out.println("Two files were merged into " + fileResult + " file successfully!!"); //Print message
                }
            } catch (IOException e) {
                System.out.println("File error");  // Print error message if file merging fails
            }
        }
    }

    /**
     * Displays a list of all files in the folder.
     */
    public void displayListFile() {
        File[] files = folder.listFiles();  // Get the list of files in the folder

        // Loop through the files array and print each file name
        for (File f : files) {
            System.out.println(f.getName());  // Print the name of the file
        }
    }

    /**
     * Deletes a file from the folder.
     */
    public void deleteFile() {
        String fileDelete = io.checkNameFile("Enter the name of file you wish to delete: ");  // Get the file name to delete
        if (checkExist(fileDelete)) {  // Check if the file exists
            File deleteF = new File(folder, fileDelete);  // Create a File object for the file to delete
            deleteF.delete();  // Delete the file
            System.out.println(fileDelete + " file deleted successfully!!!"); //Print message
        } else {
            System.out.println("File doesn't exist");  // Print message if the file does not exist
        }
    }

    /**
     * Creates a new file in the folder.
     */
    public void createFile() {
        String fileName = io.checkNameFile("Enter name: "); // Get the name of the new file
        if (checkExist(fileName)) { // Check if the file already exists
            System.out.println("File name already exists"); // Print message if the file exists
            return;
        } else {
            File fileN = new File(folder, fileName); // Create a File object for the new file
            try {
                fileN.createNewFile(); // Create the new file
                if (checkYesNo("Do you want to write to this file? (Y/N): ")) { // Ask user if they want to write content
                    writeContentToFile(fileN); // Write content to the file if user confirms
                }
                System.out.println("File created successfully"); // Print success message
            } catch (IOException e) { // Catch any IOException
                System.out.println("File error"); // Print error message if file creation fails
            }
        }
    }

    /**
     * Writes user-inputted content to the specified file.
     *
     * @param file The file to write content to.
     */
    public void writeContentToFile(File file) {
        String content = io.writeContent(); // Get content from user input
        try (FileWriter writer = new FileWriter(file)) { // Create FileWriter for the file
            writer.write(content); // Write content to the file
        } catch (IOException e) { // Catch any IOException
            System.out.println("Error writing to file."); // Print error message
        }
    }
}
