package Ayakashi.managers;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileManager {

    private final static File dataFile = new File("data");

    private static Map<String, AdvancedCrashManager> crash = new HashMap<>();

    public static void CreateDataFile() {
        try {
            new File("data").mkdir();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String pin;
    public static String loadPinCode() {
        try {
            final Scanner _scanner = new Scanner(new File("data", "saved.txt"));
            while (_scanner.hasNext()) {
                final String[] _split = _scanner.next().split(":", 2);
                System.out.println(_split[0]);
                pin = _split[0];
            }
            _scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pin;
    }

    public static void addCrash(String _methodName, String _packetName, Integer _pages, Integer _delay, Integer _amount) {
        if (dataFile.exists()) {
            try (FileWriter _fileWriter = new FileWriter(dataFile + "/" + _methodName + ".txt")) {
                _fileWriter.write( _methodName + ":" + _packetName + ":" + _pages + ":" + _delay + ":" + _amount);
                _fileWriter.flush();
                System.out.println(_methodName + ":" + _packetName + ":" + _pages + ":" + _delay + ":" + _amount);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            CreateDataFile();
        }
    }
}