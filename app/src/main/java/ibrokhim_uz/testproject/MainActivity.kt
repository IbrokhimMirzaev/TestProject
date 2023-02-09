package ibrokhim_uz.testproject

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.marginEnd
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import ibrokhim_uz.testproject.model.TestModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var tests = arrayListOf<TestModel>()
    var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tests.add(TestModel("6 + 6 / 6", "6", "7", "10", "15", "7"))
        tests.add(TestModel("8 * 2 - 9", "12", "7", "100", "24", "7"))
        tests.add(TestModel("10 + 9 - 12", "14", "7", "17", "31", "7"))
        tests.add(TestModel("7 + 7 - 7 * 7 / 7", "16", "7", "21", "4", "7"))

        createTest(index)
        createNumber(tests.size)

        answers.setOnCheckedChangeListener { radioGroup, checkedIndex ->
            if (checkedIndex != -1) {
                tests[index].status = true
                tests[index].chosenAnswerIndex = checkedIndex
            }
        }

        next.setOnClickListener {
            if (index < tests.size - 1) {
                index++
            }

            createTest(index)
        }
    }

    fun createTest(n: Int) {
        val test = tests[n]

        question.text = test.question
        answer_1.text = test.answer1
        answer_2.text = test.answer2
        answer_3.text = test.answer3
        answer_4.text = test.answer4

        println("STATUS: ${tests[n].status}")
        println("CHOSEN INDEX: ${tests[n].chosenAnswerIndex}")
        if (tests[n].status) {
            val radioButton = findViewById<RadioButton>(tests[n].chosenAnswerIndex)
            radioButton.isChecked = true
        } else {
            answers.clearCheck()
        }
    }

    private fun createNumber(n: Int) {
        for (i in 0 until n) {
            val btn = Button(this)
            btn.id = i
            btn.text = "${i + 1}"
            btn.tag = "$i"
            btn.setOnClickListener(this)
            if (tests[i].status){
                btn.setBackgroundColor(Color.GREEN)
            }
            questions_number.addView(btn)
        }
    }

    override fun onClick(p0: View?) {
        val btn = findViewById<Button>(p0!!.id)
        index = btn.tag.toString().toInt()
        createTest(index)
    }
}