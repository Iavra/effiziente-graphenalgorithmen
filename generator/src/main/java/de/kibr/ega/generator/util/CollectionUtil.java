package de.kibr.ega.generator.util;

import org.javatuples.Pair;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

public class CollectionUtil {
    private CollectionUtil() {}

    public static <T> Stream<Pair<T, T>> getAllPermutations(Collection<T> collection) {
        return collection.stream().flatMap(a -> collection.stream().map(b -> new Pair<>(a, b)));
    }

    public static <T> Stream<Pair<T, T>> getAllPermutationsWithoutDuplicates(Collection<T> collection) {
        return getAllPermutations(collection).filter(pair -> !Objects.equals(pair.getValue0(), pair.getValue1()));
    }
}
