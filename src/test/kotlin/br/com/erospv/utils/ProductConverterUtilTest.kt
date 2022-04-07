package br.com.erospv.utils

import br.com.erospv.domain.Product
import br.com.erospv.dto.ProductReq
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ProductConverterUtilTest {

    @Test
    fun `When toProductRes is called, should return a ProductRes with all data`() {
         val product = Product(
             id = 1,
             name = "product name",
             price = 10.50,
             quantityInStock = 10
         )
        val productRes = product.toProductRes()

        assertEquals(product.id, productRes.id)
        assertEquals(product.name, productRes.name)
        assertEquals(product.price, productRes.price)
        assertEquals(product.quantityInStock, productRes.quantityInStock)
    }

    @Test
    fun `When toDomain is called, should return a Product with all data`() {
        val productReq = ProductReq(
            name = "product name",
            price = 10.50,
            quantityInStock = 10
        )
        val product= productReq.toDomain()

        assertEquals(null, product.id)
        assertEquals(productReq.name, product.name)
        assertEquals(productReq.price, product.price)
        assertEquals(productReq.quantityInStock, product.quantityInStock)
    }
}