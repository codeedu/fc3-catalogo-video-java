package com.fullcycle.CatalogoVideo.infrastructure.common;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;

public final class Specifications {
  
  private Specifications() {}

  public static <T> Specification<T> like(final String prop, final String value) {
    Assert.notNull(prop, "'props' cannot be null");
    Assert.notNull(value, "'value' cannot be null");
    return (root, query, cb) -> cb.like(cb.upper(root.get(prop)), "%" + value.toUpperCase() + "%");
  }
}