package com.vignesh.parseemailverification;

import android.app.Application;
import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("KyVPbUYT4ey6Soo6iZCe8lL7jPnbKUOf80Rsoj7w")
                // if defined
                .clientKey("HI1Zra3GfVe0jo9qDUh6n7wZR2og4cw2iSrjjxS5")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
