import kotlin.math.pow

class Circle(_name: String, _radius: Double) : Shape(_name) {
    private var radius = _radius

    init {
        setDimensions()
    }

    fun setDimensions() {
        super.dimensions = "Radius = $radius, Circumference = %.3f".format(2 * Math.PI * radius)
    }

    override fun getArea() : Double = Math.PI * radius.pow(2)
}