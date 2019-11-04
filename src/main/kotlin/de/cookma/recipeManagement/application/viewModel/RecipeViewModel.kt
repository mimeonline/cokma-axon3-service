package de.cookma.recipeManagement.application.viewModel

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class RecipeViewModel(

        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        @JsonIgnore
        var id: Long? = null,
        var recipeId: String = "",
        var title: String = "",
        var subTitle: String = "",
        var preparation: String = "") {

}