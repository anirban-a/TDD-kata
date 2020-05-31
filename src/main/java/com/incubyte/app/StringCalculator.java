package com.incubyte.app;

import java.util.Arrays;

public class StringCalculator {

  private static int calledCount = 0;

  public int add(String numbers) throws NegativeNumberException {
    calledCount++;
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
    // String delim = getDelimiter(numbers);
    String delim;
    if (multiLengthDelimiterExists(numbers))
      delim = getMultiLengthDelimiter(numbers);
    else
      delim = getDelimiter(numbers);
    numbers = checkAndSanitizeDelimiterDefinition(numbers);
    int[] nums = mapStringToArrayOfNumbers(numbers, delim);
    for (int number : nums)
      sum += number;
    return sum;
  }

  private int[] mapStringToArrayOfNumbers(String numbers, String delim)
      throws NegativeNumberException {
    String pattern = ",|\n";
    if (delim != null && !delim.isEmpty())
      pattern += "|" + delim;
    int[] negatives = Arrays.stream(numbers.split(pattern)).mapToInt(Integer::parseInt)
        .filter((e) -> e < 0).toArray();
    if (negatives.length > 0) {
      String errorMessage =
          "negatives not allowed: " + integerArrayToStringRepresentation(negatives);
      throw new NegativeNumberException(errorMessage);
    }
    return Arrays.stream(numbers.split(pattern)).mapToInt(Integer::parseInt).filter((e) -> e >= 0)
        .map(e -> {
          return e >= 1000 ? 0 : e;
        }).toArray();
  }

  private String checkAndSanitizeDelimiterDefinition(String numbers) {
    if (numbers.substring(0, 3).matches("//(\\D){1,1}")) {
      return numbers.split("\n")[1];
    }
    return numbers;
  }

  private String getDelimiter(String numbers) {
    String delim = "";
    if (numbers.substring(0, 3).matches("//(\\D){1,1}")) {
      delim = numbers.split("\n")[0].split("//")[1];
    }
    return delim;
  }

  private String getMultiLengthDelimiter(String numbers) {
    String delim = "";
    if (numbers.substring(0, 3).equals("//[")) {
      String expr = numbers.split("\n")[0].split("//")[1];
      delim = expr.substring(1, expr.length() - 1);
    }
    return delim;
  }

  private boolean multiLengthDelimiterExists(String numbers) {
    return numbers.substring(0, 3).equals("//[");
  }

  private String integerArrayToStringRepresentation(int[] arr) {
    StringBuilder build = new StringBuilder();
    build.append('[');
    for (int i = 0; i < arr.length; i++) {
      build.append(arr[i]);
      if (i < arr.length - 1)
        build.append(',');
    }
    build.append(']');

    return build.toString();
  }

  public int getCalledCount() {
    return calledCount;
  }
}
