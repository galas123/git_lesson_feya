package ru.yandex.webtestslessons;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Переписать тестовые методы с использованием метода assertThat
 * Для начала поменяйте входные наборы данных таким образом, что бы тесты падали, обратите внимание на стектрейсы.
 * Проделайте ту же самую процедуру для методов с assertThat
 */
public class LibsPracticeTest {

    private LibsPractice libsPractice;

    @Before
    public void setUp() {
        libsPractice = new LibsPractice();
    }

    @Test
    public void arraySumMethodTest() {
        assertTrue(libsPractice.getArraySum(new int[]{1, 2, 3, 4}) == 10);
    }

    @Test
    public void arrayHasElementPositiveTest() {
        assertTrue(libsPractice.arrayContainsElement(new Integer[]{1, 2, 3, 4}, 2));
    }

    @Test
    public void arrayHasElementNegativeTest() {
        assertFalse(libsPractice.arrayContainsElement(new Integer[]{1, 2, 3, 4}, -1));
    }

    @Test
    public void getPositiveValuesTest() {
        assertTrue(libsPractice.getPositiveValues(new Integer[]{1, 0, -4, 15}).size() == 2);
    }

}
