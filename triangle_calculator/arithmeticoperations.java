//********** STUDENT INFORMATION **********************************************************************
//  Student Name: Brian Y
//  Student E-mail: ***

//********** MAIN CODE AREA **********************************************************************
public class arithmeticoperations
{
  public static Double add(Double number1, Double number2)
  {
    Double sum;
    sum = number1 + number2;
    return sum;
  }

  public static Double multiply(Double firstnumber, Double secondnumber)
  {
    Double product;
    product = firstnumber * secondnumber;
    return product;
  }

  public static Double power(Double firstnumber, int secondnumber)
  {
    Double power;
    power = Math.pow(firstnumber, secondnumber);
    return power;
  }

  public static Double devide(Double firstnumber, int secondnumber)
  {
    Double devision;
    devision = firstnumber / secondnumber;
    return devision;
  }

  public static Double squareRoot(Double number)
  {
    Double sqrt;
    sqrt = Math.sqrt(number);
    return sqrt;
  }
} //end of arithmeticOperations class