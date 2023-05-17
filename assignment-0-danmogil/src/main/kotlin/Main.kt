import java.util.*

fun main(args: Array<String>) {
    val reader = Scanner(System.`in`)
    val col_GREEN = "\u001B[32m"
    val col_RESET = "\u001B[0m"


    print("Enter circle's radius: ")
    val circle : Shape = Circle("C1", reader.nextDouble())

    print("\nEnter square's length & width: ")
    val square : Shape = Square("S1", reader.nextDouble(), reader.nextDouble())

    print("\nEnter triangle's side lengths: ")
    val triangle : Shape = Triangle("T1", reader.nextDouble(), reader.nextDouble(), reader.nextDouble())

    print("\nEnter equilateral triangle's side length: ")
    val eq_triangle : Shape = EquilateralTriangle("EQ-T1", reader.nextDouble())

    println("\n${col_GREEN}RESULTS$col_RESET")

    println("Name: ${circle.name}, ${circle.printDimensions()}, Area: ${circle.getArea()}")
    println("Name: ${square.name}, ${square.printDimensions()}, Area: ${square.getArea()}")
    println("Name: ${triangle.name}, ${triangle.printDimensions()}, Area: ${triangle.getArea()}")
    println("Name: ${eq_triangle.name}, ${eq_triangle.printDimensions()}, Area: ${eq_triangle.getArea()}")
}