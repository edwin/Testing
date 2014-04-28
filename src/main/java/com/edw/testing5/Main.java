package com.edw.testing5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author edwin
 */
public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }

    private void start() {
        try {
            List<String> kartus = new ArrayList<>();

            System.out.print("Jumlah Kartu > ");
            Scanner scannerJumlahKartu = new Scanner(System.in);

            int jumlahKartu = scannerJumlahKartu.nextInt();

            for (int i = 0; i < jumlahKartu; i++) {
                System.out.print("Kartu " + (i + 1) + "> ");
                Scanner scannerKartu = new Scanner(System.in);
                String kartu = scannerKartu.nextLine();
                kartus.add(kartu);
            }

            for (int i = 0; i < kartus.size(); i++) {
                System.out.println("Output Kartu " + (i + 1) + "> " + checkLuhn(kartus.get(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int checkLuhn(String number) {
        int totalLuhnNumber = 0;
        int numberOfQuestionMarks = number.split("\\?", -1).length - 1;
        long numberOfIterations = (long) (Math.pow(10, numberOfQuestionMarks) - 1);

        for (long i = numberOfIterations; i >= 0; i--) {
            String nowIteration = leftZeroAdder("" + i, numberOfQuestionMarks);
            String testNumber = number;
            for (int j = 0; j < numberOfQuestionMarks; j++) {
                testNumber = testNumber.replaceFirst("\\?", nowIteration.charAt(j) + "");
            }
            if (isLuhn(testNumber)) {
                totalLuhnNumber++;
            }
        }
        return totalLuhnNumber;
    }

    private String leftZeroAdder(String number, int length) {
        while (number.length() < length) {
            number = "0" + number;
        }
        return number;
    }

    private boolean isLuhn(String number) {
        int total = 0;
        for (int i = number.length() - 1; i >= 0; i--) {
            int subTotal = Integer.parseInt(number.substring(i, i + 1));
            if (i % 2 == 0) {
                subTotal *= 2;
                if (subTotal > 9) {
                    subTotal = (subTotal % 10) + 1;
                }
            }
            total += subTotal;
        }
        return (total % 10 == 0);
    }
}