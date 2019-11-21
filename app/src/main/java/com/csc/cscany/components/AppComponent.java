package com.csc.cscany.components;

import com.csc.cscany.activities.BaseActivity;
import com.csc.cscany.modules.AppModule;
import com.csc.cscany.modules.DbModule;
import com.csc.cscany.modules.UtilsModules;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, UtilsModules.class, DbModule.class})
public interface AppComponent {

    void inject(BaseActivity baseActivity);

}
