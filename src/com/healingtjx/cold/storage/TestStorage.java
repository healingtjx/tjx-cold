package com.healingtjx.cold.storage;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @Author: tjx
 * @Description:
 * @Date: 创建于17:10 2021-01-15
 **/
@State(name = "TestStorage", storages = {@Storage(value = "TestStorage.xml")})
public class TestStorage implements PersistentStateComponent<TestStorage.MyState> {
    MyState myState;

    @Nullable
    @Override
    public MyState getState() {
        return myState;
    }

    @Override
    public void loadState(@NotNull MyState state) {
        this.myState = state;
    }


    public static class MyState {
        public String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }


}
