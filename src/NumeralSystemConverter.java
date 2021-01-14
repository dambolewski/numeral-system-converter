
import java.util.Scanner;

public class NumeralSystemConverter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isNumberWrong = true;
        boolean isRadixWrong = false;

        int sourceRadix = 0;
        String stringNumber = null;
        int targetRadix = 0;


        System.out.println("Input source radix, number that you want to convert, target radix:");


        if (sc.hasNextInt())
            sourceRadix = sc.nextInt();
        else isRadixWrong = true;

        if (sc.hasNext())
            stringNumber = sc.next();
        assert stringNumber != null;
        String[] sourceNumber = stringNumber.split("\\.");

        if (sc.hasNextInt())
            targetRadix = sc.nextInt();
        else isRadixWrong = true;

        if (sourceRadix < 1 || sourceRadix > 36
                || targetRadix < 1 || targetRadix > 36) {
            isRadixWrong = true;
        }

        if(stringNumber.matches("[0-9A-Za-z]+(.[0-9A-Za-z]+)?")){
            isNumberWrong = false;
        }


        if (isRadixWrong) {
            System.out.println("Clearly there is problem with the radixes");
        }
        else if(isNumberWrong){
                System.out.println("I see there is problem with number itself");
            }
        else {

            double fractionalDecimalNumber = 0;
            int decimalNumber;

            if (sourceRadix == 1) {
                decimalNumber = sourceNumber[0].length();
            } else if (sourceNumber.length > 1) {
                decimalNumber = Integer.parseInt(sourceNumber[0], sourceRadix);
                String fractionalPart = sourceNumber[1];
                for (int i = 0; i < fractionalPart.length(); i++) {
                    double fractionForFormula = Character.getNumericValue(fractionalPart.charAt(i));
                    fractionalDecimalNumber = fractionalDecimalNumber + (fractionForFormula / Math.pow(sourceRadix, i + 1));
                }
            } else {
                decimalNumber = Integer.parseInt(sourceNumber[0], sourceRadix);
            }

            StringBuilder sb = new StringBuilder();
            String baseInteger;

            if (targetRadix == 1) {
                for (int i = 0; i < Integer.parseInt(sourceNumber[0]); i++) {
                    System.out.print(1 + "");
                }
            } else {
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