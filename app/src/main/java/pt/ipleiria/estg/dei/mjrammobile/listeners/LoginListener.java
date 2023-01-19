package pt.ipleiria.estg.dei.mjrammobile.listeners;

import android.content.Context;

public interface LoginListener {
    void onValidateLogin(final String token, final String username, final Context context);
}
