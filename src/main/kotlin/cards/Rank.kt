package org.example.cards

enum class Rank(private val rankName: String) {
    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JOKER("J"),
    QUEEN("Q"),
    KING("K");

    fun getRankName() = "$rankName"
}