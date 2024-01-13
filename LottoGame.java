import java.util.Arrays;
import java.util.Random;
import javax.swing.JOptionPane;

public class LottoGame {

    public static void main(String[] args) {
        // Step 1: Welcome messages
        JOptionPane.showMessageDialog(null,
                "Mauritius LottoTech.\n" +
                        "Welcome to Lotto Game.\n" +
                        "Mauritius LottoTech wishes you good luck!");

        // Step 2: Player enters six unique numbers
        int[] playerNumbers = getPlayerNumbers();

        // Step 3: Display player numbers
        displayPlayerNumbers(playerNumbers);

        // Additional message after displaying player numbers
        JOptionPane.showMessageDialog(null, "Now Lottotech machine will generate 6 winning numbers...\nRead...\nGo...");

        // Step 4: Generate six winning numbers
        int[] winningNumbers = generateWinningNumbers();

        // Step 5: Display winning numbers
        displayWinningNumbers(winningNumbers);

        // Step 6: Verify player numbers using a searching algorithm
        int matchingNumbers = verifyPlayerNumbers(playerNumbers, winningNumbers);

        // Step 7: Display final results
        displayResults(matchingNumbers, playerNumbers);
    }

    // Function to get six unique numbers from the player using JOptionPane
    private static int[] getPlayerNumbers() {
        int[] playerNumbers = new int[6];

        for (int i = 0; i < 6; i++) {
            boolean validInput = false;
            while (!validInput) {
                try {
                    String input = JOptionPane.showInputDialog("Hi Player\nPlease enter your number in range 1-40\nNote that you cannot re-enter the same number.");
                    int number = Integer.parseInt(input);

                    if (isValidNumber(number, playerNumbers) && number >= 1 && number <= 40) {
                        playerNumbers[i] = number;
                        validInput = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter a unique number in the range 1-40.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a numeric value.");
                }
            }
        }

        return playerNumbers;
    }

    // Function to check if a number is valid (unique and in the range 1-40)
    private static boolean isValidNumber(int number, int[] numbers) {
        return number >= 1 && number <= 40 && Arrays.stream(numbers).noneMatch(n -> n == number);
    }

    // Function to generate six unique winning numbers
    private static int[] generateWinningNumbers() {
        int[] winningNumbers = new int[6];
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            int number;
            do {
                number = random.nextInt(40) + 1;
            } while (contains(winningNumbers, number));
            winningNumbers[i] = number;
        }

        return winningNumbers;
    }

    // Function to check if an array contains a specific number
    private static boolean contains(int[] array, int number) {
        for (int value : array) {
            if (value == number) {
                return true;
            }
        }
        return false;
    }

    // Function to verify player numbers using a searching algorithm
    private static int verifyPlayerNumbers(int[] playerNumbers, int[] winningNumbers) {
        int matchingNumbers = 0;

        for (int playerNumber : playerNumbers) {
            if (contains(winningNumbers, playerNumber)) {
                matchingNumbers++;
            }
        }

        return matchingNumbers;
    }

    // Function to display player numbers
    private static void displayPlayerNumbers(int[] playerNumbers) {
        StringBuilder playerMessage = new StringBuilder("Player numbers:\n");

        for (int i = 0; i < playerNumbers.length; i++) {
            playerMessage.append("Array Cell: ").append(i).append(", Player Number: ").append(playerNumbers[i]).append("\n");
        }

        JOptionPane.showMessageDialog(null, playerMessage.toString());
    }

    // Function to display winning numbers
    private static void displayWinningNumbers(int[] winningNumbers) {
        StringBuilder winningMessage = new StringBuilder("Lottotech winning numbers:\n");

        for (int i = 0; i < winningNumbers.length; i++) {
            winningMessage.append("Array Cell: ").append(i).append(", Winning Number: ").append(winningNumbers[i]).append("\n");
        }

        JOptionPane.showMessageDialog(null, winningMessage.toString());
    }

    // Function to display final results using JOptionPane
    private static void displayResults(int matchingNumbers, int[] playerNumbers) {
        StringBuilder resultMessage = new StringBuilder();
                resultMessage.append("*****************\n");
        resultMessage.append("Using searching algo to verify the player numbers...\n");

        resultMessage.append("Player numbers matching with lotto numbers are: ").append(matchingNumbers).append("\n");

        switch (matchingNumbers) {
            case 3:
                resultMessage.append("Congratulations! You win Rs 100.");
                break;
            case 4:
                resultMessage.append("Congratulations! You win Rs 500.");
                break;
            case 5:
                resultMessage.append("Congratulations! You win Rs 1000.");
                break;
            case 6:
                resultMessage.append("Congratulations! You win the jackpot of Rs 10M (ten million rupees)!");
                break;
            default:
                resultMessage.append("Back luck, your chosen numbers do not match with the lotto lucky numbers\nBetter luck next time...\nPlay again...");
        }

        JOptionPane.showMessageDialog(null, resultMessage.toString());
    }
}
