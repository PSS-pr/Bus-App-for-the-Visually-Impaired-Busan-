package org.techtown.beebus;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.Window;
import android.widget.ProgressBar;

public class LodingDialog extends Dialog
{
    public LodingDialog(Context context)
    {
        super(context);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.loding_dialog);
        setCancelable(false);
    }
}
