package ru.yandex.webtestslessons;


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
}
