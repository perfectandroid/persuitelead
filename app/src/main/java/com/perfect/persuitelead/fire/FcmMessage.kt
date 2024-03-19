
package com.perfect.persuitelead.fire

data class FcmMessage(val to: String, // Device token or topic
                      val data: Map<String, String>,
                      val notification: NotificationPayload)
