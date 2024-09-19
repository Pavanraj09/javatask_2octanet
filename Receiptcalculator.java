

package java4.receiptcalculator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Receiptcalculator {

    public static void main(String[] args) {
       
        Scanner scanner = new Scanner(System.in);
        List<Double> prices = new ArrayList<>();
        List<Integer> quantities = new ArrayList<>();
        List<String> items = new ArrayList<>();

        System.out.println("Receipt Calculator");
        
        while (true) {
            System.out.print("Enter item name (or type 'done' to finish): ");
            String itemName = scanner.nextLine();
            if (itemName.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter item price: ");
            try {
                double price = Double.parseDouble(scanner.nextLine());
                System.out.print("Enter quantity for this item: ");
                int quantity = Integer.parseInt(scanner.nextLine());
                
                prices.add(price);
                quantities.add(quantity);
                items.add(itemName);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a valid price or quantity.");
            }
        }

        double total = calculateTotal(prices, quantities);
        double tax = total * 0.07; // Assume a 7% sales tax
        double grandTotal = total + tax;

        String receipt = generateReceipt(items, prices, quantities, total, tax, grandTotal);
        System.out.println(receipt);
        
        saveReceiptToFile(receipt);

        scanner.close();
    }

    private static double calculateTotal(List<Double> prices, List<Integer> quantities) {
        double total = 0.0;
        for (int i = 0; i < prices.size(); i++) {
            total += prices.get(i) * quantities.get(i);
        }
        return total;
    }

    private static String generateReceipt(List<String> items, List<Double> prices, List<Integer> quantities, double total, double tax, double grandTotal) {
        StringBuilder receipt = new StringBuilder();
        receipt.append("----- Receipt -----\n");
        for (int i = 0; i < items.size(); i++) {
            receipt.append(String.format("%s - $%.2f x %d = $%.2f\n", items.get(i), prices.get(i), quantities.get(i), prices.get(i) * quantities.get(i)));
        }
        receipt.append("-------------------\n");
        receipt.append(String.format("Subtotal: $%.2f\n", total));
        receipt.append(String.format("Sales Tax (7%%): $%.2f\n", tax));
        receipt.append(String.format("Grand Total: $%.2f\n", grandTotal));
        receipt.append("-------------------\n");
        return receipt.toString();
    }

    private static void saveReceiptToFile(String receipt) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("receipt.txt"))) {
            writer.write(receipt);
            System.out.println("Receipt saved to 'receipt.txt'");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the receipt: " + e.getMessage());
            //iam sangineni pavanraj this is my secound task build 
        }
    }
}
