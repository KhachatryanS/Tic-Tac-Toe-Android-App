package com.example.tictactoe

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var board:List<Button>
    lateinit var resetButton:Button
    lateinit var turnTextView:TextView

    private fun initialize(){
        resetButton = findViewById(R.id.reset)
        turnTextView = findViewById(R.id.turnTextView)
        board = listOf(
            findViewById(R.id.zero), findViewById(R.id.one), findViewById(R.id.two),
            findViewById(R.id.three), findViewById(R.id.four), findViewById(R.id.five),
            findViewById(R.id.six), findViewById(R.id.seven), findViewById(R.id.eight)
        )
    }
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()

        var game = TicTacToe()
        turnTextView.text = "Player\n->X<-"

        for (i in board.indices){
            board[i].setOnClickListener {
                game.makeStep(i)
                if(game.isXTurn()) {
                    turnTextView.text = "Player\n->X<-"
                    board[i].text = game.O.toString()
                }else {
                    turnTextView.text = "Player\n->O<-"
                    board[i].text = game.X.toString()
                }
                when(game.getWinner()){
                    game.X -> turnTextView.text = "WINS\n->X<-"
                    game.O -> turnTextView.text = "WINS\n->O<-"
                    game.NO_ONE -> turnTextView.text = "WINS\n->NO ONE<-"
                }

            }
        }

        resetButton.setOnClickListener {
            game = TicTacToe()
            turnTextView.text = "Player\n->X<-"
            for (i in board.indices)
                board[i].text = ""
        }

    }
}