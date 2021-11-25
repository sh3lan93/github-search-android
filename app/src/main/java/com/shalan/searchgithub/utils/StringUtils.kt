package com.shalan.searchgithub.utils


fun String.getAccessToken() = this.split("&").first().split("=").last()