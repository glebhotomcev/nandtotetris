fun Boolean.toStrBit() = if (this) "1" else "0"

fun Int.toBinary16(): String {
    return String.format("%16s", Integer.toBinaryString(this))
            .replace(" ".toRegex(), "0")
}