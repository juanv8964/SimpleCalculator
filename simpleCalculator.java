import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class simpleCalculator {
public static void main(String[] args) {
Scanner input = new Scanner(System.in);
int opsCount = 0;
int[] invalidInputs = {0};
long sessionStart = System.currentTimeMillis();
boolean historySavedThisRun = false;
double num1;
double num2;
double result = 0;
double memory = 0;
int precision = 2;
String formatPattern = "%." + precision + "f";
boolean memorySet = false;
ArrayList<String> history = new ArrayList<>();
System.out.println("=== Simple Calculator ===");
File historyFile = new File("history.txt");
if (historyFile.exists())
{
    try(BufferedReader reader = new BufferedReader(new FileReader(historyFile))){
        String line;
        while ((line = reader.readLine()) != null) {
            history.add(line);
            
        }
        System.out.println("you have loaded in " + history.size() + " history entries");
        System.out.println();
    }
    catch(IOException e){
        System.out.println("Error loading history");
    }
}
int choice;
char continueoperation ='y';
while(continueoperation =='y'){
System.out.println("Status - Memory: " + memory + " | " + "History: " + history.size() + " | " + "Precision: " + precision + " Decimals");
System.out.println();
System.out.println("====== BASIC OPERATIONS ======");
System.out.println("1. Add");
System.out.println("2. Subtract");
System.out.println("3. Multiply");
System.out.println("4. Divide");
System.out.println();
System.out.println("====== ADVANCED OPERATIONS ======");
System.out.println("5. Square root");
System.out.println("6. Power");
System.out.println("7. Modulus");
System.out.println("8. Absolute value");
System.out.println();
System.out.println("====== MEMORY ======");
System.out.println("9. Memory Store (M+)");
System.out.println("10. Memory Recall (MR)");
System.out.println("11. Memory Clear (MC)");
System.out.println();
System.out.println("====== HISTORY ======");
System.out.println("12. View history");
System.out.println("13. Clear history");
System.out.println("14. Save history to file");
System.out.println("15. Clear all (file + memory)");
System.out.println();
System.out.println("==== SETTINGS ====");
System.out.println("16. Change decimal precision");
System.out.println("17. Exit");
choice = readMenu(input, "Choose your option: 1 - 17 >> ", invalidInputs);
switch (choice) {
    case 1:
    num1 = readDouble(input, "Enter first number >> ", invalidInputs);
    num2 = readDouble(input, "Enter second number >> ",invalidInputs);
    result = addNumbers(num1, num2);
    System.out.println("your answer is >> " + result);
    history.add(String.format(formatPattern + " + " + formatPattern + " = " +  formatPattern, num1, num2, result));
    ++opsCount;
    break;
    case 2:
    num1 = readDouble(input, "Enter first number >> ", invalidInputs);
    num2 = readDouble(input, "Enter second number >> ",invalidInputs);
    result = subtractNumbers(num1, num2);
    System.out.println("your answer is >> " + result);
    history.add(String.format(formatPattern + " - " + formatPattern + " = " +  formatPattern, num1, num2, result));
    ++opsCount;
    break;
    case 3:
    num1 = readDouble(input, "Enter first number >> ", invalidInputs);
    num2 = readDouble(input, "Enter second number >> ", invalidInputs);
    result = multiplyNumbers(num1, num2);
    System.out.println("your answer is >> " + result);
    history.add(String.format(formatPattern + " * " + formatPattern + " = " +  formatPattern, num1, num2, result));
    ++opsCount;
    break;
    case 4:
    num1 = readDouble(input, "Enter first number >> ", invalidInputs);
    num2 = readDouble(input, "Enter second number >> ", invalidInputs);
    try {  
    result = divideNumbers(num1, num2);
    System.out.println("your answer is >> " + result);
    history.add(String.format(formatPattern + " / " + formatPattern + " = " +  formatPattern, num1, num2, result));
    ++opsCount;
    break;
    }
    catch(ArithmeticException e) {
        System.out.println("Error. you cannot divide by 0");
        continue;
    }
    case 5:
    try {
    num1 = readDouble(input, "Enter a number >> ", invalidInputs);
    result = squareRoot(num1);
    System.out.println("your answer is >> " + result);
    history.add(String.format("√" + formatPattern + " = " + formatPattern , num1, result));
    ++opsCount;
    break;
    }
    catch(IllegalArgumentException e){
        System.out.println("Error. cannot take square root of a negative number.");
        continue;
    }
    case 6:
    try {
    num1 = readDouble(input, "Enter the base >> ", invalidInputs);
    num2 = readDouble(input, "Enter the exponent >> ", invalidInputs);
    result = powerNumbers(num1, num2);
    System.out.println("Your answer is >> " + result);
    history.add(String.format(formatPattern + " ^ " + formatPattern + " = " + formatPattern , num1, num2, result));
    ++opsCount;
    break;
    }
    catch(IllegalArgumentException e){
        System.out.println("Error. result is not a real number.");
        continue;
    }
    case 7:
    try {
    num1 = readDouble(input, "Enter first number >> ", invalidInputs);
    num2 = readDouble(input, "Enter second number >> ", invalidInputs);
    result = modulusNumbers(num1, num2);
    System.out.println("Your answer is >> " + result);
    history.add(String.format(formatPattern + " %% " + formatPattern + " = " +  formatPattern, num1, num2, result));
    ++opsCount;
    break;
    }
    catch(ArithmeticException e){
        System.out.println("Error. cannot mod a number by 0");
        continue;
    }
    case 8:
    num1 = readDouble(input, "Enter a number >> ", invalidInputs);
    result = absoluteNumbers(num1);
    System.out.println("Your answer is >> " + result);
    history.add(String.format("|" + formatPattern + "| =" + formatPattern, num1, result));
    ++opsCount;
    break;
    case 9:
    memory = result;
    memorySet = true;
    System.out.println("Stored " + memory + " in memory");
    break;
    case 10:
    if (!memorySet) {
        System.out.println("Error. There is nothing to recall inside of memory");
        break;
    }
    System.out.println("recalled from memory: " + memory);
    break;
    case 11:
    if (!memorySet) {
        System.out.println("Error. There is nothing to clear inside of memory");
        break;
    }
    memory = 0;
    memorySet = false;
    System.out.println("Memory has successfuly been cleared: " + memory);
    break;
    case 12:
    if (history.isEmpty()) {
        System.out.println("Error. There is nothing to view inside of history");
        break;
    }
    System.out.println(String.join("\n", history));
    break;
    case 13:
    if (history.isEmpty()) {
        System.out.println("Error. There is nothing to clear inside of history");
        break;
    }
    history.clear();
    System.out.println("Your history has been erased.");
    break;
    case 14:
    if (history.isEmpty()) {
        System.out.println("Error. No history to save.");
        break;
    }
    try (BufferedWriter buffer = new BufferedWriter(new FileWriter("history.txt", true)))
    {
    for (String entry : history){
        buffer.write(entry + "\n");
    }
    System.out.println("History successfully saved to history.txt");
    historySavedThisRun = true;
    }
    catch(IOException e) {
        System.out.println("Error saving history: " + e.getMessage());
    }
    break;
    case 15:
    char y;
        System.out.println("Are you sure you want to clear all ? (y/n) ");
        y = input.next().charAt(0);
        if (y != 'y') {
            break;
        }
    if (!historyFile.exists()) {
        System.out.println("The file does not exist");
        break;
    }
    if(historyFile.length() ==0){
        System.out.println("Error. The file is empty. nothing to clear");
        break;
    }
    try(FileWriter files = new FileWriter(historyFile))
    {
        System.out.println("History text file has been cleared.");
    }
    catch(IOException e)
    {
        System.out.println("Error loading." + e);
    }
    System.out.println("Sucessfully cleared history file.");
    history.clear();
    break;
    case 16:
    System.out.print("Choose your decimal precision >> ");
    precision = input.nextInt();
    if (precision < 0 || precision > 4) {
        System.out.println("Precision must be between 1 and 4. Try again");
        break;
    }
    formatPattern = "%." + precision + "f";
    System.out.println("Your precision has been changed to: " + precision);
    break;
    case 17:
    sessionSummary(sessionStart, opsCount, invalidInputs, history, memory, historySavedThisRun);
    input.close();
    return;
    default:
System.out.println("Error: invalid operator");
continue;
}
while (true) {
    System.out.print("Do you want to continue? y or n >>> ");
    String token = input.next().trim();
    if (!token.isEmpty()){
        char c = Character.toLowerCase(token.charAt(0));
        if(c == 'y' || c == 'n'){
            continueoperation = c;
            break;
        }
    }
    System.out.println("Invalid choice. Please enter y or n.");
    invalidInputs[0]++;
}
if(continueoperation == 'n')
{
    sessionSummary(sessionStart, opsCount, invalidInputs, history, memory, historySavedThisRun);
    break;
}
}
}
public static double addNumbers(double num1, double num2 )
{
    double result;
    result = num1 +num2;
    return result;
}
public static double subtractNumbers(double num1, double num2)
{
    double result;
    result = num1 - num2;
    return result;
}
public static double multiplyNumbers(double num1, double num2)
{
    double result;
    result = num1 * num2;
    return result;
}
public static double divideNumbers(double num1, double num2)
{
    if (num2 ==0){
        throw new ArithmeticException("Cannot divide by zero");
    }
    double result;
    result = num1 / num2;
    return result;
}
public static double squareRoot(double num1)
{
    if (num1 < 0){
        throw new IllegalArgumentException("Cannot take square root of a negative number");
    }
    double result;
    result = Math.sqrt(num1);
    return result;
}
public static double powerNumbers(double num1, double num2)
{
    double result;
    result = Math.pow(num1, num2);
    if (Double.isNaN(result)){
        throw new IllegalArgumentException("Result is not a real number. ");
    }
    return result;
}
public static double modulusNumbers(double num1, double num2)
{
    if (num2 == 0){
        throw new ArithmeticException("Cannot mod a number by 0");
    }
    double result;
    result = num1 % num2;
    return result;
}
public static double absoluteNumbers(double num1)
{
    double result;
    result = Math.abs(num1);
    return result;
}

public static double readDouble(Scanner input, String prompt, int [] invalidInputs)
{   while (true) {
    System.out.print(prompt);
    if (input.hasNextDouble()) {
    return input.nextDouble();
    }

else {
    System.out.println("Invalid input try again.");
    invalidInputs[0]++;
    input.next();
    continue;
}
}
}
public static void sessionSummary(long sessionStart, int opsCount, int[] invalidInputs, ArrayList<String> history, double memory, boolean historySavedThisRun)
{
    long sessionEnd = System.currentTimeMillis();
    long duration = sessionEnd - sessionStart;
    long durationsInSeconds = duration / 1000;
    long minutes = durationsInSeconds / 60;
    long seconds = durationsInSeconds % 60;
    System.out.println("=== Session Summary ===");
    System.out.println("Operations run: " + opsCount);
    System.out.println("Invalid inputs: " + invalidInputs[0]);
    System.out.println("Histories entries this session: "+history.size());
    System.out.println("Memory set: " + memory);
    System.out.println("History saved to file: " + historySavedThisRun);
    System.out.println("Session duration: " + minutes + "m " + seconds + "s");
    System.out.println("Thanks for using Simple Calculator");
}
public static int readMenu(Scanner input, String prompt, int [] invalidInputs)
{
    int value;
    while (true) {
    System.out.print(prompt);
    if (!input.hasNextInt()) {;
    System.out.println("Invalid input try again.");
    invalidInputs[0]++;
    input.next();
    continue;
    }
    value = input.nextInt();
    input.nextLine();
    if(value < 1 || value > 17){
    System.out.println("Invalid input try again.");
    invalidInputs[0]++;
    continue;
    }
    return value;
}
    }
}