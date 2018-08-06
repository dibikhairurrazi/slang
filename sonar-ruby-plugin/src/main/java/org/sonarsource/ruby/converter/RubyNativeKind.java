/*
 * SonarSource SLang
 * Copyright (C) 2009-2018 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonarsource.ruby.converter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.sonarsource.slang.api.NativeKind;

public class RubyNativeKind implements NativeKind {

  private final Class<?> rubyObjectClass;
  private final List<Object> differentiators;

  public RubyNativeKind(Object element, Object... differentiatorObjs) {
    this(element.getClass(), differentiatorObjs);
  }

  public RubyNativeKind(Class<?> rubyObjectClass, Object... differentiatorObjs) {
    this.rubyObjectClass = rubyObjectClass;
    this.differentiators = Arrays.asList(differentiatorObjs);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RubyNativeKind that = (RubyNativeKind) o;
    return Objects.equals(rubyObjectClass, that.rubyObjectClass) &&
      Objects.equals(differentiators, that.differentiators);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rubyObjectClass, differentiators);
  }

  @Override
  public String toString() {
    if (differentiators.isEmpty()) {
      return rubyObjectClass.getSimpleName();
    } else {
      return rubyObjectClass.getSimpleName()
        + differentiators.stream().map(Object::toString).collect(Collectors.joining(", ", "[", "]"));
    }
  }

}
