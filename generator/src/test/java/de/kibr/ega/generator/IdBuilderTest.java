package de.kibr.ega.generator;

import de.kibr.ega.generator.IdBuilder;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class IdBuilderTest {

    @Test
    public void sequence_should_start_with_A() {
        // given
        IdBuilder builder = new IdBuilder();

        // when
        String id = builder.get();

        // then
        assertThat(id).isEqualTo("A");
    }

    @Test
    public void sequential_calls_should_not_return_the_same_value() {
        // given
        IdBuilder builder = new IdBuilder();

        // when
        String id1 = builder.get();
        String id2 = builder.get();

        // then
        assertThat(id1).isNotEqualTo(id2);
    }

    @Test
    public void continue_after_reaching_end_of_the_alphabet() {
        // given
        IdBuilder builder = new IdBuilder();

        // when
        for (int i = 0; i < 26; i++) builder.get();
        String id = builder.get();

        // then
        assertThat(id).isEqualTo("AA");
    }
}