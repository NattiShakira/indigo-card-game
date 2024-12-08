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

        val listOfPlayers = determineWhoPlaysFirst(humanPlayer, computerPlayer)

        deck.dealCardsToTable(table)

        showCardsOnTable(table)

        repeat(4) {
            dealCardsToPlayers(listOfPlayers)

            repeat(6) {
                repeat(2) {
                    val player = listOfPlayers[it]
                    val cardToTable = player.playCard(table)

                    if (table.isEmpty()) {
                        table.add(cardToTable)
                        showCardsOnTable(table)
                    } else {
                        table.add(cardToTable)
                        val (lastCard, secondLastCard) = table.takeLast(2)

                        if (lastCard.rank == secondLastCard.rank || lastCard.suit == secondLastCard.suit) {
                            player.winCards(table, winningRanks)
                            table.clear()

                            println("${player.getString()} wins cards\n")
                            printStats(humanPlayer, computerPlayer)

                            println("\nNo cards on the table\n")
                        } else {
                            showCardsOnTable(table)
                        }
                    }
                }
            }
        }

        // If after the last card is put on the table, no one wins the cards, they go to the player who started second
        if (table.isNotEmpty() && table.size != 52) {
            listOfPlayers[1].winCards(table, winningRanks)
            println("${listOfPlayers[1].getString()} wins all the cards left on the table because he started playing second\n")
        }

        when {
            humanPlayer.numCardsWon > computerPlayer.numCardsWon -> humanPlayer.pointsWon += 3
            computerPlayer.numCardsWon > humanPlayer.numCardsWon -> computerPlayer.pointsWon += 3
        }

        printStats(humanPlayer, computerPlayer)

        printWinner(humanPlayer, computerPlayer)
    }

    private fun printRules() {
        println("Welcome to Indigo Card Game!\n")
        println("Rules:")
        println("""
            - There are 52 cards in the deck.
            - Initially, 4 cards are put on the table from the deck, face up.
            - Then, players are given 6 cards each from the deck. Players cannot see the cards of each other.
            - Players take turns:
              - if a player has in his hand a card that matches a rank or a suit of the last card on the table, he can toss it on the table and then take all the cards from the table and put them aside into his pile of won cards
              - otherwise, the player put any card on the table and this card becomes the last card on the table
            - If players don’t have cards in their hands, each player takes 6 new cards from the deck.
            - The game lasts as long as there are cards in the deck.
            - At the end of the game, the points are given for each special card that is in the cards that a player won (A, 10, J, Q, K gives 1 point each). 
            - Plus, a player with the most cards gets 3 additional points.
            - A winner is a player with the biggest number of points.
        """.trimIndent())
        println()
    }
    private fun determineWhoPlaysFirst(humanPlayer: Player, computerPlayer: Player): MutableList<Player> {
        val listOfPlayers = mutableListOf<Player>()
        while(true) {
            println("Do you want to play first (yes or no)?")
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
                else -> {
                    println("Invalid input!\n")
                    continue
                }
            }
        }
        return listOfPlayers
    }

    private fun showCardsOnTable(table: MutableList<Card>) {
            println("\nCards on the table: ${
                table.joinToString(" ") { card -> card.strRepr() }
            }, the top card is ${table.last().strRepr()}\n")
    }

    private fun dealCardsToPlayers(listOfPlayers: MutableList<Player>) {
        println("Cards were dealt to the players; each player received 6 cards\n")
        repeat(2) {
            deck.dealCardsToPlayer(listOfPlayers[it])
        }
    }

    private fun printStats(humanPlayer: Player, computerPlayer: Player) {
        println("Game statistics:")
        println("Player: ${humanPlayer.pointsWon} points and ${humanPlayer.numCardsWon} cards")
        println("Computer: ${computerPlayer.pointsWon} points and ${computerPlayer.numCardsWon} cards")
    }

    private fun printWinner(humanPlayer: HumanPlayer, computerPlayer: ComputerPlayer) {
        println()
        println(when {
            humanPlayer.pointsWon > computerPlayer.pointsWon -> "You won with ${humanPlayer.pointsWon} points and ${humanPlayer.numCardsWon} cards!"
            computerPlayer.pointsWon > humanPlayer.pointsWon -> "Computer won with ${computerPlayer.pointsWon} points and ${computerPlayer.numCardsWon} cards!"
            else -> "It's a tie!"
        })
        println("\nGame over")
    }

}