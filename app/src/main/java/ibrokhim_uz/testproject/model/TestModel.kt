package ibrokhim_uz.testproject.model

data class TestModel(
    var question: String,
    var answer1: String,
    var answer2: String,
    var answer3: String,
    var answer4: String,
    var correct_answer: String,
    var status: Boolean = false,
    var chosenAnswerIndex: Int = -1,
)