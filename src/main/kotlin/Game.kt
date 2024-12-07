package org.example

import org.example.cards.Card
import org.example.cards.Deck
import org.example.cards.Rank
import org.example.players.ComputerPlayer
import org.example.players.HumanPlayer
import org.example.players.Player

class Game {

    private val humanPlayer = HumanPlayer()
    private val computerPlayer = ComputerPlayer()
    private val deck = Deck()

    private val table = mutableListOf<Card>()
    private val winningRanks = mutableListOf(Rank.ACE, Rank.KING, Rank.QUEEN, Rank.JOKER, Rank.TEN)

    fun play() {
        printRules()

        val listOfPlayers = start(humanPlayer, computerPlayer)

        deck.dealCardsToTable(table)

        showInitialCardsOnTable(table)
        println("\n${table.size} cards on the table, and the top card is ${table.last().strRepr()}")

        var lastWonPlayer: Player = humanPlayer

        repeat(4) {
            dealCardsToPlayers(listOfPlayers)

            repeat(6) {
                repeat(2) {
                    val player = listOfPlayers[it]
                    val cardToTable = player.playCard(table)

                    if (table.isEmpty()) {
                        table.add(cardToTable)
                        println("\n${table.size} cards on the table, and the top card is ${table.last().strRepr()}")
                    } else {
                        table.add(cardToTable)

                        val lastCard = table[table.size - 1]
                        val secondLastCard = table[table.size - 2]

                        if (lastCard.rank == secondLastCard.rank || lastCard.suit == secondLastCard.suit) {
                            player.winCards(table, winningRanks)

                            lastWonPlayer = player

                            table.clear()

                            println("${player.getString()} wins cards")
                            printStats(humanPlayer, computerPlayer)

                            println("\nNo cards on the table")
                        } else {
                            println("\n${table.size} cards on the table, and the top card is ${table.last().strRepr()}")
                        }
                    }
                }
            }
        }
        if (table.size == 52) {
            listOfPlayers[0].winCards(table, winningRanks)
        } else if (table.isNotEmpty()) {
            lastWonPlayer.winCards(table, winningRanks)
        }

        if (humanPlayer.numCardsWon == computerPlayer.numCardsWon) {
            listOfPlayers[0].pointsWon += 3
        } else if (humanPlayer.numCardsWon > computerPlayer.numCardsWon) {
            humanPlayer.pointsWon += 3
        } else {
            computerPlayer.pointsWon += 3
        }

        printStats(humanPlayer, computerPlayer)
        println("Game over")
    }

    private fun start(humanPlayer: Player, computerPlayer: Player): MutableList<Player> {
        val listOfPlayers = mutableListOf<Player>()
        while(true) {
            println("Do you want to play first (\"yes\" or \"no\")?")
            when(readln().replace(" ", "").lowercase()) {
                "yes" -> {
                    listOfPlayers.add(humanPlayer)
                    listOfPlayers.add(computerPlayer)
                    break
                }
                "no" -> {
                    listOfPlayers.add(computerPlayer)
                    listOfPlayers.add(humanPlayer)
                    break
                }
                else -> continue
            }
        }
        return listOfPlayers
    }

    private fun printRules() {
        println("Welcome to Indigo Card Game!")
        println("Rules are as follows\n")
    }

    private fun showInitialCardsOnTable(table: MutableList<Card>) {
        println(
            "Initial cards on the table: ${
                table.joinToString(" ") { it.strRepr() }
            }"
        )
    }

    private fun dealCardsToPlayers(listOfPlayers: MutableList<Player>) {
        repeat(2) {
            deck.dealCardsToPlayer(listOfPlayers[it])
        }
    }

    private fun printStats(humanPlayer: Player, computerPlayer: Player) {
        println("Score: Player ${humanPlayer.pointsWon} - Computer ${computerPlayer.pointsWon}")
        println("Cards: Player ${humanPlayer.numCardsWon} - Computer ${computerPlayer.numCardsWon}")
    }

}