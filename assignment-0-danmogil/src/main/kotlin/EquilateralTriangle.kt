import kotlin.math.pow
import kotlin.math.sqrt

class EquilateralTriangle(_name: String, _length: Double) : Triangle(_name, _length, _length, _length) {
    private var length = _length

    override fun getArea() : Double = (sqrt(3.0)/4) * length.pow(2)
}