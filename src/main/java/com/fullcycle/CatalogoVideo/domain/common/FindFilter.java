package com.fullcycle.CatalogoVideo.domain.common;

import lombok.Builder;
import lombok.Value;

@Builder(toBuilder = true)
@Value
public class FindFilter {
  public String terms;
}
