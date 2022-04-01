package br.com.erospv.resources

import br.com.erospv.ProductServiceRequest
import br.com.erospv.ProductServiceResponse
import br.com.erospv.ProductsServiceGrpc
import br.com.erospv.dto.ProductReq
import br.com.erospv.services.ProductService
import io.grpc.stub.StreamObserver
import io.micronaut.grpc.annotation.GrpcService

@GrpcService
class ProductResources(private val productService: ProductService) : ProductsServiceGrpc.ProductsServiceImplBase() {
    override fun create(request: ProductServiceRequest?, responseObserver: StreamObserver<ProductServiceResponse>?) {
        val productReq = ProductReq(
            name = request!!.name,
            price = request.price,
            quantityInStock = request.quantityInStock
        )

        val productRes = productService.create(productReq)
        val productResponse = ProductServiceResponse.newBuilder()
            .setId(productRes.id)
            .setName(productRes.name)
            .setPrice(productRes.price)
            .setQuantityInStock(productRes.quantityInStock)
            .build()

        responseObserver?.onNext(productResponse)
        responseObserver?.onCompleted()

    }
}