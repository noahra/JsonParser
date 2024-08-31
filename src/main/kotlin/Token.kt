package org.example

enum class TokenType {
    LEFT_CURLY_BRACE, RIGHT_CURLY_BRACE, COMMA, STRING, NUMBER, BOOLEAN, NULL, LEFT_SQUARE_BRACKET, RIGHT_SQUARE_BRACKET, COLON
}

class Token(val typeOfToken: TokenType, val value: String)
