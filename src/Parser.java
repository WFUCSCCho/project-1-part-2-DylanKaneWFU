/************************************************************************
 * @file Parser.java
 * @brief This program implements java to parse an input file
 * @author Dylan Kane
 * @date September 25, 2025
 *************************************************************************/

import java.io.*;
import java.util.Scanner;

public class Parser {

    private BST<DataObj> mybst = new BST<>();

    public Parser(String filename) throws IOException {
        process(new File(filename));
    }

    // Implement the process method
    // Remove redundant spaces for each input command
    public void process(File input) throws IOException {
        FileInputStream fileStream = new FileInputStream(input);
        Scanner fileScnr = new Scanner(fileStream);
        Scanner lineScnr;

        while (fileScnr.hasNextLine()) {
            String currLine = fileScnr.nextLine();
            lineScnr = new Scanner(currLine);
            String[] commandPieces = new String[5];
            String command;
            if (!currLine.isEmpty()) {
                command = lineScnr.next();
                commandPieces[0] = command;

                if (lineScnr.hasNext()) {
                    commandPieces[1] = lineScnr.next();
                }
            }

            if (commandPieces[0] != null) {
                operate_BST(commandPieces);
            }
        }

        fileStream.close();


    }

    // Implement the operate_BST method
    // Determine the incoming command and operate on the BST
    public void operate_BST(String[] command) throws FileNotFoundException {
        String[] objInfo;
        DataObj currData;
        if ((command[0].equals("insert")
            || command[0].equals("remove")
            || command[0].equals("search"))
        ) {
            //takes in user's val and reads dataset to get the desired object at that position
            FileInputStream dataSetInputStream = new FileInputStream("caffeine.csv");
            Scanner dataSetScnr = new Scanner(dataSetInputStream);
            if (Integer.parseInt(command[1]) < 1 || Integer.parseInt(command[1]) > 610) { //checks if position is valid
                writeToFile("Invalid Command", "./result.txt");
                return;
            }
            String obj = dataSetScnr.nextLine(); //skip the header of caffeine.csv
            for (int i = 0; i < Integer.parseInt(command[1]); i++) {
                obj = dataSetScnr.nextLine();
            }

            objInfo = obj.split(","); //get info about dataset item at position
            currData = new DataObj(objInfo[0],
                    Double.parseDouble(objInfo[1]),
                    Integer.parseInt(objInfo[2]),
                    Integer.parseInt(objInfo[3]),
                    objInfo[4]
            );
            dataSetScnr.close(); //close dataset file when done
        } else currData = null;

        switch (command[0]) {
            case "insert":
                mybst.insert(currData);
                writeToFile("insert " + currData, "./result.txt");
                break;
            case "remove":
                if (mybst.remove(currData)) writeToFile("removed " + currData.name(), "./result.txt");
                else writeToFile("remove failed", "./result.txt");
                break;
            case "search":
                if (mybst.search(currData, mybst.getRoot()) != null) writeToFile("found " + currData.name(), "./result.txt");
                else writeToFile("search failed", "./result.txt");
                break;
            case "print":
                writeToFile(mybst.inOrderIterator(), "./result.txt");
                break;
            case "isEmpty":
                boolean empty = mybst.isEmpty();
                writeToFile("" + empty, "./result.txt");
                break;
            // default case for Invalid Command
            default:
                writeToFile("Invalid Command", "./result.txt");
        }
    }

    public void writeToFile(String content, String filePath) throws FileNotFoundException {
        PrintWriter fileWriter = new PrintWriter(
                new FileOutputStream(filePath, true)
        );
        fileWriter.println(content);
        fileWriter.flush();
        fileWriter.close();
    }
}

