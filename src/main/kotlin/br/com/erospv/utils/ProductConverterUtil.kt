package br.com.erospv.utils

import br.com.erospv.domain.Product
import br.com.erospv.dto.ProductReq
import br.com.erospv.dto.ProductRes

fun Product.toProductRes(): ProductRes {
    return ProductRes(
        id = id!!,
        name = name,
        price = price,
        quantityInStock = quantityInStock
    )
}

fun ProductReq.toDomain(): Product {
    return Product(
        id = null,
        name = name,
        price = price,
        quantityInStock = quantityInStock
    )
}