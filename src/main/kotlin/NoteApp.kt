import java.util.Scanner

class NoteApp {
    private val archives = mutableListOf<Archive>()
    private val scanner = Scanner(System.`in`)

    fun start() {
        mainMenu()
    }

    private fun mainMenu() {
        while (true) {
            println("\nСписок архивов:")
            println("0. Создать архив")
            archives.forEachIndexed { index, archive ->
                println("${index + 1}. ${archive.name}")
            }
            println("${archives.size + 1}. Выход")

            when (val choice = readInt()) {
                0 -> createArchive()
                in 1..archives.size -> openArchive(archives[choice - 1])
                archives.size + 1 -> {
                    println("Выход из программы")
                    return
                }
                else -> println("Некорректный ввод. Попробуйте снова.")
            }
        }
    }

    private fun createArchive() {
        println("Введите название архива:")
        val name = scanner.nextLine().trim()
        if (name.isBlank()) {
            println("Название архива не может быть пустым. Попробуйте снова.")
            return
        }
        archives.add(Archive(name))
        println("Архив \"$name\" создан.")
    }

    private fun openArchive(archive: Archive) {
        while (true) {
            println("\nАрхив: ${archive.name}")
            println("0. Создать заметку")
            archive.displayNotes()
            println("${archive.notes.size + 1}. Вернуться")

            when (val choice = readInt()) {
                0 -> createNoteInArchive(archive)
                in 1..archive.notes.size -> openNote(archive.notes[choice - 1])
                archive.notes.size + 1 -> return
                else -> println("Некорректный ввод. Попробуйте снова.")
            }
        }
    }

    private fun createNoteInArchive(archive: Archive) {
        println("Введите заголовок заметки:")
        val title = scanner.nextLine().trim()
        if (title.isBlank()) {
            println("Заголовок заметки не может быть пустым. Попробуйте снова.")
            return
        }
        println("Введите текст заметки:")
        val content = scanner.nextLine().trim()
        if (content.isBlank()) {
            println("Текст заметки не может быть пустым. Попробуйте снова.")
            return
        }
        archive.createNote(title, content)
        println("Заметка \"$title\" добавлена в архив \"${archive.name}\".")
    }

    private fun openNote(note: Note) {
        println("\n--- Экран просмотра заметки ---")
        println("Заметка: ${note.title}")
        println("Содержимое: ${note.content}")
        println("-----------------------------")
        println("0. Вернуться назад")

        while (readInt() != 0) {
            println("Некорректный ввод. Повторите попытку.")
        }
    }

    private fun readInt(): Int {
        return try {
            scanner.nextLine().toInt()
        } catch (e: NumberFormatException) {
            -1
        }
    }
}