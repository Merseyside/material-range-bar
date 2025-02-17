
package com.appyvet.rangebarsample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.appyvet.rangebar.RangeBar;
import com.appyvet.rangebarsample.colorpicker.ColorPickerDialog;
import com.appyvet.rangebarsample.colorpicker.Utils;

public class MainActivity extends Activity implements
        ColorPickerDialog.OnColorSelectedListener {

    // Sets the initial values such that the image will be drawn
    private static final int INDIGO_500 = 0xff3f51b5;

    // Sets variables to save the colors of each attribute
    private int mBarColor;

    private int mConnectingLineColor;

    private int mPinColor;
    private int mTextColor;

    private int mTickColor;

    // Initializes the RangeBar in the application
    private RangeBar rangebar;

    private int mThumbColor;

    private int mThumbBoundaryColor;

    private int mTickLabelColor;

    private int mTickLabelSelectedColor;

    // Saves the state upon rotating the screen/restarting the activity
    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("BAR_COLOR", mBarColor);
        bundle.putInt("CONNECTING_LINE_COLOR", mConnectingLineColor);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Removes title bar and sets content view
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        // Sets fonts for all
//        Typeface font = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");
////        ViewGroup root = (ViewGroup) findViewById(R.id.mylayout);
////        setFont(root, font);

        // Gets the buttons references for the buttons
        final TextView barColor = findViewById(R.id.barColor);
        final TextView thumbBoundaryColor = findViewById(R.id.thumbBoundaryColor);
        final TextView connectingLineColor = findViewById(R.id.connectingLineColor);
        final TextView pinColor = findViewById(R.id.pinColor);
        final TextView pinTextColor = findViewById(R.id.textColor);
        final TextView tickColor = findViewById(R.id.tickColor);
        final TextView thumbColor = findViewById(R.id.thumbColor);
        final TextView rangeButton = findViewById(R.id.enableRange);
        final TextView disabledButton = findViewById(R.id.disable);
        final TextView tickBottomLabelsButton = findViewById(R.id.toggleTickBottomLabels);
        final TextView tickTopLabelsButton = findViewById(R.id.toggleTickTopLabels);
        final TextView tickLabelColor = findViewById(R.id.tickLabelColor);
        final TextView tickLabelSelectedColor = findViewById(R.id.tickLabelSelectColor);

        final TextView tvLeftIndex = findViewById(R.id.tvLeftIndex);
        final TextView tvRightIndex = findViewById(R.id.tvRightIndex);
        final TextView tvLeftValue = findViewById(R.id.tvLeftValue);
        final TextView tvRightValue = findViewById(R.id.tvRightValue);


        // Gets the RangeBar
        rangebar = findViewById(R.id.rangebar1);

        rangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rangebar.setRangeBarEnabled(!rangebar.isRangeBar());
            }
        });
        disabledButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rangebar.setEnabled(!rangebar.isEnabled());
            }
        });

        // Sets the display values of the indices
        rangebar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex,
                                              int rightPinIndex, String leftPinValue, String rightPinValue) {

                tvLeftIndex.setText(String.format("Left Index %d", leftPinIndex));
                tvRightIndex.setText(String.format("Right Index %d", rightPinIndex));

                tvLeftValue.setText(String.format("Left Value %s", leftPinValue));
                tvRightValue.setText(String.format("Right Value %s", rightPinValue));
            }

            @Override
            public void onTouchEnded(RangeBar rangeBar) {
                Log.d("RangeBar", "Touch ended");
            }

            @Override
            public void onTouchStarted(RangeBar rangeBar) {
                Log.d("RangeBar", "Touch started");
            }
        });

        // Setting Number Attributes -------------------------------

        // Sets tickStart
        final TextView tickStart = findViewById(R.id.tickStart);
        SeekBar tickStartSeek = findViewById(R.id.tickStartSeek);
        tickStartSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar tickCountSeek, int progress, boolean fromUser) {
                try {
                    rangebar.setTickStart(progress);
                } catch (IllegalArgumentException e) {
                }
                tickStart.setText("tickStart = " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Sets tickEnd
        final TextView tickEnd = findViewById(R.id.tickEnd);
        SeekBar tickEndSeek = findViewById(R.id.tickEndSeek);
        tickEndSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar tickCountSeek, int progress, boolean fromUser) {
                try {
                    rangebar.setTickEnd(progress);
                } catch (IllegalArgumentException e) {
                }
                tickEnd.setText("tickEnd = " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Sets tickInterval
        final TextView tickInterval = findViewById(R.id.tickInterval);
        SeekBar tickIntervalSeek = findViewById(R.id.tickIntervalSeek);
        tickIntervalSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar tickCountSeek, int progress, boolean fromUser) {
                try {
                    rangebar.setTickInterval(progress);
                } catch (IllegalArgumentException e) {
                }
                tickInterval.setText("tickInterval = " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Sets Minimum Thumb Distance
        final TextView minThumbDistance = findViewById(R.id.minThumbDistance);
        SeekBar minThumbDistanceSeek = findViewById(R.id.minThumbDistanceSeek);
        minThumbDistanceSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar tickCountSeek, int progress, boolean fromUser) {
                // We want minimum value to be -1 instead of 0 (For ignoring the thumb distance) So that is why we subtract -1
                int newProgress = progress - 1;
                try {
                    rangebar.setMinimumThumbDistance(newProgress);
                } catch (IllegalArgumentException e) {
                }
                minThumbDistance.setText("Min Thumb Distance = " + newProgress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Sets barWeight
        final TextView barWeight = findViewById(R.id.barWeight);
        SeekBar barWeightSeek = findViewById(R.id.barWeightSeek);
        barWeightSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar barWeightSeek, int progress, boolean fromUser) {
                rangebar.setBarWeight(convertDpToPixel(progress, MainActivity.this));
                barWeight.setText(String.format("barWeight = %ddp", progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Sets connectingLineWeight
        final TextView connectingLineWeight = findViewById(R.id.connectingLineWeight);
        SeekBar connectingLineWeightSeek = findViewById(R.id.connectingLineWeightSeek);
        connectingLineWeightSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar connectingLineWeightSeek, int progress,
                                          boolean fromUser) {
                rangebar.setConnectingLineWeight(convertDpToPixel(progress, MainActivity.this));
                connectingLineWeight.setText(String.format("connectingLineWeight = %ddp", progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Sets Pin radius
        final TextView pinRadius = findViewById(R.id.pinSize);
        SeekBar pinRadiusSeek = findViewById(R.id.pinSizeSeek);
        pinRadiusSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar thumbRadiusSeek, int progress, boolean fromUser) {
                rangebar.setPinRadius(convertDpToPixel(progress, MainActivity.this));
                pinRadius.setText(String.format("Pin Size = %ddp", progress));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Sets Thumb boundary Radius
        final TextView thumbBoundarySize = findViewById(R.id.thumbBoundarySize);
        SeekBar thumbBoundarySeek = findViewById(R.id.thumbBoundarySeek);
        thumbBoundarySeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar thumbRadiusSeek, int progress, boolean fromUser) {

                rangebar.setThumbBoundarySize(convertDpToPixel(progress, MainActivity.this));
                thumbBoundarySize.setText(String.format("Thumb Boundary Size = %ddp", progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Setting Color Attributes---------------------------------

        // Sets barColor
        barColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                initColorPicker(Component.BAR_COLOR, mBarColor, mBarColor);
            }
        });

        // Set tickLabelColor
        tickLabelColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                initColorPicker(Component.TICK_LABEL_COLOR, mTickLabelColor, mTickLabelColor);
            }
        });

        // Set tickLabelSelectedColor
        tickLabelSelectedColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                initColorPicker(Component.TICK_LABEL_SELECTED_COLOR, mTickLabelSelectedColor, mTickLabelSelectedColor);
            }
        });

        // Sets connectingLineColor
        connectingLineColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                initColorPicker(Component.CONNECTING_LINE_COLOR, mConnectingLineColor,
                        mConnectingLineColor);
            }
        });

        // Sets pinColor
        pinColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                initColorPicker(Component.PIN_COLOR, mPinColor, mPinColor);
            }
        });
        // Sets pinTextColor
        pinTextColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                initColorPicker(Component.TEXT_COLOR, mTextColor, mTextColor);
            }
        });
        // Sets tickColor
        tickColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                initColorPicker(Component.TICK_COLOR, mTickColor, mTickColor);
            }
        });
        // Sets thumbColor
        thumbColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                initColorPicker(Component.THUMB_COLOR, mThumbColor, mThumbColor);
            }
        });

        thumbBoundaryColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initColorPicker(Component.THUMB_BOUNDARY_COLOR, mThumbBoundaryColor, mThumbBoundaryColor);
            }
        });

        CheckBox cbRoundedBar = findViewById(R.id.cbRoundedBar);
        cbRoundedBar.setChecked(rangebar.isBarRounded());
        cbRoundedBar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                rangebar.setBarRounded(isChecked);
            }
        });

        tickTopLabelsButton.setOnClickListener(new View.OnClickListener() {
            private CharSequence[] mLabels;

            @Override
            public void onClick(View v) {
                final CharSequence[] labels = rangebar.getTickTopLabels();

                if (labels != null) {
                    mLabels = labels;
                    rangebar.setTickTopLabels(null);
                } else {
                    rangebar.setTickTopLabels(mLabels);
                }
            }
        });

        tickBottomLabelsButton.setOnClickListener(new View.OnClickListener() {
            private CharSequence[] mLabels;

            @Override
            public void onClick(View v) {
                final CharSequence[] labels = rangebar.getTickBottomLabels();

                if (labels != null) {
                    mLabels = labels;
                    rangebar.setTickBottomLabels(null);
                } else {
                    rangebar.setTickBottomLabels(mLabels);
                }
            }
        });

        findViewById(R.id.thumbRecyclerView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
            }
        });

    }

    /**
     * Sets the changed color using the ColorPickerDialog.
     *
     * @param component Component specifying which input is being used
     * @param newColor  Integer specifying the new color to be selected.
     */

    @Override
    public void onColorSelected(int newColor, Component component) {
        Log.d("Color selected", " new color = " + newColor + ",compoment = " + component);
        String hexColor = String.format("#%06X", (0xFFFFFF & newColor));

        switch (component) {
            case BAR_COLOR:
                mBarColor = newColor;
                rangebar.setBarColor(newColor);
                final TextView barColorText = findViewById(R.id.barColor);
                barColorText.setText("barColor = " + hexColor);
                barColorText.setTextColor(newColor);
                break;
            case TEXT_COLOR:
                mTextColor = newColor;
                rangebar.setPinTextColor(newColor);
                final TextView textColorText = findViewById(R.id.textColor);
                textColorText.setText("textColor = " + hexColor);
                textColorText.setTextColor(newColor);
                break;

            case CONNECTING_LINE_COLOR:
                mConnectingLineColor = newColor;
                rangebar.setConnectingLineColor(newColor);
                final TextView connectingLineColorText = findViewById(
                        R.id.connectingLineColor);
                connectingLineColorText.setText("connectingLineColor = " + hexColor);
                connectingLineColorText.setTextColor(newColor);
                break;

            case PIN_COLOR:
                mPinColor = newColor;
                rangebar.setPinColor(newColor);
                final TextView pinColorText = findViewById(R.id.pinColor);
                pinColorText.setText("pinColor = " + hexColor);
                pinColorText.setTextColor(newColor);
                break;
            case TICK_COLOR:
                mTickColor = newColor;
                rangebar.setTickDefaultColor(newColor);
                final TextView tickColorText = findViewById(R.id.tickColor);
                tickColorText.setText("tickColor = " + hexColor);
                tickColorText.setTextColor(newColor);
                break;
            case THUMB_COLOR:
                mThumbColor = newColor;
                rangebar.setThumbColor(newColor);
                final TextView thumbColorText = findViewById(R.id.thumbColor);
                thumbColorText.setText("Thumb Color = " + hexColor);
                thumbColorText.setTextColor(newColor);
                break;
            case THUMB_BOUNDARY_COLOR:
                mThumbBoundaryColor = newColor;
                rangebar.setThumbBoundaryColor(newColor);
                final TextView thumbBoundaryColorText = findViewById(R.id.thumbBoundaryColor);
                thumbBoundaryColorText.setText("Thumb Boundary Color = " + hexColor);
                thumbBoundaryColorText.setTextColor(newColor);
                break;
            case TICK_LABEL_COLOR:
                mTickLabelColor = newColor;
                rangebar.setTickLabelColor(newColor);
                break;
            case TICK_LABEL_SELECTED_COLOR:
                mTickLabelSelectedColor = newColor;
                rangebar.setTickLabelSelectedColor(newColor);
                break;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Initiates the colorPicker from within a button function.
     *
     * @param component    Component specifying which input is being used
     * @param initialColor Integer specifying the initial color choice. *
     * @param defaultColor Integer specifying the default color choice.
     */
    private void initColorPicker(Component component, int initialColor, int defaultColor) {
        ColorPickerDialog colorPicker = ColorPickerDialog
                .newInstance(R.string.colorPickerTitle, Utils.ColorUtils.colorChoice(this),
                        initialColor, 4, ColorPickerDialog.SIZE_SMALL, component);
        colorPicker.setOnColorSelectedListener(this);
        colorPicker.show(getFragmentManager(), "color");
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static int convertDpToPixel(int dp, Context context) {
        return (int) (dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static int convertPixelsToDp(int px, Context context) {
        return (int) (px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
