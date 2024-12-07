package org.example.players

import org.example.cards.Card
import kotlin.system.exitProcess

class HumanPlayer : Player {
    override val hand = mutableListOf<Card>()
    override val listOfCardsWon = mutableListOf<Card>()
    override var pointsWon = 0
    override var numCardsWon = 0

    override fun playCard(table: MutableList<Card>): Card {
        println(
            "Cards in your hand: ${
                hand.mapIndexed { index, card -> "${index + 1}) ${card.strRepr()}" }.joinToString(" ")
            }"
        )

        var index: Int
        while (true) {
            println("Choose an index of a card to play (1-${hand.size}) or type \"exit\" to stop the game:")
            val input = readln()

            if (input.replace(" ", "").lowercase() == "exit") {
                println("Game over")
                exitProcess(0)
            }

            try {
                index = input.trim().toInt()
                if (index in 1..hand.size) return hand.removeAt(index - 1)
            } catch (_: NumberFormatException) {
            }
        }
    }

    override fun getString() = "Player"
}