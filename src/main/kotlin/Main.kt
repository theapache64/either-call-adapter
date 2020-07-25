import retrofit2.http.GET
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


/**
 * Created by theapache64 : Jul 25 Sat,2020 @ 11:24
 */
@JsonClass(generateAdapter = true)
data class ProductsResponse(
    @Json(name = "products")
    val products: List<Product>
) {
    @JsonClass(generateAdapter = true)
    data class Product(
        @Json(name = "id")
        val id: Int, // 1
        @Json(name = "imageUrl")
        val imageUrl: String, // https://picsum.photos/id/11/600/300
        @Json(name = "title")
        val title: String // GitHub
    )
}


interface Api {
    @GET("399c59aa64c8886ea8dc")
    suspend fun getProducts(): Either<ApiError, ProductsResponse>
}

fun main(args: Array<String>) = runBlocking {
    val api = Retrofit.Builder()
        .baseUrl("https://api.npoint.io/")
        .addCallAdapterFactory(EitherCallAdapterFactory())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(Api::class.java)

    val productsResp = api.getProducts()
    productsResp.fold({
        println("Something went wrong : ${it.message}")
    }, {
        println("Yey!! Products got! ${it}")
    })
}