package br.com.erospv.utils

import br.com.erospv.ProductServiceRequest

class ValidationUtil {
    companion object {
        fun validatePayload(payload: ProductServiceRequest?): ProductServiceRequest {
            payload?.let {
                if (it.name.isNullOrBlank())
                    throw IllegalArgumentException("Nome não pode ser nulo ou vazio")

                if (it.price.isNaN() || it.price < 0)
                    throw IllegalArgumentException("Preço precisa ser um valor válido")

                return it
            }
            throw IllegalArgumentException()
        }
    }
}