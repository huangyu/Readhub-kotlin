package com.huangyu.readhub.ui.main.model

import com.huangyu.readhub.data.network.ApiService
import com.huangyu.readhub.mvp.BaseModel
import javax.inject.Inject

/**
 * Created by huangyu on 2018/3/28.
 */
class MainModel @Inject internal constructor(private val service: ApiService) : BaseModel(), IMainModel {


}