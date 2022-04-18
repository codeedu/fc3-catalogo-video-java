package com.fullcycle.CatalogoVideo.api.configuration;

import com.fullcycle.CatalogoVideo.application.category.create.CreateCategoryUseCase;
import com.fullcycle.CatalogoVideo.application.category.create.ICreateCategoryUseCase;
import com.fullcycle.CatalogoVideo.application.category.delete.IDeleteCategoryUseCase;
import com.fullcycle.CatalogoVideo.application.category.findall.FindAllCategoryUseCase;
import com.fullcycle.CatalogoVideo.application.category.findall.IFindAllCategoryUseCase;
import com.fullcycle.CatalogoVideo.application.category.get.FindByIdCategoryUseCase;
import com.fullcycle.CatalogoVideo.application.category.get.IFindByIdCategoryUseCase;
import com.fullcycle.CatalogoVideo.application.category.update.IUpdateCategoryUseCase;
import com.fullcycle.CatalogoVideo.application.category.update.UpdateCategoryUseCase;
import com.fullcycle.CatalogoVideo.application.category.delete.DeleteCategoryUseCase;
import com.fullcycle.CatalogoVideo.domain.category.gateways.ICategoryGateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class UseCaseConfig {
  
  private final ICategoryGateway gateway;

  @Bean
  public ICreateCategoryUseCase createCategoryUseCase() {
    return new CreateCategoryUseCase(gateway);
  }

  @Bean
  public IDeleteCategoryUseCase removeCategoryUseCase() {
    return new DeleteCategoryUseCase(gateway);
  }

  @Bean
  public IFindAllCategoryUseCase findAllCategoryUseCase() {
    return new FindAllCategoryUseCase(gateway);
  }

  @Bean
  public IFindByIdCategoryUseCase findByIdCategoryUseCase() {
    return new FindByIdCategoryUseCase(gateway);
  }

  @Bean
  public IUpdateCategoryUseCase updateCategoryUseCase() {
    return new UpdateCategoryUseCase(gateway);
  }
}
