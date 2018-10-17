package de.kibr.ega.generator.util;

import org.javatuples.Pair;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class CollectionUtilTest {

    @Test
    public void do_not_combine_elements_with_themselves() {
        // given
        List<String> list = Collections.singletonList("A");

        // when
        Stream<Pair<String, String>> pairs = CollectionUtil.getAllPermutations(list);

        // then
        assertThat(pairs).isEmpty();
    }

    @Test
    public void collect_pairs_in_both_directions() {
        // given
        List<String> list = Arrays.asList("A", "B");

        // when
        Stream<Pair<String, String>> pairs = CollectionUtil.getAllPermutations(list);

        // then
        assertThat(pairs).containsExactly(new Pair<>("A", "B"), new Pair<>("B", "A")
        );
    }
}