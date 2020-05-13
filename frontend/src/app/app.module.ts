import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material/material.module';
import { AuthenticationModule } from './authentication/authentication.module';
import { ToastrModule } from 'ngx-toastr';
import { HomeComponent } from './home/home.component';
import { CoreModule } from './core/core.module';
import { SharedModule } from './shared/shared.module';
import { JwtInterceptor } from './core/interceptors/jwt-interceptor.interceptor';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MaterialModule,
    AuthenticationModule,
    ToastrModule.forRoot({
      progressBar: true,
      timeOut: 4000,
      closeButton: true,
      positionClass: 'toast-bottom-right',
      preventDuplicates: true
    }),
    CoreModule,
    SharedModule,
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
