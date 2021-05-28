import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.lang.StringBuilder
import java.util.stream.Stream

class Parser(file: File) {

    private var stream: Stream<String>
    private var fileWriter: FileWriter

    init {
        val fileReader = FileReader(file)
        val bufReader = BufferedReader(fileReader)
        stream = bufReader.lines()
        val fileOut = File("D:\\Gleb\\Work\\Coursera\\Nand to Tetris part 1\\nand2tetris\\nand2tetris\\projects\\06\\add\\Add.hack")
        fileOut.createNewFile()
        fileWriter = FileWriter(fileOut)
    }

    fun parseFile() {
        stream.forEach { command: String ->
            if (!lineIsACommentOrEmpty(command)) {
                val binaryCommand = processCommand(command)
                fileWriter.write(binaryCommand)
                fileWriter.write('\n'.toInt())
            }
        }
        stream.close()
        fileWriter.close()
    }

    private fun processCommand(command: String): String {
        return when (detectCommandType(command)) {
            CommandType.A_COMMAND -> convertValue(command)
            CommandType.C_COMMAND -> convertCommand(command)
            CommandType.L_COMMAND -> convertLoopMark()
        }
    }

    private fun convertLoopMark() = ""

    private fun convertCommand(command: String): String {
        val code = Code()

        val dest = findDest(command)
        val destBinaryPart = code.destToBinary(dest)

        val comp = findComp(command)
        val compBinaryPart = code.compToBinary(comp)

        val jmp = findJump(command)
        val jmpBinaryPart = code.jmpToBinary(jmp)

        return StringBuilder("111")
                .append(destBinaryPart)
                .append(compBinaryPart)
                .append(jmpBinaryPart)
                .toString()
    }

    private fun findJump(command: String): String {
        return command.substringAfter(';', "")
    }

    private fun findComp(command: String): String {
        var destIndex = command.indexOf('=')
        if (destIndex == -1) destIndex = 0
        var jmpIndex = command.indexOf(';')
        if (jmpIndex == -1) jmpIndex = command.length
        return command.substring(destIndex + 1 until jmpIndex)
    }

    private fun findDest(command: String): String? {
        val index = command.indexOfFirst { c -> c == '=' }
        return if (index != -1) {
            command.substring(0..index)
        } else {
            null
        }
    }

    private fun convertValue(command: String): String {
        val value = command.drop(1).toInt()
        return value.toBinary16()
    }

    private fun detectCommandType(command: String): CommandType {
        return if (command.startsWith("@")) {
            CommandType.A_COMMAND
        } else if (command.startsWith('(') && command.endsWith(')')) {
            CommandType.L_COMMAND
        } else {
            CommandType.C_COMMAND
        }
    }

    private fun lineIsACommentOrEmpty(command: String): Boolean {
        return (command.startsWith("//") || command.trim().isEmpty())
    }

    enum class CommandType {
        A_COMMAND,
        C_COMMAND,
        L_COMMAND
    }

}