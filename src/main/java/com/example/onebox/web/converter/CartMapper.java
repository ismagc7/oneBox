package com.example.onebox.web.converter;

import com.example.onebox.domain.Cart;
import com.example.onebox.dto.CartDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartMapper
{
	CartMapper INSTANCE =  Mappers.getMapper(CartMapper.class);

	CartDto toCartDTO(Cart cart);
	Cart toCart(CartDto cartDTO);
	List<CartDto> toCartDTOs(List<Cart> carts);
}
