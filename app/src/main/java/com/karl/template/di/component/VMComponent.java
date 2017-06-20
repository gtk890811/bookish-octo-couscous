/*
 * Copyright (c) AirBolt Pty Ltd - All Rights Reserved.
 */

package com.karl.template.di.component;

import com.Airbolt.TheAirBolt.dagger.scope.PerVM;
import com.Airbolt.TheAirBolt.vm.AirboltListVM;
import com.Airbolt.TheAirBolt.vm.AirboltVM;
import com.Airbolt.TheAirBolt.vm.FirmwareVM;
import com.Airbolt.TheAirBolt.vm.HistoryListVM;
import com.Airbolt.TheAirBolt.vm.HistoryVM;
import com.Airbolt.TheAirBolt.vm.SharerListVM;
import com.Airbolt.TheAirBolt.vm.SharerVM;
import com.Airbolt.TheAirBolt.vm.UserVM;

import dagger.Component;

/**
 * Created by Karl Gao on 8/11/16.
 */

@PerVM
@Component(dependencies = AppComponent.class)
public interface VMComponent {

    void inject(UserVM vm);


    void inject(AirboltListVM vm);
    void inject(AirboltVM vm);


    void inject(HistoryListVM vm);
    void inject(HistoryVM vm);


    void inject(SharerListVM vm);
    void inject(SharerVM vm);


    void inject(FirmwareVM vm);
}
