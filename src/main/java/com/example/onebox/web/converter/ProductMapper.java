package com.example.onebox.web.converter;


import com.example.onebox.domain.Product;
import com.example.onebox.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper
{
	ProductMapper INSTANCE =  Mappers.getMapper(ProductMapper.class);

	ProductDto toDto(Product product);
	Product toEntity(ProductDto productDto);
	List<ProductDto> toDtoList(List<Product> products);

	List<Product> toEntityList(List<ProductDto> productDtos);
}
