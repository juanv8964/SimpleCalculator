public class Calculate {
public double addNumbers(double num1, double num2 )
{
    double result;
    result = num1 +num2;
    return result;
}
public double subtractNumbers(double num1, double num2)
{
    double result;
    result = num1 - num2;
    return result;
}
public double multiplyNumbers(double num1, double num2)
{
    double result;
    result = num1 * num2;
    return result;
}
public double divideNumbers(double num1, double num2)
{
    if (num2 ==0){
        throw new ArithmeticException("Cannot divide by zero");
    }
    double result;
    result = num1 / num2;
    return result;
}
public double squareRoot(double num1)
{
    if (num1 < 0){
        throw new IllegalArgumentException("Cannot take square root of a negative number");
    }
    double result;
    result = Math.sqrt(num1);
    return result;
}
public double powerNumbers(double num1, double num2)
{
    double result;
    result = Math.pow(num1, num2);
    if (Double.isNaN(result)){
        throw new IllegalArgumentException("Result is not a real number. ");
    }
    return result;
}
public double modulusNumbers(double num1, double num2)
{
    if (num2 == 0){
        throw new ArithmeticException("Cannot mod a number by 0");
    }
    double result;
    result = num1 % num2;
    return result;
}
public double absoluteNumbers(double num1)
{
    double result;
    result = Math.abs(num1);
    return result;
}
public double sin(double num1){
    return Math.sin(Math.toRadians(num1));
}
public double cos(double num1){
    return Math.cos(Math.toRadians(num1));
}
public double tan(double num1){
    return Math.tan(Math.toRadians(num1));
}
public double log(double num1){
    if(num1 <= 0){
        throw new IllegalArgumentException("Cannot take log of a non positive number");
    }
    return Math.log10(num1);
}
public double exp(double num1){
    return Math.exp(num1);
}
public long factorial(int num1){
    if (num1 < 0) {
        throw new IllegalArgumentException("factorials cannot be negative");
    }
    int result =1;
    for(int x = 1; x <= num1; x++ )
    {
        result *= x;
    }
    return result;
}
}
