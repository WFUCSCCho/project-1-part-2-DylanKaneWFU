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
            String[] commandPieces = new String[10];
            String command = lineScnr.next();
            if (command != null) {
                commandPieces[0] = command;
                if (lineScnr.hasNextLine())
                    commandPieces[1] = lineScnr.nextLine();
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
        if (command[0].equals("insert")
            || command[0].equals("remove")
            || command[0].equals("search")
        ) {
            objInfo = command[1].split(",");
            currData = new DataObj(objInfo[0],
                    Double.parseDouble(objInfo[1]),
                    Integer.parseInt(objInfo[2]),
                    Integer.parseInt(objInfo[3]),
                    objInfo[4]
            );
        } else currData = null;


        switch (command[0]) {
            case "insert":
                try {
                    objInfo = command[1]
                            .substring(1)
                            .split(",");
                    currData = new DataObj(objInfo[0],
                            Double.parseDouble(objInfo[1]),
                            Integer.parseInt(objInfo[2]),
                            Integer.parseInt(objInfo[3]),
                            objInfo[4]
                    );
                    mybst.insert(currData);
                    writeToFile("insert " + currData, "./result.txt");
                } catch (Exception e) { //added to handle invalid object inserts
                    writeToFile("insert failed", "./result.txt");
                }
                break;
            case "remove":
                objInfo = command[1].split(",");
                currData = new DataObj(objInfo[0],
                        Double.parseDouble(objInfo[1]),
                        Integer.parseInt(objInfo[2]),
                        Integer.parseInt(objInfo[3]),
                        objInfo[4]
                );
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

