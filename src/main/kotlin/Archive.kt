class Archive(val name: String) {
    val notes = mutableListOf<Note>()

    fun createNote(title: String, content: String) {
        notes.add(Note(title, content))
    }

    fun displayNotes() {
        println("Заметки в архиве \"$name\":")
        notes.forEachIndexed { index, note ->
            println("${index + 1}. ${note.title}")
        }
    }
}