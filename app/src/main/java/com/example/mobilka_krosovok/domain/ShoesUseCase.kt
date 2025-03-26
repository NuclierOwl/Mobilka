interface ShoesUseCase {
    fun addShoes(request: AddShoesRequest): Boolean
    fun getShoesDetails(productId: Int): ShoesDetailsResponse?
    fun deleteShoes(productId: Int): Boolean
    fun filterShoesByCost(minCost: Int, maxCost: Int): List<ShoesDetailsResponse>
}