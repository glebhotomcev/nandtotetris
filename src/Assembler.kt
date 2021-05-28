import java.io.*

class Assembler {

    fun start() {
        try {
            val file = loadFile()
            val parser = Parser(file)
            parser.parseFile()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Throws(FileNotFoundException::class)
    private fun loadFile(): File {
        return File("D:\\Gleb\\Work\\Coursera\\Nand to Tetris part 1\\nand2tetris\\nand2tetris\\projects\\06\\add\\Add.asm")
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val asm = Assembler()
            asm.start()
        }
    }

}