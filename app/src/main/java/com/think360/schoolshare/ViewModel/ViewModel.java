package com.think360.schoolshare.ViewModel;

import android.databinding.ObservableField;

/**
 * Created by think360 on 21/02/17.
 */

public class ViewModel {
    private ObservableField<String> text;
    public ViewModel() {
        text = new ObservableField<>();
    }
    public ObservableField<String> getText() {
        return text;
    }
}
