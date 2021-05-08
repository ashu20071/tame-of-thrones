import java.io.*;

/**
 * Driver class which holds main method.
 */

public class Geektrust {
    public static void main(String[] args) throws IOException {
        String filePath = args[0];
        File file = new File(filePath);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String inputMessage;
        String outputMessage = "";
        // Initialize controller with kingdom which sends the secret message
        Controller controller = new Controller("SPACE");
        while ((inputMessage = bufferedReader.readLine()) != null) {
            try {
                InputObject inputObject = controller.validateMessage(inputMessage);
                OutputObject outputObject = controller.processMessage(inputObject);
                outputMessage = controller.buildOutput(outputObject);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        System.out.println(outputMessage);
    }
}
