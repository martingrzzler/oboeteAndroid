package com.martingrzzler.oboeteandroid.main.util


import org.junit.Test
import org.junit.jupiter.api.Assertions.*



internal class SearchWordAdapterTest{

    @Test
    fun formatTranslationString_returnsStringWithoutBrackets() {

        // Arrange
        val fakeTranslation: List<String> = listOf("man", "person", "pal")
        // Act
        val fakeTranslationString = SearchWordAdapter.formatTranslationList(fakeTranslation)
        // Assert
        assertEquals("man, person, pal", fakeTranslationString)
        println("man, person, pal : $fakeTranslationString ")
    }

    @Test
    fun inputLongList_makesSubstring_of_50_characters() {
        // Arrange
        val longListOfTranslations: List<String> = listOf("peter", "manual", "long", "word", "need more characters", "still not enough", "now it is enough", "probably")
        // Act
        val shortenedTranslation = SearchWordAdapter.formatTranslationList(longListOfTranslations)
        // Assert
        assertEquals(52, shortenedTranslation.length)
        println("length before: ${longListOfTranslations.toString().length}-- length after: ${shortenedTranslation.length}")
    }

    @Test
    fun inputShortList_staysTheSame_minus_two_brackets() {
        //Arange
        val shortListOfTranslations: List<String> = listOf("testing", "is", "fun")
        // Act
        val translationWithoutBracketsString = SearchWordAdapter.formatTranslationList(shortListOfTranslations)
        // Assert
        assertEquals(16, translationWithoutBracketsString.length)
        println("Before length: ${shortListOfTranslations.toString().length}-- Length minus two brackets: ${translationWithoutBracketsString.length}")
    }

}