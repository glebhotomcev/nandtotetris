import java.lang.StringBuilder

class Code {

    fun destToBinary(dest: String?): String {
        // A D M - 1 1 1
        if (dest == null) return "000"
        val template = StringBuilder()
        template.append(dest.contains("A").toStrBit())
        template.append(dest.contains("D").toStrBit())
        template.append(dest.contains("M").toStrBit())
        return template.toString()
    }

    fun compToBinary(comp: String): String {
        return when (comp) {
            "0" -> "0101010"
            "1" -> "0111111"
            "-1" -> "0111010"
            "D" -> "0001100"
            "A" -> "0110000"
            "M" -> "1110000"
            "!D" -> "0001101"
            "!A" -> "0110001"
            "!M" -> "1110001"
            "-D" -> "0001111"
            "-A" -> "0110011"
            "-M" -> "1110011"
            "D+1" -> "0011111"
            "A+1" -> "0110111"
            "M+1" -> "1110111"
            "D-1" -> "0001110"
            "A-1" -> "0110010"
            "M-1" -> "1110010"
            "D+A" -> "0000010"
            "D+M" -> "1000010"
            "D-A" -> "0010011"
            "D-M" -> "1010011"
            "A-D" -> "0000111"
            "M-D" -> "1000111"
            "D&A" -> "0000000"
            "D&M" -> "1000000"
            "D|A" -> "0010101"
            "D|M" -> "1010101"
            else -> ""
        }
    }

    fun jmpToBinary(jmp: String): String {
        return when (jmp) {
            "JGT" -> "001"
            "JEQ" -> "010"
            "JGE" -> "011"
            "JLT" -> "100"
            "JNE" -> "101"
            "JLE" -> "110"
            "JMP" -> "111"
            else -> "000"
        }
    }
}