package com.example.foodies.data.models

fun mapProductDtoToProduct(productDto: ProductDto) = Product(
    id = productDto.id,
    categoryId = productDto.categoryId,
    name = productDto.name,
    description = productDto.description,
    image = productDto.image,
    priceCurrent = productDto.priceCurrent,
    priceOld = productDto.priceOld,
    measure = productDto.measure,
    measureUnit = productDto.measureUnit,
    energyPer100grams = productDto.energyPer100grams,
    proteinsPer100grams = productDto.proteinsPer100grams,
    fatsPer100Grams = productDto.fatsPer100Grams,
    carbohydratesPer100Grams = productDto.carbohydratesPer100Grams,
    tagIds = productDto.tagIds,
    count = 0
)