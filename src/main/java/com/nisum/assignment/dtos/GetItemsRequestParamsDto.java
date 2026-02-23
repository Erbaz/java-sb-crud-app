package com.nisum.assignment.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetItemsRequestParamsDto {
    public Optional<String> name = Optional.empty();
    public Optional<Double> minPrice = Optional.empty();
    public Optional<Double> maxPrice = Optional.empty();
}
