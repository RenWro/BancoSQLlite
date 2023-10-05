package com.example.classes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.release.gfg1.DBHelper
import java.sql.DriverManager
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.view.get

class MainActivity : AppCompatActivity() {
    lateinit var nomeText: EditText

    lateinit var cpfText: EditText
    lateinit var enviarbutton: Button
    lateinit var  Lista: ListView
    lateinit var nomeView: TextView
    lateinit var deletar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nomeText = findViewById(R.id.nome) as EditText
        Lista = findViewById(R.id.lista) as ListView
        cpfText = findViewById(R.id.cpf) as EditText
        deletar = findViewById(R.id.delete) as Button

        enviarbutton = findViewById(R.id.converterButton) as Button

        var arrayAdapter: ArrayAdapter<*>
        var users = arrayListOf<Any>()
        var codig = arrayListOf<Any>()



        //var A = aluno(nomeText.toString(),endText.toString(),cpfText.toString(), sexo)
        enviarbutton.setOnClickListener {

            val db = DBHelper(this, null)

            var name = nomeText.text.toString()
            var age = cpfText.text.toString()
            db.addName(name, age)
            Toast.makeText(this, name + " added to database", Toast.LENGTH_LONG).show()
            nomeText.text.clear()
            cpfText.text.clear()


            //mostrar()
        }

        val db = DBHelper(this, null)
        val cursor = db.getName()

/*

            cursor!!.moveToFirst()
            cursor.getString(1)




            users = arrayListOf(

                "Nome:" + cursor.getString(1) + " CPF: " + cursor.getString(2)

            )
            codig.add(cursor.getString(2))
            while (cursor.moveToNext()) {
                users.add(

                    "Nome:" + cursor.getString(1) + " CPF: " + cursor.getString(2)

                )
                codig.add(cursor.getString(2))
            }
            var arrayAdapte: ArrayAdapter<*>
            arrayAdapte = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1, users
            )
            Lista.adapter = arrayAdapte
*/


        Lista.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, ind: Long ->


            var num  = codig[position]

            val cursor2 = db.getDados(num.toString())
            cursor2!!.moveToFirst()
           nomeText.setText(cursor2.getString(1).toString())
            cpfText.setText(cursor2.getString(2).toString())
            Toast.makeText(
                applicationContext,
                "Position :$num", Toast.LENGTH_LONG
            )
                .show()

        }

deletar.setOnClickListener {

    db.deletetodosDados()
    Toast.makeText(
        applicationContext,
        "Deletados", Toast.LENGTH_LONG
    )
        .show()
}


    }
}














