
import java.util.Scanner;

public class NumeralSystemConverter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isNumberWrong = true;
        boolean isRadixWrong = false;
        int sourceRadix = 0;
        String stringNumber = null;
        int targetRadix = 0;
        StringBuilder sb = new StringBuilder();
        String baseInteger;

        System.out.println("Input source radix, number that you want to convert, target radix:");

        //Input validation
        if (sc.hasNextInt())
            sourceRadix = sc.nextInt();
        else isRadixWrong = true;

        if (sc.hasNext())
            stringNumber = sc.next();
        assert stringNumber != null;
        //Splitting a number into two parts - before and after the separator (if any).
        String[] sourceNumber = stringNumber.split("\\.");

        if (sc.hasNextInt())
            targetRadix = sc.nextInt();
        else isRadixWrong = true;

        if (sourceRadix < 1 || sourceRadix > 36
                || targetRadix < 1 || targetRadix > 36) {
            isRadixWrong = true;
        }
        //Checking if the number containing letter is valid (fe. af.xy number with base 35)
        //Invalid numbers are going to be handled in try/catch later on (fe. asd number with base 10)
        if (stringNumber.matches("[0-9A-Za-z]+(.[0-9A-Za-z]+)?")) {
            isNumberWrong = false;
        }

        //Depending on the results of checking the input, present: error messages or output
        if (isRadixWrong) {
            System.out.println("Clearly there is problem with the radixes");
        } else if (isNumberWrong) {
            System.out.println("I see there is problem with number itself");
        } else {
            double fractionalDecimalNumber = 0;
            int decimalNumber = 0;

            //(The conversion consists in converting a number to a decimal, and then to a number with a given number)
            //Convert a number to a number with the given base to decimal.
            //If the base was 1, the result will be the length of that number (fe. 1111111 = 7 in decimal)
            if (sourceRadix == 1) {
                decimalNumber = sourceNumber[0].length();
            }
            //If the value is separated by a separator - convert it according to the formula
            //presented on the HyperSkill website.
            else if (sourceNumber.length > 1) {
                decimalNumber = Integer.parseInt(sourceNumber[0], sourceRadix);
                String fractionalPart = sourceNumber[1];
                for (int i = 0; i < fractionalPart.length(); i++) {
                    double fractionForFormula = Character.getNumericValue(fractionalPart.charAt(i));
                    fractionalDecimalNumber = fractionalDecimalNumber + (fractionForFormula / Math.pow(sourceRadix, i + 1));
                }
            }
            //As a last resort - the number is without separator nor with base 1 -
            //convert it to a decimal number. If it is not possible - close application with message.
            //(there is no such number with such a base fe. asd number with base 10 mentioned before),
            else {
                try {
                    decimalNumber = Integer.parseInt(sourceNumber[0], sourceRadix);
                } catch (NumberFormatException e) {
                    System.out.println("I see there is problem with number itself");
                    System.exit(0);
                }
            }

            //Convert a decimal number to a base 1 number
            if (targetRadix == 1) {
                for (int i = 0; i < Integer.parseInt(sourceNumber[0]); i++) {
                    System.out.print(1 + "");
                }
            }
            //If the value is separated by a separator - convert it according to the formula
            //presented on the HyperSkill website.
            else {
                //
                baseInteger = Integer.toString(decimalNumber, targetRadix);
                sb.append(baseInteger);
                if (fractionalDecimalNumber != 0) {
                    sb.append(".");
                    for (int i = 0; i < 5; i++) {
                        double baseFraction;
                        baseFraction = fractionalDecimalNumber * targetRadix;
                        String firstValueLoop = String.valueOf(baseFraction).split("\\.")[0];
                        if (Integer.parseInt(firstValueLoop) > 9) {
                            sb.append(Character.forDigit(Integer.parseInt(firstValueLoop), targetRadix));
                        } else {
                            sb.append(firstValueLoop);
                        }
                        fractionalDecimalNumber = baseFraction - Integer.parseInt(firstValueLoop);

                    }
                }
            }
            System.out.println(sb);
        }
    }
}