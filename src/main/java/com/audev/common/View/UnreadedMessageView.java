package com.audev.common.View;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by cosxt on 24.02.2016.
 */
public class UnreadedMessageView {
    public interface main{}

    private int size;

    @JsonView(main.class)
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
