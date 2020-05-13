import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarAdminComponent } from './navbar/navbar-admin/navbar-admin.component';
import { NavbarRegisteredUserComponent } from './navbar/navbar-registered-user/navbar-registered-user.component';
import { NavbarUnregisteredUserComponent } from './navbar/navbar-unregistered-user/navbar-unregistered-user.component';



@NgModule({
  declarations: [NavbarAdminComponent, NavbarRegisteredUserComponent, NavbarUnregisteredUserComponent],
  imports: [
    CommonModule
  ]
})
export class CoreModule { }
