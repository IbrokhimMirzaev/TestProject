package ibrokhim_uz.testproject

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import ibrokhim_uz.testproject.model.TestModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var tests = arrayListOf<TestModel>()
    var index = 0
    var numberOfCorrectAnswers: Int = 0
    var count: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tests.add(TestModel("6 + 6 / 6", "6", "7", "10", "15", "7"))
        tests.add(TestModel("8 * 2 - 9", "12", "7", "100", "24", "7"))
        tests.add(TestModel("10 + 9 - 12", "14", "7", "17", "31", "7"))
        tests.add(TestModel("7 + 7 - 7 * 7 / 7", "16", "7", "21", "4", "7"))

        createTest(index)
        createNumber(tests.size)

        next.setOnClickListener {
            if (index == tests.size - 2) {
                next.text = "Finish"
            }

            check()

            if (index < tests.size - 1) {
                index++
            } else {
                Toast.makeText(this, "$numberOfCorrectAnswers ta topdingiz", Toast.LENGTH_LONG).show()
            }

            createTest(index)
            createNumber(tests.size)
        }
    }

    fun createTest(n: Int) {
        var test = tests[n]

        question.text = test.question
        answer_1.text = test.answer1
        answer_2.text = test.answer2
        answer_3.text = test.answer3
        answer_4.text = test.answer4

        if (tests[n].status) {
            var radioButton = findViewById<RadioButton>(tests[n].chosenAnswerIndex)
            radioButton.isChecked = true
        } else {
            answers.clearCheck()
        }
    }

    private fun createNumber(n: Int) {
        questions_number.removeAllViews()
        for (i in 0 until n) {
            var btn = Button(this)
            btn.id = i
            btn.text = "${i + 1}"
            btn.tag = "$i"
            if (tests[i].status) {
                btn.text = "✓"
                btn.setTextColor(Color.GREEN)
                btn.textSize = 20F
            }
            btn.setOnClickListener(this)
            questions_number.addView(btn)
        }
    }

    override fun onClick(p0: View?) {
        val btn = findViewById<Button>(p0!!.id)

        check()

        index = btn.tag.toString().toInt()

        if (index == tests.size - 1) {
            next.text = "Finish"
        } else {
            next.text = "next"
        }

        createTest(index)
        createNumber(tests.size)
    }

    private fun check() {
        if (answers.checkedRadioButtonId != -1) {
            tests[index].status = true
            tests[index].chosenAnswerIndex = answers.checkedRadioButtonId

            var radioButton = findViewById<RadioButton>(tests[index].chosenAnswerIndex)
            var chosenVariantText = radioButton.text

            if (chosenVariantText.equals(tests[index].correct_answer) && !count && index <= tests.size - 1) {
                if (index == tests.size - 1) {
                    count = true
                }
                numberOfCorrectAnswers++
            }
        }
    }
}