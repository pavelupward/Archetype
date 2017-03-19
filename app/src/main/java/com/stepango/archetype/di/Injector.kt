package com.stepango.archetype.di

import android.util.SparseArray
import com.stepango.archetype.R
import com.stepango.archetype.action.ActionHandler
import com.stepango.archetype.action.ContextAction
import com.stepango.archetype.action.MainActionHandler
import com.stepango.archetype.player.actions.IdleAction

val Any.injector: Injector by lazy { InjectorImpl() }

inline fun <T> lazyInject(crossinline block: Injector.() -> T): Lazy<T> = lazy { Injector().block() }

interface Injector {
    fun mainActionHandler(): ActionHandler

    companion object {
        operator fun invoke(): Injector = injector
    }
}

class InjectorImpl : Injector {
    override fun mainActionHandler(): ActionHandler = MainActionHandler(actions)
}

val actions = SparseArray<ContextAction>().apply {
    put(R.id.action_idle, IdleAction())
}