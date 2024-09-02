package org.example

import java.io.InputStream
import kotlin.system.exitProcess

class Tokenizer {

    fun tokenize(stream: InputStream): List<Any> {
        val reader = stream.bufferedReader()
        var number = reader.read()
        val listOfTokens: MutableList<Any> = mutableListOf()
        if (number == -1) {
            println("Invalid JSON, empty file.")
            exitProcess(1)
        }
        while (number != -1) {
            if (number.toChar() == '{') {
                listOfTokens.add(Token(TokenType.LEFT_CURLY_BRACE, number.toChar()))
            }
            if ((number.toChar()) == '}') {
                listOfTokens.add(Token(TokenType.RIGHT_CURLY_BRACE, number.toChar()))

            }
            if ((number.toChar()) == '[') {
                listOfTokens.add(Token(TokenType.LEFT_SQUARE_BRACKET, number.toChar()))
            }
            if ((number.toChar()) == ']') {
                listOfTokens.add(Token(TokenType.RIGHT_SQUARE_BRACKET, number.toChar()))
            }
            if ((number.toChar()) == ':') {
                listOfTokens.add(Token(TokenType.COLON, number.toChar()))
            }
            if (number.toChar() == '"') {
                val charArray = mutableListOf<Char>()
                number = reader.read()
                while (number.toChar() != '"') {
                    charArray.add(number.toChar())
                    number = reader.read()
                }
                if (charArray.isNotEmpty()) {
                    val str = charArray.joinToString("")
                    listOfTokens.add(Token(TokenType.STRING, str))
                }

            }
            if ((number.toChar()).isDigit()) {
                val charArray = mutableListOf<Char>()
                charArray.add(number.toChar())
                reader.mark(1)
                number = reader.read()
                while (number.toChar().isDigit() || number.toChar() == '.') {
                    charArray.add(number.toChar())
                    reader.mark(1)
                    number = reader.read()
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
            number = reader.read()

        }
        return listOfTokens
    }
}