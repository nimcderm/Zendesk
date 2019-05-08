package com.example.motorinsurance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.zopim.android.sdk.api.ZopimChat;
import com.zopim.android.sdk.prechat.PreChatForm;
import com.zopim.android.sdk.prechat.ZopimChatActivity;
import zendesk.core.AnonymousIdentity;
import zendesk.core.Identity;
import zendesk.core.Zendesk;
import zendesk.support.Support;
import zendesk.support.guide.HelpCenterActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // build pre chat form config
        PreChatForm defaultPreChat = new PreChatForm.Builder()
                .name(PreChatForm.Field.OPTIONAL)
                .email(PreChatForm.Field.OPTIONAL)
                .phoneNumber(PreChatForm.Field.OPTIONAL)
                .department(PreChatForm.Field.OPTIONAL)
                .message(PreChatForm.Field.OPTIONAL)
                .build();
        /** initialize Chat*/
        ZopimChat.init("uGq8A1cGRHMQh9bC6KMHCgGLTRRoxGkD")
                .preChatForm(defaultPreChat)
                .department("A department")
                .tags("tag1", "tag2")
                .build();

        /** Chat Button Event Listener*/
        Button mChatButton;
        mChatButton = (Button) findViewById(R.id.chat_button);
        mChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), ZopimChatActivity.class));
            }
        });



        /**Adding the initialization code to the application*/
        Zendesk.INSTANCE.init(this, "https://nimcderm.zendesk.com",
                "d860769f63a2ca91dbff4fd9b3fd4d32cc23b07464394b15",
                "mobile_sdk_client_4db1a63e7f12e3ecd346");

        /**Adding a user identity*/
        Identity identity = new AnonymousIdentity();
        Zendesk.INSTANCE.setIdentity(identity);

        Support.INSTANCE.init(Zendesk.INSTANCE);
        /**End SDK initialization and identity*/


        /** Help Center Button Event Listener*/
        Button helpCenterButton = (Button) findViewById(R.id.help_button);
        helpCenterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                HelpCenterActivity.builder()
                        .show(MainActivity.this);
            }
        });
    }
}
