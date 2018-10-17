package de.kibr.ega.core.util;

import org.javatuples.Pair;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class CollectionUtilTest {

    @Test
    public void combine_a_single_element_with_itself() {
        // given
        List<String> list = Collections.singletonList("A");

        // when
        Stream<Pair<String, String>> pairs = CollectionUtil.getAllPermutations(list);

        // then
        assertThat(pairs).containsExactly(new Pair<>("A", "A"));
    }

    @Test
    public void collect_pairs_in_both_directions() {
        // given
        List<String> list = Arrays.asList("A", "B");

        // when
        Stream<Pair<String, String>> pairs = CollectionUtil.getAllPermutations(list);

        // then
        assertThat(pairs).containsExactly(
                new Pair<>("A", "A"),
                new Pair<>("A", "B"),
                new Pair<>("B", "A"),
                new Pair<>("B", "B")
        );
    }

    @Test
    public void filter_out_self_pairs_by_calling_other_function() {
        // given
        List<String> list = Arrays.asList("A", "B");

        // when
        Stream<Pair<String, String>> pairs = CollectionUtil.getAllPermutationsWithoutDuplicates(list);

        // then
        assertThat(pairs).containsExactly(
                new Pair<>("A", "B"),
                new Pair<>("B", "A")
        );
    }
}