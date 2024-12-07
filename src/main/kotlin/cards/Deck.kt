package org.example.cards

import org.example.players.Player


class Deck {

    private var cards: MutableList<Card>

    init {
        cards = resetDeck()
        cards.shuffle()
    }

    private fun resetDeck(): MutableList<Card> {
        val cards = mutableListOf<Card>()
        for (rank in Rank.values()) {
            for (suit in Suit.values()) {
                cards.add(Card(rank, suit))
            }
        }
        return cards
    }

    fun dealCardsToPlayer(player: Player): Unit {
        repeat(6) {
            player.hand.add(cards.removeAt(cards.size - 1))
        }
    }

    fun dealCardsToTable(table: MutableList<Card>): Unit {
        println("Cards were dealt to the players; each player received 6 cards\n")
        repeat(4) {
            table.add(cards.removeAt(cards.size - 1))
        }
    }
}