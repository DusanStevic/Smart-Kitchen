import { NgModule, Optional, SkipSelf } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarAdminComponent } from './navbar/navbar-admin/navbar-admin.component';
import { NavbarRegisteredUserComponent } from './navbar/navbar-registered-user/navbar-registered-user.component';
import { NavbarUnregisteredUserComponent } from './navbar/navbar-unregistered-user/navbar-unregistered-user.component';
import { MaterialModule } from '../material/material.module';
import { AuthenticationService } from './services/authentication.service';
import { ConstantsService } from './services/constants.service';
import { AppRoutingModule } from '../app-routing.module';



@NgModule({
  declarations: [NavbarAdminComponent, NavbarRegisteredUserComponent, NavbarUnregisteredUserComponent],
  imports: [
    CommonModule,
    MaterialModule,
    AppRoutingModule
  ],
  providers: [
    AuthenticationService,
    ConstantsService,
  ],
  exports: [NavbarAdminComponent, NavbarRegisteredUserComponent, NavbarUnregisteredUserComponent]
})
export class CoreModule {
  constructor( @Optional() @SkipSelf() parentModule: CoreModule) {
    if (parentModule) {
      throw new Error('CoreModule has already been loaded. You should only import Core modules in the AppModule only.');
    }
  }
}
