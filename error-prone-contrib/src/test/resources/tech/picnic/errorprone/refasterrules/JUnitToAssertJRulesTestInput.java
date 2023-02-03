package tech.picnic.errorprone.refasterrules;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.collect.ImmutableSet;
import org.junit.jupiter.api.Assertions;
import tech.picnic.errorprone.refaster.test.RefasterRuleCollectionTestCase;

final class JUnitToAssertJRulesTest implements RefasterRuleCollectionTestCase {
  @Override
  public ImmutableSet<?> elidedTypesAndStaticImports() {
    return ImmutableSet.of(
        Assertions.class,
        assertDoesNotThrow(() -> null),
        assertInstanceOf(null, null),
        assertThrows(null, null),
        assertThrowsExactly(null, null),
        (Runnable) () -> assertFalse(true),
        (Runnable) () -> assertNotNull(null),
        (Runnable) () -> assertNotSame(null, null),
        (Runnable) () -> assertNull(null),
        (Runnable) () -> assertSame(null, null),
        (Runnable) () -> assertTrue(true));
  }

  void testThrowNewAssertionError() {
    Assertions.fail();
  }

  Object testFailWithMessage() {
    return Assertions.fail("foo");
  }

  Object testFailWithMessageAndThrowable() {
    return Assertions.fail("foo", new IllegalStateException());
  }

  void testFailWithThrowable() {
    Assertions.fail(new IllegalStateException());
  }

  void testAssertThatIsTrue() {
    assertTrue(true);
  }

  void testAssertThatWithFailMessageStringIsTrue() {
    assertTrue(true, "foo");
  }

  void testAssertThatWithFailMessageSupplierIsTrue() {
    assertTrue(true, () -> "foo");
  }

  void testAssertThatIsFalse() {
    assertFalse(true);
  }

  void testAssertThatWithFailMessageStringIsFalse() {
    assertFalse(true, "foo");
  }

  void testAssertThatWithFailMessageSupplierIsFalse() {
    assertFalse(true, () -> "foo");
  }

  void testAssertThatIsNull() {
    assertNull(new Object());
  }

  void testAssertThatWithFailMessageStringIsNull() {
    assertNull(new Object(), "foo");
  }

  void testAssertThatWithFailMessageSupplierIsNull() {
    assertNull(new Object(), () -> "foo");
  }

  void testAssertThatIsNotNull() {
    assertNotNull(new Object());
  }

  void testAssertThatWithFailMessageStringIsNotNull() {
    assertNotNull(new Object(), "foo");
  }

  void testAssertThatWithFailMessageSupplierIsNotNull() {
    assertNotNull(new Object(), () -> "foo");
  }

  void testAssertThatIsSameAs() {
    assertSame("foo", "bar");
  }

  void testAssertThatWithFailMessageStringIsSameAs() {
    assertSame("foo", "bar", "baz");
  }

  void testAssertThatWithFailMessageSupplierIsSameAs() {
    assertSame("foo", "bar", () -> "baz");
  }

  void testAssertThatIsNotSameAs() {
    assertNotSame("foo", "bar");
  }

  void testAssertThatWithFailMessageStringIsNotSameAs() {
    assertNotSame("foo", "bar", "baz");
  }

  void testAssertThatWithFailMessageSupplierIsNotSameAs() {
    assertNotSame("foo", "bar", () -> "baz");
  }

  void testAssertThatThrownByIsExactlyInstanceOf() {
    assertThrowsExactly(IllegalStateException.class, () -> {});
  }

  void testAssertThatThrownByWithFailMessageStringIsExactlyInstanceOf() {
    assertThrowsExactly(IllegalStateException.class, () -> {}, "foo");
  }

  void testAssertThatThrownByWithFailMessageSupplierIsExactlyInstanceOf() {
    assertThrowsExactly(IllegalStateException.class, () -> {}, () -> "foo");
  }

  void testAssertThatThrownByIsInstanceOf() {
    assertThrows(IllegalStateException.class, () -> {});
  }

  void testAssertThatThrownByWithFailMessageStringIsInstanceOf() {
    assertThrows(IllegalStateException.class, () -> {}, "foo");
  }

  void testAssertThatThrownByWithFailMessageSupplierIsInstanceOf() {
    assertThrows(IllegalStateException.class, () -> {}, () -> "foo");
  }

