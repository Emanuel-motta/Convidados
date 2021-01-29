package com.example.convidados.service.repository

import android.content.ContentValues
import android.content.Context
import com.example.convidados.service.constants.DataBaseConstants
import com.example.convidados.service.model.GuestModel
import java.lang.Exception

class GuestRepository (context: Context) {
    /**
     * Database acess
     */
    private val mDataBase = GuestDataBase.getDatabase(context).guestDAO()

    /**
     * load guests
     */
    fun get(id: Int): GuestModel {
        return mDataBase.get(id)
    }

    /**
     * insert guests
     */
    fun save(guest: GuestModel): Boolean {
        return mDataBase.save(guest) > 0
    }

    /**
     * list of all guests
     */
    fun getAll(): List<GuestModel> {
        return mDataBase.getInvited()
    }

    /**
     * list of all present guests
     */
    fun getPresent(): List<GuestModel> {
        return mDataBase.getPresent()
    }

    /**
     * list of all absent guests
     */
    fun getAbsents(): List<GuestModel> {
        return mDataBase.getAbsent()
    }

    /**
     * update guests
     */
    fun update(guest: GuestModel): Boolean {
        return mDataBase.update(guest) > 1
    }

    /**
     * delete guests
     */
    fun delete(guest: GuestModel) {
        mDataBase.delete(guest)
    }
}