/*
Copyright 2009-2014 Urban Airship Inc. All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this
list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice,
this list of conditions and the following disclaimer in the documentation
and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE URBAN AIRSHIP INC ``AS IS'' AND ANY EXPRESS OR
IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
EVENT SHALL URBAN AIRSHIP INC OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.urbanairship.push.sample;

import android.app.Application;
import android.support.v4.app.NotificationCompat;

import com.urbanairship.UAirship;
import com.urbanairship.push.notifications.DefaultNotificationFactory;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Optionally, customize your config at runtime:
        //
        // AirshipConfigOptions options = new AirshipConfigOptions();
        // options.inProduction = false;
        // options.developmentAppKey = "Your Development App Key";
        // options.developmentAppSecret "Your Development App Secret";
        //
        // UAirship.takeOff(this, options);

        UAirship.takeOff(this, new UAirship.OnReadyCallback() {
            @Override
            public void onAirshipReady(UAirship airship) {
                // Perform any airship configurations here

                // Create a customized default notification factory
                DefaultNotificationFactory defaultNotificationFactory = new DefaultNotificationFactory(getApplicationContext());
                defaultNotificationFactory.setSmallIconId(R.drawable.ic_notification);
                defaultNotificationFactory.setColor(NotificationCompat.COLOR_DEFAULT);

                // Set it
                airship.getPushManager().setNotificationFactory(defaultNotificationFactory);

                // Enable Push
                airship.getPushManager().setPushEnabled(true);
            }
        });
    }
}
