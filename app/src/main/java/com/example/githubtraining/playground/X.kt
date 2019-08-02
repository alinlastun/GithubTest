package com.example.githubtraining.playground

data class X(
    val string: String = "string",
    val int: Int = 32,
    val double: Double = 32.32
)

class Y {
    private val x1: X = X(string = "x1")
    private val x2: X? = X(string = "x2")
    private val x3: X? = null

    private var x4: X = X(string = "x4")
    private var x5: X? = X(string = "x5")
    private var x6: X? = null

    fun testX() {
        System.err.println("x1.string = ${x1.string}")
        if (x2 != null) {
            System.err.println("x2.string = ${x2.string}")
        }
        if (x3 != null) {
            System.err.println("x3.string = ${x3.string}")
        }

        x5?.let { nonNullX5 ->
            System.err.println("nonNullX5.string = ${nonNullX5.string}")
        }
    }
}