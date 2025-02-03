class SnikerRepImpl : SnikerRep {

    private val sneakersList = mutableListOf<SneakersDTO>()

    override fun getAllSneakers(): List<SneakersDTO> {
        return sneakersList
    }

    override fun getSneakerById(productId: Int): SneakersDTO? {
        return sneakersList.find { it.productId == productId }
    }

    override fun addSneaker(sneaker: SneakersDTO) {
        sneakersList.add(sneaker)
    }

    override fun updateSneaker(sneaker: SneakersDTO) {
        val index = sneakersList.indexOfFirst { it.productId == sneaker.productId }
        if (index != -1) {
            sneakersList[index] = sneaker
        }
    }

    override fun deleteSneaker(productId: Int) {
        sneakersList.removeIf { it.productId == productId }
    }

    override fun filterSneakers(filter: (SneakersDTO) -> Boolean): List<SneakersDTO> {
        return sneakersList.filter(filter)
    }
}