package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray(
                "first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList(
                "first", "second", "three", "four", "five");
        assertThat(list).hasSize(5)
                .contains("three")
                .contains("three", Index.atIndex(2))
                .containsAnyOf("322", "3", "three")
                .doesNotContain("first", Index.atIndex(1))
                .allSatisfy(e -> {
                    assertThat(e)
                            .isNotEmpty()
                            .isNotNull();
                })
                .anyMatch(e -> e.equals("three"))
                .element(2).isEqualTo("three");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet(
                "first", "second", "three", "four", "five");
        assertThat(set).hasSize(5)
                .contains("three")
                .containsAnyOf("322", "3", "three")
                .allSatisfy(e -> {
                    assertThat(e)
                            .isNotEmpty()
                            .isNotNull();
                })
                .anyMatch(e -> e.equals("three"));
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap(
                "one", "two", "three", "four", "five");
        assertThat(map).hasSize(5)
                .containsKeys("one", "two", "three", "four", "five")
                .doesNotContainKey("322")
                .containsValues(0, 2, 3, 4, 1)
                .doesNotContainValue(322)
                .containsEntry("three", 2);

    }

}