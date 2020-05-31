package com.incubyte.app;

import java.util.Arrays;

public class StringCalculator {

  public int add(String numbers) throws NegativeNumberException {
    if (numbers.isEmpty())
      return 0;
    if (numbers.length() == 1) {
      int result = 0;
      try {
        result = Integer.parseInt(numbers);
      } catch (Exception e) {
      }
      return result;
    }

    if (numbers.length() == 2 && numbers.charAt(0) == '-') {
      throw new NegativeNumberException("negatives not allowed");
    }
    int sum = 0;
    numbers = checkAndSanitizeDelimeterDefinition(numbers);
    int[] nums = mapStringToArrayOfNumbers(numbers);
    for (int number : nums)
      sum += number;
    return sum;
  }

  private int[] mapStringToArrayOfNumbers(String numbers) {
    String pattern = "\\D";
    return Arrays.stream(numbers.split(pattern)).mapToInt(Integer::parseInt).toArray();
  }

  private String checkAndSanitizeDelimeterDefinition(String numbers) {
    if (numbers.substring(0, 3).matches("//(\\D){1,1}")) {
      return numbers.split("\n")[1];
    }
    return numbers;
  }
}
