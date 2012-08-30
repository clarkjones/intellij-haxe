package com.intellij.plugins.haxe.lang.psi.impl;

import com.intellij.openapi.util.Key;
import com.intellij.plugins.haxe.lang.psi.HaxeComponentName;
import com.intellij.plugins.haxe.lang.psi.HaxeNamedComponent;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

/**
 * @author: Fedor.Korotkov
 */
public class ComponentNameScopeProcessor implements PsiScopeProcessor {
  private final Set<HaxeComponentName> result;

  public ComponentNameScopeProcessor(Set<HaxeComponentName> result) {
    this.result = result;
  }

  @Override
  public boolean execute(@NotNull PsiElement element, ResolveState state) {
    if (element instanceof HaxeNamedComponent) {
      final HaxeNamedComponent haxeNamedComponent = (HaxeNamedComponent)element;
      if (haxeNamedComponent.getComponentName() != null) {
        result.add(haxeNamedComponent.getComponentName());
      }
    }
    return true;
  }

  @Override
  public <T> T getHint(@NotNull Key<T> hintKey) {
    return null;
  }

  @Override
  public void handleEvent(Event event, @Nullable Object associated) {
  }
}