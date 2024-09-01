package org.example

import java.io.InputStream
import kotlin.system.exitProcess

class Tokenizer {

    fun tokenize(stream: InputStream): List<Any> {

        var number = stream.bufferedReader().read()
        val listOfTokens: MutableList<Any> = mutableListOf()
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
            if ((number.toChar()) == '[') {
                listOfTokens.add(Token(TokenType.LEFT_SQUARE_BRACKET, number.toString()))
            }
            if ((number.toChar()) == ']') {
                listOfTokens.add(Token(TokenType.RIGHT_SQUARE_BRACKET, number.toString()))
            }
            if ((number.toChar()) == ':') {
                listOfTokens.add(Token(TokenType.COLON, number.toString()))
            }
            if ((number.toChar()).isDigit()) {
                var charArray = mutableListOf<Char>()
                charArray.add(number.toChar())
                stream.bufferedReader().mark(1)
                number = stream.bufferedReader().read()
                while (number.toChar().isDigit() || number.toChar() == '.') {
                    charArray.add(number.toChar())
                    stream.bufferedReader().mark(1)
                    number = stream.bufferedReader().read()
                }
                if (charArray.isNotEmpty()) {
                    val numberString = charArray.joinToString("")
                    val numberToAdd = if (numberString.contains('.')) {
                        numberString.toFloat() // Parse as Float
                    } else {
                        numberString.toInt() // Parse as Int
                    }
                    listOfTokens.add(Token(TokenType.NUMBER, numberToAdd))
                }
            }
            number = stream.bufferedReader().read()
        }
        return listOfTokens
    }
}