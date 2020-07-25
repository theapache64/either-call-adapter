# either-call-adapter

A Retrofit call adapter you can use to return `Either<ApiError,T>` from your API interface.

```kotlin
interface Api {
    @GET("products")
    suspend fun getProducts(): Either<ApiError, ProductsResponse>
}
```