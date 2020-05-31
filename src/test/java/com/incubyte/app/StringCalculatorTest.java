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
    // Arrange
    String numbers = "";

    // Assert
    assertEquals(0, calculator.add(numbers));
  }

  @Test
  public void testAddGivenStringWithOneNumberShouldReturnSame() {
    // Arrange
    String numbers = "1";
    int expected = 1;

    // Assert
    assertEquals(expected, calculator.add(numbers));
  }

  @Test
  public void testAddGivenStringWithOneNonIntegerShouldReturnZero() {
    // Arrange
    String numbers = "?";
    int expected = 0;

    // Assert
    assertEquals(expected, calculator.add(numbers));
  }

  @Test
  public void testAddGivenStringWithTwoNumbersShouldReturnSumOfTwoNumbers() {
    // Arrange
    String numbers = "1,2";
    int expected = 3;

    // Assert
    assertEquals(expected, calculator.add(numbers));
  }

  @Test
  public void testAddGivenStringWithManyNumbersShouldReturnSumOfTheNumbers() {
    // Arrange
    String numbers = "1,2,4,1";
    int expected = 8;

    // Assert
    assertEquals(expected, calculator.add(numbers));
  }

  @Test
  public void testAddGivenStringWithNewLinesBetweenNumbersShouldReturnSumOfTheNumbers() {
    // Arrange
    String numbers = "1\n2";
    int expected = 3;

    // Assert
    assertEquals(expected, calculator.add(numbers));
  }

  @Test
  public void testAddGivenStringWithNewLinesAndCommaBetweenNumbersShouldReturnSumOfTheNumbers() {
    // Arrange
    String numbers = "1\n2,3";
    int expected = 6;

    // Assert
    assertEquals(expected, calculator.add(numbers));
  }
}
