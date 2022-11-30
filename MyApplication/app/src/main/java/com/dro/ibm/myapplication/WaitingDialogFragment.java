package com.dro.ibm.myapplication;

/**
 * Created by didiercoulon on 2020-10-13.
 */
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

/**
 * Class used to handle a waiting dialog fragment which displays the following items :
 * - A message that informs the user on the action in progress.
 * - A progress bar.
 */
public class WaitingDialogFragment extends DialogFragment {

    /**
     * Key constant used to pass parameter to dialog fragment.
     */
    private static final String KEY_MESSAGE = "message";

    /**
     * Constants used in dialog fragment size setting.
     */
    private static final double PORTRAIT_WIDTH_RATIO = 0.75;
    private static final double LANDSCAPE_WIDTH_RATIO = 0.4;
    private static final int DIALOG_FRAGMENT_HEIGHT = 300;

    /**
     * Member.
     */
    private String mMessage;

    /**
     * As recommended for this Android version of DialogFragment
     * (android.support.v4.app.DialogFragment) :
     * - Use an empty constructor.
     * - Pass parameters provided by the parent activity to the DialogFragment
     *   using setArguments(Bundle) method.
     */
    public WaitingDialogFragment() {}

    public static WaitingDialogFragment newInstance(String message) {
        WaitingDialogFragment waitingDialogFragment = new WaitingDialogFragment();

        Bundle bundle = new Bundle();
        bundle.putString(KEY_MESSAGE, message);
        waitingDialogFragment.setArguments(bundle);

        return waitingDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set class member with parameter value provided by the parent activity.
        mMessage = getArguments().getString(KEY_MESSAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View waitingView = inflater.inflate(R.layout.dialogfragment_waiting, container, false);

        // Set message.
        TextView messageTextView = waitingView.findViewById(R.id.message);
        messageTextView.setText(mMessage);

        // Set progress bar appearance.
        ProgressBar progressBar = waitingView.findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);

        return waitingView;
    }

    @Override
    public void onResume() {
        super.onResume();

        Window window = getDialog().getWindow();

        // As corners are rounded, background of the dialog fragment window must be transparent
        // otherwise white color appears in corners.
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // Get device screen width.
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int deviceScreenWidth = displayMetrics.widthPixels;

        // Get device orientation.
        int deviceOrientation = getResources().getConfiguration().orientation;

        int dialogFragmentWidth;

        // Set dialog fragment width in function of orientation.
        if (deviceOrientation == Configuration.ORIENTATION_PORTRAIT) {
            dialogFragmentWidth = (int) (deviceScreenWidth * PORTRAIT_WIDTH_RATIO);
        } else {
            dialogFragmentWidth = (int) (deviceScreenWidth * LANDSCAPE_WIDTH_RATIO);
        }

        // Set dialog fragment width.
        window.setLayout(dialogFragmentWidth, DIALOG_FRAGMENT_HEIGHT);
    }

    public void changeText(String newText) {
        // Set message.
        TextView messageTextView = getDialog().findViewById(R.id.message);
        messageTextView.setText(newText);
    }
}