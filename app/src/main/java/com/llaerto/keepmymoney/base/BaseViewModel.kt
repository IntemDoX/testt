package com.llaerto.keepmymoney.base

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.llaerto.keepmymoney.utils.SingleLiveData
import com.llaerto.keepmymoney.utils.handleException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseViewModel<VS, VA> : ViewModel()
        where VS : BaseViewState, VA : BaseViewAction {

    abstract fun initialState(): VS

    //region live datas

    val stateData: MutableLiveData<VS> = MutableLiveData()

    val actionData: SingleLiveData<VA> = SingleLiveData()

    val errorSnackBarData: SingleLiveData<Int> = SingleLiveData()

    val progressBarData: SingleLiveData<Boolean> = SingleLiveData()

    val navigationData: SingleLiveData<NavigationActions> = SingleLiveData()

    //endregion

    init {
        stateData.value = initialState()
    }

    //region protected methods

    protected fun updateState(state: VS) {
        stateData.value = state
    }

    protected fun postAction(state: VA) {
        actionData.value = state
    }

    protected fun showGenericError(@StringRes errorMessage: Int) {
        errorSnackBarData.value = errorMessage
    }

    protected fun navigate(navigationAction: NavigationActions) {
        navigationData.value = navigationAction
    }

    protected fun asyncLaunch(
        isShowProgress: Boolean = true,
        doAsync: suspend () -> Unit,
        doOnError: (Throwable) -> Unit = {
            onHandleError(it.handleException())
        }
    ): Job {
        return viewModelScope.launch {
            try {
                progressBarData.value = isShowProgress
                doAsync()
            } catch (error: Throwable) {
                if (error !is CancellationException) {
                    doOnError(error)
                }
            } finally {
                progressBarData.value = false
            }
        }
    }

    //endregion

    fun onSnackBarShown() {
        errorSnackBarData.value = null
    }

    open fun onHandleError(throwable: Throwable) {
        //override to handle model layer errors here
        throwable.message?.let {
            Log.e("BaseViewModel", it)
        } ?: throwable.printStackTrace()
    }
}