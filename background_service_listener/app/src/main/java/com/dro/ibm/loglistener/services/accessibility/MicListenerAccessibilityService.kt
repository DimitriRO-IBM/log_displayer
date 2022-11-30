package com.dro.ibm.loglistener.services.accessibility

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import com.dro.ibm.loglistener.BuildConfig

class MicListenerAccessibilityService : AccessibilityService() {
    /**
     * Callback for [android.view.accessibility.AccessibilityEvent]s.
     *
     * @param event The new event. This event is owned by the caller and cannot be used after
     * this method returns. Services wishing to use the event after this method returns should
     * make a copy.
     */
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        val source: AccessibilityNodeInfo = event?.source ?: return
        if (BuildConfig.DEBUG) {
            Log.d("AccessibilityService :: ", source.toString())
        }
    }

    /**
     * Callback for interrupting the accessibility feedback.
     */
    override fun onInterrupt() {
        TODO("Not yet implemented")
    }
}
