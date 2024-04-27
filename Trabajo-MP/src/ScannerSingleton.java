import java.io.Serializable;
import java.util.Scanner;

public class ScannerSingleton implements Serializable {
    private static Scanner scannerInstance;


    private ScannerSingleton() {}


    public static Scanner getInstance() {
        if (scannerInstance == null) {
            scannerInstance = new Scanner(System.in);
        }
        return scannerInstance;
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        in.defaultReadObject();
        scannerInstance = new Scanner(System.in);
    }


    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
        out.defaultWriteObject();
    }
}
