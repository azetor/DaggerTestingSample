package com.mobilemonkeysoftware.api

import it.cosenonjaviste.daggermock.DaggerMock

/**
 * Created by AR on 11/03/2018.
 */
fun daggerMockRule() = DaggerMock.rule<TestComponent>(ApiModule()) {
}