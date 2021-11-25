package com.shalan.searchgithub.base.activity

import android.os.Bundle

interface IActivity {

    val layoutId: Int

    fun onCreateInit(savedInstance: Bundle?)

}
