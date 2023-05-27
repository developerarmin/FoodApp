package com.example.mariyafatimafastfoodapp

data class FoodInformation(
    var id: Int,
    var name: String,
    var description: String,
    var painter: Int,
    var price: Float
)


val foodInformation = listOf(
    FoodInformation(
        id = 1,
        name = "Sushi Mobada",
        description = "The most loved among north easter Indians. Little spicy & soft.",
        painter = R.drawable.food1,
        price = 20.00f
    ),
    FoodInformation(
        id = 2,
        name = "Kani Maki",
        description = "Crispy and tasty like non other. Added meat layer that is lit.",
        painter = R.drawable.food2,
        price = 10.99f
    ),
    FoodInformation(
        id = 3,
        name = "Smoked Salmon",
        description = "Elegant and non sticky taste of rice and japanese fish salmon.",
        painter = R.drawable.food3,
        price = 14.08f
    ),
    FoodInformation(
        id = 4,
        name = "Sushi Mobada",
        description = "The most loved among north easter Indians. Little spicy & soft.",
        painter = R.drawable.food1,
        price = 63.15f
    )
)

val navPainterList = listOf(
    R.drawable.home, R.drawable.favorite, R.drawable.basket, R.drawable.profile
)

val navNameList = listOf("Home", "Favorite", "Basket", "Profile")

val menuPainterList = listOf(
    R.drawable.menu_food1, R.drawable.menu_food2,
    R.drawable.menu_food3, R.drawable.menu_food4
)

val menuNameList = listOf(
    "Western\nVeggies", "Traditional\nBiryani",
    "Classic\nIndian", "Chicken\nDum"
)

val tabViewList = listOf("New", "Offers", "Featured", "Snacks")
