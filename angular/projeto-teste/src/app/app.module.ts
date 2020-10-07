import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HelloComponent } from './hello/hello.component';
import { BemVindoComponent } from './bem-vindo/bem-vindo.component';
import { FormsModule } from '@angular/forms';
import { FuncionarioCardComponent } from './funcionario-card/funcionario-card.component';

@NgModule({
  declarations: [
    AppComponent,
    HelloComponent,
    BemVindoComponent,
    FuncionarioCardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
