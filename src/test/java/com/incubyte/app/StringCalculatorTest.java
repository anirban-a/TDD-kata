package com.incubyte.app;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StringCalculatorTest {
  private StringCalculator calculator;

  @Rule
  public ExpectedException exceptionRule = ExpectedException.none();

  @Before
  public void setUp() {
    this.calculator = new StringCalculator();
  }

  @Test
  public void testAddGivenEmptyStringReturnZero() throws NegativeNumberException {
    // Arrange
    String numbers = "";

    // Assert
    assertEquals(0, calculator.add(numbers));
  }

  @Test
  public void testAddGivenStringWithOneNumberShouldReturnSame() throws NegativeNumberException {
    // Arrange
    String numbers = "1";
    int expected = 1;

    // Assert
    assertEquals(expected, calculator.add(numbers));
  }

  @Test
  public void testAddGivenStringWithOneNonIntegerShouldReturnZero() throws NegativeNumberException {
    // Arrange
    String numbers = "?";
    int expected = 0;

    // Assert
    assertEquals(expected, calculator.add(numbers));
  }

  @Test
  public void testAddGivenStringWithTwoNumbersShouldReturnSumOfTwoNumbers()
      throws NegativeNumberException {
    // Arrange
    String numbers = "1,2";
    int expected = 3;

    // Assert
    assertEquals(expected, calculator.add(numbers));
  }

  @Test
  public void testAddGivenStringWithManyNumbersShouldReturnSumOfTheNumbers()
      throws NegativeNumberException {
    // Arrange
    String numbers = "1,2,4,1";
    int expected = 8;

    // Assert
    assertEquals(expected, calculator.add(numbers));
  }

  @Test
  public void testAddGivenStringWithNewLinesBetweenNumbersShouldReturnSumOfTheNumbers()
      throws NegativeNumberException {
    // Arrange
    String numbers = "1\n2";
    int expected = 3;

    // Assert
    assertEquals(expected, calculator.add(numbers));
  }

  @Test
  public void testAddGivenStringWithNewLinesAndCommaBetweenNumbersShouldReturnSumOfTheNumbers()
      throws NegativeNumberException {
    // Arrange
    String numbers = "1\n2,3";
    int expected = 6;

    // Assert
    assertEquals(expected, calculator.add(numbers));
  }

  @Test
  public void testAddGivenStringWithDifferentDelimitersShouldReturnSumOfTheNumbers()
      throws NegativeNumberException {
    // Arrange
    String numbers = "//;\n1;2";
    int expected = 3;

    // Assert
    assertEquals(expected, calculator.add(numbers));
  }

  @Test
  public void testAddThrowsExceptionWithNegativeInteger() throws NegativeNumberException {
    // Arrange
    String numbers = "-1";
    String errorMessage = "negatives not allowed";
    exceptionRule.expect(NegativeNumberException.class);
    exceptionRule.expectMessage(errorMessage);

    // Act and Assert
    calculator.add(numbers);
  }

  @Test
  public void testAddThrowsExceptionWithMultipleNegativeIntegers() throws NegativeNumberException {
    // Arrange
    String numbers = "-1,3,-2";
    String errorMessage = "negatives not allowed: [-1,-2]";
    exceptionRule.expect(NegativeNumberException.class);
    exceptionRule.expectMessage(errorMessage);

    // Act and Assert
    calculator.add(numbers);
  }

  @Test
  public void testGetCalledCount() throws NegativeNumberException {
    // Arrange
    int expectedCalledCount = 1;

    // Act
    calculator.add("");

    // Assert
    assertEquals(expectedCalledCount, calculator.getCalledCount());
  }

  @Test
  public void testNumberBiggerThanThousandIsIgnored() throws NegativeNumberException {
    // Arrange
    String numbers = "1,1001";
    String expected = "1";

    // Assert
    assertEquals(expected, calculator.add(numbers));
  }
}
