package io.cookma.recipeManagement.utility

import io.cookma.imagemanagement.utility.DataUri
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Before
import org.junit.Test

class DataUriTest {

    private val dataUriString: String = "data:image/png;base64,iVBOR..."

    lateinit var dataUri: DataUri


    @Before
    fun setUp() {
        dataUri = DataUri(dataUriString)
    }

    @Test
    fun type() {
        // given


        // when
        val type = dataUri.type()

        // then
        assertThat(type, equalTo("image"))
    }

    @Test
    fun extension() {
        // given

        // when
        val extension = dataUri.extension()

        // then
        assertThat(extension, equalTo("png"))
    }

    @Test
    fun data() {
        // given

        // when
        val data = dataUri.data()

        // then
        assertThat(data, equalTo("iVBOR..."))
    }
}