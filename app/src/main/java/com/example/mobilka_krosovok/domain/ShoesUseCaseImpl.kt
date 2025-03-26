class ShoesUseCaseImpl(
    private val shoesRepository: SneakersRepository
) : ShoesUseCase {

    override fun addShoes(request: AddShoesRequest): Boolean {
        val sneaker = SneakersDTO(
            productId = generateUniqueId(),
            productName = request.productName,
            cost = request.cost,
            count = request.count,
            photo = request.photo,
            description = request.description
        )
        shoesRepository.addSneaker(sneaker)
        return true
    }

    override fun getShoesDetails(productId: Int): ShoesDetailsResponse? {
        val sneaker = shoesRepository.getSneakerById(productId)
        return sneaker?.let {
            ShoesDetailsResponse(
                productName = it.productName,
                cost = it.cost,
                photo = it.photo,
                description = it.description
            )
        }
    }

    override fun deleteShoes(productId: Int): Boolean {
        shoesRepository.deleteSneaker(productId)
        return true
    }

    override fun filterShoesByCost(minCost: Int, maxCost: Int): List<ShoesDetailsResponse> {
        return shoesRepository.filterSneakers { sneaker ->
            sneaker.cost.toInt() in minCost..maxCost
        }.map {
            ShoesDetailsResponse(
                productName = it.productName,
                cost = it.cost,
                photo = it.photo,
                description = it.description
            )
        }
    }

    private fun generateUniqueId(): Int {
        return (0..1000).random()  // генерация уникального ID
    }
}