import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material/material.module';
import { AuthenticationModule } from './authentication/authentication.module';
import { ToastrModule } from 'ngx-toastr';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    AuthenticationModule,
    ToastrModule.forRoot({
      progressBar: true,
      timeOut: 4000,
      closeButton: true,
      positionClass: 'toast-bottom-right',
      preventDuplicates: true
    }),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
