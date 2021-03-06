package org.coepi.android.repo.reportsupdate

import org.coepi.android.R.drawable
import org.coepi.android.R.plurals
import org.coepi.android.R.string
import org.coepi.android.system.Resources
import org.coepi.android.system.intent.IntentKey.NOTIFICATION_INFECTION_ARGS
import org.coepi.android.system.intent.IntentNoValue
import org.coepi.android.system.log.log
import org.coepi.android.ui.notifications.AppNotificationChannels
import org.coepi.android.ui.notifications.NotificationConfig
import org.coepi.android.ui.notifications.NotificationIntentArgs
import org.coepi.android.ui.notifications.NotificationPriority.HIGH
import org.coepi.android.ui.notifications.NotificationsShower

interface NewAlertsNotificationShower {
    fun showNotification(newAlertsCount: Int)
}

class NewAlertsNotificationShowerImpl(
    private val notificationsShower: NotificationsShower,
    private val notificationChannelsInitializer: AppNotificationChannels,
    private val resources: Resources
) : NewAlertsNotificationShower {

    override fun showNotification(newAlertsCount: Int) {
        log.d("Showing notification...")
        notificationsShower.showNotification(notificationConfiguration(newAlertsCount))
    }

    private fun notificationConfiguration(newAlertsCount: Int): NotificationConfig =
        NotificationConfig(
            drawable.ic_launcher_foreground,
            resources.getString(string.infection_notification_title),
            resources.getQuantityString(plurals.alerts_new_notifications_count, newAlertsCount),
            HIGH,
            notificationChannelsInitializer.reportsChannelId,
            NotificationIntentArgs(NOTIFICATION_INFECTION_ARGS, IntentNoValue())
        )
}