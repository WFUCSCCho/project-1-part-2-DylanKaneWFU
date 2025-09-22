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
            int i = 0;

            while (lineScnr.hasNext()) {
                String curr = lineScnr.next();
                if (curr != null) {
                    commandPieces[i] = curr;
                    i++;
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
        String name;
        switch (command[0]) {
            case "insert":
                DataObj currData = new DataObj(command[1], Double.parseDouble(command[2]), Integer.parseInt(command[3]), Integer.parseInt(command[4]), command[5]);
                mybst.insert(currData);
                writeToFile("insert " + val, "./result.txt");
                break;
            case "remove":
                name = (command[1]); //removing by name for now but if i need to remove by obj i'll do it
                if (mybst.remove(name)) writeToFile("removed " + name, "./result.txt");
                else writeToFile("remove failed", "./result.txt");
                break;
            case "search":
                name = (command[1]);
                if (mybst.search(name, mybst.getRoot()) != null) writeToFile("found " + name, "./result.txt");
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
        PrintWriter fileWriter = new PrintWriter(new FileOutputStream(filePath, true));
        fileWriter.println(content);
        fileWriter.flush();
        fileWriter.close();
    }
}

