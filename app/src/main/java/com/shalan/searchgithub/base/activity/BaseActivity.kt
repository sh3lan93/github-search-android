package com.shalan.searchgithub.base.activity

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import com.shalan.searchgithub.base.network.errorhandling.CommonErrors
import com.shalan.searchgithub.base.states.CommonStatusImp
import com.shalan.searchgithub.base.states.IResult
import com.shalan.searchgithub.base.viewmodel.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import kotlin.reflect.KClass

abstract class BaseActivity<Binding : ViewDataBinding, ViewModel : BaseViewModel>(
    @LayoutRes override val layoutId: Int,
    clazz: KClass<ViewModel>,
    vararg viewModelParams: Any = emptyArray()
) :
    AppCompatActivity(), IActivity {


    protected val viewmodel: ViewModel by viewModel(clazz = clazz, parameters = {
        parametersOf(viewModelParams)
    })

    protected lateinit var binding: Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        lifecycle.addObserver(viewmodel)
        viewmodel.startLogic()
        onCreateInit(savedInstanceState)
    }

    fun <T> LiveData<IResult<T>>.consumeState(
        onLoading: () -> Unit = {},
        onSuccess: (data: T?) -> Unit = {},
        onError: (error: CommonErrors?) -> Unit = {}
    ) {
        this.observe(this@BaseActivity) { result ->
            when (result.whichStatus()) {
                CommonStatusImp.LOADING -> onLoading.invoke()
                CommonStatusImp.SUCCESS -> onSuccess.invoke(result.fetchData())
                CommonStatusImp.ERROR -> onError.invoke(result.fetchError())
            }
        }
    }

    fun View.hideKeyboard(activity: Activity) {
        WindowInsetsControllerCompat(activity.window, this).apply {
            hide(WindowInsetsCompat.Type.ime())
        }
    }
}