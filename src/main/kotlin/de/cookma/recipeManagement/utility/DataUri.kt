package de.cookma.recipeManagement.utility

data class DataUri(val dataUri: String) {


    fun type(): String {
        var colon = dataUri.indexOf(':') + 1
        var slash = dataUri.indexOf('/')
        return dataUri.substring(colon, slash)
    }

    fun extension(): String {
        var slash = dataUri.indexOf('/') + 1
        var semicolon = dataUri.indexOf(';')
        return dataUri.substring(slash, semicolon)
    }

    fun data(): String {
        var comma = dataUri.indexOf(',')
        return dataUri.substring(comma + 1)
    }

}