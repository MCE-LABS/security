import java.util.Scanner;

public class RailFenceCipher {

    // Function to perform encryption using Rail Fence Cipher
    public static String encrypt(String message, int rails) {
        char[][] railMatrix = new char[rails][message.length()];
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < message.length(); j++) {
                railMatrix[i][j] = '\0';  // Initializing the matrix with null characters
            }
        }

        int row = 0;
        boolean down = true;

        for (int i = 0; i < message.length(); i++) {
            railMatrix[row][i] = message.charAt(i);

            if (row == rails - 1) {
                down = false;
            } else if (row == 0) {
                down = true;
            }

            if (down) {
                row++;
            } else {
                row--;
            }
        }

        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < message.length(); j++) {
                if (railMatrix[i][j] != '\0') {
                    encryptedText.append(railMatrix[i][j]);
                }
            }
        }

        return encryptedText.toString();
    }

    // Function to perform decryption using Rail Fence Cipher
    public static String decrypt(String ciphertext, int rails) {
        char[][] railMatrix = new char[rails][ciphertext.length()];
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < ciphertext.length(); j++) {
                railMatrix[i][j] = '\0';  // Initializing the matrix with null characters
            }
        }

        int row = 0;
        boolean down = true;

        for (int i = 0; i < ciphertext.length(); i++) {
            railMatrix[row][i] = 'X';  // Filling the matrix with placeholder characters

            if (row == rails - 1) {
                down = false;
            } else if (row == 0) {
                down = true;
            }

            if (down) {
                row++;
            } else {
                row--;
            }
        }

        int index = 0;
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < ciphertext.length(); j++) {
                if (railMatrix[i][j] == 'X' && index < ciphertext.length()) {
                    railMatrix[i][j] = ciphertext.charAt(index++);
                }
            }
        }

        StringBuilder decryptedText = new StringBuilder();
        row = 0;
        down = true;

        for (int i = 0; i < ciphertext.length(); i++) {
            decryptedText.append(railMatrix[row][i]);

            if (row == rails - 1) {
                down = false;
            } else if (row == 0) {
                down = true;
            }

            if (down) {
                row++;
            } else {
                row--;
            }
        }

        return decryptedText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the message: ");
        String message = scanner.nextLine().toUpperCase();

        // Default number of rails is set to 2
        int defaultRails = 2;

        // Encryption with default number of rails
        String encryptedMessage = encrypt(message, defaultRails);
        System.out.println("Encrypted message: " + encryptedMessage);

        // Decryption with default number of rails
        String decryptedMessage = decrypt(encryptedMessage, defaultRails);
        System.out.println("Decrypted message: " + decryptedMessage);
    }
}
