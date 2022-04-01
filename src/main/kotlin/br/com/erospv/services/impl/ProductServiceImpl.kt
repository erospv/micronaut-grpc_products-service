package br.com.erospv.services.impl

import br.com.erospv.dto.ProductReq
import br.com.erospv.dto.ProductRes
import br.com.erospv.repository.ProductRepository
import br.com.erospv.services.ProductService
import br.com.erospv.utils.toDomain
import br.com.erospv.utils.toProductRes
import jakarta.inject.Singleton

@Singleton
class ProductServiceImpl(private val productRepository: ProductRepository) : ProductService {
    override fun create(req: ProductReq): ProductRes {
        val createdProduct = productRepository.save(req.toDomain())
        return createdProduct.toProductRes()
    }
}