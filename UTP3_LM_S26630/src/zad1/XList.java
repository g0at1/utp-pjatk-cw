package zad1;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XList<T> extends ArrayList<T> {

  public XList(T... val) { super(Arrays.asList(val)); }

  public XList(Collection<T> val) { super(val); }

  public static <T> XList<T> of(T... val) { return new XList<>(val); }

  public static <T> XList<T> of(Collection<T> collection) {
    return new XList<>(collection);
  }

  public static XList<String> charsOf(String str) {
    XList<String> charList = new XList<>();

    for (char ch : str.toCharArray()) {
      charList.add(String.valueOf(ch));
    }

    return charList;
  }

  public static XList<String> tokensOf(String str, String separator) {
    return new XList<>(Arrays.asList(str.split(separator)));
  }

  public static XList<String> tokensOf(String str) {
    return tokensOf(str, " ");
  }

  public XList<T> union(Collection<T> collection) {
    XList<T> combinedList = new XList<>(this);
    combinedList.addAll(collection);

    return combinedList;
  }

  public XList<T> union(T... val) { return union(Arrays.asList(val)); }

  public XList<T> diff(Collection<T> collection) {
    XList<T> diffList = new XList<>(this);
    diffList.removeAll(collection);

    return diffList;
  }

  public XList<T> unique() {
    return this.stream().distinct().collect(
        Collectors.toCollection(XList::new));
  }

  private <U> XList<XList<U>> combine(List<XList<U>> xLists) {
    if (xLists.isEmpty()) {
      XList<XList<U>> list = new XList<>();
      list.add(new XList<>());

      return list;
    }

    XList<XList<U>> result = new XList<>();
    for (U u : xLists.getLast()) {
      XList<XList<U>> tmp = combine(xLists.subList(0, xLists.size() - 1));

      for (XList<U> list : tmp) {
        list.add(u);
      }

      result.addAll(tmp);
    }

    return result;
  }

  public <U> XList<XList<U>> combine() {
    XList<XList<U>> xLists = new XList<>();

    for (List<U> list : (List<List<U>>)this) {
      xLists.add(XList.of(list));
    }

    return this.combine(xLists);
  }

  public <U> XList<U> collect(Function<T, U> function) {
    XList<U> collectList = new XList<>();

    for (T t : this) {
      collectList.add(function.apply(t));
    }

    return collectList;
  }

  public String join(String separator) {
    return this.stream()
        .map(Object::toString)
        .collect(Collectors.joining(separator));
  }

  public String join() { return join(""); }

  public void forEachWithIndex(BiConsumer<T, Integer> biConsumer) {
    for (int i = 0; i < this.size(); i++) {
      biConsumer.accept(this.get(i), i);
    }
  }
}
