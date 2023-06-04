package com.example.hw_2_14;

import com.example.hw_2_14.exceptions.ArrayIsFullException;
import com.example.hw_2_14.exceptions.ElementMissingException;
import com.example.hw_2_14.exceptions.ParameterIsNullException;
import com.example.hw_2_14.exceptions.ParameterIsOutOfRangeException;

import java.util.Arrays;

public class StringListImpl implements StringList {

    private static final int INITIAL_SIZE = 15;

    private final String[] data;

    private int capacity;


    public StringListImpl() {
        data = new String[INITIAL_SIZE];
        capacity = 0;
    }


    @Override
    public String add(String item) {
        return add(capacity, item);
    }

    @Override
    public String add(int index, String item) {
        if (index < 0 || index > capacity) {
            throw new IllegalArgumentException("Индекс должен быть в границах [0, capacity]");
        }
        if (item == null) {
            throw new IllegalArgumentException("Нельзя добавить null");
        }
        if (capacity == data.length) {
            throw new IllegalArgumentException("Массив заполенен");
        }
        if (index < capacity) {
            System.arraycopy(data, index, data, index + 1, capacity - index);
        }
        data[index] = item;
        capacity++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        if (index < 0 || index >= capacity) {
            throw new IllegalArgumentException("Индекс должен быть в границах [0, capacity]");
        }
        if (item == null) {
            throw new IllegalArgumentException("Нельзя установить null");
        }
        return data[index] = item;
    }

    @Override
    public String remove(String item) {
        int indexForRemoving = indexOf(item);
        if (indexForRemoving == -1) {
            throw new IllegalArgumentException("Такой элемент отсутствует");
        }
        return remove(indexForRemoving);
    }

    @Override
    public String remove(int index) {
        if (index < 0 || index > capacity) {
            throw new IllegalArgumentException("Индекс должен быть в границах [0, capacity]");
        }
        String removedItem = data[index];
        if (index + 1 < capacity) {
            System.arraycopy(data, index + 1, data, index, capacity - index - 1);
        }
        capacity--;
        data[capacity] = null;
        return removedItem;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        if (item == null) {
            throw new IllegalArgumentException("Нельзя использовать null");
        }
        for (int i = 0; i < capacity; i++) {
            if (data[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        if (item == null) {
            throw new IllegalArgumentException("Нельзя использовать null");
        }
        for (int i = data.length - 1; i > 0; i--) {
            if (data[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        if (index < 0 || index >= capacity) {
            throw new IllegalArgumentException("Индекс должен быть в границах [0, capacity]");
        }
        return data[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null || size() != otherList.size()) {
            return false;
        }
        for (int i = 0; i < capacity; i++) {
            if (!get(i).equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            data[i] = null;
        }
        capacity = 0;
    }

    @Override
    public String[] toArray() {
        String[] array = new String[capacity];
        System.arraycopy(data, 0, array, 0, capacity);
        return array;
    }
}
