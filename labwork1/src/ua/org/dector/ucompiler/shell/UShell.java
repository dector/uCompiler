package ua.org.dector.ucompiler.shell;

import ua.org.dector.ucompiler.queries.QueryWrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dector
 */
public class UShell implements Runnable {
    public static void main(String[] args) {
        UShell uShell = new UShell(args);
        uShell.run();
    }

    public UShell(String[] args) {
        System.out.println("===== uShell pre-start preparations STARTED =====");
        performOperations(parseOperations(args));
        System.out.println("=====uShell pre-start preparations FINISHED =====\n");
    }

    public void run() {
        System.out.println("uShell has been started");
    }

    private Map<String, String> parseOperations(String[] args) {
        Map<String, String> arguments = new HashMap<String, String>();

        String currentArg;
        for (int i = 0; i < args.length; i++) {
            currentArg = args[i];
            if (currentArg.startsWith("--")) {
                arguments.put(currentArg.substring(2).toLowerCase(), args[++i]);
            }
        }

        System.out.println("Parsed operations: " + arguments);

        return arguments;
    }

    private void performOperations(Map<String, String> operations) {
        for (String operationName : operations.keySet()) {
            String operationArgument = operations.get(operationName);

            if (operationName.equals(ShellOperations.LOAD_OPERATION)) {             // already ignoredcase at parseOperations
                QueryWrapper.executeQueriesFromFile(operationArgument);
//                List<String> queriesList = ShellUtils.loadFile(operationArgument);
//                QueryWrapper.executeQueries(queriesList);
            } //else if ...
        }
    }
}
