package com.mobilemonkeysoftware.ui

import com.mobilemonkeysoftware.api.ApiModule
import it.cosenonjaviste.daggermock.DaggerMock


/**
 * Created by AR on 12/03/2018.
 */
internal fun daggerMockRule() = DaggerMock.rule<TestComponent>(TestModule(), UiContractModule(), ApiModule()) {
}
