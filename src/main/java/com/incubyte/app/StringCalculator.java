package com.incubyte.app;

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
    return 0;
  }
}
