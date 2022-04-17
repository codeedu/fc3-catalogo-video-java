package com.fullcycle.CatalogoVideo;

import com.fullcycle.CatalogoVideo.domain.category.Category;

public final class Composer {
  
  private Composer() {}

  public static Category moviesCategory() {
    return Category.newCategory(
      "Movies",
      "The nicests movies ever",
      true            
    );
  }

  public static Category seriesCategory() {
    return Category.newCategory(
      "Series",
      "The funniest series online",
      true
    );
  }
}
