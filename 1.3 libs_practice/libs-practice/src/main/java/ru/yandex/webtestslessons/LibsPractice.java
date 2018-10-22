package ru.yandex.webtestslessons;


import java.util.ArrayList;
import java.util.List;

public class LibsPractice {

    //Переписать метод используя Stream API
    public int getArraySum(int[] numbers) {
        int sum = 0;
        for(int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    //Переписать метод используя org.apache.commons.lang3.ArrayUtils
    public <T> boolean arrayContainsElement(T[] array, T element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    //Переписать метод используя Stream API
    public List<String> getPositiveValues(Integer[] numbers) {
        List<String> listOfPositiveValues = new ArrayList();
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > 0) {
                listOfPositiveValues.add(numbers[i].toString());
            }
        }
        return listOfPositiveValues;
    }
}
