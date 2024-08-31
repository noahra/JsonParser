package org.example

import java.io.File
import java.io.InputStream
import java.util.*
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    var fileName = args[0]
    val inputStream: InputStream = File(fileName).inputStream()
    val queue: Queue<Char> = LinkedList()
    var number = inputStream.bufferedReader().read()
    if (number == -1) {
        println("Invalid JSON, empty file.")
        exitProcess(1)
    }
    while (number != -1) {
        if (number.toChar() == '{') {
            queue.add(number.toChar())
        }
        if ((number.toChar()) == '}' && queue.peek() != '{') {
            println("Error: Mismatched closing brace '}' found.")
            exitProcess(1)
        }
        if ((number.toChar()) == '}' && queue.peek() == '{') {
            queue.add(number.toChar())
        }
        number = inputStream.bufferedReader().read()
    }
}