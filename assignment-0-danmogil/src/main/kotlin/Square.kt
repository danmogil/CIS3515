class Square(_name: String, _length: Double, _width: Double) : Shape(_name) {
    private var length = _length
    private var width = _width

    init {
        setDimensions()
    }

    fun setDimensions() {
        super.dimensions = "Length = $length, Width = $width"
    }

    override fun getArea() : Double = length * width
}