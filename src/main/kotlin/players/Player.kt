package org.example.players

import org.example.cards.Card
import org.example.cards.Rank

interface Player {
    val hand: MutableList<Card>
    val listOfCardsWon: MutableList<Card>
    var pointsWon: Int
    var numCardsWon: Int

    fun playCard(table: MutableList<Card>): Card

    fun getString(): String

    fun winCards(cardsOnTable: MutableList<Card>, winningRanks: MutableList<Rank>) {
        val filteredCards = cardsOnTable.filter { it.rank in winningRanks }
        numCardsWon += cardsOnTable.size
        pointsWon += filteredCards.size
    }

}