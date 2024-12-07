package org.example.cards

enum class Suit(private val suitName: String) {
    SPADES("\u2660"),
    HEARTS("\u2665"),
    DIAMONDS("\u2666"),
    CLUBS("\u2663");

    fun getSuitName() = "$suitName"
}