package com.incubyte.app;

public class StringCalculator {

  public int add(String numbers) {
    if(numbers.isEmpty())
      return 0;
    if(numbers.length()==1)
      return Integer.parseInt(numbers);
    return 0;
  }
}
