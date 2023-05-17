import kotlin.math.sqrt

open class Triangle(_name: String, _length1: Double, _length2: Double, _length3: Double) : Shape(_name) {
    private var length1 = _length1
    private var length2 = _length2
    private var length3 = _length3

    init {
        setDimensions()
    }

    fun setDimensions() {
        super.dimensions = "Side lengths: $length1 x $length2 x $length3"
    }

    override fun getArea() : Double {
        val s = (length1 + length2 + length3) / 2
        return sqrt(s * (s-length1) * (s-length2) * (s-length3))
    }
}