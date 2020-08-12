package com.llaerto.keepmymoney.base

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar


abstract class BaseFragment<VS, VA, VM, VB> : Fragment()
        where VS : BaseViewState,
              VA : BaseViewAction,
              VM : BaseViewModel<VS, VA>,
              VB : ViewBinding {

    abstract val viewModel: VM
    abstract var _binding: VB?

    open val progressBar: View? = null

    protected val binding get() = _binding!!
    protected var appliedState: VS? = null

    //region open methods

    abstract fun applyViewState(viewState: VS)

    open fun applyViewAction(actionState: VA) {
        //override if need to handle some toast/snack/etc
    }

    //endregion

    //region fragment methods

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat
            .setOnApplyWindowInsetsListener(view) { view, insets ->
                view.updatePadding(
                    top = insets.systemWindowInsetTop,
                    bottom = insets.systemWindowInsetBottom
                )
                insets
            }
        viewModel.stateData.observe(viewLifecycleOwner, Observer<VS> {
            if (it != appliedState) {
                applyViewState(it)
            }
        })
        viewModel.actionData.observe(viewLifecycleOwner, Observer<VA> {
            applyViewAction(it)
        })
        viewModel.progressBarData.observe(viewLifecycleOwner, Observer {
            progressBar?.visibility = if (it) View.VISIBLE else View.GONE
        })
        viewModel.navigationData.observe(viewLifecycleOwner, Observer {
            navigate(it)
        })
        viewModel.errorSnackBarData.observe(viewLifecycleOwner, Observer { text ->
            text?.let {
                Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show()
                viewModel.onSnackBarShown()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //endregion

    //region notifications

    fun showSnack(view: View, textRes: Int, actionTextRes: Int, action: (v: View) -> Unit) {
        Snackbar.make(view, textRes, Snackbar.LENGTH_LONG).setAction(actionTextRes, action).show()
    }

    fun showAlert(
        titleResId: Int? = null,
        messageResId: Int,
        positiveButtonResId: Int? = null,
        negativeButtonResId: Int? = null,
        positiveListener: (dialog: DialogInterface, which: Int?) -> Unit = { _, _ -> },
        negativeListener: (dialog: DialogInterface, which: Int?) -> Unit = { _, _ -> }
    ) {
        context?.let {
            val builder = AlertDialog.Builder(it)
            with(builder) {
                titleResId?.let {
                    setTitle(it)
                }
                setMessage(messageResId)
                positiveButtonResId?.let {
                    setPositiveButton(it, positiveListener)
                }
                negativeButtonResId?.let {
                    setNegativeButton(it, negativeListener)
                }
                builder.create().show()
            }
        }
    }

    //endregion

    //region private methods

    private fun navigate(navigationAction: NavigationActions) {
        when (navigationAction) {
            is NavigationActions.NavigationAction -> findNavController().navigate(navigationAction.destination, navigationAction.navOptions)
            is NavigationActions.PopBackStackAction -> findNavController().popBackStack()
        }
    }

    private fun <T : ViewModel> T.createFactory(): ViewModelProvider.Factory {
        val viewModel = this
        return object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T = viewModel as T
        }
    }

    //endregion


}