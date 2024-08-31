package org.example

import java.io.InputStream
import kotlin.system.exitProcess

class Tokenizer {

    fun tokenize(stream: InputStream): List<Token> {

        var number = stream.bufferedReader().read()
        val listOfTokens: MutableList<Token> = mutableListOf()
        if (number == -1) {
            println("Invalid JSON, empty file.")
            exitProcess(1)
        }
        while (number != -1) {
            if (number.toChar() == '{') {
                listOfTokens.add(Token(TokenType.LEFT_CURLY_BRACE, number.toString()))
            }
            if ((number.toChar()) == '}') {
                listOfTokens.add(Token(TokenType.RIGHT_CURLY_BRACE, number.toString()))
            }
            number = stream.bufferedReader().read()
        }
        return listOfTokens
    }
}