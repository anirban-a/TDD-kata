package com.incubyte.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
}
