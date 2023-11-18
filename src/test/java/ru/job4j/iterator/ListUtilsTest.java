package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIf() {
        ListUtils.removeIf(input, x -> x == 3);
        assertThat(input).hasSize(1).doesNotContain(3).containsSequence(1);
    }

    @Test
    void whenRemoveIfMoreThan3() {
        input = new ArrayList<>(Arrays.asList(1, 3, 5, 6, 8, 9, 10));
        ListUtils.removeIf(input, x -> x > 3);
        assertThat(input).hasSize(2).containsSequence(1, 3).doesNotContain(5, 6, 8, 9, 10);
    }

    @Test
    void whenRemoveIfDoesNotContains() {
        ListUtils.removeIf(input, x -> x == 10);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }

    @Test
    void whenReplaceIf3on322() {
        ListUtils.replaceIf(input, x -> x == 3, 322);
        assertThat(input).hasSize(2).containsSequence(1, 322).doesNotContain(3);
    }

    @Test
    void whenReplaceIfMoreThan3on322() {
        input = new ArrayList<>(Arrays.asList(1, 3, 5, 6, 8, 9, 10));
        ListUtils.replaceIf(input, x -> x > 3, 322);
        assertThat(input).hasSize(7).containsSequence(1, 3, 322).doesNotContain(5, 6, 8, 9, 10);
    }

    @Test
    void whenReplaceIfNotContains() {
        ListUtils.removeIf(input, x -> x == 10);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }

    @Test
    void whenRemoveAllExcept1() {
        input = new ArrayList<>(Arrays.asList(1, 3, 5, 6, 8, 9, 10));
        List<Integer> toDelete = new ArrayList<>(Arrays.asList(3, 5, 6, 8, 9, 10));
        ListUtils.removeAll(input, toDelete);
        assertThat(input).hasSize(1).containsSequence(1).doesNotContain(3, 5, 6, 8, 9, 10);
    }

    @Test
    void whenRemoveAll() {
        input = new ArrayList<>(Arrays.asList(1, 3, 5, 6, 8, 9, 10));
        List<Integer> toDelete = new ArrayList<>(Arrays.asList(1, 3, 5, 6, 8, 9, 10));
        ListUtils.removeAll(input, toDelete);
        assertThat(input).hasSize(0).doesNotContain(1, 3, 5, 6, 8, 9, 10);
    }

    @Test
    void whenRemoveAll1And10() {
        input = new ArrayList<>(Arrays.asList(1, 3, 5, 6, 8, 9, 10));
        List<Integer> toDelete = new ArrayList<>(Arrays.asList(1, 10));
        ListUtils.removeAll(input, toDelete);
        assertThat(input).hasSize(5).containsSequence(3, 5, 6, 8, 9).doesNotContain(1, 10);
    }
}