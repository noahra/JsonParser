package org.example

import java.io.File
import java.io.InputStream

fun main(args: Array<String>) {
    val fileName = args[0]
    val inputStream: InputStream = File(fileName).inputStream()
    val list = Tokenizer().tokenize(inputStream)

    println(list)
}