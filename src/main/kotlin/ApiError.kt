import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class ApiError(
    val errorCode: Int,
    val message: String
) {
}