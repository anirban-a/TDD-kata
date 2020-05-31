package com.incubyte.app;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class StringCalculatorTest {
  private StringCalculator calculator;

  @Before
  public void setUp() {
    this.calculator = new StringCalculator();
  }

  @Test
  public void testAddGivenEmptyStringReturnZero() {
    String numbers = "";
    assertEquals(0, calculator.add(numbers));
  }

  @Test
  public void testAddGivenStringWithOneNumberShouldReturnSame() {
    String numbers = "1";
    int expected = 1;
    assertEquals(expected, calculator.add(numbers));
  }

  @Test
  public void testAddGivenStringWithTwoNumbersShouldReturnSumOfTwoNumbers() {
    String numbers = "1,2";
    int expected = 3;
    assertEquals(expected, calculator.add(numbers));
  }
}
