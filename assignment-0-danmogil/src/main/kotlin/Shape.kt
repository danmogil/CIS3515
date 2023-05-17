abstract class Shape (_name : String) : Dimensionable{
    var name = _name
    protected lateinit var dimensions : String

    open fun getArea() : Double {
        return 0.0
    }

    override fun printDimensions(): String = dimensions
}