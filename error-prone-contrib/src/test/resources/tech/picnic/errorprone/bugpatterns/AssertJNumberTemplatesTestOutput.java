package tech.picnic.errorprone.bugpatterns;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.collect.ImmutableSet;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.assertj.core.api.NumberAssert;

final class AssertJNumberTemplatesTest implements RefasterTemplateTestCase {
  ImmutableSet<NumberAssert<?, ?>> testAbstractIntegerAssertIsPositive() {
    return ImmutableSet.of(
        assertThat((byte) 0).isPositive(),
        assertThat((byte) 0).isPositive(),
        assertThat((short) 0).isPositive(),
        assertThat((short) 0).isPositive(),
        assertThat(0).isPositive(),
        assertThat(0).isPositive(),
        assertThat(0L).isPositive(),
        assertThat(0L).isPositive(),
        assertThat(0.0F).isPositive(),
        assertThat(0.0).isPositive(),
        assertThat(BigInteger.ZERO).isPositive(),
        assertThat(BigInteger.ZERO).isPositive(),
        assertThat(BigDecimal.ZERO).isPositive());
  }

  ImmutableSet<NumberAssert<?, ?>> testAbstractIntegerAssertIsNotPositive() {
    return ImmutableSet.of(
        assertThat((byte) 0).isNotPositive(),
        assertThat((byte) 0).isNotPositive(),
        assertThat((short) 0).isNotPositive(),
        assertThat((short) 0).isNotPositive(),
        assertThat(0).isNotPositive(),
        assertThat(0).isNotPositive(),
        assertThat(0L).isNotPositive(),
        assertThat(0L).isNotPositive(),
        assertThat(0.0F).isNotPositive(),
        assertThat(0.0).isNotPositive(),
        assertThat(BigInteger.ZERO).isNotPositive(),
        assertThat(BigInteger.ZERO).isNotPositive(),
        assertThat(BigDecimal.ZERO).isNotPositive());
  }

  ImmutableSet<NumberAssert<?, ?>> testAbstractIntegerAssertIsNegative() {
    return ImmutableSet.of(
        assertThat((byte) 0).isNegative(),
        assertThat((byte) 0).isNegative(),
        assertThat((short) 0).isNegative(),
        assertThat((short) 0).isNegative(),
        assertThat(0).isNegative(),
        assertThat(0).isNegative(),
        assertThat(0L).isNegative(),
        assertThat(0L).isNegative(),
        assertThat(0.0F).isNegative(),
        assertThat(0.0).isNegative(),
        assertThat(BigInteger.ZERO).isNegative(),
        assertThat(BigInteger.ZERO).isNegative(),
        assertThat(BigDecimal.ZERO).isNegative());
  }

  ImmutableSet<NumberAssert<?, ?>> testAbstractIntegerAssertIsNotNegative() {
    return ImmutableSet.of(
        assertThat((byte) 0).isNotNegative(),
        assertThat((byte) 0).isNotNegative(),
        assertThat((short) 0).isNotNegative(),
        assertThat((short) 0).isNotNegative(),
        assertThat(0).isNotNegative(),
        assertThat(0).isNotNegative(),
        assertThat(0L).isNotNegative(),
        assertThat(0L).isNotNegative(),
        assertThat(0.0F).isNotNegative(),
        assertThat(0.0).isNotNegative(),
        assertThat(BigInteger.ZERO).isNotNegative(),
        assertThat(BigInteger.ZERO).isNotNegative(),
        assertThat(BigDecimal.ZERO).isNotNegative());
  }
}
