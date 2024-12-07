package org.example.cards

class Card(val rank: Rank, val suit: Suit) {

    fun strRepr() = "${rank.getRankName()}${suit.getSuitName()}"

}