package org.example.players

import org.example.cards.Card

class ComputerPlayer : Player {
    override val hand = mutableListOf<Card>()
    override val listOfCardsWon = mutableListOf<Card>()
    override var pointsWon = 0
    override var numCardsWon = 0

    override fun playCard(table: MutableList<Card>): Card {
        println(
            "Cards in computer's hand: ${hand.joinToString(" ") { card -> card.strRepr() }}"
        )

        val card: Card
        if (hand.size == 1) {
            card = hand[0]
        } else if (table.isEmpty()) {
            val listOfCardsWithSameSuit = findCardsWithSameSuit()
            val listOfCardsWithSameRank = findCardsWithSameRank()
            card = if (listOfCardsWithSameSuit.size > 1) {
                listOfCardsWithSameSuit.random()
            } else if (listOfCardsWithSameRank.size > 1) {
                listOfCardsWithSameRank.random()
            } else {
                hand.random()
            }
        } else {
            val lastCard = table.last()
            val listOfCardWithSameSuitAsLast = findCardsWithSameSuitAsLast(lastCard)
            val listOfCardWithSameRankAsLast = findCardsWithSameRankAsLast(lastCard)
            card = if (listOfCardWithSameSuitAsLast.size > 1) {
                listOfCardWithSameSuitAsLast.random()
            } else if (listOfCardWithSameRankAsLast.size > 1) {
                listOfCardWithSameRankAsLast.random()
            } else if (listOfCardWithSameRankAsLast.size == 1 || listOfCardWithSameSuitAsLast.size == 1 ) {
                listOfCardWithSameSuitAsLast.addAll(listOfCardWithSameRankAsLast)
                listOfCardWithSameSuitAsLast.random()
            }else {
                val listOfCardsWithSameSuit = findCardsWithSameSuit()
                val listOfCardsWithSameRank = findCardsWithSameRank()
                if (listOfCardsWithSameSuit.size > 1) {
                    listOfCardsWithSameSuit.random()
                } else if (listOfCardsWithSameRank.size > 1) {
                    listOfCardsWithSameRank.random()
                } else {
                    hand.random()
                }
            }
        }
        println("Computer plays ${card.strRepr()}")
        hand.remove(card)
        return card
    }

    override fun getString() = "Computer"

    private fun findCardsWithSameSuit(): MutableList<Card> {
        val rankCounts = hand.groupingBy { it.suit }.eachCount()
        return hand.filter { card -> (rankCounts[card.suit] ?: 0) > 1 }.toMutableList()
    }

    private fun findCardsWithSameRank(): MutableList<Card> {
        val rankCounts = hand.groupingBy { it.rank }.eachCount()
        return hand.filter { card -> (rankCounts[card.rank] ?: 0) > 1 }.toMutableList()
    }

    private fun findCardsWithSameSuitAsLast(lastCard: Card): MutableList<Card> {
        return hand.filter { it.suit == lastCard.suit }.toMutableList()
    }

    private fun findCardsWithSameRankAsLast(lastCard: Card): MutableList<Card> {
        return hand.filter { it.rank == lastCard.rank }.toMutableList()
    }
}