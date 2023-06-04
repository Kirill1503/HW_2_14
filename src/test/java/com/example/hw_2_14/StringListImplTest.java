package com.example.hw_2_14;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class StringListImplTest {
    StringList stringList = new StringListImpl();

    @ParameterizedTest
    @MethodSource("params")
    void add(String params) {
        stringList.add(params);
        System.out.print(Arrays.toString(stringList.toArray()));
        assertThat(stringList.isEmpty()).isFalse();
    }


    @Test
    void add2() {
        stringList.add(0, "test1");
        stringList.add(1, "test2");
        stringList.add(2, "test3");
        stringList.add(3, "test4");
        stringList.add(4, "test5");
        System.out.print(Arrays.toString(stringList.toArray()));
        assertThat(stringList.size()).isEqualTo(5);
        assertThat(stringList.get(2)).isEqualTo("test3");
        assertThat(stringList.isEmpty()).isFalse();
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stringList.add(-1, "ttt"));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stringList.add(1, null));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stringList.add(15, "null"));
    }

    @Test
    void set() {
        stringList.add(0, "test1");
        stringList.add(1, "test2");
        stringList.add(2, "test3");
        stringList.set(0, "test5");
        assertThat(stringList.get(0)).isEqualTo("test5");
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stringList.set(-1, "hhh"));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stringList.set(15, "hhh"));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stringList.set(3, null));
    }

    @Test
    void remove() {
        stringList.add("Иванов");
        System.out.print(Arrays.toString(stringList.toArray()));
        stringList.remove("Иванов");
        System.out.print(Arrays.toString(stringList.toArray()));
        stringList.isEmpty();
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stringList.remove(-1));
    }

    @Test
    void testRemove() {
        stringList.add(0, "test1");
        stringList.add(1, "test2");
        stringList.add(2, "test3");
        stringList.remove(0);
        assertThat(stringList.get(0)).isEqualTo("test2");
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stringList.remove(14));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stringList.remove(-1));
    }

    @Test
    void contains() {
        stringList.add(0, "test1");
        stringList.add(1, "test2");
        stringList.add(2, "test3");
        assertThat(stringList.contains("test2")).isTrue();
        assertThat(stringList.contains("tyru")).isFalse();
    }

    @ParameterizedTest
    @MethodSource("params")
    void indexOf(String a) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stringList.indexOf(null));
        stringList.add(a);
        System.out.print(Arrays.toString(stringList.toArray()));
        assertThat(stringList.indexOf(stringList.get(0))).isEqualTo(0);
        assertThat(stringList.indexOf("Невский")).isEqualTo(-1);
    }

    @Test
    void lastIndexOf() {
        stringList.add("Иванов");
        stringList.add("Александров");
        stringList.add("Игнатов");
        stringList.add("Игнатов");
        stringList.add("Игнатов");
        stringList.add("Игнатов");
        stringList.add("Игнатов");
        stringList.add("Игнатов");
        stringList.add("Игнатов");
        stringList.add("Игнатов");
        stringList.add("Игнатов");
        stringList.add("Игнатов");
        stringList.add("Игнатов");
        stringList.add("Игнатов");
        stringList.add("Петров");
        System.out.print(Arrays.toString(stringList.toArray()));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stringList.lastIndexOf(null));
        assertThat(stringList.lastIndexOf("Петров")).isEqualTo(14);
        assertThat(stringList.indexOf("Невский")).isEqualTo(-1);
    }

    @Test
    void get() {
    }

    @Test
    void testEquals() {
        StringList stringList1 = new StringListImpl();
        StringList stringList2 = new StringListImpl();
        StringList stringList3 = new StringListImpl();
        stringList1.add("Иван");
        stringList1.add("Федор");
        stringList1.add("Семен");
        stringList2.add("Иван");
        stringList2.add("Федор");
        stringList2.add("Семен");
        stringList3.add("Семен");
        assertThat(stringList1.equals(stringList2)).isEqualTo(true);
        assertThat(stringList1.equals(stringList3)).isEqualTo(false);
    }

    @Test
    void size() {
    }

    @Test
    void isEmpty() {
    }

    @Test
    public void clear() {
        stringList.add("test1");
        stringList.add("test2");
        stringList.add("test3");
        stringList.add("test4");
        assertThat(stringList.size()).isEqualTo(4);
        stringList.clear();
        assertThat(stringList.isEmpty()).isTrue();
        assertThat(stringList.toArray()).hasSize(0);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stringList.get(1));
    }

    @Test
    void toArray() {
    }

    public static Stream<Arguments> params() {
        return Stream.of(
                Arguments.of("Иванов"),
                Arguments.of("Александров"),
                Arguments.of("Игнатов")
        );
    }
}