/*
 * Copyright (C) 2007 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.devtools.build.wireless.testing.java.injector.util;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * This is a not a final implementation DO NOT REUSE IT because it will be
 * eventually replaced.
 * 
 * <p>Trie implementation supporting CharSequences as prefixes.
 *
 * <p>Prefixes are sequences of characters, and the set of allowed characters is
 * specified as a range of sequential characters. By default, any seven-bit
 * character may appear in a prefix, and so the trie is a 128-ary tree.
 *
 * @deprecated
 */
public class PrefixTrie<T> implements PrefixMap<T> {
  /*
   * The set of allowed characters in prefixes is given by a range of
   * consecutive characters. rangeOffset denotes the beginning of the range,
   * and rangeSize gives the number of characters in the range, which is used as
   * the number of children of each node.
   */
  private final char rangeOffset;
  private final int rangeSize;

  private final Node<T> root;

  /** Constructs a trie for holding strings of seven-bit characters. */
  public PrefixTrie() {
    rangeOffset = '\0';
    rangeSize = 128;
    root = new Node<T>(rangeSize);
  }

  /**
   * Constructs a trie for holding strings of characters. The set of characters
   * allowed in prefixes is given by the range [rangeOffset, lastCharInRange],
   * inclusive.
   */
  public PrefixTrie(char firstCharInRange, char lastCharInRange) {
    this.rangeOffset = firstCharInRange;
    this.rangeSize = lastCharInRange - firstCharInRange + 1;

    if (rangeSize <= 0) {
      throw new IllegalArgumentException("Char range must include some chars");
    }

    root = new Node<T>(rangeSize);
  }

  /**
   * {@inheritDoc}
   *
   * @throws IllegalArgumentException if prefix contains a character outside the
   *     range of legal prefix characters.
   */
  public T put(CharSequence prefix, T value) {
    if (value == null) {
      throw new NullPointerException();
    }

    Node<T> current = root;
    for (int i = 0; i < prefix.length(); i++) {
      int nodeIndex = prefix.charAt(i) - rangeOffset;
      try {
        Node<T> next = current.next[nodeIndex];
        if (next == null) {
          next = current.next[nodeIndex] = new Node<T>(rangeSize);
        }
        current = next;
      } catch (ArrayIndexOutOfBoundsException e) {
        throw new IllegalArgumentException(
            "'" + prefix.charAt(i) + "' is not a legal prefix character.");
      }
    }
    T oldValue = current.value;
    current.value = value;
    return oldValue;
  }

  public T get(CharSequence s) {
    Node<T> deepestWithValue = root;
    Node<T> current = root;
    for (int i = 0; i < s.length(); i++) {
      int nodeIndex = s.charAt(i) - rangeOffset;
      if (nodeIndex < 0 || rangeSize <= nodeIndex) {
        return null;
      }
      current = current.next[nodeIndex];
      if (current == null) {
        break;
      }
      if (current.value != null) {
        deepestWithValue = current;
      }
    }
    return deepestWithValue.value;
  }

  /**
   * Returns a Map containing the same data as this structure.
   *
   * <p>This implementation constructs and populates an entirely new map rather
   * than providing a map view on the trie, so this is mostly useful for
   * debugging.
   *
   * @return a Map mapping each prefix to its corresponding value.
   */
  public Map<String, T> toMap() {
    Map<String, T> map = Maps.newLinkedHashMap();
    addEntries(root, new StringBuilder(), map);
    return map;
  }

  /**
   * Adds to the given map all entries at or below the given node.
   *
   * @param builder a StringBuilder containing the prefix for the given node.
   */
  private void addEntries(
      Node<T> node, StringBuilder builder, Map<String, T> map) {
    if (node.value != null) {
      map.put(builder.toString(), node.value);
    }

    for (int i = 0; i < node.next.length; i++) {
      Node<T> next = node.next[i];
      if (next != null) {
        builder.append((char) (i + rangeOffset));
        addEntries(next, builder, map);
        builder.deleteCharAt(builder.length() - 1);
      }
    }
  }

  private static class Node<T> {
    T value;
    final Node<T>[] next;

    @SuppressWarnings("unchecked")
    Node(int numChildren) {
      next = new Node[numChildren];
    }
  }
}


/**
 * Maps string prefixes to values. For example, if you {@code put("foo",1)},
 * {@code get("foobar")} returns {@code 1}. Prohibits null values.
 *
 * <p>Use instead of iterating over a series of string prefixes calling {@code
 * String.startsWith(prefix)}.
 */
interface PrefixMap<T> {
  /**
   * Maps prefix to value.
   *
   * @return the previous value stored for this prefix, or {@code null} if none
   * @throws IllegalArgumentException if prefix is an empty string.
   */
  T put(CharSequence prefix, T value);

  /**
   * Finds a prefix that matches {@code s} and returns the mapped value. If
   * multiple prefixes in the map match {@code s}, the longest match wins.
   *
   * @return value for prefix matching {@code s}, or {@code null} if none match
   */
  T get(CharSequence s);
}