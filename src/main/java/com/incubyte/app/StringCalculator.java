package com.incubyte.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    String[] delimiters = getDelimiters(numbers);
    numbers = checkAndSanitizeDelimiterDefinition(numbers);
    int[] nums = mapStringToArrayOfNumbers(numbers, delimiters);
    for (int number : nums)
      sum += number;
    return sum;
  }

  private int[] mapStringToArrayOfNumbers(String numbers, String[] delimiters)
      throws NegativeNumberException {
    String pattern = ",|\n";
    for (int i = 0; i < delimiters.length; i++) {
      if (!delimiters[i].isEmpty())
        pattern += "|" + delimiters[i];
    }
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

  private String[] getDelimiters(String numbers) {
    List<String> list = new ArrayList<>();
    if (!multiLengthDelimiterExists(numbers))
      return new String[] {getDelimiter(numbers)};
    else {
      String expr = numbers.split("\n")[0];
      StringBuilder builder = null;
      for (int i = 0; i < expr.length(); i++) {
        if (expr.charAt(i) == '[') {
          builder = new StringBuilder();
        } else if (expr.charAt(i) == ']') {
          list.add(builder.toString());
          builder = null;
        } else if (builder != null)
          builder.append(expr.charAt(i));
      }
    }
    return list.toArray(new String[] {});
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
