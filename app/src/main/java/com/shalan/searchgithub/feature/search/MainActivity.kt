package com.shalan.searchgithub.feature.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.shalan.searchgithub.R
import com.shalan.searchgithub.base.activity.BaseActivity
import com.shalan.searchgithub.databinding.ActivityMainBinding

class MainActivity :
    BaseActivity<ActivityMainBinding, MainViewModel>(
        layoutId = R.layout.activity_main, MainViewModel::class
    ) {


    private val adapter by lazy {
        SearchResultAdapter()
    }

    private fun openGithubWebFlowForAuthorization() {
        GithubAuthenticationBuilder.buildAuthUri()
            .also { authUrl ->
                openBrowser(authUrl)
            }
    }

    private fun openBrowser(uri: Uri) {
        Intent(Intent.ACTION_VIEW).apply {
            data = uri
        }.also {
            startActivity(it)
        }
    }

    override fun onCreateInit(savedInstance: Bundle?) {
        binding.rvResult.adapter = adapter
        observeResult()
        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            binding.etSearch.hideKeyboard(this)
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (binding.etSearch.text.toString().isNotEmpty()) {
                    viewmodel.search(binding.etSearch.text.toString())
                    return@setOnEditorActionListener true
                }
            }
            false
        }
    }

    private fun observeResult() {
        viewmodel._isUserAuthenticatedResult.observe(this) {
            if (it.not())
                openGithubWebFlowForAuthorization()
        }

        viewmodel._authorizeUserResult.consumeState(onLoading = {
            showAuthorizationLoader()
        }, onError = {
            hideAuthorizationLoader()
            showToast(getString(R.string.unknown_error))
        }, onSuccess = {
            hideAuthorizationLoader()
        })

        viewmodel._searchResult.consumeState(onLoading = {
            binding.etSearch.isEnabled = false
        }, onSuccess = {
            binding.etSearch.isEnabled = true
            adapter.submitList(it)
        }, onError = {
            binding.etSearch.isEnabled = true
            showToast(getString(R.string.unknown_error))
        })
    }

    private fun showAuthorizationLoader() {
        binding.rvResult.visibility = GONE
        binding.etSearch.visibility = GONE
        binding.loader.visibility = VISIBLE
    }

    private fun hideAuthorizationLoader() {
        binding.rvResult.visibility = VISIBLE
        binding.etSearch.visibility = VISIBLE
        binding.loader.visibility = GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.data?.getQueryParameter(GithubAuthenticationBuilder.CODE_PARAM_KEY)?.let { code ->
            viewmodel.authorizeUser(code)
        }
    }
}