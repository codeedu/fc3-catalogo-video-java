package com.fullcycle.CatalogoVideo.domain.common;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Pagination<T> {
  public int currentPage;
  public int perPage;
  public long total;
  public List<T> items;

  public <R> Pagination<R> map(final Function<T, R> mapper) {
    return Pagination.<R>builder()
      .currentPage(currentPage)
      .perPage(perPage)
      .total(total)
      .items(
        this.items.stream()
          .map(mapper)
          .collect(Collectors.toList())
      )
      .build();
  }
}
