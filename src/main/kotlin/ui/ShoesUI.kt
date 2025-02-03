package org.example.ui

import org.example.domain.ShoesUseCase
import org.example.domain.request.AddShoesRequest
import org.example.domain.response.ShoesDetailsResponse

class ShoesUI(private val shoesUseCase: ShoesUseCase) {

    fun start() {
        while (true) {
            println("Выберите действие:")
            println("Add -- Добавить кроссовки")
            println("Info -- Получить информацию о кроссовках")
            println("Del -- Удалить кроссовки")
            println("Fil Фильтровать кроссовки по цене")
            println("Exit Выйти")

            when (readlnOrNull()?.toIntOrNull()) {
                "Add" -> addShoes()
                "Info" -> getShoesDetails()
                "Del" -> deleteShoes()
                "Fil" -> filterShoesByCost()
                "Exit" -> return
                "exit" -> return
                else -> println("Нет такой команды. Давай по новой.")
            }
        }
    }

    private fun addShoes() {
        println("Введите название кроссовок:")
        val productName = readlnOrNull() ?: run {
            println("Название не может быть пустым.")
            return
        }

        println("Введите цену кроссовок:")
        val cost = readlnOrNull() ?: run {
            println("Цена не может быть пустой.")
            return
        }

        println("Введите количество кроссовок:")
        val count = readlnOrNull()?.toIntOrNull() ?: run {
            println("Некорректное количество.")
            return
        }

        println("Введите URL фотографии кроссовок:")
        val photo = readlnOrNull() ?: run {
            println("URL фотографии не может быть пустым.")
            return
        }

        println("Введите описание кроссовок:")
        val description = readlnOrNull() ?: run {
            println("Описание не может быть пустым.")
            return
        }

        val request = AddShoesRequest(
            productName = productName,
            cost = cost,
            count = count,
            photo = photo,
            description = description
        )

        val isSuccess = shoesUseCase.addShoes(request)
        if (isSuccess) {
            println("Кроссовки успешно добавлены!")
        } else {
            println("Ошибка при добавлении кроссовок.")
        }
    }

    private fun getShoesDetails() {
        println("Введите ID кроссовок:")
        val productId = readlnOrNull()?.toIntOrNull() ?: run {
            println("Некорректный ID.")
            return
        }

        val details = shoesUseCase.getShoesDetails(productId)
        if (details != null) {
            println("Информация о кроссовках:")
            println("Название: ${details.productName}")
            println("Цена: ${details.cost}")
            println("Фотография: ${details.photo}")
            println("Описание: ${details.description}")
        } else {
            println("Кроссовки с ID $productId не найдены.")
        }
    }

    private fun deleteShoes() {
        println("Введите ID кроссовок для удаления:")
        val productId = readlnOrNull()?.toIntOrNull() ?: run {
            println("Некорректный ID.")
            return
        }

        val isSuccess = shoesUseCase.deleteShoes(productId)
        if (isSuccess) {
            println("Кроссовки успешно удалены!")
        } else {
            println("Ошибка при удалении кроссовок.")
        }
    }

    private fun filterShoesByCost() {
        println("Введите минимальную цену:")
        val minCost = readlnOrNull()?.toIntOrNull() ?: run {
            println("Некорректная цена.")
            return
        }

        println("Введите максимальную цену:")
        val maxCost = readlnOrNull()?.toIntOrNull() ?: run {
            println("Некорректная цена.")
            return
        }

        val filteredShoes = shoesUseCase.filterShoesByCost(minCost, maxCost)
        if (filteredShoes.isNotEmpty()) {
            println("Найденные кроссовки:")
            filteredShoes.forEach { shoes ->
                println("Название: ${shoes.productName}, Цена: ${shoes.cost}")
            }
        } else {
            println("Кроссовки в указанном диапазоне цен не найдены.")
        }
    }
}