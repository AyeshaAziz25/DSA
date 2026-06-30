import java.util.Scanner;
import javax.sound.sampled.*;

public class SimpleBinarySound {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Get user input
        System.out.print("Enter a number, word, or sentence: ");
        String input = scanner.nextLine();
        scanner.close();

        // Step 2: Convert input to decimal
        System.out.println("\nDecimal representation:");
        StringBuilder binaryString = new StringBuilder();

        for (char c : input.toCharArray()) {
            int decimalValue = (int) c; // ASCII value for characters
            System.out.println(c + " -> " + decimalValue);

            // Step 3: Convert to binary
            String binaryValue = Integer.toBinaryString(decimalValue);
            binaryString.append(binaryValue).append(" ");
        }

        System.out.println("\nBinary representation:");
        System.out.println(binaryString);

        // Step 4: Generate sound based on binary sequence
        System.out.println("\nGenerating sound...");
        playSound(binaryString.toString());
    }

    // Function to generate sound
    public static void playSound(String binaryString) {
        try {
            for (char bit : binaryString.toCharArray()) {
                if (bit == '1') {
                    // Generate a short beep sound for '1'
                    beep(400, 150);
                } else if (bit == '0') {
                    // Generate a lower beep for '0'
                    beep(200, 100);
                }
                Thread.sleep(50); // Short pause between beeps
            }
        } catch (InterruptedException e) {
            System.out.println("Error in sound generation: " + e.getMessage());
        }
    }

    // Function to create a beep sound
    public static void beep(int frequency, int duration) {
        try {
            float sampleRate = 44100;
            byte[] buf = new byte[(int) sampleRate * duration / 1000];

            for (int i = 0; i < buf.length; i++) {
                double angle = 2.0 * Math.PI * i * frequency / sampleRate;
                buf[i] = (byte) (Math.sin(angle) * 127);
            }

            AudioFormat format = new AudioFormat(sampleRate, 8, 1, true, false);
            SourceDataLine sdl = AudioSystem.getSourceDataLine(format);
            sdl.open(format);
            sdl.start();
            sdl.write(buf, 0, buf.length);
            sdl.drain();
            sdl.close();
        } catch (Exception e) {
            System.out.println("Sound generation error: " + e.getMessage());
        }
    }
}
/*import java.util.Scanner;

public class SimpleBinarySound {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user input
        System.out.print("Enter a number, word, or sentence: ");
        String input = scanner.nextLine();
        scanner.close();

        // Convert input to decimal and binary
        StringBuilder binaryString = new StringBuilder();
        System.out.println("\nDecimal and Binary representation:");

        for (char c : input.toCharArray()) {
            int decimalValue = (int) c; // ASCII to decimal
            String binary = Integer.toBinaryString(decimalValue); // Decimal to binary

            System.out.println("'" + c + "' -> Decimal: " + decimalValue + " | Binary: " + binary);
            binaryString.append(binary).append(" ");
        }

        // Display final binary output
        System.out.println("\nFinal Binary Sequence: " + binaryString);

        // Generate beep sound for binary digits
        for (char bit : binaryString.toString().toCharArray()) {
            if (bit == '1') {
                java.awt.Toolkit.getDefaultToolkit().beep(); // Beep for '1'
                try { Thread.sleep(150); } catch (InterruptedException e) {} 
            } else if (bit == '0') {
                try { Thread.sleep(100); } catch (InterruptedException e) {} 
            }
        }
        
        System.out.println("\nBeep sound played based on binary sequence.");
    }
}
*/
