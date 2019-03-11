package tech.picnic.errorprone.refastertemplates;

import static com.google.common.collect.ImmutableList.toImmutableList;
import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Streams;
import com.google.errorprone.refaster.ImportPolicy;
import com.google.errorprone.refaster.Refaster;
import com.google.errorprone.refaster.annotation.AfterTemplate;
import com.google.errorprone.refaster.annotation.BeforeTemplate;
import com.google.errorprone.refaster.annotation.UseImportPolicy;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.stream.Stream;

/** Refaster templates related to expressions dealing with {@link ImmutableList}s. */
final class ImmutableListTemplates {
  private ImmutableListTemplates() {}

  /** Prefer {@link ImmutableList#builder()} over the associated constructor. */
  // XXX: This drops generic type information, sometimes leading to non-compilable code. Anything
  // we can do about that?
  static final class ImmutableListBuilder<T> {
    @BeforeTemplate
    ImmutableList.Builder<T> before() {
      return new ImmutableList.Builder<>();
    }

    @AfterTemplate
    ImmutableList.Builder<T> after() {
      return ImmutableList.builder();
    }
  }

  /** Prefer {@link ImmutableList#of()} over more contrived alternatives. */
  static final class EmptyImmutableList<T> {
    @BeforeTemplate
    ImmutableList<T> before() {
      return Refaster.anyOf(
          ImmutableList.<T>builder().build(), Stream.<T>empty().collect(toImmutableList()));
    }

    @AfterTemplate
    ImmutableList<T> after() {
      return ImmutableList.of();
    }
  }

  /**
   * Prefer {@link ImmutableList#copyOf(Iterable)} and variants over more contrived alternatives.
   */
  static final class IterableToImmutableList<T> {
    // XXX: Drop the inner `Refaster.anyOf` if/when we introduce a rule to choose between one and
    // the other.
    @BeforeTemplate
    ImmutableList<T> before(T[] iterable) {
      return Refaster.anyOf(
          ImmutableList.<T>builder().add(iterable).build(),
          Refaster.anyOf(Stream.of(iterable), Arrays.stream(iterable)).collect(toImmutableList()));
    }

    @BeforeTemplate
    ImmutableList<T> before(Iterator<T> iterable) {
      return Refaster.anyOf(
          ImmutableList.<T>builder().addAll(iterable).build(),
          Streams.stream(iterable).collect(toImmutableList()));
    }

    @BeforeTemplate
    ImmutableList<T> before(Iterable<T> iterable) {
      return Refaster.anyOf(
          ImmutableList.<T>builder().addAll(iterable).build(),
          Streams.stream(iterable).collect(toImmutableList()));
    }

    @BeforeTemplate
    ImmutableList<T> before(Collection<T> iterable) {
      return iterable.stream().collect(toImmutableList());
    }

    @AfterTemplate
    ImmutableList<T> after(Iterable<T> iterable) {
      return ImmutableList.copyOf(iterable);
    }
  }

  /** Prefer {@link ImmutableList#toImmutableList()} over the more verbose alternative. */
  // XXX: Once the code base has been sufficiently cleaned up, we might want to also rewrite
  // `Collectors.toList(`), with the caveat that it allows mutation (though this cannot be relied
  // upon) as well as nulls. Another option is to explicitly rewrite those variants to
  // `Collectors.toSet(ArrayList::new)`.
  static final class StreamToImmutableList<T> {
    @BeforeTemplate
    ImmutableList<T> before(Stream<T> stream) {
      return Refaster.anyOf(
          ImmutableList.copyOf(stream.iterator()),
          ImmutableList.copyOf(stream::iterator),
          stream.collect(collectingAndThen(toList(), ImmutableList::copyOf)));
    }

    @AfterTemplate
    @UseImportPolicy(ImportPolicy.STATIC_IMPORT_ALWAYS)
    ImmutableList<T> after(Stream<T> stream) {
      return stream.collect(toImmutableList());
    }
  }

  /** Don't call {@link ImmutableList#asList()}; it is a no-op. */
  static final class ImmutableListAsList<T> {
    @BeforeTemplate
    ImmutableList<T> before(ImmutableList<T> list) {
      return list.asList();
    }

    @AfterTemplate
    ImmutableList<T> after(ImmutableList<T> list) {
      return list;
    }
  }

  /** Prefer {@link ImmutableList#sortedCopyOf(Iterable)} over more contrived alternatives. */
  static final class ImmutableListSortedCopyOf<T extends Comparable<? super T>> {
    @BeforeTemplate
    ImmutableList<T> before(Iterable<T> iterable) {
      return Refaster.anyOf(
          ImmutableList.sortedCopyOf(naturalOrder(), iterable),
          Streams.stream(iterable).sorted().collect(toImmutableList()));
    }

    @BeforeTemplate
    ImmutableList<T> before(Collection<T> iterable) {
      return iterable.stream().sorted().collect(toImmutableList());
    }

    @AfterTemplate
    ImmutableList<T> after(Collection<T> iterable) {
      return ImmutableList.sortedCopyOf(iterable);
    }
  }

  /**
   * Prefer {@link ImmutableList#sortedCopyOf(Comparator, Iterable)} over more contrived
   * alternatives.
   */
  static final class ImmutableListSortedCopyOfWithCustomComparator<T> {
    @BeforeTemplate
    ImmutableList<T> before(Iterable<T> iterable, Comparator<T> cmp) {
      return Streams.stream(iterable).sorted(cmp).collect(toImmutableList());
    }

    @BeforeTemplate
    ImmutableList<T> before(Collection<T> iterable, Comparator<T> cmp) {
      return iterable.stream().sorted(cmp).collect(toImmutableList());
    }

    @AfterTemplate
    ImmutableList<T> after(Collection<T> iterable, Comparator<? super T> cmp) {
      return ImmutableList.sortedCopyOf(cmp, iterable);
    }
  }
}
