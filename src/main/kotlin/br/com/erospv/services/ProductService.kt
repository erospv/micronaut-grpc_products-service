package br.com.erospv.services

import br.com.erospv.dto.ProductReq
import br.com.erospv.dto.ProductRes

interface ProductService {
    fun create(req: ProductReq): ProductRes
}