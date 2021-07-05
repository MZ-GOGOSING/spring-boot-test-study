package me.gogosing.support.query;

import java.util.Optional;
import java.util.function.Consumer;

public class QueryDslHelper {

  private QueryDslHelper() {
    throw new UnsupportedOperationException();
  }

  public static class WhereCondition<V> {

    V value;

    public WhereCondition(V value) {
      this.value = value;
    }

    public void then(Consumer<V> consumer) {
      Optional.ofNullable(value).ifPresent(consumer);
    }
  }

  public static <V> WhereCondition<V> optionalWhen(V value) {
    return new WhereCondition<>(value);
  }
}
