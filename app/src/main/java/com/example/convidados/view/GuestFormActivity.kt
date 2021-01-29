package com.example.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.R
import com.example.convidados.service.constants.GuestConstants
import com.example.convidados.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: GuestFormViewModel
    private var mGuestId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListeners()
        observe()
        loadData()

        findViewById<RadioButton>(R.id.radio_presence).isChecked = true
    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.button_save) {

            val name = findViewById<EditText>(R.id.edit_name).text.toString()
            val presence = findViewById<RadioButton>(R.id.radio_presence).isChecked
            mViewModel.save(mGuestId, name, presence)


        }
    }

    private fun loadData(){
        val bundle = intent.extras
        if (bundle != null){
             mGuestId = bundle.getInt(GuestConstants.GUESTID)
            mViewModel.load(mGuestId)
        }
    }

    private fun observe(){
        mViewModel.saveGuest.observe(this, Observer {
            if (it){
                Toast.makeText(applicationContext, "Sucesso", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext, "Falha", Toast.LENGTH_SHORT).show()
            }
            finish()
        })

        mViewModel.guest.observe(this, Observer {
            findViewById<EditText>(R.id.edit_name).setText(it.name)
            if (it.presence){
                findViewById<RadioButton>(R.id.radio_presence).isChecked = true
            }else{
                findViewById<RadioButton>(R.id.radio_absents).isChecked = true
            }
        })
    }

    private fun setListeners() {
        val myButton: Button = findViewById(R.id.button_save)
        myButton.setOnClickListener(this)
    }


}