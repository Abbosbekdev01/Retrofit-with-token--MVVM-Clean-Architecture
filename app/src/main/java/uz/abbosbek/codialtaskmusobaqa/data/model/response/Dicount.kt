package uz.abbosbek.codialtaskmusobaqa.data.model.response

data class Dicount(
    val amount: Int,
    val discount: Int,
    val endDate: String,
    val id: Int,
    val product: Product,
    val sold: Int,
    val startDate: String
)