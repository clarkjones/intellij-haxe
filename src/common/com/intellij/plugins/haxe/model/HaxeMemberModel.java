/*
 * Copyright 2000-2013 JetBrains s.r.o.
 * Copyright 2014-2015 AS3Boyan
 * Copyright 2014-2014 Elias Ku
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
package com.intellij.plugins.haxe.model;

import com.intellij.plugins.haxe.lang.psi.*;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

abstract public class HaxeMemberModel {
  private PsiElement basePsi;

  public HaxeMemberModel(PsiElement basePsi) {
    this.basePsi = basePsi;
  }

  public PsiElement getPsi() { return basePsi; }

  abstract public HaxeClassModel getDeclaringClass();

  private HaxeModifiersModel _modifiers;
  @NotNull
  public HaxeModifiersModel getModifiers() {
    if (_modifiers == null) _modifiers = new HaxeModifiersModel(basePsi);
    return _modifiers;
  }

  public static HaxeMemberModel fromPsi(PsiElement element) {
    if (element instanceof HaxeIdentifier) return fromPsi(element.getParent());
    if (element instanceof HaxeComponentName) return fromPsi(element.getParent());
    if (element instanceof HaxeVarDeclarationPart) return fromPsi(element.getParent());
    if (element instanceof HaxeMethod) return ((HaxeMethod)element).getModel();
    if (element instanceof HaxeVarDeclaration) return ((HaxeVarDeclaration)element).getModel();
    return null;
  }
}
