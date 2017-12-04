package de.cookma.recipeManagement.domain.model

class Recipe {
    var title: String = ""
    var subTitle: String = ""
    var images: List<Image> = ArrayList()
    var ingredients: List<Ingredient> = ArrayList()
    var preparation: String = ""
}