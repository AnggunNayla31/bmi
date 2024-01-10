package com.smktelkommlg.www.tugas1

    import android.annotation.SuppressLint
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.widget.Button
    import android.widget.EditText
    import android.widget.RadioGroup
    import android.widget.TextView

    class MainActivity : AppCompatActivity() {

    private var gender:String="Laki-laki"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Inisialisasi widget
        val buttonCalculate = findViewById<Button>(R.id.buttonCalculate)
        val buttonReset = findViewById<Button>(R.id.buttonReset)
        val editTextHeight = findViewById<EditText>(R.id.editTextHeight)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)
        val radioGroupGender = findViewById<RadioGroup>(R.id.radioGroupGender)
        val editTextWeight = findViewById<EditText>(R.id.editTextWeight)
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextAlamat = findViewById<EditText>(R.id.editTextAlamat)

        buttonCalculate.setOnClickListener{
            calculateBMI(editTextHeight, textViewResult, radioGroupGender, editTextWeight, editTextName, editTextAlamat)
        }
    }

     private fun calculateBMI(
         editTextHeight: EditText,
         textViewResult: TextView,
         radioGroupGender: RadioGroup,
         editTextWeight: EditText,
         editTextName: EditText,
         editTextAlamat:EditText
     )

     {
         val Name = editTextName.text.toString()
         val Alamat = editTextAlamat.text.toString()
         val height = editTextHeight.text.toString().toDouble()
         val weight = editTextWeight.text.toString().toDouble()

        // Memperoleh ID RadioButton yang dipilih
        val selectedGenderId = radioGroupGender.checkedRadioButtonId

        // Memeriksa jenis kelamin yang dipilih
        gender = when (selectedGenderId) {
            R.id.radioButtonMale -> "Laki-laki"
            R.id.radioButtonFemale -> "Perempuan"
            else -> "Laki-laki"

    }
         // Menghitung BMI berdasarkan jenis kelamin
         val bmi = when (gender){
             "Laki-laki" -> weight/((height/100)*(height/100))
             "Perempuan" -> weight/((height/100)*(height/100))*0.9//Faktor koreksi untuk perempuan
             else -> 0.0
         }

         val result = when {
             bmi < 18.5 -> "Berat badan kurang"
             bmi >= 18.5 && bmi < 24.9 -> "Berat badan normal"
             bmi >= 25 && bmi < 29.9 -> "Berat badan berlebih"
             else -> "Obesitas"
         }

        textViewResult.text="BMI:%.2f\n$result".format(bmi)
     }
}