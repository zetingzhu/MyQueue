//package com.zzt.myqueue.messageDialog;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.res.Resources;
//import android.database.Cursor;
//import android.graphics.drawable.Drawable;
//import android.os.Bundle;
//import android.os.Message;
//import android.text.method.MovementMethod;
//import android.util.TypedValue;
//import android.view.ContextThemeWrapper;
//import android.view.KeyEvent;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.Button;
//import android.widget.ListAdapter;
//import android.widget.ListView;
//
//import androidx.annotation.ArrayRes;
//import androidx.annotation.AttrRes;
//import androidx.annotation.DrawableRes;
//import androidx.annotation.StringRes;
//import androidx.annotation.StyleRes;
//
//import com.zzt.myqueue.R;
//
//
///**
// * @author: zeting
// * @date: 2019/12/27
// */
//@Deprecated
//public class MyAlertDialog1 extends MyDialog {
//    private final static String TAG = MyAlertDialog1.class.getSimpleName();
//    private MyAlertController mAlert;
//
//    /**
//     * Special theme constant for {@link #MyAlertDialog(Context, int)}: use
//     * the traditional (pre-Holo) alert dialog theme.
//     *
//     * @deprecated Use {@link android.R.style#Theme_Material_Dialog_Alert}.
//     */
//    @Deprecated
//    public static final int THEME_TRADITIONAL = 1;
//
//    /**
//     * Special theme constant for {@link #MyAlertDialog(Context, int)}: use
//     * the holographic alert theme with a dark background.
//     *
//     * @deprecated Use {@link android.R.style#Theme_Material_Dialog_Alert}.
//     */
//    @Deprecated
//    public static final int THEME_HOLO_DARK = 2;
//
//    /**
//     * Special theme constant for {@link #MyAlertDialog(Context, int)}: use
//     * the holographic alert theme with a light background.
//     *
//     * @deprecated Use {@link android.R.style#Theme_Material_Light_Dialog_Alert}.
//     */
//    @Deprecated
//    public static final int THEME_HOLO_LIGHT = 3;
//
//    /**
//     * Special theme constant for {@link #MyAlertDialog(Context, int)}: use
//     * the device's default alert theme with a dark background.
//     *
//     * @deprecated Use {@link android.R.style#Theme_DeviceDefault_Dialog_Alert}.
//     */
//    @Deprecated
//    public static final int THEME_DEVICE_DEFAULT_DARK = 4;
//
//    /**
//     * Special theme constant for {@link #MyAlertDialog(Context, int)}: use
//     * the device's default alert theme with a light background.
//     *
//     * @deprecated Use {@link android.R.style#Theme_DeviceDefault_Light_Dialog_Alert}.
//     */
//    @Deprecated
//    public static final int THEME_DEVICE_DEFAULT_LIGHT = 5;
//
//    /**
//     * No layout hint.
//     *
//     * @hide
//     */
//    public static final int LAYOUT_HINT_NONE = 0;
//
//    /**
//     * Hint layout to the side.
//     *
//     * @hide
//     */
//    public static final int LAYOUT_HINT_SIDE = 1;
//
//
//    /**
//     * Gets one of the buttons used in the dialog. Returns null if the specified
//     * button does not exist or the dialog has not yet been fully created (for
//     * example, via {@link #show()} or {@link #create()}).
//     *
//     * @param whichButton The identifier of the button that should be returned.
//     *                    For example, this can be
//     *                    {@link DialogInterface#BUTTON_POSITIVE}.
//     * @return The button from the dialog, or null if a button does not exist.
//     */
//    public Button getButton(int whichButton) {
//        return mAlert.getButton(whichButton);
//    }
//
//    /**
//     * Gets the list view used in the dialog.
//     *
//     * @return The {@link ListView} from the dialog.
//     */
//    public ListView getListView() {
//        return mAlert.getListView();
//    }
//
//    @Override
//    public void setTitle(CharSequence title) {
//        super.setTitle(title);
//        mAlert.setTitle(title);
//    }
//
//    /**
//     * @see Builder#setCustomTitle(View)
//     */
//    public void setCustomTitle(View customTitleView) {
//        mAlert.setCustomTitle(customTitleView);
//    }
//
//    public void setMessage(CharSequence message) {
//        mAlert.setMessage(message);
//    }
//
//    /**
//     * @hide
//     */
//    public void setMessageMovementMethod(MovementMethod movementMethod) {
//        mAlert.setMessageMovementMethod(movementMethod);
//    }
//
//    /**
//     * @hide
//     */
//    public void setMessageHyphenationFrequency(int hyphenationFrequency) {
//        mAlert.setMessageHyphenationFrequency(hyphenationFrequency);
//    }
//
//    /**
//     * Set the view to display in that dialog.
//     */
//    public void setView(View view) {
//        mAlert.setView(view);
//    }
//
//    /**
//     * Set the view to display in that dialog, specifying the spacing to appear around that
//     * view.
//     *
//     * @param view              The view to show in the content area of the dialog
//     * @param viewSpacingLeft   Extra space to appear to the left of {@code view}
//     * @param viewSpacingTop    Extra space to appear above {@code view}
//     * @param viewSpacingRight  Extra space to appear to the right of {@code view}
//     * @param viewSpacingBottom Extra space to appear below {@code view}
//     */
//    public void setView(View view, int viewSpacingLeft, int viewSpacingTop, int viewSpacingRight,
//                        int viewSpacingBottom) {
//        mAlert.setView(view, viewSpacingLeft, viewSpacingTop, viewSpacingRight, viewSpacingBottom);
//    }
//
//    /**
//     * Internal api to allow hinting for the best button panel layout.
//     *
//     * @hide
//     */
//    void setButtonPanelLayoutHint(int layoutHint) {
//        mAlert.setButtonPanelLayoutHint(layoutHint);
//    }
//
//    /**
//     * Set a message to be sent when a button is pressed.
//     *
//     * @param whichButton Which button to set the message for, can be one of
//     *                    {@link DialogInterface#BUTTON_POSITIVE},
//     *                    {@link DialogInterface#BUTTON_NEGATIVE}, or
//     *                    {@link DialogInterface#BUTTON_NEUTRAL}
//     * @param text        The text to display in positive button.
//     * @param msg         The {@link Message} to be sent when clicked.
//     */
//    public void setButton(int whichButton, CharSequence text, Message msg) {
//        mAlert.setButton(whichButton, text, null, msg);
//    }
//
//    /**
//     * Set a listener to be invoked when the positive button of the dialog is pressed.
//     *
//     * @param whichButton Which button to set the listener on, can be one of
//     *                    {@link DialogInterface#BUTTON_POSITIVE},
//     *                    {@link DialogInterface#BUTTON_NEGATIVE}, or
//     *                    {@link DialogInterface#BUTTON_NEUTRAL}
//     * @param text        The text to display in positive button.
//     * @param listener    The {@link OnClickListener} to use.
//     */
//    public void setButton(int whichButton, CharSequence text, OnClickListener listener) {
//        mAlert.setButton(whichButton, text, listener, null);
//    }
//
//    /**
//     * @deprecated Use {@link #setButton(int, CharSequence, Message)} with
//     * {@link DialogInterface#BUTTON_POSITIVE}.
//     */
//    @Deprecated
//    public void setButton(CharSequence text, Message msg) {
//        setButton(BUTTON_POSITIVE, text, msg);
//    }
//
//    /**
//     * @deprecated Use {@link #setButton(int, CharSequence, Message)} with
//     * {@link DialogInterface#BUTTON_NEGATIVE}.
//     */
//    @Deprecated
//    public void setButton2(CharSequence text, Message msg) {
//        setButton(BUTTON_NEGATIVE, text, msg);
//    }
//
//    /**
//     * @deprecated Use {@link #setButton(int, CharSequence, Message)} with
//     * {@link DialogInterface#BUTTON_NEUTRAL}.
//     */
//    @Deprecated
//    public void setButton3(CharSequence text, Message msg) {
//        setButton(BUTTON_NEUTRAL, text, msg);
//    }
//
//    /**
//     * Set a listener to be invoked when button 1 of the dialog is pressed.
//     *
//     * @param text     The text to display in button 1.
//     * @param listener The {@link OnClickListener} to use.
//     * @deprecated Use
//     * {@link #setButton(int, CharSequence, OnClickListener)}
//     * with {@link DialogInterface#BUTTON_POSITIVE}
//     */
//    @Deprecated
//    public void setButton(CharSequence text, final OnClickListener listener) {
//        setButton(BUTTON_POSITIVE, text, listener);
//    }
//
//    /**
//     * Set a listener to be invoked when button 2 of the dialog is pressed.
//     *
//     * @param text     The text to display in button 2.
//     * @param listener The {@link OnClickListener} to use.
//     * @deprecated Use
//     * {@link #setButton(int, CharSequence, OnClickListener)}
//     * with {@link DialogInterface#BUTTON_NEGATIVE}
//     */
//    @Deprecated
//    public void setButton2(CharSequence text, final OnClickListener listener) {
//        setButton(BUTTON_NEGATIVE, text, listener);
//    }
//
//    /**
//     * Set a listener to be invoked when button 3 of the dialog is pressed.
//     *
//     * @param text     The text to display in button 3.
//     * @param listener The {@link OnClickListener} to use.
//     * @deprecated Use
//     * {@link #setButton(int, CharSequence, OnClickListener)}
//     * with {@link DialogInterface#BUTTON_NEUTRAL}
//     */
//    @Deprecated
//    public void setButton3(CharSequence text, final OnClickListener listener) {
//        setButton(BUTTON_NEUTRAL, text, listener);
//    }
//
//    /**
//     * Set resId to 0 if you don't want an icon.
//     *
//     * @param resId the resourceId of the drawable to use as the icon or 0
//     *              if you don't want an icon.
//     */
//    public void setIcon(@DrawableRes int resId) {
//        mAlert.setIcon(resId);
//    }
//
//    public void setIcon(Drawable icon) {
//        mAlert.setIcon(icon);
//    }
//
//    /**
//     * Set an icon as supplied by a theme attribute. e.g. android.R.attr.alertDialogIcon
//     *
//     * @param attrId ID of a theme attribute that points to a drawable resource.
//     */
//    public void setIconAttribute(@AttrRes int attrId) {
//        TypedValue out = new TypedValue();
//        getContext().getTheme().resolveAttribute(attrId, out, true);
//        mAlert.setIcon(out.resourceId);
//    }
//
//    public void setInverseBackgroundForced(boolean forceInverseBackground) {
//        mAlert.setInverseBackgroundForced(forceInverseBackground);
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mAlert.installContent();
//    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (mAlert.onKeyDown(keyCode, event)) return true;
//        return super.onKeyDown(keyCode, event);
//    }
//
//    @Override
//    public boolean onKeyUp(int keyCode, KeyEvent event) {
//        if (mAlert.onKeyUp(keyCode, event)) return true;
//        return super.onKeyUp(keyCode, event);
//    }
//
//    protected MyAlertDialog1(Context context, boolean cancelable, OnCancelListener cancelListener) {
//        this(context, 0);
//
//        setCancelable(cancelable);
//        setOnCancelListener(cancelListener);
//    }
//
//    protected MyAlertDialog1(Context context) {
//        super(context);
//        mAlert = MyAlertController.create(getContext(), this, getWindow());
//    }
//
//
//    protected MyAlertDialog1(Context context, int themeResId) {
//        super(context, themeResId);
//    }
//
//    static @StyleRes
//    int resolveDialogTheme(Context context, @StyleRes int themeResId) {
//        final TypedValue outValue = new TypedValue();
//        context.getTheme().resolveAttribute(R.attr.alertDialogTheme, outValue, true);
//        return outValue.resourceId;
//    }
//
//    public static class Builder {
//        private final MyAlertController.AlertParams P;
//
//        public Builder(Context context) {
//            this(context, resolveDialogTheme(context, Resources.ID_NULL));
//        }
//
//        public Builder(Context context, int themeResId) {
//            P = new MyAlertController.AlertParams(new ContextThemeWrapper(
//                    context, resolveDialogTheme(context, themeResId)));
//        }
//
//        public Context getContext() {
//            return P.mContext;
//        }
//
//        /**
//         * Set the title using the given resource id.
//         *
//         * @return This Builder object to allow for chaining of calls to set methods
//         */
//        public Builder setTitle(@StringRes int titleId) {
//            P.mTitle = P.mContext.getText(titleId);
//            return this;
//        }
//
//        /**
//         * Set the title displayed in the {@link Dialog}.
//         *
//         * @return This Builder object to allow for chaining of calls to set methods
//         */
//        public Builder setTitle(CharSequence title) {
//            P.mTitle = title;
//            return this;
//        }
//
//        /**
//         * Set the title using the custom view {@code customTitleView}.
//         * <p>
//         * The methods {@link #setTitle(int)} and {@link #setIcon(int)} should
//         * be sufficient for most titles, but this is provided if the title
//         * needs more customization. Using this will replace the title and icon
//         * set via the other methods.
//         * <p>
//         * <strong>Note:</strong> To ensure consistent styling, the custom view
//         * should be inflated or constructed using the alert dialog's themed
//         * context obtained via {@link #getContext()}.
//         *
//         * @param customTitleView the custom view to use as the title
//         * @return this Builder object to allow for chaining of calls to set
//         * methods
//         */
//        public Builder setCustomTitle(View customTitleView) {
//            P.mCustomTitleView = customTitleView;
//            return this;
//        }
//
//        /**
//         * Set the message to display using the given resource id.
//         *
//         * @return This Builder object to allow for chaining of calls to set methods
//         */
//        public Builder setMessage(@StringRes int messageId) {
//            P.mMessage = P.mContext.getText(messageId);
//            return this;
//        }
//
//        /**
//         * Set the message to display.
//         *
//         * @return This Builder object to allow for chaining of calls to set methods
//         */
//        public Builder setMessage(CharSequence message) {
//            P.mMessage = message;
//            return this;
//        }
//
//        /**
//         * Set the resource id of the {@link Drawable} to be used in the title.
//         * <p>
//         * Takes precedence over values set using {@link #setIcon(Drawable)}.
//         *
//         * @return This Builder object to allow for chaining of calls to set methods
//         */
//        public Builder setIcon(@DrawableRes int iconId) {
//            P.mIconId = iconId;
//            return this;
//        }
//
//        /**
//         * Set the {@link Drawable} to be used in the title.
//         * <p>
//         * <strong>Note:</strong> To ensure consistent styling, the drawable
//         * should be inflated or constructed using the alert dialog's themed
//         * context obtained via {@link #getContext()}.
//         *
//         * @return this Builder object to allow for chaining of calls to set
//         * methods
//         */
//        public Builder setIcon(Drawable icon) {
//            P.mIcon = icon;
//            return this;
//        }
//
//        /**
//         * Set an icon as supplied by a theme attribute. e.g.
//         * {@link android.R.attr#alertDialogIcon}.
//         * <p>
//         * Takes precedence over values set using {@link #setIcon(int)} or
//         * {@link #setIcon(Drawable)}.
//         *
//         * @param attrId ID of a theme attribute that points to a drawable resource.
//         */
//        public Builder setIconAttribute(@AttrRes int attrId) {
//            TypedValue out = new TypedValue();
//            P.mContext.getTheme().resolveAttribute(attrId, out, true);
//            P.mIconId = out.resourceId;
//            return this;
//        }
//
//        /**
//         * Set a listener to be invoked when the positive button of the dialog is pressed.
//         *
//         * @param textId   The resource id of the text to display in the positive button
//         * @param listener The {@link OnClickListener} to use.
//         * @return This Builder object to allow for chaining of calls to set methods
//         */
//        public Builder setPositiveButton(@StringRes int textId, final OnClickListener listener) {
//            P.mPositiveButtonText = P.mContext.getText(textId);
//            P.mPositiveButtonListener = listener;
//            return this;
//        }
//
//        /**
//         * Set a listener to be invoked when the positive button of the dialog is pressed.
//         *
//         * @param text     The text to display in the positive button
//         * @param listener The {@link OnClickListener} to use.
//         * @return This Builder object to allow for chaining of calls to set methods
//         */
//        public Builder setPositiveButton(CharSequence text, final OnClickListener listener) {
//            P.mPositiveButtonText = text;
//            P.mPositiveButtonListener = listener;
//            return this;
//        }
//
//        /**
//         * Set a listener to be invoked when the negative button of the dialog is pressed.
//         *
//         * @param textId   The resource id of the text to display in the negative button
//         * @param listener The {@link OnClickListener} to use.
//         * @return This Builder object to allow for chaining of calls to set methods
//         */
//        public Builder setNegativeButton(@StringRes int textId, final OnClickListener listener) {
//            P.mNegativeButtonText = P.mContext.getText(textId);
//            P.mNegativeButtonListener = listener;
//            return this;
//        }
//
//        /**
//         * Set a listener to be invoked when the negative button of the dialog is pressed.
//         *
//         * @param text     The text to display in the negative button
//         * @param listener The {@link OnClickListener} to use.
//         * @return This Builder object to allow for chaining of calls to set methods
//         */
//        public Builder setNegativeButton(CharSequence text, final OnClickListener listener) {
//            P.mNegativeButtonText = text;
//            P.mNegativeButtonListener = listener;
//            return this;
//        }
//
//        /**
//         * Set a listener to be invoked when the neutral button of the dialog is pressed.
//         *
//         * @param textId   The resource id of the text to display in the neutral button
//         * @param listener The {@link OnClickListener} to use.
//         * @return This Builder object to allow for chaining of calls to set methods
//         */
//        public Builder setNeutralButton(@StringRes int textId, final OnClickListener listener) {
//            P.mNeutralButtonText = P.mContext.getText(textId);
//            P.mNeutralButtonListener = listener;
//            return this;
//        }
//
//        /**
//         * Set a listener to be invoked when the neutral button of the dialog is pressed.
//         *
//         * @param text     The text to display in the neutral button
//         * @param listener The {@link OnClickListener} to use.
//         * @return This Builder object to allow for chaining of calls to set methods
//         */
//        public Builder setNeutralButton(CharSequence text, final OnClickListener listener) {
//            P.mNeutralButtonText = text;
//            P.mNeutralButtonListener = listener;
//            return this;
//        }
//
//        /**
//         * Sets whether the dialog is cancelable or not.  Default is true.
//         *
//         * @return This Builder object to allow for chaining of calls to set methods
//         */
//        public Builder setCancelable(boolean cancelable) {
//            P.mCancelable = cancelable;
//            return this;
//        }
//
//        /**
//         * Sets the callback that will be called if the dialog is canceled.
//         *
//         * <p>Even in a cancelable dialog, the dialog may be dismissed for reasons other than
//         * being canceled or one of the supplied choices being selected.
//         * If you are interested in listening for all cases where the dialog is dismissed
//         * and not just when it is canceled, see
//         * {@link #setOnDismissListener(OnDismissListener) setOnDismissListener}.</p>
//         *
//         * @return This Builder object to allow for chaining of calls to set methods
//         * @see #setCancelable(boolean)
//         * @see #setOnDismissListener(OnDismissListener)
//         */
//        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
//            P.mOnCancelListener = onCancelListener;
//            return this;
//        }
//
//        /**
//         * Sets the callback that will be called when the dialog is dismissed for any reason.
//         *
//         * @return This Builder object to allow for chaining of calls to set methods
//         */
//        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
//            P.mOnDismissListener = onDismissListener;
//            return this;
//        }
//
//        /**
//         * Sets the callback that will be called if a key is dispatched to the dialog.
//         *
//         * @return This Builder object to allow for chaining of calls to set methods
//         */
//        public Builder setOnKeyListener(OnKeyListener onKeyListener) {
//            P.mOnKeyListener = onKeyListener;
//            return this;
//        }
//
//        /**
//         * Set a list of items to be displayed in the dialog as the content, you will be notified of the
//         * selected item via the supplied listener. This should be an array type i.e. R.array.foo
//         *
//         * @return This Builder object to allow for chaining of calls to set methods
//         */
//        public Builder setItems(@ArrayRes int itemsId, final OnClickListener listener) {
//            P.mItems = P.mContext.getResources().getTextArray(itemsId);
//            P.mOnClickListener = listener;
//            return this;
//        }
//
//        /**
//         * Set a list of items to be displayed in the dialog as the content, you will be notified of the
//         * selected item via the supplied listener.
//         *
//         * @return This Builder object to allow for chaining of calls to set methods
//         */
//        public Builder setItems(CharSequence[] items, final OnClickListener listener) {
//            P.mItems = items;
//            P.mOnClickListener = listener;
//            return this;
//        }
//
//        /**
//         * Set a list of items, which are supplied by the given {@link ListAdapter}, to be
//         * displayed in the dialog as the content, you will be notified of the
//         * selected item via the supplied listener.
//         *
//         * @param adapter  The {@link ListAdapter} to supply the list of items
//         * @param listener The listener that will be called when an item is clicked.
//         * @return This Builder object to allow for chaining of calls to set methods
//         */
//        public Builder setAdapter(final ListAdapter adapter, final OnClickListener listener) {
//            P.mAdapter = adapter;
//            P.mOnClickListener = listener;
//            return this;
//        }
//
//        /**
//         * Set a list of items, which are supplied by the given {@link Cursor}, to be
//         * displayed in the dialog as the content, you will be notified of the
//         * selected item via the supplied listener.
//         *
//         * @param cursor      The {@link Cursor} to supply the list of items
//         * @param listener    The listener that will be called when an item is clicked.
//         * @param labelColumn The column name on the cursor containing the string to display
//         *                    in the label.
//         * @return This Builder object to allow for chaining of calls to set methods
//         */
//        public Builder setCursor(final Cursor cursor, final OnClickListener listener,
//                                 String labelColumn) {
//            P.mCursor = cursor;
//            P.mLabelColumn = labelColumn;
//            P.mOnClickListener = listener;
//            return this;
//        }
//
//        /**
//         * Set a list of items to be displayed in the dialog as the content,
//         * you will be notified of the selected item via the supplied listener.
//         * This should be an array type, e.g. R.array.foo. The list will have
//         * a check mark displayed to the right of the text for each checked
//         * item. Clicking on an item in the list will not dismiss the dialog.
//         * Clicking on a button will dismiss the dialog.
//         *
//         * @param itemsId      the resource id of an array i.e. R.array.foo
//         * @param checkedItems specifies which items are checked. It should be null in which case no
//         *                     items are checked. If non null it must be exactly the same length as the array of
//         *                     items.
//         * @param listener     notified when an item on the list is clicked. The dialog will not be
//         *                     dismissed when an item is clicked. It will only be dismissed if clicked on a
//         *                     button, if no buttons are supplied it's up to the user to dismiss the dialog.
//         * @return This Builder object to allow for chaining of calls to set methods
//         */
//        public Builder setMultiChoiceItems(@ArrayRes int itemsId, boolean[] checkedItems,
//                                           final OnMultiChoiceClickListener listener) {
//            P.mItems = P.mContext.getResources().getTextArray(itemsId);
//            P.mOnCheckboxClickListener = listener;
//            P.mCheckedItems = checkedItems;
//            P.mIsMultiChoice = true;
//            return this;
//        }
//
//        /**
//         * Set a list of items to be displayed in the dialog as the content,
//         * you will be notified of the selected item via the supplied listener.
//         * The list will have a check mark displayed to the right of the text
//         * for each checked item. Clicking on an item in the list will not
//         * dismiss the dialog. Clicking on a button will dismiss the dialog.
//         *
//         * @param items        the text of the items to be displayed in the list.
//         * @param checkedItems specifies which items are checked. It should be null in which case no
//         *                     items are checked. If non null it must be exactly the same length as the array of
//         *                     items.
//         * @param listener     notified when an item on the list is clicked. The dialog will not be
//         *                     dismissed when an item is clicked. It will only be dismissed if clicked on a
//         *                     button, if no buttons are supplied it's up to the user to dismiss the dialog.
//         * @return This Builder object to allow for chaining of calls to set methods
//         */
//        public Builder setMultiChoiceItems(CharSequence[] items, boolean[] checkedItems,
//                                           final OnMultiChoiceClickListener listener) {
//            P.mItems = items;
//            P.mOnCheckboxClickListener = listener;
//            P.mCheckedItems = checkedItems;
//            P.mIsMultiChoice = true;
//            return this;
//        }
//
//        /**
//         * Set a list of items to be displayed in the dialog as the content,
//         * you will be notified of the selected item via the supplied listener.
//         * The list will have a check mark displayed to the right of the text
//         * for each checked item. Clicking on an item in the list will not
//         * dismiss the dialog. Clicking on a button will dismiss the dialog.
//         *
//         * @param cursor          the cursor used to provide the items.
//         * @param isCheckedColumn specifies the column name on the cursor to use to determine
//         *                        whether a checkbox is checked or not. It must return an integer value where 1
//         *                        means checked and 0 means unchecked.
//         * @param labelColumn     The column name on the cursor containing the string to display in the
//         *                        label.
//         * @param listener        notified when an item on the list is clicked. The dialog will not be
//         *                        dismissed when an item is clicked. It will only be dismissed if clicked on a
//         *                        button, if no buttons are supplied it's up to the user to dismiss the dialog.
//         * @return This Builder object to allow for chaining of calls to set methods
//         */
//        public Builder setMultiChoiceItems(Cursor cursor, String isCheckedColumn, String labelColumn,
//                                           final OnMultiChoiceClickListener listener) {
//            P.mCursor = cursor;
//            P.mOnCheckboxClickListener = listener;
//            P.mIsCheckedColumn = isCheckedColumn;
//            P.mLabelColumn = labelColumn;
//            P.mIsMultiChoice = true;
//            return this;
//        }
//
//        /**
//         * Set a list of items to be displayed in the dialog as the content, you will be notified of
//         * the selected item via the supplied listener. This should be an array type i.e.
//         * R.array.foo The list will have a check mark displayed to the right of the text for the
//         * checked item. Clicking on an item in the list will not dismiss the dialog. Clicking on a
//         * button will dismiss the dialog.
//         *
//         * @param itemsId     the resource id of an array i.e. R.array.foo
//         * @param checkedItem specifies which item is checked. If -1 no items are checked.
//         * @param listener    notified when an item on the list is clicked. The dialog will not be
//         *                    dismissed when an item is clicked. It will only be dismissed if clicked on a
//         *                    button, if no buttons are supplied it's up to the user to dismiss the dialog.
//         * @return This Builder object to allow for chaining of calls to set methods
//         */
//        public Builder setSingleChoiceItems(@ArrayRes int itemsId, int checkedItem,
//                                            final OnClickListener listener) {
//            P.mItems = P.mContext.getResources().getTextArray(itemsId);
//            P.mOnClickListener = listener;
//            P.mCheckedItem = checkedItem;
//            P.mIsSingleChoice = true;
//            return this;
//        }
//
//        /**
//         * Set a list of items to be displayed in the dialog as the content, you will be notified of
//         * the selected item via the supplied listener. The list will have a check mark displayed to
//         * the right of the text for the checked item. Clicking on an item in the list will not
//         * dismiss the dialog. Clicking on a button will dismiss the dialog.
//         *
//         * @param cursor      the cursor to retrieve the items from.
//         * @param checkedItem specifies which item is checked. If -1 no items are checked.
//         * @param labelColumn The column name on the cursor containing the string to display in the
//         *                    label.
//         * @param listener    notified when an item on the list is clicked. The dialog will not be
//         *                    dismissed when an item is clicked. It will only be dismissed if clicked on a
//         *                    button, if no buttons are supplied it's up to the user to dismiss the dialog.
//         * @return This Builder object to allow for chaining of calls to set methods
//         */
//        public Builder setSingleChoiceItems(Cursor cursor, int checkedItem, String labelColumn,
//                                            final OnClickListener listener) {
//            P.mCursor = cursor;
//            P.mOnClickListener = listener;
//            P.mCheckedItem = checkedItem;
//            P.mLabelColumn = labelColumn;
//            P.mIsSingleChoice = true;
//            return this;
//        }
//
//        /**
//         * Set a list of items to be displayed in the dialog as the content, you will be notified of
//         * the selected item via the supplied listener. The list will have a check mark displayed to
//         * the right of the text for the checked item. Clicking on an item in the list will not
//         * dismiss the dialog. Clicking on a button will dismiss the dialog.
//         *
//         * @param items       the items to be displayed.
//         * @param checkedItem specifies which item is checked. If -1 no items are checked.
//         * @param listener    notified when an item on the list is clicked. The dialog will not be
//         *                    dismissed when an item is clicked. It will only be dismissed if clicked on a
//         *                    button, if no buttons are supplied it's up to the user to dismiss the dialog.
//         * @return This Builder object to allow for chaining of calls to set methods
//         */
//        public Builder setSingleChoiceItems(CharSequence[] items, int checkedItem, final OnClickListener listener) {
//            P.mItems = items;
//            P.mOnClickListener = listener;
//            P.mCheckedItem = checkedItem;
//            P.mIsSingleChoice = true;
//            return this;
//        }
//
//        /**
//         * Set a list of items to be displayed in the dialog as the content, you will be notified of
//         * the selected item via the supplied listener. The list will have a check mark displayed to
//         * the right of the text for the checked item. Clicking on an item in the list will not
//         * dismiss the dialog. Clicking on a button will dismiss the dialog.
//         *
//         * @param adapter     The {@link ListAdapter} to supply the list of items
//         * @param checkedItem specifies which item is checked. If -1 no items are checked.
//         * @param listener    notified when an item on the list is clicked. The dialog will not be
//         *                    dismissed when an item is clicked. It will only be dismissed if clicked on a
//         *                    button, if no buttons are supplied it's up to the user to dismiss the dialog.
//         * @return This Builder object to allow for chaining of calls to set methods
//         */
//        public Builder setSingleChoiceItems(ListAdapter adapter, int checkedItem, final OnClickListener listener) {
//            P.mAdapter = adapter;
//            P.mOnClickListener = listener;
//            P.mCheckedItem = checkedItem;
//            P.mIsSingleChoice = true;
//            return this;
//        }
//
//        /**
//         * Sets a listener to be invoked when an item in the list is selected.
//         *
//         * @param listener the listener to be invoked
//         * @return this Builder object to allow for chaining of calls to set methods
//         * @see AdapterView#setOnItemSelectedListener(AdapterView.OnItemSelectedListener)
//         */
//        public Builder setOnItemSelectedListener(final AdapterView.OnItemSelectedListener listener) {
//            P.mOnItemSelectedListener = listener;
//            return this;
//        }
//
//
//        /**
//         * Creates an {@link MyAlertDialog1} with the arguments supplied to this
//         * builder.
//         * <p>
//         * Calling this method does not display the dialog. If no additional
//         * processing is needed, {@link #show()} may be called instead to both
//         * create and display the dialog.
//         */
//        public MyAlertDialog1 create() {
//            // Context has already been wrapped with the appropriate theme.
//            final MyAlertDialog1 dialog = new MyAlertDialog1(P.mContext);
//            P.apply(dialog.mAlert);
//            dialog.setCancelable(P.mCancelable);
//            if (P.mCancelable) {
//                dialog.setCanceledOnTouchOutside(true);
//            }
//            dialog.setOnCancelListener(P.mOnCancelListener);
//            dialog.setOnDismissListener(P.mOnDismissListener);
//            if (P.mOnKeyListener != null) {
//                dialog.setOnKeyListener(P.mOnKeyListener);
//            }
//            return dialog;
//        }
//
//        /**
//         * Creates an {@link MyAlertDialog1} with the arguments supplied to this
//         * builder and immediately displays the dialog.
//         * <p>
//         * Calling this method is functionally identical to:
//         * <pre>
//         *     AlertDialog dialog = builder.create();
//         *     dialog.show();
//         * </pre>
//         */
//        public MyAlertDialog1 show() {
//            final MyAlertDialog1 dialog = create();
//            dialog.show();
//            return dialog;
//        }
//    }
//}
