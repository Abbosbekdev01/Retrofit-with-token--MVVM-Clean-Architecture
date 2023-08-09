package uz.abbosbek.codialtaskmusobaqa.data.model.response

data class UserResponse(
    val dicounts: List<Dicount>,
    val total_ball: Int,
    val total_coupon: Int,
    val total_sales_summa: Int,
    val total_users: Int,
    val warehouses: List<Warehouse>
)