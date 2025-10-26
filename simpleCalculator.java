import java.util.ArrayList;
import java.util.Scanner;
public class simpleCalculator {
public static void main(String[] args) {
Calculate calc = new Calculate();
Settings userSettings = new Settings();
HistoryManager manager = new HistoryManager();
Scanner input = new Scanner(System.in);
int opsCount = 0;
int[] invalidInputs = {0};
long sessionStart = System.currentTimeMillis();
boolean historySavedThisRun = false;
double num1;
double num2;
double result = 0;
double memory = 0;
String format = userSettings.getFormatPattern();
boolean memorySet = false;
System.out.println("=== Simple Calculator ===");
manager.loadHistory();
int choice;
char continueoperation ='y';
while(continueoperation =='y'){
System.out.println("Status - Memory: " + memory + " | " + "History: " + manager.getHistorySize() + " | " + "Precision: " + userSettings.getPrecision() + " Decimals");
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
System.out.println("====== SCIENTIFIC OPERATIONS ======");
System.out.println("16. Sine");
System.out.println("17. Cosine");
System.out.println("18. Tangent");
System.out.println("19. Logarithm");
System.out.println("20. Exponential");
System.out.println("21. Factorial");
System.out.println();
System.out.println("==== SETTINGS ====");
System.out.println("22. Change decimal precision");
System.out.println("23. Exit");
choice = readMenu(input, "Choose your option: 1 - 23 >> ", invalidInputs);
switch (choice) {
    case 1:
    num1 = readDouble(input, "Enter first number >> ", invalidInputs);
    num2 = readDouble(input, "Enter second number >> ",invalidInputs);
    result = calc.addNumbers(num1, num2);
    System.out.println("your answer is >> " + result);
    manager.addEntry(String.format(format + " + " + format + " = " +  format, num1, num2, result));
    ++opsCount;
    break;
    case 2:
    num1 = readDouble(input, "Enter first number >> ", invalidInputs);
    num2 = readDouble(input, "Enter second number >> ",invalidInputs);
    result = calc.subtractNumbers(num1, num2);
    System.out.println("your answer is >> " + result);
    manager.addEntry(String.format(format + " - " + format + " = " +  format, num1, num2, result));
    ++opsCount;
    break;
    case 3:
    num1 = readDouble(input, "Enter first number >> ", invalidInputs);
    num2 = readDouble(input, "Enter second number >> ", invalidInputs);
    result = calc.multiplyNumbers(num1, num2);
    System.out.println("your answer is >> " + result);
    manager.addEntry(String.format(format + " * " + format + " = " +  format, num1, num2, result));
    ++opsCount;
    break;
    case 4:
    num1 = readDouble(input, "Enter first number >> ", invalidInputs);
    num2 = readDouble(input, "Enter second number >> ", invalidInputs);
    try {  
    result = calc.divideNumbers(num1, num2);
    System.out.println("your answer is >> " + result);
    manager.addEntry(String.format(format + " / " + format + " = " +  format, num1, num2, result));
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
    result = calc.squareRoot(num1);
    System.out.println("your answer is >> " + result);
    manager.addEntry(String.format("√" + format + " = " + format , num1, result));
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
    result = calc.powerNumbers(num1, num2);
    System.out.println("Your answer is >> " + result);
    manager.addEntry(String.format(format + " ^ " + format + " = " + format , num1, num2, result));
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
    result = calc.modulusNumbers(num1, num2);
    System.out.println("Your answer is >> " + result);
    manager.addEntry(String.format(format + " %% " + format + " = " +  format, num1, num2, result));
    ++opsCount;
    break;
    }
    catch(ArithmeticException e){
        System.out.println("Error. cannot mod a number by 0");
        continue;
    }
    case 8:
    num1 = readDouble(input, "Enter a number >> ", invalidInputs);
    result = calc.absoluteNumbers(num1);
    System.out.println("Your answer is >> " + result);
    manager.addEntry(String.format("|" + format + "| =" + format, num1, result));
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
    if (manager.isEmpty()) {
        System.out.println("Error. There is nothing to view inside of history");
        break;
    }
    System.out.println(String.join("\n", manager.getHistory()));
    break;
    case 13:
    if (manager.isEmpty()) {
        System.out.println("Error. There is nothing to clear inside of history");
        break;
    }
    manager.clearHistory();
    break;
    case 14:
    if (manager.isEmpty()) {
        break;
    }
    manager.saveHistoryFile();
    historySavedThisRun = true;
    break;
    case 15:
    char y;
        System.out.println("Are you sure you want to clear all ? (y/n) ");
        y = input.next().charAt(0);
        if (y != 'y') {
            break;
        }
    manager.clearFile();
    manager.clearHistory();
    memory = 0;
    memorySet = false;
    break;
    case 16:
    num1 = readDouble(input, "Enter angle in degrees >> ", invalidInputs);
    result = calc.sin(num1);
    System.out.println("Your answer is >> " + result);
    manager.addEntry(String.format("sin(" + format + ") = " + format, num1, result));
    ++opsCount;
    break;
    case 17:
    num1 = readDouble(input, "Enter angle in degrees >> ", invalidInputs);
    result = calc.cos(num1);
    System.out.println("Your answer is >> " + result);
    manager.addEntry(String.format("cos(" + format + ") = " + format, num1, result));
    ++opsCount;
    break;
    case 18:
    num1 = readDouble(input, "Enter angle in degrees >> ", invalidInputs);
    result = calc.tan(num1);
    System.out.println("Your answer is >> " + result);
    manager.addEntry(String.format("tan(" + format + ") = " + format, num1, result));
    ++opsCount;
    break;
    case 19:
    num1 = readDouble(input, "Enter a positive number >> ", invalidInputs);
    try {
    result = calc.log(num1);
    System.out.println("Your answer is >> " + result);
    manager.addEntry(String.format("log(" + format + ") = + " + format, num1, result));
    ++opsCount;
    }
    catch(IllegalArgumentException e){
        System.out.println("Error: " + e.getMessage());
    }
    break;
    case 20:
    num1 = readDouble(input, "Enter a number >> ", invalidInputs);
    result = calc.exp(num1);
    System.out.println("Your answer is >> " + result);
    manager.addEntry(String.format("exp(" + format + ") = " + format, num1, result));
    ++opsCount;
    break;
    case 21:
    num1 = readDouble(input, "Enter a non-negative number >> ", invalidInputs);
    try{
        result = calc.factorial((int) num1);
        System.out.println("Your answer is >> " + result);
        manager.addEntry(String.format("%.0f! = %.0f", num1, result));
        ++opsCount;
    }
    catch (IllegalArgumentException e)
    {
        System.out.println("Error: " + e.getMessage());
    }
    break;
    case 22:
    System.out.print("Choose your decimal precision >> ");
    int p = input.nextInt();
    try{
    userSettings.setPrecision(p);
    System.out.println("Your precision has been changed to: " + userSettings.getPrecision());
    }
    catch(IllegalArgumentException e){
        System.out.println("Invalid precision. " + e.getMessage());
    }
    break;
    case 23:
    sessionSummary(sessionStart, opsCount, invalidInputs, manager.getHistory(), memory, historySavedThisRun);
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
    sessionSummary(sessionStart, opsCount, invalidInputs, manager.getHistory(), memory, historySavedThisRun);
    break;
}
}
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
    if(value < 1 || value > 23){
    System.out.println("Invalid input try again.");
    invalidInputs[0]++;
    continue;
    }
    return value;
}
    }
}