  void testAssertThatCodeDoesNotThrowAnyException() {
    assertDoesNotThrow(() -> {});
    assertDoesNotThrow(() -> toString());
  }

  void testAssertThatCodeWithFailMessageStringDoesNotThrowAnyException() {
    assertDoesNotThrow(() -> {}, "foo");
    assertDoesNotThrow(() -> toString(), "bar");
  }

  void testAssertThatCodeWithFailMessageSupplierDoesNotThrowAnyException() {
    assertDoesNotThrow(() -> {}, () -> "foo");
    assertDoesNotThrow(() -> toString(), () -> "bar");
  }

  void testAssertThatIsInstanceOf() {
    assertInstanceOf(Object.class, new Object());
  }

  void testAssertThatWithFailMessageStringIsInstanceOf() {
    assertInstanceOf(Object.class, new Object(), "foo");
  }

  void testAssertThatWithFailMessageSupplierIsInstanceOf() {
    assertInstanceOf(Object.class, new Object(), () -> "foo");
  }

  void testAssertThatIsEqualTo() {
    assertEquals((short) 0, (short) 0);
    assertEquals((byte) 0, (byte) 0);
    assertEquals(0, 0);
    assertEquals(0L, 0L);
    assertEquals(0.0F, 0.0F);
    assertEquals(0.0, 0.0);
    assertEquals((char) 0, (char) 0);
    assertEquals(new Object(), new Object());
    assertEquals("actual", "expected");
  }

  void testAssertThatIsEqualToWithMessage() {
    assertEquals((short) 0, (short) 0, "foo");
    assertEquals((byte) 0, (byte) 0, "bar");
    assertEquals(0, 0, "baz");
    assertEquals(0L, 0L, "qux");
    assertEquals(0.0F, 0.0F, "quux");
    assertEquals(0.0, 0.0, "quuz");
    assertEquals((char) 0, (char) 0, "corge");
    assertEquals(new Object(), new Object(), "grault");
    assertEquals("actual", "expected", "garply");
  }

  void testAssertThatIsEqualToWithMessageSupplier() {
    assertEquals((short) 0, (short) 0, () -> "foo");
    assertEquals((byte) 0, (byte) 0, () -> "bar");
    assertEquals(0, 0, () -> "baz");
    assertEquals(0L, 0L, () -> "qux");
    assertEquals(0.0F, 0.0F, () -> "quux");
    assertEquals(0.0, 0.0, () -> "quuz");
    assertEquals((char) 0, (char) 0, () -> "corge");
    assertEquals(new Object(), new Object(), () -> "grault");
    assertEquals("actual", "expected", () -> "garply");
  }

  void testAssertThatIsNotEqualTo() {
    assertNotEquals((short) 0, (short) 0);
    assertNotEquals((byte) 0, (byte) 0);
    assertNotEquals(0, 0);
    assertNotEquals(0L, 0L);
    assertNotEquals(0.0F, 0.0F);
    assertNotEquals(0.0, 0.0);
    assertNotEquals((char) 0, (char) 0);
    assertNotEquals(new Object(), new Object());
    assertNotEquals("actual", "expected");
  }

  void testAssertThatIsNotEqualToWithMessage() {
    assertNotEquals((short) 0, (short) 0, "foo");
    assertNotEquals((byte) 0, (byte) 0, "bar");
    assertNotEquals(0, 0, "baz");
    assertNotEquals(0L, 0L, "qux");
    assertNotEquals(0.0F, 0.0F, "quux");
    assertNotEquals(0.0, 0.0, "quuz");
    assertNotEquals((char) 0, (char) 0, "corge");
    assertNotEquals(new Object(), new Object(), "grault");
    assertNotEquals("actual", "expected", "garply");
  }

  void testAssertThatIsNotEqualToWithMessageSupplier() {
    assertNotEquals((short) 0, (short) 0, () -> "foo");
    assertNotEquals((byte) 0, (byte) 0, () -> "bar");
    assertNotEquals(0, 0, () -> "baz");
    assertNotEquals(0L, 0L, () -> "qux");
    assertNotEquals(0.0F, 0.0F, () -> "quux");
    assertNotEquals(0.0, 0.0, () ->"quuz");
    assertNotEquals((char) 0, (char) 0, () -> "corge");
    assertNotEquals(new Object(), new Object(), () -> "grault");
    assertNotEquals("actual", "expected", () ->"garply");
  }
}
