package org.example.players

import org.example.cards.Card

class ComputerPlayer : Player {
    override val hand = mutableListOf<Card>()
    override val listOfCardsWon = mutableListOf<Card>()
    override var pointsWon = 0
    override var numCardsWon = 0

    override fun playCard(table: MutableList<Card>): Card {
        println("Cards in computer's hand: ${hand.joinToString(" ") { "[X]" }}")

        val card: Card = when {
            hand.size == 1 -> hand[0]
            table.isEmpty() -> {
                val listOfCardsWithSameSuit = findCardsWithSameSuit()
                val listOfCardsWithSameRank = findCardsWithSameRank()
                when {
                    listOfCardsWithSameSuit.size > 1 -> listOfCardsWithSameSuit.random()
                    listOfCardsWithSameRank.size > 1 -> listOfCardsWithSameRank.random()
                    else -> hand.random()
                }
            }
            else -> {
                val lastCard = table.last()
                val listOfCardWithSameSuitAsLast = findCardsWithSameSuitAsLast(lastCard)
                val listOfCardWithSameRankAsLast = findCardsWithSameRankAsLast(lastCard)

                when {
                    listOfCardWithSameSuitAsLast.size > 1 -> listOfCardWithSameSuitAsLast.random()
                    listOfCardWithSameRankAsLast.size > 1 -> listOfCardWithSameRankAsLast.random()
                    listOfCardWithSameSuitAsLast.size == 1 || listOfCardWithSameRankAsLast.size == 1 -> {
                        listOfCardWithSameSuitAsLast.apply { addAll(listOfCardWithSameRankAsLast) }.random()
                    }
                    else -> {
                        val listOfCardsWithSameSuit = findCardsWithSameSuit()
                        val listOfCardsWithSameRank = findCardsWithSameRank()
                        when {
                            listOfCardsWithSameSuit.size > 1 -> listOfCardsWithSameSuit.random()
                            listOfCardsWithSameRank.size > 1 -> listOfCardsWithSameRank.random()
                            else -> hand.random()
                        }
                    }
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