package com.incubyte.app;

import java.util.Arrays;

public class StringCalculator {

  public int add(String numbers) {
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
    int sum = 0;
    int[] nums = mapStringToArrayOfNumbers(numbers);
    for (int number : nums)
      sum += number;
    return sum;
  }

  private int[] mapStringToArrayOfNumbers(String numbers) {
    String pattern = "\\n|,";
    return Arrays.stream(numbers.split(pattern)).mapToInt(Integer::parseInt).toArray();
  }
}
